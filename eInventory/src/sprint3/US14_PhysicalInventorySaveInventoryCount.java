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

import sprint2.AbstractTest;
import common.Base;
import common.GlobalVariable;
import common.ReadTestData;
import common.Reporter;
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.PhysicalInventoryPage;


public class US14_PhysicalInventorySaveInventoryCount extends AbstractTest
{
	
	//TC2004 Verify that the user is able to save total # of units of a raw-item in a physical inventory.
	
	@Test()
	
	public void Sprint3_US14_TC2004() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	
	{
		/*Start-Variable Deceleration*/
		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint3_US14_TC2004", "Object1");
		String wrinId = ReadTestData.getTestData(manualInvoiceNewPageSheet, "WRINId");
		String outerPack = ReadTestData.getTestData(manualInvoiceNewPageSheet, "OuterPackQty");
		String outerPackNew="5";
		String userId=GlobalVariable.userId;
		String storeId=GlobalVariable.StoreId;
		String totalUnitsBeforeChnage;
		String newTotalUnitsBeforeChnage;
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
		// enter the outer pack value
		physicalInventoryPage.OuterPackQty_TB.clear();
		physicalInventoryPage.OuterPackQty_TB.sendKeys(outerPack);
		//click on the inner pack text box
		physicalInventoryPage.LooseCountQty_TB.click();
		//Fetch the total units
		totalUnitsBeforeChnage=physicalInventoryPage.TotalUnits_Column_Value.getText().trim();
		//click on save button
		physicalInventoryPage.Save_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.InventorySaved_Confirmation_MSG));
		//Go to Physical Inventory page
		homePage.Menu_DD_BT.click();
		wait.until(ExpectedConditions.visibilityOf(homePage.InventoryManagement_BT));
		homePage.PhysicalInventory_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.PreviousInventoriesForStoreNumber_Title));
		//click on the created physical inventory
		physicalInventoryPage.clickOnInProgressInventory(date, time);
		//enter the new outer pack value
		physicalInventoryPage.OuterPackQty_TB.sendKeys(outerPackNew);
		//click on save button
		physicalInventoryPage.Save_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.InventorySaved_Confirmation_MSG));
		//Go to physical Inventory page
		homePage.Menu_DD_BT.click();
		wait.until(ExpectedConditions.visibilityOf(homePage.InventoryManagement_BT));
		homePage.PhysicalInventory_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.PreviousInventoriesForStoreNumber_Title));
		//click on the created physical inventory
		physicalInventoryPage.clickOnInProgressInventory(date, time);
		//Fetch the total units value
		newTotalUnitsBeforeChnage=physicalInventoryPage.TotalUnits_Column_Value.getText().trim();
		if(newTotalUnitsBeforeChnage.equalsIgnoreCase(totalUnitsBeforeChnage))
		{
			Reporter.reportTestFailure(
					browser,"Sprint3_US14_TC2004","Sprint3_US14_TC2004",
					"User should be able to change the outer pack value",
					"Fail");
			AbstractTest.takeSnapShot("Sprint3_US14_TC2004");
		}
		else
		{
			Reporter.reportPassResult(
					browser,"Sprint3_US14_TC2004",
					"User should be able to change the outer pack value",
					"Pass");
		}
		
	}
	
	
	//TC2005 Verify that the user cannot save invalid data in inventory.
	
	@Test()
	
	public void Sprint3_US14_TC2005() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	{
		
		/*Start-Variable Deceleration*/
		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint3_US14_TC2005", "Object1");
		String wrinId = ReadTestData.getTestData(manualInvoiceNewPageSheet, "WRINId");
		String outerPack = ReadTestData.getTestData(manualInvoiceNewPageSheet, "OuterPackQty");
		String outerPackNew="we";
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
		// enter the outer pack value
		physicalInventoryPage.OuterPackQty_TB.clear();
		physicalInventoryPage.OuterPackQty_TB.sendKeys(outerPack);
		//click on save button
		physicalInventoryPage.Save_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.InventorySaved_Confirmation_MSG));
		//Go to Physical Inventory page
		homePage.Menu_DD_BT.click();
		wait.until(ExpectedConditions.visibilityOf(homePage.InventoryManagement_BT));
		homePage.PhysicalInventory_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.PreviousInventoriesForStoreNumber_Title));
		//click on the created physical inventory
		physicalInventoryPage.clickOnInProgressInventory(date, time);
		//enter the new outer pack value
		physicalInventoryPage.OuterPackQty_TB.sendKeys(outerPackNew);
		//Verify that error message is displaying for invalid value
		if(driver.findElement(By.xpath("//div[@class='toast-message' and text()='Values must be numeric with up to 2 decimals. (Example: 12345.99)']")).isDisplayed())
		{
			Reporter.reportPassResult(
					browser,"Sprint3_US14_TC2005",
					"Error message shoudl display for invalid outer pack value",
					"Pass");
		}
		else
		{
			Reporter.reportTestFailure(
					browser,"Sprint3_US14_TC2005","Sprint3_US14_TC2005",
					"Error message shoudl display for invalid outer pack value",
					"Fail");
			AbstractTest.takeSnapShot("Sprint3_US14_TC2005");
		}
	
		
		
		
	}
	
