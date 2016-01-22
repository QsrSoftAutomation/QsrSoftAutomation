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
import eInventoryPageClasses.TransferLandingPage;

public class US733_SelectValidStoreNumbersFromTransferPage extends AbstractTest {

	//TC1560:Verify that when user enters any invalid national store number to transfer in or out, it should give a message as: "Invalid National Store Number.  Please re-enter a valid National Store Number."
	@Test()
	public void sprint8_US733_TC1560() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/**Variable Section :**/
		TransferLandingPage transferLandingPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String invalidStoreNumber =GlobalVariable.invalidStoreNumber;
		String wrinId = GlobalVariable.addTransferItemWrin;
		String outerPackQty = "1";
		String looseUnitsQty = "1";
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Transfer landing page
		transferLandingPage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToTransferLandingPage();
		// Click on insert new transfer button
		transferLandingPage.CreateNewTransfers_BT.click();
		wait.until(ExpectedConditions.visibilityOf(transferLandingPage.InsertNewTransfersPopup_InputNationalStoreNo_TB));
		//enter an invalid store id
		transferLandingPage.InsertNewTransfersPopup_InputNationalStoreNo_TB.sendKeys(invalidStoreNumber);
		//Create an out transfer with invalid store id
		transferLandingPage.InsertNewTransfersPopup_TransferOut_RB.click();
		//search a sample wRIN id & select a raw item
		transferLandingPage.insertAndAddDetailsToTransfer(wrinId, outerPackQty,"", looseUnitsQty);
		//click on submit button
		transferLandingPage.AddTransferItemsPopup_Submit_BT.click();
		if (Base.isElementDisplayed(transferLandingPage.InsertNewTransfersPopup_Error_MSG)) {
			Reporter.reportPassResult(
					browser,"sprint8_US733_TC1560",
					"User should be able to view the error message : 'Invalid National Store Number'",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint8_US733_TC1560","sprint8_US733_TC1560",
					"User should be able to view the error message : 'Invalid National Store Number'",
					"Fail");
			AbstractTest.takeSnapShot("sprint8_US733_TC1560");
		}
	}
	
	//TC1558:Verify that user can only select existing national store numbers to transfer to or from (unless office) from transfer page.
	@Test()
	public void sprint8_US733_TC1558() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/**Variable Section :**/
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet transferLandingPageSheet = ReadTestData.getTestDataSheet("sprint8_US733_TC1558", "Object1");
		String NationalStoreNo = ReadTestData.getTestData(transferLandingPageSheet, "InputNationalStoreNumber");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Transfer landing page
		TransferLandingPage transferLandingPage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToTransferLandingPage();
		// Click on insert new transfer button
		transferLandingPage.CreateNewTransfers_BT.click();
		wait.until(ExpectedConditions.visibilityOf(transferLandingPage.InsertNewTransfersPopup_InputNationalStoreNo_TB));
		// Enter a valid store id
		transferLandingPage.InsertNewTransfersPopup_InputNationalStoreNo_TB.sendKeys(NationalStoreNo);
		// Create an In transfer with valid store id
		transferLandingPage.InsertNewTransfersPopup_TransferIn_RB.click();
		// click on continue transfer button
		// Verify that user should be able to navigate to Raw item search box pop up for IN transfer
		boolean transferPlaced = Base.isElementDisplayed(transferLandingPage.AddTransferItemsPopup_RawItemsSearchBox_TB);
		// Cancel the transfer
		transferLandingPage.AddTransferItemsPopup_Cancel_BT.click();
		wait.until(ExpectedConditions.visibilityOf(transferLandingPage.AddTransferItemsPopup_Warning_Message_Yes_BT));
		// Click on the cancel transfer button in warning pop up
		transferLandingPage.AddTransferItemsPopup_Warning_Message_Yes_BT.click();
		// click on insert transfer
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(transferLandingPage.CreateNewTransfers_BT));
		transferLandingPage.CreateNewTransfers_BT.click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(transferLandingPage.InsertNewTransfersPopup_InputNationalStoreNo_TB));
		// enter a valid store id
		transferLandingPage.InsertNewTransfersPopup_InputNationalStoreNo_TB.clear();
		transferLandingPage.InsertNewTransfersPopup_InputNationalStoreNo_TB.sendKeys(NationalStoreNo);
		// Create an out transfer with valid store id
		transferLandingPage.InsertNewTransfersPopup_TransferOut_RB.click();
		// Verify that user should be able to navigate to Raw item search box pop up for out transfer
		transferPlaced = transferPlaced && Base.isElementDisplayed(transferLandingPage.AddTransferItemsPopup_RawItemsSearchBox_TB);
		// Verify that an error message for invalid store id will be displayed
		if (transferPlaced) {
			Reporter.reportPassResult(
					browser,"sprint8_US733_TC1558",
					"User should be able to select existing national store numbers to transfer to or from in transfer page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint8_US733_TC1558","sprint8_US733_TC1558",
					"User should be able to select existing national store numbers to transfer to or from in transfer page",
					"Fail");
			AbstractTest.takeSnapShot("sprint8_US733_TC1558");

		}
	}

}
