package sprint14;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import common.GlobalVariable;
import common.ReadTestData;
import common.Reporter;

public class AbstractTest 

{
	protected static WebDriver driver;
	protected Properties prop;
	protected static WebDriverWait wait;
	protected Actions action;
	private static String browser=null;
	
	public static void takeSnapShot(String tcName)
	{
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	     //The below method will save the screen shot in d drive with name "TCXXX.png"
	        try {
	        	FileUtils.copyFile(scrFile, new File("FailureScreenshot/"+getBrowser()+"_"+tcName+".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public static WebDriver getDriver()
	{
		return driver;
		
	}
	
   public static String getBrowserName()
   {
	return getBrowser();
	   
   }
	
   @BeforeSuite
	public void beforeTest() throws WriteException, IOException
	{
		Reporter.createExcelReport();
	}
   
   @Parameters({"browser","StoreId"})
   @BeforeTest
   public void initializeTestData(String browser,String StoreId){
	   
	   GlobalVariable.StoreId=StoreId;
	   if(browser.equalsIgnoreCase("FF")){
		   ReadTestData.getTestScenarioSheet("TestData/eInventoryTestData_Firefox.xls");
	   }else if(browser.equalsIgnoreCase("IE")){
		   ReadTestData.getTestScenarioSheet("TestData/eInventoryTestData_IE.xls");
	   }else if(browser.equalsIgnoreCase("Chrome")){
		   ReadTestData.getTestScenarioSheet("TestData/eInventoryTestData_Chrome.xls");
	   }
	   
   }
   
	@Parameters({"browser"})
	@BeforeMethod(groups={"A","B"})
	public void beforeMethod(String browser)
		{
			this.setBrowser(browser);
		//initialize the driver and launch the application
			if(browser.equalsIgnoreCase("IE"))
			{
				System.setProperty("webdriver.ie.driver","Drivers/IEDriverServer.exe");
				driver=new InternetExplorerDriver();
				wait = new WebDriverWait(driver,60);
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(500, TimeUnit.SECONDS);
				action = new Actions(driver);
				driver.get("https://qa-ebos.qsrsoft.com");
				driver.manage().window().maximize();
			}
			else if(browser.equalsIgnoreCase("FF"))
			{
				ProfilesIni profile=new ProfilesIni();
				FirefoxProfile fprofile=new FirefoxProfile();
				fprofile=profile.getProfile("MyProfile");
				driver =new FirefoxDriver(fprofile);
				action = new Actions(driver);
				wait = new WebDriverWait(driver,60);
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(500, TimeUnit.SECONDS);
				driver.get("https://qa-ebos.qsrsoft.com");
				driver.manage().window().maximize();

			}
			else if(browser.equalsIgnoreCase("Chrome"))
			{	
                ChromeOptions options = new ChromeOptions();
                options.addArguments("test-type");              
				System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
				driver=new ChromeDriver(options);
				wait = new WebDriverWait(driver,60);
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(500, TimeUnit.SECONDS);
				action = new Actions(driver);
				driver.get("https://qa-ebos.qsrsoft.com");
				driver.manage().window().maximize();

			}
			else if(browser.equalsIgnoreCase("Safari"))
			{	
				driver = new SafariDriver();
			    action = new Actions(driver);
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(500, TimeUnit.SECONDS);
				driver.get("http://www.google.com/");
				driver.manage().window().maximize();

			}
		
			
		}
	
	@AfterTest
	public void afterTest() throws BiffException, WriteException, IOException
	{
		Reporter.updateTotalCount(getBrowser());

	}
	
	@AfterSuite
	public void afterSuite() throws RowsExceededException, BiffException, WriteException, IOException
	{
//		Reporter.updateTotalCount();
	}
	
	@AfterMethod(groups={"A","B"})
	
	public void afterMethod() throws BiffException, IOException, WriteException
	{
		Reporter.updateTestReport(getBrowser());
		driver.close();
		driver.quit();
	}

	public static String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		AbstractTest.browser = browser;
	}
	

}
