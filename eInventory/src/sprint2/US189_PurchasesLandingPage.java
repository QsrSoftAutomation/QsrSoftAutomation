package sprint2;

import java.io.IOException;
import java.text.ParseException;

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
import eInventoryPageClasses.ManualInvoiceNewPage;
import eInventoryPageClasses.PurchasesPage;

public class US189_PurchasesLandingPage extends AbstractTest

{
	// TC469 Verify that the User is able to view all the pending purchase on the Purchases Landing page
	@Test()
	public void Sprint2_US189_TC469() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/* Start-Variable Deceleration */
		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint2_US189_TC469", "Object1");
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
		purchasePage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPurchaseLandingPage();
		// Create a new manual purchase
		purchasePage.goToManualInvoiceNewPage().createAManualPurchaseForWrinID(wrinId, vendor, quantity, invoiceId);
		// Click on Menu Btn
		homePage.Menu_DD_BT.click();
		// Verify that Pending invoice is present or not
		if (purchasePage.goToPurchaseLandingPage().verifyPendindInvoiceIsPresent(invoiceId)) {
			Reporter.reportPassResult(
					browser,"Sprint2_US189_TC469",
					"Pending purchase should be displayed on purchase landing page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint2_US189_TC469","Sprint2_US189_TC469",
					"Pending purchase should be displayed on purchase landing page",
					"Fail");
			AbstractTest.takeSnapShot("Sprint2_US189_TC469");
		}
	}

	/* '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''' */
	// TC471 Verify that user is able to enter a new Manual Purchase from Purchase landing page
	@Test()
	public void Sprint2_US189_TC471() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/* Start-Variable Deceleration */
		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint2_US189_TC471", "Object1");
		String wrinId = ReadTestData.getTestData(manualInvoiceNewPageSheet,"EnterQuickSearchWithSuggestionsForManualPurchases");
		String vendor = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Vendor");
		String quantity = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Quantity");
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String invoiceId = Base.randomNumberFiveDigit();
		PurchasesPage purchasePage = null;
		/* End-Variable Deceleration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// navigate to purchase landing page
		purchasePage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPurchaseLandingPage();
		// Create a new manual purchase
		purchasePage.goToManualInvoiceNewPage().createAManualPurchaseForWrinID(wrinId, vendor, quantity, invoiceId);
		// Click on Menu Btn
		homePage.Menu_DD_BT.click();
		wait.until(ExpectedConditions.visibilityOf(purchasePage.goToPurchaseLandingPage().PurchaseForStoreNumber_Title));
		// Find the number of records after the invoice is created
		if (purchasePage.verifyPendindInvoiceIsPresent(invoiceId)) {
			Reporter.reportPassResult(
					browser,"Sprint2_US189_TC471",
					"Added Manual Invoice should be updated in Purchase Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint2_US189_TC471","Sprint2_US189_TC471",
					"Added Manual Invoice should be updated in Purchase Landing Page",
					"Fail");
			AbstractTest.takeSnapShot("Sprint2_US189_TC471");
		}
	}
	
	/* ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''' */
	// TC473 Verify that Purchase History List is available on the Purchases Landing Page.
	@Test()
	public void Sprint2_US189_TC473() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/* Start-Variable Deceleration */
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/* End-Variable Deceleration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Go to Purchase Landing page and verify that all the content is displaying properly including Purchase History Section.
		boolean result = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPurchaseLandingPage().isPurchaseLandingPageLoaded();
		if (result) {
			Reporter.reportPassResult(
					browser, "Sprint2_US189_TC473",
					"Purchage landing page should be loaded successfully",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "Sprint2_US189_TC473","Sprint2_US189_TC473",
					"Purchage landing page should be loaded successfully",
					"Fail");
			AbstractTest.takeSnapShot("Sprint2_US189_TC473");
		}
	}
	
	/* ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''' */
	// TC474 Verify that User is able to view the Posted Purchases list.
	@Test()
	public void Sprint2_US189_TC474() throws ParseException,
			RowsExceededException, BiffException, WriteException, IOException,
			InterruptedException {
		/* Start-Variable Deceleration */
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/* End-Variable Deceleration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Go to Purchase landing Page
		PurchasesPage purchasePage = homePage.selectUser(userId)
				.selectLocation(storeId).navigateToInventoryManagement().goToPurchaseLandingPage();
		// Fetch the selected start date
		String date1 = purchasePage.StartDate_BT.getAttribute("value");
		// Fetch the selected end date
		String date2 = purchasePage.EndDate_BT.getAttribute("value");
		// click on GetPurchageHistory button
		purchasePage.GetPurchaseHistory_BT.click();
		// Verify that only the records between the start and end date is displaying
		if (purchasePage.verifyPurchaseHistoryDisplayedForSelectedDateRange(date1, date2)) {
			Reporter.reportPassResult(
					browser,"Sprint2_US189_TC474",
					"Only posted purchase of selected date range should display",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint2_US189_TC474_Condition1","Sprint2_US189_TC474",
					"Only posted purchase of selected date range should display",
					"Fail");
			AbstractTest.takeSnapShot("Sprint2_US189_TC474_Condition1");
		}
		// verify records are showing in ascending order
		if (purchasePage.verifyPurchaseHistoryRecordsAreInAscendingOrder()) {
			Reporter.reportPassResult(
					browser, "Sprint2_US189_TC474",
					"Records should be displayed in descending order of date",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint2_US189_TC474_Condition2", "Sprint2_US189_TC474",
					"Records should be displayed in descending order of date",
					"Fail");
			AbstractTest.takeSnapShot("Sprint2_US189_TC474_Condition2");
		}
	}

	/*'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	// TC475 Verify that User is able to view the Posted Purchases Details.
	@Test()
	public void Sprint2_US189_TC475() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/* Start-Variable Deceleration */
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/* End-Variable Deceleration */
		/* Start-Fetch the data From Test Data Sheet */
		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint2_US189_TC475", "Object1");
		String wrinId = ReadTestData.getTestData(manualInvoiceNewPageSheet,"EnterQuickSearchWithSuggestionsForManualPurchases");
		String vendor = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Vendor");
		String quantity = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Quantity");
		/* End */
		String invoiceNumber = Base.randomNumberFiveDigit();
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		PurchasesPage purchasesPage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPurchaseLandingPage();
		// Go to manual invoice new page then select a vendor then add a row item
		ManualInvoiceNewPage manualInvoiceNewPage = purchasesPage.goToManualInvoiceNewPage().selectAVendorFromDropdown(vendor)
				.searchAndSelectARawItem(wrinId).enterQuantityInQuantityTextBox(wrinId, quantity);
		// Fetch the invoice date ,wrinId,description ,UOM and UOM/Case value for the added item
		String invoiceDate = manualInvoiceNewPage.InvoiceDate_TB.getAttribute("value");
		String wrinID = manualInvoiceNewPage.InvoiceTable_WrinId_Value.getText().trim();
		String description = manualInvoiceNewPage.InvoiceTable_Description_Value.getText().trim();
		String uom = manualInvoiceNewPage.InvoiceTable_Uom_Value.getText().trim();
		String uomCase = manualInvoiceNewPage.InvoiceTable_UomCase_Value.getText().trim();
		manualInvoiceNewPage.InvoiceNumber_TB.sendKeys(invoiceNumber);
		manualInvoiceNewPage.clickOnFinalizeButton().FinalizePopUp_Continue_BT.click();
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceNewPage.InvoiceFinalize_Confirmation_MSG));
		purchasesPage.GetPurchaseHistory_BT.click();
		// Wait until the purchase history table is loaded
		wait.until(ExpectedConditions.visibilityOfAllElements(purchasesPage.postedPurchase_List));
		// click on the last record of the purchase history table because newly added record display at last
		String todayDate = Base.returnTodayDate();
		purchasesPage.clickOnPostedPurchaseRecord(todayDate, invoiceNumber);
		// wait until posted purchase detail page is opened
		wait.until(ExpectedConditions.visibilityOf(purchasesPage.PostedPurchasePopUp_Title));
		// Verify correct vendor is showing
		if (purchasesPage.PostedPurchaseDetailPopUp_VendorName_Value.getText().trim().equalsIgnoreCase(vendor)) {
			Reporter.reportPassResult(
					browser, "Sprint2_US189_TC475",
					"correct vendor should be displayed",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint2_US189_TC475_Condition1", "Sprint2_US189_TC475",
					"correct vendor should be displayed",
					"Fail");
			AbstractTest.takeSnapShot("Sprint2_US189_TC475_Condition1");
		}	
		// Verify that Invoice ID,Invoice Date and Delivery Date label are present
		if (Base.isElementDisplayed(purchasesPage.PostedPurchaseDetailPopUp_InvoiceId_Label)
				& Base.isElementDisplayed(purchasesPage.PostedPurchaseDetailPopUp_InvoiceDate_Label)
				& Base.isElementDisplayed(purchasesPage.PostedPurchaseDetailPopUp_DeliveryDate_Label)) {
			Reporter.reportPassResult(
					browser,"Sprint2_US189_TC475",
					"Invoice ID,Invoice Date,Delivery Date Labels should be present",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint2_US189_TC475_Condition2","Sprint2_US189_TC475",
					"Invoice ID,Invoice Date,Delivery Date Labels should be present",
					"Fail");
			AbstractTest.takeSnapShot("Sprint2_US189_TC475_Condition2");
		}
		// Verify that invoice date and delivery date are same that is equal to invoice creation date
		if (purchasesPage.PostedPurchaseDetailPopUp_InvoiceDate_Value.getText().trim().equalsIgnoreCase(invoiceDate)
				& purchasesPage.PostedPurchaseDetailPopUp_DeliveryDate_Value.getText().trim().equalsIgnoreCase(invoiceDate)) {
			Reporter.reportPassResult(
					browser, "Sprint2_US189_TC475",
					"correct Invoice & Delivery date should be displayed",
					"Pass");
		}else {
			Reporter.reportTestFailure(
					browser,"Sprint2_US189_TC475_Condition3", "Sprint2_US189_TC475",
					"correct Invoice & Delivery date should be displayed",
					"Fail");
			AbstractTest.takeSnapShot("Sprint2_US189_TC475_Condition3");
		}
		// Very the following column headers WRIN ,Description,UOM,UOM/case,Case Price,Cases Purchased and Cost
		if (Base.isElementDisplayed(purchasesPage.PostedPurchaseDetailPopUp_WRINColumn_Label)
				& Base.isElementDisplayed(purchasesPage.PostedPurchaseDetailPopUp_descriptionColumn_Label)
				& Base.isElementDisplayed(purchasesPage.PostedPurchaseDetailPopUp_UOMColumn_Label)
				& Base.isElementDisplayed(purchasesPage.PostedPurchaseDetailPopUp_UOMCaseColumn_Label)
				& Base.isElementDisplayed(purchasesPage.PostedPurchaseDetailPopUp_CasePriceColumn_Label)
				& Base.isElementDisplayed(purchasesPage.PostedPurchaseDetailPopUp_CasesPurchasedColumn_Label)
				& Base.isElementDisplayed(purchasesPage.PostedPurchaseDetailPopUp_costColumn_Label)) {
			Reporter.reportPassResult(
					browser,"Sprint2_US189_TC475",
					"wRIN,description,UOM,UOM/Case,Case Price,Cases Purchased & Cost column header should be displayed ",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint2_US189_TC475_Condition4","Sprint2_US189_TC475",
					"wRIN,description,UOM,UOM/Case,Case Price,Cases Purchased & Cost column header should be displayed",
					"Fail");
			AbstractTest.takeSnapShot("Sprint2_US189_TC475_Condition4");
		}	
		// Verify the value of the following columns WrinID,Description,UOM and UOM/CASE
		if (purchasesPage.verifyRawItemValuesInPostesPurchase(1, wrinID,description, uom, uomCase)) {
			Reporter.reportPassResult(
					browser,"Sprint2_US189_TC475",
					"Correct wRIN ID,Description,UOM,UOM/Case should be displayed ",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint2_US189_TC475_Condition5","Sprint2_US189_TC475",
					"Correct wRIN ID,Description,UOM,UOM/Case should be displayed ",
					"Fail");
			AbstractTest.takeSnapShot("Sprint2_US189_TC475_Condition5");
		}	
		// Verify the Purchase Total label
		if (Base.isElementDisplayed(purchasesPage.PostedPurchaseDetailPopUp_TotalPurchases_Label)) {
			Reporter.reportPassResult(
					browser, "Sprint2_US189_TC475",
					"Total purchase label should be displayed",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint2_US189_TC475_Condition6", "Sprint2_US189_TC475",
					"Total Purchase label should be displayed",
					"Fail");
			AbstractTest.takeSnapShot("Sprint2_US189_TC475_Condition6");
		}	
		// verify the Purchase total amount is equal to the sum of cost for all records
		String cost = purchasesPage.getTotalPurchaseCostForPostedPurchase();
		if (purchasesPage.PostedPurchaseDetailPopUp_TotalPurchases_Value.getText().trim().contains(cost)) {
			Reporter.reportPassResult(
					browser, "Sprint2_US189_TC475",
					"correct price total should be displayed",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint2_US189_TC475_Condition7", "Sprint2_US189_TC475",
					"correct price total should be displayed",
					"Fail");
			AbstractTest.takeSnapShot("Sprint2_US189_TC475_Condition7");
		}
	}
}
