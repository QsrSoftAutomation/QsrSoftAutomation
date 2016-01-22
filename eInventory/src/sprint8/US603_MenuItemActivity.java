package sprint8;

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

import common.Base;
import common.GlobalVariable;
import common.ReadTestData;
import common.Reporter;
import sprint2.AbstractTest;
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.MenuItemActivityPage;

public class US603_MenuItemActivity extends AbstractTest {

	//TC1596:verify that a user should be able to search and select a menu item from the search box provided on Menu Item Activity landing page.
	@Test()
	public void sprint8_US603_TC1596() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/**Variable Section :**/
		MenuItemActivityPage menuItemActivityPage;
		String storeId = GlobalVariable.StoreId;
		String userId=GlobalVariable.userId;
		HSSFSheet menuItemActivityPageSheet = ReadTestData.getTestDataSheet("sprint8_US603_TC1596", "Object1");
		String menuItemNumber = ReadTestData.getTestData(menuItemActivityPageSheet, "MenuItemNumber");
		String menuItemDescription = ReadTestData.getTestData(menuItemActivityPageSheet, "MenuItemDescription");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		//Navigate to menu item activity page 
		menuItemActivityPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToMenuItemActivityPage();
		//Search and select menu Item using menu Item Number = 1
		menuItemActivityPage.searchAndSelectMenuItem(menuItemNumber);
		wait.until(ExpectedConditions.visibilityOf(menuItemActivityPage.MiaStartDate_BT));
		menuItemActivityPage.ShowResults_BT.click();
		wait.until(ExpectedConditions.visibilityOf(menuItemActivityPage.SelectItem_Value));
		//Verify that user is able to search the menu item using menu item number=1
		if(menuItemActivityPage.SelectItem_Value.getText().contains(menuItemNumber +" - "+menuItemDescription)){
			Reporter.reportPassResult(
					browser,"sprint8_US603_TC1596",
					"User should be able to search and select the menu item by typing menu item number=1",
					"Pass");

		} else {
			Reporter.reportTestFailure(
					browser,"sprint8_US603_TC1596","sprint8_US603_TC1596",
					"User should be able to search and select the menu item by typing menu item number=1",
					"Fail");
			AbstractTest.takeSnapShot("sprint8_US603_TC1596_condition_1");
			}
		//Search and select menu Item using menu Item Description = Hamburger
		menuItemActivityPage.Clear_BT.click();
		menuItemActivityPage.searchAndSelectMenuItem(menuItemDescription);
		wait.until(ExpectedConditions.visibilityOf(menuItemActivityPage.MiaStartDate_BT));
		menuItemActivityPage.ShowResults_BT.click();
		wait.until(ExpectedConditions.visibilityOf(menuItemActivityPage.SelectItem_Value));
		//Verify that user is able to search the menu item using menu item Description = Hamburger
		if(menuItemActivityPage.SelectItem_Value.getText().contains(menuItemNumber +" - "+menuItemDescription)){
			Reporter.reportPassResult(
					browser,"sprint8_US603_TC1596",
					"User should be able to search and select the menu item by typing menu item description=Hamburger",
					"Pass");

		} else {
			Reporter.reportTestFailure(
					browser,"sprint8_US603_TC1596","sprint8_US603_TC1596",
					"User should be able to search and select the menu item by typing menu item description=Hamburger",
					"Fail");
			AbstractTest.takeSnapShot("sprint8_US603_TC1596_condition_2");
			}
	}
	
	//TC1599:verify that a Verify that the user should be able to select the date range for the menu item selected on Menu Item Activity page.
	@Test()
	public void sprint8_US603_TC1599() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/**Variable Section :**/
		String storeId = GlobalVariable.StoreId;
		String userId=GlobalVariable.userId;
		MenuItemActivityPage menuItemActivityPage;
		HSSFSheet menuItemActivityPageSheet = ReadTestData.getTestDataSheet("sprint8_US603_TC1599", "Object1");
		String menuItemNumber = ReadTestData.getTestData(menuItemActivityPageSheet, "MenuItemNumber");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		//Navigate to menu item activity page 
		menuItemActivityPage= homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToMenuItemActivityPage();
		//Search and select menu Item using menu Item Number = 1
		menuItemActivityPage.searchAndSelectMenuItem(menuItemNumber);
		wait.until(ExpectedConditions.visibilityOf(menuItemActivityPage.MiaStartDate_BT));
		String todayDate = Base.returnTodayDate();
		//click on the start date button
		wait.until(ExpectedConditions.visibilityOf(menuItemActivityPage.MiaStartDate_BT));
		menuItemActivityPage.MiaStartDate_TB.click();
		//verify that by default today date is selected
		boolean defaultTodayDateSelected = menuItemActivityPage.verifyDateIsSelectedForStartDate(todayDate);
		//click on the end date button
		menuItemActivityPage.MiaEndDate_TB.click();
		//Verify that by default today date is selected
		defaultTodayDateSelected = defaultTodayDateSelected && menuItemActivityPage.verifyDateIsSelectedForEndDate(todayDate);
		if(defaultTodayDateSelected){
			Reporter.reportPassResult(
					browser,"sprint8_US603_TC1599",
					"User should be able to view the start date and end date as default to current date",
					"Pass");

		} else {
			Reporter.reportTestFailure(
					browser,"sprint8_US603_TC1599","sprint8_US603_TC1599",
					"User should be able to view the start date and end date as default to current date",
					"Fail");
			AbstractTest.takeSnapShot("sprint8_US603_TC1599_condition_1");
			}
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		// Get start date as 5 days earlier date from today date
		cal1.add(Calendar.DATE, -5);
        String fromDate = dateFormat.format(cal1.getTime());
        //Get end date as 1 day earlier date from today date
        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DATE, -1);
        String toDate = dateFormat.format(cal2.getTime());
        //enter past date in start date 
        menuItemActivityPage.enterDateInMenuItemStartDate(fromDate);
        //Verify that user is able to select past date as start date
        menuItemActivityPage.MiaStartDate_BT.click();
        boolean pastDateSelected = menuItemActivityPage.verifyDateIsSelectedForStartDate(fromDate);	
        //enter past date in end date 
		menuItemActivityPage.enterDateInMenuItemEndDate(toDate);
		//Verify that user is able to select past date as end date
		menuItemActivityPage.MiaEndDate_BT.click();
		pastDateSelected = pastDateSelected && menuItemActivityPage.verifyDateIsSelectedForEndDate(toDate);
		// Get start date as 1 day later date from today date
		Calendar cal3 = Calendar.getInstance();
		cal3.add(Calendar.DATE, +1);
		String tomorrowDate = dateFormat.format(cal3.getTime());
		//click on the start date button
		menuItemActivityPage.MiaStartDate_TB.click();
		//Verify that user is not able to select future date as start date ;it will be disabled
		boolean futureDatedisabled =menuItemActivityPage.verifyFutureDateIsDisabledForStartDate(tomorrowDate);
		//click on the end date button
		menuItemActivityPage.MiaEndDate_TB.click();
		//Verify that user is not able to select future date as end date ;it will be disabled
		futureDatedisabled = futureDatedisabled && menuItemActivityPage.verifyFutureDateIsDisabledForEndDate(tomorrowDate);
		if(pastDateSelected && futureDatedisabled){
			Reporter.reportPassResult(
					browser,"sprint8_US603_TC1599",
					"User should be able to select current or past dates only. Future date can not be selected.",
					"Pass");

		} else {
			Reporter.reportTestFailure(
					browser,"sprint8_US603_TC1599","sprint8_US603_TC1599",
					"User should be able to select current or past dates only. Future date can not be selected.",
					"Fail");
			AbstractTest.takeSnapShot("sprint8_US603_TC1599_condition_2");
			}
	}
	
	// TC1605:Verify that the user should be able to select a time period for each date in the date range for the menu item selected.
	@Test()
	public void sprint8_US603_TC1605() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/**Variable Section :**/
		MenuItemActivityPage menuItemActivityPage;
		String storeId = GlobalVariable.StoreId;
		String userId=GlobalVariable.userId;
		HSSFSheet menuItemActivityPageSheet = ReadTestData.getTestDataSheet("sprint8_US603_TC1605", "Object1");
		String menuItemNumber = ReadTestData.getTestData(menuItemActivityPageSheet, "MenuItemNumber");
		String defaultStartTime = ReadTestData.getTestData(menuItemActivityPageSheet, "DefaultStartTime");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to menu item activity page
		menuItemActivityPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToMenuItemActivityPage();
		// Search and select menu Item using menu Item Number = 1
		menuItemActivityPage.searchAndSelectMenuItem(menuItemNumber);
		wait.until(ExpectedConditions.visibilityOf(menuItemActivityPage.MiaStartDate_BT));
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		//Get start date and end as 5 days earlier date from today date
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.DATE, -5);
        String fromDate = dateFormat.format(cal1.getTime());
        String toDate = dateFormat.format(cal1.getTime());
        //get current date and time
        Calendar cal3 = Calendar.getInstance();
        cal3.add(Calendar.DATE, 0);
        dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:MM");
        String currentTime = dateFormat.format(cal3.getTime()).split(" ")[1];
        String currentHour = currentTime.split(":")[0];
        //enter start date 
        menuItemActivityPage.enterDateInMenuItemStartDate(fromDate);
       //enter end date 
        menuItemActivityPage.enterDateInMenuItemEndDate(toDate);
        //Click on start time button
        menuItemActivityPage.StartTime_TB.click();
        Thread.sleep(1000);
        // verify that start time is displayed as 00:00
        boolean defaultTimeDisplayed = menuItemActivityPage.StartTime_Value.getText().equals(defaultStartTime);
        //click on end time button
        menuItemActivityPage.EndTime_TB.click();
        Thread.sleep(1000);
        // verify that end time is displayed as current hour
        defaultTimeDisplayed = defaultTimeDisplayed && menuItemActivityPage.EndTime_Value.getText().equals(currentHour+":00");
        if(defaultTimeDisplayed){
			Reporter.reportPassResult(
					browser,"sprint8_US603_TC1605",
					"User should be able to view the start time as 12 am and end time as current hour",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint8_US603_TC1605","sprint8_US603_TC1605",
					"User should be able to view the start time as 12 am and end time as current hour",
					"Fail");
			AbstractTest.takeSnapShot("sprint8_US603_TC1605");
			}
	}

	// TC1606:Verify that the Menu Item Activity will only display up to the current time if the current date is part of the date range selected.
	@Test()
	public void sprint8_US603_TC1606() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/**Variable Section :**/
		MenuItemActivityPage menuItemActivityPage;
		String storeId = GlobalVariable.StoreId;
		String userId=GlobalVariable.userId;
		HSSFSheet menuItemActivityPageSheet = ReadTestData.getTestDataSheet("sprint8_US603_TC1606", "Object1");
		String menuItemNumber = ReadTestData.getTestData(menuItemActivityPageSheet, "MenuItemNumber");
		String defaultStartTime = ReadTestData.getTestData(menuItemActivityPageSheet, "DefaultStartTime");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to menu item activity page
		menuItemActivityPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToMenuItemActivityPage();
		// Search and select menu Item using menu Item Number = 1
		menuItemActivityPage.searchAndSelectMenuItem(menuItemNumber);
		wait.until(ExpectedConditions.visibilityOf(menuItemActivityPage.MiaStartDate_BT));
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		// get current date and time
		Calendar cal3 = Calendar.getInstance();
		cal3.add(Calendar.DATE, 0);
		dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:MM");
		String currentDate = dateFormat.format(cal3.getTime()).split(" ")[0];
		String currentTime = dateFormat.format(cal3.getTime()).split(" ")[1];
		String currentHour = currentTime.split(":")[0]+":00";
		// enter start date
		menuItemActivityPage.enterDateInMenuItemStartDate(currentDate);
		// enter end date
		menuItemActivityPage.enterDateInMenuItemEndDate(currentDate);
		// verify that start time is displayed as 00:00
		menuItemActivityPage.selectStartTime(defaultStartTime);
		// verify that end time is displayed as current hour
		menuItemActivityPage.selectEndTime(currentHour);
		menuItemActivityPage.ShowResults_BT.click();
		wait.until(ExpectedConditions.visibilityOf(menuItemActivityPage.SelectItem_Value));
		boolean menuItemActivityDisplayed = menuItemActivityPage.verifyMenuActivityTimeForSelectedDateRange(currentDate, currentDate, defaultStartTime, currentHour);
		if (menuItemActivityDisplayed) {
			Reporter.reportPassResult(
					browser,"sprint8_US603_TC1606",
					"User should be able to view the menu item activity up to  current time for the current date.",
					"Pass");

		} else {
			Reporter.reportTestFailure(
					browser,"sprint8_US603_TC1606","sprint8_US603_TC1606",
					"User should be able to view the menu item activity up to  current time for the current date.",
					"Fail");
			AbstractTest.takeSnapShot("sprint8_US603_TC1606");
		}
	}
	
	//TC1607:Verify that the user should be able to view the date information in greater detail for every 15 minute time slice for each date and the time period selected.
	@Test()
	public void sprint8_US603_TC1607() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/**Variable Section :**/
		MenuItemActivityPage menuItemActivityPage;
		String storeId = GlobalVariable.StoreId;
		String userId=GlobalVariable.userId;
		HSSFSheet menuItemActivityPageSheet = ReadTestData.getTestDataSheet("sprint8_US603_TC1607", "Object1");
		String menuItemNumber = ReadTestData.getTestData(menuItemActivityPageSheet, "MenuItemNumber");
		String startTime = ReadTestData.getTestData(menuItemActivityPageSheet, "CustomStartTime");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to menu item activity page
		menuItemActivityPage =homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToMenuItemActivityPage();
		// Search and select menu Item using menu Item Number = 1
		menuItemActivityPage.searchAndSelectMenuItem(menuItemNumber);
		wait.until(ExpectedConditions.visibilityOf(menuItemActivityPage.MiaStartDate_BT));
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		// get yesterday date and time
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.DATE, -1);
		String yesterdayDate = dateFormat.format(cal1.getTime());
		//Get current hour
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH");
		Calendar cal2 = Calendar.getInstance();
		cal2.add(Calendar.DATE, 0);
		String currentHour = dateFormat2.format(cal2.getTime());
		//Get end time as 2 hour later from current time
		String endTime = String.valueOf(Integer.parseInt(currentHour)+2)+":00";
		// enter start date
		menuItemActivityPage.enterDateInMenuItemStartDate(yesterdayDate);
		// enter end date
		menuItemActivityPage.enterDateInMenuItemEndDate(yesterdayDate);
		// verify that start time is displayed as 00:00
		menuItemActivityPage.selectStartTime(startTime);
		// verify that end time is displayed as 2 hour later from current time
		menuItemActivityPage.selectEndTime(endTime);
		menuItemActivityPage.ShowResults_BT.click();
		wait.until(ExpectedConditions.visibilityOf(menuItemActivityPage.SelectItem_Value));
		menuItemActivityPage.expandDateGroup();
		boolean recordsWithTimeSpanDisplayed = menuItemActivityPage.verifyMenuActivityTimeInDetailForSelectedDate(yesterdayDate, startTime, endTime);
		if (recordsWithTimeSpanDisplayed) {
			Reporter.reportPassResult(
					browser,"sprint8_US603_TC1607",
					"User should be able to view the date information in greater detail for every 15 minute time slice for each date and the time period selected.",
					"Pass");

		} else {
			Reporter.reportTestFailure(
					browser,"sprint8_US603_TC1607","sprint8_US603_TC1607",
					"User should be able to view the date information in greater detail for every 15 minute time slice for each date and the time period selected.",
					"Fail");
			AbstractTest.takeSnapShot("sprint8_US603_TC1606");
		}
	}
	
	// TC1624:Verify that the user should be able to view Total Menu Items Sold rolled up for the time periods for the selected date range.
	@Test()
	public void sprint8_US603_TC1624() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/**Variable Section :**/
		MenuItemActivityPage menuItemActivityPage;
		String storeId = GlobalVariable.StoreId;
		String userId=GlobalVariable.userId;
		HSSFSheet menuItemActivityPageSheet = ReadTestData.getTestDataSheet("sprint8_US603_TC1624", "Object1");
		String menuItemNumber = ReadTestData.getTestData(menuItemActivityPageSheet, "MenuItemNumber");
		String startTime = ReadTestData.getTestData(menuItemActivityPageSheet, "CustomStartTime");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to menu item activity page
		menuItemActivityPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToMenuItemActivityPage();
		// Search and select menu Item using menu Item Number = 1
		menuItemActivityPage.searchAndSelectMenuItem(menuItemNumber);
		wait.until(ExpectedConditions.visibilityOf(menuItemActivityPage.MiaStartDate_BT));
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		// get Start date as 5days earlier date
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.DATE, -5);
		String startDate = dateFormat.format(cal1.getTime());
		//Get end Date as yesterday date
		Calendar cal2 = Calendar.getInstance();
		cal2.add(Calendar.DATE, -1);
		String endDate = dateFormat.format(cal2.getTime());	
		//Get current hour
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH");
		Calendar cal3 = Calendar.getInstance();
		cal3.add(Calendar.DATE, 0);
		String currentHour = dateFormat2.format(cal3.getTime());
		//Get end time as 2 hour later from current time
		String endTime = String.valueOf(Integer.parseInt(currentHour)+2)+":00";
		// enter start date
		menuItemActivityPage.enterDateInMenuItemStartDate(startDate);
		// enter end date
		menuItemActivityPage.enterDateInMenuItemEndDate(endDate);
		// Enter start time
		menuItemActivityPage.selectStartTime(startTime);
		// Enter end time
		menuItemActivityPage.selectEndTime(endTime);
		//Click on search button
		menuItemActivityPage.ShowResults_BT.click();
		wait.until(ExpectedConditions.visibilityOf(menuItemActivityPage.SelectItem_Value));
		//Verify that sold value for menu item should be displayed for selected date range
		boolean soldValueForMenuItemDisplayed = menuItemActivityPage.verifySoldValueForMenuItemDisplayed(startDate,endDate);
		if (soldValueForMenuItemDisplayed) {
			Reporter.reportPassResult(
					browser,"sprint8_US603_TC1624",
					"User should be able to view Total Menu Items Sold rolled up for the time periods for the selected date range",
					"Pass");

		} else {
			Reporter.reportTestFailure(
					browser,"sprint8_US603_TC1624","sprint8_US603_TC1624",
					"User should be able to view Total Menu Items Sold rolled up for the time periods for the selected date range",
					"Fail");
			AbstractTest.takeSnapShot("sprint8_US603_TC1624");
		}
	}
	
	// TC1632:Verify that the user should be able to view Total Menu Items Wasted (rolled up for the time periods for the selected date range).
	@Test()
	public void sprint8_US603_TC1632() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/**Variable Section :**/
		MenuItemActivityPage menuItemActivityPage;
		String storeId = GlobalVariable.StoreId;
		String userId=GlobalVariable.userId;
		HSSFSheet menuItemActivityPageSheet = ReadTestData.getTestDataSheet("sprint8_US603_TC1632", "Object1");
		String menuItemNumber = ReadTestData.getTestData(menuItemActivityPageSheet, "MenuItemNumber");
		String startTime = ReadTestData.getTestData(menuItemActivityPageSheet, "CustomStartTime");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to menu item activity page
		menuItemActivityPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToMenuItemActivityPage();
		// Search and select menu Item using menu Item Number = 1
		menuItemActivityPage.searchAndSelectMenuItem(menuItemNumber);
		wait.until(ExpectedConditions.visibilityOf(menuItemActivityPage.MiaStartDate_BT));
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		// get Start date as 5days earlier date
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.DATE, -5);
		String startDate = dateFormat.format(cal1.getTime());
		// Get end Date as yesterday date
		Calendar cal2 = Calendar.getInstance();
		cal2.add(Calendar.DATE, -1);
		String endDate = dateFormat.format(cal2.getTime());
		// Get current hour
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH");
		Calendar cal3 = Calendar.getInstance();
		cal3.add(Calendar.DATE, 0);
		String currentHour = dateFormat2.format(cal3.getTime());
		// Get end time as 2 hour later from current time
		String endTime = String.valueOf(Integer.parseInt(currentHour) + 2)+ ":00";
		// enter start date
		menuItemActivityPage.enterDateInMenuItemStartDate(startDate);
		// enter end date
		menuItemActivityPage.enterDateInMenuItemEndDate(endDate);
		// enter start time
		menuItemActivityPage.selectStartTime(startTime);
		// enter end time
		menuItemActivityPage.selectEndTime(endTime);
		// Click on search button
		menuItemActivityPage.ShowResults_BT.click();
		wait.until(ExpectedConditions.visibilityOf(menuItemActivityPage.SelectItem_Value));
		// Verify that Waste value for menu item should be displayed for selected date range
		boolean wasteValueForMenuItemDisplayed = menuItemActivityPage.verifyWasteValueForMenuItemDisplayed(startDate, endDate);
		if (wasteValueForMenuItemDisplayed) {
			Reporter.reportPassResult(
					browser,"sprint8_US603_TC1632",
					"User should be able to view Total Menu Items Wasted (rolled up for the time periods for the selected date range)",
					"Pass");

		} else {
			Reporter.reportTestFailure(
					browser,"sprint8_US603_TC1632","sprint8_US603_TC1632",
					"User should be able to view Total Menu Items Wasted (rolled up for the time periods for the selected date range)",
					"Fail");
			AbstractTest.takeSnapShot("sprint8_US603_TC1632");
		}
	}

	// TC1634:Verify that the user should be able to view Total Menu Items Promo'd (rolled up for the time periods for the selected date range).
	@Test()
	public void sprint8_US603_TC1634() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/**Variable Section :**/
		MenuItemActivityPage menuItemActivityPage;
		String storeId = GlobalVariable.StoreId;
		String userId=GlobalVariable.userId;
		HSSFSheet menuItemActivityPageSheet = ReadTestData.getTestDataSheet("sprint8_US603_TC1634", "Object1");
		String menuItemNumber = ReadTestData.getTestData(menuItemActivityPageSheet, "MenuItemNumber");
		String startTime = ReadTestData.getTestData(menuItemActivityPageSheet, "CustomStartTime");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to menu item activity page
		menuItemActivityPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToMenuItemActivityPage();
		// Search and select menu Item using menu Item Number = 1
		menuItemActivityPage.searchAndSelectMenuItem(menuItemNumber);
		wait.until(ExpectedConditions.visibilityOf(menuItemActivityPage.MiaStartDate_BT));
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		// get Start date as 5days earlier date
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.DATE, -5);
		String startDate = dateFormat.format(cal1.getTime());
		// Get end Date as yesterday date
		Calendar cal2 = Calendar.getInstance();
		cal2.add(Calendar.DATE, -1);
		String endDate = dateFormat.format(cal2.getTime());
		// Get current hour
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH");
		Calendar cal3 = Calendar.getInstance();
		cal3.add(Calendar.DATE, 0);
		String currentHour = dateFormat2.format(cal3.getTime());
		// Get end time as 2 hour later from current time
		String endTime = String.valueOf(Integer.parseInt(currentHour) + 2)+ ":00";
		// enter start date
		menuItemActivityPage.enterDateInMenuItemStartDate(startDate);
		// enter end date
		menuItemActivityPage.enterDateInMenuItemEndDate(endDate);
		// enter start time
		menuItemActivityPage.selectStartTime(startTime);
		// enter end time
		menuItemActivityPage.selectEndTime(endTime);
		// Click on search button
		menuItemActivityPage.ShowResults_BT.click();
		wait.until(ExpectedConditions.visibilityOf(menuItemActivityPage.SelectItem_Value));
		// Verify that promo value for menu item should be displayed for selected date range
		boolean promoValueForMenuItemDisplayed = menuItemActivityPage.verifyPromoValueForMenuItemDisplayed(startDate, endDate);
		if (promoValueForMenuItemDisplayed) {
			Reporter.reportPassResult(
					browser,"sprint8_US603_TC1634",
					"User should be able to view Total Menu Items Promo'd (rolled up for the time periods for the selected date range).",
					"Pass");

		} else {
			Reporter.reportTestFailure(
					browser,"sprint8_US603_TC1634","sprint8_US603_TC1634",
					"User should be able to view Total Menu Items Promo'd (rolled up for the time periods for the selected date range).",
					"Fail");
			AbstractTest.takeSnapShot("sprint8_US603_TC1634");
		}
	}
	
	// TC1643:Verify that user should be able to view rolled up POS activity for date and time selected as a default for each date in the date range.
	@Test()
	public void sprint8_US603_TC1643() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/**Variable Section :**/
		MenuItemActivityPage menuItemActivityPage;
		String storeId = GlobalVariable.StoreId;
		String userId=GlobalVariable.userId;
		HSSFSheet menuItemActivityPageSheet = ReadTestData.getTestDataSheet("sprint8_US603_TC1643", "Object1");
		String menuItemNumber = ReadTestData.getTestData(menuItemActivityPageSheet, "MenuItemNumber");
		String startTime = ReadTestData.getTestData(menuItemActivityPageSheet, "CustomStartTime");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to menu item activity page
		menuItemActivityPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToMenuItemActivityPage();
		// Search and select menu Item using menu Item Number = 1
		menuItemActivityPage.searchAndSelectMenuItem(menuItemNumber);
		wait.until(ExpectedConditions.visibilityOf(menuItemActivityPage.MiaStartDate_BT));
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		// get Start date as 5days earlier date
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.DATE, -5);
		String startDate = dateFormat.format(cal1.getTime());
		// Get end Date as yesterday date
		Calendar cal2 = Calendar.getInstance();
		cal2.add(Calendar.DATE, -1);
		String endDate = dateFormat.format(cal2.getTime());
		// Get current hour
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH");
		Calendar cal3 = Calendar.getInstance();
		cal3.add(Calendar.DATE, 0);
		String currentHour = dateFormat2.format(cal3.getTime());
		// Get end time as 2 hour later from current time
		String endTime = String.valueOf(Integer.parseInt(currentHour) + 2)+ ":00";
		// enter start date
		menuItemActivityPage.enterDateInMenuItemStartDate(startDate);
		// enter end date
		menuItemActivityPage.enterDateInMenuItemEndDate(endDate);
		// enter start time
		menuItemActivityPage.selectStartTime(startTime);
		// enter end time
		menuItemActivityPage.selectEndTime(endTime);
		// Click on search button
		menuItemActivityPage.ShowResults_BT.click();
		wait.until(ExpectedConditions.visibilityOf(menuItemActivityPage.SelectItem_Value));
		// Verify that Pos Activity value for menu item should be displayed for selected date range
		boolean PosActivityForMenuItemDisplayed = menuItemActivityPage.verifyPOSActivityForMenuItemDisplayed(startDate, endDate);
		if (PosActivityForMenuItemDisplayed) {
			Reporter.reportPassResult(
					browser,"sprint8_US603_TC1643",
					"User should be able to view rolled up POS activity for date and time selected as a default for each date in the date range",
					"Pass");

		} else {
			Reporter.reportTestFailure(
					browser,"sprint8_US603_TC1643","sprint8_US603_TC1643",
					"User should be able to view rolled up POS activity for date and time selected as a default for each date in the date range",
					"Fail");
			AbstractTest.takeSnapShot("sprint8_US603_TC1643");
		}
	}
	
	// TC1645:Verify that user should be able to view the menu item activity after expanding the dates at 15 min level.
	@Test()
	public void sprint8_US603_TC1645() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/**Variable Section :**/
		MenuItemActivityPage menuItemActivityPage;
		String storeId = GlobalVariable.StoreId;
		String userId=GlobalVariable.userId;
		HSSFSheet menuItemActivityPageSheet = ReadTestData.getTestDataSheet("sprint8_US603_TC1645", "Object1");
		String menuItemNumber = ReadTestData.getTestData(menuItemActivityPageSheet, "MenuItemNumber");
		String startTime = ReadTestData.getTestData(menuItemActivityPageSheet, "CustomStartTime");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to menu item activity page
		menuItemActivityPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToMenuItemActivityPage();
		// Search and select menu Item using menu Item Number = 1
		menuItemActivityPage.searchAndSelectMenuItem(menuItemNumber);
		wait.until(ExpectedConditions.visibilityOf(menuItemActivityPage.MiaStartDate_BT));
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		// get Start date as 4 days earlier date
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.DATE, -4);
		String startDate = dateFormat.format(cal1.getTime());
		// get end date as yesterday date
		Calendar cal2 = Calendar.getInstance();
		cal2.add(Calendar.DATE, -1);
		String endDate = dateFormat.format(cal2.getTime());
		//Get current hour
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH");
		Calendar cal3 = Calendar.getInstance();
		cal3.add(Calendar.DATE, 0);
		String currentHour = dateFormat2.format(cal2.getTime());
		//Get end time as 2 hour later from current time
		String endTime = String.valueOf(Integer.parseInt(currentHour)+2)+":00";
		// enter start date
		menuItemActivityPage.enterDateInMenuItemStartDate(startDate);
		// enter end date
		menuItemActivityPage.enterDateInMenuItemEndDate(endDate);
		// Select start time
		menuItemActivityPage.selectStartTime(startTime);
		// Select end time
		menuItemActivityPage.selectEndTime(endTime);
		//click on search button
		menuItemActivityPage.ShowResults_BT.click();
		wait.until(ExpectedConditions.visibilityOf(menuItemActivityPage.SelectItem_Value));
		//Expand the date group
		menuItemActivityPage.expandDateGroup();
		//Verify that menu item activity is displayed in the 15 min time slice for the expanded date.
		boolean miaForTimeSpanDisplayed = menuItemActivityPage.verifyMenuActivityDisplayedFor15MinTimeSlice(endDate);
		if (miaForTimeSpanDisplayed) {
			Reporter.reportPassResult(
					browser,"sprint8_US603_TC1645",
					"User should be able to view the menu item activity in the 15 min time slice for the expanded date.",
					"Pass");

		} else {
			Reporter.reportTestFailure(
					browser,"sprint8_US603_TC1645","sprint8_US603_TC1645",
					"User should be able to view the menu item activity in the 15 min time slice for the expanded date.",
					"Fail");
			AbstractTest.takeSnapShot("sprint8_US603_TC1645");
		}
	}

	//TC1646:Verify that the user should be able to view the total menu items sold for a date in 15 minute time slice.
	@Test()
	public void sprint8_US603_TC1646() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/**Variable Section :**/
		MenuItemActivityPage menuItemActivityPage;
		String storeId = GlobalVariable.StoreId;
		String userId=GlobalVariable.userId;
		HSSFSheet menuItemActivityPageSheet = ReadTestData.getTestDataSheet("sprint8_US603_TC1646", "Object1");
		String menuItemNumber = ReadTestData.getTestData(menuItemActivityPageSheet, "MenuItemNumber");
		String startTime = ReadTestData.getTestData(menuItemActivityPageSheet, "CustomStartTime");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to menu item activity page
		menuItemActivityPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToMenuItemActivityPage();
		// Search and select menu Item using menu Item Number = 1
		menuItemActivityPage.searchAndSelectMenuItem(menuItemNumber);
		wait.until(ExpectedConditions.visibilityOf(menuItemActivityPage.MiaStartDate_BT));
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		// get Start date as 4 days earlier date
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.DATE, -4);
		String startDate = dateFormat.format(cal1.getTime());
		// get end date as yesterday date
		Calendar cal2 = Calendar.getInstance();
		cal2.add(Calendar.DATE, -1);
		String endDate = dateFormat.format(cal2.getTime());
		// Get current hour
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH");
		Calendar cal3 = Calendar.getInstance();
		cal3.add(Calendar.DATE, 0);
		String currentHour = dateFormat2.format(cal2.getTime());
		// Get end time as 2 hour later from current time
		String endTime = String.valueOf(Integer.parseInt(currentHour) + 2)+ ":00";
		// enter start date
		menuItemActivityPage.enterDateInMenuItemStartDate(startDate);
		// enter end date
		menuItemActivityPage.enterDateInMenuItemEndDate(endDate);
		// Select start time
		menuItemActivityPage.selectStartTime(startTime);
		// Select end time
		menuItemActivityPage.selectEndTime(endTime);
		// click on search button
		menuItemActivityPage.ShowResults_BT.click();
		wait.until(ExpectedConditions.visibilityOf(menuItemActivityPage.SelectItem_Value));
		// Expand the date group
		menuItemActivityPage.expandDateGroup();
		// Verify that menu item activity is displayed in the 15 min time slice
		// for the expanded date.
		boolean soldItemForTimeSpanDisplayed = menuItemActivityPage.verifySoldMenuItemDisplayedFor15MinTimeSlice(endDate);
		if (soldItemForTimeSpanDisplayed) {
			Reporter.reportPassResult(
					browser,"sprint8_US603_TC1646",
					"User should be able to view the total menu items sold for a date in 15 minute time slice.",
					"Pass");

		} else {
			Reporter.reportTestFailure(
					browser,"sprint8_US603_TC1646","sprint8_US603_TC1646",
					"User should be able to view the total menu items sold for a date in 15 minute time slice.",
					"Fail");
			AbstractTest.takeSnapShot("sprint8_US603_TC1646");
		}
	}
	
	// TC1647:Verify that the user should be able to view the Total Menu Items Wasted in the 15 minute time slice.
	@Test()
	public void sprint8_US603_TC1647() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/**Variable Section :**/
		MenuItemActivityPage menuItemActivityPage;
		String storeId = GlobalVariable.StoreId;
		String userId=GlobalVariable.userId;
		HSSFSheet menuItemActivityPageSheet = ReadTestData.getTestDataSheet("sprint8_US603_TC1647", "Object1");
		String menuItemNumber = ReadTestData.getTestData(menuItemActivityPageSheet, "MenuItemNumber");
		String startTime = ReadTestData.getTestData(menuItemActivityPageSheet, "CustomStartTime");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to menu item activity page
		menuItemActivityPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToMenuItemActivityPage();
		// Search and select menu Item using menu Item Number = 1
		menuItemActivityPage.searchAndSelectMenuItem(menuItemNumber);
		wait.until(ExpectedConditions.visibilityOf(menuItemActivityPage.MiaStartDate_BT));
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		// get Start date as 4 days earlier date
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.DATE, -4);
		String startDate = dateFormat.format(cal1.getTime());
		// get end date as yesterday date
		Calendar cal2 = Calendar.getInstance();
		cal2.add(Calendar.DATE, -1);
		String endDate = dateFormat.format(cal2.getTime());
		// Get current hour
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH");
		Calendar cal3 = Calendar.getInstance();
		cal3.add(Calendar.DATE, 0);
		String currentHour = dateFormat2.format(cal2.getTime());
		// Get end time as 2 hour later from current time
		String endTime = String.valueOf(Integer.parseInt(currentHour) + 2)+ ":00";
		// enter start date
		menuItemActivityPage.enterDateInMenuItemStartDate(startDate);
		// enter end date
		menuItemActivityPage.enterDateInMenuItemEndDate(endDate);
		// Select start time
		menuItemActivityPage.selectStartTime(startTime);
		// Select end time
		menuItemActivityPage.selectEndTime(endTime);
		// click on search button
		menuItemActivityPage.ShowResults_BT.click();
		wait.until(ExpectedConditions.visibilityOf(menuItemActivityPage.SelectItem_Value));
		// Expand the date group
		menuItemActivityPage.expandDateGroup();
		// Verify that menu item activity is displayed in the 15 min time slice
		// for the expanded date.
		boolean wasteItemForTimeSpanDisplayed = menuItemActivityPage.verifyWasteItemDisplayedFor15MinTimeSlice(endDate);
		if (wasteItemForTimeSpanDisplayed) {
			Reporter.reportPassResult(
					browser,"sprint8_US603_TC1647",
					"User should be able to view the waste menu items sold for a date in 15 minute time slice.",
					"Pass");

		} else {
			Reporter.reportTestFailure(
					browser,"sprint8_US603_TC1647","sprint8_US603_TC1647",
					"User should be able to view the waste menu items sold for a date in 15 minute time slice.",
					"Fail");
			AbstractTest.takeSnapShot("sprint8_US603_TC1647");
		}
	}
	
	//TC1648:Verify that the user should be able to view the Total Menu Items Promo's in the 15 minute time slice.
	@Test()
	public void sprint8_US603_TC1648() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/**Variable Section :**/
		MenuItemActivityPage menuItemActivityPage;
		String storeId = GlobalVariable.StoreId;
		String userId=GlobalVariable.userId;
		HSSFSheet menuItemActivityPageSheet = ReadTestData.getTestDataSheet("sprint8_US603_TC1648", "Object1");
		String menuItemNumber = ReadTestData.getTestData(menuItemActivityPageSheet, "MenuItemNumber");
		String startTime = ReadTestData.getTestData(menuItemActivityPageSheet, "CustomStartTime");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to menu item activity page
		menuItemActivityPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToMenuItemActivityPage();
		// Search and select menu Item using menu Item Number = 1
		menuItemActivityPage.searchAndSelectMenuItem(menuItemNumber);
		wait.until(ExpectedConditions.visibilityOf(menuItemActivityPage.MiaStartDate_BT));
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		// get Start date as 4 days earlier date
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.DATE, -4);
		String startDate = dateFormat.format(cal1.getTime());
		// get end date as yesterday date
		Calendar cal2 = Calendar.getInstance();
		cal2.add(Calendar.DATE, -1);
		String endDate = dateFormat.format(cal2.getTime());
		// Get current hour
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH");
		Calendar cal3 = Calendar.getInstance();
		cal3.add(Calendar.DATE, 0);
		String currentHour = dateFormat2.format(cal2.getTime());
		// Get end time as 2 hour later from current time
		String endTime = String.valueOf(Integer.parseInt(currentHour) + 2)+ ":00";
		// enter start date
		menuItemActivityPage.enterDateInMenuItemStartDate(startDate);
		// enter end date
		menuItemActivityPage.enterDateInMenuItemEndDate(endDate);
		// Select start time
		menuItemActivityPage.selectStartTime(startTime);
		// Select end time
		menuItemActivityPage.selectEndTime(endTime);
		// click on search button
		menuItemActivityPage.ShowResults_BT.click();
		wait.until(ExpectedConditions.visibilityOf(menuItemActivityPage.SelectItem_Value));
		// Expand the date group
		menuItemActivityPage.expandDateGroup();
		// Verify that menu item activity is displayed in the 15 min time slice
		// for the expanded date.
		boolean menuItemPromoForTimeSpanDisplayed = menuItemActivityPage.verifyMenuItemPromoDisplayedFor15MinTimeSlice(endDate);
		if (menuItemPromoForTimeSpanDisplayed) {
			Reporter.reportPassResult(
					browser,"sprint8_US603_TC1648",
					"User should be able to view the total Menu Items Promo's in the 15 minute time slice",
					"Pass");

		} else {
			Reporter.reportTestFailure(
					browser,"sprint8_US603_TC1648","sprint8_US603_TC1648",
					"User should be able to view the total Menu Items Promo's in the 15 minute time slice",
					"Fail");
			AbstractTest.takeSnapShot("sprint8_US603_TC1648");
		}
	}
	
	// TC1674:Verify that the user should be able to view the date information in greater detail for every 15 minute time slice for each date and the time period selected.
	@Test()
	public void sprint8_US603_TC1674() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/**Variable Section :**/
		MenuItemActivityPage menuItemActivityPage;
		String storeId = GlobalVariable.StoreId;
		String userId=GlobalVariable.userId;
		HSSFSheet menuItemActivityPageSheet = ReadTestData.getTestDataSheet("sprint8_US603_TC1674", "Object1");
		String menuItemNumber = ReadTestData.getTestData(menuItemActivityPageSheet, "MenuItemNumber");
		String startTime = ReadTestData.getTestData(menuItemActivityPageSheet, "CustomStartTime");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to menu item activity page
		menuItemActivityPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToMenuItemActivityPage();
		// Search and select menu Item using menu Item Number = 1
		menuItemActivityPage.searchAndSelectMenuItem(menuItemNumber);
		wait.until(ExpectedConditions.visibilityOf(menuItemActivityPage.MiaStartDate_BT));
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		// get yesterday date and time
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.DATE, -4);
		String startDate = dateFormat.format(cal1.getTime());
		
		Calendar cal2 = Calendar.getInstance();
		cal2.add(Calendar.DATE, -1);
		String endDate = dateFormat.format(cal2.getTime());
		
		//Get current hour
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH");
		Calendar cal3 = Calendar.getInstance();
		cal3.add(Calendar.DATE, 0);
		String currentHour = dateFormat2.format(cal3.getTime());
		//Get end time as 2 hour later from current time
		String endTime = String.valueOf(Integer.parseInt(currentHour)+2)+":00";
		// enter start date
		menuItemActivityPage.enterDateInMenuItemStartDate(startDate);
		// enter end date
		menuItemActivityPage.enterDateInMenuItemEndDate(endDate);
		// verify that start time is displayed as 00:00
		menuItemActivityPage.selectStartTime(startTime);
		// verify that end time is displayed as 2 hour later from current time
		menuItemActivityPage.selectEndTime(endTime);
		menuItemActivityPage.ShowResults_BT.click();
		wait.until(ExpectedConditions.visibilityOf(menuItemActivityPage.SelectItem_Value));
		menuItemActivityPage.expandDateGroup();
		boolean recordsWithTimeSpanDisplayed = menuItemActivityPage.verifyMenuActivityTimeInDetailForEachDate(startTime, endTime);
		if (recordsWithTimeSpanDisplayed) {
			Reporter.reportPassResult(
					browser,"sprint8_US603_TC1674",
					"User should be able to view the date information in greater detail for every 15 minute time slice for each date and the time period selected.",
					"Pass");

		} else {
			Reporter.reportTestFailure(
					browser,"sprint8_US603_TC1674","sprint8_US603_TC1674",
					"User should be able to view the date information in greater detail for every 15 minute time slice for each date and the time period selected.",
					"Fail");
			AbstractTest.takeSnapShot("sprint8_US603_TC1674");
		}
	}
}
