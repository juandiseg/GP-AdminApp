package navigation.administration.reportsNav.reportsGeneration;

public class reportGeneratorFactory {

    // A factory is implemented for the report generator, as it is a functionality
    // which may need to be extended in the future. The GUI for generating excel
    // reports could also be used for generating any other type of report as well.
    // It is only necessary to modify this class in order for "mainReports" to have
    // access to newly implemented reports.

    public static iReportable createReportGenerator(String request) {
        iReportable reportGenerator = null;
        if ("Sales Report".equals(request)) {
            return new salesReportGenerator();
        } else if ("Expenses Report".equals(request)) {
            return new expensesReportGenerator();
        }
        return reportGenerator;
    }

    public static String[] getReportTypes() {
        return new String[] { "Sales Report", "Expenses Report" };
    }
}
