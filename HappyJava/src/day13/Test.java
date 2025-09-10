package day13;

class Employee {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() { return name; }
    public double getSalary() { return salary; }
}

class PayrollCalculator {
    static public double calculatePay(Employee employee) {
        // 급여 계산 로직
        return employee.getSalary() * 12; //
    }
}


class EmployeeRepository {
    static public void save(Employee employee) {
        // DB 저장 로직
        System.out.println(employee.getName() + " 저장");
    }
}

class EmployeeReport {
    static public String writeReport(Employee employee) {
        // 보고서 생성 로직
        return "작성자: " + employee.getName() + ", 월급: " + employee.getSalary();
    }
}

class Test {
    public static void main(String[] args) {
        Employee emp = new Employee("백종현", 300);

        double pay = PayrollCalculator.calculatePay(emp);
        System.out.println("연봉: " + pay);

        EmployeeRepository.save(emp);

        String reportText = EmployeeReport.writeReport(emp);
        System.out.println(reportText);
    }
}