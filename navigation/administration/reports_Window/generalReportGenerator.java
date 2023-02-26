package navigation.administration.reports_Window;

import org.apache.poi.ss.usermodel.*;
import java.io.FileOutputStream;

import util.iReportable;

public class generalReportGenerator extends iReportable {

    public void generateReport(String from, String to, String folderPath) throws Exception {
        Sheet sheet = getWorkbook().createSheet("General Report");
        int row = 1;
        row = new expensesReportGenerator().employeeData(sheet, row, from, to);
        String fileName = "GENERAL " + from.substring(8, 10) + "-" + from.substring(5, 7) + "~" + to.substring(8, 10)
                + "-" + to.substring(5, 7) + ".xlsx";
        FileOutputStream fileOut = new FileOutputStream(fileName);
        getWorkbook().write(fileOut);
        fileOut.close();
        getWorkbook().close();
    }

}