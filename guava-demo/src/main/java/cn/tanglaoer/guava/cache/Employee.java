package cn.tanglaoer.guava.cache;

import lombok.Data;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @Date 2023/4/29
 */
@Data
public class Employee {
    private String name;
    private String dept;
    private String empID;

    public Employee(String name, String dept, String empID) {
        this.name = name;
        this.dept = dept;
        this.empID = empID;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", dept='" + dept + '\'' +
                ", empID='" + empID + '\'' +
                '}';
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("The name " + getName() + " will be GC.");
        super.finalize();
    }
}
