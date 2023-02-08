import java.io.FileOutputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.*;
import ch.qos.logback.classic.*;
import componentsFood.product;
import navigation.administration.shifts_Window.shiftsAPI;



public class testingExcel {
    public static void main(String[] args) throws Exception {

        // Create a Workbook
        Workbook workbook = new XSSFWorkbook();

        // Create a Sheet
        Sheet sheet = workbook.createSheet("Sales Report");

        sheet.createRow(1).createCell(1).setCellValue("PRODUCT SALES");
        Row row3 = sheet.createRow(3);
        row3.createCell(1).setCellValue("Price Since");
        row3.createCell(2).setCellValue("Product");
        row3.createCell(3).setCellValue("Quantity");
        row3.createCell(4).setCellValue("Price");
        row3.createCell(5).setCellValue("Total");
        int i = 0;
        fillTable(sheet);

        //for(product temp : productsList){
        //    sheet.createRow(3+i).createCell(1).setCellValue(temp.getName());
        //    i++;
        //}

        // Write the Workbook to a file
        FileOutputStream fileOut = new FileOutputStream("ExcelFile.xlsx");
        workbook.write(fileOut);
        fileOut.close();

        // Closing the workbook
        workbook.close();
    }
    public static void fillTable(Sheet sheet){
        testingAPI managerDB = new testingAPI();
        ArrayList<product> productsList = managerDB.getAllProducts();
        int i = 4;
        for(product temp: productsList){
            int amountSold = managerDB.getNumberProductSoldSimple(temp.getId());
            if(amountSold > 0){
                Row tempRow = sheet.createRow(i);
                tempRow.createCell(1).setCellValue(temp.getDate());
                tempRow.createCell(2).setCellValue(temp.getName());
                tempRow.createCell(3).setCellValue(managerDB.getNumberProductSoldSimple(temp.getId()));
                tempRow.createCell(4).setCellValue(temp.getPrice());
                tempRow.createCell(5).setCellValue(temp.getPrice() * amountSold);
                i++;
            }
        }
    }

}