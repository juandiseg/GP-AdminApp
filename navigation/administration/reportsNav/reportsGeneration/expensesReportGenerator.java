package navigation.administration.reportsNav.reportsGeneration;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import componentsFood.productIngredients;
import componentsFood.employee;
import componentsFood.ingredient;
import componentsFood.shift;
import util.databaseAPIs.productAPI;
import util.databaseAPIs.reportsAPI;
import util.databaseAPIs.rolesAPI;
import util.inputFormatting.dateInverter;

import java.util.ArrayList;
import java.util.Stack;

public class expensesReportGenerator implements iReportable {

    private CellStyle currencyStyle;
    private CellStyle roundingStyle;
    private CellStyle boldStyle;
    private CellStyle timeStyle;

    public void generateReport(String from, String to, String folderPath) throws Exception {
        Workbook workbook = new XSSFWorkbook();
        DataFormat format = workbook.createDataFormat();
        currencyStyle = workbook.createCellStyle();
        roundingStyle = workbook.createCellStyle();
        boldStyle = workbook.createCellStyle();
        Font boldFont = workbook.createFont();
        boldStyle.setFont(boldFont);
        boldStyle.setDataFormat(format.getFormat("_(€* ###,##0.00_);_(€* (###,##0.00);_(€* \"-\"??_);_(@_)"));
        currencyStyle.setDataFormat(format.getFormat("_(€* #,##0.00_);_(€* (#,##0.00);_(€* \"-\"??_);_(@_)"));
        boldFont.setBold(true);
        roundingStyle.setDataFormat((format.getFormat("#####0.00")));
        timeStyle = workbook.createCellStyle();
        timeStyle.setDataFormat(format.getFormat("HH:MM"));
        Sheet sheet = workbook.createSheet("Expenses Report");
        int row = 1;
        from = dateInverter.invert(from);
        to = dateInverter.invert(to);
        int[] coordinates = productData(sheet, row, 1, from, to);
        int tempRow = coordinates[0];
        if (coordinates[1] != -1)
            tempRow += 2;
        employeeData(sheet, tempRow, 1, from, to);
        String excelName = "/Expenses " + from.substring(8, 10) + "-" + from.substring(5, 7)
                + "~" + to.substring(8, 10) + "-" + to.substring(5, 7) + ".xlsx";
        FileOutputStream fileout = new FileOutputStream(folderPath.concat(excelName));
        workbook.write(fileout);
        fileout.close();
        workbook.close();
    }

    public int[] productData(Sheet theSheet, int row, int column, String from, String to) {
        // Writes on the excel sheet starting from the specified row and column the
        // summary of product expenses within the given timeframe.

        ArrayList<ArrayList<productIngredients>> lLProducts = reportsAPI.generateProductExpenses(from, to);
        return printProductSales(theSheet, lLProducts, row, column);
    }

    public int[] employeeData(Sheet theSheet, int row, int column, String from, String to) {
        // Writes on the excel sheet starting from the specified row and column the
        // summary of employee expenses within the given timeframe.

        ArrayList<employee> temp = reportsAPI.getAllEmployeesAndShifts(from, to);
        int c = column - 1;
        int[] columns = { 'F', 'E', 'G', 'H', 'I' };
        char[] columnChar = { (char) (columns[0] + c), (char) (columns[1] + c), (char) (columns[2] + c),
                (char) (columns[3] + c), (char) (columns[4] + c) };
        if (temp.isEmpty())
            return new int[] { row, -1 };
        Row tempRow = theSheet.createRow(row);
        tempRow.createCell(column).setCellValue("Shift Date");
        tempRow.createCell(column + 1).setCellValue("Employee");
        tempRow.createCell(column + 2).setCellValue("Role");
        tempRow.createCell(column + 3).setCellValue("Start Shift");
        tempRow.createCell(column + 4).setCellValue("End Shift");
        tempRow.createCell(column + 5).setCellValue("Total Hours");
        tempRow.createCell(column + 6).setCellValue("Salary/Hour");
        tempRow.createCell(column + 7).setCellValue("Total Cost");
        row += 2;
        int originalRow = row;
        for (employee tempEmployee : temp) {
            for (shift tempShift : tempEmployee.getShifts()) {
                tempRow = theSheet.createRow(row);
                tempRow.createCell(column).setCellValue(tempShift.getDate());
                tempRow.createCell(column + 1).setCellValue(tempEmployee.getName());
                tempRow.createCell(column + 2).setCellValue(rolesAPI.getNameOfRole(tempEmployee.getRoleID()));
                Cell cell4 = tempRow.createCell(column + 3);
                cell4.setCellValue(tempShift.getStartTime().substring(0, 5));
                cell4.setCellStyle(timeStyle);
                Cell cell5 = tempRow.createCell(column + 4);
                cell5.setCellValue(tempShift.getEndTime().substring(0, 5));
                cell5.setCellStyle(timeStyle);
                Cell cell6 = tempRow.createCell(column + 5);
                cell6.setCellFormula(columnChar[0] + "" + (row + 1) + "-" + columnChar[1] + (row + 1));
                cell6.setCellStyle(timeStyle);
                Cell cell7 = tempRow.createCell(column + 6);
                cell7.setCellValue(tempEmployee.getSalary());
                cell7.setCellStyle(currencyStyle);
                Cell cell8 = tempRow.createCell(column + 7);
                cell8.setCellFormula(columnChar[2] + "" + (row + 1) + "*24*" + columnChar[3] + "" + (row + 1));
                cell8.setCellStyle(currencyStyle);
                ++row;
            }
        }
        Cell totalCell = theSheet.createRow(row).createCell(column + 7);
        totalCell.setCellFormula("SUM(" + columnChar[4] + (originalRow + 1) + ":" + columnChar[4] + (row) + ")");
        totalCell.setCellStyle(boldStyle);
        return new int[] { row, column + 7 };
    }

