package sprint2;

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

import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.ManualInvoiceEditPage;
import eInventoryPageClasses.ManualInvoiceNewPage;
import eInventoryPageClasses.PurchasesPage;
import eInventoryPageClasses.StoreLedgerDetailPage;

public class US179_PostAManualPurchase extends AbstractTest {
	
	/* ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''' */
	// TC530 Verify that User is able to view the Posted Manual Invoice.
	@Test()
	public void Sprint2_US179_TC530() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/* Start-Variable Deceleration */
		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint2_US179_TC530", "Object1");
		String wrinId = ReadTestData.getTestData(manualInvoiceNewPageSheet,"EnterQuickSearchWithSuggestionsForManualPurchases");
		String vendor = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Vendor");
		String quantity = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Quantity");
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		PurchasesPage purchasePage;
		String invoiceId = Base.randomNumberFiveDigit();
		/* End-Variable Deceleration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Top verify that manual invoice is present on the system or not
		purchasePage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPurchaseLandingPage();
		//Create a new manual purchase
		ManualInvoiceNewPage manualInvoiceNewPage = purchasePage.goToManualInvoiceNewPage()
						.createAManualPurchaseForWrinID(wrinId, vendor,quantity, invoiceId);
		String totalTransactionValue = manualInvoiceNewPage.TotalPurchases_Value.getText().substring(1);
		String totalFoodValue = manualInvoiceNewPage.TotalFood_Value.getText().substring(1);
		// Click on Menu Btn
		homePage.Menu_DD_BT.click();
		// Navigate to physical Inventory and click on the saved inventory with status "In-Progress".
		purchasePage.goToPurchaseLandingPage().clickOntheInvoice(invoiceId);
		//Post the newly created manual purchase
		manualInvoiceNewPage.clickOnFinalizeButton().postTheManualPurchage();
		Thread.sleep(5000);
		//Navigate to Store Ledger Detail Page
		StoreLedgerDetailPage storeLedgerDetailPage = purchasePage.clickOnViewStoreLedgerButton();
		// get current date, month and year from the system date
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Calendar cal1 = Calendar.getInstance();
		String date = dateFormat.format(cal1.getTime());
		// verify that purchase is posted in store Ledger Page records
		boolean dataPostedInStoreLedgerPage = storeLedgerDetailPage
				.verifyDataIsPostedInStoreLedgerPage(date, invoiceId, totalTransactionValue, totalFoodValue);
		if (dataPostedInStoreLedgerPage) {
				Reporter.reportPassResult(
						browser,"Sprint2_US179_TC530",
						"User should be able to find the invoice id and transaction details on store ledger page for vendor",
						"Pass");
			} else {
				Reporter.reportTestFailure(
						browser,"Sprint2_US179_TC530","Sprint2_US179_TC530",
						"User should be able to find the invoice id and transaction details on store ledger page for vendor",
						"Fail");
				AbstractTest.takeSnapShot("Sprint2_US179_TC530");
			}
	}
	
	/*'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	//TC452 Verify that User is able to view the "Finalize" Button on Manual Purchase Detail Screen
	@Test()
	public void Sprint2_US179_TC452() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/* Start-Variable Deceleration */
		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint2_US179_TC452", "Object1");
		String wrinId = ReadTestData.getTestData(manualInvoiceNewPageSheet,"EnterQuickSearchWithSuggestionsForManualPurchases");
		String vendor = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Vendor");	
		String quantity = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Quantity");
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String invoiceNumber = Base.randomNumberFiveDigit();
		PurchasesPage purchasesPage;
		/* End-Variable Deceleration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Top verify that manual invoice is present on the system or not
		purchasesPage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPurchaseLandingPage();
		// Create a new manual purchase
		purchasesPage.goToManualInvoiceNewPage().createAManualPurchaseForWrinID(wrinId, vendor, quantity,invoiceNumber);
		// Click on Menu Btn
		homePage.Menu_DD_BT.click();
		// Navigate to physical Inventory and click on the saved inventory with status "In-Progress".
		ManualInvoiceEditPage manualInvoiceEditPage = purchasesPage	.goToPurchaseLandingPage().clickOntheInvoice(invoiceNumber);
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceEditPage.Finalize_BT));
		if (manualInvoiceEditPage.Finalize_BT.isDisplayed()) {
			Reporter.reportPassResult(
					browser, "Sprint2_US179_TC452",
					"Finalize button should display on the Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "Sprint2_US179_TC452","Sprint2_US179_TC452",
					"Finalize button should display on the Page",
					"Fail");
			AbstractTest.takeSnapShot("Sprint2_US179_TC452");
		}
	}
	
	/*'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	//Verify that User is able to post the pending manual invoice
	@Test()
	public void Sprint2_US179_TC453() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/* Start-Variable Deceleration */
		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint2_US179_TC452", "Object1");
		String wrinId = ReadTestData.getTestData(manualInvoiceNewPageSheet,"EnterQuickSearchWithSuggestionsForManualPurchases");
		String vendor = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Vendor");
		String quantity = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Quantity");
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String invoiceNumber = Base.randomNumberFiveDigit();
		PurchasesPage purchasesPage;
		/* End-Variable Deceleration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		purchasesPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPurchaseLandingPage();
		// Create a manual Purchase
		purchasesPage.goToManualInvoiceNewPage().createAManualPurchaseForWrinID(wrinId, vendor, quantity,invoiceNumber);
		// Click on Menu Btn
		homePage.Menu_DD_BT.click();
		// //Click on newly created invoice
		ManualInvoiceEditPage manualInvoiceEditPage = purchasesPage.goToPurchaseLandingPage().clickOntheInvoice(invoiceNumber);
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceEditPage.Finalize_BT));
		manualInvoiceEditPage.Finalize_BT.click();
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceEditPage.FinalizePopUp_Continue_BT));
		manualInvoiceEditPage.FinalizePopUp_Continue_BT.click();
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceEditPage.InvoiceFinalize_Confirmation_Message));
		/*
		 * A success message "Your invoice has been posted" should be displayed to user at top right corner of screen. User should be navigated back
		 * to purchases page Pending manual invoice saved on date 'D' should be removed from pending purchases section so that it cannot be edited.
		 */
		if (manualInvoiceEditPage.InvoiceFinalize_Confirmation_Message.isDisplayed()) {
			Reporter.reportPassResult(
					browser, "Sprint2_US179_TC453",
					"Confirmation message should display",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint2_US179_TC453_Condition1", "Sprint2_US179_TC453",
					"Confirmation message should display",
					"Fail");
			AbstractTest.takeSnapShot("Sprint2_US179_TC453_Condition1");
		}
		if (purchasesPage.PendingPurchases_Title.isDisplayed() && !(purchasesPage.verifyPendindInvoiceIsPresent(invoiceNumber))) {
			Reporter.reportPassResult(
					browser,"Sprint2_US179_TC453",
					"User should be moved back to the Purchase landing page and invoice should be moved from pending purchage page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint2_US179_TC453_Condition2","Sprint2_US179_TC453_Condition2",
					"User should be moved back to the Purchase landing page and invoice should be moved from pending purchage page",
					"Fail");
			AbstractTest.takeSnapShot("Sprint2_US179_TC453_Condition2");
		}
	}
}
