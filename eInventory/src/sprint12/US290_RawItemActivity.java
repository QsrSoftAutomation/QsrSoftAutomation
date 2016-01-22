package sprint12;


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
import eInventoryPageClasses.RawItemActivityPage;
import eInventoryPageClasses.TransferLandingPage;

public class US290_RawItemActivity extends AbstractTest{
	
	//TC2235:Verify the presence of  event "Start of day on raw item activity page.
	@Test()
	public void sprint12_US1290_TC2235() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet transferLandingPageSheet = ReadTestData.getTestDataSheet("Sprint7_US764_TC1417", "Object1");
		String wrinId = ReadTestData.getTestData(transferLandingPageSheet,"WRINId");
		String storeNum = ReadTestData.getTestData(transferLandingPageSheet,"InputNationalStoreNumber");
		String outerPackQty = ReadTestData.getTestData(transferLandingPageSheet,"OuterPackQty");
		String innerPackQty = ReadTestData.getTestData(transferLandingPageSheet,"InnerPackQty");
		String looseUnitsQty = ReadTestData.getTestData(transferLandingPageSheet,"LooseUnitsQty");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to promo and waste landing page
		TransferLandingPage transferLandingPage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToTransferLandingPage();
		transferLandingPage.CreateNewTransfers_BT.click();
		wait.until(ExpectedConditions.visibilityOf(transferLandingPage.InsertNewTransfersPopup_InputNationalStoreNo_TB));
		String date = Base.returnTodayDate();
		String time =  Base.getCurrentTimeStamp();
		transferLandingPage.InsertNewTransfersPopup_InputNationalStoreNo_TB.sendKeys(storeNum);
		transferLandingPage.InsertNewTransfersPopup_TransferOut_RB.click();
		Thread.sleep(3000);
		transferLandingPage.insertAndAddDetailsToTransfer(wrinId, outerPackQty, innerPackQty, looseUnitsQty);
		Thread.sleep(3000);
		String unitCount = transferLandingPage.unitCountForTransfer_Value.getText();
		System.out.println("unitCountTransferred"+unitCount);
		//Submit the transfer
		transferLandingPage.AddTransferItemsPopup_Submit_BT.click();
		//wait.until(ExpectedConditions.visibilityOf(transferLandingPage.ChangesSaved_Confirmation_MSG));
		Thread.sleep(6000);
		homePage.goBackToInventoryManagementMenu();
		RawItemActivityPage rawItemActivityPage = homePage.goToRawItemActivityPage();
		rawItemActivityPage.searchAndSelectWRINID(wrinId).getItemDetails_Button.click();
		// click on waste tab
		// Verify that user is able to enter raw waste for a wrin with loose unit quantity as 2 digits past the decimal
		if (rawItemActivityPage.verifyDeductedCountForSelectedDateTime(date, time, unitCount)) {
			Reporter.reportPassResult(
					browser,"sprint12_US1290_TC2235",
					"User should be able to find one of the event as Transfer out and start of day as X",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint12_US1290_TC2235","sprint12_US1290_TC2235",
					"User should be able to find one of the event as Transfer out and start of day as X",
					"Fail");
			AbstractTest.takeSnapShot("sprint12_US1290_TC2235");
		}
	}
	

}
