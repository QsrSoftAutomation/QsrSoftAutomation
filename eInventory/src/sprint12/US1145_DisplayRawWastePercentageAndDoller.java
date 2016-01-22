package sprint12;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import common.GlobalVariable;
import common.Reporter;
import eInventoryPageClasses.HomePage;
import sprint2.AbstractTest;

public class US1145_DisplayRawWastePercentageAndDoller extends AbstractTest
{
	
	//TC2172 Verify that the user is able to view the the Current Month Raw Waste $ in the Month to Date (Actual) $ column on the Food Over Base landing page in the Current Month section
	@Test()
	
	public void Sprint11_US1145_TC2172() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException 
	{
		/** Variable Section : **/
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String [] record=new String[8];
		String pattern=("(\\$)(\\W{0,1})(\\d{1,2})(.)(\\d{1,2})");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Food over base page
		homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToFoodOverBasePage();
		Thread.sleep(4000);
		List <WebElement> monthToDate =driver.findElements(By.xpath("//table[@id='raw_item_info_table+undefined+']/tbody/tr/td[5]"));
		//Verify  that raw waste amount is showing for all the 8 fields
		for(int i=0;i<=7;i++)
		{
			record[i]=monthToDate.get(i).getText().trim();
			System.out.println(record[i]);
			System.out.println(Pattern.compile(pattern).matcher(record[i]).matches());
			if(Pattern.compile(pattern).matcher(record[i]).matches())
			{
				if(i==7)
				{
					Reporter.reportPassResult(
							browser,"Sprint11_US1145_TC2172",
							"User should be able to see Current month raw waste $ amount in Month to date(Actual) column",
							"Pass");
					break;
				}
				continue;
			}
			else
			{
				Reporter.reportTestFailure(
						browser,"Sprint11_US1145_TC2172","sprint12_US1125_TC2149",
						"User should be able to see Current month raw waste $ amount in Month to date(Actual) column",
						"Fail");
				AbstractTest.takeSnapShot("Sprint11_US1145_TC2172");
				break;
			}
		}
	}
	
	//TC2174 Verify that the user is able to view the the Current Month Raw Waste $ amount in proper dollar format in the Month to Date (Actual) $ column on the Food Over Base landing page in the Current Month section 
	
	@Test()
	
	public void Sprint11_US1145_TC2174() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	{
		/** Variable Section : **/
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String [] record=new String[8];
		String pattern=("(\\$)(\\W{0,1})(\\d{1,2})(.)(\\d{1,2})");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Food over base page
		homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToFoodOverBasePage();
		Thread.sleep(4000);
		List <WebElement> monthToDate =driver.findElements(By.xpath("//table[@id='raw_item_info_table+undefined+']/tbody/tr/td[5]"));
		//Verify  that raw waste amount is showing for all the 8 fields
		for(int i=0;i<=7;i++)
		{
			record[i]=monthToDate.get(i).getText().trim();
			System.out.println(record[i]);
			System.out.println(Pattern.compile(pattern).matcher(record[i]).matches());
			if(Pattern.compile(pattern).matcher(record[i]).matches())
			{
				if(i==7)
				{
					Reporter.reportPassResult(
							browser,"Sprint11_US1145_TC2172",
							"User should be able to see Current month raw waste $ amount in Month to date(Actual) column",
							"Pass");
					break;
				}
				continue;
			}
			else
			{
				Reporter.reportTestFailure(
						browser,"Sprint11_US1145_TC2172","sprint12_US1125_TC2149",
						"User should be able to see Current month raw waste $ amount in Month to date(Actual) column",
						"Fail");
				AbstractTest.takeSnapShot("Sprint11_US1145_TC2172");
				break;
			}
		}
	}
	
//TC2175 Verify that the user is able to view the the Current Month Raw Waste % in the Month to Date (Actual) % column on the Food Over Base landing page in the Current Month section
	
	
	@Test()
	
	public void Sprint11_US1145_TC2175() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException 
	{
		/** Variable Section : **/
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String [] record=new String[8];
		String pattern=("(\\W{0,1})(\\d{1,2})(.)(\\d{1,2})(\\s{0,1})(\\%)");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Food over base page
		homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToFoodOverBasePage();
		Thread.sleep(4000);
		List <WebElement> monthToDate =driver.findElements(By.xpath("//table[@id='raw_item_info_table+undefined+']/tbody/tr/td[4]"));
		//Verify  that raw waste amount is showing for all the 8 fields
		for(int i=0;i<=7;i++)
		{
			record[i]=monthToDate.get(i).getText().trim();
			System.out.println(record[i]);
			System.out.println(Pattern.compile(pattern).matcher(record[i]).matches());
			if(Pattern.compile(pattern).matcher(record[i]).matches())
			{
				if(i==7)
				{
					Reporter.reportPassResult(
							browser,"Sprint11_US1145_TC2175",
							"User should be able to see Current month raw waste % amount in Month to date(Actual) column",
							"Pass");
					break;
				}
				continue;
			}
			else
			{
				Reporter.reportTestFailure(
						browser,"Sprint11_US1145_TC2175","Sprint11_US1145_TC2175",
						"User should be able to see Current month raw waste % amount in Month to date(Actual) column",
						"Fail");
				AbstractTest.takeSnapShot("Sprint11_US1145_TC2175");
				break;
			}
		}
		
		
	}
	
