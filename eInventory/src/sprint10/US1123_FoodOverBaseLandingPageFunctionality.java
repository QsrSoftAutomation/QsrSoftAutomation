package sprint10;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import sprint2.AbstractTest;
import common.Base;
import common.GlobalVariable;
import common.Reporter;
import eInventoryPageClasses.FoodOverBasePage;
import eInventoryPageClasses.HomePage;

public class US1123_FoodOverBaseLandingPageFunctionality extends AbstractTest{
	
	//TC1912: Verify that the user is able to view "Food Over Base" page.
	@Test()
	public void sprint10_US1123_TC1912() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		FoodOverBasePage foodOverBasePage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Food Over Base page
		foodOverBasePage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToFoodOverBasePage();
		//Verify that user is able to view Base Over Page Header
		if (Base.isElementDisplayed(foodOverBasePage.FoodOverBase_Label)) {
			Reporter.reportPassResult(
					browser,"sprint10_US1123_TC1912",
					"User should be navigated to 'Food Over Base' landing page.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint10_US1123_TC1912","sprint10_US1123_TC1912",
					"User should be navigated to 'Food Over Base' landing page.",
					"Fail");
			AbstractTest.takeSnapShot("sprint10_US1123_TC1912");
		}
	}
	
	//TC1918: Verify that the user is able to view the heading as "Controllable Comp" on the "Food Over Base" landing page.
	@Test()
	public void sprint10_US1123_TC1918() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		FoodOverBasePage foodOverBasePage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Food Over Base page
		foodOverBasePage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToFoodOverBasePage();
		// Verify that user is able to view Controllable Comp  Header for each month table 
		if (foodOverBasePage.verifyControllableCompHeaderDisplayedForEachMonth()) {
			Reporter.reportPassResult(
					browser,"sprint10_US1123_TC1918",
					"User should be able to view the heading as Controllable Comp on the Food Over Base landing page.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint10_US1123_TC1918","sprint10_US1123_TC1918",
					"User should be able to view the heading as Controllable Comp on the Food Over Base landing page.",
					"Fail");
			AbstractTest.takeSnapShot("sprint10_US1123_TC1918");
		}
	}
	
	/*TC1920: Verify that the user is able to view "Base Food, Menu Item Waste, Raw Waste, Condiment, Employee/Manager Food,
	Discount Coupon, Stat Variance and  Unexplained Difference" rows in the section displaying the historical Food over Base percentages.*/
	@Test()
	public void sprint10_US1123_TC1920() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		FoodOverBasePage foodOverBasePage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Food Over Base page
		foodOverBasePage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToFoodOverBasePage();
		/*Verify that the user is able to view "Base Food, Menu Item Waste, Raw Waste, Condiment, Employee/Manager Food,Discount Coupon,
		Stat Variance and  Unexplained Difference" rows in the section displaying the historical Food over Base percentages*/
		if (foodOverBasePage.verifyRawHeaderDisplayedForEachMonth()) {
			Reporter.reportPassResult(
					browser,"sprint10_US1123_TC1920",
					"User should be able to view different Raw headers in Food Over Base landing page.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint10_US1123_TC1920","sprint10_US1123_TC1920",
					"User should be able to view different Raw headers in Food Over Base landing page.",
					"Fail");
			AbstractTest.takeSnapShot("sprint10_US1123_TC1920");
		}
	}
	
	//TC1932: Verify that the user is able to view name of month and year for each historical month above the details columns of Controllable Comp.
	@Test()
	public void sprint10_US1123_TC1932() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		FoodOverBasePage foodOverBasePage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Food Over Base page
		foodOverBasePage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToFoodOverBasePage();
		// Verify that user is able to view name of month and year for each historical month
		if (foodOverBasePage.verifyMonthAndYearIsDisplayedForEachHistoricalMonth()) {
			Reporter.reportPassResult(
					browser,"sprint10_US1123_TC1932",
					"User should be able to view name of month and year for each historical month=x above the details columns of Controllable Comp",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint10_US1123_TC1932","sprint10_US1123_TC1932",
					"User should be able to view name of month and year for each historical month=x above the details columns of Controllable Comp",
					"Fail");
			AbstractTest.takeSnapShot("sprint10_US1123_TC1932");
		}
	}
	
	//TC1947: Verify that the user is able to view name of month and year for each historical month above the details columns of Controllable Comp.
	@Test()
	public void sprint10_US1123_TC1947() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		FoodOverBasePage foodOverBasePage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Food Over Base page
		foodOverBasePage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToFoodOverBasePage();
		// Verify that User should be able to view 'actual %=x' and 'actual $=y' of 'Food Over Base' for each historical month
		if (foodOverBasePage.verifyActualValuesDisplayedForEachHistoricalMonth()) {
			Reporter.reportPassResult(
					browser,"sprint10_US1123_TC1947",
					"User should be able to view 'actual %=x' and 'actual $=y' of 'Food Over Base' for each historical month=z in column 1 and column 2 respectively.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint10_US1123_TC1947","sprint10_US1123_TC1947",
					"User should be able to view 'actual %=x' and 'actual $=y' of 'Food Over Base' for each historical month=z in column 1 and column 2 respectively.",
					"Fail");
			AbstractTest.takeSnapShot("sprint10_US1123_TC1947");
		}
	}
	
	// TC1948: Verify that the user is able to view past 3 months' final Food Over Base component calculations as a default.
	@Test()
	public void sprint10_US1123_TC1948() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		FoodOverBasePage foodOverBasePage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Food Over Base page
		foodOverBasePage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToFoodOverBasePage();
		// Verify that user is able to view 'actual %=x' and 'actual $=y' calculations for past 3 months'
		if (foodOverBasePage.PreviousMonths_Header_Label.size() == 3
				& foodOverBasePage.verifyMonthAndYearIsDisplayedForEachHistoricalMonth()
				& foodOverBasePage.verifyActualValuesDisplayedForEachHistoricalMonth()) {
			Reporter.reportPassResult(
					browser,"sprint10_US1123_TC1948",
					"User should be able to view past 3 months' final Food Over Base component calculations, i.e 'actual %=x' and 'actual $=y' as a default.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint10_US1123_TC1948","sprint10_US1123_TC1948",
					"User should be able to view past 3 months' final Food Over Base component calculations, i.e 'actual %=x' and 'actual $=y' as a default.",
					"Fail");
			AbstractTest.takeSnapShot("sprint10_US1123_TC1948");
		}
	}
	
	/*TC1953: Verify that the user is able to view "Base Food, Menu Item Waste, Raw Waste, Condiment, Employee/Manager Food,
	Discount Coupon, Stat Variance and  Unexplained Difference" rows in the section displaying the current month to date Food 
	Over Base components calculated information.*/
	@Test()
	public void sprint10_US1123_TC1953() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		FoodOverBasePage foodOverBasePage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Food Over Base page
		foodOverBasePage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToFoodOverBasePage();
		// Verify that user is able to view Base Food, Menu Item Waste, Raw Waste etc. rows in the section displaying the current month to date
		int currentMonth = Base.getMonthFromDate(Base.returnTodayDate())+1;
		int currentYear = Base.getYearFromDate(Base.returnTodayDate());
		String monthName = Base.getMonthName(currentMonth);
		if (foodOverBasePage.CurrentMonth_Header_Label.getText().equals(monthName+" "+currentYear)
				& foodOverBasePage.verifyRawHeaderDisplayedForEachMonth()){
			Reporter.reportPassResult(
					browser,"sprint10_US1123_TC1953",
					"User should be able to view different rows in the section displaying the current month to date in Food Over Base landing page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint10_US1123_TC1953","sprint10_US1123_TC1953",
					"User should be able to view different rows in the section displaying the current month to date in Food Over Base landing page",
					"Fail");
			AbstractTest.takeSnapShot("sprint10_US1123_TC1953");
		}
	}
	
	/*TC1954: Verify that the user is able to view the 2nd column named as "Targets" in the section displaying the 
	current month to date Food Over Base components calculated information.*/
	@Test()
	public void sprint10_US1123_TC1954() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		FoodOverBasePage foodOverBasePage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Food Over Base page
		foodOverBasePage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToFoodOverBasePage();
		// Verify that User should be able to view the 2nd column named as "Targets" in the section displaying the current month to date Food Over Base components calculated information.
		if (foodOverBasePage.verifyColumnHeaderDisplayedForCurrentMonth()) {
			Reporter.reportPassResult(
					browser,"sprint10_US1123_TC1954",
					"User should be able to view the 2nd column named as 'Targets' in the section displaying the current month to date",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint10_US1123_TC1954","sprint10_US1123_TC1954",
					"User should be able to view the 2nd column named as 'Targets' in the section displaying the current month to date",
					"Fail");
			AbstractTest.takeSnapShot("sprint10_US1123_TC1954");
		}
	}
	
	/*TC1955: Verify that the user is able to view "%=x" and "$=y" in column 1 and column 2 respectively of second column  named 
	"Targets" for the section on the page displaying the current month to date Food Over Base components calculated information.*/
	@Test()
	public void sprint10_US1123_TC1955() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		FoodOverBasePage foodOverBasePage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Food Over Base page
		foodOverBasePage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToFoodOverBasePage();
		// Verify that user is able to view "%=x" in column 1 respectively of second column named "Targets" 
		boolean targetPercentDisplayed = true;
		for(WebElement targetPercent : foodOverBasePage.CurrentMonth_TargetPercent_List){
			targetPercentDisplayed = targetPercentDisplayed & targetPercent.getText().contains("%");
		}
		// Verify that user is able to view "$=y"  in column 2 respectively of second column named "Targets" 
		boolean targetDollerDisplayed = true;
		for(WebElement targetDoller : foodOverBasePage.CurrentMonth_TargetDoller_List){
			targetPercentDisplayed = targetPercentDisplayed & targetDoller.getText().contains("$");
		}
		if (targetPercentDisplayed & targetDollerDisplayed) {
			Reporter.reportPassResult(
					browser,"sprint10_US1123_TC1955",
					"User should be able to view '%=x' and '$=y' in column 1 and column 2 respectively of second column named 'Targets' for the section on the page displaying the current month to date",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint10_US1123_TC1955","sprint10_US1123_TC1955",
					"User should be able to view '%=x' and '$=y' in column 1 and column 2 respectively of second column named 'Targets' for the section on the page displaying the current month to date",
					"Fail");
			AbstractTest.takeSnapShot("sprint10_US1123_TC1955");
		}
	}
	
	/*TC1956: Verify that the user is able to view the 3rd section named as "Month to Date (Actual)" in the section
	  displaying the current month to date Food Over Base components calculated information.*/
	@Test()
	public void sprint10_US1123_TC1956() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		FoodOverBasePage foodOverBasePage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Food Over Base page
		foodOverBasePage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToFoodOverBasePage();
		// Verify that the user is able to view the 3rd section named as "Month to Date (Actual)" in the section displaying the current month to date
		if (foodOverBasePage.verifyColumnHeaderDisplayedForCurrentMonth()) {
			Reporter.reportPassResult(
					browser,"sprint10_US1123_TC1956",
					"User should be able to view the 3rd section named as 'Month to Date (Actual)' in the section displaying the current month to date ",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint10_US1123_TC1956","sprint10_US1123_TC1956",
					"User should be able to view the 3rd section named as 'Month to Date (Actual)' in the section displaying the current month to date ",
					"Fail");
			AbstractTest.takeSnapShot("sprint10_US1123_TC1956");
		}
	}
	
	/*TC1957 : Verify that the user is able to view "actual %=x" and "actual $=y" in column 1 and column 2 respectively 
	 of third column named "Month to Date (Actual)" for the section on the page displaying the current month to date 
	 Food Over Base components calculated information.*/
	@Test()
	public void sprint10_US1123_TC1957() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		FoodOverBasePage foodOverBasePage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Food Over Base page
		foodOverBasePage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToFoodOverBasePage();
		// Verify that user is able to view "%=x" in column 1 respectively of second column named "Targets" 
		boolean monthToDatePercentDisplayed = true;
		for(WebElement monthToDatePercent : foodOverBasePage.CurrentMonth_MonthToDateActualPercent_List){
			monthToDatePercentDisplayed =  monthToDatePercentDisplayed & monthToDatePercent.getText().contains("%");
		}
		// Verify that user is able to view "$=y"  in column 2 respectively of second column named "Targets" 
		boolean monthToDateDollerDisplayed = true;
		for(WebElement monthToDateDoller : foodOverBasePage.CurrentMonth_MonthToDateActualDoller_List){
			monthToDateDollerDisplayed = monthToDateDollerDisplayed & monthToDateDoller.getText().contains("$");
		}
		if (monthToDatePercentDisplayed & monthToDateDollerDisplayed) {
			Reporter.reportPassResult(
					browser,"sprint10_US1123_TC1957",
					"User should be able to view 'actual %=x' and 'actual $=y' in column 1 and column 2 respectively of third column named 'Month to Date (Actual)'for the section on the page displaying the current month to date",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint10_US1123_TC1957","sprint10_US1123_TC1957",
					"User should be able to view 'actual %=x' and 'actual $=y' in column 1 and column 2 respectively of third column named 'Month to Date (Actual)'for the section on the page displaying the current month to date",
					"Fail");
			AbstractTest.takeSnapShot("sprint10_US1123_TC1957");
		}
	}
	
	/*TC1958: Verify that the user is able to view "Product Net Sales=x" above the "Month to Date" column in the section of the 
	page displaying the current month to date Food Over Base components calculated information.*/
	@Test()
	public void sprint10_US1123_TC1958() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		FoodOverBasePage foodOverBasePage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Food Over Base page
		foodOverBasePage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToFoodOverBasePage();
		// Verify that user is able to view "Product Net Sales=x" above the "Month to Date" column
		if (foodOverBasePage.ProductNetSales_Label.getText().contains("Net Sales: $")) {
			Reporter.reportPassResult(
					browser,"sprint10_US1123_TC1958",
					"User should be view 'Product Net Sales=x' above the 'Month to Date' column",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint10_US1123_TC1958","sprint10_US1123_TC1958",
					"User should be view 'Product Net Sales=x' above the 'Month to Date' column",
					"Fail");
			AbstractTest.takeSnapShot("sprint10_US1123_TC1958");
		}
	}
}