    private int[] printProductSales(Sheet sheet, ArrayList<ArrayList<productIngredients>> listList, int row,
            int column) {
        if (!areThereSales(listList))
            return new int[] { row, -1 };
        Row titleRow = sheet.getRow(row);
        if (titleRow == null)
            titleRow = sheet.createRow(row);
        titleRow.createCell(column).setCellValue("PRODUCT EXPENSES");
        row += 2;
        row = productHeaders(sheet, row, column);
        int c = column - 1;
        int[] columns = { 'D', 'E', 'G', 'H', 'I', 'J' };
        char[] columnChar = { (char) (columns[0] + c), (char) (columns[1] + c), (char) (columns[2] + c),
                (char) (columns[3] + c), (char) (columns[4] + c), (char) (columns[5] + c) };
        Stack<Integer> productTotals = new Stack<Integer>();
        for (ArrayList<productIngredients> bigTemp : listList) {
            for (productIngredients temp : bigTemp) {
                if (temp.getNumberSoldProducts() != 0 || temp.getNumberSoldMenus() != 0) {
                    int originalRow = row;
                    Row tempRow = sheet.getRow(row);
                    if (tempRow == null)
                        tempRow = sheet.createRow(row);
                    tempRow.createCell(column).setCellValue(dateInverter.invert(temp.getIngredientsDate()));
                    tempRow.createCell(column + 1)
                            .setCellValue(productAPI.getName(temp.getProductID(),
                                    dateInverter.invert(temp.getProductDate())));
                    tempRow.createCell(column + 2).setCellValue(temp.getNumberSoldProducts());
                    tempRow.createCell(column + 3).setCellValue(temp.getNumberSoldMenus());
                    for (int i = 0; i < temp.getIngredients().size(); i++) {
                        ingredient tempIngr = temp.getIngredients().get(i);
                        tempIngr.getProviderID();
                        tempRow.createCell(column + 4).setCellValue(tempIngr.getName());
                        tempRow.createCell(column + 5).setCellValue(tempIngr.getAmount());
                        Cell cell7 = tempRow.createCell(column + 6);
                        cell7.setCellValue(tempIngr.getPrice());
                        cell7.setCellStyle(currencyStyle);
                        Cell cell8 = tempRow.createCell(column + 7);
                        cell8.setCellValue(temp.getQuantity().get(i));
                        cell8.setCellStyle(roundingStyle);
                        Cell cell9 = tempRow.createCell(column + 8);
                        String formula = "(" + columnChar[0] + (originalRow + 1) + "+" + columnChar[1]
                                + (originalRow + 1) +
                                ")/" + columnChar[2] + (row + 1)
                                + "*" + columnChar[3] + (row + 1) + "*" + columnChar[4] + (row + 1);
                        cell9.setCellFormula(formula);
                        cell9.setCellStyle(currencyStyle);
                        row++;
                        tempRow = sheet.getRow(row);
                        if (tempRow == null)
                            tempRow = sheet.createRow(row);
                    }
                    tempRow = sheet.getRow(row);
                    if (tempRow == null)
                        tempRow = sheet.createRow(row);
                    tempRow.createCell(column + 8)
                            .setCellFormula("SUM(" + columnChar[5] + "" + (originalRow + 1) + ":" + columnChar[5] + "" +
                                    (row) + ")");
                    tempRow.getCell(column + 8).setCellStyle(boldStyle);
                    productTotals.add(row + 1);
                    row += 2;
                }
            }
        }
        String formula = "SUM(";
        if (productTotals.isEmpty())
            return new int[] { row, -1 };
        while (!productTotals.isEmpty()) {
            String temp = columnChar[5] + "" + productTotals.pop() + ",";
            formula = formula.concat(temp);
        }
        formula = formula.substring(0, formula.length() - 1);
        formula = formula.concat(")");
        Row totalRow = sheet.getRow(row);
        if (totalRow == null)
            totalRow = sheet.createRow(row);
        totalRow.createCell(column + 8).setCellFormula(formula);
        totalRow.getCell(column + 8).setCellStyle(boldStyle);
        return new int[] { row, column + 8 };
    }

    private boolean areThereSales(ArrayList<ArrayList<productIngredients>> listList) {
        for (ArrayList<productIngredients> bigTemp : listList) {
            for (productIngredients temp : bigTemp) {
                if (temp.getNumberSoldProducts() != 0 || temp.getNumberSoldMenus() != 0)
                    return true;
            }
        }
        return false;
    }

    private int productHeaders(Sheet theSheet, int row, int column) {
        Row tempRow = theSheet.getRow(row);
        if (tempRow == null)
            tempRow = theSheet.createRow(row);
        tempRow.createCell(column).setCellValue("Cost From");
        tempRow.createCell(column + 1).setCellValue("Product");
        tempRow.createCell(column + 2).setCellValue("Sold Alone");
        tempRow.createCell(column + 3).setCellValue("Sold in Menu");
        tempRow.createCell(column + 4).setCellValue("Ingredients");
        tempRow.createCell(column + 5).setCellValue("Provided/price");
        tempRow.createCell(column + 6).setCellValue("Price");
        tempRow.createCell(column + 7).setCellValue("Quantity Used");
        tempRow.createCell(column + 8).setCellValue("Total Cost");
        return ++row;
    }

}