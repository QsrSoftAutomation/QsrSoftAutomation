package sprint8;

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

import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.ManualInvoiceNewPage;

public class US827_ValidationWhenEnteringADuplicateManualPurchaseInvoiceNumber
		extends AbstractTest {

	// TC1555 : Verify that user is not allowed to enter a manual invoice number which is being used by an existing manual invoice for same vendor
	@Test()
	public void Sprint8_US827_TC1555() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint8_US827_TC1555", "Object1");
		String vendor = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Vendor");
		String wrinId = ReadTestData.getTestData(manualInvoiceNewPageSheet,"EnterQuickSearchWithSuggestionsForManualPurchases");
		String quantity = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Quantity");
		String invoiceNumber = Base.randomNumberFiveDigit();
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		String userId = GlobalVariable.userId;
		String storeId = GlobalVariable.StoreId;
		// Go to purchase landing page and click on Enter Manual Purchase button
		ManualInvoiceNewPage manualInvoiceNewPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement()
				.goToPurchaseLandingPage().goToManualInvoiceNewPage();
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceNewPage.ManualInvoiceNew_Label));
		// Create a manual purchase with given invoice Number
		manualInvoiceNewPage.createAManualPurchaseForWrinID(wrinId, vendor,quantity, invoiceNumber);
		homePage.Menu_DD_BT.click();
		manualInvoiceNewPage.goToPurchaseLandingPage().goToManualInvoiceNewPage();
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceNewPage.ManualInvoiceNew_Label));
		// Now again try to create a manual invoice with this given invoice ID
		manualInvoiceNewPage.selectAVendorFromDropdown(vendor).searchAndSelectARawItem(wrinId)
				.enterQuantityInQuantityTextBox(wrinId, quantity).InvoiceNumber_TB.sendKeys(invoiceNumber);
		manualInvoiceNewPage.clickOnSaveButton();
		if (Base.isElementDisplayed(manualInvoiceNewPage.DuplicateInvoiceNumber_Error_MSG)) {
			Reporter.reportPassResult(
					browser, "Sprint8_US827_TC1555",
					"Duplicate Invoice error message should display",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "Sprint8_US827_TC1555","Sprint8_US827_TC1555",
					"Duplicate Invoice error message should display",
					"Fail");
			AbstractTest.takeSnapShot("Sprint8_US827_TC1555");
		}
	}
	
	// TC1557 Verify that user is not allowed to enter a manual invoice number which is being used by an existing manual invoice for different vendor
	@Test()
	public void Sprint8_US827_TC1557() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/* Start-Fetch the data From Test Data Sheet */
		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint8_US827_TC1557", "Object2");
		String vendor1 = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Vendor");
		String wrinId1 = ReadTestData.getTestData(manualInvoiceNewPageSheet,"EnterQuickSearchWithSuggestionsForManualPurchases");
		HSSFSheet manualInvoiceNewPageSheet1 = ReadTestData.getTestDataSheet("Sprint8_US827_TC1557", "Object1");
		String quantity = ReadTestData.getTestData(manualInvoiceNewPageSheet1,"Quantity");
		String vendor2 = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Vendor");
		String wrinId2 = ReadTestData.getTestData(manualInvoiceNewPageSheet,"EnterQuickSearchWithSuggestionsForManualPurchases");
		String userId = GlobalVariable.userId;
		String storeId = GlobalVariable.StoreId;
		String invoiceNumber = Base.randomNumberFiveDigit();
		/* End */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Go to purchase landing page and click on Enter Manual Purchase button
		ManualInvoiceNewPage manualInvoiceNewPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement()
				.goToPurchaseLandingPage().goToManualInvoiceNewPage();
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceNewPage.ManualInvoiceNew_Label));
		// Create a manual purchase with given invoice Number
		manualInvoiceNewPage.createAManualPurchaseForWrinID(wrinId1,vendor1,quantity, invoiceNumber);
		homePage.Menu_DD_BT.click();
		// Now again click on Enter Manual purchase button
		manualInvoiceNewPage.goToPurchaseLandingPage().goToManualInvoiceNewPage();
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceNewPage.ManualInvoiceNew_Label));
		// Now again try to create a manual invoice with this given invoice ID
		manualInvoiceNewPage.selectAVendorFromDropdown(vendor2).searchAndSelectARawItem(wrinId2)
				.enterQuantityInQuantityTextBox(wrinId2, quantity).InvoiceNumber_TB.sendKeys(invoiceNumber);
		manualInvoiceNewPage.clickOnSaveButton();
		if (Base.isElementDisplayed(manualInvoiceNewPage.DuplicateInvoiceNumber_Error_MSG)) {
			Reporter.reportPassResult(
					browser, "Sprint8_US827_TC1557",
					"Duplicate Invoice error message should display",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "Sprint8_US827_TC1557","Sprint8_US827_TC1557",
					"Duplicate Invoice error message should display",
					"Fail");
			AbstractTest.takeSnapShot("Sprint8_US827_TC1557");
		}
	}
}
