package navigation.administration.reports_Window;

import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import java.io.FileOutputStream;

import componentsFood.ingredient;
import componentsFood.productIngredients;
import util.iReportable;
import java.util.ArrayList;
import java.util.Iterator;

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
        boldStyle.setDataFormat(format.getFormat("_(€* ###,##0.00_);_(€* (###,##0.00);_(€* \"-\"??_);_(@_)"));
        boldStyle.setFont(boldFont);
        roundingStyle.setDataFormat((format.getFormat("#####0.00")));
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
        ArrayList<ArrayList<productIngredients>> lLProducts = generateProductSales(theSheet, from, to);
        ArrayList<ArrayList<productIngredients>> lLMenus = new reportsAPI().getAllProductIngredientsFromMenus(from, to);
        ArrayList<ArrayList<productIngredients>> combination = combineListOfLists(lLProducts, lLMenus);
        printProductSales(theSheet, combination, row);
        return row;
    }

    private ArrayList<ArrayList<productIngredients>> combineListOfLists(ArrayList<ArrayList<productIngredients>> a, ArrayList<ArrayList<productIngredients>>b){
        for(ArrayList<productIngredients> tempA : a){
            Iterator<ArrayList<productIngredients>> bIter = b.iterator();
            while (bIter.hasNext()) {
                ArrayList<productIngredients> tempB = bIter.next();
                if(tempA.get(0).getProductID() == tempB.get(0).getProductID()){
                    Iterator<productIngredients> smallTempBIter = tempB.iterator();
                    while (smallTempBIter.hasNext()) {
                        productIngredients smallTempB = smallTempBIter.next();
                        boolean isFound = false;
                        for(productIngredients smallTempA : tempA){
                            if(smallTempA.getProductDate().equals(smallTempB.getProductDate())){
                                smallTempA.setNumberSoldMenus(smallTempB.getNumberSoldMenus());
                                smallTempBIter.remove();
                                isFound = true;
                                break;
                            }
                        }
                        if (!isFound) {
                            tempA.add(smallTempB);
                            smallTempBIter.remove();
                        }
                    }
                    if (tempB.isEmpty()) {
                        bIter.remove();
                    }
                }
            }
        }
        a.addAll(b);
        return a;
    }
    
    public int printProductSales(Sheet sheet, ArrayList<ArrayList<productIngredients>> listList, int row){
        reportsAPI managerDB = new reportsAPI();
        for(ArrayList<productIngredients> bigTemp : listList){
            for(productIngredients temp : bigTemp){
                if(temp.getNumberSoldProducts()!=0 || temp.getNumberSoldMenus()!=0){
                    Row tempRow = sheet.createRow(row);
                    tempRow.createCell(1).setCellValue(temp.getIngredientsDate());
                    tempRow.createCell(2).setCellValue(managerDB.getNameOfProduct(temp.getProductID(), temp.getProductDate()));
                    tempRow.createCell(3).setCellValue(temp.getNumberSoldProducts());
                    tempRow.createCell(4).setCellValue(temp.getNumberSoldMenus());
                    int originalRow = row;
                    for(int i = 0; i<temp.getIngredients().size(); i++){
                        ingredient tempIngr = temp.getIngredients().get(i);
                        tempRow.createCell(5).setCellValue(tempIngr.getName());
                        tempRow.createCell(6).setCellValue(tempIngr.getAmount());
                        Cell cell7 = tempRow.createCell(7);
                        cell7.setCellValue(tempIngr.getPrice());
                        cell7.setCellStyle(currencyStyle);
                        Cell cell8 = tempRow.createCell(8);
                        cell8.setCellValue(temp.getQuantity().get(i));
                        cell8.setCellStyle(roundingStyle);
                        Cell cell9 = tempRow.createCell(9);
                        cell9.setCellFormula("(D"+(originalRow+1)+"+E"+(originalRow+1)+")*G"+(row+1)+"/H"+(row+1)+"*I"+(row+1));
                        cell9.setCellStyle(currencyStyle);
                        row++;
                        tempRow = sheet.createRow(row);
                    }
                    tempRow = sheet.createRow(row);
                    tempRow.createCell(9).setCellFormula("SUM(J"+(originalRow+1)+":J"+(row)+")");
                    tempRow.getCell(9).setCellStyle(boldStyle);
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
        tempRow.createCell(3).setCellValue("Quantity As Products");
        tempRow.createCell(4).setCellValue("Quantity As Menus");
        tempRow.createCell(5).setCellValue("Ingredients");
        tempRow.createCell(6).setCellValue("Quantity As Products");
        tempRow.createCell(7).setCellValue("Price");
        tempRow.createCell(8).setCellValue("Quantity in Product");
        tempRow.createCell(9).setCellValue("Total Cost");
        return ++row;
    }

    private ArrayList<ArrayList<productIngredients>> generateProductSales(Sheet sheet, String from, String to){
        reportsAPI managerDB = new reportsAPI();
        ArrayList<ArrayList<productIngredients>> productIngredientsLists = managerDB.getAllProductIngredientsFromProducts();
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
                temp.setNumberSoldProducts(amountSold);
            }
        }
        return productIngredientsLists;
    }

}