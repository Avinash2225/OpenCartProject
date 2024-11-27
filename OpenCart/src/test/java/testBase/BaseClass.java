package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseClass {
	public static WebDriver driver;
	
	public Logger logger;
	public Properties p;
	 @SuppressWarnings("deprecation")
	@BeforeClass(groups= {"Sanity", "Regression", "Master" })
	 
	 @Parameters ({"os", "browser"})
	 public	void setup( String os ,String br) throws IOException {
		 // loading Properties File
		 
		 FileReader file = new FileReader("C:\\Users\\avina\\OneDrive\\Desktop\\Github_for_Java\\Java_codes\\Day7\\src\\test\\resources\\config.properties");
		 
		p = new Properties();
		p.load(file);
		 
		 logger = LogManager.getLogger(this.getClass());
		 
		 if(p.getProperty("execuation_env").equalsIgnoreCase("remote"))
		 {
			 DesiredCapabilities capabilities = new DesiredCapabilities();
			// capabilities.setPlatform(Platform.WIN11);
		//	 capabilities.setBrowserName("chrome");
		 // os
			 if(os.equalsIgnoreCase("windows")) {
				 capabilities.setPlatform(Platform.WIN11);
			 } else {
				 System.out.println("No matching os");
				 
				 return;
			 }
		 // browser
			 switch(br.toLowerCase()) 
			 {
			 case "chrome": 	 capabilities.setBrowserName("chrome"); break;
			 case "edge" : capabilities.setBrowserName("MicrosoftEdge"); break;
			 default: System.out.println("No ,matching browser"); 
			 return;
			 }
		 driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub" ), capabilities);
		 
		 } 
		 
		 
		 // this is for local
		 
		 if(p.getProperty("execuation_env").equalsIgnoreCase("local"))
          {
			 switch(br.toLowerCase()) 
			 {
			 case "chrome" :driver = new ChromeDriver(); break;
			 case "edge" :driver = new EdgeDriver(); break;
			 case "firefox" :driver = new FirefoxDriver(); break;

			  default: System.out.println("Invalid browser name..."); return;
			 }
			  
		 }
	 
		 
		
		 
		//	driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		//	driver.get("https://tutorialsninja.com/demo/");
		 
			driver.get(p.getProperty("appURL"));  // reading URL from properties file

			
		 driver.manage().window().maximize();
		 	 
		}
		
	@AfterClass	public void tearDown() {
			driver.quit();
		}
	
	@SuppressWarnings("deprecation")
	public String randomeString() // we are creating this for passing random email id in the run time
	{
		
	String  generatedstring =	RandomStringUtils.randomAlphabetic(5);
	return generatedstring;
	}
	
	public String randomeNumber()  // we are passing the random phone number
	{
		
	@SuppressWarnings("deprecation")
	String  generatenumber =	RandomStringUtils.randomNumeric(10);
	
	return generatenumber;
	}
	public String randomeAlphaNumeric()  // we are passing the random password combination of the alphanumeric
	{
		
	@SuppressWarnings("deprecation")
	String  generatestring =	RandomStringUtils.randomNumeric(10);

	@SuppressWarnings("deprecation")
	String  generatenumber =	RandomStringUtils.randomNumeric(3);
	
	return (generatestring+"@"+generatenumber);  // here returing both alpha+numeric and also adding @ for special character this is most important for first time when we are automation registartion page
	}
	
	// Takes screen shot mathod and 
	/*public String captureScreen (String tname) throws WebDriverException  {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
		File sourceFile = null;
		try {
			File sourceFile1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\" + tname + "_ " +timeStamp + "_" + ".png";
				File targetFile = new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		return targetFilePath;  */
	
	public String captureScreen(String tname) {
	    String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	    String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";

	    // Ensure the screenshots directory exists
	    File screenshotsDir = new File(System.getProperty("user.dir") + "\\screenshots");
	    if (!screenshotsDir.exists()) {
	        screenshotsDir.mkdirs();
	    }

	    try {
	        // Capture the screenshot and assign to sourceFile
	        TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
	        File sourceFile = takeScreenshot.getScreenshotAs(OutputType.FILE);
	        
	        // Define the target file
	        File targetFile = new File(targetFilePath);
	        
	        // Copy sourceFile to targetFile
	        FileUtils.copyFile(sourceFile, targetFile);
	        System.out.println("Screenshot saved at: " + targetFilePath);
	        
	        return targetFilePath;

	    } catch (WebDriverException e) {
	        System.err.println("Failed to capture screenshot due to WebDriverException: " + e.getMessage());
	        return null;
	    } catch (IOException e) {
	        System.err.println("Failed to save screenshot due to IOException: " + e.getMessage());
	        return null;
	
	    }
	}
}
	
