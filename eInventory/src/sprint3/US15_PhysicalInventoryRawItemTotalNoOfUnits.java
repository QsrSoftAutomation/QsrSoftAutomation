package sprint3;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import common.Base;
import common.GlobalVariable;
import common.ReadTestData;
import common.Reporter;
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.PhysicalInventoryPage;
import sprint2.AbstractTest;

public class US15_PhysicalInventoryRawItemTotalNoOfUnits extends AbstractTest
{
	
	//TC2054 (Can not be automated)
	
	//TC2055 Verify that the user cannot enter more than 2 digits after decimal pplaces in outer pack, inner pack and loose unit columns.
	
	@Test()
	
	public void Sprint3_US15_TC2055() throws RowsExceededException, BiffException, WriteException, IOException, InterruptedException
	{
		/*Start-Variable Deceleration*/
		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint3_US15_TC2055", "Object1");
		String wrinId = ReadTestData.getTestData(manualInvoiceNewPageSheet, "WRINId");
		String outerPack = "12.452";
		String innerPack="14.352";
		String looseUnits="11.111";
		String userId=GlobalVariable.userId;
		String storeId=GlobalVariable.StoreId;
		PhysicalInventoryPage physicalInventoryPage;
		String time;
		String date=Base.returnTodayDate();
		/*End*/
		
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		// Navigate to physical inventory Page
		physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPhysicalInventoryPage();
		//Click on start New Inventory button
		physicalInventoryPage.StartNewInventory_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.StartInventory_BT));
		//Fetch the Time of Inventory
		time=physicalInventoryPage.SelectDateAndTimeForNewInventoryPopUp_Time_TB.getAttribute("value");
		System.out.println("Time is"+time);
		physicalInventoryPage.SelectDateAndTimeForNewInventoryPopUp_Cancel_BT.click();
		Thread.sleep(2000);
		physicalInventoryPage.BackToTop_BT.click();
		try
		{
			physicalInventoryPage.clickOnInProgressInventory(date, time);

		}
		catch(Exception e)
		{
			physicalInventoryPage.StartNewInventory_BT.click();
			physicalInventoryPage.StartInventory_BT.click();
			wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.CreateInventory_Search_TB));
		}
		
		/*while (Base.isElementDisplayed(physicalInventoryPage.AlreadyAnInventoryPopUp_ViewInventory_BT)) {
			Thread.sleep(180000);
			physicalInventoryPage.AlreadyAnInventoryPopUp_Cancel_BT.click();
			physicalInventoryPage.StartInventory_BT.click();

		}*/
		//Select the monthly from drop down
		physicalInventoryPage.selectInventoryType("Monthly");
		try{
			physicalInventoryPage.acceptTheAlertMessage();
			Thread.sleep(20000);
		}catch(Exception e){}
		physicalInventoryPage.CreateInventory_Search_TB.sendKeys(wrinId);
		Thread.sleep(2000);
		// enter the outer pack value and verify that error message is displaying for invalid outer pack
		physicalInventoryPage.OuterPackQty_TB.sendKeys(outerPack);
		if(driver.findElement(By.xpath("//div[@class='toast-message' and text()='Values must be numeric with up to 2 decimals. (Example: 12345.99)']")).isDisplayed())
		{
			Reporter.reportPassResult(
					browser,"Sprint3_US15_TC2055",
					"Error message should display for invalid outer pack value",
					"Pass");
			
		}
		else
		{
			Reporter.reportTestFailure(
					browser,"Sprint3_US15_TC2055_Condition1","Sprint3_US15_TC2055",
					"Error message should display for invalid outer pack value",
					"Fail");
			AbstractTest.takeSnapShot("Sprint3_US15_TC2055_Condition1");
		}
		Thread.sleep(5000);
		// enter the inner pack value and verify that error message is displaying for invalid outer pack
				physicalInventoryPage.InnerPackQty_TB.sendKeys(innerPack);
				if(driver.findElement(By.xpath("//div[@class='toast-message' and text()='Values must be numeric with up to 2 decimals. (Example: 12345.99)']")).isDisplayed())
				{
					Reporter.reportPassResult(
							browser,"Sprint3_US15_TC2055",
							"Error message should display for invalid inner pack value",
							"Pass");
					
				}
				else
				{
					Reporter.reportTestFailure(
							browser,"Sprint3_US15_TC2055_Condition2","Sprint3_US15_TC2055",
							"Error message should display for invalid inner pack value",
							"Fail");
					AbstractTest.takeSnapShot("Sprint3_US15_TC2055_Condition2");
				}
				
				Thread.sleep(5000);
				// enter the loose units value and verify that error message is displaying for invalid outer pack
						physicalInventoryPage.LooseCountQty_TB.sendKeys(looseUnits);
						if(driver.findElement(By.xpath("//div[@class='toast-message' and text()='Values must be numeric with up to 2 decimals. (Example: 12345.99)']")).isDisplayed())
						{
							Reporter.reportPassResult(
									browser,"Sprint3_US15_TC2055",
									"Error message should display for invalid loose units value",
									"Pass");
							
						}
						else
						{
							Reporter.reportTestFailure(
									browser,"Sprint3_US15_TC2055_Condition3","Sprint3_US15_TC2055",
									"Error message should display for invalid loose units value",
									"Fail");
							AbstractTest.takeSnapShot("Sprint3_US15_TC2055_Condition3");
						}
		
	}
	
