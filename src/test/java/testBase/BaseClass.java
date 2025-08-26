package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import org.apache.logging.log4j.LogManager;//log4j
import org.apache.logging.log4j.Logger;   //log4j




//Reusability we have created BaseClass

public class BaseClass {
	 public static WebDriver driver;
	 public Logger logger; //log4j
	 public Properties p;
	  
	  @BeforeClass(groups= {"Sanity", "Regression","Master"})
	  @Parameters({"os","browser"})
	  public void setup(String os, String br) throws IOException  
	  {
		  //Loading Properties file
		  FileReader file=new FileReader("./src//test//resources//config.properties");
		  p=new Properties();
		  p.load(file);
		  
		  logger=LogManager.getLogger(this.getClass());//Log4j2
		  
		  switch(br.toLowerCase()) 
		  {
		  case "chrome":driver=new ChromeDriver();break;
		  case "firefox" : driver=new FirefoxDriver();break;
		  case "edge" : driver:new EdgeDriver();break;
		  default :System.out.println("Invalid Browser Name..."); return;
		  }

		 // driver=new ChromeDriver();
		  
		  driver.manage().deleteAllCookies();
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		  //Thread.sleep(2000);
		  
		  //driver.get("https://tutorialsninja.com/demo/");
		  driver.get(p.getProperty("appURL2"));   //reading URL from properties file
		  driver.manage().window().maximize();
	  }
	  @AfterClass(groups= {"Sanity", "Regression","Master"})
	  public void tearDown() {
		  driver.quit();
		  
	  }
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
		
		public String captureScreen(String tname) throws IOException {

			String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
					
			TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
			File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
			
			String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
			File targetFile=new File(targetFilePath);
			
			sourceFile.renameTo(targetFile);
				
			return targetFilePath;

		}

}
