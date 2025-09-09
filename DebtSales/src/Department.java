
import java.util.Scanner;

public class Department {
    public static final int MAX_DEPARTMENTS = 4;
    public static final int MONTHS = 3;

    // sales[d][m] : d = 0..3 (dept 1..4), m = 0..2 (month 1..3)
    private double[][] sales;

    // Constructor: initialize sales to NaN (meaning: no data entered)
    public Department() {
        sales = new double[MAX_DEPARTMENTS][MONTHS];
        for (int d = 0; d < MAX_DEPARTMENTS; d++) {
            for (int m = 0; m < MONTHS; m++) {
                sales[d][m] = Double.NaN;
            }
        }
    }

    // populateArray: get user input for the departments that exist
    public void populateArray() {
        Scanner sc = new Scanner(System.in);
        int numDepartments;
        do {
            System.out.print("Enter number of departments with sales (1-" + MAX_DEPARTMENTS + "): ");
            while (!sc.hasNextInt()) {
                System.out.print("Please enter an integer: ");
                sc.next();
            }
            numDepartments = sc.nextInt();
        } while (numDepartments < 1 || numDepartments > MAX_DEPARTMENTS);

        for (int d = 0; d < numDepartments; d++) {
            System.out.println("\nDepartment " + (d + 1) + ":");
            for (int m = 0; m < MONTHS; m++) {
                double value;
                do {
                    System.out.print("  Enter sales for month " + (m + 1) + ": ");
                    while (!sc.hasNextDouble()) {
                        System.out.print("  Please enter a number: ");
                        sc.next();
                    }
                    value = sc.nextDouble();
                    if (value < 0) System.out.println("  Sales cannot be negative. Enter again.");
                } while (value < 0);
                sales[d][m] = value;
            }
        }
        // NOTE: do NOT close the scanner here because it would close System.in for the caller
    }

    // Returns average sales for each month across departments that have data
    public double[] calculateAvgSalesPerMonth() {
        double[] avg = new double[MONTHS];
        for (int m = 0; m < MONTHS; m++) {
            double sum = 0.0;
            int count = 0;
            for (int d = 0; d < MAX_DEPARTMENTS; d++) {
                if (!Double.isNaN(sales[d][m])) {
                    sum += sales[d][m];
                    count++;
                }
            }
            avg[m] = (count > 0) ? (sum / count) : Double.NaN;
        }
        return avg;
    }

    // Returns average sales for each department across the months (or NaN if that dept has no data)
    public double[] calculateAvgSalesPerDepart() {
        double[] avg = new double[MAX_DEPARTMENTS];
        for (int d = 0; d < MAX_DEPARTMENTS; d++) {
            double sum = 0.0;
            int count = 0;
            for (int m = 0; m < MONTHS; m++) {
                if (!Double.isNaN(sales[d][m])) {
                    sum += sales[d][m];
                    count++;
                }
            }
            avg[d] = (count > 0) ? (sum / count) : Double.NaN;
        }
        return avg;
    }

    // Receives month (1..3). Returns department number (1-based) with highest sales for that month, or -1 if no data
    public int determineHighestMonthlySales(int month) {
        if (month < 1 || month > MONTHS) throw new IllegalArgumentException("Month must be 1-" + MONTHS);
        int m = month - 1;
        double max = Double.NEGATIVE_INFINITY;
        int bestDept = -1;
        for (int d = 0; d < MAX_DEPARTMENTS; d++) {
            if (!Double.isNaN(sales[d][m])) {
                if (sales[d][m] > max) {
                    max = sales[d][m];
                    bestDept = d + 1; // return 1-based department number
                }
            }
        }
        return bestDept;
    }

    // Receives department number (1..4). Returns month number (1-based) with highest sales for that department, or -1 if no data
    public int determineHighestDepartSales(int department) {
        if (department < 1 || department > MAX_DEPARTMENTS) throw new IllegalArgumentException("Department must be 1-" + MAX_DEPARTMENTS);
        int d = department - 1;
        double max = Double.NEGATIVE_INFINITY;
        int bestMonth = -1;
        for (int m = 0; m < MONTHS; m++) {
            if (!Double.isNaN(sales[d][m])) {
                if (sales[d][m] > max) {
                    max = sales[d][m];
                    bestMonth = m + 1;
                }
            }
        }
        return bestMonth;
    }

    // Utility to print the matrix (for testing)
    public void displaySales() {
        System.out.println("\nSales table (N/A = no data):");
        System.out.printf("%-10s","Dept\\Month");
        for (int m = 0; m < MONTHS; m++) System.out.printf("%12d", m + 1);
        System.out.println();

        for (int d = 0; d < MAX_DEPARTMENTS; d++) {
            System.out.printf("%-10s", "Dept " + (d + 1));
            for (int m = 0; m < MONTHS; m++) {
                if (Double.isNaN(sales[d][m])) System.out.printf("%12s", "N/A");
                else System.out.printf("%12.0f", sales[d][m]);
            }
            System.out.println();
        }
    }
}



