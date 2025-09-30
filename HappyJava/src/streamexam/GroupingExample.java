package streamexam;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupingExample {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", "IT", 5000),
                new Employee("Bob", "HR", 4000),
                new Employee("Charlie", "IT", 5500),
                new Employee("David", "HR", 4500),
                new Employee("Eve", "Sales", 4800)
        );

        Map<String, List<Employee>> byDepartment = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));


        System.out.println("=====부서별 직원:=====");
        byDepartment.forEach((dept, emps) -> {
            System.out.println(dept + ": " +
                    emps.stream().map(Employee::getName).collect(Collectors.toList()));
        });


        // 부서별 평균 급여
        Map<String, Double> avgSalaryByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                ));

        System.out.println("\n부서별 평균 급여:");
        avgSalaryByDept.forEach((dept, avg) ->
                System.out.println(dept + ": " + avg)
        );

        // 다단계 그룹화
        Map<String, Map<Boolean, List<Employee>>> complexGrouping =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.partitioningBy(e -> e.getSalary() > 4500)
                        ));


        System.out.println("===========다단계 그룹화 =================");
        complexGrouping.forEach((dept, emps) -> {
            System.out.println("부서 :" + dept);

            //급여 > 4500 (true)
            List<Employee> highSalEmp = emps.get(true);
            List<String> highSalEmpNames = highSalEmp.stream()
                    .map(Employee::getName)
                    .collect(Collectors.toList());
            System.out.println(" - sal > 4500 (true) :" + highSalEmpNames);

            List<Employee> nomalSalEmp = emps.get(false);
            List<String> nomalSalEmpNames = nomalSalEmp.stream()
                    .map(Employee::getName)
                    .collect(Collectors.toList());
            System.out.println(" - sal <= 4500 (true) :" + nomalSalEmpNames);


        });





    }


    static class Employee {
        private String name;
        private String department;
        private double salary;

        public Employee(String name, String department, double salary) {
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        public String getName() { return name; }
        public String getDepartment() { return department; }
        public double getSalary() { return salary; }
    }
}