	//TC2176 Verify that the user is able to view the the Current Month Raw Waste % amount in proper percentage format in the Month to Date (Actual) % column on the Food Over Base landing page in the Current Month section.
	

	@Test()
	
	public void Sprint11_US1145_TC2176() throws RowsExceededException, BiffException, WriteException, IOException, InterruptedException
	{
		/** Variable Section : **/
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String [] record=new String[8];
		String pattern=("(\\W{0,1})(\\d{1,2})(.)(\\d{1,2})(\\s{0,1})(\\%)");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Food over base page
		homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToFoodOverBasePage();
		Thread.sleep(4000);
		List <WebElement> monthToDate =driver.findElements(By.xpath("//table[@id='raw_item_info_table+undefined+']/tbody/tr/td[4]"));
		//Verify  that raw waste amount is showing for all the 8 fields
		for(int i=0;i<=7;i++)
		{	System.out.println("eye is"+i);
			record[i]=monthToDate.get(i).getText().trim();
			System.out.println(record[i]);
			System.out.println(Pattern.compile(pattern).matcher(record[i]).matches());
			if(Pattern.compile(pattern).matcher(record[i]).matches())
			{
				if(i==7)
				{
					Reporter.reportPassResult(
							browser,"Sprint11_US1145_TC2176",
							"User should be able to see Current month raw waste % amount in Month to date(Actual) column",
							"Pass");
					break;
				}
				continue;
			}
			else
			{
				Reporter.reportTestFailure(
						browser,"Sprint11_US1145_TC2176","Sprint11_US1145_TC2176",
						"User should be able to see Current month raw waste % amount in Month to date(Actual) column",
						"Fail");
				AbstractTest.takeSnapShot("Sprint11_US1145_TC2176");
				break;
			}
		}
	}

	
	//TC2178 Verify that the user is able to view the Historical Month Raw Waste $ in the Actual $ column for each historical month on the Food Over Base landing page in the Historical Month section.
	
	@Test()
	
	public void Sprint11_US1145_TC2178() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	{
		
		/** Variable Section : **/
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String [] record=new String[8];
		String [] record1=new String[8];
		String [] record2=new String[8];
		String pattern=("(\\$)(\\W{0,1})(\\d{1,4})(.)(\\d{1,2})");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Food over base page
		homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToFoodOverBasePage();
		Thread.sleep(4000);
		//Verify the correct actual $ is showing for all the fields for first Historical month
		List <WebElement> actualDollarFirstHistoricalMonth =driver.findElements(By.xpath("//table[@id='raw_item_info_table+1+']/tbody/tr/td[3]"));
		List <WebElement> actualDollarSecondHistoricalMonth =driver.findElements(By.xpath("//table[@id='raw_item_info_table+2+']/tbody/tr/td[3]"));
		List <WebElement> actualDollarThirdHistoricalMonth =driver.findElements(By.xpath("//table[@id='raw_item_info_table+3+']/tbody/tr/td[3]"));

		for(int i=0;i<=7;i++)
		{
			record[i]=actualDollarFirstHistoricalMonth.get(i).getText().trim();
			System.out.println(record[i]);
			System.out.println(Pattern.compile(pattern).matcher(record[i]).matches());
			
			record1[i]=actualDollarSecondHistoricalMonth.get(i).getText().trim();
			System.out.println(record1[i]);
			System.out.println(Pattern.compile(pattern).matcher(record1[i]).matches());
			
			record2[i]=actualDollarThirdHistoricalMonth.get(i).getText().trim();
			System.out.println(record2[i]);
			System.out.println(Pattern.compile(pattern).matcher(record2[i]).matches());
			
			if(Pattern.compile(pattern).matcher(record[i]).matches()
					&& Pattern.compile(pattern).matcher(record1[i]).matches()
					&& Pattern.compile(pattern).matcher(record2[i]).matches())
			{
				if(i==7)
				{
					Reporter.reportPassResult(
							browser,"Sprint11_US1145_TC2178",
							"User should be able to view the Historical Month Raw Waste $ in the Actual $ column for each historical month in the Historical Month section.",
							"Pass");
					break;
				}
				continue;
			}
			else
			{
				Reporter.reportTestFailure(
						browser,"Sprint11_US1145_TC2178","Sprint11_US1145_TC2178",
						"User should be able to view the Historical Month Raw Waste $ in the Actual $ column for each historical month in the Historical Month section.",
						"Fail");
				AbstractTest.takeSnapShot("Sprint11_US1145_TC2178");
				break;
			}
		}
		
	}
	
