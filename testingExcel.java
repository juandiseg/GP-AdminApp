import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat.Style;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import componentsFood.menu;
import componentsFood.product;



public class testingExcel {
    
    static Workbook workbook = new XSSFWorkbook();
    static CellStyle style = workbook.createCellStyle();
    static DataFormat format = workbook.createDataFormat();

    public static void main(String[] args) throws Exception {
        // Create a Sheet
        Sheet sheet = workbook.createSheet("Sales Report");
        style.setDataFormat(format.getFormat("_(€* #,##0.00_);_(€* (#,##0.00);_(€* \"-\"??_);_(@_)"));

        int row = 1;
        sheet.createRow(row).createCell(1).setCellValue("PRODUCT SALES");
        row += 2;
        Row row3 = sheet.createRow(row);
        row3.createCell(1).setCellValue("Price Since");
        row3.createCell(2).setCellValue("Product");
        row3.createCell(3).setCellValue("Quantity");
        row3.createCell(4).setCellValue("Price");
        row3.createCell(5).setCellValue("Total");
        row = productSales(sheet, row + 1);

        sheet.createRow(row).createCell(1).setCellValue("MENU SALES");
        row += 2;
        Row rowI = sheet.createRow(row);
        rowI.createCell(1).setCellValue("Price Since");
        rowI.createCell(2).setCellValue("Menu");
        rowI.createCell(3).setCellValue("Quantity");
        rowI.createCell(4).setCellValue("Price");
        rowI.createCell(5).setCellValue("Total");
        row = menuSales(sheet, row+1);

        FileOutputStream fileOut = new FileOutputStream("ExcelFile.xlsx");
        workbook.write(fileOut);
        fileOut.close();

        // Closing the workbook
        workbook.close();
    }
    public static int productSales(Sheet sheet, int row){
        int initialRow = row;
        testingAPI managerDB = new testingAPI();
        ArrayList<ArrayList<product>> productsLists = managerDB.getAllProducts();
        for(ArrayList<product> bigTemp: productsLists){
            for(int i=0; i<bigTemp.size(); i++){
                product temp = bigTemp.get(i);
                product tempNext = null;
                if(i+1 < bigTemp.size())
                tempNext = bigTemp.get(i+1);
                int amountSold = managerDB.getNumberSoldProduct(temp, tempNext);
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

    public static int menuSales(Sheet sheet, int row){
        int initialRow = row;
        testingAPI managerDB = new testingAPI();
        ArrayList<ArrayList<menu>> menusLists = managerDB.getAllMenus();
        for(ArrayList<menu> listOfMenu: menusLists){
            for(int i=0; i<listOfMenu.size(); i++){
                menu temp = listOfMenu.get(i);
                menu tempNext = null;
                if(i + 1 < listOfMenu.size())
                tempNext = listOfMenu.get(i+1);
                int amountSold = managerDB.getNumberSoldMenu(temp, tempNext);
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

    public char turnColumnIntoLetter(int columnNumber){
        return ((char)(columnNumber + 65));
    }

}