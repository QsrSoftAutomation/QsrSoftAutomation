package sprint9;

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
import common.GlobalVariable;
import common.ReadTestData;
import common.Reporter;
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.MenuItemActivityPage;

public class US1047_MenuItemActivityPage  extends AbstractTest {
	
	//TC1733:Verify that the user should be able to expand the days to view the Menu Item activity at the 15 minute detail for the searched menu item.
	@Test()
	public void sprint9_US1047_TC1733() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		MenuItemActivityPage menuItemActivityPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet menuItemActivityPageSheet = ReadTestData.getTestDataSheet("sprint9_US1047_TC1733", "Object1");
		String menuItemNumber = ReadTestData.getTestData(menuItemActivityPageSheet, "MenuItemNumber");
		String startTime = ReadTestData.getTestData(menuItemActivityPageSheet,"CustomStartTime");
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
		cal1.add(Calendar.DATE, -1);
		String yesterdayDate = dateFormat.format(cal1.getTime());
		// Get current hour
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH");
		Calendar cal2 = Calendar.getInstance();
		cal2.add(Calendar.DATE, 0);
		String currentHour = dateFormat2.format(cal2.getTime());
		// Get end time as 2 hour later from current time
		String endTime = String.valueOf(Integer.parseInt(currentHour) + 2)+ ":00";
		// enter start date
		menuItemActivityPage.enterDateInMenuItemStartDate(yesterdayDate);
		// enter end date
		menuItemActivityPage.enterDateInMenuItemEndDate(yesterdayDate);
		// select start time as 05:00
		menuItemActivityPage.selectStartTime(startTime);
		//select end time
		menuItemActivityPage.selectEndTime(endTime);
		//click on search menu item activity button
		menuItemActivityPage.ShowResults_BT.click();
		wait.until(ExpectedConditions.visibilityOf(menuItemActivityPage.SelectItem_Value));
		//Expand the date group for menu item activity
		menuItemActivityPage.expandDateGroup();
		//Verify menu item activity is displayed in details with 15 minute time stamp
		if (menuItemActivityPage.verifyMenuActivityTimeInDetailForSelectedDate(yesterdayDate,startTime, endTime)) {
			Reporter.reportPassResult(
					browser,"sprint9_US1047_TC1733",
					"User should be able to view the expanded Menu Item activity for a menu item='a' at the 15 minute detail for the date='D'.",
					"Pass");

		} else {
			Reporter.reportTestFailure(
					browser,"sprint9_US1047_TC1733","sprint9_US1047_TC1733",
					"User should be able to view the expanded Menu Item activity for a menu item='a' at the 15 minute detail for the date='D'.",
					"Fail");
			AbstractTest.takeSnapShot("sprint9_US1047_TC1733");
		}
	}
	
	// TC1732:Verify that the user should be able to view the collapsed rolled up daily totals for a menu item on the Menu Item Activity page.
	@Test()
	public void sprint9_US1047_TC1732() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		MenuItemActivityPage menuItemActivityPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet menuItemActivityPageSheet = ReadTestData.getTestDataSheet("sprint9_US1047_TC1732", "Object1");
		String menuItemNumber = ReadTestData.getTestData(menuItemActivityPageSheet, "MenuItemNumber");
		String startTime = ReadTestData.getTestData(menuItemActivityPageSheet,"CustomStartTime");
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
		// Enter start time
		menuItemActivityPage.selectStartTime(startTime);
		// Enter end time
		menuItemActivityPage.selectEndTime(endTime);
		// Click on search button
		menuItemActivityPage.ShowResults_BT.click();
		wait.until(ExpectedConditions.visibilityOf(menuItemActivityPage.SelectItem_Value));
		//verify that daily totals for a menu item is displayed on the Menu Item Activity page
		boolean dailyTotalsDisplayed  = menuItemActivityPage.verifySoldValueForMenuItemDisplayed(startDate, endDate)
				&& menuItemActivityPage.verifyEmpMealValueForMenuItemDisplayed(startDate, endDate)
				&& menuItemActivityPage.verifyMgrMealValueForMenuItemDisplayed(startDate, endDate)
				&& menuItemActivityPage.verifyWasteValueForMenuItemDisplayed(startDate, endDate)
				&& menuItemActivityPage.verifyPromoValueForMenuItemDisplayed(startDate, endDate)
				&& menuItemActivityPage.verifyPOSActivityForMenuItemDisplayed(startDate, endDate);
		if (dailyTotalsDisplayed) {
			Reporter.reportPassResult(
					browser,"sprint9_US1047_TC1732",
					"User should be able to view the collapsed rolled up daily totals for a menu item='a' on the Menu Item Activity page.",
					"Pass");

		} else {
			Reporter.reportTestFailure(
					browser,"sprint9_US1047_TC1732","sprint9_US1047_TC1732",
					"User should be able to view the collapsed rolled up daily totals for a menu item='a' on the Menu Item Activity page.",
					"Fail");
			AbstractTest.takeSnapShot("sprint9_US1047_TC1732");
		}
	}

}