//TC2056 Verify that a user can enter only 4 digits before a decimal and 2 digits after the decimal in the outer pack column.		
		
@Test()
	
	public void Sprint3_US15_TC2056() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	{
	
	/*Start-Variable Deceleration*/
	HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint3_US15_TC2056", "Object1");
	String wrinId = ReadTestData.getTestData(manualInvoiceNewPageSheet, "WRINId");
	String invalidOuterPack = "12345.551";
	String validOuterPack="1234.55";
	String userId=GlobalVariable.userId;
	String storeId=GlobalVariable.StoreId;
	PhysicalInventoryPage physicalInventoryPage;
	String time;
	String date=Base.returnTodayDate();
	/*End*/
	
	HomePage homePage=PageFactory.initElements(driver, HomePage.class);
	// Navigate to physical inventory Page
	physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPhysicalInventoryPage();
	//Click on start New Inventory button
	physicalInventoryPage.StartNewInventory_BT.click();
	wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.StartInventory_BT));
	//Fetch the Time of Inventory
	time=physicalInventoryPage.SelectDateAndTimeForNewInventoryPopUp_Time_TB.getAttribute("value");
	System.out.println("Time is"+time);
	physicalInventoryPage.SelectDateAndTimeForNewInventoryPopUp_Cancel_BT.click();
	Thread.sleep(2000);
	physicalInventoryPage.BackToTop_BT.click();
	try
	{
		physicalInventoryPage.clickOnInProgressInventory(date, time);

	}
	catch(Exception e)
	{
		physicalInventoryPage.StartNewInventory_BT.click();
		physicalInventoryPage.StartInventory_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.CreateInventory_Search_TB));
	}
	
	/*while (Base.isElementDisplayed(physicalInventoryPage.AlreadyAnInventoryPopUp_ViewInventory_BT)) {
		Thread.sleep(180000);
		physicalInventoryPage.AlreadyAnInventoryPopUp_Cancel_BT.click();
		physicalInventoryPage.StartInventory_BT.click();

	}*/
	//Select the monthly from drop down
	physicalInventoryPage.selectInventoryType("Monthly");
	try{
		physicalInventoryPage.acceptTheAlertMessage();
		Thread.sleep(20000);
	}catch(Exception e){}
	physicalInventoryPage.CreateInventory_Search_TB.sendKeys(wrinId);
	Thread.sleep(2000);
	// enter the outer pack value and verify that error message is displaying for invalid outer pack
	physicalInventoryPage.OuterPackQty_TB.sendKeys(invalidOuterPack);
	if(driver.findElement(By.xpath("//div[@class='toast-message' and text()='Values must be numeric with up to 2 decimals. (Example: 12345.99)']")).isDisplayed())
	{
		Reporter.reportPassResult(
				browser,"Sprint3_US15_TC2056",
				"Error message should display for invalid outer pack value",
				"Pass");
		
	}
	else
	{
		Reporter.reportTestFailure(
				browser,"Sprint3_US15_TC2056_Condition1","Sprint3_US15_TC2056",
				"Error message should display for invalid outer pack value",
				"Fail");
		AbstractTest.takeSnapShot("Sprint3_US15_TC2056_Condition1");
	}
	
	Thread.sleep(5000);
	// enter the valid outer pack value
	physicalInventoryPage.OuterPackQty_TB.clear();
	physicalInventoryPage.OuterPackQty_TB.sendKeys(validOuterPack);
	
	if(driver.findElements(By.xpath("//div[@class='toast-message' and text()='Values must be numeric with up to 2 decimals. (Example: 12345.99)']")).size()==0)
	{
		Reporter.reportPassResult(
				browser,"Sprint3_US15_TC2056",
				"Error message should not display for valid outer pack value",
				"Pass");
		
	}
	else
	{
		Reporter.reportTestFailure(
				browser,"Sprint3_US15_TC2056_Condition2","Sprint3_US15_TC2056",
				"Error message should not display for valid outer pack value",
				"Fail");
		AbstractTest.takeSnapShot("Sprint3_US15_TC2056_Condition2");
	}
}
	
	
//TC2057 Verify that a user can enter only 5 digits before a decimal and 2 digits after the decimal in the inner pack column.

