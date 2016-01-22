package sprint13;

import java.io.IOException;
import java.util.regex.Pattern;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import common.Base;
import common.GlobalVariable;
import common.Reporter;
import eInventoryPageClasses.HomePage;
import sprint2.AbstractTest;

public class US924_DisplayCondimentsPercentageAndDoller extends AbstractTest
{
	
	//TC2333 Verify that the user is able to view "Condiment" on the Food Over Base landing page in the Current Month section
	
	@Test()
	
	public void sprint13_US924_TC2333() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	{
		/** Variable Section : **/
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Food over base page
		homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToFoodOverBasePage();
		//Verify that Condiment label is displaying
		if(Base.isElementDisplayed(By.xpath("//table[@id='raw_item_info_table+undefined+']//td[text()='Condiment']")))
		{
			Reporter.reportPassResult(
					browser,"sprint13_US924_TC2333",
					"Condiment Label should dispaly in Current month section",
					"Pass");
		}
		else
		{
			Reporter.reportTestFailure(
					browser,"sprint13_US924_TC2333","sprint13_US924_TC2333",
					"Condiment Label should dispaly in Current month section",
					"Fail");
			AbstractTest.takeSnapShot("sprint13_US924_TC2333");
		}
	
	}
		
	//TC2334 Verify that the user is able to view the aggregated month to date Condiment Cost $ in the Month to Date (Actual) $ column
		
		
		@Test()
		
		public void sprint13_US924_TC2334() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
		{
			/** Variable Section : **/
			String storeId = GlobalVariable.StoreId;
			String userId = GlobalVariable.userId;
			String pattern=("(\\$)(\\W{0,1})(\\d{1,4})(.)(\\d{1,2})");
			/***********************************/
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			// Navigate to Food over base page
			homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToFoodOverBasePage();
			Thread.sleep(3000);
			//Verify that the amount is showing in proper format
			String text=driver.findElement(By.xpath("//table[@id='raw_item_info_table+undefined+']//td[5][preceding-sibling::td[text()='Condiment']]")).getText().trim();
			if(Pattern.compile(pattern).matcher(text).matches())
			{
				Reporter.reportPassResult(
						browser,"sprint13_US924_TC2334",
						"Condimant month to date actual($) avlue should display in correct format",
						"Pass");
			}
			else
			{
				Reporter.reportTestFailure(
						browser,"sprint13_US924_TC2334","sprint13_US924_TC2334",
						"Condimant month to date actual($) avlue should display in correct format",
						"Fail");
				AbstractTest.takeSnapShot("sprint13_US924_TC2334");
			}
			
			
			
		}
	
	
	//TC2336 Verify that the user is able to view the aggregated month to date Condiment Cost $ in the Month to Date (Actual) $ column in proper $ format
	
	
		@Test()
		
		public void sprint13_US924_TC2336() throws RowsExceededException, BiffException, WriteException, IOException, InterruptedException
		{
			/** Variable Section : **/
			String storeId = GlobalVariable.StoreId;
			String userId = GlobalVariable.userId;
			String pattern=("(\\$)(\\W{0,1})(\\d{1,4})(.)(\\d{1,2})");
			/***********************************/
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			// Navigate to Food over base page
			homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToFoodOverBasePage();
			Thread.sleep(3000);
			//Verify that the amount is showing in proper format
			String text=driver.findElement(By.xpath("//table[@id='raw_item_info_table+undefined+']//td[5][preceding-sibling::td[text()='Condiment']]")).getText().trim();
			if(Pattern.compile(pattern).matcher(text).matches())
			{
				Reporter.reportPassResult(
						browser,"sprint13_US924_TC2336",
						"Condimant month to date actual($) value should display in correct format",
						"Pass");
			}
			else
			{
				Reporter.reportTestFailure(
						browser,"sprint13_US924_TC2336","sprint13_US924_TC2336",
						"Condimant month to date actual($) value should display in correct format",
						"Fail");
				AbstractTest.takeSnapShot("sprint13_US924_TC2336");
			}
		}
	
	//TC2338 Verify that the user is able to view the aggregated month to date Condiment % in the Month to Date (actual) % column
	
	@Test()
		
	public void sprint13_US924_TC2338() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	{
		/** Variable Section : **/
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String pattern=("(\\W{0,1})(\\d{1,4})(.)(\\d{1,2})(\\%)");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Food over base page
		homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToFoodOverBasePage();
		Thread.sleep(3000);
		//Verify that the amount is showing in proper format
		String text=driver.findElement(By.xpath("//table[@id='raw_item_info_table+undefined+']//td[4][preceding-sibling::td[text()='Condiment']]")).getText().trim();
		if(Pattern.compile(pattern).matcher(text).matches())
		{
			Reporter.reportPassResult(
					browser,"sprint13_US924_TC2338",
					"Condimant month to date actual(%) avlue should display in correct format",
					"Pass");
		}
		else
		{
			Reporter.reportTestFailure(
					browser,"sprint13_US924_TC2338","sprint13_US924_TC2338",
					"Condimant month to date actual(%) avlue should display in correct format",
					"Fail");
			AbstractTest.takeSnapShot("sprint13_US924_TC2338");
		}
		
	}
	
