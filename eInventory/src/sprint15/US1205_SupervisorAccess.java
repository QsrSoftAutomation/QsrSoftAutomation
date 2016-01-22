package sprint15;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
import eInventoryPageClasses.CustomRawItemListsPage;
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.ManualInvoiceNewPage;
import eInventoryPageClasses.ManualVendorsPage;
import eInventoryPageClasses.MenuItemActivityPage;
import eInventoryPageClasses.MenuItemInformationPage;
import eInventoryPageClasses.PhysicalInventoryPage;
import eInventoryPageClasses.PromotionsAndWastePage;
import eInventoryPageClasses.PurchasesPage;
import eInventoryPageClasses.RawItemActivityPage;
import eInventoryPageClasses.StoreLedgerDetailPage;
import eInventoryPageClasses.TransferLandingPage;
import sprint2.AbstractTest;

public class US1205_SupervisorAccess extends AbstractTest
{
	
	//TC2567 User has READ ONLY access to the Physical Inventory pages.
	
	@Test()
	
	public void sprint15_US1205_TC2567() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	{
		/** Variable Section : **/
		String storeId = GlobalVariable.supervisorStoreId;
		String userId = GlobalVariable.supervisorUserId;
		/***********************************/
		//Go to Physical Inventory page
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		PhysicalInventoryPage physicalInventoryPage =homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPhysicalInventoryPage();
		//Verify that functionality of this page is not accessible
		System.out.println(physicalInventoryPage.CreateDailyInventory_BT.getAttribute("disabled"));
		if (Boolean.valueOf(physicalInventoryPage.CreateDailyInventory_BT.getAttribute("disabled"))) {
			Reporter.reportPassResult(
					browser, "sprint15_US1205_TC2567",
					"Physical Inventory page is not enabled for the Supervisor Role User", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint15_US1205_TC2567","sprint15_US1205_TC2567",
					"Physical Inventory page is not enabled for the Supervisor Role User", "Fail");
			AbstractTest.takeSnapShot("sprint15_US1205_TC2567");
		}
		
	}
	
	
	//TC2569 User can use all of the functionality available on the Purchases page.
	
	@Test(enabled=false)
	
	public void sprint15_US1205_TC2569() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	{
		
		/** Variable Section : **/
		String storeId = GlobalVariable.supervisorStoreId;
		String userId = GlobalVariable.supervisorUserId;
//		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("sprint15_US1205_TC2569", "Object1");
		String wrinId = GlobalVariable.createPurchaseWrin1;//ReadTestData.getTestData(manualInvoiceNewPageSheet,"EnterQuickSearchWithSuggestionsForManualPurchases");
		String vendor =GlobalVariable.vendorName;// ReadTestData.getTestData(manualInvoiceNewPageSheet,"Vendor");
		String quantity = "8";
		String invoiceId = Base.randomNumberFiveDigit();
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		PurchasesPage purchasesPage =homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPurchaseLandingPage();
		ManualInvoiceNewPage manualInvoiceNewPage = purchasesPage.goToManualInvoiceNewPage();
		manualInvoiceNewPage.createAManualPurchaseForWrinID(wrinId, vendor, quantity, invoiceId);
		
		// Verify thatuser is able to submit teh manual invoice
		if (true) {
			Reporter.reportPassResult(
					browser, "sprint15_US1205_TC2569",
					"User should be able to submit the manual Invoice", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint15_US1205_TC2569","sprint15_US1205_TC2569",
					"User should be able to submit the manual Invoice", "Fail");
			AbstractTest.takeSnapShot("sprint15_US1205_TC2569");
		}
		
		//Approve button is not working and  needs to complete once Approve button will start working
	}
	
	//TC2570 User can use all of the functionality available on the Purchases page.
	
	@Test()
	
	public void sprint15_US1205_TC2570() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException 
	{
		/** Variable Section : **/
		String storeId = GlobalVariable.supervisorStoreId;
		String userId = GlobalVariable.supervisorUserId;
		/***********************************/
		//Navigate to the purchase landing page
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		PurchasesPage purchasesPage =homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPurchaseLandingPage();
		ManualInvoiceNewPage manualInvoiceNewPage = purchasesPage.goToManualInvoiceNewPage();
		Thread.sleep(5000);
		//Click on 'Cancel' button on the page
		manualInvoiceNewPage.Cancel_BT.click();
		Thread.sleep(4000);
		if(!Base.isElementDisplayed(manualInvoiceNewPage.Cancel_BT))
		{
			Reporter.reportPassResult(
					browser, "sprint15_US1205_TC2570",
					"User should be redirected to Purchase landing page on clicking 'Cancel' button", "Pass");
		}
		else
		{
			Reporter.reportTestFailure(
					browser, "sprint15_US1205_TC2570","sprint15_US1205_TC2570",
					"User should be redirected to Purchase landing page on clicking 'Cancel' button", "Fail");
			AbstractTest.takeSnapShot("sprint15_US1205_TC2570");
		}
				
	}
	
	
	//TC2571 User can use all of the functionality available on the Purchases page.
	
	@Test(enabled=false)
	
	public void sprint15_US1205_TC2571() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException 
	{
		/** Variable Section : **/
		String storeId = GlobalVariable.supervisorStoreId;
		String userId = GlobalVariable.supervisorUserId;
//		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("sprint15_US1205_TC2571", "Object1");
		String wrinId = GlobalVariable.createPurchaseWrin1;// ReadTestData.getTestData(manualInvoiceNewPageSheet,"EnterQuickSearchWithSuggestionsForManualPurchases");
		String vendor = GlobalVariable.vendorName;// ReadTestData.getTestData(manualInvoiceNewPageSheet,"Vendor");
		String quantity = "4";
		String invoiceId = Base.randomNumberFiveDigit();
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		//Navigate to purchase landing page
		PurchasesPage purchasesPage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPurchaseLandingPage();
		//Click on create new invoice button 
		ManualInvoiceNewPage manualInvoiceNewPage = purchasesPage.goToManualInvoiceNewPage();
		//Create a new purchase
		manualInvoiceNewPage.createAManualPurchaseForWrinID(wrinId, vendor,quantity, invoiceId);
		//Click on approve button for the same purchase
		homePage.Menu_DD_BT.click();
		wait.until(ExpectedConditions.elementToBeClickable(homePage.Purchases_BT)).click();
		Thread.sleep(2000);
		manualInvoiceNewPage.clickOnApproveButtonForManualPurchase(invoiceId);
		//click on the approve button
		wait.until(ExpectedConditions.visibilityOf(purchasesPage.ManualInvoiceApprove_BT)).click();
		// Verify that manual invoice is approved
		if (true) {
			Reporter.reportPassResult(browser, "sprint15_US1205_TC2571",
					"Operator should be able to edit and finalize a pending purchase", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint15_US1205_TC2571","sprint15_US1205_TC2571",
					"Operator should be able to edit and finalize a pending purchase", "Fail");
			AbstractTest.takeSnapShot("sprint15_US1205_TC2571");
		}
		
		//Approve button is not working so not proceed it further
		
		
	
	}
	
	
	//TC252572 User can use all of the functionality available on the Purchases page.
	
