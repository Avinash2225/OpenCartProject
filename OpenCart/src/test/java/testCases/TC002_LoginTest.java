package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups="Sanity")
	public void Verify_login() {
		
		logger.info("***********Starting TC_002_LoginTest***************");
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
		
		Assert.assertEquals(targetPage, true, "Login failed");
//		Assert.assertTrue(targetPage);
		
		
		logger.info("***********finished TC_002_LoginTest***************");
		}
		catch (Exception e4)
		{
			Assert.fail();
		}
		
		
		
	}
	

}