	//TC2339 Verify that the user is able to view the aggregated month to date Condiment % in the Month to Date (actual) % column in proper % format
	
	@Test()
	
	public void sprint13_US924_TC2339() throws RowsExceededException, BiffException, WriteException, IOException, InterruptedException
	{
		
		/** Variable Section : **/
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String pattern=("(\\W{0,1})(\\d{1,4})(.)(\\d{1,2})(\\%)");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Food over base page
		homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToFoodOverBasePage();
		Thread.sleep(3000);
		//Verify that the amount is showing in proper format
		String text=driver.findElement(By.xpath("//table[@id='raw_item_info_table+undefined+']//td[4][preceding-sibling::td[text()='Condiment']]")).getText().trim();
		if(Pattern.compile(pattern).matcher(text).matches())
		{
			Reporter.reportPassResult(
					browser,"sprint13_US924_TC2339",
					"Condimant month to date actual(%) avlue should display in correct format",
					"Pass");
		}
		else
		{
			Reporter.reportTestFailure(
					browser,"sprint13_US924_TC2339","sprint13_US924_TC2339",
					"Condimant month to date actual(%) avlue should display in correct format",
					"Fail");
			AbstractTest.takeSnapShot("sprint13_US924_TC2339");
		}
		
		
	}
	
	//TC2340 Verify that the user is able to view the aggregated month Condiment Cost $ in the Actual $ column for each historical month in proper $ format
	
	@Test()
	
	public void sprint13_US924_TC2340() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	{
		/** Variable Section : **/
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String pattern=("(\\$)(\\W{0,1})(\\d{1,4})(.)(\\d{1,2})");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Food over base page
		homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToFoodOverBasePage();
		Thread.sleep(3000);
		//Verify the condement value for each of the historical months
		String firstHistoricalMonth=driver.findElement(By.xpath("//table[@id='raw_item_info_table+1+']/tbody/tr/td[3][preceding-sibling::td[text()='Condiment']]")).getText().trim();
		String secondHistoricalMonth=driver.findElement(By.xpath("//table[@id='raw_item_info_table+2+']/tbody/tr/td[3][preceding-sibling::td[text()='Condiment']]")).getText().trim();
		String thirdHistoricalMonth=driver.findElement(By.xpath("//table[@id='raw_item_info_table+3+']/tbody/tr/td[3][preceding-sibling::td[text()='Condiment']]")).getText().trim();
		if(Pattern.compile(pattern).matcher(firstHistoricalMonth).matches()
			&& Pattern.compile(pattern).matcher(secondHistoricalMonth).matches()
			&& Pattern.compile(pattern).matcher(thirdHistoricalMonth).matches())
		{
			Reporter.reportPassResult(
					browser,"sprint13_US924_TC2340",
					"Condimant $ value should display for each of the historical month",
					"Pass");
		}
		else
		{
			Reporter.reportTestFailure(
					browser,"sprint13_US924_TC2340","sprint13_US924_TC2340",
					"Condimant $ value should display for each of the historical month",
					"Fail");
			AbstractTest.takeSnapShot("sprint13_US924_TC2340");
		}
	
	}
	
	
	//TC2341 Verify that the user is able to view the aggregated month Condiment % in the Actual % column for each historical month in proper % format
	
	

	@Test()
	
	public void sprint13_US924_TC2341() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	{
		/** Variable Section : **/
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String pattern=("(\\W{0,1})(\\d{1,4})(.)(\\d{1,2})(\\%)");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Food over base page
		homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToFoodOverBasePage();
		Thread.sleep(3000);
		//Verify the condement value for each of the historical months
		String firstHistoricalMonth=driver.findElement(By.xpath("//table[@id='raw_item_info_table+1+']/tbody/tr/td[2][preceding-sibling::td[text()='Condiment']]")).getText().trim();
		String secondHistoricalMonth=driver.findElement(By.xpath("//table[@id='raw_item_info_table+2+']/tbody/tr/td[2][preceding-sibling::td[text()='Condiment']]")).getText().trim();
		String thirdHistoricalMonth=driver.findElement(By.xpath("//table[@id='raw_item_info_table+3+']/tbody/tr/td[2][preceding-sibling::td[text()='Condiment']]")).getText().trim();
		if(Pattern.compile(pattern).matcher(firstHistoricalMonth).matches()
			&& Pattern.compile(pattern).matcher(secondHistoricalMonth).matches()
			&& Pattern.compile(pattern).matcher(thirdHistoricalMonth).matches())
		{
			Reporter.reportPassResult(
					browser,"sprint13_US924_TC2341",
					"Condimant % value should display for each of the historical month",
					"Pass");
		}
		else
		{
			Reporter.reportTestFailure(
					browser,"sprint13_US924_TC2341","sprint13_US924_TC2341",
					"Condimant % value should display for each of the historical month",
					"Fail");
			AbstractTest.takeSnapShot("sprint13_US924_TC2341");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
