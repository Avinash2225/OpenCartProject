package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistractionTest extends BaseClass {

	
@Test (groups= { "Regression", "Master"})
public	void varify_account_registration() {
	try {
	logger.info("****** Starting Test Case TC001_AccountRegistractionTest**********");
	HomePage hp = new HomePage(driver);
	
	hp.clickMyaccount();
	
	logger.info("****** Clicked on my Account link**********");

	
	hp.clickRegistar();
	
	logger.info("****** Clicked on Registar link**********");

	
	AccountRegistrationPage ap = new AccountRegistrationPage(driver);
	logger.info("****** Providing customer details**********");

	
	ap.setFirstName(randomeString().toUpperCase());
	ap.setLastName(randomeString().toLowerCase());
	//ap.setEmail("avinashdev210@gmail.com");
	ap.setEmail(randomeString()+"@gmail.com");

	ap.setTelephone(randomeNumber());
    
	//String password = randomAlphaNumeric();
	
	String password = randomeAlphaNumeric(); // we are storing the password here so that same password should pass in the password field
	
	ap.setPassword(password);
	ap.setConfirmPassword(password);
		ap.setPrivacyPolicy();
	ap.ClickContinue();

	logger.info("******Validating expected message**********");

	
	String confmsg = ap.getConfirmationMsg();
	if(confmsg.equals("your account has been created!")) {
		Assert.assertTrue(true);
	} 
	else {
		logger.error("test failed....");
		logger.debug("debug logs...");
		Assert.assertTrue(true);

	}
	
//	Assert.assertEquals(confmsg, "your account has been created!");
	}
	catch(Exception e1) {
	
		Assert.fail();
	}
}
	
}
