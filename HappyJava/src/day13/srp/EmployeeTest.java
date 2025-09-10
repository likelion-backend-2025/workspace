package day13.srp;


//급여계산만 담당
 class Calculator {

    public double calculatePay(Employee employee) {
        return employee.getSalary() * 2.0; //2.0은 보너스!
    }
}

//직원 데이터만 담당해야함
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


//보고서 생성 책임
 class ReportGenerate {
    public String generateReport(Employee employee) {
        return employee.getName() + "의 월급은 " + employee.getSalary();
    }
}

//db 저장만 담당
 class saveEmployee {
    public void saveEmp(Employee employee) {
        System.out.println(employee.getName() + "데이터베이스에 저장했습니다.");
    }
}

public class EmployeeTest {
    public static void main(String[] args) {
        Employee em = new Employee("윤수정", 90000);

        Calculator calc = new Calculator();
        saveEmployee empDB = new saveEmployee();
        ReportGenerate report = new ReportGenerate();

        //보고서
        //report.generateReport(em);
        System.out.println(report.generateReport(em));

        //계산된 월급
        double pay = calc.calculatePay(em);
        System.out.println("계산된 급여는 " + pay);

    }
}