	//TC2179 Verify that the user is able to view the Historical Month Raw Waste $ in proper dollar format in the Actual $ column for each historical month on the Food Over Base landing page in the Historical Month section.
	
		@Test()
		
		public void Sprint11_US1145_TC2179() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
		{
			
			/** Variable Section : **/
			String storeId = GlobalVariable.StoreId;
			String userId = GlobalVariable.userId;
			String [] record=new String[8];
			String [] record1=new String[8];
			String [] record2=new String[8];
			String pattern=("(\\$)(\\W{0,1})(\\d{1,4})(.)(\\d{1,2})");
			/***********************************/
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			// Navigate to Food over base page
			homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToFoodOverBasePage();
			Thread.sleep(4000);
			//Verify the correct actual $ is showing for all the fields for first Historical month
			List <WebElement> actualDollarFirstHistoricalMonth =driver.findElements(By.xpath("//table[@id='raw_item_info_table+1+']/tbody/tr/td[3]"));
			List <WebElement> actualDollarSecondHistoricalMonth =driver.findElements(By.xpath("//table[@id='raw_item_info_table+2+']/tbody/tr/td[3]"));
			List <WebElement> actualDollarThirdHistoricalMonth =driver.findElements(By.xpath("//table[@id='raw_item_info_table+3+']/tbody/tr/td[3]"));

			for(int i=0;i<=7;i++)
			{
				record[i]=actualDollarFirstHistoricalMonth.get(i).getText().trim();
				System.out.println(record[i]);
				System.out.println(Pattern.compile(pattern).matcher(record[i]).matches());
				
				record1[i]=actualDollarSecondHistoricalMonth.get(i).getText().trim();
				System.out.println(record1[i]);
				System.out.println(Pattern.compile(pattern).matcher(record1[i]).matches());
				
				record2[i]=actualDollarThirdHistoricalMonth.get(i).getText().trim();
				System.out.println(record2[i]);
				System.out.println(Pattern.compile(pattern).matcher(record2[i]).matches());
				
				if(Pattern.compile(pattern).matcher(record[i]).matches()
						&& Pattern.compile(pattern).matcher(record1[i]).matches()
						&& Pattern.compile(pattern).matcher(record2[i]).matches())
				{
					if(i==7)
					{
						Reporter.reportPassResult(
								browser,"Sprint11_US1145_TC2179",
								"User should be able to view the Historical Month Raw Waste $ in the Actual $ column for each historical month in the Historical Month section.",
								"Pass");
						break;
					}
					continue;
				}
				else
				{
					Reporter.reportTestFailure(
							browser,"Sprint11_US1145_TC2179","Sprint11_US1145_TC2179",
							"User should be able to view the Historical Month Raw Waste $ in the Actual $ column for each historical month in the Historical Month section.",
							"Fail");
					AbstractTest.takeSnapShot("Sprint11_US1145_TC2179");
					break;
				}
			}
			
		}
	
//TC2180 Verify that the user is able to view the Historical Month Raw Waste % in the Actual % column for each historical month on the Food Over Base landing page in the Historical Month section.	
	
	@Test()
			
