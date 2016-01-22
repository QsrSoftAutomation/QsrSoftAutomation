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
import eInventoryPageClasses.ManualVendorsPage;
import eInventoryPageClasses.PurchasesPage;
import eInventoryPageClasses.RawItemInformationPage;
import eInventoryPageClasses.StoreLedgerDetailPage;

public class US826_ManualPurchaseVendorsAbilityToDeleteManualPurchaseVendor  extends AbstractTest {
	
	//TC1552: Verify that the user has an ability to delete a manual vendor on the manual purchase vendor edit page.
	@Test()
	public void sprint8_US826_TC1552() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/**Variable Section :**/
		ManualVendorsPage manualVendorsPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String vendorName = GlobalVariable.vendorName;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Manual Vendor page
		manualVendorsPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToManualVendorsPage();
		wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.AddVendor_BT));
		//Verify that delete vendor button for a vendor should be displayed
		boolean deleteButtonDisplayed = Base.isElementDisplayed(manualVendorsPage.deleteVendor_BT(vendorName));
		if (deleteButtonDisplayed) {
			Reporter.reportPassResult(
					browser,"sprint8_US826_TC1552",
					"User should be able to view delete button for a manual vendor on Manual Vendor Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint8_US826_TC1552","sprint8_US826_TC1552",
					"User should be able to view delete button for a manual vendor on Manual Vendor Page",
					"Fail");
			AbstractTest.takeSnapShot("sprint8_US826_TC1552");

		}
	}
	
	//TC1553: Verify warning message while deleting a manual vendor from manual purchase vendor edit page.
	@Test()
	public void sprint8_US826_TC1553() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/**Variable Section :**/
		ManualVendorsPage manualVendorsPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String vendorName = "Testauto" + Base.generateNdigitRandomNumber(4);
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Manual Vendor page
		manualVendorsPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToManualVendorsPage();
		wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.AddVendor_BT));
		//Create a new vendor
		manualVendorsPage.createANewVendor(vendorName, "");
		// Click on the Delete vendor button
		manualVendorsPage.deleteVendor_BT(vendorName).click();
		//Verify that user should be able to view the confirmation message
		boolean deleteVendorConfirmationDisplayed = Base.isElementDisplayed(manualVendorsPage.DeleteVendorPopUp_Confirmation_Message);
		//Verify that user should be able to view the delete button on confirmation pop up
		deleteVendorConfirmationDisplayed = deleteVendorConfirmationDisplayed && Base.isElementDisplayed(manualVendorsPage.DeleteVendorPopUp_Confirmation_BT);
		//Verify that user should be able to view the close button on confirmation pop up
		deleteVendorConfirmationDisplayed = deleteVendorConfirmationDisplayed && Base.isElementDisplayed(manualVendorsPage.DeleteVendorPopUp_Close_BT);
		if (deleteVendorConfirmationDisplayed) {
			Reporter.reportPassResult(
					browser,"sprint8_US826_TC1553",
					"User should be able to view delete confirmation message along with delete and close button",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint8_US826_TC1553","sprint8_US826_TC1553",
					"User should be able to view delete confirmation message along with delete and close button",
					"Fail");
			AbstractTest.takeSnapShot("sprint8_US826_TC1553");

		}
	}
	
	// TC1554: Verify the option to proceed with delete and cancel button once the "X" button is clicked for any manual vendor on manual purchase vendor landing page..
	@Test()
	public void sprint8_US826_TC1554() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/**Variable Section :**/
		ManualVendorsPage manualVendorsPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/***********************************/
		String newVendorName = "Testauto"+Base.generateNdigitRandomNumber(4);
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Manual Vendor page
		manualVendorsPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToManualVendorsPage();
		wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.AddVendor_BT));
		//CLick on Add New vendor button
		manualVendorsPage.AddVendor_BT.click();
		//Enter a new vendor name
		wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.AddvendorDetailsPopUp_VendorName_TB));
		Thread.sleep(2000);
		manualVendorsPage.AddvendorDetailsPopUp_VendorName_TB.sendKeys(newVendorName);
		//Click on Save vendor button
		manualVendorsPage.AddvendorDetailsPopUp_SaveVendor_BT.click();
		wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.Confirmation_Message));
		wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.VendorName_Row(newVendorName)));
		Thread.sleep(3000);
		// Click on the Delete vendor button
		manualVendorsPage.deleteVendor_BT(newVendorName).click();
		wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.DeleteVendorPopUp_Close_BT));
		//Click on the close button on delete vendor confirmation pop up
		manualVendorsPage.DeleteVendorPopUp_Close_BT.click();
		//Verify that vendor will not be deleted
		boolean vendorNotDeletedOnClickingCloseBtn =  Base.isElementDisplayed(manualVendorsPage.VendorName_Row(newVendorName));
		//Click on the delete vendor button
		manualVendorsPage.deleteVendor_BT(newVendorName).click();
		wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.DeleteVendorPopUp_Close_BT));
		//Click on the delete button on delete vendor confirmation pop up
		manualVendorsPage.DeleteVendorPopUp_Confirmation_BT.click();
		wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.DeleteVendorPopUp_VendorDeleted_Message));
		Thread.sleep(5000);
		//Verify vendor should be deleted
		boolean vendorDeletedOnCLickingDeleteBtn = manualVendorsPage.verifyVendorDeleted(newVendorName);
		if (vendorNotDeletedOnClickingCloseBtn && vendorDeletedOnCLickingDeleteBtn ) {
			Reporter.reportPassResult(
					browser,"sprint8_US826_TC1554",
					"User should be able to proceed with delete and cancel button once the 'X' button is clicked for any manual vendor ",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint8_US826_TC1554","sprint8_US826_TC1554",
					"User should be able to proceed with delete and cancel button once the 'X' button is clicked for any manual vendor ",
					"Fail");
			AbstractTest.takeSnapShot("sprint8_US826_TC1554");

		}
	}
	
	// TC1556: Verify the purchase history with the vendor name will stay in tact, after the vendor is deleted from manual purchase vendor edit page.
	@Test()
	public void sprint8_US826_TC1556() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/**Variable Section :**/
		ManualVendorsPage manualVendorsPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet rawItemInformationPageSheet = ReadTestData.getTestDataSheet("sprint8_US826_TC1556", "Object1");
		String wrinIDForNewVendor = ReadTestData.getTestData(rawItemInformationPageSheet, "WRINId");
		String itemQty="1";
		/***********************************/
		String newVendorName = "Testauto" + Base.generateNdigitRandomNumber(4);
		String invoiceId = Base.randomNumberFiveDigit();
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Manual Vendor page
		manualVendorsPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToManualVendorsPage();
		wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.AddVendor_BT));
		//Create a new vendor
		manualVendorsPage.createANewVendor(newVendorName, "");
		//Click on Menu Btn
		homePage.goBackToInventoryManagementMenu();
		//Associate a raw item with the new vendor
		homePage.goToRawItemInformationPage().associateRawItemToVendor(wrinIDForNewVendor, newVendorName);
		//Click on Menu Btn
		homePage.goBackToInventoryManagementMenu();
		//Create a new manual purchase and post the purchase
		homePage.goToPurchaseLandingPage().goToManualInvoiceNewPage()
				.createAManualPurchaseForWrinID(wrinIDForNewVendor, newVendorName, itemQty,invoiceId).clickOnFinalizeButton().postTheManualPurchage();
		//Navigate to manual vendor page and delete the vendor
		Thread.sleep(2000); 
		//Click on Menu Btn
		homePage.Menu_DD_BT.click();
		homePage.goToManualVendorsPage().deleteVendor_BT(newVendorName).click();
		wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.DeleteVendorPopUp_Close_BT));
		manualVendorsPage.DeleteVendorPopUp_Confirmation_BT.click();
		wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.DeleteVendorPopUp_VendorDeleted_Message));
		Thread.sleep(4000);
		//Verify vendor is deleted
		boolean vendorDeletedOnCLickingDeleteBtn = manualVendorsPage.verifyVendorDeleted(newVendorName);
		//Click on Menu Btn
		homePage.goBackToInventoryManagementMenu();
		//Navigate to purchase Landing page and get purchase history
		PurchasesPage purchasesPage = homePage.goToPurchaseLandingPage();
		purchasesPage.GetPurchaseHistory_BT.click();
		//Click on the posted purchase record that was created above
		purchasesPage.clickOnPostedPurchaseRecord(Base.returnTodayDate(), invoiceId);
		//Verify that deleted vendor name should displayed in the posted purchase
		boolean manualVendorDisplayed = Base.isElementDisplayed(purchasesPage.PostedPurchaseDetailPopUp_VendorName_Value);
		if ( vendorDeletedOnCLickingDeleteBtn && manualVendorDisplayed) {
			Reporter.reportPassResult(
					browser,"sprint8_US826_TC1556",
					"Purchase history with the vendor name should stay in tact, after the vendor is deleted from manual vendor page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint8_US826_TC1556","sprint8_US826_TC1556",
					"Purchase history with the vendor name should stay in tact, after the vendor is deleted from manual vendor page",
					"Fail");
			AbstractTest.takeSnapShot("sprint8_US826_TC1556");

		}
	}
	
	// TC1561: Verify the raw item will not be designated as "manual purchase" once the manual vendor is deleted.
	@Test()
	public void sprint8_US826_TC1561() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/**Variable Section :**/
		ManualVendorsPage manualVendorsPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet rawItemInformationPageSheet = ReadTestData.getTestDataSheet("sprint8_US826_TC1561", "Object1");
		String wrinIDForNewVendor = ReadTestData.getTestData(rawItemInformationPageSheet, "WRINId");
		/***********************************/
		String newVendorName = "Testauto" + Base.generateNdigitRandomNumber(4);
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Manual Vendor page
		manualVendorsPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToManualVendorsPage();
		wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.AddVendor_BT));
		// Create a new vendor
		manualVendorsPage.createANewVendor(newVendorName, "");
		// Associate a raw item with the new vendor
		homePage.goBackToInventoryManagementMenu();
		homePage.goToRawItemInformationPage().associateRawItemToVendor(wrinIDForNewVendor, newVendorName);
		// navigate to manual vendor page and delete the vendor
		homePage.goBackToInventoryManagementMenu();
		homePage.goToManualVendorsPage().deleteVendor_BT(newVendorName).click();
		wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.DeleteVendorPopUp_Close_BT));
		manualVendorsPage.DeleteVendorPopUp_Confirmation_BT.click();
		wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.DeleteVendorPopUp_VendorDeleted_Message));
		Thread.sleep(4000);
		// Verify vendor is deleted
		boolean vendorDeletedOnCLickingDeleteBtn = manualVendorsPage.verifyVendorDeleted(newVendorName);
		// Navigate to Raw Item Info Page and search for the WRIN Id
		homePage.goBackToInventoryManagementMenu();
		RawItemInformationPage rawItemInformationPage = homePage.goToRawItemInformationPage().searchAndSelectWRINID(wrinIDForNewVendor);
		//Verify that manual purchase check box for the WRIN Id should be in selected state.
		boolean manualPurchaseChkBoxChecked = rawItemInformationPage.ManualPurchase_CB.getAttribute("checked").equals("true");
		if (vendorDeletedOnCLickingDeleteBtn && manualPurchaseChkBoxChecked) {
			Reporter.reportPassResult(
					browser,"sprint8_US826_TC1561",
					"User should be able to find the manual purchase check box in selected state.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint8_US826_TC1561","sprint8_US826_TC1561",
					"User should be able to find the manual purchase check box in selected state.",
					"Fail");
			AbstractTest.takeSnapShot("sprint8_US826_TC1561");

		}
	}
	
	// TC1562: Verify once deleted, the vendor is not select-able from the Manual Purchase entry screen.
	@Test()
	public void sprint8_US826_TC1562() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/**Variable Section :**/
		ManualVendorsPage manualVendorsPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/***********************************/
		String newVendorName = "Testauto" + Base.generateNdigitRandomNumber(4);
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Manual Vendor page
		manualVendorsPage =  homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToManualVendorsPage();
		wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.AddVendor_BT));
		// Create a new vendor
		manualVendorsPage.createANewVendor(newVendorName, "");
		//Navigate to Manual Invoice New page and verify that vendor name should be displayed in Manual Purchase entry Vendor Dropdown
		homePage.goBackToInventoryManagementMenu();
		boolean vendorDisplayed = homePage.goToPurchaseLandingPage().goToManualInvoiceNewPage().verifyVendorDisplayed(newVendorName);
		//Navigate to manual vendor page and delete the vendor
		homePage.Menu_DD_BT.click();
		homePage.goToManualVendorsPage().deleteVendor_BT(newVendorName).click();
		wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.DeleteVendorPopUp_Close_BT));
		manualVendorsPage.DeleteVendorPopUp_Confirmation_BT.click();
		wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.DeleteVendorPopUp_VendorDeleted_Message));
		//Navigate to Manual Invoice New page and verify that vendor name should not be displayed in Manual Purchase entry Vendor Dropdown
		homePage.goBackToInventoryManagementMenu();
		boolean vendorDeletedFromManualPurchaseDD = homePage.goToPurchaseLandingPage().goToManualInvoiceNewPage().verifyVendorDisplayed(newVendorName);
		if (vendorDisplayed && (!vendorDeletedFromManualPurchaseDD)) {
			Reporter.reportPassResult(
					browser,"sprint8_US826_TC1562",
					"Once vendor deleted user should not be able to select that vendor from the Manual Purchase entry screen",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint8_US826_TC1562","sprint8_US826_TC1562",
					"Once vendor deleted user should not be able to select that vendor from the Manual Purchase entry screen",
					"Fail");
			AbstractTest.takeSnapShot("sprint8_US826_TC1562");

		}
	}
	
	// TC1697:Verify the vendor and any associated finalized manual purchases will still be view-able on the store's ledger page once the vendor is deleted.
	@Test()
	public void sprint8_US826_TC1697() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/**Variable Section :**/
		ManualVendorsPage manualVendorsPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String itemQty="1";
		HSSFSheet rawItemInformationPageSheet = ReadTestData.getTestDataSheet("sprint8_US826_TC1697", "Object1");
		String wrinIDForNewVendor = ReadTestData.getTestData(rawItemInformationPageSheet, "WRINId");
		/***********************************/
		String newVendorName = "Testauto" + Base.generateNdigitRandomNumber(4);
		String invoiceId = Base.randomNumberFiveDigit();
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Manual Vendor page
		manualVendorsPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToManualVendorsPage();
		wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.AddVendor_BT));
		// Create a new vendor
		manualVendorsPage.createANewVendor(newVendorName, "");
		//Associate a raw item with the new vendor
		homePage.goBackToInventoryManagementMenu();
		homePage.goToRawItemInformationPage().associateRawItemToVendor(wrinIDForNewVendor, newVendorName);
		//Create a new manual purchase and post the purchase
		homePage.goBackToInventoryManagementMenu();
		homePage.goToPurchaseLandingPage().goToManualInvoiceNewPage()
						.createAManualPurchaseForWrinID(wrinIDForNewVendor, newVendorName, itemQty,invoiceId).clickOnFinalizeButton().postTheManualPurchage();
		//Navigate to manual vendor page and delete the vendor
		Thread.sleep(3000); 
		homePage.Menu_DD_BT.click();
		homePage.goToManualVendorsPage().deleteVendor_BT(newVendorName).click();
		wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.DeleteVendorPopUp_Close_BT));
		manualVendorsPage.DeleteVendorPopUp_Confirmation_BT.click();
		wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.DeleteVendorPopUp_VendorDeleted_Message));
		Thread.sleep(3000);
		//Navigate to purchase Landing page and click on View Store Ledger Button
		homePage.goBackToInventoryManagementMenu();
		StoreLedgerDetailPage storeLedgerDetailPage = homePage.goToPurchaseLandingPage().clickOnViewStoreLedgerButton();
		// Verify that finalized manual purchases will still be view-able on the store's ledger page
		if (storeLedgerDetailPage.verifyPosetedPurchaseDisplayedForNewVendor(newVendorName, Base.returnTodayDate(), invoiceId)) {
			Reporter.reportPassResult(
					browser,"sprint8_US826_TC1697",
					"User should be able to view finalized manual purchases on the store's ledger page after deleting the vendor",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint8_US826_TC1697","sprint8_US826_TC1697",
					"User should be able to view finalized manual purchases on the store's ledger page after deleting the vendor",
					"Fail");
			AbstractTest.takeSnapShot("sprint8_US826_TC1697");

		}
	}
}
