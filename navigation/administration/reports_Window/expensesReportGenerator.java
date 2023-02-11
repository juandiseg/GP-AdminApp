package navigation.administration.reports_Window;

import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import java.io.FileOutputStream;
import componentsFood.productIngredients;
import util.iReportable;
import java.util.ArrayList;

public class expensesReportGenerator implements iReportable {
    
    private Workbook workbook = new XSSFWorkbook();
    private CellStyle style = workbook.createCellStyle();
    private DataFormat format = workbook.createDataFormat();


    public void generateReport(String from, String to) throws Exception {
        Sheet sheet = workbook.createSheet("Expenses Report");
        style.setDataFormat(format.getFormat("_(€* #,##0.00_);_(€* (#,##0.00);_(€* \"-\"??_);_(@_)"));

        int row = 1;
        row = productData(sheet, row, from, to);

        FileOutputStream fileOut = new FileOutputStream("Expenses" +from+ "-"+to+".xlsx");
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
        tempRow.createCell(1).setCellValue("Cost Since");
        tempRow.createCell(2).setCellValue("Product");
        tempRow.createCell(3).setCellValue("Quantity");
        tempRow.createCell(4).setCellValue("Ingredients");
        tempRow.createCell(5).setCellValue("Quantity");
        tempRow.createCell(6).setCellValue("Price");
        tempRow.createCell(7).setCellValue("Quantity in Product");
        tempRow.createCell(8).setCellValue("Total Cost");
        return ++row;
    }

    private int productSales(Sheet sheet, String from, String to, int row){
        reportsAPI managerDB = new reportsAPI();
        ArrayList<ArrayList<productIngredients>> productIngredientsLists = managerDB.getAllProductIngredients();
        for(ArrayList<productIngredients> bigTemp: productIngredientsLists){
            for(int i=0; i<bigTemp.size(); i++){
                productIngredients temp = bigTemp.get(i);
                managerDB.addIngredientsToProductIngredients(temp);
                productIngredients tempNext = null;
                String nextDate = "";
                if(i+1 < bigTemp.size()){
                    tempNext = bigTemp.get(i+1);
                    nextDate = tempNext.getDate();
                }
                int amountSold = managerDB.getNumberSoldProduct(temp.getProductID(), temp.getDate(), nextDate, from, to);
                temp.setNumberProductsSold(amountSold);
            }
        }
        for(ArrayList<productIngredients> aTemp : productIngredientsLists){
            for(productIngredients temp : aTemp){
                temp.print();
            }
        }
        return row+2;
    }

}