package sprint11;

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
import eInventoryPageClasses.TransferLandingPage;
import sprint2.AbstractTest;

public class US964_SelectValidStoreNumbersFromTheTransferPageDetailsValidation extends AbstractTest
{
	
	//TC1936 Verify, on the transfer page, I can only select valid national store numbers to transfer to or from (unless office).
	@Test()
	public void Sprint11_US964_TC1936() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/* Start-Variable Deceleration */
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet transferLandingPageSheet = ReadTestData.getTestDataSheet("Sprint11_US964_TC1936", "Object1");
		String validStoreID = ReadTestData.getTestData(transferLandingPageSheet, "InputNationalStoreNumber");
		String invalidStoreID = GlobalVariable.invalidStoreNumber;
		String wrinId = GlobalVariable.addTransferItemWrin;
		String outerPackQty = "1";
		String innerPackQty = "1";
		String looseUnitsQty = "1";
		/* End-Variable Deceleration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		TransferLandingPage transferLandingPage = PageFactory.initElements(driver, TransferLandingPage.class);
		// Enter url & goto transfer landing page & verify that 'Insert new
		// Transfer' button should be present on Transfer Landing Page
		homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToTransferLandingPage().CreateNewTransfers_BT.click();
		wait.until(ExpectedConditions.visibilityOf(transferLandingPage.InsertNewTransfersPopup_InputNationalStoreNo_TB));
		// Enter the valid store number
		transferLandingPage.InsertNewTransfersPopup_InputNationalStoreNo_TB.sendKeys(validStoreID);
		// Select transfer type
		transferLandingPage.InsertNewTransfersPopup_TransferOut_RB.click();
		// click on COntinue button
		wait.until(ExpectedConditions.visibilityOf(transferLandingPage.AddTransferItemsPopup_RawItemsSearchBox_TB));
		if (Base.isElementDisplayed(transferLandingPage.AddTransferItemsPopup_RawItemsSearchBox_TB)) {
			Reporter.reportPassResult(
					browser, "Sprint11_US964_TC1936",
					"User should be able to enter the valid store number",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint11_US964_TC1936_Condition1","Sprint11_US964_TC1936",
					"Correct UOM case value should display", "Fail");
			AbstractTest.takeSnapShot("Sprint11_US964_TC1936_Condition1");
		}
		// click on Cancel button
		transferLandingPage.AddTransferItemsPopup_Cancel_BT.click();
		wait.until(ExpectedConditions.visibilityOf(transferLandingPage.AddTransferItemsPopup_Warning_Message_Yes_BT));
		// click on Cancel button on warning message
		transferLandingPage.AddTransferItemsPopup_Warning_Message_Yes_BT.click();
		wait.until(ExpectedConditions.visibilityOf(transferLandingPage.CreateNewTransfers_BT));
		// click on insert new transfer button
		transferLandingPage.CreateNewTransfers_BT.click();
		wait.until(ExpectedConditions.visibilityOf(transferLandingPage.InsertNewTransfersPopup_InputNationalStoreNo_TB));
		// Enter the invalid store number
		transferLandingPage.InsertNewTransfersPopup_InputNationalStoreNo_TB.clear();
		transferLandingPage.InsertNewTransfersPopup_InputNationalStoreNo_TB.sendKeys(invalidStoreID);
		// Select transfer type
		transferLandingPage.InsertNewTransfersPopup_TransferOut_RB.click();
		transferLandingPage.insertAndAddDetailsToTransfer(wrinId, outerPackQty,innerPackQty, looseUnitsQty);
		transferLandingPage.AddTransferItemsPopup_Submit_BT.click();
		if (Base.isElementDisplayed(transferLandingPage.InsertNewTransfersPopup_Error_MSG)) {
			Reporter.reportPassResult(
					browser, "Sprint11_US964_TC1936",
					"Error message should display for invalid store number",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint11_US964_TC1936_Condition2","Sprint11_US964_TC1936",
					"Error message should display for invalid store number",
					"Fail");
			AbstractTest.takeSnapShot("Sprint11_US964_TC1936_Condition2");
		}
	}
	
	// TC1993 Select valid store numbers from the Transfer page - details validation
	@Test()
	public void Sprint11_US964_TC1993() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/* Start-Variable Deceleration */
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet transferLandingPageSheet = ReadTestData.getTestDataSheet("Sprint11_US964_TC1993", "Object1");
		String validStoreID = ReadTestData.getTestData(transferLandingPageSheet, "InputNationalStoreNumber");
		/* End-Variable Deceleration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		TransferLandingPage transferLandingPage = PageFactory.initElements(driver, TransferLandingPage.class);
		// Enter url & goto transfer landing page & verify that 'Insert new Transfer' button should be present on Transfer Landing Page
		homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToTransferLandingPage().CreateNewTransfers_BT.click();
		wait.until(ExpectedConditions.visibilityOf(transferLandingPage.InsertNewTransfersPopup_InputNationalStoreNo_TB));
		// Enter the valid store number that is not been closed
		transferLandingPage.InsertNewTransfersPopup_InputNationalStoreNo_TB.sendKeys(validStoreID);
		// Select transfer type
		transferLandingPage.InsertNewTransfersPopup_TransferOut_RB.click();
		// click on COntinue button
		wait.until(ExpectedConditions.visibilityOf(transferLandingPage.AddTransferItemsPopup_RawItemsSearchBox_TB));
		if (Base.isElementDisplayed(transferLandingPage.AddTransferItemsPopup_RawItemsSearchBox_TB)) {
			Reporter.reportPassResult(
					browser, "Sprint11_US964_TC1993",
					"User should be able to enter the valid store number",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "Sprint11_US964_TC1993","Sprint11_US964_TC1993",
					"Correct UOM case value should display", "Fail");
			AbstractTest.takeSnapShot("Sprint11_US964_TC1993");
		}
	}
}
