package edu.uoc.correction.service;

import edu.uoc.correction.model.Student;
import edu.uoc.correction.model.Testcase;
import edu.uoc.correction.model.Testsuite;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelGenerator {
    public void createExcel(List<Student> students) throws IOException {
        System.out.println(System.lineSeparator() + "############ Create EXCEL ############");

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        workbook.setSheetName(0, "CORRECTION");

        List<String> headers = new ArrayList<>();


        headers.add("Student");
        /*File folderOriginal = new File(Config.DIRECTORY_ORIGINAL_TESTS);
        for(File fileOriginal : folderOriginal.listFiles()) {
            headers.add(fileOriginal.getName());
            headers.add("Successful");
            headers.add("Error");
        }*/
        headers.add("Successful TOTAL");
        headers.add("Grade/Note");

        /*headers.add("Student");
        for(String test : GradleExecute.listTests){
            headers.add(test);
            headers.add("Successful");
            headers.add("Error");
        }
        headers.add("Successful TOTAL");
        headers.add("Grade/Note");*/

        CellStyle headerStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        headerStyle.setFont(font);

        CellStyle successStyle = workbook.createCellStyle();
        successStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        successStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        successStyle.setFont(font);
        successStyle.setDataFormat(workbook.createDataFormat().getFormat("0%"));

        CellStyle failureStyle = workbook.createCellStyle();
        failureStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
        failureStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        failureStyle.setFont(font);
        failureStyle.setDataFormat(workbook.createDataFormat().getFormat("0%"));

        CellStyle percentageStyle = workbook.createCellStyle();
        percentageStyle.setDataFormat(workbook.createDataFormat().getFormat("0%"));

        CellStyle wraptyle = workbook.createCellStyle();
        wraptyle.setWrapText(true);

        HSSFRow headerRow = sheet.createRow(0);

        int indexHeaderCell = 0;
        for(String header: headers){
            HSSFCell cell = headerRow.createCell(indexHeaderCell);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(header);
            indexHeaderCell++;
        }

        int row = 1;

        for(Student student : students){
            int testsTotal = 0, failuresTotal = 0, errorsTotal = 0, successfulTotal = 0;
            HSSFRow dataRow = sheet.createRow(row);
            int cell = 0;
            String[] studentNameSplit = student.getName().split("_");
            dataRow.createCell(cell++).setCellValue(studentNameSplit[0]+"_"+studentNameSplit[1]);

            System.out.print(row + " - " + studentNameSplit[0]+"_"+studentNameSplit[1] + ": ");

            /*if(student.getTestsuiteList() != null){
                for(Testsuite testsuite : student.getTestsuiteList()){
                    int successful = ((testsuite.getTests() - testsuite.getFailures() - testsuite.getErrors()) * 100) / testsuite.getTests();

                    testsTotal += testsuite.getTests();
                    failuresTotal += testsuite.getFailures();
                    errorsTotal += testsuite.getErrors();


                    dataRow.createCell(cell++).setCellValue(testsuite.getTests() - testsuite.getFailures() - testsuite.getErrors() + "/" + testsuite.getTests());
                    HSSFCell successfulCell = dataRow.createCell(cell++);
                    successfulCell.setCellValue((double)successful/100);
                    successfulCell.setCellStyle(percentageStyle);

                    String failureStr = new String();
                    int countLines = 0;
                    for (Testcase testcase : testsuite.getTestcase()){
                        if(testcase.getFailure() != null){
                            failureStr += testcase.getName() + ": " + testcase.getFailure().getMessage() + "::";
                            countLines++;
                        }
                    }
                    if(!failureStr.equals("")){
                        //dataRow.createCell(cell++).setCellValue(failureStr.substring(0, failureStr.length()-2).replaceAll("::", System.lineSeparator()));
                        HSSFCell cellError = dataRow.createCell(cell++);
                        cellError.setCellValue(failureStr.substring(0, failureStr.length()-2).replaceAll("::", System.lineSeparator()));
                        //dataRow.setHeight((short)(dataRow.getHeight() * countLines));

                        cellError.setCellStyle(wraptyle);
                        //dataRow.setHeight((short)-1);
                    }else{
                        dataRow.createCell(cell++).setCellValue(failureStr);
                    }
                }

                successfulTotal = ((testsTotal - failuresTotal - errorsTotal) * 100) / testsTotal;
                System.out.println("Successful " + successfulTotal + "%");

                HSSFCell total = dataRow.createCell(cell++);
                total.setCellValue((double)successfulTotal/100);

                if(successfulTotal >= 50) total.setCellStyle(successStyle);
                else total.setCellStyle(failureStyle);

                HSSFCell note = dataRow.createCell(cell++);
                note.setCellValue((Config.EXERCICE_NOTE*successfulTotal)/100);

            }else{
                dataRow.createCell(cell++).setCellValue(student.getComment());
                System.out.println(student.getComment() + " - Successful 0%");
            }*/


            row++;
        }



        for(short i = 0; i < sheet.getRow(0).getLastCellNum(); i++){
            sheet.autoSizeColumn(i);
        }


        FileOutputStream file = new FileOutputStream(System.getProperty("user.dir")+ "/results/PAC2Ex1.xls");
        workbook.write(file);
        file.close();


    }
}
