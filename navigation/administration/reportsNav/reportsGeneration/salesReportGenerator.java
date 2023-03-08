package navigation.administration.reportsNav.reportsGeneration;

import org.apache.poi.ss.usermodel.*;
import java.io.FileOutputStream;
import componentsFood.product;
import util.databaseAPIs.dateInverter;
import util.databaseAPIs.reportsAPI;
import componentsFood.menu;
import java.util.ArrayList;

public class salesReportGenerator extends iReportable {

    private CellStyle style = getWorkbook().createCellStyle();
    private DataFormat format = getWorkbook().createDataFormat();

    public void generateReport(String from, String to, String folderPath) throws Exception {
        Sheet sheet = getWorkbook().createSheet("Sales Report");
        style.setDataFormat(format.getFormat("_(€* #,##0.00_);_(€* (#,##0.00);_(€* \"-\"??_);_(@_)"));

        from = dateInverter.invert(from);
        to = dateInverter.invert(to);

        int row = 1;
        row = productSales(sheet, row, from, to);
        row = menuSales(sheet, row, from, to);

        String excelName = "/Sales " + from.substring(8, 10) + "-" + from.substring(5, 7)
                + "~" + to.substring(8, 10) + "-" + to.substring(5, 7) + ".xlsx";
        FileOutputStream fileout = new FileOutputStream(folderPath.concat(excelName));
        getWorkbook().write(fileout);
        fileout.close();
        getWorkbook().close();
    }

    private int productHeaders(Sheet theSheet, int row) {
        theSheet.createRow(row).createCell(1).setCellValue("PRODUCT SALES");
        row += 2;
        Row tempRow = theSheet.createRow(row);
        tempRow.createCell(1).setCellValue("Price Since");
        tempRow.createCell(2).setCellValue("Product");
        tempRow.createCell(3).setCellValue("Quantity");
        tempRow.createCell(4).setCellValue("Price");
        tempRow.createCell(5).setCellValue("Total");
        return ++row;
    }

    private int productSales(Sheet sheet, int row, String from, String to) {
        int initialRow = row;
        ArrayList<ArrayList<product>> productsLists = reportsAPI.getAllProducts();
        if (!thereAreProductSales(productsLists, from, to))
            return row;
        row = productHeaders(sheet, row);
        for (ArrayList<product> bigTemp : productsLists) {
            for (int i = 0; i < bigTemp.size(); i++) {
                product temp = bigTemp.get(i);
                product tempNext = null;
                if (i + 1 < bigTemp.size())
                    tempNext = bigTemp.get(i + 1);
                int amountSold = reportsAPI.getNumberSoldProducts(temp, tempNext, from, to);
                if (amountSold > 0) {
                    Row tempRow = sheet.createRow(row);
                    tempRow.createCell(1).setCellValue(temp.getDate());
                    tempRow.createCell(2).setCellValue(temp.getName());
                    tempRow.createCell(3).setCellValue(amountSold);
                    Cell tempCell1 = tempRow.createCell(4);
                    tempCell1.setCellValue(temp.getPrice());
                    tempCell1.setCellStyle(style);
                    Cell tempCell2 = tempRow.createCell(5);
                    tempCell2.setCellFormula("D" + (row + 1) + "*E" + (row + 1));
                    tempCell2.setCellStyle(style);
                    row++;
                }
            }
        }
        Cell summarySales = sheet.createRow(row).createCell(5);
        summarySales.setCellFormula("SUM(F" + (initialRow + 1) + ":F" + row + ")");
        summarySales.setCellStyle(style);
        return row + 2;
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

    private int menuHeaders(Sheet theSheet, int row) {
        theSheet.createRow(row).createCell(1).setCellValue("MENU SALES");
        row += 2;
        Row tempRow = theSheet.createRow(row);
        tempRow.createCell(1).setCellValue("Price Since");
        tempRow.createCell(2).setCellValue("Menu");
        tempRow.createCell(3).setCellValue("Quantity");
        tempRow.createCell(4).setCellValue("Price");
        tempRow.createCell(5).setCellValue("Total");
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

    private int menuSales(Sheet sheet, int row, String from, String to) {
        int initialRow = row;
        ArrayList<ArrayList<menu>> menusLists = reportsAPI.getAllMenus();
        if (!thereAreMenuSales(menusLists, from, to))
            return row;
        row = menuHeaders(sheet, row);
        for (ArrayList<menu> listOfMenu : menusLists) {
            for (int i = 0; i < listOfMenu.size(); i++) {
                menu temp = listOfMenu.get(i);
                menu tempNext = null;
                if (i + 1 < listOfMenu.size())
                    tempNext = listOfMenu.get(i + 1);
                int amountSold = reportsAPI.getNumberSoldMenus(temp, tempNext, from, to);
                if (amountSold > 0) {
                    Row tempRow = sheet.createRow(row);
                    tempRow.createCell(1).setCellValue(temp.getDate());
                    tempRow.createCell(2).setCellValue(temp.getName());
                    tempRow.createCell(3).setCellValue(amountSold);
                    Cell tempCell1 = tempRow.createCell(4);
                    tempCell1.setCellValue(temp.getPrice());
                    tempCell1.setCellStyle(style);
                    Cell tempCell2 = tempRow.createCell(5);
                    tempCell2.setCellFormula("D" + (row + 1) + "*E" + (row + 1));
                    tempCell2.setCellStyle(style);
                    row++;
                }
            }
        }
        Cell summarySales = sheet.createRow(row).createCell(5);
        summarySales.setCellFormula("SUM(F" + (initialRow + 1) + ":F" + row + ")");
        summarySales.setCellStyle(style);
        return row + 2;
    }

}