@Test()

public void Sprint3_US15_TC2057() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
{
	
	/*Start-Variable Deceleration*/
	HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint3_US15_TC2057", "Object1");
	String wrinId = ReadTestData.getTestData(manualInvoiceNewPageSheet, "WRINId");
	String invalidInnerPack = "123456.551";
	String validinnerPack="12345.55";
	String userId=GlobalVariable.userId;
	String storeId=GlobalVariable.StoreId;
	PhysicalInventoryPage physicalInventoryPage;
	String time;
	String date=Base.returnTodayDate();
	/*End*/
	
	HomePage homePage=PageFactory.initElements(driver, HomePage.class);
	// Navigate to physical inventory Page
	physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPhysicalInventoryPage();
	//Click on start New Inventory button
	physicalInventoryPage.StartNewInventory_BT.click();
	wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.StartInventory_BT));
	//Fetch the Time of Inventory
	time=physicalInventoryPage.SelectDateAndTimeForNewInventoryPopUp_Time_TB.getAttribute("value");
	System.out.println("Time is"+time);
	physicalInventoryPage.SelectDateAndTimeForNewInventoryPopUp_Cancel_BT.click();
	Thread.sleep(2000);
	physicalInventoryPage.BackToTop_BT.click();
	try
	{
		physicalInventoryPage.clickOnInProgressInventory(date, time);

	}
	catch(Exception e)
	{
		physicalInventoryPage.StartNewInventory_BT.click();
		physicalInventoryPage.StartInventory_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.CreateInventory_Search_TB));
	}
	
	/*while (Base.isElementDisplayed(physicalInventoryPage.AlreadyAnInventoryPopUp_ViewInventory_BT)) {
		Thread.sleep(180000);
		physicalInventoryPage.AlreadyAnInventoryPopUp_Cancel_BT.click();
		physicalInventoryPage.StartInventory_BT.click();

	}*/
	//Select the monthly from drop down
	physicalInventoryPage.selectInventoryType("Monthly");
	try{
		physicalInventoryPage.acceptTheAlertMessage();
		Thread.sleep(20000);
	}catch(Exception e){}
	physicalInventoryPage.CreateInventory_Search_TB.sendKeys(wrinId);
	Thread.sleep(2000);
	// enter the inner pack value and verify that error message is displaying for invalid outer pack
	physicalInventoryPage.InnerPackQty_TB.sendKeys(invalidInnerPack);
	if(driver.findElement(By.xpath("//div[@class='toast-message' and text()='Values must be numeric with up to 2 decimals. (Example: 12345.99)']")).isDisplayed())
	{
		Reporter.reportPassResult(
				browser,"Sprint3_US15_TC2057",
				"Error message should display for invalid inner pack value",
				"Pass");
		
	}
	else
	{
		Reporter.reportTestFailure(
				browser,"Sprint3_US15_TC2057_Condition1","Sprint3_US15_TC2057",
				"Error message should display for invalid inner pack value",
				"Fail");
		AbstractTest.takeSnapShot("Sprint3_US15_TC2057_Condition1");
	}
	
	Thread.sleep(5000);
	// enter the valid inner pack value
	physicalInventoryPage.InnerPackQty_TB.clear();
	physicalInventoryPage.InnerPackQty_TB.sendKeys(validinnerPack);
	
	if(driver.findElements(By.xpath("//div[@class='toast-message' and text()='Values must be numeric with up to 2 decimals. (Example: 12345.99)']")).size()==0)
	{
		Reporter.reportPassResult(
				browser,"Sprint3_US15_TC2057",
				"Error message should not display for valid inner pack value",
				"Pass");
		
	}
	else
	{
		Reporter.reportTestFailure(
				browser,"Sprint3_US15_TC2057_Condition2","Sprint3_US15_TC2057",
				"Error message should not display for valid inner pack value",
				"Fail");
		AbstractTest.takeSnapShot("Sprint3_US15_TC2057_Condition2");
	}
	
	
	
}

