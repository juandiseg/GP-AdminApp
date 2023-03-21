package navigation.administration.reportsNav.reportsGeneration;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import componentsFood.product;
import util.databaseAPIs.reportsAPI;
import util.inputFormatting.dateInverter;
import componentsFood.menu;
import java.util.ArrayList;

public class salesReportGenerator implements iReportable {

    private CellStyle style;
    private DataFormat format;

    public void generateReport(String from, String to, String folderPath) throws Exception {
        Workbook workbook = new XSSFWorkbook();
        style = workbook.createCellStyle();
        format = workbook.createDataFormat();
        Sheet sheet = workbook.createSheet("Sales Report");
        style.setDataFormat(format.getFormat("_(€* #,##0.00_);_(€* (#,##0.00);_(€* \"-\"??_);_(@_)"));

        from = dateInverter.invert(from);
        to = dateInverter.invert(to);

        int row = 1;
        int[] coordinates = productSales(sheet, row, 1, from, to);
        int tempRow = coordinates[0];
        if (coordinates[1] != -1)
            tempRow += 2;
        menuSales(sheet, tempRow, 1, from, to);
        String excelName = "/Sales " + from.substring(8, 10) + "-" + from.substring(5, 7)
                + "~" + to.substring(8, 10) + "-" + to.substring(5, 7) + ".xlsx";
        FileOutputStream fileout = new FileOutputStream(folderPath.concat(excelName));
        workbook.write(fileout);
        fileout.close();
        workbook.close();
    }

    private int productHeaders(Sheet theSheet, int row, int column) {
        Row titleRow = theSheet.getRow(row);
        if (titleRow == null)
            titleRow = theSheet.createRow(row);
        titleRow.createCell(column).setCellValue("PRODUCT SALES");
        row += 2;
        Row tempRow = theSheet.getRow(row);
        if (tempRow == null)
            tempRow = theSheet.createRow(row);
        tempRow.createCell(column).setCellValue("Price Since");
        tempRow.createCell(column + 1).setCellValue("Product");
        tempRow.createCell(column + 2).setCellValue("Quantity");
        tempRow.createCell(column + 3).setCellValue("Price");
        tempRow.createCell(column + 4).setCellValue("Total");
        return ++row;
    }

    public int[] productSales(Sheet sheet, int row, int column, String from, String to) {
        // Writes on the excel sheet starting from the specified row and column the
        // summary of product sales within the given timeframe.

        int initialRow = row;
        ArrayList<ArrayList<product>> productsLists = reportsAPI.getAllProducts();
        if (!thereAreProductSales(productsLists, from, to))
            return new int[] { row, -1 };
        row = productHeaders(sheet, row, column);
        int c = column - 1;
        int[] columns = { 'D', 'E', 'F' };
        char[] columnChar = { (char) (columns[0] + c), (char) (columns[1] + c), (char) (columns[2] + c) };
        for (ArrayList<product> bigTemp : productsLists) {
            for (int i = 0; i < bigTemp.size(); i++) {
                product temp = bigTemp.get(i);
                product tempNext = null;
                if (i + 1 < bigTemp.size())
                    tempNext = bigTemp.get(i + 1);
                int amountSold = reportsAPI.getNumberSoldProducts(temp, tempNext, from, to);
                if (amountSold > 0) {
                    Row tempRow = sheet.getRow(row);
                    if (tempRow == null)
                        tempRow = sheet.createRow(row);
                    tempRow.createCell(column).setCellValue(temp.getDate());
                    tempRow.createCell(column + 1).setCellValue(temp.getName());
                    tempRow.createCell(column + 2).setCellValue(amountSold);
                    Cell tempCell1 = tempRow.createCell(column + 3);
                    tempCell1.setCellValue(temp.getPrice());
                    tempCell1.setCellStyle(style);
                    Cell tempCell2 = tempRow.createCell(column + 4);
                    tempCell2.setCellFormula(columnChar[0] + "" + (row + 1) + "*" + columnChar[1] + "" + (row + 1));
                    tempCell2.setCellStyle(style);
                    row++;
                }
            }
        }
        Row summaryRow = sheet.getRow(row);
        if (summaryRow == null)
            summaryRow = sheet.createRow(row);
        Cell summarySales = summaryRow.createCell(column + 4);

        // Formula to sum up all sales.
        summarySales.setCellFormula(
                "SUM(" + columnChar[2] + "" + (initialRow + 1) + ":" + columnChar[2] + "" + row + ")");
        summarySales.setCellStyle(style);
        return new int[] { row, column + 4 };
    }

