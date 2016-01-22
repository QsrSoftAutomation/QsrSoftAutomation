package sprint2;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import common.Base;
import common.GlobalVariable;
import common.ReadTestData;
import common.Reporter;
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.ManualInvoiceNewPage;
import eInventoryPageClasses.PurchasesPage;

public class US400_ListOfRawItemsOnTheManualPurchasePage extends AbstractTest {
	// TC518 Verify that User is able to view all "not-posted" pending purchases on Purchases Landing page.
	@Test()
	public void Sprint2_US400_TC518() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/* Start-Variable Deceleration */
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/* End-Variable Deceleration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		PurchasesPage purchasePage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPurchaseLandingPage();
		// Go to purchase landing page and verify that purchase landing page is loaded successfully.
		boolean result1 = purchasePage.isPurchaseLandingPageLoaded();
		if (result1) {
			Reporter.reportPassResult(
					browser, "Sprint2_US400_TC518",
					"Purchase Landing Page should be loaded successfully",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint2_US400_TC518_Condition1", "Sprint2_US400_TC518",
					"Purchase Landing Page should be loaded successfully",
					"Fail");
			AbstractTest.takeSnapShot("Sprint2_US400_TC518_Condition1");
		}
		// Verify date,type and vendor is present for all non posted electronic and manual invoice.
		boolean result2 = purchasePage.isDateTypeVendorPresentForAllPendingManualAndElectronicInvoice();
		if (result2) {
			Reporter.reportPassResult(
					browser,"Sprint2_US400_TC518",
					"Date,Type and Vendor columns should be display for all the non posted invoices",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint2_US400_TC518_Condition2","Sprint2_US400_TC518",
					"Date,Type and Vendor columns should be display for all the non posted invoices",
					"Fail");
			AbstractTest.takeSnapShot("Sprint2_US400_TC518_Condition2");
		}
	}
	 
	// TC519 Verify that user is able to create new manual purchase.
	@Test()
	public void Sprint2_US400_TC519() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/* Start-Variable Deceleration */
		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint2_US400_TC519", "Object1");
		String wrinId = ReadTestData.getTestData(manualInvoiceNewPageSheet,"EnterQuickSearchWithSuggestionsForManualPurchases");
		String vendor = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Vendor");
		String quantity = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Quantity");
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/* End-Variable Deceleration */
		// Moving to Manual Invoice new page
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		ManualInvoiceNewPage manualInvoiceNewPage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPurchaseLandingPage().goToManualInvoiceNewPage();
		// verify manual invoice new page
		if (manualInvoiceNewPage.isManualInvoiceNewPageIsLoaded()) {
			Reporter.reportPassResult(
					browser, "Sprint2_US400_TC519",
					"Manual Invoice New Page should be loaded successfully",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint2_US400_TC519_Condition1", "Sprint2_US400_TC519",
					"Manual Invoice New Page should be loaded successfully",
					"Fail");
			AbstractTest.takeSnapShot("Sprint2_US400_TC519_Condition1");
		}
		// Create a new manual invoice
		manualInvoiceNewPage.createAManualPurchaseForWrinID(wrinId, vendor,quantity, "");
		// verify the selected item is added to manual purchase section and user is able add a quantity and save
		if (Base.isElementDisplayed(manualInvoiceNewPage.InvoiceSaved_Confirmation_MSG)) {
			Reporter.reportPassResult(
					browser,"Sprint2_US400_TC519",
					"Selected Item should be added to manual purchase section and user should be able to add the quantity and save",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint2_US400_TC519_Condition2","Sprint2_US400_TC519",
					"Selected Item should be added to manual purchase section and user should be able to add the quantity and save",
					"Fail");
			AbstractTest.takeSnapShot("Sprint2_US400_TC519_Condition2");
		}
	}
}
