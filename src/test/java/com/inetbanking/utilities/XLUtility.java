package com.inetbanking.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class XLUtility
{
    String path;
    public FileInputStream fi;
    public FileOutputStream fo;
    public HSSFWorkbook workbook;
    public HSSFSheet sheet;
    public HSSFRow row;
    public HSSFCell cell;
    public CellStyle style;

    public XLUtility(String path)
    {
        this.path=path;
    }
    public int getRowCount(String sheetName) throws IOException {
        fi=new FileInputStream(path);
        workbook=new HSSFWorkbook(fi);
        sheet=workbook.getSheet(sheetName);
        int rowcount = sheet.getLastRowNum();
        workbook.close();
        fi.close();
        return rowcount;
    }

    public int getCellCount(String sheetName,int rownum) throws IOException {
        fi=new FileInputStream(path);
        workbook=new HSSFWorkbook(fi);
        sheet=workbook.getSheet(sheetName);
        row=sheet.getRow(rownum);
        int cellCount=row.getLastCellNum();
        workbook.close();
        fi.close();
        return cellCount;

    }

    public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
        fi=new FileInputStream(path);
        workbook=new HSSFWorkbook(fi);
        sheet=workbook.getSheet(sheetName);
        row=sheet.getRow(rownum);
        cell=row.getCell(colnum);

        DataFormatter formatter= new DataFormatter();
        String data;
        try{
             data = formatter.formatCellValue(cell);
        }
        catch (Exception e)
        {
            data="";
        }
        workbook.close();
        fi.close();
        return data;

    }
    public void setCellData(String sheetName,int rownum,int colnum,String data) throws IOException {
        fi=new FileInputStream(path);
        workbook=new HSSFWorkbook(fi);
        sheet=workbook.getSheet(sheetName);

        row=sheet.getRow(rownum);
        cell=row.getCell(colnum);
        cell.setCellValue(data);

        fo=new FileOutputStream(path);
        workbook.write(fo);
        workbook.close();
        fi.close();
        fo.close();

    }

    public void fillGreenColour(String sheetName,int rownum,int colnum,String data) throws IOException {
        fi=new FileInputStream(path);
        workbook=new HSSFWorkbook(fi);
        sheet=workbook.getSheet(sheetName);

        row=sheet.getRow(rownum);
        cell=row.getCell(colnum);

        style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);
        workbook.write(fo);
        workbook.close();
        fi.close();
        fo.close();

    }

    public void fillRedColour(String sheetName,int rownum,int colnum,String data) throws IOException {
        fi = new FileInputStream(path);
        workbook = new HSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);

        row = sheet.getRow(rownum);
        cell = row.getCell(colnum);

        style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);
        workbook.write(fo);
        workbook.close();
        fi.close();
        fo.close();
    }

    public  void takeScreenShot() throws IOException {
        WebDriverManager.chromedriver().setup();
        RemoteWebDriver driver = new ChromeDriver();
        File SourceFile = driver.getScreenshotAs(OutputType.FILE);
        File DestFile = new File("D:\\FileReaders/Sc1.png");
        FileUtils.copyFile(SourceFile,DestFile);
        System.out.println("Screenshot Successful");

    }//File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                   // FileUtils.copyFile(screenshot, new File("D:\\Training\\FailedTestScriptScreenshot\\" + sheet.getCell(0, i).getContents() + ".png"));
}//div[@class='right-block']/child::span/child::span


//String UserDir = System.getProperty("user.dir");
//String pathSeparator = System.getProperty("file.separator");
//String file_path = UserDir+pathSeparator+"src"+pathSeparator+"main"+pathSeparator+"resources"+pathSeparator+"testdata2.xls";
