package sprint2;

import java.io.IOException;

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
import eInventoryPageClasses.PurchasesPage;

public class US186_EditManualPurchase extends AbstractTest {
	
	// Verify that the User is able to view pending manual invoices under pending purchases section on the Purchases Landing page
	@Test()
	public void Sprint2_US186_TC411() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/* Start-Variable Deceleration */
		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint2_US186_TC411", "Object1");
		String wrinId = ReadTestData.getTestData(manualInvoiceNewPageSheet,"EnterQuickSearchWithSuggestionsForManualPurchases");
		String vendor = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Vendor");
		String quantity = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Quantity");
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String invoiceId = Base.randomNumberFiveDigit();
		PurchasesPage purchasePage;
		/* End-Variable Deceleration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// navigate to purchase landing page
		purchasePage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPurchaseLandingPage();
		// Create a new manual purchase
		purchasePage.goToManualInvoiceNewPage().createAManualPurchaseForWrinID(wrinId, vendor, quantity, invoiceId);
		// Click on Menu Btn
		homePage.Menu_DD_BT.click();
		// Verify that Pending invoice is present or not
		if (purchasePage.goToPurchaseLandingPage().verifyPendindInvoiceIsPresent(invoiceId)) {
			Reporter.reportPassResult(
					browser, "Sprint2_US186_TC411",
					"Pending Invoice should display",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "Sprint2_US186_TC411","Sprint2_US186_TC411",
					"Pending Invoice should display",
					"Fail");
			AbstractTest.takeSnapShot("Sprint2_US186_TC411");
		}
	}
	
	/* 	Verify that below fields are read only to user on pending manual invoice page:
		-Vendor,-Invoice Date, -Invoice Time, -Invoice number*/
	@Test()
	public void Sprint2_US186_TC1540() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/* Start-Variable Deceleration */
		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint2_US186_TC1540", "Object1");
		String wrinId = ReadTestData.getTestData(manualInvoiceNewPageSheet,"EnterQuickSearchWithSuggestionsForManualPurchases");
		String vendor = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Vendor");
		String quantity = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Quantity");
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String invoiceNumber = Base.randomNumberFiveDigit();
		PurchasesPage purchasesPage;
		/* End-Variable Deceleration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// navigate to purchase landing page
		purchasesPage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPurchaseLandingPage();
		// Create a manual Purchase
		purchasesPage.goToManualInvoiceNewPage().createAManualPurchaseForWrinID(wrinId, vendor, quantity,invoiceNumber);
		// Click on Menu Btn
		homePage.Menu_DD_BT.click();
		// Click on newly created invoice
		ManualInvoiceEditPage manualInvoiceEditPage = purchasesPage.goToPurchaseLandingPage().clickOntheInvoice(invoiceNumber);
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceEditPage.ManualInvoice_Edit_Label));
		String actInvoiceNumber = manualInvoiceEditPage.InvoiceNumber_TB.getAttribute("value");
		String actinvoiceDate = manualInvoiceEditPage.invoiceDate_TB.getAttribute("value");
		String date = Base.returnTodayDate();
		if (actInvoiceNumber.equalsIgnoreCase(invoiceNumber) && actinvoiceDate.equalsIgnoreCase(date)) {
			Reporter.reportPassResult(
					browser, "Sprint2_US186_TC1540",
					"Pending Invoice should display",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "Sprint2_US186_TC1540","Sprint2_US186_TC1540", 
					"Pending Invoice should display",
					"Fail");
			AbstractTest.takeSnapShot("Sprint2_US186_TC1540");
		}
	}
	
	// Verify that below Columns are present on pending manual invoice page:
	@Test()
	public void Sprint2_US186_TC1541() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/* Start-Variable Deceleration */
		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint2_US186_TC1541", "Object1");
		String wrinId = ReadTestData.getTestData(manualInvoiceNewPageSheet,"EnterQuickSearchWithSuggestionsForManualPurchases");
		String vendor = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Vendor");
		String quantity = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Quantity");
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String invoiceId = Base.randomNumberFiveDigit();
		PurchasesPage purchasePage;
		/* End-Variable Deceleration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// navigate to purchase landing page
		purchasePage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPurchaseLandingPage();
		// Create a new manual purchase
		purchasePage.goToManualInvoiceNewPage().createAManualPurchaseForWrinID(wrinId, vendor, quantity, invoiceId);
		// Click on Menu Btn
		homePage.Menu_DD_BT.click();
		// //Click on newly created invoice
		ManualInvoiceEditPage manualInvoiceEditPage = purchasePage.goToPurchaseLandingPage().clickOntheInvoice(invoiceId);
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceEditPage.ManualInvoice_Edit_Label));
		/*
		 * Verify that Following columns are displaying or not -WRIN
		 * ,DESCRIPTION,UOM,UOM/CASE,$/CASE,QUANTITY,SUB-TOTAL,REMOVE
		 */
		if (Base.isElementDisplayed(manualInvoiceEditPage.WRIN_Column_Label)
				& Base.isElementDisplayed(manualInvoiceEditPage.Description_Column_Label)
				& Base.isElementDisplayed(manualInvoiceEditPage.UOM_Column_Label)
				& Base.isElementDisplayed(manualInvoiceEditPage.UOMCase_Column_Label)
				& Base.isElementDisplayed(manualInvoiceEditPage.DollerCase_Column_Label)
				& Base.isElementDisplayed(manualInvoiceEditPage.Quantity_Column_Label)
				& Base.isElementDisplayed(manualInvoiceEditPage.SubTotal_Column_Label)
				& Base.isElementDisplayed(manualInvoiceEditPage.Remove_Column_Label)) {
			Reporter.reportPassResult(
					browser, "Sprint2_US186_TC1541",
					"Pending Invoice should display",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "Sprint2_US186_TC1541","Sprint2_US186_TC1541",
					"Pending Invoice should display",
					"Fail");
			AbstractTest.takeSnapShot("Sprint2_US186_TC1541");
		}
	}
	
	// Verify that user is able to edit and save the existing pending manual invoice
	@Test()
	public void Sprint2_US186_TC1542() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/* Start-Variable Deceleration */
		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint2_US186_TC1542", "Object1");
		String wrinId = ReadTestData.getTestData(manualInvoiceNewPageSheet,"EnterQuickSearchWithSuggestionsForManualPurchases");
		String vendor = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Vendor");
		String quantity = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Quantity");
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String invoiceId = Base.randomNumberFiveDigit();
		PurchasesPage purchasesPage;
		/* End-Variable Deceleration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// navigate to purchase landing page
		purchasesPage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPurchaseLandingPage();
		// Create a new manual purchase
		purchasesPage.goToManualInvoiceNewPage().createAManualPurchaseForWrinID(wrinId, vendor, quantity,invoiceId);
		// Click on Menu Btn
		homePage.Menu_DD_BT.click();
		// Click on newly created invoice
		ManualInvoiceEditPage manualInvoiceEditPage = purchasesPage.goToPurchaseLandingPage().clickOntheInvoice(invoiceId);
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceEditPage.ManualInvoice_Edit_Label));
		manualInvoiceEditPage.Quantity_TB.get(0).clear();
		manualInvoiceEditPage.Quantity_TB.get(0).sendKeys("3");
		manualInvoiceEditPage.Save_BT.click();
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceEditPage.InvoiceSave_Confirmation_Message));
		if (manualInvoiceEditPage.InvoiceSave_Confirmation_Message.isDisplayed()) {
			Reporter.reportPassResult(
					browser, "Sprint2_US186_TC1542",
					"Confirmation message should display",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "Sprint2_US186_TC1542","Sprint2_US186_TC1542",
					"Confirmation message should display",
					"Fail");
			AbstractTest.takeSnapShot("Sprint2_US186_TC1542");
		}
	}

	/* Verify that below buttons are present on pending manual invoice page:
		-Cancel button
		-Delete button
		-Save button
		-Finalize button*/
	@Test()
	public void Sprint2_US186_TC1543() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/* Start-Variable Deceleration */
		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint2_US186_TC1543", "Object1");
		String wrinId = ReadTestData.getTestData(manualInvoiceNewPageSheet,"EnterQuickSearchWithSuggestionsForManualPurchases");
		String vendor = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Vendor");
		String quantity = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Quantity");
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String invoiceId = Base.randomNumberFiveDigit();
		PurchasesPage purchasesPage;
		/* End-Variable Deceleration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// navigate to purchase landing page
		purchasesPage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPurchaseLandingPage();
		// Create a new manual purchase
		purchasesPage.goToManualInvoiceNewPage().createAManualPurchaseForWrinID(wrinId, vendor, quantity,invoiceId);
		// Click on Menu Btn
		homePage.Menu_DD_BT.click();
		// Click on newly created invoice
		ManualInvoiceEditPage manualInvoiceEditPage = purchasesPage.goToPurchaseLandingPage().clickOntheInvoice(invoiceId);
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceEditPage.ManualInvoice_Edit_Label));
		/*
		 * Verify the following buttons are displaying or not -Cancel button
		 * ,-Delete button, -Save button,-Finalize button
		 */
		if (Base.isElementDisplayed(manualInvoiceEditPage.Cancel_BT)
				& Base.isElementDisplayed(manualInvoiceEditPage.Delete_BT)
				& Base.isElementDisplayed(manualInvoiceEditPage.Save_BT)
				& Base.isElementDisplayed(manualInvoiceEditPage.Finalize_BT)) {
			Reporter.reportPassResult(
					browser, "Sprint2_US186_TC1543",
					"All the button should display",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "Sprint2_US186_TC1543","Sprint2_US186_TC1543",
					"All the button should display",
					"Fail");
			AbstractTest.takeSnapShot("Sprint2_US186_TC1543");
		}
	}

	// Verify that user is able to add new WRIN items to existing pending manual invoice.
	@Test()
	public void Sprint2_US186_TC1544() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/* Start-Variable Deceleration */
		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint2_US186_TC1544", "Object1");
		String wrinId = ReadTestData.getTestData(manualInvoiceNewPageSheet,"EnterQuickSearchWithSuggestionsForManualPurchases");
		String vendor = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Vendor");
		String quantity = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Quantity");
		HSSFSheet manualInvoiceNewPageSheet1 = ReadTestData.getTestDataSheet("Sprint2_US186_TC1544", "Object2");
		String wrinId1 = ReadTestData.getTestData(manualInvoiceNewPageSheet1,"EnterQuickSearchWithSuggestionsForManualPurchases");
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String invoiceId = Base.randomNumberFiveDigit();
		PurchasesPage purchasesPage;

		/* End-Variable Deceleration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// navigate to purchase landing page
		purchasesPage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPurchaseLandingPage();
		// Create a new manual purchase
		purchasesPage.goToManualInvoiceNewPage().createAManualPurchaseForWrinID(wrinId, vendor, quantity,invoiceId);
		// Click on Menu Btn
		homePage.Menu_DD_BT.click();
		// Click on newly created invoice
		ManualInvoiceEditPage manualInvoiceEditPage = purchasesPage.goToPurchaseLandingPage().clickOntheInvoice(invoiceId);
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceEditPage.ManualInvoice_Edit_Label));
		int size1 = manualInvoiceEditPage.getNumberOfItemInPurchase();
		manualInvoiceEditPage.searchAndSelectARawItem(wrinId1);
		Thread.sleep(2000);
		int size2 = manualInvoiceEditPage.getNumberOfItemInPurchase();
		if (size2 == size1 + 1) {
			Reporter.reportPassResult(
					browser, "Sprint2_US186_TC1544",
					"User should be able to add the WRIN ID", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "Sprint2_US186_TC1544","Sprint2_US186_TC1544",
					"User should be able to add the WRIN ID",
					"Fail");
			AbstractTest.takeSnapShot("Sprint2_US186_TC1544");
		}
	}
 	
	// Verify that user is able to remove WRIN items from pending manual invoice.
	@Test()
	public void Sprint2_US186_TC1545() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/* Start-Variable Deceleration */
		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint2_US186_TC1545", "Object1");
		String wrinId = ReadTestData.getTestData(manualInvoiceNewPageSheet,"EnterQuickSearchWithSuggestionsForManualPurchases");
		String vendor = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Vendor");
		String quantity = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Quantity");
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String invoiceId = Base.randomNumberFiveDigit();
		PurchasesPage purchasesPage;
		/* End-Variable Deceleration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// navigate to purchase landing page
		purchasesPage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPurchaseLandingPage();
		// Create a new manual purchase
		purchasesPage.goToManualInvoiceNewPage().createAManualPurchaseForWrinID(wrinId, vendor, quantity,invoiceId);
		// Click on Menu Btn
		homePage.Menu_DD_BT.click();
		// Click on newly created invoice
		ManualInvoiceEditPage manualInvoiceEditPage = purchasesPage
				.goToPurchaseLandingPage().clickOntheInvoice(invoiceId);
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceEditPage.ManualInvoice_Edit_Label));
		int size1 = manualInvoiceEditPage.getNumberOfItemInPurchase();
		// Remove the Item
		manualInvoiceEditPage.Remove_Link_List.get(0).click();
		Thread.sleep(5000);
		int size2 = manualInvoiceEditPage.getNumberOfItemInPurchase();
		if (size2 == size1 - 1 || Base.isElementDisplayed(manualInvoiceEditPage.NoSearchResultsFound_Message_Label)) {
			Reporter.reportPassResult(
					browser, "Sprint2_US186_TC1545",
					"Record should be removed from the list",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "Sprint2_US186_TC1545","Sprint2_US186_TC1545",
					"Record should be removed from the list",
					"Fail");
			AbstractTest.takeSnapShot("Sprint2_US186_TC1545");
		}
	}
	
	// Verify that user is able to post the existing pending manual invoice.
	@Test()
	public void Sprint2_US186_TC1546() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/* Start-Variable Deceleration */
		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint2_US186_TC1546", "Object1");
		String wrinId = ReadTestData.getTestData(manualInvoiceNewPageSheet,"EnterQuickSearchWithSuggestionsForManualPurchases");
		String vendor = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Vendor");
		String quantity = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Quantity");
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String invoiceId = Base.randomNumberFiveDigit();
		PurchasesPage purchasesPage;
		/* End-Variable Deceleration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// navigate to purchase landing page
		purchasesPage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPurchaseLandingPage();
		// Create a new manual purchase
		purchasesPage.goToManualInvoiceNewPage().createAManualPurchaseForWrinID(wrinId, vendor, quantity,invoiceId);
		// Click on Menu Btn
		homePage.Menu_DD_BT.click();
		// Click on newly created invoice
		ManualInvoiceEditPage manualInvoiceEditPage = purchasesPage.goToPurchaseLandingPage().clickOntheInvoice(invoiceId);
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceEditPage.ManualInvoice_Edit_Label));
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
					browser, "Sprint2_US186_TC1546",
					"Confirmation message should display",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint2_US186_TC1546_Condition1", "Sprint2_US186_TC1546",
					"Confirmation message should display",
					"Fail");
			AbstractTest.takeSnapShot("Sprint2_US186_TC1546_Condition1");
		}
		wait.until(ExpectedConditions.visibilityOf(purchasesPage.PendingPurchases_Title));
		if (purchasesPage.PendingPurchases_Title.isDisplayed()&& !purchasesPage.verifyPendindInvoiceIsPresent(invoiceId)) {
			Reporter.reportPassResult(
					browser,"Sprint2_US186_TC1546",
					"User should be moved back to the Purchase landing page and invoice should be moved from pending purchage page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint2_US186_TC1546_Condition2","Sprint2_US186_TC1546",
					"User should be moved back to the Purchase landing page and invoice should be moved from pending purchage page",
					"Fail");
			AbstractTest.takeSnapShot("Sprint2_US186_TC1546_Condition2");
		}
	}

	// Verify that user is able to delete the existing pending manual invoice.
	@Test()
	public void Sprint2_US186_TC1547() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/* Start-Variable Deceleration */
		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint2_US186_TC1547", "Object1");
		String wrinId = ReadTestData.getTestData(manualInvoiceNewPageSheet,"EnterQuickSearchWithSuggestionsForManualPurchases");
		String vendor = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Vendor");
		String quantity = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Quantity");
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String invoiceId = Base.randomNumberFiveDigit();
		PurchasesPage purchasesPage;
		/* End-Variable Deceleration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// navigate to purchase landing page
		purchasesPage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPurchaseLandingPage();
		// Create a new manual purchase
		purchasesPage.goToManualInvoiceNewPage().createAManualPurchaseForWrinID(wrinId, vendor, quantity,invoiceId);
		// Click on Menu Btn
		homePage.Menu_DD_BT.click();
		// Click on newly created invoice
		ManualInvoiceEditPage manualInvoiceEditPage = purchasesPage.goToPurchaseLandingPage().clickOntheInvoice(invoiceId);
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceEditPage.ManualInvoice_Edit_Label));
		// Click on delete button
		manualInvoiceEditPage.Delete_BT.click();
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceEditPage.DeletePopup_OK_BT));
		manualInvoiceEditPage.DeletePopup_OK_BT.click();
		wait.until(ExpectedConditions.visibilityOf(purchasesPage.PendingPurchases_Title));
		/*
		 * Invoice should be deleted after clicking on OK button User should be
		 * navigated back to purchases page Pending manual invoice saved on date
		 * 'D' should be removed from pending purchases section
		 */
		if (purchasesPage.PendingPurchases_Title.isDisplayed() && !purchasesPage.verifyPendindInvoiceIsPresent(invoiceId)) {
			Reporter.reportPassResult(
					browser,"Sprint2_US186_TC1547",
					"User should be moved back to the Purchase landing page and invoice should be moved from pending purchage page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint2_US186_TC1547","Sprint2_US186_TC1547",
					"User should be moved back to the Purchase landing page and invoice should be moved from pending purchage page",
					"Fail");
			AbstractTest.takeSnapShot("Sprint2_US186_TC1547");
		}
	}
}
