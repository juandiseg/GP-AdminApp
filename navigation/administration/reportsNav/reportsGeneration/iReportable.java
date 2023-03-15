package navigation.administration.reportsNav.reportsGeneration;

public interface iReportable {

    public abstract void generateReport(String from, String to, String folderPath) throws Exception;

}
