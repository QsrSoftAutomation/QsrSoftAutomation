package sprint5;


import java.awt.AWTException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.openqa.selenium.By;
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
import eInventoryPageClasses.RawItemActivityPage;
import eInventoryPageClasses.TransferLandingPage;

public class US553_InitiateAndSubmitTransfer extends AbstractTest{
	//Verify the user is able to submit "Transfer Out " from transfer landing page
	@Test()
	public void Sprint5_US553_TC778() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/* Start-Variable Deceleration */
		HSSFSheet transferLandingPageSheet = ReadTestData.getTestDataSheet("Sprint5_US553_TC778", "Object1");
		String wrinId = ReadTestData.getTestData(transferLandingPageSheet,"WRINId");
		String NationalStoreNo = ReadTestData.getTestData(transferLandingPageSheet, "InputNationalStoreNumber");
		String outerPackQty = ReadTestData.getTestData(transferLandingPageSheet, "OuterPackQty");
		String looseUnitsQty = ReadTestData.getTestData(transferLandingPageSheet, "LooseUnitsQty");
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String ActualPlaceHolderText = "Enter Raw Item WRIN or Description";
		/* End-Variable Deceleration */
		// Create instance of home, Transfer landing Page & ManualInvoiceEditPage
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		TransferLandingPage transferlandingpage = PageFactory.initElements(driver, TransferLandingPage.class);
		// Enter url & verify user is able to land in transfer landing page
		boolean result = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToTransferLandingPage().TransferLandingPage_Label.isDisplayed();
		if (result) {
			Reporter.reportPassResult(
					browser, "Sprint5_US553_TC778",
					"Transfer Landing Page should be Loaded ", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint5_US553_TC778_Condition1", "Sprint5_US553_TC778",
					"Transfer Landing Page should be Loaded ", "Fail");
			AbstractTest.takeSnapShot("Sprint5_US553_TC778_Condition1");
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
					browser,"Sprint5_US529_TC778",
					"Transfer Landing Page should be Loaded with 'insert new transfer'button,'list of submitted transfer,'start date'&'end date' label,'calendar widget start','calendar widget end'&'refresh' button  fields",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint5_US529_TC798_Condition2","Sprint5_US529_TC798",
					"Transfer Landing Page should be Loaded with 'insert new transfer'button,'list of submitted transfer,'start date'&'end date' label,'calendar widget start','calendar widget end'&'refresh' button  fields",
					"Fail");
			AbstractTest.takeSnapShot("Sprint5_US529_TC798_Condition2");
		}
		// click on 'insert new transfers' button & input store No. & select 'out' as transfer type,click continue
		transferlandingpage.CreateNewTransfers_BT.click();
		wait.until(ExpectedConditions.visibilityOf(transferlandingpage.InsertNewTransfersPopup_InputNationalStoreNo_TB));
		transferlandingpage.InsertNewTransfersPopup_InputNationalStoreNo_TB.clear();
		transferlandingpage.InsertNewTransfersPopup_InputNationalStoreNo_TB.sendKeys(NationalStoreNo);
		transferlandingpage.InsertNewTransfersPopup_TransferOut_RB.click();
		wait.until(ExpectedConditions.visibilityOf(transferlandingpage.AddTransferItemsPopup_RawItemsSearchBox_TB));
		// verify 'Add Transfer Items' pop up screen with 'place holder' &  'cancel' button options
		String ExpectedPlaceHolderText = driver.findElement(By.xpath("//input[@placeholder='Enter Raw Item WRIN or Description']")).getAttribute("placeholder");
		if (transferlandingpage.AddTransferItemsPopup_Cancel_BT.isDisplayed()
				&& ExpectedPlaceHolderText.equalsIgnoreCase(ActualPlaceHolderText)) {
			Reporter.reportPassResult(
					browser,"Sprint5_US553_TC778",
					"'Add Transfer Popup' screen should be loaded with proper fields ",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint5_US553_TC778_Condition3","Sprint5_US553_TC778",
					"'Add Transfer Popup' screen should be loaded with proper fields",
					"Fail");
			AbstractTest.takeSnapShot("Sprint5_US553_TC778_Condition3");
		}	
		//search a sample wRIN id & select a raw item
		transferlandingpage.insertAndAddDetailsToTransfer(wrinId, outerPackQty,"", looseUnitsQty);
		//verify wRINID is added to the transfer list
		if (driver.findElement(By.xpath("//table[@id='transfer_add']/tbody/tr/td[1]")).getText().contains(wrinId)) {
			Reporter.reportPassResult(
					browser, "Sprint5_US611_TC840",
					"selected wRINID should be added in the transfer list",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint5_US553_TC778_Condition4", "Sprint5_US553_TC778",
					"selected wRINID should be added in the transfer list",
					"Fail");
			AbstractTest.takeSnapShot("Sprint5_US553_TC778_Condition4");
		}
		Thread.sleep(3000);
		String amount = transferlandingpage.AddTransferPopup_TotalAmount_Value.getText().split("\\$")[1];
		String time=transferlandingpage.InsertNewTransfersPopup_Time_Value.getText().trim();
		//click on submit button
		transferlandingpage.AddTransferItemsPopup_Submit_BT.click();
		//verify confirmation message
		if (transferlandingpage.verifyTransferPlaced(Base.returnTodayDate(),time, amount)) {
			Reporter.reportPassResult(
					browser, "Sprint5_US553_TC778",
					"User should be able to submit transfer", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint5_US553_TC778_Condition5", "Sprint5_US553_TC778",
					"User should be able to submit transfer", "Fail");
			AbstractTest.takeSnapShot("Sprint5_US553_TC778_Condition5");
		}
	}

	//Verify the user is able to change the default date while initiating transfer out and validate the error message while entering invalid store number.
	@Test()
	public void Sprint5_US553_TC781() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/* Start-Variable Deceleration */
		String storeId = GlobalVariable.StoreId;
		String InValidStoreNo = GlobalVariable.invalidStoreNumber;
		String userId = GlobalVariable.userId;
		String wrinId = GlobalVariable.addTransferItemWrin;
		String outerPackQty = "1";
		String looseUnitsQty = "1";
		/* End-Variable Deceleration */
		// Create instance of home, Transfer landing Page &  ManualInvoiceEditPage
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		TransferLandingPage transferlandingpage = PageFactory.initElements(	driver, TransferLandingPage.class);
		// Navigate to Transfer Landing Page
		homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToTransferLandingPage();
		// click on 'insert new Transfers' button
		transferlandingpage.CreateNewTransfers_BT.click();
		// find the current date of system
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		// Get start date as 3 days earlier date from today date
		cal1.add(Calendar.DATE, -3);
        String transferDate = dateFormat.format(cal1.getTime());
        int day = Base.getDayFromDate(transferDate);
        int month = Base.getMonthFromDate(transferDate);
		// click on current date-1 in the calendar widget
        transferlandingpage.selectDateInAddNewTransferPopUp(String.valueOf(day), String.valueOf(month));
		// Enter Invalid store No. & Validate error msg
		transferlandingpage.InsertNewTransfersPopup_InputNationalStoreNo_TB.sendKeys(InValidStoreNo);
		transferlandingpage.InsertNewTransfersPopup_TransferOut_RB.click();
		//search a sample wRIN id & select a raw item
		transferlandingpage.insertAndAddDetailsToTransfer(wrinId, outerPackQty,"", looseUnitsQty);
		//click on submit button
		transferlandingpage.AddTransferItemsPopup_Submit_BT.click();
		if (Base.isElementDisplayed(transferlandingpage.InsertNewTransfersPopup_Error_MSG)) {
			Reporter.reportPassResult(
					browser, "Sprint5_US553_TC781",
					"Error confirmation message should be displayed", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "Sprint5_US553_TC781","Sprint5_US553_TC781",
					"Error confirmation message should be displayed", "Fail");
			AbstractTest.takeSnapShot("Sprint5_US553_TC781");

		}

	}

	//Verify the user  is able to search raw items in "Add Transfer Items" pop-up screen, able to add quantities for outer pack , inner pack and loose unit  for selected raw item and add 1 or more raw items to the Transfer and verify the transfer submission without entering quantities
	@Test()
	public void Sprint5_US553_TC794() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/* Start-Variable Deceleration */
		HSSFSheet transferLandingPageSheet = ReadTestData.getTestDataSheet("Sprint5_US553_TC794", "Object1");
		String wrinId = ReadTestData.getTestData(transferLandingPageSheet,"WRINId");
		String NationalStoreNo = ReadTestData.getTestData(transferLandingPageSheet, "InputNationalStoreNumber");
		String outerPackQty = ReadTestData.getTestData(transferLandingPageSheet, "OuterPackQty");
		String looseUnitsQty = ReadTestData.getTestData(transferLandingPageSheet, "LooseUnitsQty");
		HSSFSheet transferLandingPageSheet1 = ReadTestData.getTestDataSheet("Sprint5_US553_TC794", "Object2");
		String wrinId1 = ReadTestData.getTestData(transferLandingPageSheet1,"WRINId");
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/* End-Variable Deceleration */
		// create instances of home,transfer landing and manual invoice edit pages
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		TransferLandingPage transferlandingpage = PageFactory.initElements(driver, TransferLandingPage.class);
		// Navigate to 'Add Transfer Items' pop-up Screen by clicking 'insert new transfer' button
		homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToTransferLandingPage().CreateNewTransfers_BT.click();
		// create a transfer-out
		wait.until(ExpectedConditions.visibilityOf(transferlandingpage.InsertNewTransfersPopup_InputNationalStoreNo_TB));
		transferlandingpage.InsertNewTransfersPopup_InputNationalStoreNo_TB.clear();
		transferlandingpage.InsertNewTransfersPopup_InputNationalStoreNo_TB.sendKeys(NationalStoreNo);
		Thread.sleep(2000);
		transferlandingpage.InsertNewTransfersPopup_TransferOut_RB.click();
		// Enter a wRIN id in the search field & select item
		transferlandingpage.insertAndAddDetailsToTransfer(wrinId, outerPackQty, "", looseUnitsQty);
		// Enter 1 more wRINID and Add 1 more raw item in the transfer list
		transferlandingpage.insertAndAddDetailsToTransfer(wrinId1, outerPackQty, "", looseUnitsQty);
		Thread.sleep(3000);
		String amount = transferlandingpage.AddTransferPopup_TotalAmount_Value.getText().split("\\$")[1];
		String time=transferlandingpage.InsertNewTransfersPopup_Time_Value.getText().trim();
		// click submit button
		transferlandingpage.AddTransferItemsPopup_Submit_BT.click();
		// verify 'submit successful' confirmation message
		if (transferlandingpage.verifyTransferPlaced(Base.returnTodayDate(), time, amount)) {
			Reporter.reportPassResult(
					browser, "Sprint5_US553_TC794",
					"User shouldd be able to submit transfer with 1 or more raw items", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "Sprint5_US553_TC794","Sprint5_US553_TC794",
					"User shouldd be able to submit transfer with 1 or more raw items", "Fail");
			AbstractTest.takeSnapShot("Sprint5_US553_TC794");
		}
	}
		
	//Verify that user is taken to the transfer landing page once the "cancel" button is clicked from transfer detail screen.
	@Test ()
	public void Sprint5_US553_TC795() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/* Start-Variable Deceleration */
		HSSFSheet transferLandingPageSheet = ReadTestData.getTestDataSheet("Sprint5_US553_TC795", "Object1");
		String wrinId = ReadTestData.getTestData(transferLandingPageSheet,"WRINId");
		String NationalStoreNo = ReadTestData.getTestData(transferLandingPageSheet, "InputNationalStoreNumber");
		String outerPackQty = ReadTestData.getTestData(transferLandingPageSheet, "OuterPackQty");
		String looseUnitsQty = ReadTestData.getTestData(transferLandingPageSheet, "LooseUnitsQty");
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/* End-Variable Deceleration */
		// Create instance of home, Transfer landing Page & ManualInvoiceEditPage
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		TransferLandingPage transferlandingpage = PageFactory.initElements(driver, TransferLandingPage.class);
		// Navigate to Transfer Landing Page
		homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToTransferLandingPage();
		// click on 'insert new transfers' button & input store No. & select 'out' as transfer type,click continue
		transferlandingpage.CreateNewTransfers_BT.click();
		wait.until(ExpectedConditions.visibilityOf(transferlandingpage.InsertNewTransfersPopup_InputNationalStoreNo_TB));
		System.out.println("National store number " + NationalStoreNo);
		transferlandingpage.InsertNewTransfersPopup_InputNationalStoreNo_TB.sendKeys(NationalStoreNo);
		transferlandingpage.InsertNewTransfersPopup_TransferOut_RB.click();
		transferlandingpage.insertAndAddDetailsToTransfer(wrinId, outerPackQty, "", looseUnitsQty);
		// click on cancel button
		wait.until(ExpectedConditions.visibilityOf(transferlandingpage.AddTransferItemsPopup_Cancel_BT));
		transferlandingpage.AddTransferItemsPopup_Cancel_BT.click();
		// validate the warning message
		if (Base.isElementDisplayed(transferlandingpage.AddTransferItemsPopup_Warning_Message)
				& Base.isElementDisplayed(transferlandingpage.CanceTransferPopup_Yes_BT)
				& Base.isElementDisplayed(transferlandingpage.CanceTransferPopup_No_BT)) {
			Reporter.reportPassResult(
					browser, "Sprint5_US553_TC795",
					"Error warning message should be displayed", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint5_US553_TC795_Condition1", "Sprint5_US553_TC795",
					"Error warning message should be displayed", "Fail");
			AbstractTest.takeSnapShot("Sprint5_US553_TC795_Condition1");
		}
		// click cancel button and verify user should be landed to transfer landing page
		transferlandingpage.CanceTransferPopup_Yes_BT.click();
		wait.until(ExpectedConditions.visibilityOf(transferlandingpage.TransferLandingPage_Label));
		if (transferlandingpage.TransferLandingPage_Label.isDisplayed()) {
			Reporter.reportPassResult(
					browser, "Sprint5_US553_TC795",
					"Transfer Landing Page should be Loaded ", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint5_US553_TC795_Condition2", "Sprint5_US553_TC795",
					"Transfer Landing Page should be Loaded ", "Fail");
			AbstractTest.takeSnapShot("Sprint5_US553_TC795_Condition2");
		}
	}
	
	//Verify print method for each and every transfer record.
	@Test ()
	public void Sprint5_US553_TC874() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException,
			AWTException {
		/* Start-Variable Deceleration */
		HSSFSheet transferLandingPageSheet = ReadTestData.getTestDataSheet("Sprint5_US553_TC874", "Object1");
		String wrinId = ReadTestData.getTestData(transferLandingPageSheet,"WRINId");
		String NationalStoreNo = ReadTestData.getTestData(transferLandingPageSheet, "InputNationalStoreNumber");
		String outerPackQty = ReadTestData.getTestData(transferLandingPageSheet, "OuterPackQty");
		String innerPackQty = ReadTestData.getTestData(transferLandingPageSheet, "InnerPackQty");
		String looseUnitsQty = ReadTestData.getTestData(transferLandingPageSheet, "LooseUnitsQty");
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/* End-Variable Deceleration */
		// create instances for home page and transfer landing page
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		TransferLandingPage transferlandingpage = PageFactory.initElements(driver, TransferLandingPage.class);
		// Enter url & go to transfer landing page
		homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToTransferLandingPage();
		// verify if any submitted record exists or not,if not then create one record.
		if (transferlandingpage.TransferLandingPage_Records_List.size() > 0) {
			// click first submitted transfer
			transferlandingpage.TransferLandingPage_Records_List.get(0).click();
		} else {
			transferlandingpage.createATransferTransaction(wrinId, NationalStoreNo, "OUT", outerPackQty, innerPackQty, looseUnitsQty);
			// click on first submitted record.
			transferlandingpage.TransferLandingPage_Records_List.get(0).click();
		}
		// verify 'print' button is available on submitted transfer page
		wait.until(ExpectedConditions.visibilityOf(transferlandingpage.TransferDetailsPopup_Print_BT));
		boolean printbtnstatus = transferlandingpage.TransferDetailsPopup_Print_BT.isDisplayed();
		if (printbtnstatus) {
			Reporter.reportPassResult(
					browser,"Sprint5_US553_TC874",
					"print button should be available on 'submitted-transfer' page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint5_US553_TC874","Sprint5_US553_TC874",
					"print button should be available on 'submitted-transfer' page",
					"Fail");
			AbstractTest.takeSnapShot("Sprint5_US553_TC874");

		}

		/**************** Code to verify print pop up is still need to add */
	}
	
	//TC971 Verify the impact on "Raw Item Activity" page and "Store Ledger" page, once the transfer is submitted.
	@Test()
	public void Sprint5_US553_TC971() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/* Start-Variable Deceleration */
		HSSFSheet transferLandingPageSheet = ReadTestData.getTestDataSheet("Sprint5_US553_TC971", "Object1");
		String wrinId = ReadTestData.getTestData(transferLandingPageSheet,"WRINId");
		String NationalStoreNo = ReadTestData.getTestData(transferLandingPageSheet, "InputNationalStoreNumber");
		String outerPackQty = ReadTestData.getTestData(transferLandingPageSheet, "OuterPackQty");
		String innerPackQty = ReadTestData.getTestData(transferLandingPageSheet, "InnerPackQty");
		String looseUnitsQty = ReadTestData.getTestData(transferLandingPageSheet, "LooseUnitsQty");
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String actCount;
		String expCount;
		String totalAmount = null;
		String time = null;
		RawItemActivityPage rawItemActivityPage;
		PurchasesPage purchasesPage;
		String date = Base.returnTodayDate();
		/* End-Variable Deceleration */
		// create instances for home page and transfer landing page
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Go to purchase landing page and click on view store ledger button and fetch the number of records
		purchasesPage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPurchaseLandingPage();
		// click on store ledger button
		purchasesPage.ViewStoreLedger_BT.click();
		Thread.sleep(2000);
		// Fetch the amount for the newly added record which is displaying at bottom
		int size = driver.findElements(By.xpath("//table[@id='ledger_table']/tbody/tr")).size();
		// Enter URL & go to transfer landing page
		TransferLandingPage transferlandingpage = homePage.navigateToInventoryManagement().goToTransferLandingPage();
		// Create a transfer out type transaction click on insert new transfer button
		transferlandingpage.CreateNewTransfers_BT.click();
		wait.until(ExpectedConditions.visibilityOf(transferlandingpage.InsertNewTransfersPopup_InputNationalStoreNo_TB));
		transferlandingpage.InsertNewTransfersPopup_InputNationalStoreNo_TB.sendKeys(NationalStoreNo);
		// click on transfer out
		transferlandingpage.InsertNewTransfersPopup_TransferOut_RB.click();
		Thread.sleep(3000);
		// Enter the WRIN Id
		transferlandingpage.AddTransferItemsPopup_RawItemsSearchBox_TB.sendKeys(wrinId);
		driver.findElement(By.xpath("//strong[text()='" + wrinId + "']")).click();
		// Enter the outerPackQty for the food item
		transferlandingpage.AddTransferItemsPopup_OuterPack_TB.sendKeys(outerPackQty);
		try {
			// Enter the innerPackQty for the food item
			transferlandingpage.AddTransferItemsPopup_InnerPack_TB.sendKeys(innerPackQty);
		} catch (Exception e) {
			// innerPackQty is not applicable for the food item
		}
		Thread.sleep(3000);
		// Enter the looseUnitsQty for the food item
		transferlandingpage.AddTransferItemsPopup_LooseUnits_TB.sendKeys(looseUnitsQty);
		// Again click on the outer pack value
		transferlandingpage.AddTransferItemsPopup_OuterPack_TB.click();
		expCount = transferlandingpage.AddTransferItemsPopup_TotalUnits_TB.getAttribute("value");
		System.out.println("Expected Count" + "-" + expCount);
		// Fetch the sub total Value
		wait.until(ExpectedConditions.visibilityOf(transferlandingpage.AddTransferItemsPopup_Add_BT));
		// Add the transfer
		transferlandingpage.AddTransferItemsPopup_Add_BT.click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(transferlandingpage.AddTransferItemsPopup_Submit_BT));
		time=transferlandingpage.InsertNewTransfersPopup_Time_Value.getText().trim();
		totalAmount = transferlandingpage.AddTransferPopup_TotalAmount_Value.getText().split("\\$")[1];
		System.out.println("total amunt" + totalAmount);
		// click on submit button
		transferlandingpage.AddTransferItemsPopup_Submit_BT.click();
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOf(transferlandingpage.TransferLandingPage_Label));
		// Go to Raw Item Activity page
		homePage.Menu_DD_BT.click();
		wait.until(ExpectedConditions.visibilityOf(homePage.RawItemActivity_BT));
		Thread.sleep(3000);
		// click on the raw item activity button
		homePage.RawItemActivity_BT.click();
		Thread.sleep(3000);
		rawItemActivityPage = PageFactory.initElements(driver,RawItemActivityPage.class);
		wait.until(ExpectedConditions.visibilityOf(rawItemActivityPage.RawItemActivity_Label));
		// Search and select the wrin ID
		rawItemActivityPage.searchAndSelectWRINID(wrinId);
		wait.until(ExpectedConditions.visibilityOf(rawItemActivityPage.getItemDetails_Button));
		// click on 'get item details' button
		rawItemActivityPage.getItemDetails_Button.click();
		// verify user is able to view alkl the Information for the selected wRIN ID.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//u[text()='System Date and Time']")));
		Thread.sleep(3000);
		// Fetch the count value
		actCount = driver.findElement(By.xpath("//table[@id='raw_item_detail_table']/tbody/tr[1]/td[4][preceding-sibling::td/span[text()='"+ date + " " + time + "']]/span")).getText().trim();
		System.out.println("Act COunt" + actCount);
		// Verify that correct count value is showing at raw item activity page
		if (actCount.contains("-" + expCount)) {
			Reporter.reportPassResult(
					browser,"Sprint5_US553_TC971",
					"Correct count value should display at raw item activity page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint5_US553_TC971_Condition1","Sprint5_US553_TC971",
					"Correct count value should display at raw item activity page",
					"Fail");
			AbstractTest.takeSnapShot("Sprint5_US553_TC971_Condition1");
		}
		// Go to purchase landing page
		homePage.Menu_DD_BT.click();
		wait.until(ExpectedConditions.visibilityOf(homePage.Menu_Back_BT));
		homePage.Menu_Back_BT.click();
		wait.until(ExpectedConditions.visibilityOf(homePage.Purchases_BT));
		homePage.Purchases_BT.click();
		wait.until(ExpectedConditions.visibilityOf(purchasesPage.Purchases_Label));
		// click on store ledger button
		purchasesPage.ViewStoreLedger_BT.click();
		Thread.sleep(2000);
		// Fetch the amount for the newly added record which is displaying at bottom
		int size1 = driver.findElements(By.xpath("//table[@id='ledger_table']/tbody/tr")).size();
		System.out.println(driver.findElement(By.xpath("//table[@id='ledger_table']/tbody/tr["+ (size1 - 1) + "]/td[4]/span")).getText().trim());
		// Fetch the Actual Amount
		if ((driver.findElement(By.xpath("//table[@id='ledger_table']/tbody/tr[" + (size1 - 1)+ "]/td[4]/span")).getText().trim()).equalsIgnoreCase("-" + totalAmount)	&& size1 == size + 1) {
			Reporter.reportPassResult(
					browser,"Sprint5_US553_TC971",
					"Correct amount should display at store ledger detail page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint5_US553_TC971_Condition2","Sprint5_US553_TC971",
					"Correct amount should display at store ledger detail page",
					"Fail");
			AbstractTest.takeSnapShot("Sprint5_US553_TC971_Condition2");
		}
	}
}
