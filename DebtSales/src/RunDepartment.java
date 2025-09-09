
public class RunDepartment {
    public static void main(String[] args) {
        Department dept = new Department();

        // Populate sales with user input
        dept.populateArray();

        // Calculate and display average sales per department
        double[] avgDepts = dept.calculateAvgSalesPerDepart();
        System.out.println("\nAverage Sales per Department:");
        for (int i = 0; i < avgDepts.length; i++) {
            if (!Double.isNaN(avgDepts[i])) {
                System.out.printf("Department %d: %.2f\n", i + 1, avgDepts[i]);
            }
        }

        // Calculate and display average sales per month
        double[] avgMonths = dept.calculateAvgSalesPerMonth();
        System.out.println("\nAverage Sales per Month:");
        for (int i = 0; i < avgMonths.length; i++) {
            if (!Double.isNaN(avgMonths[i])) {
                    
                System.out.printf("Month %d: %.2f\n", i + 1, avgMonths[i]);
            }
        }

        // Determine and display the month with the highest sales per department
        System.out.println("\nMonth with Highest Sales per Department:");
        for (int d = 1; d <= Department.MAX_DEPARTMENTS; d++) {
            int bestMonth = dept.determineHighestDepartSales(d);
            if (bestMonth != -1) {
                System.out.printf("Department %d: Month %d\n", d, bestMonth);
            }
        }

        // Determine and display the department with the highest sales per month
        System.out.println("\nDepartment with Highest Sales per Month:");
        for (int m = 1; m <= 3; m++) {
            int bestDept = dept.determineHighestMonthlySales(m);
            if (bestDept != -1) {
                System.out.printf("Month %d: Department %d\n", m, bestDept);
            }
        }
    }
}
