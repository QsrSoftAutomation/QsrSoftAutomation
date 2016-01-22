package sprint10;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import sprint2.AbstractTest;
import common.Base;
import common.GlobalVariable;
import common.ReadTestData;
import common.Reporter;
import eInventoryPageClasses.CustomRawItemListsPage;
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.ManualInvoiceEditPage;
import eInventoryPageClasses.ManualVendorsPage;
import eInventoryPageClasses.PurchasesPage;

public class US1046_AbilityToRestoreDeletedInvoicesManualVendorsAndCustomLists extends AbstractTest{
	
	//TC1800: Verify that the user should be able to restore a deleted custom list  from "Custom Raw Item List " page.
	@Test()
	public void sprint10_US1046_TC1800() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String rawItemListName = "Test" + Base.generateNdigitRandomNumber(3);
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Go to Custom raw Item list page
		CustomRawItemListsPage customRawItemListsPage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToCustomRawItemListsPage();
		//Create a new Custom List
		customRawItemListsPage.createACustomRawList(rawItemListName);
		//Delete the custom list
		customRawItemListsPage.deleteCustomRawList(rawItemListName);
		//Verify that custom list is deleted from the "Custom Raw Item List " page.
		boolean customListDeleted = customRawItemListsPage.verifyCustomListIsDisplayed(rawItemListName);
		//Restore the list
		customRawItemListsPage.restoreCustomList(rawItemListName);
		//Go to "Custom Raw Item List " page.
		homePage.Menu_DD_BT.click();
		homePage.goToCustomRawItemListsPage();
		//Verify that custom list is restored and displayed in "Custom Raw Item List " page.
		boolean customListRestored = customRawItemListsPage.verifyCustomListIsDisplayed(rawItemListName);
		if (!customListDeleted & customListRestored) {
			Reporter.reportPassResult(browser, "sprint10_US1046_TC1800",
					"User should be able to restore a deleted custom list  from 'Custom Raw Item List' page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint10_US1046_TC1800","sprint10_US1046_TC1800",
					"User should be able to restore a deleted custom list  from 'Custom Raw Item List' page",
					"Fail");
			AbstractTest.takeSnapShot("sprint10_US1046_TC1800");
		}
	}
	
	// TC1798: Verify that the user should be able to restore a deleted manual vendors from "manual vendors" page.
	@Test()
	public void sprint10_US1046_TC1798() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		ManualVendorsPage manualVendorsPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String vendorNumber = String.valueOf(Base.generateNdigitRandomNumber(4));
		String vendorName = "Testauto" + vendorNumber;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Manual Vendor page
		manualVendorsPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToManualVendorsPage();
		wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.AddVendor_BT));
		// Create a new vendor
		manualVendorsPage.createANewVendor(vendorName, vendorNumber);
		// Click on the Delete vendor button
		manualVendorsPage.deleteVendor_BT(vendorName).click();
		wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.DeleteVendorPopUp_Confirmation_BT));
		// Click on delete button
		manualVendorsPage.DeleteVendorPopUp_Confirmation_BT.click();
		wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.DeleteVendorPopUp_VendorDeleted_Message));
		Thread.sleep(5000);
		// Restore manual vendor
		manualVendorsPage.restoreManualVendor(vendorName);
		// Click on Menu Btn
		homePage.goBackToInventoryManagementMenu();
		// Verify that manual vendor should be restored and displayed in manual vendor page
		if (manualVendorsPage.goToManualVendorsPage().verifyVendorIsRestored(vendorName)) {
			Reporter.reportPassResult(
					browser,"sprint10_US1046_TC1798",
					"User should be able to view the manual vendor=x restored.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint10_US1046_TC1798","sprint10_US1046_TC1798",
					"User should be able to view the manual vendor=x restored.",
					"Fail");
			AbstractTest.takeSnapShot("sprint10_US1046_TC1798");
		}
	}
	
	//TC1797: Verify that the user should be able to restore a deleted invoice from "Purchases" page.
		@Test (enabled=false)
		public void sprint10_US1046_TC1797() throws RowsExceededException,
				BiffException, WriteException, IOException, InterruptedException {
			/** Variable Section : **/
			PurchasesPage purchasesPage;
			String storeId = GlobalVariable.StoreId;
			String userId = GlobalVariable.userId;
			String invoiceId = Base.randomNumberFiveDigit();
			HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint2_US189_TC469", "Object1");
			String wrinId = ReadTestData.getTestData(manualInvoiceNewPageSheet,"EnterQuickSearchWithSuggestionsForManualPurchases");
			String vendor = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Vendor");
			String quantity = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Quantity");
			ManualInvoiceEditPage manualInvoiceEditPage;
			/***********************************/
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			manualInvoiceEditPage = PageFactory.initElements(driver, ManualInvoiceEditPage.class);
			//Navigate to Purchases Page
			purchasesPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPurchaseLandingPage();
			// Create a new manual purchase
			purchasesPage.goToManualInvoiceNewPage().createAManualPurchaseForWrinID(wrinId, vendor, quantity, invoiceId);
			//Delete the manual purchase
			manualInvoiceEditPage.Delete_BT.click();
			wait.until(ExpectedConditions.visibilityOf(manualInvoiceEditPage.DeletePopup_OK_BT));
			//Click on delete button
			manualInvoiceEditPage.DeletePopup_OK_BT.click();
			wait.until(ExpectedConditions.visibilityOf(manualInvoiceEditPage.InvoiceDeleted_Confirmation_MSG));
			wait.until(ExpectedConditions.visibilityOf(purchasesPage.RestorePurchases_BT));
			//Click on Restore purchases button
			purchasesPage.RestorePurchases_BT.click();
			//Restore the deleted purchase
			purchasesPage.restoreDeletedPurchases(invoiceId);;
			//Navigate back to purchase landing page 
			homePage.Menu_DD_BT.click();
			//Verify that rrestored purchase should displayed in pending purchase list
			if (purchasesPage.goToPurchaseLandingPage().verifyPendindInvoiceIsPresent(invoiceId)) {
				Reporter.reportPassResult(
						browser,"sprint10_US1046_TC1797",
						"Venodr name=x should not be restored and duplicated.",
						"Pass");
			} else {
				Reporter.reportTestFailure(
						browser,"sprint10_US1046_TC1797","sprint10_US1046_TC1797",
						"Venodr name=x should not be restored and duplicated.",
						"Fail");
				AbstractTest.takeSnapShot("sprint10_US1046_TC1797");
			}
		}
		
}