	public void Sprint11_US1145_TC2180() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	{
		/** Variable Section : **/
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String [] record=new String[8];
		String [] record1=new String[8];
		String [] record2=new String[8];
		String pattern=("(\\W{0,1})(\\d{1,2})(.)(\\d{1,2})(\\s{0,1})(\\%)");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Food over base page
		homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToFoodOverBasePage();
		Thread.sleep(4000);
		//User should be able to view the Historical Month Raw Waste % in the Actual % column for each historical month in the Historical Month section
		List <WebElement> actualDollarFirstHistoricalMonth =driver.findElements(By.xpath("//table[@id='raw_item_info_table+1+']/tbody/tr/td[2]"));
		List <WebElement> actualDollarSecondHistoricalMonth =driver.findElements(By.xpath("//table[@id='raw_item_info_table+2+']/tbody/tr/td[2]"));
		List <WebElement> actualDollarThirdHistoricalMonth =driver.findElements(By.xpath("//table[@id='raw_item_info_table+3+']/tbody/tr/td[2]"));

		for(int i=0;i<=7;i++)
		{
			record[i]=actualDollarFirstHistoricalMonth.get(i).getText().trim();
			System.out.println(record[i]);
			System.out.println(Pattern.compile(pattern).matcher(record[i]).matches());
			
			record1[i]=actualDollarSecondHistoricalMonth.get(i).getText().trim();
			System.out.println(record1[i]);
			System.out.println(Pattern.compile(pattern).matcher(record1[i]).matches());
			
			record2[i]=actualDollarThirdHistoricalMonth.get(i).getText().trim();
			System.out.println(record2[i]);
			System.out.println(Pattern.compile(pattern).matcher(record2[i]).matches());
			
			if(Pattern.compile(pattern).matcher(record[i]).matches()
					&& Pattern.compile(pattern).matcher(record1[i]).matches()
					&& Pattern.compile(pattern).matcher(record2[i]).matches())
			{
				if(i==7)
				{
					Reporter.reportPassResult(
							browser,"Sprint11_US1145_TC2180",
							"User should be able to view the Historical Month Raw Waste % in the Actual % column for each historical month in the Historical Month section",
							"Pass");
					break;
				}
				continue;
			}
			else
			{
				Reporter.reportTestFailure(
						browser,"Sprint11_US1145_TC2180","Sprint11_US1145_TC2180",
						"User should be able to view the Historical Month Raw Waste % in the Actual % column for each historical month in the Historical Month section",
						"Fail");
				AbstractTest.takeSnapShot("Sprint11_US1145_TC2180");
				break;
			}
		}
		
		
		
		
	}
	
	
	//TC2181 Verify that the user is able to view the Historical Month Raw Waste % in proper percentage format in the Actual % column for each historical month on the Food Over Base landing page in the Historical Month section.	
	
		@Test()
				
		public void Sprint11_US1145_TC2181() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
		{
			/** Variable Section : **/
			String storeId = GlobalVariable.StoreId;
			String userId = GlobalVariable.userId;
			String [] record=new String[8];
			String [] record1=new String[8];
			String [] record2=new String[8];
			String pattern=("(\\W{0,1})(\\d{1,2})(.)(\\d{1,2})(\\s{0,1})(\\%)");
			/***********************************/
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			// Navigate to Food over base page
			homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToFoodOverBasePage();
			Thread.sleep(4000);
			//User should be able to view the Historical Month Raw Waste % in the Actual % column for each historical month in the Historical Month section
			List <WebElement> actualDollarFirstHistoricalMonth =driver.findElements(By.xpath("//table[@id='raw_item_info_table+1+']/tbody/tr/td[2]"));
			List <WebElement> actualDollarSecondHistoricalMonth =driver.findElements(By.xpath("//table[@id='raw_item_info_table+2+']/tbody/tr/td[2]"));
			List <WebElement> actualDollarThirdHistoricalMonth =driver.findElements(By.xpath("//table[@id='raw_item_info_table+3+']/tbody/tr/td[2]"));

			for(int i=0;i<=7;i++)
			{
				record[i]=actualDollarFirstHistoricalMonth.get(i).getText().trim();
				System.out.println(record[i]);
				System.out.println(Pattern.compile(pattern).matcher(record[i]).matches());
				
				record1[i]=actualDollarSecondHistoricalMonth.get(i).getText().trim();
				System.out.println(record1[i]);
				System.out.println(Pattern.compile(pattern).matcher(record1[i]).matches());
				
				record2[i]=actualDollarThirdHistoricalMonth.get(i).getText().trim();
				System.out.println(record2[i]);
				System.out.println(Pattern.compile(pattern).matcher(record2[i]).matches());
				
				if(Pattern.compile(pattern).matcher(record[i]).matches()
						&& Pattern.compile(pattern).matcher(record1[i]).matches()
						&& Pattern.compile(pattern).matcher(record2[i]).matches())
				{
					if(i==7)
					{
						Reporter.reportPassResult(
								browser,"Sprint11_US1145_TC2181",
								"User should be able to view the Historical Month Raw Waste % in the Actual % column for each historical month in the Historical Month section",
								"Pass");
						break;
					}
					continue;
				}
				else
				{
					Reporter.reportTestFailure(
							browser,"Sprint11_US1145_TC2181","Sprint11_US1145_TC2181",
							"User should be able to view the Historical Month Raw Waste % in the Actual % column for each historical month in the Historical Month section",
							"Fail");
					AbstractTest.takeSnapShot("Sprint11_US1145_TC2181");
					break;
				}
			}
			
		}
		
		
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
