package sprint5;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import sprint2.AbstractTest;

import common.Base;
import common.GlobalVariable;
import common.ReadTestData;
import common.Reporter;

import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.TransferLandingPage;

public class US611_ListOfRawItemsOnTransferPage  extends AbstractTest{
	//Verify the user is able to search and select raw items in "Add Transfer Items" pop-up screen, able to enter quantities for outer pack  inner pack and loose unit  for selected raw item and add raw items to the Transfer
	@Test()
	public void Sprint5_US611_TC840() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/* Start-Variable Deceleration */
		HSSFSheet transferLandingPageSheet = ReadTestData.getTestDataSheet("Sprint5_US611_TC840", "Object1");
		String wrinId = ReadTestData.getTestData(transferLandingPageSheet,"WRINId");
		String NationalStoreNo = ReadTestData.getTestData(transferLandingPageSheet, "InputNationalStoreNumber");
		String outerPackQty = ReadTestData.getTestData(transferLandingPageSheet, "OuterPackQty");
		String looseUnitsQty = ReadTestData.getTestData(transferLandingPageSheet, "LooseUnitsQty");
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/* End-Variable Deceleration */
		// create instances of home,transfer landing and manual invoice edit pages
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		TransferLandingPage transferlandingpage = PageFactory.initElements(	driver, TransferLandingPage.class);
		// Navigate to 'Add Transfer Items' pop-up Screen by clicking 'insert new transfer' button
		homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToTransferLandingPage().CreateNewTransfers_BT.click();
		// create a transfer-out
		transferlandingpage.InsertNewTransfersPopup_InputNationalStoreNo_TB.clear();
		transferlandingpage.InsertNewTransfersPopup_InputNationalStoreNo_TB.sendKeys(NationalStoreNo);
		Thread.sleep(2000);
		transferlandingpage.InsertNewTransfersPopup_TransferOut_RB.click();
		// Enter a wRIN id in the search field & select item
		transferlandingpage.insertAndAddDetailsToTransfer(wrinId, outerPackQty, "", looseUnitsQty);
		String time = transferlandingpage.InsertNewTransfersPopup_Time_Value.getText().trim();
		String totalAmount = transferlandingpage.AddTransferPopup_TotalAmount_Value.getText().split("\\$")[1];
		// verify wRINID is added to the transfer list
		if (driver.findElement(By.xpath("//table[@id='transfer_add']/tbody/tr/td[1]")).getText().contains(wrinId)) {
			Reporter.reportPassResult(
					browser, "Sprint5_US611_TC840",
					"selected wRINID should be added in the transfer list",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint5_US611_TC840_condition1", "Sprint5_US611_TC840",
					"selected wRINID should be added in the transfer list",
					"Fail");
			AbstractTest.takeSnapShot("Sprint5_US611_TC840_condition1");
		}
		// click on submit button
		transferlandingpage.AddTransferItemsPopup_Submit_BT.click();
		Thread.sleep(4000);
		if (transferlandingpage.verifyTransferPlaced(Base.returnTodayDate(), time, totalAmount)) {
			Reporter.reportPassResult(
					browser, "Sprint5_US611_TC840",
					"User is able to submit transfer", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint5_US611_TC840_Condition2", "Sprint5_US611_TC840",
					"User is able to submit transfer", "Fail");
			AbstractTest.takeSnapShot("Sprint5_US611_TC840_Condition2");
		}
	}	
}
