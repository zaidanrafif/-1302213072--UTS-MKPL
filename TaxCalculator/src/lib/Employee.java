package lib;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Employee {

    private static final int GRADE_1_SALARY = 3000000;
    private static final int GRADE_2_SALARY = 5000000;
    private static final int GRADE_3_SALARY = 7000000;
    private static final double FOREIGNER_SURCHARGE = 1.5;

    private String employeeId;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String address;

    private LocalDate dateJoined;
    private boolean isForeign;
    private boolean isMale;

    private int monthlySalary;
    private int otherMonthlyIncome;
    private int annualDeductible;

    private String spouseName;
    private String spouseIdNumber;

    private List<String> childNames;
    private List<String> childIdNumbers;

    public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, LocalDate dateJoined, boolean isForeign, boolean isMale) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.address = address;
        this.dateJoined = dateJoined;
        this.isForeign = isForeign;
        this.isMale = isMale;

        childNames = new LinkedList<>();
        childIdNumbers = new LinkedList<>();
    }

    public void setMonthlySalary(int grade) {
        switch (grade) {
            case 1:
                monthlySalary = (int) (GRADE_1_SALARY * (isForeign ? FOREIGNER_SURCHARGE : 1));
                break;
            case 2:
                monthlySalary = (int) (GRADE_2_SALARY * (isForeign ? FOREIGNER_SURCHARGE : 1));
                break;
            case 3:
                monthlySalary = (int) (GRADE_3_SALARY * (isForeign ? FOREIGNER_SURCHARGE : 1));
                break;
            default:
                throw new IllegalArgumentException("Invalid grade");
        }
    }

    public void setAnnualDeductible(int deductible) {
        this.annualDeductible = deductible;
    }

    public void setAdditionalIncome(int income) {
        this.otherMonthlyIncome = income;
    }

    public void setSpouse(String spouseName, String spouseIdNumber) {
        this.spouseName = spouseName;
        this.spouseIdNumber = spouseIdNumber;
    }

    public void addChild(String childName, String childIdNumber) {
        childNames.add(childName);
        childIdNumbers.add(childIdNumber);
    }

    public int getAnnualIncomeTax() {
        int monthsWorked = calculateMonthWorkingInYear();
        return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthsWorked, annualDeductible, spouseIdNumber.equals(""), childIdNumbers.size());
    }

    private int calculateMonthWorkingInYear() {
        LocalDate currentDate = LocalDate.now();
        int months = 12;
        if (currentDate.getYear() == dateJoined.getYear()) {
            months = currentDate.getMonthValue() - dateJoined.getMonthValue() + 1;
        }
        return months;
    }
}
