package navigation.administration.reportsNav.reportsGeneration;

import org.apache.poi.ss.usermodel.*;
import java.io.FileOutputStream;
import componentsFood.productIngredients;
import componentsFood.employee;
import componentsFood.ingredient;
import componentsFood.shift;
import util.databaseAPIs.reportsAPI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class expensesReportGenerator extends iReportable {

    public void generateReport(String from, String to, String folderPath) throws Exception {
        Sheet sheet = getWorkbook().createSheet("Expenses Report");
        int row = 1;
        row = productData(sheet, row, from, to);
        row = employeeData(sheet, row, from, to);

        String excelName = "/Expenses " + from.substring(8, 10) + "-" + from.substring(5, 7)
                + "~" + to.substring(8, 10) + "-" + to.substring(5, 7) + ".xlsx";
        FileOutputStream fileout = new FileOutputStream(folderPath.concat(excelName));
        getWorkbook().write(fileout);
        fileout.close();
        getWorkbook().close();
    }

    private int productData(Sheet theSheet, int row, String from, String to) {
        theSheet.createRow(row).createCell(1).setCellValue("PRODUCT EXPENSES");
        row += 2;
        row = productHeaders(theSheet, row);
        ArrayList<ArrayList<productIngredients>> lLProducts = generateProductSales(from, to);
        ArrayList<ArrayList<productIngredients>> lLMenus = new reportsAPI().getAllProductIngredientsFromMenus(from, to);
        ArrayList<ArrayList<productIngredients>> combination = combineListOfLists(lLProducts, lLMenus);
        System.out.println(combination.size());
        return printProductSales(theSheet, combination, row);
    }

    public int employeeData(Sheet theSheet, int row, String from, String to) {
        reportsAPI managerDB = new reportsAPI();
        ArrayList<employee> temp = managerDB.getAllEmployeesAndShifts(from, to);
        Row tempRow = theSheet.createRow(row);
        tempRow.createCell(1).setCellValue("Shift Date");
        tempRow.createCell(2).setCellValue("Employee");
        tempRow.createCell(3).setCellValue("Role");
        tempRow.createCell(4).setCellValue("Start Shift");
        tempRow.createCell(5).setCellValue("End Shift");
        tempRow.createCell(6).setCellValue("Total Hours");
        tempRow.createCell(7).setCellValue("Salary/Hour");
        tempRow.createCell(8).setCellValue("Total Cost");
        row += 2;
        int originalRow = row;
        for (employee tempEmployee : temp) {
            for (shift tempShift : tempEmployee.getShifts()) {
                tempRow = theSheet.createRow(row);
                tempRow.createCell(1).setCellValue(tempShift.getDate());
                tempRow.createCell(2).setCellValue(tempEmployee.getName());
                tempRow.createCell(3).setCellValue(managerDB.getRoleName(tempEmployee.getRoleID()));
                Cell cell4 = tempRow.createCell(4);
                cell4.setCellValue(tempShift.getStartTime().substring(0, 5));
                cell4.setCellStyle(iReportable.timeStyle);
                Cell cell5 = tempRow.createCell(5);
                cell5.setCellValue(tempShift.getEndTime().substring(0, 5));
                cell5.setCellStyle(iReportable.timeStyle);
                Cell cell6 = tempRow.createCell(6);
                cell6.setCellFormula("F" + (row + 1) + "-E" + (row + 1));
                cell6.setCellStyle(iReportable.timeStyle);
                Cell cell7 = tempRow.createCell(7);
                cell7.setCellValue(tempEmployee.getSalary());
                cell7.setCellStyle(iReportable.currencyStyle);
                Cell cell8 = tempRow.createCell(8);
                cell8.setCellFormula("G" + (row + 1) + "*24*H" + (row + 1));
                cell8.setCellStyle(iReportable.currencyStyle);
                ++row;
            }
        }
        Cell totalCell = theSheet.createRow(row).createCell(8);
        totalCell.setCellFormula("SUM(I" + (originalRow + 1) + ":I" + (row) + ")");
        totalCell.setCellStyle(iReportable.boldStyle);
        return row + 2;
    }

    private ArrayList<ArrayList<productIngredients>> combineListOfLists(ArrayList<ArrayList<productIngredients>> a,
            ArrayList<ArrayList<productIngredients>> b) {
        for (ArrayList<productIngredients> tempA : a) {
            Iterator<ArrayList<productIngredients>> bIter = b.iterator();
            while (bIter.hasNext()) {
                ArrayList<productIngredients> tempB = bIter.next();
                if (tempA.get(0).getProductID() == tempB.get(0).getProductID()) {
                    Iterator<productIngredients> smallTempBIter = tempB.iterator();
                    while (smallTempBIter.hasNext()) {
                        productIngredients smallTempB = smallTempBIter.next();
                        boolean isFound = false;
                        for (productIngredients smallTempA : tempA) {
                            if (smallTempA.getProductDate().equals(smallTempB.getProductDate())) {
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

    private int printProductSales(Sheet sheet, ArrayList<ArrayList<productIngredients>> listList, int row) {
        reportsAPI managerDB = new reportsAPI();
        Stack<Integer> productTotals = new Stack<Integer>();
        for (ArrayList<productIngredients> bigTemp : listList) {
            for (productIngredients temp : bigTemp) {
                System.out.println("here");
                if (temp.getNumberSoldProducts() != 0 || temp.getNumberSoldMenus() != 0) {
                    int originalRow = row;
                    Row tempRow = sheet.createRow(row);
                    tempRow.createCell(1).setCellValue(temp.getIngredientsDate());
                    System.out.println(temp.getIngredientsDate());
                    tempRow.createCell(2)
                            .setCellValue(managerDB.getNameOfProduct(temp.getProductID(), temp.getProductDate()));
                    tempRow.createCell(3).setCellValue(temp.getNumberSoldProducts());
                    tempRow.createCell(4).setCellValue(temp.getNumberSoldMenus());
                    for (int i = 0; i < temp.getIngredients().size(); i++) {
                        ingredient tempIngr = temp.getIngredients().get(i);
                        tempIngr.getProviderID();
                        tempRow.createCell(5).setCellValue(tempIngr.getName());
                        tempRow.createCell(6).setCellValue(tempIngr.getAmount());
                        Cell cell7 = tempRow.createCell(7);
                        cell7.setCellValue(tempIngr.getPrice());
                        cell7.setCellStyle(iReportable.currencyStyle);
                        Cell cell8 = tempRow.createCell(8);
                        cell8.setCellValue(temp.getQuantity().get(i));
                        cell8.setCellStyle(iReportable.roundingStyle);
                        Cell cell9 = tempRow.createCell(9);
                        cell9.setCellFormula("(D" + (originalRow + 1) + "+E" + (originalRow + 1) +
                                ")/G" + (row + 1)
                                + "*H" + (row + 1) + "*I" + (row + 1));
                        cell9.setCellStyle(iReportable.currencyStyle);
                        row++;
                        tempRow = sheet.createRow(row);
                    }
                    tempRow = sheet.createRow(row);
                    tempRow.createCell(9).setCellFormula("SUM(J" + (originalRow + 1) + ":J" +
                            (row) + ")");
                    tempRow.getCell(9).setCellStyle(iReportable.boldStyle);
                    productTotals.add(row + 1);

                    row += 2;
                }
            }
        }
        String formula = "SUM(";
        if (productTotals.isEmpty())
            return row;
        while (!productTotals.isEmpty()) {
            String temp = "J" + productTotals.pop() + ",";
            formula = formula.concat(temp);
        }
        formula = formula.substring(0, formula.length() - 1);
        formula = formula.concat(")");
        Row totalRow = sheet.createRow(row);
        totalRow.createCell(9).setCellFormula(formula);
        totalRow.getCell(9).setCellStyle(iReportable.boldStyle);
        return row += 2;
    }

    private int productHeaders(Sheet theSheet, int row) {
        Row tempRow = theSheet.createRow(row);
        tempRow.createCell(1).setCellValue("Cost From");
        tempRow.createCell(2).setCellValue("Product");
        tempRow.createCell(3).setCellValue("Sold Alone");
        tempRow.createCell(4).setCellValue("Sold in Menu");
        tempRow.createCell(5).setCellValue("Ingredients");
        tempRow.createCell(6).setCellValue("Provided/price");
        tempRow.createCell(7).setCellValue("Price");
        tempRow.createCell(8).setCellValue("Quantity Used");
        tempRow.createCell(9).setCellValue("Total Cost");
        return ++row;
    }

    private ArrayList<ArrayList<productIngredients>> generateProductSales(String from, String to) {
        reportsAPI managerDB = new reportsAPI();
        ArrayList<ArrayList<productIngredients>> productIngredientsLists = managerDB
                .getAllProductIngredientsFromProducts();
        for (ArrayList<productIngredients> bigTemp : productIngredientsLists) {
            for (int i = 0; i < bigTemp.size(); i++) {
                productIngredients temp = bigTemp.get(i);
                managerDB.addIngredientsToProductIngredients(temp);
                productIngredients tempNext = null;
                String nextDate = "";
                if (i + 1 < bigTemp.size()) {
                    tempNext = bigTemp.get(i + 1);
                    nextDate = tempNext.getIngredientsDate();
                }
                System.out.println(temp.getProductID() + ", and date :" + temp.getProductDate());
                temp.setNumberSoldProducts(managerDB.getNumberSoldProduct(temp, nextDate, from, to));
            }
        }
        return productIngredientsLists;
    }

}