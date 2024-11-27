package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProvider1 {
	// DataProvider 1
	
	@DataProvider (name= "LoginData") // this anme should be different
	public String[][] getData() throws IOException{
		String path = "C:\\Users\\avina\\OneDrive\\Desktop\\Github_for_Java\\Java_codes\\Day7\\testdata\\datadrivenopencart.xlsx"; // taking excel file from test data
		
		ExcelUtility xlutil = new ExcelUtility(path); // creating an object for xl utility
		
		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcols = xlutil.getCellCount("Sheet1", 1);
		
		String logindata [] [] = new String [totalrows] [totalcols]; // created for two dimension array which can store 
		for(int i=1; i<=totalrows; i++) // 1 // read data from xl storing in two dimensional array
		{
			for (int j=0; j<totalcols; j++)  // 0 i is rows j is column
			{
				logindata[i-1] [j] = xlutil.getCellData("Sheet1", i, j);  // 1,0
			}
		}
		return logindata; // returning  two dimensional array
	}
	
	
	
	
	
	
	

}
