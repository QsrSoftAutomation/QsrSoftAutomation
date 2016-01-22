package sprint9;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import sprint2.AbstractTest;
import common.Base;
import common.GlobalVariable;
import common.Reporter;
import eInventoryPageClasses.HomePage;

public class US916_ViewAndPrintStatVariance extends AbstractTest{
	
	//TC1706: Verify the selection from the inventory drop down is available named Daily Stat
	@Test()
	public void sprint9_US916_TC1706() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Promo and waste page
		homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().OtherInventoryFunctions_BT.click();
		if (Base.isElementDisplayed(homePage.VarianceStat_BT)) {
			Reporter.reportPassResult(
					browser,"sprint9_US916_TC1706",
					"User should be able to view Daily stat option from inventory drop down",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint9_US916_TC1706","sprint9_US916_TC1706",
					"User should be able to view Daily stat option from inventory drop down",
					"Fail");
			AbstractTest.takeSnapShot("sprint9_US916_TC1706");
		}
	}
	
	//TC1707:Verify the user has the ability to to view the daily stat.
	/*@Test()
	public void sprint9_US916_TC1707() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		*//** Variable Section : **//*
		String storeId = GlobalVariable.StoreId;
		*//***********************************//*
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Promo and waste page
		DailyStatPage dailyStatPage = homePage.selectAStore(storeId).goToDailyStatPage();
		dailyStatPage.StartDate_BT.click();
		String date = "07/02/2015";
		int month = Base.getMonthFromDate(date);
		//int day = Base.getDayFromDate(date);
		String monthName = Base.getMonthName(month+1);
		dailyStatPage.clickOnMonth(monthName);
		dailyStatPage.selectDateFromCalender(date);
		
		if (Base.isElementDisplayed(homePage.Inventory_DD_DailyStat)) {
			Reporter.reportPassResult(
					browser,"sprint9_US916_TC1706",
					"User should be able to view Daily stat option from inventory drop down",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint9_US916_TC1706","sprint9_US916_TC1706",
					"User should be able to view Daily stat option from inventory drop down",
					"Fail");
			AbstractTest.takeSnapShot("sprint9_US916_TC1706");
		}
	}*/

}
