package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*Data is valid  - login success - test pass  - logout
                                 - login failed - test fail

Data is invalid - login success - test fail  - logout
                                - login failed - test pass
*/



public class TC003_LoginDDT extends BaseClass{
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="Datadriven") //getting data provider from different class 
	 public void verify_loginDDT(String email,String pwd, String exp) {
		
		logger.info("**** Starting TC_003_LoginDDT *****");
		try {
		//HomePage
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin(); //Login link under MyAccount
			
		//Login page
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin(); //Login button
			
		//My Account Page
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage=macc.isMyAccountPageExists();
		
				
		if(exp.equalsIgnoreCase("valid"))
		{
			if(targetPage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(true);
				/*Assert.assertTrue(true);
				macc.clickLogout(); */
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(targetPage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
			logger.info("**** Finished TC_003_LoginDDT *****");
		}
	
  }
	catch(Exception e) {
		Assert.fail();
	  
  }
 }
}	