    private boolean thereAreProductSales(ArrayList<ArrayList<product>> productsLists, String from, String to) {
        for (ArrayList<product> bigTemp : productsLists) {
            for (int i = 0; i < bigTemp.size(); i++) {
                product temp = bigTemp.get(i);
                product tempNext = null;
                if (i + 1 < bigTemp.size())
                    tempNext = bigTemp.get(i + 1);
                int amountSold = reportsAPI.getNumberSoldProducts(temp, tempNext, from, to);
                if (amountSold != 0)
                    return true;
            }
        }
        return false;
    }

    private int menuHeaders(Sheet theSheet, int row, int column) {
        Row titleRow = theSheet.getRow(row);
        if (titleRow == null)
            titleRow = theSheet.createRow(row);
        titleRow.createCell(column).setCellValue("MENU SALES");
        row += 2;
        Row tempRow = theSheet.getRow(row);
        if (tempRow == null)
            tempRow = theSheet.createRow(row);
        tempRow.createCell(column).setCellValue("Price Since");
        tempRow.createCell(column + 1).setCellValue("Menu");
        tempRow.createCell(column + 2).setCellValue("Quantity");
        tempRow.createCell(column + 3).setCellValue("Price");
        tempRow.createCell(column + 4).setCellValue("Total");
        return ++row;
    }

    private boolean thereAreMenuSales(ArrayList<ArrayList<menu>> menusLists, String from, String to) {
        for (ArrayList<menu> listOfMenu : menusLists) {
            for (int i = 0; i < listOfMenu.size(); i++) {
                menu temp = listOfMenu.get(i);
                menu tempNext = null;
                if (i + 1 < listOfMenu.size())
                    tempNext = listOfMenu.get(i + 1);
                int amountSold = reportsAPI.getNumberSoldMenus(temp, tempNext, from, to);
                if (amountSold != 0)
                    return true;
            }
        }
        return false;
    }

    public int[] menuSales(Sheet sheet, int row, int column, String from, String to) {
        // Writes on the excel sheet starting from the specified row and column the
        // summary of menu sales within the given timeframe.

        int initialRow = row;
        ArrayList<ArrayList<menu>> menusLists = reportsAPI.getAllMenus();
        if (!thereAreMenuSales(menusLists, from, to))
            return new int[] { row, -1 };
        row = menuHeaders(sheet, row, column);
        int c = column - 1;
        int[] columns = { 'D', 'E', 'F' };
        char[] columnChar = { (char) (columns[0] + c), (char) (columns[1] + c), (char) (columns[2] + c) };
        for (ArrayList<menu> listOfMenu : menusLists) {
            for (int i = 0; i < listOfMenu.size(); i++) {
                menu temp = listOfMenu.get(i);
                menu tempNext = null;
                if (i + 1 < listOfMenu.size())
                    tempNext = listOfMenu.get(i + 1);
                int amountSold = reportsAPI.getNumberSoldMenus(temp, tempNext, from, to);
                if (amountSold > 0) {
                    Row tempRow = sheet.getRow(row);
                    if (tempRow == null)
                        tempRow = sheet.createRow(row);
                    tempRow.createCell(column).setCellValue(temp.getDate());
                    tempRow.createCell(column + 1).setCellValue(temp.getName());
                    tempRow.createCell(column + 2).setCellValue(amountSold);
                    Cell tempCell1 = tempRow.createCell(column + 3);
                    tempCell1.setCellValue(temp.getPrice());
                    tempCell1.setCellStyle(style);
                    Cell tempCell2 = tempRow.createCell(column + 4);
                    tempCell2.setCellFormula(columnChar[0] + "" + (row + 1) + "*" + columnChar[1] + "" + (row + 1));
                    tempCell2.setCellStyle(style);
                    row++;
                }
            }
        }
        Row summaryRow = sheet.getRow(row);
        if (summaryRow == null)
            summaryRow = sheet.createRow(row);
        Cell summarySales = summaryRow.createCell(column + 4);
        summarySales
                .setCellFormula("SUM(" + columnChar[2] + "" + (initialRow + 1) + ":" + columnChar[2] + "" + row + ")");
        summarySales.setCellStyle(style);
        return new int[] { row, column + 4 };
    }

}