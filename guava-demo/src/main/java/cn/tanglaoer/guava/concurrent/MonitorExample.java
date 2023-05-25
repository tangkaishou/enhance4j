package cn.tanglaoer.guava.concurrent;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @Date 2023/4/26
 */
public class MonitorExample {

    public static void main(String[] args) {
        final Synchronized sync = new Synchronized();

        final AtomicInteger COUNTER = new AtomicInteger(0);

        for (int i = 0; i <= 3; i++) {
            new Thread(() -> {
                for (;;) {
                    try {
                        int data = COUNTER.getAndIncrement();
                        System.out.println(Thread.currentThread() + " offer " + data);
                        sync.offer(data);
                        TimeUnit.MINUTES.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        for (int i = 0; i <= 2; i++) {
            new Thread(() -> {
                for(;;) {
                    try {
                        int data = sync.take();
                        System.out.println(Thread.currentThread() + " take " + data);
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    static class Synchronized { private final LinkedList<Integer> queue = new LinkedList<>(); private final int MAX = 10;
        public void offer(int value) {
            synchronized (queue) {
                while (queue.size() >= MAX)  {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                queue.addLast(value);
                queue.notifyAll();
            }
        }

        public int take() {
            synchronized (queue) {
                while (queue.isEmpty()) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            Integer value = queue.removeFirst();
            queue.notifyAll();
            return value;
        }
    }

    static class LockCondition { private final ReentrantLock lock = new ReentrantLock();

        private final Condition FULL_CONDTION = lock.newCondition();

        private final Condition EMPTY_CONDITION = lock.newCondition();

        private final LinkedList<Integer> queue = new LinkedList<>();

        private final int MAX = 10;

        public void offer(int value) {
            try {
                lock.lock();
                while (queue.size() >= MAX) {
                    FULL_CONDTION.await();
                }
                queue.addLast(value);
                EMPTY_CONDITION.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public int take() {
            Integer value = null;
            try {
                lock.lock();
                while (queue.isEmpty()) {
                    EMPTY_CONDITION.wait();
                }
                value = queue.removeFirst();
                FULL_CONDTION.signalAll();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
            return value;
        }

        private <T> void doAction(Consumer<T> consumer, T t) {
            try {
                lock.lock();
                consumer.accept(t);
            }finally {
                lock.unlock();
            }
        }
    }

    static class MonitorGuard {
        
    }
}

