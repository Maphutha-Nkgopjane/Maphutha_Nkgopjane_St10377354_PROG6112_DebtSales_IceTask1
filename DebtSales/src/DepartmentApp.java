
import java.util.Scanner;

public class DepartmentApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Department dept = new Department();

        // Get input from user
        dept.populateArray();

        // Display matrix
        dept.displaySales();

        // Average per month
        double[] avgMonths = dept.calculateAvgSalesPerMonth();
        System.out.println("\nAverage sales per month:");
        for (int i = 0; i < avgMonths.length; i++) {
            System.out.printf("Month %d: %s\n", i + 1, Double.isNaN(avgMonths[i]) ? "N/A" : String.format("%.2f", avgMonths[i]));
        }

        // Average per department
        double[] avgDepts = dept.calculateAvgSalesPerDepart();
        System.out.println("\nAverage sales per department:");
        for (int i = 0; i < avgDepts.length; i++) {
            System.out.printf("Dept %d: %s\n", i + 1, Double.isNaN(avgDepts[i]) ? "N/A" : String.format("%.2f", avgDepts[i]));
        }

        // Highest department for a month
        System.out.print("\nEnter month number (1-3) to find the department with highest sales: ");
        int month = sc.nextInt();
        int bestDept = dept.determineHighestMonthlySales(month);
        if (bestDept == -1) System.out.println("No sales data for that month.");
        else System.out.println("Department with highest sales in month " + month + ": Dept " + bestDept);

        // Highest month for a department
        System.out.print("\nEnter department number (1-4) to find which month had its highest sales: ");
        int dnum = sc.nextInt();
        int bestMonth = dept.determineHighestDepartSales(dnum);
        if (bestMonth == -1) System.out.println("No sales data for that department.");
        else System.out.println("Month with highest sales for Dept " + dnum + ": Month " + bestMonth);

        sc.close();
    }
}