//TC2058 Verify that a user can enter only 5 digits before a decimal and 2 digits after the decimal in the loose column.

@Test()

public void Sprint3_US15_TC2058() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException 
{
	/*Start-Variable Deceleration*/
	HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint3_US15_TC2058", "Object1");
	String wrinId = ReadTestData.getTestData(manualInvoiceNewPageSheet, "WRINId");
	String invalidLooseUnit = "123456.551";
	String validLooseUnit="12345.55";
	String userId=GlobalVariable.userId;
	String storeId=GlobalVariable.StoreId;
	PhysicalInventoryPage physicalInventoryPage;
	String time;
	String date=Base.returnTodayDate();
	/*End*/
	
	HomePage homePage=PageFactory.initElements(driver, HomePage.class);
	// Navigate to physical inventory Page
	physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPhysicalInventoryPage();
	//Click on start New Inventory button
	physicalInventoryPage.StartNewInventory_BT.click();
	wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.StartInventory_BT));
	//Fetch the Time of Inventory
	time=physicalInventoryPage.SelectDateAndTimeForNewInventoryPopUp_Time_TB.getAttribute("value");
	System.out.println("Time is"+time);
	physicalInventoryPage.SelectDateAndTimeForNewInventoryPopUp_Cancel_BT.click();
	Thread.sleep(2000);
	physicalInventoryPage.BackToTop_BT.click();
	try
	{
		physicalInventoryPage.clickOnInProgressInventory(date, time);

	}
	catch(Exception e)
	{
		physicalInventoryPage.StartNewInventory_BT.click();
		physicalInventoryPage.StartInventory_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.CreateInventory_Search_TB));
	}
	
	/*while (Base.isElementDisplayed(physicalInventoryPage.AlreadyAnInventoryPopUp_ViewInventory_BT)) {
		Thread.sleep(180000);
		physicalInventoryPage.AlreadyAnInventoryPopUp_Cancel_BT.click();
		physicalInventoryPage.StartInventory_BT.click();

	}*/
	//Select the monthly from drop down
	physicalInventoryPage.selectInventoryType("Monthly");
	try{
		physicalInventoryPage.acceptTheAlertMessage();
		Thread.sleep(20000);
	}catch(Exception e){}
	physicalInventoryPage.CreateInventory_Search_TB.sendKeys(wrinId);
	Thread.sleep(2000);
	// enter the Loose Units value and verify that error message is displaying for invalid outer pack
	physicalInventoryPage.LooseCountQty_TB.sendKeys(invalidLooseUnit);
	if(driver.findElement(By.xpath("//div[@class='toast-message' and text()='Values must be numeric with up to 2 decimals. (Example: 12345.99)']")).isDisplayed())
	{
		Reporter.reportPassResult(
				browser,"Sprint3_US15_TC2058",
				"Error message should display for invalid loose unit value",
				"Pass");
		
	}
	else
	{
		Reporter.reportTestFailure(
				browser,"Sprint3_US15_TC2058_Condition1","Sprint3_US15_TC2057",
				"Error message should display for invalid loose unit value",
				"Fail");
		AbstractTest.takeSnapShot("Sprint3_US15_TC2057_Condition1");
	}
	
	Thread.sleep(5000);
	// enter the valid Loose Units value
	physicalInventoryPage.LooseCountQty_TB.clear();
	physicalInventoryPage.LooseCountQty_TB.sendKeys(validLooseUnit);
	
	if(driver.findElements(By.xpath("//div[@class='toast-message' and text()='Values must be numeric with up to 2 decimals. (Example: 12345.99)']")).size()==0)
	{
		Reporter.reportPassResult(
				browser,"Sprint3_US15_TC2058",
				"Error message should not display for valid loose units value",
				"Pass");
		
	}
	else
	{
		Reporter.reportTestFailure(
				browser,"Sprint3_US15_TC2058_Condition2","Sprint3_US15_TC2058",
				"Error message should not display for valid loose units value",
				"Fail");
		AbstractTest.takeSnapShot("Sprint3_US15_TC2058_Condition2");
	}
	
}




















































}
	
	
	
	
	
	
	
	
