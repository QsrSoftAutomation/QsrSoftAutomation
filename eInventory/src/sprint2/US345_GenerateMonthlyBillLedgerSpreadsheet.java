package sprint2;

import java.io.IOException;
import java.util.Calendar;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import common.Base;
import common.GlobalVariable;
import common.Reporter;
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.PurchasesPage;
import eInventoryPageClasses.StoreLedgerDetailPage;


public class US345_GenerateMonthlyBillLedgerSpreadsheet extends AbstractTest {
	/* ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''' */
	// TC532 Verify that "View Store Ledger" button is available on the Physical Inventory page to export the Manual Purchase and Electronic Invoice
	@Test()
	public void Sprint2_US345_TC532() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/* Start-Variable Deceleration */
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		PurchasesPage purchasePage;
		/* End-Variable Deceleration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Going to the purchase landing page and verifying that "View Store Ledger button" is loaded on this page
		purchasePage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPurchaseLandingPage();
		if (purchasePage.isPurchaseLandingPageLoaded() & purchasePage.ViewStoreLedger_BT.isDisplayed() ) {
			Reporter.reportPassResult(
					browser,"Sprint2_US345_TC532",
					"View Store Ledger button should display on the page successfully",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint2_US345_TC532","Sprint2_US345_TC532",
					"View Store Ledger button should display on the page successfully",
					"Fail");
			AbstractTest.takeSnapShot("Sprint2_US345_TC532");
		}
	}
	
	/* ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''' */
	// TC533 Verify that user can navigate to Store Ledger page by clicking on "View Store Ledger" button
	@Test()
	public void Sprint2_US345_TC533() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/* Start-Variable Deceleration */
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/* End-Variable Deceleration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		/*
		 * Going to the purchase landing page after that clicking on
		 * "View Store Ledger" button and verifying that Store ledger page is loaded successfully.
		 */
		homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement()
				.goToPurchaseLandingPage().clickOnViewStoreLedgerButton()
				.verifyStoreLedgerDetailPage(browser, "Sprint2_US345_TC533");
	}
	
	/* ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''' */
	// TC534 Verify that default view on Store Ledger detail page is of current month
	@Test()
	public void Sprint2_US345_TC534() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/* Start-Variable Deceleration */
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String month = null;
		/* End-Variable Deceleration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		/*Going to the purchase landing page after that clicking on "View Store Ledger" button and 
		verifying that current month view is displaying on Store Ledger detail page..*/
		StoreLedgerDetailPage storeLedgerDetailPage = homePage.selectUser(userId).selectLocation(storeId).
				navigateToInventoryManagement().goToPurchaseLandingPage().clickOnViewStoreLedgerButton();
		// Fetch the current month and Year from the system
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		String Year = Integer.toString(year);
		int Month = (now.get(Calendar.MONTH) + 1);
		// Change the number of month into the String
		month = Base.getMonthName(Month);
		// Convert the month and year into the expected format
		String month_dd_value = "" + month + " " + Year + "";
		boolean result = storeLedgerDetailPage.month_DD_FirstElement.getText()
				.trim().equalsIgnoreCase(month_dd_value);
		if (result) {
			Reporter.reportPassResult(
					browser,"Sprint2_US345_TC534",
					"Default View of Current month should display on Store Ledgere detail Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint2_US345_TC534","Sprint2_US345_TC534",
					"Default View of Current month should display on Store Ledgere detail Page",
					"Fail");
		}
	}
	
	/* ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''' */
	/* TC 535 Verify that Store ledger Detail page shows: 
	 * 1) Monthly ledger transactions 
	 * 2) Invoices,Purchases and Adjustments for the current month
	 * as default view
	 */
	@Test()
	public void Sprint2_US345_TC535() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/* Start-Variable Deceleration */
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/* End-Variable Deceleration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		/*Going to the purchase landing page after that clicking on "View Store Ledger" button and 
		verifying the 'Search' label and Text box ,all the columns, and the header of the page.*/
		homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPurchaseLandingPage()
				.clickOnViewStoreLedgerButton().verifyStoreLedgerDetailPage(browser, "Sprint2_US345_TC535");
	}
	
	@Test()
	public void Sprint2_US345_TC536() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/* Start-Variable Deceleration */
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/* End-Variable Deceleration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		/*Going to the purchase landing page after that clicking on "View Store Ledger" button and 
		verifying the 'Search' label and Text box ,all the columns, and the header of the page.*/
		StoreLedgerDetailPage storeLedgerDetailPage= homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPurchaseLandingPage().clickOnViewStoreLedgerButton();
		// get current date, month and year from the system date
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int Month = (now.get(Calendar.MONTH) + 1);
		// Select current month/year from the selectMonthFromStoreLedger DropDown
		storeLedgerDetailPage.selectMonthFromStoreLedgerDrpDwn(Month, year);
		boolean storeLedgerDetailDisplayedForMonth = Base.isElementDisplayed(storeLedgerDetailPage.StoreLedgerTable_InvoiceId_Label)
				& Base.isElementDisplayed(storeLedgerDetailPage.StoreLedgerTable_DeliveryDate_Label)
				& Base.isElementDisplayed(storeLedgerDetailPage.StoreLedgerTable_InvoiceType_Label)
				& Base.isElementDisplayed(storeLedgerDetailPage.StoreLedgerTable_TransactionAmount_Label)
				& Base.isElementDisplayed(storeLedgerDetailPage.StoreLedgerTable_Food_Label)
				& Base.isElementDisplayed(storeLedgerDetailPage.StoreLedgerTable_Paper_Label)
				& Base.isElementDisplayed(storeLedgerDetailPage.StoreLedgerTable_OpsSupplies_Label)
				& Base.isElementDisplayed(storeLedgerDetailPage.StoreLedgerTable_Linens_Label)
				& Base.isElementDisplayed(storeLedgerDetailPage.StoreLedgerTable_NonProductHappyMeal_Label)
				& Base.isElementDisplayed(storeLedgerDetailPage.StoreLedgerTable_NonProductOther_Label)
				& Base.isElementDisplayed(storeLedgerDetailPage.StoreLedgerTable_Tax1_Label)
				& Base.isElementDisplayed(storeLedgerDetailPage.StoreLedgerTable_Tax2_Label)
				& Base.isElementDisplayed(storeLedgerDetailPage.StoreLedgerTable_Tax3_Label)
				& Base.isElementDisplayed(storeLedgerDetailPage.StoreLedgerTable_StateTax_Label);
		System.out.println("storeLedgerDetailDisplayedForMonth "+storeLedgerDetailDisplayedForMonth);
		// verify that purchase is posted in store Ledger Page records
		boolean dataPostedInStoreLedgerPage = storeLedgerDetailPage.verifyDataForSelectedMonth(Month, year);
		if (storeLedgerDetailDisplayedForMonth & dataPostedInStoreLedgerPage) {
			Reporter.reportPassResult(
					browser,"Sprint2_US345_TC536",
					"User should be able to view data for selected month on Store Ledgere detail Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint2_US345_TC536","Sprint2_US345_TC536",
					"User should be able to view data for selected month on Store Ledgere detail Page",
					"Fail");
		}
	}

}