	@Test()
	
	public void sprint15_US1205_TC2572() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException 
	{
		/** Variable Section : **/
		String storeId = GlobalVariable.supervisorStoreId;
		String userId = GlobalVariable.supervisorUserId;
//		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet(
//				"sprint15_US1205_TC2572", "Object1");
		String wrinId = GlobalVariable.createPurchaseWrin1;// ReadTestData.getTestData(manualInvoiceNewPageSheet,"EnterQuickSearchWithSuggestionsForManualPurchases");
		String vendor = GlobalVariable.vendorName;// ReadTestData.getTestData(manualInvoiceNewPageSheet,"Vendor");
		String quantity = "5";
		String invoiceId = Base.randomNumberFiveDigit();
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		//Navigate to purchase landing page
		PurchasesPage purchasesPage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPurchaseLandingPage();
		//Click on create new invoice button 
		ManualInvoiceNewPage manualInvoiceNewPage = purchasesPage.goToManualInvoiceNewPage();
		//Create a new purchase
		manualInvoiceNewPage.createAManualPurchaseForWrinID(wrinId, vendor,quantity, invoiceId);
		//Click on approve button for the same purchase
		homePage.Menu_DD_BT.click();
		wait.until(ExpectedConditions.elementToBeClickable(homePage.Purchases_BT)).click();
		Thread.sleep(2000);
		manualInvoiceNewPage.clickOnApproveButtonForManualPurchase(invoiceId);
		//Click on the delete button
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceNewPage.ManualInvoiceDelete_BT)).click();
		//click on the Yes button on confirmation pop up
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceNewPage.ManualInvoiceDelete_ConfirmationPopUp_Yes_BT)).click();
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceNewPage.InvoiceDeleted_Confirmation_MSG));
		Thread.sleep(5000);
		// Verify that manual purchase should be deleted from the purchase page
		if (!manualInvoiceNewPage.verifyManualInvoiceIsDisplayed(invoiceId)) {
			Reporter.reportPassResult(
					browser, "sprint15_US1205_TC2572",
					"Operator is able to delete a purchase", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint15_US1205_TC2572","sprint15_US1205_TC2572",
					"Operator is able to delete a purchase", "Fail");
			AbstractTest.takeSnapShot("sprint15_US1205_TC2572");
		}
	}
	
	
	//TC2574 To Verify if the Supervisor is able to view store ledger
	
	
	@Test()
	
	public void sprint15_US1205_TC2574() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	{
		/** Variable Section : **/
		String storeId = GlobalVariable.supervisorStoreId;
		String userId = GlobalVariable.supervisorUserId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		//Navigate to purchase landing page
		PurchasesPage purchasesPage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPurchaseLandingPage();
		//click on view ledger button
		StoreLedgerDetailPage storeLedgerDetailPage = purchasesPage.clickOnViewStoreLedgerButton();
		//select last month from the dropdown
//		storeLedgerDetailPage.selectMonthFromStoreLedgerDrpDwn(Base.getMonthFromDate(Base.returnTodayDate()), Base.getYearFromDate(Base.returnTodayDate()));
		Thread.sleep(5000);
		// Verify that operator should be able to view ledger details for the selected month
		if (storeLedgerDetailPage.verifyDataForSelectedMonth(Base.getMonthFromDate(Base.returnTodayDate()), Base.getYearFromDate(Base.returnTodayDate()))){
			Reporter.reportPassResult(
					browser, "sprint15_US1205_TC2574",
					"Operator is able to view store ledger", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint15_US1205_TC2574","sprint15_US1205_TC2574",
					"Operator is able to view store ledger", "Fail");
			AbstractTest.takeSnapShot("sprint15_US1205_TC2574");
		}
	}
	
	
	//TC2575 To Verify if the Supervisor is able to restore purchase
	
	@Test()
	
	public void sprint15_US1205_TC2575() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	{
	
		/** Variable Section : **/
		String storeId = GlobalVariable.supervisorStoreId;
		String userId = GlobalVariable.supervisorUserId;
//		HSSFSheet manualInvoiceNewPageSheet = ReadTestData.getTestDataSheet("sprint15_US1205_TC2575", "Object1");
		String wrinId = GlobalVariable.createPurchaseWrin1;// ReadTestData.getTestData(manualInvoiceNewPageSheet,"EnterQuickSearchWithSuggestionsForManualPurchases");
		String vendor = GlobalVariable.vendorName;// ReadTestData.getTestData(manualInvoiceNewPageSheet,"Vendor");
		String quantity = "4";
		String invoiceId = Base.randomNumberFiveDigit();
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		//Navigate to purchase landing page
		PurchasesPage purchasesPage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPurchaseLandingPage();
		//Click on create new invoice button 
		ManualInvoiceNewPage manualInvoiceNewPage = purchasesPage.goToManualInvoiceNewPage();
		//Create a new purchase
		manualInvoiceNewPage.createAManualPurchaseForWrinID(wrinId, vendor,quantity, invoiceId);
		//Click on approve button for the same purchase
		homePage.Menu_DD_BT.click();
		wait.until(ExpectedConditions.elementToBeClickable(homePage.Purchases_BT)).click();
		Thread.sleep(2000);
		manualInvoiceNewPage.clickOnApproveButtonForManualPurchase(invoiceId);
		//Click on the delete button
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceNewPage.ManualInvoiceDelete_BT)).click();
		//click on the Yes button on confirmation pop up
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceNewPage.ManualInvoiceDelete_ConfirmationPopUp_Yes_BT)).click();
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceNewPage.InvoiceDeleted_Confirmation_MSG));
		Thread.sleep(5000);
		boolean manualInvoiceDeleted = !(manualInvoiceNewPage.verifyManualInvoiceIsDisplayed(invoiceId));
		//click on restore purchases button
		purchasesPage.RestorePurchases_BT.click();
		//restore the manual invoice with the same invoice id
		purchasesPage.restoreDeletedPurchases(invoiceId);
		// Verify that manual invoice should be restored 
		if (manualInvoiceDeleted & manualInvoiceNewPage.verifyManualInvoiceIsDisplayed(invoiceId)) {
			Reporter.reportPassResult(
					browser, "sprint15_US1205_TC2575",
					"Operator is able to restore purchase", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint15_US1205_TC2575","sprint15_US1205_TC2575",
					"Operator is able to restore purchase", "Fail");
			AbstractTest.takeSnapShot("sprint15_US1205_TC2575");
		}
		
		
		
		
	}
	
	
	//TC2576 User can use some of the functionality available on the Promotions and Waste page.
	
	@Test()
	
	public void sprint15_US1205_TC2576() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException 
	{
		/** Variable Section : **/
		String storeId = GlobalVariable.supervisorStoreId;
		String userId = GlobalVariable.supervisorUserId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Promotion and waste page
		PromotionsAndWastePage promotionAndWastePage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPromotionsAndWastePage();
		// Verify that operator should not be able to view Raw Waste button on promotion and waste page
		if (!Base.isElementDisplayed(promotionAndWastePage.RawWaste_BT)) {
			Reporter.reportPassResult(
					browser, "sprint15_US1205_TC2576",
					"Operator is restricted from entering raw waste", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint15_US1205_TC2576","sprint15_US1205_TC2576",
					"Operator is restricted from entering raw waste", "Fail");
			AbstractTest.takeSnapShot("sprint15_US1205_TC2576");
		}
	}
	
	
	//TC2577 Verify that Supervisor is restricted from entering completed waste
		
	@Test()
	
	public void sprint15_US1205_TC2577() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	{
		/** Variable Section : **/
		String storeId = GlobalVariable.supervisorStoreId;
		String userId = GlobalVariable.supervisorUserId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Promotion and waste page
		PromotionsAndWastePage promotionAndWastePage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPromotionsAndWastePage();
		// Verify that operator should not be able to view Completed Waste button on promotion and waste page
		if (!Base.isElementDisplayed(promotionAndWastePage.CompletedWaste_BT)) {
			Reporter.reportPassResult(
					browser, "sprint15_US1205_TC2577",
					"Operator is restricted from entering completed waste", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint15_US1205_TC2577","sprint15_US1205_TC2577",
					"Operator is restricted from entering completed waste", "Fail");
			AbstractTest.takeSnapShot("sprint15_US1205_TC2577");
		}
	}
	
	//TC2580 To Verify if the Supervisor is able to transfer raw items in/out to other stores.
		
	@Test()
	
	public void sprint15_US1205_TC2580() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	{
		
		/** Variable Section : **/
		String storeId = GlobalVariable.supervisorStoreId;
		String userId = GlobalVariable.supervisorUserId;
		HSSFSheet transferLandingPageSheet = ReadTestData.getTestDataSheet("sprint15_US1205_TC2580", "Object1");
		String samplewRINID = ReadTestData.getTestData(transferLandingPageSheet,"WRINId");
		String transferType = ReadTestData.getTestData(transferLandingPageSheet,"TransferType");
		String transferStoreNumber = ReadTestData.getTestData(transferLandingPageSheet,"InputNationalStoreNumber");
		String caseQuantity = ReadTestData.getTestData(transferLandingPageSheet,"OuterPackQty");
		String innerPackQuantity =ReadTestData.getTestData(transferLandingPageSheet,"InnerPackQty");
		String looseUnitQuantity =ReadTestData.getTestData(transferLandingPageSheet,"LooseUnitsQty");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		TransferLandingPage transferLandingPage = PageFactory.initElements(driver, TransferLandingPage.class);
		//Navigate to Transfer Landing page and click on create new transfer button
		homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToTransferLandingPage().CreateNewTransfers_BT.click();
		wait.until(ExpectedConditions.visibilityOf(transferLandingPage.InsertNewTransfersPopup_InputNationalStoreNo_TB));
		//Get the time of transfer
		String time=transferLandingPage.InsertNewTransfersPopup_Time_Value.getText().trim();
		//Select the transfer type as "in" and select the store from dropdown an add the transfer details
		System.out.println("Type is"+transferType);
		transferLandingPage.selectTransferType(transferType)
				.selectLocationToTransfer(transferStoreNumber).insertAndAddDetailsToTransfer(samplewRINID, caseQuantity, innerPackQuantity,looseUnitQuantity);
		Thread.sleep(2000);
		//Verify that cancel and print button are displayed  
		boolean result = Base.isElementDisplayed(transferLandingPage.AddTransferItemsPopup_Cancel_BT)
				& Base.isElementDisplayed(transferLandingPage.AddTransferItemsPopup_Print_BT);
		//Get the total transfer amount
		String amount = transferLandingPage.AddTransferPopup_TotalAmount_Value.getText().split("\\$")[1];
		//Submit the transfer
		transferLandingPage.AddTransferItemsPopup_Submit_BT.click();
		//click on the yes button for confirmation
		wait.until(ExpectedConditions.elementToBeClickable(transferLandingPage.SubmitTransferConfirmationPopUp_Yes_BT));
		transferLandingPage.SubmitTransferConfirmationPopUp_Yes_BT.click();
		Thread.sleep(5000);
		//Verify that transfer entries should displayed in Transfer landing page
		if (result & transferLandingPage.verifyTransferPlaced(Base.returnTodayDate(), time, amount)) {
			Reporter.reportPassResult(
					browser,"sprint15_US1205_TC2580",
					"User should be able to transfer raw items in/out to other stores and able to view print button",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint15_US1205_TC2580","sprint15_US1205_TC2580",
					"User should be able to transfer raw items in/out to other stores and able to view print button",
					"Fail");
			AbstractTest.takeSnapShot("sprint15_US1208_TC2643_Condition1");
		}
		int caseQty2 = Integer.parseInt(caseQuantity) + 1;
		int innerPackQty2 = Integer.parseInt(innerPackQuantity) + 1;
		int looseUnitsQty2 = Integer.parseInt(looseUnitQuantity) + 1;
		Thread.sleep(4000);
		//click on Create new Transfer button
//		transferLandingPage.BackToTop_BT.click();
		transferLandingPage.CreateNewTransfers_BT.click();
		wait.until(ExpectedConditions.visibilityOf(transferLandingPage.InsertNewTransfersPopup_InputNationalStoreNo_TB));
		//Get the time of transfer
		String time2=transferLandingPage.InsertNewTransfersPopup_Time_Value.getText().trim();
		//Select the transfer type as "in" and select the store from dropdown an add the transfer details
		transferLandingPage.selectTransferType(transferType).insertAndAddDetailsToTransfer(samplewRINID,
				String.valueOf(caseQty2), String.valueOf(innerPackQty2),String.valueOf(looseUnitsQty2));
		//Get the amount
		String amount2 = transferLandingPage.AddTransferPopup_TotalAmount_Value.getText().split("\\$")[1];
		System.out.println("amount "+amount2);
		//click on cancel button
		transferLandingPage.AddTransferItemsPopup_Cancel_BT.click();
		wait.until(ExpectedConditions.visibilityOf(transferLandingPage.AddTransferItemsPopup_Warning_Message_Yes_BT)).click();
		Thread.sleep(2000);
		// Verify that user should be able to cancel transfer and transfer entries should not displayed in transfer page 
		if (!transferLandingPage.verifyTransferPlaced(Base.returnTodayDate(),time2, amount2)) {
			Reporter.reportPassResult(browser, "sprint15_US1205_TC2580",
					"Supervisor should be able to cancel transfer for raw items in/out to other stores",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser, "sprint15_US1205_TC2580","sprint15_US1205_TC2580",
					"Supervisor should be able to cancel transfer for raw items in/out to other stores",
					"Fail");
			AbstractTest.takeSnapShot("sprint15_US1205_TC2580_Condition2");
		}
		
		
		
		
	}
	
	
	//TC2581 To Verify if the Supervisor is able to transfer raw items to office .
	
		@Test()
		
		public void sprint15_US1205_TC2581() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException 
		{
			
			/** Variable Section : **/
			String storeId = GlobalVariable.supervisorStoreId;
			String userId = GlobalVariable.supervisorUserId;
			HSSFSheet transferLandingPageSheet = ReadTestData.getTestDataSheet("sprint15_US1205_TC2581", "Object1");
			String samplewRINID = ReadTestData.getTestData(transferLandingPageSheet,"WRINId");
			String transferType = ReadTestData.getTestData(transferLandingPageSheet,"TransferType");
			String caseQuantity = ReadTestData.getTestData(transferLandingPageSheet,"OuterPackQty");
			String innerPackQuantity =ReadTestData.getTestData(transferLandingPageSheet,"InnerPackQty");
			String looseUnitQuantity =ReadTestData.getTestData(transferLandingPageSheet,"LooseUnitsQty");
			/***********************************/
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			TransferLandingPage transferLandingPage = PageFactory.initElements(driver, TransferLandingPage.class);
			//Navigate to Transfer Landing page and click on create new transfer button
			homePage.selectUser(userId).selectLocation(storeId)
					.navigateToInventoryManagement().goToTransferLandingPage().CreateNewTransfers_BT.click();
			wait.until(ExpectedConditions.visibilityOf(transferLandingPage.InsertNewTransfersPopup_InputNationalStoreNo_TB));
			//Get the time of transfer
			String time = transferLandingPage.InsertNewTransfersPopup_Time_Value.getText().trim();
			//Select the transfer type as "Office" and add the transfer details
			transferLandingPage.selectTransferType(transferType).insertAndAddDetailsToTransfer(samplewRINID, caseQuantity, innerPackQuantity,looseUnitQuantity);
			Thread.sleep(2000);
			//Verify that cancel and print button are displayed  
			boolean result = Base.isElementDisplayed(transferLandingPage.AddTransferItemsPopup_Cancel_BT)
					& Base.isElementDisplayed(transferLandingPage.AddTransferItemsPopup_Print_BT);
			//Get the total transfer amount
			String amount = transferLandingPage.AddTransferPopup_TotalAmount_Value.getText().split("\\$")[1];
			//Submit the transfer
			transferLandingPage.AddTransferItemsPopup_Submit_BT.click();
			//click on the yes button for confirmation
			wait.until(ExpectedConditions.elementToBeClickable(transferLandingPage.SubmitTransferConfirmationPopUp_Yes_BT));
			transferLandingPage.SubmitTransferConfirmationPopUp_Yes_BT.click();
			Thread.sleep(5000);
			//Verify that transfer entries should displayed in Transfer landing page
			if (result& transferLandingPage.verifyTransferPlaced(Base.returnTodayDate(), time, amount)) {
				Reporter.reportPassResult(
						browser,"sprint15_US1205_TC2581",
						"Supervisor should be able to submit transfer to office",
						"Pass");
			} else {
				Reporter.reportTestFailure(
						browser,"sprint15_US1205_TC2581","sprint15_US1205_TC2581",
						"Supervisor should be able to submit transfer to office",
						"Fail");
				AbstractTest.takeSnapShot("sprint15_US1205_TC2581");
			}
		}
	
	
		//TC2582 : To Verify if the Supervisor is able to view and print raw items transfer details
		@Test()
		public void sprint15_US1205_TC2582() throws RowsExceededException,
				BiffException, WriteException, IOException, InterruptedException {
			/** Variable Section : **/
			String storeId = GlobalVariable.supervisorStoreId;
			String userId = GlobalVariable.supervisorUserId;
			HSSFSheet transferLandingPageSheet = ReadTestData.getTestDataSheet("sprint15_US1205_TC2582", "Object1");
			String samplewRINID = ReadTestData.getTestData(transferLandingPageSheet,"WRINId");
			String transferType = ReadTestData.getTestData(transferLandingPageSheet,"TransferType");
			String caseQuantity = ReadTestData.getTestData(transferLandingPageSheet,"OuterPackQty");
			String innerPackQuantity =ReadTestData.getTestData(transferLandingPageSheet,"InnerPackQty");
			String looseUnitQuantity =ReadTestData.getTestData(transferLandingPageSheet,"LooseUnitsQty");
			/***********************************/
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			TransferLandingPage transferLandingPage = PageFactory.initElements(driver, TransferLandingPage.class);
			//Navigate to Transfer Landing page and click on create new transfer button
			homePage.selectUser(userId).selectLocation(storeId)
					.navigateToInventoryManagement().goToTransferLandingPage().CreateNewTransfers_BT.click();
			wait.until(ExpectedConditions.visibilityOf(transferLandingPage.InsertNewTransfersPopup_InputNationalStoreNo_TB));
			//Get the time of transfer
			String time = transferLandingPage.InsertNewTransfersPopup_Time_Value.getText().trim();
			//Select the transfer type as "Office" and add the transfer details
			transferLandingPage.selectTransferType(transferType)
					.insertAndAddDetailsToTransfer(samplewRINID, caseQuantity,innerPackQuantity, looseUnitQuantity);
			Thread.sleep(2000);
			//Get the total transfer amount
			String amount = transferLandingPage.AddTransferPopup_TotalAmount_Value.getText().split("\\$")[1];
			//Submit the transfer
			transferLandingPage.AddTransferItemsPopup_Submit_BT.click();
			//click on the yes button for confirmation
			wait.until(ExpectedConditions.elementToBeClickable(transferLandingPage.SubmitTransferConfirmationPopUp_Yes_BT));
			transferLandingPage.SubmitTransferConfirmationPopUp_Yes_BT.click();
			Thread.sleep(5000);
			//View the transfer entry
			transferLandingPage.viewTransfer(Base.returnTodayDate(), time, amount);
			wait.until(ExpectedConditions.visibilityOf(transferLandingPage.ViewTransferItemsPopup_GrandTotal_Value));
			//Verify that user is able to view the transfer entry details
			if (transferLandingPage.ViewTransferItemsPopup_GrandTotal_Value.getText().equals(amount)
					& Base.isElementDisplayed(transferLandingPage.ViewTransferItemsPopup_Print_BT)) {
				Reporter.reportPassResult(
						browser, "sprint15_US1205_TC2582",
						"Supervisor is able to view raw items transfer details and able to view print button", "Pass");
			} else {
				Reporter.reportTestFailure(
						browser, "sprint15_US1205_TC2582","sprint15_US1208_TC2582",
						"Supervisor is able to view raw items transfer details and able to view print button", "Fail");
				AbstractTest.takeSnapShot("sprint15_US1208_TC2582");
			}
		}
	
	
		//TC2585:To verify if the Supervisor is able to view the raw item activity details
		@Test()
		public void sprint15_US1205_TC2585() throws RowsExceededException,
				BiffException, WriteException, IOException, InterruptedException {
			/** Variable Section : **/
			String storeId = GlobalVariable.supervisorStoreId;
			String userId = GlobalVariable.supervisorUserId;
			String wrinId =GlobalVariable.rawItemActivityWrin;
			/***********************************/
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			// Navigate to Raw Item Activity Page
			RawItemActivityPage rawItemActivityPage = homePage.selectUser(userId)
					.selectLocation(storeId).navigateToInventoryManagement().goToRawItemActivityPage().searchAndSelectWRINID(wrinId);
			Calendar cal1 = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			// Get start date as month start date
			cal1.set(Calendar.DATE, 1);
	        String startDate = dateFormat.format(cal1.getTime());
	        //Get end date as today date
	        Calendar cal2 = Calendar.getInstance();
	        cal2.add(Calendar.DATE, -0);
	        String endDate = dateFormat.format(cal2.getTime());
	        //Select start Date
	        rawItemActivityPage.StartDate_BT.click();
	        Thread.sleep(1000);
	        rawItemActivityPage.selectStartDate(startDate);
	        //Select End Date
	        rawItemActivityPage.EndDate_BT.click();
	        Thread.sleep(1000);
	        rawItemActivityPage.selectEndDate(endDate);
	        //CLick on get details button
	        rawItemActivityPage.clickOngetItemDetailButton();
	        wait.until(ExpectedConditions.visibilityOf(rawItemActivityPage.DoneWithThisItem_BT));
	        //Verify that raw item activity details are displayed for the selected raw item
	        boolean result = rawItemActivityPage.rawItemDetailList.size()>0;
	        //Click on DoneWithThis Item button
	        rawItemActivityPage.DoneWithThisItem_BT.click();
	        wait.until(ExpectedConditions.visibilityOf(rawItemActivityPage.Search_TB));
			// Verify that user is navigated to Raw item information Page
			if (result & Base.isElementDisplayed(rawItemActivityPage.Search_TB)) {
				Reporter.reportPassResult(
						browser,"sprint15_US1205_TC2585",
						"Supervisor is able to view the raw item activity deatails",
						"Pass");
			} else {
				Reporter.reportTestFailure(
						browser,"sprint15_US1205_TC2585","sprint15_US1205_TC2585",
						"Supervisor is able to view the raw item activity deatails",
						"Fail");
				AbstractTest.takeSnapShot("sprint15_US1205_TC2585");
			}
		}
	
	
		//TC2586 : To verify if the Supervisor is able to view the details of waste/promo on the raw item activity view page
		@Test()
		public void sprint15_US1205_TC2586() throws RowsExceededException,
				BiffException, WriteException, IOException, InterruptedException {
			/** Variable Section : **/
			String storeId = GlobalVariable.supervisorStoreId;
			String userId = GlobalVariable.supervisorUserId;
//			HSSFSheet rawItemActivitySheet = ReadTestData.getTestDataSheet("sprint15_US1205_TC2586", "Object1");
			String wrinId = GlobalVariable.rawItemActivityWrin;
			/***********************************/
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			// Navigate to Raw Item Activity Page
			RawItemActivityPage rawItemActivityPage = homePage.selectUser(userId)
					.selectLocation(storeId).navigateToInventoryManagement().goToRawItemActivityPage().searchAndSelectWRINID(wrinId);
			Calendar cal1 = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			// Get start date as current month start date
			cal1.set(Calendar.DAY_OF_MONTH, 1);
			String startDate = dateFormat.format(cal1.getTime());
			// Get end date today date
			Calendar cal2 = Calendar.getInstance();
			cal2.add(Calendar.DATE, -0);
			String endDate = dateFormat.format(cal2.getTime());
			//Enter start date
			rawItemActivityPage.StartDate_BT.click();
			Thread.sleep(1000);
			rawItemActivityPage.selectStartDate(startDate);
			//Select end date
			rawItemActivityPage.EndDate_BT.click();
			Thread.sleep(1000);
			rawItemActivityPage.selectEndDate(endDate);
			//CLick on getItem Detail button
			rawItemActivityPage.clickOngetItemDetailButton();
			wait.until(ExpectedConditions.visibilityOf(rawItemActivityPage.DoneWithThisItem_BT));
			//Verify that raw item activity details are displayed for the selected raw item
			boolean result = rawItemActivityPage.rawItemDetailList.size() > 0;
			//click on the waste item details for the raw item
			rawItemActivityPage.clickOnWasteDetails();
			//Verify that waste details are displayed for the selected raw item
			wait.until(ExpectedConditions.visibilityOf(rawItemActivityPage.WasteDetailPopUp_WasteDetailList));
			result = result & rawItemActivityPage.WasteDetailPopUp_WasteDetailList.getText().contains(wrinId);
			//Click on the close button on waste details pop up
			rawItemActivityPage.ActivityDetailPopUp_Close_BT.click();
			//Click on DoneWithThisItem  button
			wait.until(ExpectedConditions.visibilityOf(rawItemActivityPage.DoneWithThisItem_BT));
			rawItemActivityPage.DoneWithThisItem_BT.click();
			// Verify that user is navigated to Raw item information Page
			wait.until(ExpectedConditions.visibilityOf(rawItemActivityPage.Search_TB));
			if (result & Base.isElementDisplayed(rawItemActivityPage.Search_TB)) {
				Reporter.reportPassResult(
						browser,"sprint15_US1205_TC2586",
						"Supervisor should be able to view the details of waste/promo on the raw item activity view page",
						"Pass");
			} else {
				Reporter.reportTestFailure(
						browser,"sprint15_US1205_TC2586","sprint15_US1205_TC2586",
						"Supervisor user should be able to view the details of waste/promo on the raw item activity view page",
						"Fail");
				AbstractTest.takeSnapShot("sprint15_US1205_TC2586");
			}
		}
	
	
		//TC2588 : To verify if the Supervisor is able to use all the functionality on the Menu Item Information page.
		@Test()
		public void sprint15_US1205_TC2588() throws RowsExceededException,
				BiffException, WriteException, IOException, InterruptedException {
			/** Variable Section : **/
			String storeId = GlobalVariable.supervisorStoreId;
			String userId = GlobalVariable.supervisorUserId;
			HSSFSheet menuItemActivityPageSheet = ReadTestData.getTestDataSheet("sprint15_US1205_TC2588", "Object1");
			String menuItemNumber = ReadTestData.getTestData(menuItemActivityPageSheet, "MenuItemNumber");
			String menuItemDescription = ReadTestData.getTestData(menuItemActivityPageSheet, "MenuItemDescription");
			/***********************************/
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			MenuItemInformationPage menuInformationPage = PageFactory.initElements(driver, MenuItemInformationPage.class);
			// Navigate to Raw Item Activity Page
			MenuItemActivityPage menuItemActivityPage = homePage.selectUser(userId)
					.selectLocation(storeId).navigateToInventoryManagement().goToMenuItemActivityPage();
			// Search and select menu Item using menu Item Number = 1
			menuItemActivityPage.searchAndSelectMenuItem(menuItemNumber);
			wait.until(ExpectedConditions
					.visibilityOf(menuItemActivityPage.MiaStartDate_BT));
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			// get Start date as current month start date
			Calendar cal1 = Calendar.getInstance();
			cal1.set(Calendar.DAY_OF_MONTH, 1);
			String startDate = dateFormat.format(cal1.getTime());
			// Get end Date as yesterday date
			Calendar cal2 = Calendar.getInstance();
			cal2.add(Calendar.DATE, -1);
			String endDate = dateFormat.format(cal2.getTime());
			// enter start date
			menuItemActivityPage.enterDateInMenuItemStartDate(startDate);
			// enter end date
			menuItemActivityPage.enterDateInMenuItemEndDate(endDate);
			// Click on search button
			menuItemActivityPage.ShowResults_BT.click();
			wait.until(ExpectedConditions.visibilityOf(menuItemActivityPage.SelectItem_Value));
			menuItemActivityPage.Information_BT.click();
			wait.until(ExpectedConditions.visibilityOf(menuInformationPage.MenuItemInformation_Title));
			if (menuInformationPage.verifyMenuItemInfoPageLoaded(menuItemNumber, menuItemDescription)) {
				Reporter.reportPassResult(
						browser,"sprint15_US1205_TC2588",
						"Supervisor should be able to view item page with the information of the menu item",
						"Pass");
			} else {
				Reporter.reportTestFailure(
						browser,"sprint15_US1205_TC2588","sprint15_US1205_TC2588",
						"Supervisor user should be able to view item page with the information of the menu item",
						"Fail");
				AbstractTest.takeSnapShot("sprint15_US1205_TC2588");
			}
		}
	
	
		//TC2589 : To verify if the Supervisor is able to use all the functionality on the Menu Item activity page.
		@Test()
		public void sprint15_US1205_TC2589() throws RowsExceededException,
				BiffException, WriteException, IOException, InterruptedException {
			/** Variable Section : **/
			String storeId = GlobalVariable.supervisorStoreId;
			String userId = GlobalVariable.supervisorUserId;
			HSSFSheet menuItemActivityPageSheet = ReadTestData.getTestDataSheet("sprint8_US603_TC1632", "Object1");
			String menuItemNumber = ReadTestData.getTestData(menuItemActivityPageSheet, "MenuItemNumber");
			String startTime = ReadTestData.getTestData(menuItemActivityPageSheet, "CustomStartTime");
			/***********************************/
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			// Navigate to Raw Item Activity Page
			MenuItemActivityPage menuItemActivityPage = homePage.selectUser(userId)
					.selectLocation(storeId).navigateToInventoryManagement().goToMenuItemActivityPage();
			// Search and select menu Item using menu Item Number = 1
			menuItemActivityPage.searchAndSelectMenuItem(menuItemNumber);
			wait.until(ExpectedConditions.visibilityOf(menuItemActivityPage.MiaStartDate_BT));
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			// get Start date as current month start date
			Calendar cal1 = Calendar.getInstance();
			cal1.set(Calendar.DAY_OF_MONTH, 1);
			String startDate = dateFormat.format(cal1.getTime());
			// Get end Date as yesterday date
			Calendar cal2 = Calendar.getInstance();
			cal2.add(Calendar.DATE, -1);
			String endDate = dateFormat.format(cal2.getTime());
			// Get current hour
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH");
			Calendar cal3 = Calendar.getInstance();
			cal3.add(Calendar.DATE, 0);
			String currentHour = dateFormat2.format(cal3.getTime());
			// Get end time as 2 hour later from current time
			String endTime = String.valueOf(Integer.parseInt(currentHour) + 2)+ ":00";
			// enter start date
			menuItemActivityPage.enterDateInMenuItemStartDate(startDate);
			// enter end date
			menuItemActivityPage.enterDateInMenuItemEndDate(endDate);
			// enter start time
			menuItemActivityPage.selectStartTime(startTime);
			// enter end time
			menuItemActivityPage.selectEndTime(endTime);
			// Click on search button
			menuItemActivityPage.ShowResults_BT.click();
			wait.until(ExpectedConditions.visibilityOf(menuItemActivityPage.SelectItem_Value));
			if (menuItemActivityPage.verifyMenuActivityTimeForSelectedDateRange(startDate, endDate, startTime, endTime)) {
				Reporter.reportPassResult(
						browser,"sprint15_US1205_TC2589",
						"Supervisor should be able to view  the list of menu item activity for the selected menu item, date range and time range",
						"Pass");
			} else {
				Reporter.reportTestFailure(
						browser,"sprint15_US1205_TC2589","sprint15_US1205_TC2589",
						"Supervisor user should be able to view  the list of menu item activity for the selected menu item, date range and time range",
						"Fail");
				AbstractTest.takeSnapShot("sprint15_US1205_TC2589");
			}
		}
	
		//TC2591: To verify if the Supervisor is able to add a custom list
		@Test()
		public void sprint15_US1205_TC2591() throws RowsExceededException,
				BiffException, WriteException, IOException, InterruptedException {
			/** Variable Section : **/
			String storeId = GlobalVariable.supervisorStoreId;
			String userId = GlobalVariable.supervisorUserId;
			String rawItemListName = "Test" + Base.generateNdigitRandomNumber(3);
			/***********************************/
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			// Go to Custom raw Item list page
			CustomRawItemListsPage customRawItemListsPage = homePage.selectUser(userId).selectLocation(storeId)
					.navigateToInventoryManagement().goToCustomRawItemListsPage();
			// create new Custom List
			customRawItemListsPage.createACustomRawList(rawItemListName);
			boolean result = Base.isElementDisplayed(customRawItemListsPage.ChangesSaved_Confirmation_MSG);
			// verify that custom list is created and displayed in custom Raw Item list page
			if (result && customRawItemListsPage.verifyCustomListIsDisplayed(rawItemListName)) {
				Reporter.reportPassResult(
						browser, "sprint15_US1205_TC2591",
						"Supervisor should be able to add a custom list", "Pass");

			} else {
				Reporter.reportTestFailure(
						browser, "sprint15_US1205_TC2591","sprint15_US1205_TC2591",
						"Supervisor should be able to add a custom list", "Fail");
				AbstractTest.takeSnapShot("sprint15_US1205_TC2591");
			}
		}
		
	
		//TC2592: To verify if the Supervisor is able to cancel a custom list add
		@Test()
		public void sprint15_US1205_TC2592() throws RowsExceededException,
				BiffException, WriteException, IOException, InterruptedException {
			/** Variable Section : **/
			String storeId = GlobalVariable.supervisorStoreId;
			String userId = GlobalVariable.supervisorUserId;
			String rawItemListName = "Test" + Base.generateNdigitRandomNumber(3);
			/***********************************/
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			// Go to Custom raw Item list page
			CustomRawItemListsPage customRawItemListsPage = homePage.selectUser(userId).selectLocation(storeId)
					.navigateToInventoryManagement().goToCustomRawItemListsPage();
			// click add list button
			customRawItemListsPage.AddList_BT.click();
			wait.until(ExpectedConditions.visibilityOf(customRawItemListsPage.CreateNewRawItemListPopup_Name_TB));
			Thread.sleep(2000);
			// enter custom list name
			customRawItemListsPage.CreateNewRawItemListPopup_Name_TB.sendKeys(rawItemListName);
			// click on cancel button
			customRawItemListsPage.CreateNewRawItemListPopup_Cancel_BT.click();
			// verify that custom list should not be added in custom Raw Item list page
			if (!customRawItemListsPage.verifyCustomListIsDisplayed(rawItemListName)) {
				Reporter.reportPassResult(
						browser, "sprint15_US1205_TC2592",
						"User should be  able to cancel a custom list add", "Pass");
			} else {
				Reporter.reportTestFailure(
						browser, "sprint15_US1205_TC2592","sprint15_US1205_TC2592",
						"User should be  able to cancel a custom list add", "Fail");
				AbstractTest.takeSnapShot("sprint15_US1205_TC2592");
			}
		}
	
	
		//TC2593: To verify if the Supervisor is able to delete a custom list
		@Test()
		public void sprint15_US1205_TC2593() throws RowsExceededException,
				BiffException, WriteException, IOException, InterruptedException {
			/** Variable Section : **/
			String storeId = GlobalVariable.supervisorStoreId;
			String userId = GlobalVariable.supervisorUserId;
			String rawItemListName = "Test" + Base.generateNdigitRandomNumber(3);
			/***********************************/
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			// Go to Custom raw Item list page
			CustomRawItemListsPage customRawItemListsPage = homePage.selectUser(userId).selectLocation(storeId)
					.navigateToInventoryManagement().goToCustomRawItemListsPage();
			// create new custom list
			customRawItemListsPage.createACustomRawList(rawItemListName);
			// verify new list is added and displayed
			boolean result = customRawItemListsPage.verifyCustomListIsDisplayed(rawItemListName);
			// Delete the added custom list
			customRawItemListsPage.deleteCustomRawList(rawItemListName);
			// Verify that custom list should be deleted and should not displayed in custom Raw Item list page
			result = result & (!customRawItemListsPage.verifyCustomListIsDisplayed(rawItemListName));
			if (result) {
				Reporter.reportPassResult(
						browser, "sprint15_US1205_TC2593",
						"Supervisor should be able to delete a custom list",
						"Pass");
			} else {
				Reporter.reportTestFailure(
						browser, "sprint15_US1205_TC2593","sprint15_US1205_TC2593",
						"Supervisor should be able to delete a custom list",
						"Fail");
				AbstractTest.takeSnapShot("sprint15_US1205_TC2593");
			}
		}
	
		//TC2594: To verify if the Supervisor is able to restore a custom list
		@Test()
		public void sprint15_US1205_TC2594() throws RowsExceededException,
				BiffException, WriteException, IOException, InterruptedException {
			/** Variable Section : **/
			String storeId = GlobalVariable.supervisorStoreId;
			String userId = GlobalVariable.supervisorUserId;
			String rawItemListName = "Test" + Base.generateNdigitRandomNumber(3);
			/***********************************/
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			// Go to Custom raw Item list page
			CustomRawItemListsPage customRawItemListsPage = homePage.selectUser(userId).selectLocation(storeId)
					.navigateToInventoryManagement().goToCustomRawItemListsPage();
			//Create a new list
			customRawItemListsPage.createACustomRawList(rawItemListName);
			//Delete the newly created list
			customRawItemListsPage.deleteCustomRawList(rawItemListName);
			//restore the deleted list
			customRawItemListsPage.restoreCustomList(rawItemListName);
			//Go to custom Raw Item list page
			homePage.Menu_DD_BT.click();
			homePage.goToCustomRawItemListsPage();
			// Verify that custom list is restored and displayed in  "Custom Raw Item List " page.
			boolean customListRestored = customRawItemListsPage.verifyCustomListIsDisplayed(rawItemListName);
			if (customListRestored) {
				Reporter.reportPassResult(
						browser, "sprint15_US1205_TC2594",
						"Supervisor should be able to restore a custom list",
						"Pass");
			} else {
				Reporter.reportTestFailure(
						browser, "sprint15_US1205_TC2594","sprint15_US1205_TC2594",
						"Supervisor should be able to restore a custom list",
						"Fail");
				AbstractTest.takeSnapShot("sprint15_US1205_TC2594");
			}
		}
		
		//TC2596: To verify if the Supervisor is able to add a manual vendor
		@Test()
		public void sprint15_US1205_TC2596() throws RowsExceededException,
				BiffException, WriteException, IOException, InterruptedException {
			/** Variable Section : **/
			ManualVendorsPage manualVendorsPage;
			String storeId = GlobalVariable.supervisorStoreId;
			String userId = GlobalVariable.supervisorUserId;
			String randomNum = String.valueOf(Base.generateNdigitRandomNumber(4));
			String newVendorName = "Testauto" + randomNum;
			/***********************************/
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			// Navigate to Manual Vendor page
			manualVendorsPage =  homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToManualVendorsPage();
			wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.AddVendor_BT));
			// Create a new vendor
			manualVendorsPage.createANewVendor(newVendorName, randomNum);
			//verify that operator is able to add new manual vendor
			if (Base.isElementDisplayed(manualVendorsPage.VendorName_Row(newVendorName))) {
				Reporter.reportPassResult(
						browser, "sprint15_US1205_TC2596",
						"Supervisor is able to add a manual vendor",
						"Pass");
			} else {
				Reporter.reportTestFailure(
						browser, "sprint15_US1205_TC2596","sprint15_US1205_TC2596",
						"Supervisor is able to add a manual vendor",
						"Fail");
				AbstractTest.takeSnapShot("sprint15_US1205_TC2596");
			}
		}
		
		//TC2597: To verify if the Supervisor is able to edit and delete a manual vendor
		@Test()
		public void sprint15_US1205_TC2597() throws RowsExceededException,
				BiffException, WriteException, IOException, InterruptedException {
			/** Variable Section : **/
			ManualVendorsPage manualVendorsPage;
			String storeId = GlobalVariable.supervisorStoreId;
			String userId = GlobalVariable.supervisorUserId;
			String randomNum = String.valueOf(Base.generateNdigitRandomNumber(4));
			String newVendorName = "Testauto" + randomNum;
			/***********************************/
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			// Navigate to Manual Vendor page
			manualVendorsPage =  homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToManualVendorsPage();
			wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.AddVendor_BT));
			// Create a new vendor
			manualVendorsPage.createANewVendor(newVendorName, randomNum);
			manualVendorsPage.editVendor_BT(newVendorName).click();
			wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.EditVendorDetails_Title));
			wait.until(ExpectedConditions.elementToBeClickable(manualVendorsPage.Delete_BT)).click();
			wait.until(ExpectedConditions.elementToBeClickable(manualVendorsPage.DeleteVendorConfirmationPopUp_Submit_BT)).click();
			//verify that operator is able to add new manual vendor
			if (manualVendorsPage.verifyVendorDeleted(newVendorName)) {
				Reporter.reportPassResult(
						browser, "sprint15_US1205_TC2597",
						"Supervisor is able to Edit and Delete a manual vendor",
						"Pass");
			} else {
				Reporter.reportTestFailure(
						browser, "sprint15_US1205_TC2597","sprint15_US1205_TC2597",
						"Supervisor is able to edit and delete a manual vendor",
						"Fail");
				AbstractTest.takeSnapShot("sprint15_US1205_TC2597");
			}
		}
		


}
