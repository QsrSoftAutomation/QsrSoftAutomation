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
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.TransferLandingPage;

public class US1242_SelectValidStoreNumbersFromTransferPage extends AbstractTest{
	
	//TC2012: Verify, on the transfer page, I can only select valid national store numbers to transfer to or from (unless office).
	@Test()
	public void sprint10_US1242_TC2012() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		TransferLandingPage transferLandingPage;
		//Need the store 1422
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet transferLandingPageSheet = ReadTestData.getTestDataSheet("sprint10_US1242_TC2012", "Object1");
		String validStoreNumber = ReadTestData.getTestData(transferLandingPageSheet, "InputNationalStoreNumber");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Food Over Base page
		transferLandingPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToTransferLandingPage();
		// Click on insert new transfer button
		transferLandingPage.CreateNewTransfers_BT.click();
		wait.until(ExpectedConditions.visibilityOf(transferLandingPage.InsertNewTransfersPopup_InputNationalStoreNo_TB));
		//Verify that User should be able to view the message "Select a date and time for your new transfer"
		boolean result = Base.isElementDisplayed(transferLandingPage.InsertNewTransfersPopup_SelectDateTime_Label);
		// enter a valid store id
		transferLandingPage.InsertNewTransfersPopup_InputNationalStoreNo_TB.sendKeys(validStoreNumber);
		//Verify that User should be allowed to enter the store number.
		result = result & transferLandingPage.InsertNewTransfersPopup_InputNationalStoreNo_TB.getAttribute("value").equals(validStoreNumber);
		if (result) {
			Reporter.reportPassResult(
					browser,"sprint10_US1242_TC2012",
					"User should be able to enter a store number in insert new transfer pop up",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint10_US1242_TC2012","sprint10_US1242_TC2012",
					"User should be able to enter a store number in insert new transfer pop up",
					"Fail");
			AbstractTest.takeSnapShot("sprint10_US1242_TC2012");
		}
	}

	//TC2013: Verify If any of the restaurants close (or are closed), these restaurants will not be valid anymore.
	@Test()
	public void sprint10_US1242_TC2013() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		TransferLandingPage transferLandingPage;
		// Need the store 1422
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String closedStoreNumber = GlobalVariable.invalidStoreNumber;
		String wrinId = GlobalVariable.addTransferItemWrin;
		String outerPackQty="1";
		String innerPackQty="1";
		String looseUnitsQty="1";
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Food Over Base page
		transferLandingPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToTransferLandingPage();
		// Click on insert new transfer button
		transferLandingPage.CreateNewTransfers_BT.click();
		wait.until(ExpectedConditions.visibilityOf(transferLandingPage.InsertNewTransfersPopup_InputNationalStoreNo_TB));
		// enter an closed store id
		transferLandingPage.InsertNewTransfersPopup_InputNationalStoreNo_TB.sendKeys(closedStoreNumber);
		// Create an out transfer with invalid store id
		transferLandingPage.InsertNewTransfersPopup_TransferOut_RB.click();
		transferLandingPage.insertAndAddDetailsToTransfer(wrinId, outerPackQty, innerPackQty, looseUnitsQty);
		transferLandingPage.AddTransferItemsPopup_Submit_BT.click();
		// Verify that an error message for closed(invalid) store id will be displayed
		if (Base.isElementDisplayed(transferLandingPage.InvalidStoreNumber_Messag)) {
			Reporter.reportPassResult(
					browser,"sprint10_US1242_TC2013",
					"User should be able to view the error message : 'Invalid National Store Number' for closed store number",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint10_US1242_TC2013","sprint10_US1242_TC2013",
					"User should be able to view the error message : 'Invalid National Store Number' for closed store number",
					"Fail");
			AbstractTest.takeSnapShot("sprint10_US1242_TC2013");
		}
	}
	
	//TC2014: Verify transfer by re entering valid National Store Number
	@Test()
	public void sprint10_US1242_TC2014() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		TransferLandingPage transferLandingPage;
		// Need the store 1422
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet transferLandingPageSheet = ReadTestData.getTestDataSheet("sprint10_US1242_TC2014", "Object1");
		String validStoreNumber = ReadTestData.getTestData(transferLandingPageSheet, "InputNationalStoreNumber");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Food Over Base page
		transferLandingPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToTransferLandingPage();
		// Click on insert new transfer button
		transferLandingPage.CreateNewTransfers_BT.click();
		wait.until(ExpectedConditions.visibilityOf(transferLandingPage.InsertNewTransfersPopup_InputNationalStoreNo_TB));
		// enter an closed store id
		transferLandingPage.InsertNewTransfersPopup_InputNationalStoreNo_TB.sendKeys(validStoreNumber);
		// Create an out transfer with invalid store id
		transferLandingPage.InsertNewTransfersPopup_TransferOut_RB.click();
		// Verify that Search box to search a raw item should displayed
		if (Base.isElementDisplayed(transferLandingPage.AddTransferItemsPopup_RawItemsSearchBox_TB)) {
			Reporter.reportPassResult(
					browser,"sprint10_US1242_TC2014",
					"User should be able to select the valid store.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint10_US1242_TC2014","sprint10_US1242_TC2014",
					"User should be able to select the valid store.",
					"Fail");
			AbstractTest.takeSnapShot("sprint10_US1242_TC2014");
		}
	}
}
