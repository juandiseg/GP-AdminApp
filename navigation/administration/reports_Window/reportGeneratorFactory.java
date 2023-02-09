package navigation.administration.reports_Window;

import util.iReportable;

public class reportGeneratorFactory {
    public iReportable createReportGenerator(String request){
        iReportable reportGenerator = null;
        if("SALES".equals(request)){
            return new salesReportGenerator();
        } else if ("EXPENSES".equals(request)){
            return new expensesReportGenerator();
        }
        return reportGenerator;
    }
}
