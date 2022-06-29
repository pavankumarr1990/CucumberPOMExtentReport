//package common;
//
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//
//public class excelDataReader {
//    public void readExcel(String filePath, String fileName, String sheetName) throws IOException {
//        File file = new File(filePath + "\\" + fileName);
//        FileInputStream inputStream = new FileInputStream(file);
//        Workbook workbookName = null;
//        String fileExtensionName = fileName.substring(fileName.indexOf("."));
//        if (fileExtensionName.equals(".xlsx")) {
//            workbookName = new XSSFWorkbook(inputStream);
//        } else if (fileExtensionName.equals(".xls")) {
//            workbookName = new HSSFWorkbook(inputStream);
//        }
//        Sheet sheetNames = workbookName.getSheet(sheetName);
//        int rowCount = sheetNames.getLastRowNum() - sheetNames.getFirstRowNum();
//        System.out.println(rowCount);
//        for (int i = 0; i < rowCount + 1; i++) {
//            Row row = sheetNames.getRow(i);
//            for (int j = 0; j < row.getLastCellNum(); j++) {
//                System.out.print(row.getCell(j).getStringCellValue() + "|| ");
//            }
//            System.out.println();
//        }
//    }
//
//    public static void main(String... strings) throws IOException {
//        excelDataReader objExcelFile = new excelDataReader();
//        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\data";
//        objExcelFile.readExcel(filePath, "testDataa.xlsx", "Users");
//
//
//    }
//}
