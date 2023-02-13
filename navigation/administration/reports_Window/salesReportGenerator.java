package navigation.administration.reports_Window;

import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import java.io.FileOutputStream;
import componentsFood.product;
import util.iReportable;
import componentsFood.menu;
import java.util.ArrayList;

public class salesReportGenerator extends iReportable {
    
    private Workbook workbook = new XSSFWorkbook();
    private CellStyle style = workbook.createCellStyle();
    private DataFormat format = workbook.createDataFormat();


    public void generateReport(String from, String to) throws Exception {
        Sheet sheet = workbook.createSheet("Sales Report");
        style.setDataFormat(format.getFormat("_(€* #,##0.00_);_(€* (#,##0.00);_(€* \"-\"??_);_(@_)"));

        int row = 1;
        row = productData(sheet, row, from, to);
        row = menuData(sheet, row, from, to);

        FileOutputStream fileOut = new FileOutputStream("Sales" +from+ "-"+to+".xlsx");
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();
    }

    private int productData(Sheet theSheet, int row, String from, String to){
        theSheet.createRow(row).createCell(1).setCellValue("PRODUCT SALES");
        row += 2;
        return productSales(theSheet, from, to, productHeaders(theSheet, row));
    }

    private int productHeaders(Sheet theSheet, int row){
        Row tempRow = theSheet.createRow(row);
        tempRow.createCell(1).setCellValue("Price Since");
        tempRow.createCell(2).setCellValue("Product");
        tempRow.createCell(3).setCellValue("Quantity");
        tempRow.createCell(4).setCellValue("Price");
        tempRow.createCell(5).setCellValue("Total");
        return ++row;
    }

    private int productSales(Sheet sheet, String from, String to, int row){
        int initialRow = row;
        reportsAPI managerDB = new reportsAPI();
        ArrayList<ArrayList<product>> productsLists = managerDB.getAllProducts();
        for(ArrayList<product> bigTemp: productsLists){
            for(int i=0; i<bigTemp.size(); i++){
                product temp = bigTemp.get(i);
                product tempNext = null;
                if(i+1 < bigTemp.size())
                tempNext = bigTemp.get(i+1);
                int amountSold = managerDB.getNumberSoldProduct(temp, tempNext, from, to);
                if(amountSold > 0){
                    Row tempRow = sheet.createRow(row);
                    tempRow.createCell(1).setCellValue(temp.getDate());
                    tempRow.createCell(2).setCellValue(temp.getName());
                    tempRow.createCell(3).setCellValue(amountSold);
                    Cell tempCell1 = tempRow.createCell(4);
                    tempCell1.setCellValue(temp.getPrice());
                    tempCell1.setCellStyle(style);
                    Cell tempCell2 = tempRow.createCell(5);
                    tempCell2.setCellFormula("D"+(row+1)+"*E"+(row+1));
                    tempCell2.setCellStyle(style);
                    row++;
                }
            }
        }
        Cell summarySales = sheet.createRow(row).createCell(5); 
        summarySales.setCellFormula("SUM(F"+(initialRow+1)+":F"+row+")");
        summarySales.setCellStyle(style);
        return row+2;
    }

    private int menuData(Sheet theSheet, int row, String from, String to){
        theSheet.createRow(row).createCell(1).setCellValue("MENU SALES");
        row += 2;
        return menuSales(theSheet, from, to, menuHeaders(theSheet, row));
    }

    private int menuHeaders(Sheet theSheet, int row){
        Row tempRow = theSheet.createRow(row);
        tempRow.createCell(1).setCellValue("Price Since");
        tempRow.createCell(2).setCellValue("Menu");
        tempRow.createCell(3).setCellValue("Quantity");
        tempRow.createCell(4).setCellValue("Price");
        tempRow.createCell(5).setCellValue("Total");
        return ++row;
    }

    private int menuSales(Sheet sheet, String from, String to, int row){
        int initialRow = row;
        reportsAPI managerDB = new reportsAPI();
        ArrayList<ArrayList<menu>> menusLists = managerDB.getAllMenus();
        for(ArrayList<menu> listOfMenu: menusLists){
            for(int i=0; i<listOfMenu.size(); i++){
                menu temp = listOfMenu.get(i);
                menu tempNext = null;
                if(i + 1 < listOfMenu.size())
                tempNext = listOfMenu.get(i+1);
                int amountSold = managerDB.getNumberSoldMenu(temp, tempNext, from, to);
                if(amountSold > 0){
                    Row tempRow = sheet.createRow(row);
                    tempRow.createCell(1).setCellValue(temp.getDate());
                    tempRow.createCell(2).setCellValue(temp.getName());
                    tempRow.createCell(3).setCellValue(amountSold);
                    Cell tempCell1 = tempRow.createCell(4);
                    tempCell1.setCellValue(temp.getPrice());
                    tempCell1.setCellStyle(style);
                    Cell tempCell2 = tempRow.createCell(5);
                    tempCell2.setCellFormula("D"+(row+1)+"*E"+(row+1));
                    tempCell2.setCellStyle(style);
                    row++;
                }
            }
        }
        Cell summarySales = sheet.createRow(row).createCell(5); 
        summarySales.setCellFormula("SUM(F"+(initialRow+1)+":F"+row+")");
        summarySales.setCellStyle(style);
        return row+2;
    }

}