//TC2006 Verify that the user is able to view previously saved inventory count.
	
	
	@Test()
	
	public void Sprint3_US14_TC2006() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	{
		
		/*Start-Variable Deceleration*/
		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint3_US14_TC2006", "Object1");
		String wrinId = ReadTestData.getTestData(manualInvoiceNewPageSheet, "WRINId");
		String outerPack = ReadTestData.getTestData(manualInvoiceNewPageSheet, "OuterPackQty");
		String userId=GlobalVariable.userId;
		String storeId=GlobalVariable.StoreId;
		String newTotalUnitsBeforeChnage;
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
		// enter the outer pack value
		physicalInventoryPage.OuterPackQty_TB.clear();
		physicalInventoryPage.OuterPackQty_TB.sendKeys(outerPack);
		//click on the inner pack text box
		physicalInventoryPage.LooseCountQty_TB.click();
		//click on save button
		physicalInventoryPage.Save_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.InventorySaved_Confirmation_MSG));
		//Go to Physical Inventory page
		homePage.Menu_DD_BT.click();
		wait.until(ExpectedConditions.visibilityOf(homePage.InventoryManagement_BT));
		homePage.PhysicalInventory_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.PreviousInventoriesForStoreNumber_Title));
		//click on the created physical inventory
		physicalInventoryPage.clickOnInProgressInventory(date, time);
		//Fetch the total units value
		newTotalUnitsBeforeChnage=physicalInventoryPage.TotalUnits_Column_Value.getText().trim();
		if(newTotalUnitsBeforeChnage.length()!=0)
		{
			Reporter.reportPassResult(
					browser,"Sprint3_US14_TC2006",
					"User should be able to view the total count",
					"Pass");
		}
		else
		{
			Reporter.reportTestFailure(
					browser,"Sprint3_US14_TC2006","Sprint3_US14_TC2006",
					"User should be able to view the total count",
					"Fail");
			AbstractTest.takeSnapShot("Sprint3_US14_TC2006");
		}
	}
	
	//TC2007 Verify that the user is able to view success message after saving an inventory successfully.
	
@Test()
	
	public void Sprint3_US14_TC2007() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	
	{
		/*Start-Variable Deceleration*/
		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint3_US14_TC2007", "Object1");
		String wrinId = ReadTestData.getTestData(manualInvoiceNewPageSheet, "WRINId");
		String outerPack = ReadTestData.getTestData(manualInvoiceNewPageSheet, "OuterPackQty");
		String outerPackNew="5";
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
		// enter the outer pack value
		physicalInventoryPage.OuterPackQty_TB.clear();
		physicalInventoryPage.OuterPackQty_TB.sendKeys(outerPack);
		//click on save button
		physicalInventoryPage.Save_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.InventorySaved_Confirmation_MSG));
		//Go to Physical Inventory page
		homePage.Menu_DD_BT.click();
		wait.until(ExpectedConditions.visibilityOf(homePage.InventoryManagement_BT));
		homePage.PhysicalInventory_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.PreviousInventoriesForStoreNumber_Title));
		//click on the created physical inventory
		physicalInventoryPage.clickOnInProgressInventory(date, time);
		//enter the new outer pack value
		physicalInventoryPage.OuterPackQty_TB.sendKeys(outerPackNew);
		//click on save button
		physicalInventoryPage.Save_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.InventorySaved_Confirmation_MSG));
		
		if(physicalInventoryPage.InventorySaved_Confirmation_MSG.isDisplayed())
		{
			Reporter.reportPassResult(
					browser,"Sprint3_US14_TC2007",
					"User should be able to change the outer pack value",
					"Pass");
			
		}
		else
		{
			Reporter.reportTestFailure(
					browser,"Sprint3_US14_TC2007","Sprint3_US14_TC2007",
					"User should be able to change the outer pack value",
					"Fail");
			AbstractTest.takeSnapShot("Sprint3_US14_TC2007");
		}
		
	}
	
	
	

}
