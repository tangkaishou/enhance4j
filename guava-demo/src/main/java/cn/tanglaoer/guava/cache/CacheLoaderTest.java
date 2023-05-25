package cn.tanglaoer.guava.cache;

import com.google.common.cache.*;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @Date 2023/4/29
 */
public class CacheLoaderTest {

    @Test
    public void testBasic() throws ExecutionException {
        LoadingCache<String, Employee> cache = CacheBuilder.newBuilder()
                .maximumSize(10)
                .expireAfterAccess(30, TimeUnit.SECONDS)
                .build(new CacheLoader<String, Employee>() {
                    @Override
                    public Employee load(String key) throws Exception {
                        return findEmployeeByName(key);
                    }
                });
        Employee alex = cache.get("Alex");
        System.out.println(alex);

        Employee temp = cache.get("Alex");
        System.out.println(temp);

        System.out.println(cache.get("nullvalue"));
    }

    private Employee findEmployeeByName(final String name) {
        System.out.println("The employee " + name + " is load from DB.");
        if (name.equals("nullvalue")) {
            return null;
        }
        return new Employee(name, name, name);
    }


    @Test
    public void testEvictionBySize() {
        CacheLoader<String, Employee> cacheLoader = createCacheLoader();
        LoadingCache<String, Employee> cache = CacheBuilder.newBuilder().maximumSize(3).build(cacheLoader);
        cache.getUnchecked("tanglaeor");

        cache.getUnchecked("Jack");

        cache.getUnchecked("Guava");

        System.out.println(cache.size());

        cache.getUnchecked("example");

        System.out.println(cache.size());

        assertThat(cache.getIfPresent("tanglaoer"), nullValue());

    }

    private CacheLoader<String, Employee> createCacheLoader() {
        return new CacheLoader<>() {
            @Override
            public Employee load(String key) throws Exception {
                return findEmployeeByName(key);
            }
        };
    }

    @Test
    public void testEvictionByWeigh() {
        Weigher<String, Employee> weigher = (key, employee) -> employee.getName().length() + employee.getEmpID().length()
                + employee.getDept().length();

        LoadingCache<String, Employee> cache = CacheBuilder.newBuilder()
                .maximumWeight(45)
                .concurrencyLevel(1)
                .weigher(weigher)
                .build(createCacheLoader());

        cache.getUnchecked("tanglaeor");

        cache.getUnchecked("Jack");

        cache.getUnchecked("Guava");

        System.out.println(cache.size());

        cache.getUnchecked("example");

        System.out.println(cache.size());
    }
}
