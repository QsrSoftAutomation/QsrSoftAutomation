package sprint2;


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
import eInventoryPageClasses.ManualInvoiceNewPage;
import eInventoryPageClasses.PurchasesPage;



public class US178_EnterAManualPurachase extends AbstractTest
{
	// TC393 :(Verify that User is able to access the purchase Landing Page in eProfitability application)
	@Test()
	public void Sprint2_US178_TC393() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/* Start-Variable Deceleration */
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/* End-Variable Deceleration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to purchase landing page and
		boolean result = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement()
				.goToPurchaseLandingPage().isPurchaseLandingPageLoaded();
		// verify that purchase landing page is loaded successfully.
		if (result) {
			Reporter.reportPassResult(
					browser,"Sprint2_US178_TC393",
					"Purchase Landing Page should be Loaded with all the required fields",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint2_US178_TC393","Sprint2_US178_TC393",
					"Purchase Landing Page should be Loaded with all the required fields",
					"Fail");
			AbstractTest.takeSnapShot("Sprint2_US178_TC393");
		}
	
	}
		
	// TC397 Verify that user is able to view the 'UnPosted' Manual Purchase from purchase landing Page.
	@Test()
	public void Sprint2_US178_TC397() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/* Start-Variable Deceleration */
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/* End-Variable Deceleration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Go to purchase landing page then click on First available manual
		// invoice and verify that 'manual invoice edit' page is loaded
		ManualInvoiceEditPage manualInvoiceEditPage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPurchaseLandingPage().doubleClickOnFirstAvailableManualInvoice();
		Thread.sleep(4000);
		boolean result = manualInvoiceEditPage.isManualInvoiceEditPageIsLoaded();
		if (result) {
			Reporter.reportPassResult(
					browser,"Sprint2_US178_TC397",
					"Manual Invoice Edit Page should be Loaded with all the required fields",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint2_US178_TC397","Sprint2_US178_TC397",
					"Manual Invoice Edit Page  should be Loaded with all the required fields",
					"Fail");
			AbstractTest.takeSnapShot("Sprint2_US178_TC397");
		}
	}	
	
	/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''*/	
	//TC398 Verify that user is able to enter a new Manual Purchase from Purchase landing page
	@Test()
	public void Sprint2_US178_TC398() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/* Start-Variable Deceleration */
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint2_US178_TC398", "Object1");
		String wrinId = ReadTestData.getTestData(manualInvoiceNewPageSheet,"EnterQuickSearchWithSuggestionsForManualPurchases");
		String vendor = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Vendor");
		String quantity = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Quantity");
		/* End-Variable Deceleration */
		PurchasesPage purchasePage = null;
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Go to Purchase landing page
		purchasePage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPurchaseLandingPage();
		wait.until(ExpectedConditions.visibilityOf(purchasePage.PurchaseForStoreNumber_Title));
		// Find the number of records before the invoice creation
		int noOfRec_beforeAdd = purchasePage.getNumberOfRecords();;
		// Go to Purchase landing page
		purchasePage.goToManualInvoiceNewPage().createAManualPurchaseForWrinID(wrinId, vendor, quantity, "");
		//Click on Menu Btn
		homePage.Menu_DD_BT.click();
		purchasePage.goToPurchaseLandingPage();
		wait.until(ExpectedConditions.visibilityOf(purchasePage.PurchaseForStoreNumber_Title));
		// Find the number of records after the invoice is created
		int noOfRec_afterAdd = purchasePage.getNumberOfRecords();
		if (noOfRec_afterAdd == noOfRec_beforeAdd + 1) {
			Reporter.reportPassResult(
					browser,"Sprint2_US178_TC398",
					"Added Manual Invoice should be updated in Purchase Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint2_US178_TC398","Sprint2_US178_TC398",
					"Added Manual Invoice should be updated in Purchase Landing Page",
					"Fail");
			AbstractTest.takeSnapShot("Sprint2_US178_TC398");
		}

	}
	
	/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''*/	
	// TC399 Verify that user is able to search a raw item from the list of raw items on Pending Purchase: Manual Purchase detail screen
	@Test()
	public void Sprint2_US178_TC399() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/* Start-Variable Deceleration */
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint2_US178_TC399", "Object1");
		String wrinId = ReadTestData.getTestData(manualInvoiceNewPageSheet,"EnterQuickSearchWithSuggestionsForManualPurchases");
		String vendor = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Vendor");
		/* End-Variable Deceleration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPurchaseLandingPage()
				.goToManualInvoiceNewPage().selectAVendorFromDropdown(vendor).searchAndSelectARawItem(wrinId);
		Thread.sleep(4000);
		if (driver.findElements(By.xpath("//tbody[@id='invoice_tbl_body']/tr/td[2]")).size() == 1) {
			Reporter.reportPassResult(
					browser, "Sprint2_US178_TC399",
					"User should be able to search and select a raw item",
					"Pass");

		} else {
			Reporter.reportTestFailure(
					browser, "Sprint2_US178_TC399","Sprint2_US178_TC399",
					"User should be able to search and select a raw item",
					"Fail");
			AbstractTest.takeSnapShot("Sprint2_US178_TC399");
		}

	}
	
	/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''*/	
	//TC400 Verify that user is able to search a raw item from the manual Purchase section on Pending Purchase: Manual Purchase detail screen
	@Test()
	public void Sprint2_US178_TC400() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/* Start-Variable Deceleration */
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint2_US178_TC400", "Object1");
		String wrinId = ReadTestData.getTestData(manualInvoiceNewPageSheet,"EnterQuickSearchWithSuggestionsForManualPurchases");
		String vendor = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Vendor");
		HSSFSheet manualInvoiceNewPageSheet1 = ReadTestData.getTestDataSheet("Sprint2_US178_TC400", "Object2");
		String wrinId1 = ReadTestData.getTestData(manualInvoiceNewPageSheet1,"EnterQuickSearchWithSuggestionsForManualPurchases");
		/* End-Variable Deceleration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		ManualInvoiceNewPage manualInvoiceNew = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement()
				.goToPurchaseLandingPage().goToManualInvoiceNewPage().selectAVendorFromDropdown(vendor)
				.searchAndSelectARawItem(wrinId).searchAndSelectARawItem(wrinId1);
		manualInvoiceNew.Search_TB_02.sendKeys(wrinId);
		Thread.sleep(2000);
		if (driver.findElements(By.xpath("//tbody[@id='invoice_tbl_body']/tr")).size() == 1) {
			Reporter.reportPassResult(
					browser, "Sprint2_US178_TC400",
					"User should be able to search the selected raw item",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "Sprint2_US178_TC400","Sprint2_US178_TC400",
					"User should be able to search the selected raw item",
					"Fail");
			AbstractTest.takeSnapShot("Sprint2_US178_TC400");
		}
	}
	
	/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''*/	
	//TC401 Verify that user is able to remove  a raw item from the manual Purchase section on 'Pending Purchase details screen'.
	@Test()
	public void Sprint2_US178_TC401() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/* Start-Variable Deceleration */
		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint2_US178_TC401", "Object1");
		String wrinId = ReadTestData.getTestData(manualInvoiceNewPageSheet,"EnterQuickSearchWithSuggestionsForManualPurchases");
		String vendor = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Vendor");
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/* End-Variable Deceleration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPurchaseLandingPage()
				.goToManualInvoiceNewPage().selectAVendorFromDropdown(vendor).searchAndSelectARawItem(wrinId);
		// click on remove button for the added item
		driver.findElement(By.xpath("//tbody[@id='invoice_tbl_body']/tr/td[9]/button")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//tbody[@id='invoice_tbl_body']/tr/td[9]/button")));
		// verify that the item is removed from the list
		boolean result = driver.findElement(By.xpath("//tbody[@id='invoice_tbl_body']/tr/td")).getText().equalsIgnoreCase("No search results found.");
		if (result) {
			Reporter.reportPassResult(
					browser,"Sprint2_US178_TC401",
					"On clicking Remove button Item should be removed from the list",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint2_US178_TC401","Sprint2_US178_TC401",
					"On clicking Remove button Item should be removed from the list",
					"Fail");
			AbstractTest.takeSnapShot("Sprint2_US178_TC401");
		}
	}
	
	/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''*/	
	//TC402 Verify that user is able to delete the manual purchase from the ''Pending Purchase: Manual Purchase detail screen'.
	@Test()
	public void Sprint2_US178_TC402() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException
	{
		/* Start-Variable Deceleration */
		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint2_US178_TC402", "Object1");
		String wrinId = ReadTestData.getTestData(manualInvoiceNewPageSheet,"EnterQuickSearchWithSuggestionsForManualPurchases");
		String vendor = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Vendor");
		String quantity = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Quantity");
		String invoiceNumber = Base.randomNumberFiveDigit();
		PurchasesPage purchasePage = null;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/* End-Variable Deceleration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		ManualInvoiceEditPage manualInvoiceEditPage = PageFactory.initElements(driver, ManualInvoiceEditPage.class);
		// Go to Purchase Landing Page
		purchasePage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPurchaseLandingPage();
		wait.until(ExpectedConditions.visibilityOf(purchasePage.PurchaseForStoreNumber_Title));
		// Count the number of records before the manual invoice is created
		int noOfRec_beforeAdd = purchasePage.getNumberOfRecords();;
		// Create a manual invoice and delete this invoice
		ManualInvoiceNewPage manualInvoiceNewPage = purchasePage.goToManualInvoiceNewPage().createAManualPurchaseForWrinID(wrinId, vendor, quantity, invoiceNumber);
		// Click on Menu Btn
		homePage.Menu_DD_BT.click();
		manualInvoiceNewPage.goToPurchaseLandingPage();
		wait.until(ExpectedConditions.visibilityOf(purchasePage.PurchaseForStoreNumber_Title));
		purchasePage.clickOntheInvoice(invoiceNumber);
		manualInvoiceEditPage.Delete_BT.click();
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceEditPage.DeletePopup_OK_BT));
		manualInvoiceEditPage.DeletePopup_OK_BT.click();
		// Again count the number of records
		int noOfRec_afterAdd = purchasePage.getNumberOfRecords();
		if (noOfRec_afterAdd == noOfRec_beforeAdd) {
			Reporter.reportPassResult(
					browser, "Sprint2_US178_TC402",
					"Record should be deleted from the list", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "Sprint2_US178_TC402","Sprint2_US178_TC402",
					"Record should be deleted from the list", "Fail");
			AbstractTest.takeSnapShot("Sprint2_US178_TC402");
		}
	}
	
	/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	//TC403-Verify that user is able to close the manual purchase from the ''Pending Purchase: Manual Purchase detail screen'
	@Test()
	public void Sprint2_US178_TC403() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/* Start-Variable Deceleration */
		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("Sprint2_US178_TC403", "Object1");
		String wrinId = ReadTestData.getTestData(manualInvoiceNewPageSheet,"EnterQuickSearchWithSuggestionsForManualPurchases");
		String vendor = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Vendor");
		String quantity = ReadTestData.getTestData(manualInvoiceNewPageSheet,"Quantity");
		PurchasesPage purchasePage = null;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/* End-Variable Deceleration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		ManualInvoiceEditPage manualInvoiceEditPage = PageFactory.initElements(driver, ManualInvoiceEditPage.class);
		// Go to Purchase Landing Page
		purchasePage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPurchaseLandingPage();
		wait.until(ExpectedConditions.visibilityOf(purchasePage.PurchaseForStoreNumber_Title));
		int noOfRec_beforeCancel = purchasePage.getNumberOfRecords();
		// Create a manual invoice and delete this invoice
		purchasePage.goToManualInvoiceNewPage().selectAVendorFromDropdown(vendor)
				.searchAndSelectARawItem(wrinId).enterQuantityInQuantityTextBox(wrinId, quantity);
		manualInvoiceEditPage.Cancel_BT.click();
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceEditPage.CancelPopup_OK_BT));
		manualInvoiceEditPage.CancelPopup_OK_BT.click();
		Thread.sleep(4000);
		int noOfRec_afterCancel = purchasePage.getNumberOfRecords();
		if (noOfRec_beforeCancel == noOfRec_afterCancel) {
			Reporter.reportPassResult(
					browser,"Sprint2_US178_TC403",
					"After click on Cancel button page user should navigating back to the purchase landing page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint2_US178_TC403","Sprint2_US178_TC403",
					"After click on Cancel button page user should navigating back to the purchase landing page",
					"Fail");
			AbstractTest.takeSnapShot("Sprint2_US178_TC403");
		}
	}
}
