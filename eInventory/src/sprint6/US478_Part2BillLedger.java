package sprint6;

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
import eInventoryPageClasses.PurchasesPage;
import eInventoryPageClasses.StoreLedgerDetailPage;
import eInventoryPageClasses.TransferLandingPage;

public class US478_Part2BillLedger extends AbstractTest {

	// Verify the change in column header on store ledger page
	@Test()
	public void Sprint6_US478_TC1274() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		TransferLandingPage transferLandingPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet transferLandingPageSheet = ReadTestData.getTestDataSheet("Sprint6_US478_TC1274", "Object1");
		String nationalStorenumber = ReadTestData.getTestData(transferLandingPageSheet, "InputNationalStoreNumber");
		String wrinId = GlobalVariable.addTransferItemWrin1;
		String outerPackQty = ReadTestData.getTestData(transferLandingPageSheet, "OuterPackQty");
		String looseUnitsQty = ReadTestData.getTestData(transferLandingPageSheet, "LooseUnitsQty");
		/*****************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		PurchasesPage purchasesPage = PageFactory.initElements(driver,PurchasesPage.class);
		transferLandingPage = PageFactory.initElements(driver,TransferLandingPage.class);
		// Go to transfer landing page and click on Insert new transfer button
		homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToTransferLandingPage().CreateNewTransfers_BT.click();
		// Enter the store number
		wait.until(ExpectedConditions.visibilityOf(transferLandingPage.InsertNewTransfersPopup_InputNationalStoreNo_TB));
		transferLandingPage.InsertNewTransfersPopup_InputNationalStoreNo_TB.clear();
		transferLandingPage.InsertNewTransfersPopup_InputNationalStoreNo_TB.sendKeys(nationalStorenumber);
		// Select the types of transfer
		transferLandingPage.InsertNewTransfersPopup_TransferOut_RB.click();
		// Click on Continue button
		transferLandingPage.insertAndAddDetailsToTransfer(wrinId, outerPackQty,"", looseUnitsQty);
		String subTotal = transferLandingPage.AddTransferPopup_TotalAmount_Value.getText().split("\\$")[1];
		// click on submit button
		transferLandingPage.AddTransferItemsPopup_Submit_BT.click();
		Thread.sleep(6000);
/*		// get the first transfer from TransferLandingPage Records List
		transferLandingPage.TransferLandingPage_Records_List.get(0).click();
		// Get date and time for the latest transfer record
		transferLandingPage.TransferDetailPopUp_Close_BTN.click();
*/		// Go to Purchase landing page and click on View Store Ledger button
		homePage.Menu_DD_BT.click();
		wait.until(ExpectedConditions.visibilityOf(homePage.Menu_OtherInventoryFunction_Back_BT));
		Thread.sleep(2000);
		homePage.Menu_OtherInventoryFunction_Back_BT.click();
		wait.until(ExpectedConditions.visibilityOf(homePage.Purchases_BT));
		homePage.Purchases_BT.click();
		wait.until(ExpectedConditions.visibilityOf(purchasesPage.Purchases_Label));
		purchasesPage.ViewStoreLedger_BT.click();
		Thread.sleep(4000);
		StoreLedgerDetailPage storeLedgerDetailPage = PageFactory.initElements(driver,StoreLedgerDetailPage.class);
		wait.until(ExpectedConditions.visibilityOf(storeLedgerDetailPage.InvoiceId_List.get(0)));
		if (storeLedgerDetailPage.verifyTransferIsDisplayedInStoreLedgerPage(nationalStorenumber, Base.returnTodayDate(), subTotal)) {
			Reporter.reportPassResult(
					browser, "Sprint6_US478_TC1274",
					"Correct store number should display", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "Sprint6_US478_TC1274","Sprint6_US478_TC1274",
					"Correct store number should display", "Fail");
			AbstractTest.takeSnapShot("Sprint6_US478_TC1274");
		}
	}
}
