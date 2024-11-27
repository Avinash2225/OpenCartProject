package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProvider1;

public class Tc003_LoginDDT  extends BaseClass{
	
	@Test(dataProvider= "LoginData" , dataProviderClass=DataProvider1.class , groups= "datadriven")  // getting data  provider from different class 
	public void verify_loginDDT(String email, String pwd, String exp)  // why we are using data providers because earlier we are using dta providers in same class but now we are using from
	{                             // different class that is why we are using this concept
		logger.info("*****************  Starting TC_003_Login ****************");
		try {
		// Homepage
		HomePage hp = new HomePage(driver);
		hp.clickMyaccount();
		hp.clickLogin();
	// this is loginpage
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));  // this is test case for valid credential
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		
	//	Myaccountpage
		
		MyAccountPage mapc = new MyAccountPage(driver);
		
		boolean targetPage = mapc.isMyAccountPageExists();
		if(exp.equalsIgnoreCase("Valid")) {
			if (targetPage ==true) {
				mapc.clickLogout();
				
				Assert.assertTrue(true);
					}
		}
			else {
				Assert.assertTrue(false);
			}
		
		if(exp.equalsIgnoreCase("invalid")){
			mapc.clickLogout();
			Assert.assertTrue(false);
		}
		else {
			Assert.assertTrue(true);
		}
		
		
	}catch(Exception e2) 
	{
		Assert.fail();
	}
		
		
	
	logger.info("**************** finished TC_003_Login ****************");
	}
}
		
		