package sprint7;

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

public class US764_AbilityToInitiateAndSubmitTransferForOffice extends AbstractTest {

	@Test()
	public void Sprint7_US764_TC1417() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/* Start-Variable Deceleration */
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet transferLandingPageSheet = ReadTestData.getTestDataSheet("Sprint7_US764_TC1417", "Object1");
		String wrinId = ReadTestData.getTestData(transferLandingPageSheet,"WRINId");
		String outerPackQty = ReadTestData.getTestData(transferLandingPageSheet,"OuterPackQty");
		String innerPackQty = ReadTestData.getTestData(transferLandingPageSheet,"InnerPackQty");
		String looseUnitsQty = ReadTestData.getTestData(transferLandingPageSheet,"LooseUnitsQty");
		/* End-Variable Deceleration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		//Select the store 762 and navigate to Transfer Landing Page
		TransferLandingPage transferLandingPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToTransferLandingPage();
		//Proceed to insert a new transfer against a WRIN to office
		transferLandingPage.CreateNewTransfers_BT.click();
		//Select the transfer To Office chkBox
		transferLandingPage.transferToOffice_chkBox.click();
		/*
		 * insert outerPackQty,innerPackQty,looseUnitsQty for the searched WRIN
		 * and get the total Unit to be transferred and fetch the unit Count Transferred
		 */
		String unitCountTransferred = transferLandingPage
				.insertAndAddDetailsToTransfer(wrinId, outerPackQty,innerPackQty, looseUnitsQty).unitCountForTransfer_Value.getText();
		System.out.println("unitCountTransferred"+unitCountTransferred);
		String date = Base.returnTodayDate();
		String time = transferLandingPage.InsertNewTransfersPopup_Time_Value.getText();
		//Submit the transfer
		transferLandingPage.AddTransferItemsPopup_Submit_BT.click();
		//wait.until(ExpectedConditions.visibilityOf(transferLandingPage.ChangesSaved_Confirmation_MSG));
		//click on bck to top button
		if(Base.isElementDisplayed(transferLandingPage.BackToTop_BT))
			transferLandingPage.BackToTop_BT.click();
		Thread.sleep(2000);
		//Navigate to raw item activity page
		homePage.goBackToInventoryManagementMenu();
		RawItemActivityPage rawitemactivitypage = homePage.goToRawItemActivityPage();
		wait.until(ExpectedConditions.visibilityOf(rawitemactivitypage.RawItemActivity_Label));
		rawitemactivitypage.RawItemActivity_Label.isDisplayed();
		//Search the WRIN Id for which transfer was performed click on getItemDetail button and navigate to item detail page
		rawitemactivitypage.searchAndSelectWRINID(GlobalVariable.addTransferItemWrin).clickOngetItemDetailButton();
		/*
		 * verify the deducted count displayed in raw Item Activity Page for the
		 * Selected WRIN should be the same as unit transferred from the TransferLanding page
		 */
		if (rawitemactivitypage.verifyDeductedCountForSelectedDateTime(date, time, unitCountTransferred)) {
			Reporter.reportPassResult(
					browser,"Sprint7_US764_TC1417",
					"Deducted count should be displayed for selected WRIN in raw item activity page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint7_US764_TC1417","Sprint7_US764_TC1417",
					"Deducted count should be displayed for selected WRIN in raw item activity page",
					"Fail");
			AbstractTest.takeSnapShot("Sprint7_US764_TC1417");
		}
	}
}
