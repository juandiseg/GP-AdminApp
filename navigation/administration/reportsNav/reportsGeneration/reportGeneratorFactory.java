package navigation.administration.reportsNav.reportsGeneration;

public class reportGeneratorFactory {
    public static iReportable createReportGenerator(String request) {
        iReportable reportGenerator = null;
        if ("Sales Report".equals(request)) {
            return new salesReportGenerator();
        } else if ("Expenses Report".equals(request)) {
            return new expensesReportGenerator();
        } else if ("General Report".equals(request)) {
            return new generalReportGenerator();
        }
        return reportGenerator;
    }

    public static String[] getReportTypes() {
        return new String[] { "Sales Report", "Expenses Report", "General Report" };
    }
}
