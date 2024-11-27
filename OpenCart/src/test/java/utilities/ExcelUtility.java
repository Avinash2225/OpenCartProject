package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public  FileInputStream fi;
	public  FileOutputStream fo;
	public  XSSFWorkbook wb;
    public  XSSFSheet ws;
    public  XSSFRow row;
    public  XSSFCell cell;
    public  CellStyle style;
    String path;
    
    public ExcelUtility(String path) {
    	this.path=path;
    }
    
    public  int  getRowCount(String sheetname) throws IOException
    {
  fi = new    FileInputStream(path);
    
    wb = new XSSFWorkbook(fi);
    ws = wb.getSheet(sheetname);
    int rowcount = ws.getLastRowNum();
    wb.close();
    fi.close();
    return rowcount;
    }
     
    public  int getCellCount( String sheetname, int rownum) throws IOException
    {
    	  fi = new    FileInputStream(path);

    wb = new XSSFWorkbook(fi);
    ws = wb.getSheet(sheetname);
    row = ws.getRow(rownum);
    int cellcount = row.getLastCellNum();
    wb.close();
    fi.close();
    return cellcount;
    		
    
      }  
    
    public  String getCellData(String sheetname ,  int rownum, int colnum) throws IOException
    {
    	// this will read data from single cell
    	  fi =new FileInputStream(path);
    	    wb = new XSSFWorkbook(fi);
    	    ws = wb.getSheet(sheetname);
    	    row = ws.getRow(rownum);
    	    cell = row.getCell(colnum);
    	    
	    	DataFormatter formatter = new DataFormatter(); // only one apparoch will follow

    	    String data;

    	    try {
    	    	// data = cell.toString()
    	    	data = formatter.formatCellValue(cell); // Returns the formatted value of a cell as a String regardless of the cell type
    	    
    	    // some timw in ecxel there is no data but forcefully want to write it
    	    } catch (Exception e2) {
    	    	data = "";
    	    }
    wb.close();
    fi.close();
    return data;
    
    }
// how to write data in Excel
    
    public  String setCellData(String sheetname ,  int rownum, int colnum , String data) throws IOException
    {
    	File xlfile = new File(path);
    	if(!xlfile.exists()) {
    		wb = new XSSFWorkbook();
    		fo = new FileOutputStream(path);
    		wb.write(fo);
    	}
    	
    fi = new FileInputStream(path);
    wb = new XSSFWorkbook(fi);
    
  //  ws = wb.getSheet(sheetname);
   // row = ws.getRow(rownum);
    
    if(wb.getSheetIndex(sheetname)==-1)  // if sheet not exist then create new one sheet
    	wb.createSheet(sheetname);
    ws = wb.getSheet(sheetname);
    
    if(ws.getRow(rownum)==null)   // if row is not exist then create new one
          ws.createRow(rownum);
    row = ws.getRow(rownum);
    
    
    cell = row.createCell(colnum);
    cell.setCellValue(data);
    fo = new FileOutputStream(path);
    wb.write(fo);
    wb.close();
    fi.close();
    fo.close();
    return ("String");
    }
    
    
    public  String fillGreenColor(String sheetname ,  int rownum, int colnum ) throws IOException
    {
    	fi = new FileInputStream(path);
    	wb = new XSSFWorkbook(fi);
    	ws = wb.getSheet(sheetname);
    	row = ws.getRow(rownum);
    	cell = row.getCell(colnum);
    	
    	style = wb.createCellStyle();
    	
    	style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
    	style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    	
    	cell.setCellStyle(style);
    	fo = new FileOutputStream(path);
    	wb.write(fo);
    	wb.close();
    	fi.close();
    	fo.close();
return("String");

    }
public  void filledRedColore(String sheetname , int rownum, int colnum ) throws IOException
{
	fi = new FileInputStream(path);
	wb = new XSSFWorkbook(fi);
	ws = wb.getSheet(sheetname);
	row = ws.getRow(rownum);
	cell = row.getCell(colnum);
	
	style = wb.createCellStyle();
	
	style.setFillBackgroundColor(IndexedColors.RED.getIndex());
	style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	
	cell.setCellStyle(style);
	fo = new FileOutputStream(path);
	wb.write(fo);
	wb.close();
	fi.close();
	fo.close();
	
	
	
	
}
	
}
