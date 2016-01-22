package sprint3;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
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

public class US87_AccessingPreviouslySavedInventoryCounts extends AbstractTest{
	
	// TC65::Verify that All the Previously saved inventories are listed by the Inventory Date and Time on Physical Inventory Application.
	@Test()
	public void sprint3_US87_TC65() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PhysicalInventoryPage physicalInventoryPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to physical inventory Page
		physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPhysicalInventoryPage();
		/*Verify fields : PreviousInventoriesForStoreNumber header, ClickRowToOpenInventory header, Date Column, time column, 
		 *created BY column and lat saved column displayed*/
		boolean result = Base.isElementDisplayed(physicalInventoryPage.PreviousInventoriesForStoreNumber_Title);
		result = result && Base.isElementDisplayed(physicalInventoryPage.ClickRowToOpenInventory_Label);
		result = result && Base.isElementDisplayed(physicalInventoryPage.InventoryTable_DateColumn_Label)
				&& Base.isElementDisplayed(physicalInventoryPage.InventoryTable_TimeColumn_Label)
				&& Base.isElementDisplayed(physicalInventoryPage.InventoryTable_CreatedByColumn_Label)
				&& Base.isElementDisplayed(physicalInventoryPage.InventoryTable_LastSavedColumn_Label);
		//Verify that start new inventory button is displayed
		result = result && Base.isElementDisplayed(physicalInventoryPage.StartNewInventory_BT);
		if (result) {
			Reporter.reportPassResult(
					browser,"sprint3_US87_TC65",
					"User should be able to view previously saved inventories are listed by the Inventory Date and Time on Physical Inventory Application",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint3_US87_TC65","sprint3_US87_TC65",
					"User should be able to view previously saved inventories are listed by the Inventory Date and Time on Physical Inventory Application",
					"Fail");
			AbstractTest.takeSnapShot("sprint3_US87_TC65");
		}
	}
	
	//TC66::Verify that User is able to select a Previously Saved Inventory count.
	@Test()
	public void sprint3_US87_TC66() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PhysicalInventoryPage physicalInventoryPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String inventoryType = "Monthly";
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to physical inventory Page >> Save a physical inventory
		physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement()
				.goToPhysicalInventoryPage().saveANewInventory(inventoryType);
		// Get the time for the inventory
		String time = physicalInventoryPage.InventoryTime_Label.getText().split("Time: ")[1];
		// Click on Menu Btn
		homePage.Menu_DD_BT.click();
		// Navigate to physical Inventory and click on the saved inventory with status "In-Progress".
		physicalInventoryPage.goToPhysicalInventoryPage().clickOnInProgressInventory(Base.returnTodayDate(), time);
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.SearchItem_TB));
		if (Base.isElementDisplayed(physicalInventoryPage.Save_BT)) {
			Reporter.reportPassResult(
					browser,"sprint3_US87_TC66",
					"User should be able to  select a Previously Saved Inventory count",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint3_US87_TC66","sprint3_US87_TC66",
					"User should be able to select a Previously Saved Inventory count",
					"Fail");
			AbstractTest.takeSnapShot("sprint3_US87_TC66");
		}
	}
	
	//TC67::Verify that User is able to view/edit the most current saved effective inventory counts every time.
	@Test()
	public void sprint3_US87_TC67() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PhysicalInventoryPage physicalInventoryPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet physicalInventoryPageSheet = ReadTestData.getTestDataSheet("sprint3_US87_TC67", "Object1");
		String wrinId = ReadTestData.getTestData(physicalInventoryPageSheet, "WRINId");
		String inventoryType = ReadTestData.getTestData(physicalInventoryPageSheet, "ListType");
		String outerPackQty = ReadTestData.getTestData(physicalInventoryPageSheet, "OuterPackQty");
		String looseCountQty = ReadTestData.getTestData(physicalInventoryPageSheet, "LooseUnitsQty");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to physical inventory Page
		physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPhysicalInventoryPage();
		//Click on start new inventory button
		physicalInventoryPage.StartNewInventory_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.StartInventory_BT));
		/*Verify fields :Date Picker(To select the Inventory Date) , Date Text Box(To display the Selected Inventory Date),
		 Time Picker(To select the Inventory Time) , Time Text Box (To display the Selected Inventory Time), Cancel button displayed*/
		boolean inventoryPopUpFieldsDisplayed = Base.isElementDisplayed(physicalInventoryPage.SelectDateAndTimeForNewInventoryPopUp_DatePicker)
				&& Base.isElementDisplayed(physicalInventoryPage.SelectDateAndTimeForNewInventoryPopUp_TimePicker)
				&& Base.isElementDisplayed(physicalInventoryPage.SelectDateAndTimeForNewInventoryPopUp_Date_TB)
				&& Base.isElementDisplayed(physicalInventoryPage.SelectDateAndTimeForNewInventoryPopUp_Time_TB)
				&& Base.isElementDisplayed(physicalInventoryPage.SelectDateAndTimeForNewInventoryPopUp_Cancel_BT);
		
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		// Get date as 2 days earlier date from today date
		cal1.add(Calendar.DATE, -2);
        String date = dateFormat.format(cal1.getTime());
        //select date from date picker
        physicalInventoryPage.selectADateForPhysicalInventory(date);
		//select the time stamp previous to current time stamp from time picker
		String time = physicalInventoryPage.selectTimeStampPriorToCurrentTimeStamp();
		//Start and save the new inventory
		physicalInventoryPage.StartInventory_BT.click();
		try {
			physicalInventoryPage.selectInventoryType(inventoryType);
			physicalInventoryPage.searchItemAndSaveInInventory(wrinId, outerPackQty, "",looseCountQty);
		} catch (Exception e) {
			physicalInventoryPage.EditInventory_BT.click();
			if (Base.isElementDisplayed(physicalInventoryPage.Save_BT)) {
				physicalInventoryPage.selectInventoryType(inventoryType);
				physicalInventoryPage.searchItemAndSaveInInventory(wrinId, outerPackQty,"",looseCountQty);
			}
		}
		// get last saved date and time from new inventory
		String lastSavedDateTime =  physicalInventoryPage.LastSavedAt_Label.getText();
		String newOuterPackQty = String.valueOf(Integer.parseInt(outerPackQty) + 1);
		// Click on Menu Btn
		homePage.Menu_DD_BT.click();
		// Navigate to physical Inventory verify that last saved date and time is displayed for the saved inventory
		boolean lastSavedDateTimeDisplayed = physicalInventoryPage.goToPhysicalInventoryPage().getLastSavedDateAndTimeForInProgressInventory(date, time).equals(lastSavedDateTime);
		//CLick on the saved inventory
		physicalInventoryPage.clickOnInProgressInventory(date, time);
		//Search for the raw item and update outer pack quantity and save the inventory
		 physicalInventoryPage.searchItemAndSaveInInventory(wrinId, newOuterPackQty, "",looseCountQty);
		//Click on the audit mark created for the updated raw item
		physicalInventoryPage.clickOnAuditMarkForARawItem(wrinId);
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.AuditDetailForRawItemPopUp_Headet_Label));
		Thread.sleep(2000);
		//Verify that before value and after value are displayed in audit table for the raw item
		boolean beforAfterValueDisplayedInAuditTable = physicalInventoryPage.getBeforeValueForRawItemInAuditTable(Base.returnTodayDate()).equals(outerPackQty)
				&& physicalInventoryPage.getAfterValueForRawItemInAuditTable(Base.returnTodayDate()).equals(newOuterPackQty);
		//Close the audit detail pop up
		physicalInventoryPage.AuditDetailForRawItemPopUp_Close_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.SearchItem_TB));
		// Click on Menu Btn
		homePage.Menu_DD_BT.click();
		// Navigate to physical Inventory and click on the saved inventory with status "In-Progress".
		physicalInventoryPage.goToPhysicalInventoryPage().clickOnInProgressInventory(date, time);
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.SearchItem_TB));
		Thread.sleep(2000);
		//Search the raw item
		physicalInventoryPage.SearchItem_TB.clear();
		physicalInventoryPage.SearchItem_TB.sendKeys(wrinId);
		//Verify  outer pack qty is updated for the raw item
		boolean outerPackQtyUpdated = physicalInventoryPage.OuterPackQty_TB.getAttribute("value").equals(newOuterPackQty);
		if (inventoryPopUpFieldsDisplayed && lastSavedDateTimeDisplayed 
				&& beforAfterValueDisplayedInAuditTable && outerPackQtyUpdated) {
			Reporter.reportPassResult(
					browser,"sprint3_US87_TC67",
					"User should be able to view/edit the most current saved effective inventory counts every time.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint3_US87_TC67","sprint3_US87_TC67",
					"User should be able to view/edit the most current saved effective inventory counts every time.",
					"Fail");
			AbstractTest.takeSnapShot("sprint3_US87_TC67");
		}
	}
	
	// TC68::Verify that the last 14 days Inventory counts form the current date are displayed in the list of Previously saved inventory counts.
	@Test()
	public void sprint3_US87_TC68() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PhysicalInventoryPage physicalInventoryPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet physicalInventoryPageSheet = ReadTestData.getTestDataSheet("sprint3_US87_TC67", "Object1");
		String inventoryType = ReadTestData.getTestData(physicalInventoryPageSheet, "ListType");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to physical inventory Page
		physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPhysicalInventoryPage();
		// Click on start new inventory button
		physicalInventoryPage.StartNewInventory_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.StartInventory_BT));
		
		 /* Verify fields :Date Picker(To select the Inventory Date) , Date Text Box(To display the Selected Inventory Date), 
		 * Time Picker(To select the Inventory Time) , Time Text Box (To display the Selected Inventory Time), Cancel button displayed*/
		 
		boolean inventoryPopUpFieldsDisplayed = Base
				.isElementDisplayed(physicalInventoryPage.SelectDateAndTimeForNewInventoryPopUp_DatePicker)
				&& Base.isElementDisplayed(physicalInventoryPage.SelectDateAndTimeForNewInventoryPopUp_TimePicker)
				&& Base.isElementDisplayed(physicalInventoryPage.SelectDateAndTimeForNewInventoryPopUp_Date_TB)
				&& Base.isElementDisplayed(physicalInventoryPage.SelectDateAndTimeForNewInventoryPopUp_Time_TB)
				&& Base.isElementDisplayed(physicalInventoryPage.SelectDateAndTimeForNewInventoryPopUp_Cancel_BT);
		// Get date as 3 days earlier date from today date
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		cal1.add(Calendar.DATE, -3);
		String date1 = dateFormat.format(cal1.getTime());
		// select date from date picker
		physicalInventoryPage.selectADateForPhysicalInventory(date1);
		// Start and save the new inventory
		physicalInventoryPage.StartInventory_BT.click();
		try {
			physicalInventoryPage.selectInventoryType(inventoryType);
			physicalInventoryPage.Save_BT.click();
			wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.InventorySaved_Confirmation_MSG));
			Thread.sleep(3000);
		} catch (Exception e) {
			physicalInventoryPage.EditInventory_BT.click();
			physicalInventoryPage.selectInventoryType(inventoryType);
			physicalInventoryPage.Save_BT.click();
			wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.InventorySaved_Confirmation_MSG));
			Thread.sleep(3000);
		}
		// Get the time for the inventory
		String time = physicalInventoryPage.InventoryTime_Label.getText().split("Time: ")[1];
		// Click on Menu Btn
		homePage.Menu_DD_BT.click();
		// Navigate to physical inventory page and Verify that physical inventory is saved
		boolean physicalInventorySaved = physicalInventoryPage.goToPhysicalInventoryPage().verifyInventorySaved(date1, time);
		System.out.println("physicalInventorySaved "+physicalInventorySaved);
		// Click on start new inventory button
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.StartNewInventory_BT));
		// Get date as 2 days earlier date from today date
		Calendar cal2 = Calendar.getInstance();
		cal2.add(Calendar.DATE, -2);
		String date2 = dateFormat.format(cal2.getTime());
		// Click on start new inventory button
		physicalInventoryPage.StartNewInventory_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.StartInventory_BT));
		// select date from date picker
		physicalInventoryPage.selectADateForPhysicalInventory(date2);
		// Start and save the new inventory
		physicalInventoryPage.StartInventory_BT.click();
		try {
			physicalInventoryPage.selectInventoryType(inventoryType);
			physicalInventoryPage.Save_BT.click();
			wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.InventorySaved_Confirmation_MSG));
			Thread.sleep(3000);
		} catch (Exception e) {
			physicalInventoryPage.EditInventory_BT.click();
			physicalInventoryPage.selectInventoryType(inventoryType);
			physicalInventoryPage.Save_BT.click();
			wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.InventorySaved_Confirmation_MSG));
			Thread.sleep(3000);
		}
		// Get the time for the inventory
		String time2 = physicalInventoryPage.InventoryTime_Label.getText().split("Time: ")[1];
		// Click on Menu Btn
		homePage.Menu_DD_BT.click();
		// Navigate to physical Inventory Page and Verify that physical inventory is saved
		physicalInventorySaved = physicalInventorySaved && physicalInventoryPage.goToPhysicalInventoryPage().verifyInventorySaved(date2, time2);
		System.out.println("physicalInventorySaved2 "+physicalInventorySaved);
		// Search the raw item
		Calendar cal3 = Calendar.getInstance();
		// Get date as 15 days earlier date from today date
		cal3.add(Calendar.DATE, -15);
		String date3 = dateFormat.format(cal3.getTime());
		//Get the month name from the 15 days earlier date
		String monthName = Base.getMonthName(Base.getMonthFromDate(date3)+1);
		System.out.println(monthName);
		// Click on start new inventory button
		physicalInventoryPage.StartNewInventory_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.StartInventory_BT));
		//Select the month for 14 days earlier date
		physicalInventoryPage.selectMonthForPhysicalInventory(monthName);
		//Verify that 14 days earlier date should be disabled
		System.out.println(physicalInventoryPage.getClassOfDateInCalender(date3));
		boolean inventoryCountNotDisplayedFor14DaysBeforeDate = physicalInventoryPage.getClassOfDateInCalender(date3).contains("disabled");
		System.out.println("inventoryCountNotDisplayedFor14DaysBeforeDate "+inventoryCountNotDisplayedFor14DaysBeforeDate);
		//Cancel the new inventory creation
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.SelectDateAndTimeForNewInventoryPopUp_Cancel_BT));
		physicalInventoryPage.SelectDateAndTimeForNewInventoryPopUp_Cancel_BT.click();
		//Verify that 15 days earlier records should not be displayed in inventory table
		inventoryCountNotDisplayedFor14DaysBeforeDate = inventoryCountNotDisplayedFor14DaysBeforeDate && (!physicalInventoryPage.verifyRecordForADateIsDisplayed(date3));
		System.out.println("inventoryCountNotDisplayedFor14DaysBeforeDate1 "+inventoryCountNotDisplayedFor14DaysBeforeDate);
		if ( inventoryPopUpFieldsDisplayed && physicalInventorySaved && inventoryCountNotDisplayedFor14DaysBeforeDate) {
			Reporter.reportPassResult(
					browser,"sprint3_US87_TC68",
					"User should be able to view the last 14 days Inventory counts form the current date in the list of Previously saved inventory counts.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint3_US87_TC68","sprint3_US87_TC68",
					"User should be able to view the last 14 days Inventory counts form the current date in the list of Previously saved inventory counts. ",
					"Fail");
			AbstractTest.takeSnapShot("sprint3_US87_TC68");
		}
	}

}
