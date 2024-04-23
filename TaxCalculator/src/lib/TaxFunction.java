package lib;

public class TaxFunction {

    private static final double TAX_RATE = 0.05;
    private static final int BASE_EXEMPTION = 54000000;
    private static final int MARRIED_BONUS = 4500000;
    private static final int CHILD_EXEMPTION = 1500000;
    private static final int MAX_CHILDREN = 3;
    private static final int MAX_MONTHS = 12;

    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int monthsWorked, int deductible, boolean isMarried, int numberOfChildren) {
        
        if (monthsWorked > MAX_MONTHS) {
            System.err.println("More than 12 month working per year");
            monthsWorked = MAX_MONTHS;
        }

        numberOfChildren = Math.min(numberOfChildren, MAX_CHILDREN);

        int taxExemption = calculateExemption(isMarried, numberOfChildren);

        int taxableIncome = ((monthlySalary + otherMonthlyIncome) * monthsWorked) - deductible - taxExemption;

        int tax = (int) Math.round(TAX_RATE * taxableIncome);

        return Math.max(tax, 0);
    }

    private static int calculateExemption(boolean isMarried, int numberOfChildren) {
        int exemption = BASE_EXEMPTION;

        if (isMarried) {
            exemption += MARRIED_BONUS;
        }

        exemption += Math.min(numberOfChildren, MAX_CHILDREN) * CHILD_EXEMPTION;

        return exemption;
    }

    public static <TaxParameters> int calculateTax(TaxParameters taxParameters) {
        throw new UnsupportedOperationException("Unimplemented method 'calculateTax'");
    }
}
