package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration()
	{
		logger.info("***** Starting TC001_AccountRegistrationTest *****");
		//logger.info("This is debug log message");
		
		try 
		{
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked On MyAccount Link");
		
		hp.clickRegister();
		logger.info("Clicked On Register Link ");
		
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		
		logger.info ("Providing Customer Info..");
		
		regpage.setFirstName(randomeString().toUpperCase());
		regpage.setLastName(randomeString().toUpperCase());
		regpage.setEmail(randomeString()+"@gmail.com");// randomly generated the email
		regpage.setTelephone(randomeNumber());
		
		String password=randomeAlphaNumeric();
		
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		
		logger.info("Validating Expected message....");
		String confmsg=regpage.getConfirmationMsg();
		Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		
		logger.info("Test passed");
		}
		catch(Exception e)
		{
			logger.error("Test failed: ");
			//logger.debug("debug log...");
			//Assert.fail("Test failed: ");
		    Assert.fail();
			
		}
		
		logger.info("***** Finished TC001_AccountRegistrationTest *****");
		
		
	}

	//public WebDriver driver;
	/*
    @Test
	public void verify_account_registration {
      
	   HomePage hp =new HomePage(driver);
	  hp.clickMyAccount();
      hp.clickRegister();
			  
   //  AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
    	  
	AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
			
	regpage.setFirstName(randomeString().toUpperCase());
	regpage.setLastName(randomeString().toUpperCase());
	regpage.setEmail(randomeString()+"@gmail.com");// randomly generated the email
	regpage.setTelephone(randomeNumber());
			
	//String password=randomAlphaNumeric();
			
	String password=randomeAlphaNumeric();
			
	regpage.setPassword(password);
	regpage.setConfirmPassword(password);
			
	regpage.setPrivacyPolicy();
	regpage.clickContinue();
			
	String confmsg=regpage.getConfirmationMsg();
	Assert.assertEquals(confmsg, "Your Account Has Been Created!");
			
  }
  */
	  /*
	  		public String randomeString() {
	  			 String generatedstring=RandomStringUtils.randomAlphabetic(5);
	  			 return  generatedstring;
	  			 
	  		}
	  		public String randomeNumber() {
	  			 String  generatednumber=RandomStringUtils.randomNumeric(10);
	  			 return  generatednumber;
	  		}
	  		
	  		public String randomeAlphaNumeric() {
	  			
	  			    String generatedstring=RandomStringUtils.randomAlphabetic(3);
	  			    String generatednumber=RandomStringUtils.randomNumeric(10);
		  			return  (generatedstring+"@"+generatednumber);
	  		}
	  		*/
		  		

}
