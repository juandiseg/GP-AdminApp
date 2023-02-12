package navigation.administration.reports_Window;

import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import java.io.FileOutputStream;

import componentsFood.ingredient;
import componentsFood.productIngredients;
import util.iReportable;
import java.util.ArrayList;

public class expensesReportGenerator implements iReportable {
    
    private Workbook workbook = new XSSFWorkbook();
    private CellStyle currencyStyle = workbook.createCellStyle();
    private DataFormat format = workbook.createDataFormat();
    private CellStyle boldStyle = workbook.createCellStyle();
    private Font boldFont = workbook.createFont();
    private CellStyle roundingStyle = workbook.createCellStyle();

    public void generateReport(String from, String to) throws Exception {
        Sheet sheet = workbook.createSheet("Expenses Report");
        currencyStyle.setDataFormat(format.getFormat("_(€* #,##0.00_);_(€* (#,##0.00);_(€* \"-\"??_);_(@_)"));
        boldFont.setBold(true);
        boldStyle.setDataFormat(format.getFormat("_(€* #,##0.00_);_(€* (#,##0.00);_(€* \"-\"??_);_(@_)"));
        boldStyle.setFont(boldFont);
        roundingStyle.setDataFormat((format.getFormat("0.00")));
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
        row = productHeaders(theSheet, row);
        ArrayList<ArrayList<productIngredients>> listListsSales = generateProductSales(theSheet, from, to);
        printProductSales(theSheet, listListsSales, row);
        
        
        return row;
    }

    public int printProductSales(Sheet sheet, ArrayList<ArrayList<productIngredients>> listList, int row){
        reportsAPI managerDB = new reportsAPI();
        for(ArrayList<productIngredients> bigTemp : listList){
            for(productIngredients temp : bigTemp){
                if(temp.getNumberProductsSold()!=0){
                    Row tempRow = sheet.createRow(row);
                    tempRow.createCell(1).setCellValue(temp.getIngredientsDate());
                    tempRow.createCell(2).setCellValue(managerDB.getNameOfProduct(temp.getProductID(), temp.getProductDate()));
                    tempRow.createCell(3).setCellValue(temp.getNumberProductsSold());
                    int originalRow = row;
                    for(int i = 0; i<temp.getIngredients().size(); i++){
                        ingredient tempIngr = temp.getIngredients().get(i);
                        tempRow.createCell(4).setCellValue(tempIngr.getName());
                        tempRow.createCell(5).setCellValue(tempIngr.getAmount());
                        Cell cell6 = tempRow.createCell(6);
                        cell6.setCellValue(tempIngr.getPrice());
                        cell6.setCellStyle(currencyStyle);
                        Cell cell7 = tempRow.createCell(7);
                        cell7.setCellValue(temp.getQuantity().get(i));
                        cell7.setCellStyle(roundingStyle);
                        Cell cell8 = tempRow.createCell(8);
                        cell8.setCellFormula("D"+(originalRow+1)+"*H"+(row+1)+"/F"+(row+1)+"*G"+(row+1));
                        cell8.setCellStyle(currencyStyle);
                        row++;
                        tempRow = sheet.createRow(row);
                    }
                    tempRow = sheet.createRow(row);
                    tempRow.createCell(8).setCellFormula("SUM(I"+(originalRow+1)+":I"+(row)+")");
                    tempRow.getCell(8).setCellStyle(boldStyle);
                    row+=2;
                }
            }
        }        
        return row;
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

    private ArrayList<ArrayList<productIngredients>> generateProductSales(Sheet sheet, String from, String to){
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
                    nextDate = tempNext.getIngredientsDate();
                }
                int amountSold = managerDB.getNumberSoldProduct(temp.getProductID(), temp.getIngredientsDate(), nextDate, from, to);
                temp.setNumberProductsSold(amountSold);
            }
        }
        return productIngredientsLists;
    }

}