package sprint5;

import java.io.IOException;

import eInventoryPageClasses.HomePage;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import common.GlobalVariable;
import common.ReadTestData;
import common.Reporter;
import eInventoryPageClasses.TransferLandingPage;
import sprint2.AbstractTest;

public class US529_TransferLandingPage extends AbstractTest {

	// Verify that user is able to Transfer landing page
	@Test()
	public void Sprint5_US529_TC798() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/* Start-Variable Deceleration */
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/* End-Variable Deceleration */
		// Enter URL & verify user is able to land in transfer landing page
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		TransferLandingPage transferlandingpage = PageFactory.initElements(	driver, TransferLandingPage.class);
		boolean result = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement()
				.goToTransferLandingPage().TransferLandingPage_Label.isDisplayed();
		if (result) {
			Reporter.reportPassResult(
					browser,"Sprint5_US529_TC798",
					"Transfer Landing Page should be Loaded with all the required fields",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint5_US529_TC798_Condition1","Sprint5_US529_TC798",
					"Transfer Landing Page should be Loaded with all the required fields",
					"Fail");
			AbstractTest.takeSnapShot("Sprint5_US529_TC798_Condition1");
		}
		/*verify following options are displayed-'insert new transfer'button,'list of submitted transfer,'start date'&'end date'
		label,'calendar widget start','calendar widget end'&'refresh' button*/
		if (transferlandingpage.CreateNewTransfers_BT.isDisplayed()
				&& transferlandingpage.TransferLandingPage_StartDate_TB.isDisplayed()
				&& transferlandingpage.TransferLandingPage_Records_List.size() > 0
				&& transferlandingpage.TransferLandingPage_EndDate_TB.isDisplayed()
				&& transferlandingpage.TransferLandingPage_StartDateCalendar_BT.isDisplayed()
				&& transferlandingpage.TransferLandingPage_EndDateCalendar_BT.isDisplayed()
				&& transferlandingpage.TransferLandingPage_Refresh_BT.isDisplayed()) {
			Reporter.reportPassResult(
					browser,"Sprint5_US529_TC798",
					"Transfer Landing Page should be Loaded with 'insert new transfer'button,'list of submitted transfer,'start date'&'end date' label,'calendar widget start','calendar widget end'&'refresh' button  fields",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint5_US529_TC798_Condition2","Sprint5_US529_TC798",
					"Transfer Landing Page should be Loaded with 'insert new transfer'button,'list of submitted transfer,'start date'&'end date' label,'calendar widget start','calendar widget end'&'refresh' button  fields",
					"Fail");
			AbstractTest.takeSnapShot("Sprint5_US529_TC798_Condition2");
		}
	}

	//Verify that 'Insert new Transfer' button should be present on Transfer Landing Page
	@Test()
	public void Sprint5_US529_TC799() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/* Start-Variable Deceleration */
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/* End-Variable Deceleration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Enter url & goto transfer landing page & verify that 'Insert new Transfer' button should be present on Transfer Landing Page
		boolean resultbtn = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement()
				.goToTransferLandingPage().CreateNewTransfers_BT.isDisplayed();
		if (resultbtn) {
			Reporter.reportPassResult(
					browser,"Sprint5_US529_TC799",
					"'Insert New Transfer' button should be displayed on Transfer Landing Page ",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint5_US529_TC799","Sprint5_US529_TC799",
					"'Insert New Transfer' button should be displayed on Transfer Landing Page ",
					"Fail");
			AbstractTest.takeSnapShot("Sprint5_US529_TC799");
		}
	}

	//Verify that "submitted transfers" list are available on transfer landing page
	@Test()
	public void Sprint5_US529_TC876() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/* Start-Variable Deceleration */
		HSSFSheet transferLandingPageSheet = ReadTestData.getTestDataSheet("Sprint5_US529_TC876", "Object1");
		String wrinId = ReadTestData.getTestData(transferLandingPageSheet,"WRINId");
		String NationalStoreNo = ReadTestData.getTestData(transferLandingPageSheet, "InputNationalStoreNumber");
		String outerPackQty = ReadTestData.getTestData(transferLandingPageSheet, "OuterPackQty");
		String innerPackQty = ReadTestData.getTestData(transferLandingPageSheet, "InnerPackQty");
		String looseUnitsQty = ReadTestData.getTestData(transferLandingPageSheet, "LooseUnitsQty");
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/* End-Variable Deceleration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		TransferLandingPage transferlandingpage = PageFactory.initElements(driver, TransferLandingPage.class);
		// Enter url & go to transfer landing page
		homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToTransferLandingPage();
		// validate There should be at least one Transfer Record(Submitted Transfers) present in transfer landing page.
		if (transferlandingpage.TransferLandingPage_Records_List.size() > 0) {
			// click on first submitted transfer
			transferlandingpage.TransferLandingPage_Records_List.get(0).click();
		} else {
			// create one transfer out record
			transferlandingpage.CreateNewTransfers_BT.click();
			// create a transfer-out
			transferlandingpage.InsertNewTransfersPopup_InputNationalStoreNo_TB.sendKeys(NationalStoreNo);
			Thread.sleep(2000);
			transferlandingpage.InsertNewTransfersPopup_TransferOut_RB.click();
			transferlandingpage.InsertNewTransfersPopup_ContinueTransfer_BT.click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" //h3[text()='Add Transfer Items']")));
			// Enter a wRIN id in the search field & select item
			transferlandingpage.AddTransferItemsPopup_RawItemsSearchBox_TB.sendKeys(wrinId);
			action.sendKeys(Keys.SPACE).build().perform();
			Thread.sleep(1500);
			action.sendKeys(Keys.BACK_SPACE).build().perform();
			driver.findElement(By.xpath("//strong[text()=" + wrinId + "]")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='transfer_input_outer_pack']")));
			// Enter quantities for outer pack,Inner pack & Loose units for selected raw item..
			transferlandingpage.AddTransferItemsPopup_OuterPack_TB.sendKeys(outerPackQty);
			transferlandingpage.AddTransferItemsPopup_InnerPack_TB.sendKeys(innerPackQty);
			transferlandingpage.AddTransferItemsPopup_LooseUnits_TB.sendKeys(looseUnitsQty);
			// click on add button
			transferlandingpage.AddTransferItemsPopup_Add_BT.click();
			// click on submit button
			driver.findElement(By.xpath("//input[@id='submit_enter_transfer']")).click();
			// click on first submitted record.
			transferlandingpage.TransferLandingPage_Records_List.get(0).click();
		}
		// verify user should be able to view the following details 'transfer-type','store-no','date','created-by'&'total transfer amount'.
		if (transferlandingpage.TransferDetailsPopup_Type_Label.isDisplayed()
				&& transferlandingpage.TransferDetailsPopup_TotalAmount_Label.isDisplayed()
				&& transferlandingpage.TransferDetailsPopup_NationalStoreNO_Label.isDisplayed()
				&& transferlandingpage.TransferDetailsPopup_DateTime_Label.isDisplayed()
				&& transferlandingpage.TransferDetailsPopup_CreatedBy_Label.isDisplayed()) {
			Reporter.reportPassResult(
					browser, "Sprint5_US529_TC876",
					"All the labels should be displayed", 
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint5_US529_TC876_condition2", "Sprint5_US529_TC876",
					"All the labels should be displayed",
					"Fail");
			AbstractTest.takeSnapShot("Sprint5_US529_TC876_condition2");

		}
		// System.out.println("Expected String datetime lenght is"+driver.findElement(By.xpath("//div[@id='transfer_detail_modal_count_div']/div/span[6]/span")).getText().length());
		// verify that the following fields 'transfer-type','store-no','date','created-by'&'total transfer amount'are populated.
		if (transferlandingpage.SubmittedTransferPopup_Type_Value.getText().length() > 0
				&& transferlandingpage.SubmittedTransferPopup_TotalAmount_Value.getText().length() > 0
				&& transferlandingpage.SubmittedTransferPopup_NationalStoreNO_Value.getText().length() > 0
				&& transferlandingpage.SubmittedTransferPopup_Date_Value.getText().length() > 0
				&& transferlandingpage.SubmittedTransferPopup_Time_Value.getText().length() > 0
				&& transferlandingpage.SubmittedTransferPopup_CreatedBy_Value.getText().length() > 0
				&& transferlandingpage.TransferDetailsPopup_Records_List.size() > 0) {
			Reporter.reportPassResult(
					browser, "Sprint5_US529_TC876",
					"All the labels should be populated with some value",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint5_US529_TC876_condition3", "Sprint5_US529_TC876",
					"All the labels should be populated with some value",
					"Fail");
			AbstractTest.takeSnapShot("Sprint5_US529_TC876_condition3");
		}
	}
	
	//Verify  "start date" and "end date" option are available on "Transfer Landing" page and "Print" button available  in "Submitted Transfers"
	@Test()
	public void Sprint5_US529_TC877() throws RowsExceededException, BiffException, WriteException, IOException, InterruptedException
	{
		/*Start-Variable Deceleration*/
		HSSFSheet transferLandingPageSheet = ReadTestData.getTestDataSheet("Sprint5_US529_TC877", "Object1");
		String wrinId = ReadTestData.getTestData(transferLandingPageSheet, "WRINId");
		String NationalStoreNo = ReadTestData.getTestData(transferLandingPageSheet, "InputNationalStoreNumber");
		String outerPackQty = ReadTestData.getTestData(transferLandingPageSheet, "OuterPackQty");
		String innerPackQty = ReadTestData.getTestData(transferLandingPageSheet, "InnerPackQty");
		String looseUnitsQty = ReadTestData.getTestData(transferLandingPageSheet, "LooseUnitsQty");
		String storeId=GlobalVariable.StoreId;
		String userId=GlobalVariable.userId;
		/*End-Variable Deceleration*/
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		TransferLandingPage transferlandingpage=PageFactory.initElements(driver, TransferLandingPage.class);
		//Enter url & go to transfer landing page
		homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToTransferLandingPage();
		//Verify "start-date" and "end-date" option are available on "Transfer Landing" page
		boolean startdatestatus=transferlandingpage.TransferLandingPage_StartDate_TB.isDisplayed();
		boolean enddatestatus=transferlandingpage.TransferLandingPage_EndDate_TB.isDisplayed();
		if(startdatestatus && enddatestatus)
		{
			Reporter.reportPassResult(browser, "Sprint5_US529_TC877", "Start date & End date option should be available on transfer landing page", "Pass");
		}
		else
		{
			Reporter.reportTestFailure(browser, "Sprint5_US529_TC877_condition1", "Sprint5_US529_TC877", "Start date & End date option should be available on transfer landing page", "Fail");
			AbstractTest.takeSnapShot("Sprint5_US529_TC877_condition1");

		}		
		//verify if any submitted record exists or not,if not then create one record.
		if(transferlandingpage.TransferLandingPage_Records_List.size()>0)
		{
			//click first submitted transfer
			transferlandingpage.TransferLandingPage_Records_List.get(0).click();
		}
		else
		{
			//create one transfer out record
			homePage.selectAStore("762").goToTransferLandingPage().CreateNewTransfers_BT.click();
			//create a transfer-out
			transferlandingpage.InsertNewTransfersPopup_InputNationalStoreNo_TB.sendKeys(NationalStoreNo);
			Thread.sleep(2000);
			transferlandingpage.InsertNewTransfersPopup_TransferOut_RB.click();
			transferlandingpage.InsertNewTransfersPopup_ContinueTransfer_BT.click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Add Transfer Items']")));
			//Enter a wRIN id in the search field & select item
			transferlandingpage.AddTransferItemsPopup_RawItemsSearchBox_TB.sendKeys(wrinId);
			action.sendKeys(Keys.SPACE).build().perform(); 
			Thread.sleep(1500); 
			action.sendKeys(Keys.BACK_SPACE).build().perform();
			driver.findElement(By.xpath("//strong[text()="+wrinId+"]")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='transfer_input_outer_pack']")));
			//Enter quantities for outer pack,Inner pack &Looseunits for selected raw item..
			transferlandingpage.AddTransferItemsPopup_OuterPack_TB.sendKeys(outerPackQty);
			transferlandingpage.AddTransferItemsPopup_InnerPack_TB.sendKeys(innerPackQty);
			transferlandingpage.AddTransferItemsPopup_LooseUnits_TB.sendKeys(looseUnitsQty);
			//click on add button
			transferlandingpage.AddTransferItemsPopup_Add_BT.click();
			//click on submit button
			driver.findElement(By.xpath("//input[@id='submit_enter_transfer']")).click();
			//click on first submitted record.
			transferlandingpage.TransferLandingPage_Records_List.get(0).click();
		}
		//verify 'print' button is available on submitted transfer page
		wait.until(ExpectedConditions.visibilityOf(transferlandingpage.TransferDetailsPopup_Print_BT));
		boolean printbtnstatus=transferlandingpage.TransferDetailsPopup_Print_BT.isDisplayed();
		if(printbtnstatus)
		{
			Reporter.reportPassResult(browser, "Sprint5_US529_TC877", "print button should be available on 'submitted-transfer' page", "Pass");
		}
		else
		{
			Reporter.reportTestFailure(browser, "Sprint5_US529_TC877_condition2", "Sprint5_US529_TC877", "print button should be available on 'submitted-transfer' page", "Fail");
			AbstractTest.takeSnapShot("Sprint5_US529_TC877_condition2");

		}		
	//*...........verify print button pop up functionality.................."//
	}

}
