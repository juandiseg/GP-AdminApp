package util;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public abstract class iReportable {
    
    private Workbook workbook = new XSSFWorkbook();
    private DataFormat format;
    public static CellStyle currencyStyle;
    public static CellStyle boldStyle;
    public static CellStyle roundingStyle;
    public static CellStyle timeStyle;
    private Font boldFont = workbook.createFont();

    public iReportable(){
        format = workbook.createDataFormat();
        currencyStyle = workbook.createCellStyle();
        boldStyle = workbook.createCellStyle();
        roundingStyle = workbook.createCellStyle();
        timeStyle = workbook.createCellStyle();
        currencyStyle.setDataFormat(format.getFormat("_(€* #,##0.00_);_(€* (#,##0.00);_(€* \"-\"??_);_(@_)"));
        boldFont.setBold(true);
        boldStyle.setDataFormat(format.getFormat("_(€* ###,##0.00_);_(€* (###,##0.00);_(€* \"-\"??_);_(@_)"));
        boldStyle.setFont(boldFont);
        roundingStyle.setDataFormat((format.getFormat("#####0.00")));
        timeStyle.setDataFormat(format.getFormat("HH:MM"));
    }

    public abstract void generateReport(String from, String to) throws Exception;

    public Workbook getWorkbook(){
        return workbook;
    }

}
