package sprint8;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.openqa.selenium.By;
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
import sprint2.AbstractTest;

public class US899_ManualPurchaseAuditCapabilities extends AbstractTest {
	
	// TC1530 Verify that audit Column is present on pending purchase page
	@Test()
	public void Sprint8_US899_TC1530() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		PurchasesPage purchasesPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPurchaseLandingPage();
		if (Base.isElementDisplayed(purchasesPage.Audit_Label)) {
			Reporter.reportPassResult(
					browser, "Sprint8_US899_TC1530",
					"Audit column should be present", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "Sprint8_US899_TC1530","Sprint8_US899_TC1530", 
					"Audit column should be present",
					"Fail");
			AbstractTest.takeSnapShot("Sprint8_US899_TC1530");
		}
	}
	
	/*
	 * TC1532 Verify that audit Column present on purchases page contains the
	 * below fields: Time Stamp User Field Name Before Value After Value
	 */
	@Test()
	public void Sprint8_US899_TC1532() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/* Start-Variable declaration */
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint8_US899_TC1532", "Object1");
		String vendor = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Vendor");
		String wrinId = ReadTestData.getTestData(manualInvoiceNewPageSheet,"EnterQuickSearchWithSuggestionsForManualPurchases");
		String quantity1 = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Quantity");
		String quantity2 = String.valueOf((Integer.parseInt(quantity1) + 1));
		ManualInvoiceEditPage manualInvoiceEditPage;
		PurchasesPage purchasesPage;
		String invoiceNumber = Base.randomNumberFiveDigit();
		/* End-Variable declaration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Go to manual invoice new page and create a invoice
		purchasesPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPurchaseLandingPage();
		purchasesPage.goToManualInvoiceNewPage().createAManualPurchaseForWrinID(wrinId, vendor, quantity1,invoiceNumber);
		homePage.Menu_DD_BT.click();
		// go to purchase landing page open the invoice ,edit the quantity and save the invoice
		manualInvoiceEditPage = purchasesPage.goToPurchaseLandingPage().clickOntheInvoice(invoiceNumber);
		manualInvoiceEditPage.Quantity_TB.get(0).clear();
		manualInvoiceEditPage.Quantity_TB.get(0).sendKeys(quantity2);
		manualInvoiceEditPage.Save_BT.click();
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceEditPage.InvoiceSaved_Confirmation_MSG));
		homePage.Menu_DD_BT.click();
		manualInvoiceEditPage.goToPurchaseLandingPage().clickOnAuditButtonForInvoice(invoiceNumber);
		Thread.sleep(3000);
		/*
		 * Audit dialog box should be displayed to user with below fields with
		 * their values: -Time stamp , -User, Field Name , Before value, After
		 * value:
		 */
		if (Base.isElementDisplayed(purchasesPage.AuditPopUp_AfterValue_Column_Label)
				&& Base.isElementDisplayed(purchasesPage.AuditPopUp_BeforeValue_Column_Label)
				&& Base.isElementDisplayed(purchasesPage.AuditPopUp_FieldName_Column_Label)
				&& Base.isElementDisplayed(purchasesPage.AuditPopUp_TimeStamp_Column_Label)
				&& driver.findElements(By.xpath("//table[@id='purchase_audit_modal_tbl']/tbody/tr")).size() >= 1) {
			Reporter.reportPassResult(
					browser,"Sprint8_US899_TC1532",
					"Audit dialog box should display with fields and their respective values",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint8_US899_TC1532","Sprint8_US899_TC1532",
					"Audit dialog box should display with fields and their respective values",
					"Fail");
			AbstractTest.takeSnapShot("Sprint8_US899_TC1532");
		}
	}
}
