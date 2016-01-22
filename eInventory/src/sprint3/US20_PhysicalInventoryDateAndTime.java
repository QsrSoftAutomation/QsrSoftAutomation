package sprint3;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import sprint2.AbstractTest;
import common.Base;
import common.GlobalVariable;
import common.Reporter;
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.PhysicalInventoryPage;

public class US20_PhysicalInventoryDateAndTime extends AbstractTest{
	
	//TC2068 :: Verify a screen or area (ex. dialog box) on the screen for the user to choose the Inventory Date and Time.
	@Test()
	public void sprint3_US20_TC2068() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PhysicalInventoryPage physicalInventoryPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to physical inventory Page
		physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPhysicalInventoryPage();
		// Click on start new inventory button
		physicalInventoryPage.StartNewInventory_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.StartInventory_BT));
		//Verify that user should be able to see a pop up to select date and time
		boolean inventoryPopUpFieldsDisplayed = Base.isElementDisplayed(physicalInventoryPage.SelectDateAndTimeForNewInventoryPopUp_DatePicker)
				&& Base.isElementDisplayed(physicalInventoryPage.SelectDateAndTimeForNewInventoryPopUp_TimePicker)
				&& Base.isElementDisplayed(physicalInventoryPage.SelectDateAndTimeForNewInventoryPopUp_Date_TB)
				&& Base.isElementDisplayed(physicalInventoryPage.SelectDateAndTimeForNewInventoryPopUp_Time_TB);
		if (inventoryPopUpFieldsDisplayed) {
			Reporter.reportPassResult(
					browser,"sprint3_US20_TC2068",
					"User should be able to see a pop up screen asking to choose date and time.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint3_US20_TC2068","sprint3_US20_TC2068",
					"User should be able to see a pop up screen asking to choose date and time.",
					"Fail");
			AbstractTest.takeSnapShot("sprint3_US20_TC2068");
		}
	}
	
	//TC2069 :: Verify the current date should show as a default in the format MM/DD/YYYY.
	@Test()
	public void sprint3_US20_TC2069() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PhysicalInventoryPage physicalInventoryPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to physical inventory Page
		physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPhysicalInventoryPage();
		// Click on start new inventory button
		physicalInventoryPage.StartNewInventory_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.StartInventory_BT));
		// Verify that user should be able to see current date as default selected date in select date time pop up
		String todayDateClass = physicalInventoryPage.getClassOfDateInCalender(Base.returnTodayDate());
		if (todayDateClass.contains("xdsoft_current")) {
			Reporter.reportPassResult(
					browser,"sprint3_US20_TC2069",
					"User should be able to see current date as a default in select date time pop up",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint3_US20_TC2069","sprint3_US20_TC2069",
					"User should be able to see current date as a default in select date time pop up",
					"Fail");
			AbstractTest.takeSnapShot("sprint3_US20_TC2069");
		}
	}
	
	//TC2070 ::Verify,"the current time should show as a default to the nearest 15 minute increment".
	@Test()
	public void sprint3_US20_TC2070() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PhysicalInventoryPage physicalInventoryPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to physical inventory Page
		physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPhysicalInventoryPage();
		// Click on start new inventory button
		physicalInventoryPage.StartNewInventory_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.StartInventory_BT));
		//get current time from system
		String currentTime = new SimpleDateFormat("HH:mm").format(new Date());
		String selectedTime = physicalInventoryPage.SelectDateAndTimeForNewInventoryPopUp_SelectedTime.getText();
		//Verify that default selected time should be shown as nearest 15 minute increment to current time
		boolean result = true;
		String hour = currentTime.split(":")[0];
		int minutes = Integer.parseInt(currentTime.split(":")[1]);
		System.out.println(" minutes "+ minutes);
		if(minutes >= 0 && minutes <15){
			result =  result && selectedTime.equals(hour+":"+"00");
		}else if(minutes >= 15 && minutes <30){
			result =  result && selectedTime.equals(hour+":"+"15");
		}else if(minutes >= 30 && minutes <45){
			result =  result && selectedTime.equals(hour+":"+"30");
		}else if(minutes >= 45 && minutes <=59){
			result =  result && selectedTime.equals(hour+":"+"45");
		}
		if (result) {
			Reporter.reportPassResult(
					browser,"sprint3_US20_TC2070",
					"User should be able to see current date should show as a default in select date time pop up",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint3_US20_TC2070","sprint3_US20_TC2070",
					"User should be able to see current date should show as a default in select date time pop up",
					"Fail");
			AbstractTest.takeSnapShot("sprint3_US20_TC2070");
		}
	}
	
	// TC2071 ::Verify,"the time will be select-able in 15 minute increments on the :00, :15, :30, and :45 of each hour".
	@Test()
	public void sprint3_US20_TC2071() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PhysicalInventoryPage physicalInventoryPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to physical inventory Page
		physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPhysicalInventoryPage();
		// Click on start new inventory button
		physicalInventoryPage.StartNewInventory_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.StartInventory_BT));
		//Verify that the time will be select-able in 15 minute increments on the :00, :15, :30, and :45 of each hour
		if (physicalInventoryPage.verifyTimeDisplayedIn15MinteTimeSpan()) {
			Reporter.reportPassResult(
					browser,"sprint3_US20_TC2071",
					"User should be able to select time in 15 minute increments on the :00, :15, :30, and :45 of each hour",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint3_US20_TC2071","sprint3_US20_TC2071",
					"User should be able to select time in 15 minute increments on the :00, :15, :30, and :45 of each hour",
					"Fail");
			AbstractTest.takeSnapShot("sprint3_US20_TC2071");
		}
	}
	
	// TC2072 ::Verify,"If I enter a date and time that has already been saved, there must be something that prompts the user a date and time has been previously entered".
	@Test()
	public void sprint3_US20_TC2072() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PhysicalInventoryPage physicalInventoryPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String inventoryType = "Daily";
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to physical inventory Page
		physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPhysicalInventoryPage();
		String inventoryTime;
		//Get Current time
		String currentTime = Base.getCurrentTimeStamp();
		//If inventory is already saved for current date and time stamp than do not save a new inventory
		if(physicalInventoryPage.verifyInventorySaved(Base.returnTodayDate(), currentTime))
		{
			inventoryTime = currentTime;
		}//else save a new inventory and get the current date time and navigate back to physical inventory page
		else{
			physicalInventoryPage.saveANewInventory(inventoryType);
			inventoryTime = physicalInventoryPage.InventoryTime_Label.getText().split("Time: ")[1];
			// Click on Menu Btn
			homePage.Menu_DD_BT.click();
			// Navigate to physical Inventory and click on the saved inventory with status "In-Progress".
			physicalInventoryPage.goToPhysicalInventoryPage();
		}
		//click
		physicalInventoryPage.StartNewInventory_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.StartInventory_BT));
		physicalInventoryPage.selectTimeForPhysicalInventory(inventoryTime);
		physicalInventoryPage.StartInventory_BT.click();
		if (Base.isElementDisplayed(physicalInventoryPage.EditInventory_BT)) {
			Reporter.reportPassResult(
					browser,"sprint3_US20_TC2072",
					"User should be able to view pop up that prompts the user a date and time has been previously entered",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint3_US20_TC2072","sprint3_US20_TC2072",
					"User should be able to view pop up that prompts the user a date and time has been previously entered",
					"Fail");
			AbstractTest.takeSnapShot("sprint3_US20_TC2072");
		}
	}
	
	// TC2073 ::Verify, "I should not be able to enter a future date and time as it is impossible to do a future inventory count".
	@Test()
	public void sprint3_US20_TC2073() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PhysicalInventoryPage physicalInventoryPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to physical inventory Page
		physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPhysicalInventoryPage();
		physicalInventoryPage.StartNewInventory_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.StartInventory_BT));
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		cal1.add(Calendar.DATE, +1);
		String date = dateFormat.format(cal1.getTime());
		physicalInventoryPage.getClassOfDateInCalender(date);
		String currentTimeStamp = Base.getCurrentTimeStamp();
		String nextTimeStamp = Base.getHourFromTime(currentTimeStamp) + ":" + (Base.getMinuteFromTime(currentTimeStamp)+15);
		if (physicalInventoryPage.getClassOfDateInCalender(date).contains("xdsoft_disabled") 
				&& physicalInventoryPage.getClassOfTimeStamp(nextTimeStamp).contains("xdsoft_disabled")) {
			Reporter.reportPassResult(
					browser,"sprint3_US20_TC2073",
					"User should not be able to select future date or future time",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint3_US20_TC2073","sprint3_US20_TC2073",
					"User should not be able to select future date or future time",
					"Fail");
			AbstractTest.takeSnapShot("sprint3_US20_TC2073");
		}
	}
	
	// TC2074 ::Verify, "As a user I will be allowed to enter an inventory that is 14 days in the past.
	@Test()
	public void sprint3_US20_TC2074() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PhysicalInventoryPage physicalInventoryPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to physical inventory Page
		physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPhysicalInventoryPage();
		//Click on start new inventory button
		physicalInventoryPage.StartNewInventory_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.StartInventory_BT));
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		//Get 14 days earlier date
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.DATE, -13);
		String date1 = dateFormat.format(cal1.getTime());
		//Get the month name from the 14 days earlier date
		String monthName1 = Base.getMonthName(Base.getMonthFromDate(date1)+1);
		//Select the month for 14 days earlier date
		physicalInventoryPage.selectMonthForPhysicalInventory(monthName1);
		//Get the class attribute of  14 days earlier date
		String date1Class = physicalInventoryPage.getClassOfDateInCalender(date1);
		//Get 15 days earlier date
		Calendar cal2 = Calendar.getInstance();
		cal2.add(Calendar.DATE, -14);
		String date2 = dateFormat.format(cal2.getTime());
		//Get the month name from the 14 days earlier date
		String monthName2 = Base.getMonthName(Base.getMonthFromDate(date2)+1);
		//Select the month for 14 days earlier date
		physicalInventoryPage.selectMonthForPhysicalInventory(monthName2);
		//Get the class attribute of  14 days earlier date
		String date2Class = physicalInventoryPage.getClassOfDateInCalender(date2);
		if (!date1Class.contains("xdsoft_disabled") && date2Class.contains("xdsoft_disabled")) {
			Reporter.reportPassResult(
					browser,"sprint3_US20_TC2074",
					"User should not be able to select up to 14 days earlier date and should not be able to select date which does not fall within earlier 14 days.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint3_US20_TC2074","sprint3_US20_TC2074",
					"User should not be able to select up to 14 days earlier date and should not be able to select date which does not fall within earlier 14 days.",
					"Fail");
			AbstractTest.takeSnapShot("sprint3_US20_TC2074");
		}
	}
	
	// TC2075 ::Verify,"a user has a method to choose the date and time of the inventory count".
	@Test()
	public void sprint3_US20_TC2075() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PhysicalInventoryPage physicalInventoryPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String inventoryType = "Daily";
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to physical inventory Page>>Save a new inventory
		physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement()
				.goToPhysicalInventoryPage().saveANewInventory(inventoryType);
		// Get the time for the inventory
		String time = physicalInventoryPage.InventoryTime_Label.getText().split("Time: ")[1];
		// Click on Menu Btn
		homePage.Menu_DD_BT.click();
		// Navigate to physical Inventory Page and Verify that physical inventory is saved
		if (physicalInventoryPage.goToPhysicalInventoryPage().verifyInventorySaved(Base.returnTodayDate(), time)) {
			Reporter.reportPassResult(
					browser,"sprint3_US20_TC2075",
					"User should be able to create an invnetory.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint3_US20_TC2075","sprint3_US20_TC2075",
					"User should be able to create an invnetory.",
					"Fail");
			AbstractTest.takeSnapShot("sprint3_US20_TC2075");
		}
	}

}
