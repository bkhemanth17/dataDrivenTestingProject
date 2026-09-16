package utilityPackage;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
public class utilityClass {
    public static FileInputStream inputStream;
    public static FileOutputStream outputStream;
    public static XSSFWorkbook workbook;
    public static XSSFSheet sheet;
    public static XSSFRow row;
    public static XSSFCell cell;
    public static CellStyle style;

    public static int getRow(String fileLoc, String sheetLoc) throws FileNotFoundException, IOException {
        inputStream = new FileInputStream(fileLoc);
        workbook= new XSSFWorkbook(inputStream);
        sheet = workbook.getSheet(sheetLoc);
        int row = sheet.getLastRowNum();
        workbook.close();
        inputStream.close();
        return row;
    }

    public static int getCellCount(String fileLoc, String sheetLoc, int rowNum) throws FileNotFoundException, IOException{
        inputStream = new FileInputStream(fileLoc);
        workbook = new XSSFWorkbook(inputStream);
        sheet = workbook.getSheet(sheetLoc);
        row = sheet.getRow(rowNum);
        int cellValue = row.getLastCellNum();
        workbook.close();
        inputStream.close();
        return cellValue;
    }

    public static String getCellData(String fileLoc, String sheetLoc, int rowNum, int colNum) throws FileNotFoundException, IOException{
        inputStream = new FileInputStream(fileLoc);
        workbook = new XSSFWorkbook(inputStream);
        sheet = workbook.getSheet(sheetLoc);
        row = sheet.getRow(rowNum);
        cell = row.getCell(colNum);
        String data;
        try{

            //data = cell.toString();
            DataFormatter formatter = new DataFormatter();
            data = formatter.formatCellValue(cell);

            /*we can use any one DataFormatter or cell.toString()
            DataFormatter -> apache poi library returns String
            cell.toString() -> is a java function returns String
             */

        }catch (Exception exception){

            /*we use exception because if cell has empty value -> the java will throws exception
            we catch them and make the string data as empty
             */
            data="";
        }
        workbook.close();
        inputStream.close();
        return data;
    }

    public static void setCellData (String fileLoc, String sheetLoc, int rowNum, int colNum, String data) throws FileNotFoundException, IOException{

        inputStream = new FileInputStream(fileLoc);
        workbook = new XSSFWorkbook(inputStream);
        sheet = workbook.getSheet(sheetLoc);
        row = sheet.getRow(rowNum);
        cell = row.createCell(colNum);
        cell.setCellValue(data);
        outputStream = new FileOutputStream(fileLoc);
        workbook.write(outputStream);
        workbook.close();
        inputStream.close();
        outputStream.close();
    }

    public static void fillGreenColour(String fileLoc, String sheetLoc, int rowNum, int colNum) throws FileNotFoundException, IOException{
        inputStream = new FileInputStream(fileLoc);
        workbook = new XSSFWorkbook(inputStream);
        sheet = workbook.getSheet(sheetLoc);
        row = sheet.getRow(rowNum);
        cell = row.getCell(colNum);

        style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);
        outputStream = new FileOutputStream(fileLoc);
        workbook.write(outputStream);
        workbook.close();
        inputStream.close();
        outputStream.close();
    }

    public static void fillRedColour(String fileLoc, String sheetLoc, int rowNum, int colNum) throws FileNotFoundException, IOException{
        inputStream = new FileInputStream(fileLoc);
        workbook = new XSSFWorkbook(inputStream);
        sheet = workbook.getSheet(sheetLoc);
        row = sheet.getRow(rowNum);
        cell = row.getCell(colNum);

        style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);
        outputStream = new FileOutputStream(fileLoc);
        workbook.write(outputStream);
        workbook.close();
        inputStream.close();
        outputStream.close();
    }
}
