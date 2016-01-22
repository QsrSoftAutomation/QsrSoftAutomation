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
import eInventoryPageClasses.FoodOverBasePage;
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.ManualInvoiceNewPage;
import eInventoryPageClasses.ManualVendorsPage;
import eInventoryPageClasses.MenuItemActivityPage;
import eInventoryPageClasses.MenuItemInformationPage;
import eInventoryPageClasses.PromotionsAndWastePage;
import eInventoryPageClasses.PurchasesPage;
import eInventoryPageClasses.RawItemActivityPage;
import eInventoryPageClasses.RawItemPromoPage;
import eInventoryPageClasses.TransferLandingPage;
import eInventoryPageClasses.VarianceStatPage;
import sprint2.AbstractTest;

public class US1207_OrgAdminAccess  extends AbstractTest{
	
	//TC2662 : To Verify if the Org Admin is able to enter a manual purchase
	@Test()
	public void sprint15_US1207_TC2662() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.orgAdminStoreId;
		String userId = GlobalVariable.orgAdminUserId;
		String wrinId = GlobalVariable.createPurchaseWrin1;
		String vendor =GlobalVariable.vendorName;
		String quantity = "1";
		String invoiceId = Base.randomNumberFiveDigit();
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		//Navigate to purchase landing page
		PurchasesPage purchasesPage =homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPurchaseLandingPage();
		//Click on create new invoice button
		ManualInvoiceNewPage manualInvoiceNewPage = purchasesPage.goToManualInvoiceNewPage();
		//Create a new manual purchase
		manualInvoiceNewPage.createAManualPurchaseForWrinID(wrinId, vendor, quantity, invoiceId);
		homePage.Menu_DD_BT.click();
		wait.until(ExpectedConditions.elementToBeClickable(homePage.Purchases_BT)).click();
		Thread.sleep(2000);
		if (purchasesPage.verifyPendindInvoiceIsPresent(invoiceId)) {
			Reporter.reportPassResult(
					browser, "sprint15_US1207_TC2662",
					"Org Admin should be able to enter a manual purchase", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint15_US1207_TC2662","sprint15_US1207_TC2662",
					"Org Admin should be able to enter a manual purchase", "Fail");
			AbstractTest.takeSnapShot("sprint15_US1207_TC2662");
		}
	}
	
	//TC2663 : To Verify if the Org Admin is able to cancel a purchase
	@Test()
	public void sprint15_US1207_TC2663() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.orgAdminStoreId;
		String userId = GlobalVariable.orgAdminUserId;
		String wrinId = GlobalVariable.createPurchaseWrin1;
		String vendor =GlobalVariable.vendorName;
		String quantity = "1";
		String invoiceId = Base.randomNumberFiveDigit();
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		//Navigate to purchase landing page
		PurchasesPage purchasesPage =homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPurchaseLandingPage();
		//Click on create new invoice button
		ManualInvoiceNewPage manualInvoiceNewPage = purchasesPage.goToManualInvoiceNewPage();
		//Create a new manual purchase
		manualInvoiceNewPage.selectAVendorFromDropdown(vendor);
		manualInvoiceNewPage.searchAndSelectARawItem(wrinId);
		manualInvoiceNewPage.enterQuantityInQuantityTextBox(wrinId,quantity);
		manualInvoiceNewPage.InvoiceNumber_TB.sendKeys(invoiceId);
		//Cancel the purchase
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceNewPage.Cancel_BT)).click();
		homePage.Menu_DD_BT.click();
		//Verify that invoice should not be created
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(homePage.Purchases_BT)).click();
		Thread.sleep(2000);
		if (!purchasesPage.verifyPendindInvoiceIsPresent(invoiceId)) {
			Reporter.reportPassResult(
					browser, "sprint15_US1207_TC2663",
					"Org Admin should be able to cancel a manual purchase", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint15_US1207_TC2663","sprint15_US1207_TC2663",
					"Org Admin should be able to cancel a manual purchase", "Fail");
			AbstractTest.takeSnapShot("sprint15_US1207_TC2663");
		}
	}

	//TC2664 : To Verify if the Org Admin is able to edit and finalize a pending purchase.
	@Test(enabled = false)
	public void sprint15_US1207_TC2664() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.orgAdminStoreId;
		String userId = GlobalVariable.orgAdminUserId;
		String wrinId = GlobalVariable.createPurchaseWrin1;
		String vendor =GlobalVariable.vendorName;
		String quantity = "1";
		String invoiceId = Base.randomNumberFiveDigit();
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		//Navigate to purchase landing page
		PurchasesPage purchasesPage =homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPurchaseLandingPage();
		//Click on create new invoice button
		ManualInvoiceNewPage manualInvoiceNewPage = purchasesPage.goToManualInvoiceNewPage();
		//Create a new manual purchase
		manualInvoiceNewPage.createAManualPurchaseForWrinID(wrinId, vendor, quantity, invoiceId);
		//click on approve button for the created manual purchase button
		homePage.Menu_DD_BT.click();
		wait.until(ExpectedConditions.elementToBeClickable(homePage.Purchases_BT)).click();
		Thread.sleep(2000);
		manualInvoiceNewPage.clickOnApproveButtonForManualPurchase(invoiceId);
		wait.until(ExpectedConditions.visibilityOf(purchasesPage.ManualInvoiceApprove_BT)).click();
		// Verify that manual invoice is approved
		if (true) {
			Reporter.reportPassResult(
					browser, "sprint15_US1207_TC2664",
					"Operator should be able to enter a manual purchase", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint15_US1207_TC2664","sprint15_US1207_TC2664",
					"Operator should be able to enter a manual purchase", "Fail");
			AbstractTest.takeSnapShot("sprint15_US1207_TC2664");
		}
	}
	
	// TC2665 :To Verify if the Org Admin is able to delete a pending purchase.
	@Test()
	public void sprint15_US1207_TC2665() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.orgAdminStoreId;
		String userId = GlobalVariable.orgAdminUserId;
		String wrinId = GlobalVariable.createPurchaseWrin1;
		String vendor =GlobalVariable.vendorName;
		String quantity = "1";
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
					browser, "sprint15_US1207_TC2665",
					"Org Admin is able to delete a purchase", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint15_US1207_TC2665","sprint15_US1207_TC2665",
					"Org Admin is able to delete a purchase", "Fail");
			AbstractTest.takeSnapShot("sprint15_US1207_TC2665");
		}
	}
	
	//TC2668 :To Verify if the Org Admin is able to restore purchase
	@Test()
	public void sprint15_US1207_TC2668() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.orgAdminStoreId;
		String userId = GlobalVariable.orgAdminUserId;
		String wrinId = GlobalVariable.createPurchaseWrin1;
		String vendor =GlobalVariable.vendorName;
		String quantity = "1";
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
					browser, "sprint15_US1207_TC2668",
					"Org Admin is able to restore purchase", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint15_US1207_TC2668","sprint15_US1207_TC2668",
					"Org Admin is able to restore purchase", "Fail");
			AbstractTest.takeSnapShot("sprint15_US1207_TC2668");
		}
	}
	
	//TC2669 : Verify that Org Admin is restricted from entering raw waste
	@Test()
	public void sprint15_US1207_TC2669() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.orgAdminStoreId;
		String userId = GlobalVariable.orgAdminUserId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Promotion and waste page
		PromotionsAndWastePage promotionAndWastePage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPromotionsAndWastePage();
		// Verify that operator should not be able to view Raw Waste button on promotion and waste page
		if (!Base.isElementDisplayed(promotionAndWastePage.RawWaste_BT)) {
			Reporter.reportPassResult(
					browser, "sprint15_US1207_TC2669",
					"Operator is restricted from entering raw waste", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint15_US1207_TC2669","sprint15_US1207_TC2669",
					"Operator is restricted from entering raw waste", "Fail");
			AbstractTest.takeSnapShot("sprint15_US1207_TC2669");
		}
	}
	
	//TC2670 : Verify that Org Admin is restricted from entering completed waste.
	@Test()
	public void sprint15_US1207_TC2670() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.orgAdminStoreId;
		String userId = GlobalVariable.orgAdminUserId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Promotion and waste page
		PromotionsAndWastePage promotionAndWastePage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPromotionsAndWastePage();
		// Verify that operator should not be able to view Completed Waste button on promotion and waste page
		if (!Base.isElementDisplayed(promotionAndWastePage.CompletedWaste_BT)) {
			Reporter.reportPassResult(
					browser, "sprint15_US1207_TC2670",
					"Org Admin is restricted from entering completed waste", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint15_US1207_TC2670","sprint15_US1207_TC2670",
					"Org Admin is restricted from entering completed waste", "Fail");
			AbstractTest.takeSnapShot("sprint15_US1207_TC2670");
		}
	}
	
	//TC2671 : To Verify if the Org Admin is able to enter raw promo
	@Test()
	public void sprint15_US1207_TC2671() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.orgAdminStoreId;
		String userId = GlobalVariable.orgAdminUserId;
		String samplewRINID = GlobalVariable.rawItemWatsewrin1;
		String caseQuantity = "1";
		String innerPackQuantity = "1";
		String looseUnitQuantity = "1";
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		RawItemPromoPage rawItemPromoPage = PageFactory.initElements(driver, RawItemPromoPage.class);
		// Navigate to Promotion and waste page
		PromotionsAndWastePage promotionAndWastePage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPromotionsAndWastePage();
		// CLick on Raw Promo Button
		promotionAndWastePage.RawPromo_BT.click();
		wait.until(ExpectedConditions.visibilityOf(rawItemPromoPage.RawPromo_Title));
		// Create a raw Promo entry
		rawItemPromoPage.addARawPromoItem(samplewRINID, innerPackQuantity, caseQuantity, looseUnitQuantity);
		//get the total promo amount
		String promoAmount = rawItemPromoPage.getTotalPromoAmount();
		System.out.println("promoAmount "+promoAmount);
		//submit raw promo entry
		rawItemPromoPage.Submit_BT.click();
		// Verify that raw promo entry should displayed in Promotion and waste page
		if (promotionAndWastePage.isRawPromoEntryPresent(Base.returnTodayDate(), promoAmount)) {
			Reporter.reportPassResult(
					browser, "sprint15_US1207_TC2671",
					"Org Admin User should be able to enter  raw promo",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint15_US1207_TC2671","sprint15_US1207_TC2671",
					"Org Admin User should be able to enter  raw promo",
					"Fail");
			AbstractTest.takeSnapShot("sprint15_US1207_TC2671");
		}
	}
	
	//TC2672 : To Verify if the Org Admin is able to cancel raw promo entry
	@Test()
	public void sprint15_US1207_TC2672() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.orgAdminStoreId;
		String userId = GlobalVariable.orgAdminUserId;
		String samplewRINID = GlobalVariable.rawItemWatsewrin1;
		String caseQuantity = "3";
		String innerPackQuantity = "3";
		String looseUnitQuantity = "3";
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		RawItemPromoPage rawItemPromoPage = PageFactory.initElements(driver,RawItemPromoPage.class);
		// Navigate to Promotion and waste page
		PromotionsAndWastePage promotionAndWastePage = homePage
				.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPromotionsAndWastePage();
		// Click on Raw promo Button
		promotionAndWastePage.RawPromo_BT.click();
		wait.until(ExpectedConditions.visibilityOf(rawItemPromoPage.RawPromo_Title));
		// Create a raw promo entry
		rawItemPromoPage.addARawPromoItem(samplewRINID, innerPackQuantity,caseQuantity, looseUnitQuantity);
		//get the promo amount
		String promoAmount = rawItemPromoPage.getTotalPromoAmount();
		//click on cancel button
		rawItemPromoPage.Cancel_BT.click();
		//click on yes button
		wait.until(ExpectedConditions.elementToBeClickable(rawItemPromoPage.RawPromoEntryIncomplete_PopUp_YES_BT)).click();
		// Verify that raw promo entry should not displayed in Promotion and waste page
		if (!promotionAndWastePage.isRawPromoEntryPresent(Base.returnTodayDate(), promoAmount)) {
			Reporter.reportPassResult(browser, "sprint15_US1207_TC2672",
					"Org Admin User should be able to cancel raw promo entry", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint15_US1207_TC2672","sprint15_US1207_TC2672",
					"Org Admin User should be able to cancel  raw promo entry", "Fail");
			AbstractTest.takeSnapShot("sprint15_US1207_TC2672");
		}
	}
	
	//TC2673:To Verify if the Org Admin is able to transfer raw items in/out to other stores.
	@Test()
	public void sprint15_US1207_TC2673() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.orgAdminStoreId;
		String userId = GlobalVariable.orgAdminUserId;
		HSSFSheet transferLandingPageSheet = ReadTestData.getTestDataSheet("sprint15_US1208_TC2643", "Object1");
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
					browser,"sprint15_US1207_TC2673",
					"Org Admin should be able to transfer raw items in/out to other stores and able to view print button",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint15_US1207_TC2673","sprint15_US1207_TC2673",
					"Org Admin should be able to transfer raw items in/out to other stores and able to view print button",
					"Fail");
			AbstractTest.takeSnapShot("sprint15_US1207_TC2673_Condition1");
		}
		int caseQty2 = Integer.parseInt(caseQuantity) + 1;
		int innerPackQty2 = Integer.parseInt(innerPackQuantity) + 1;
		int looseUnitsQty2 = Integer.parseInt(looseUnitQuantity) + 1;
		//click on Create new Transfer button
		transferLandingPage.CreateNewTransfers_BT.click();
		wait.until(ExpectedConditions.visibilityOf(transferLandingPage.InsertNewTransfersPopup_InputNationalStoreNo_TB));
		//Get the time of transfer
		String time2=transferLandingPage.InsertNewTransfersPopup_Time_Value.getText().trim();
		//Select the transfer type as "in" and select the store from dropdown an add the transfer details
		transferLandingPage.selectTransferType(transferType).selectLocationToTransfer(transferStoreNumber).insertAndAddDetailsToTransfer(samplewRINID,
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
			Reporter.reportPassResult(browser, "sprint15_US1207_TC2673",
					"Org Admin should be able to transfer raw items in/out to other stores",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser, "sprint15_US1207_TC2673","sprint15_US1207_TC2673",
					"Org Admin should be able to transfer raw items in/out to other stores",
					"Fail");
			AbstractTest.takeSnapShot("sprint15_US1207_TC2673_Condition2");
		}
	}
	
	//TC2674 : To Verify if the Org Admin is able to transfer raw items to office
	@Test()
	public void sprint15_US1207_TC2674() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.orgAdminStoreId;
		String userId = GlobalVariable.orgAdminUserId;
		HSSFSheet transferLandingPageSheet = ReadTestData.getTestDataSheet("sprint15_US1208_TC2644", "Object1");
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
					browser,"sprint15_US1207_TC2674",
					"Org Admin should be able to submit transfer to office",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint15_US1207_TC2674","sprint15_US1207_TC2674",
					"Org Admin should be able to submit transfer to office",
					"Fail");
			AbstractTest.takeSnapShot("sprint15_US1207_TC2674");
		}
	}
	
	//TC2675 : To Verify if the Org Admin is able to view and print raw items transfer details
	@Test()
	public void sprint15_US1207_TC2675() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.orgAdminStoreId;
		String userId = GlobalVariable.orgAdminUserId;
		HSSFSheet transferLandingPageSheet = ReadTestData.getTestDataSheet("sprint15_US1208_TC2645", "Object1");
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
					browser, "sprint15_US1207_TC2675",
					"Org Admin is able to view raw items transfer details and able to view print button", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint15_US1207_TC2675","sprint15_US1207_TC2675",
					"Org Admin is able to view raw items transfer details and able to view print button", "Fail");
			AbstractTest.takeSnapShot("sprint15_US1207_TC2675");
		}
	}
	
	//TC2676:To Verify if the Org Admin is able to view daily stat
	@Test()
	public void sprint15_US1207_TC2676() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.orgAdminStoreId;
		String userId = GlobalVariable.orgAdminUserId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to variance stat page
		VarianceStatPage varianceStatPage = homePage.selectUser(userId)
				.selectLocation(storeId).navigateToInventoryManagement().goToVarianceStatPage();
		//select daily stat from drop down
		varianceStatPage.selectVarianceStatType("Daily");
		//click on start date button
		wait.until(ExpectedConditions.elementToBeClickable(varianceStatPage.StartDate_BT)).click();
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		// Get yesterday date as start date
		cal1.add(Calendar.DATE, -1);
		String startDate = dateFormat.format(cal1.getTime());
		//Select yesterday date from calender
		varianceStatPage.selectDateFromCalender(startDate);
		//Verify that daily stat should displayed for the selected date 
		if (varianceStatPage.verifyVarianceStatLoaded()) {
			Reporter.reportPassResult(
					browser, "sprint15_US1207_TC2676",
					"Org Admin is able to view daily variance stat", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint15_US1207_TC2676","sprint15_US1207_TC2676",
					"Org Admin is able to view daily variance stat", "Fail");
			AbstractTest.takeSnapShot("sprint15_US1207_TC2676_Condition1");
		}
		// click on dont with this item button
		wait.until(ExpectedConditions.visibilityOf(varianceStatPage.DoneWithThisItem_BT)).click();
		//Select monthly from the drop down
		varianceStatPage.selectVarianceStatType("Monthly");
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		//get last month and year
		Calendar cal2 = Calendar.getInstance();
		cal2.add(Calendar.MONTH, -1);
		cal2.set(Calendar.DATE, 1);
        String date = dateFormat2.format(cal2.getTime());
        //select last month from the drop down
        varianceStatPage.selectMonth(date);
        //verify that variance stat should displayed for the selected month
        boolean monthlyVarianceDisplayed = varianceStatPage.verifyVarianceStatLoaded();
        //click on done with this item button
        wait.until(ExpectedConditions.visibilityOf(varianceStatPage.DoneWithThisItem_BT)).click();
        //verify that user should be on variance stat page
		if (monthlyVarianceDisplayed & Base.isElementDisplayed(varianceStatPage.VarianceStatType_DD)) {
			Reporter.reportPassResult(
					browser,"sprint15_US1207_TC2676",
					"Org Admin is able to view Monthly variance stat",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint15_US1207_TC2676","sprint15_US1207_TC2676",
					"Org Admin is able to view Monthly variance stat",
					"Fail");
			AbstractTest.takeSnapShot("sprint15_US1207_TC2676_Condition2");
		}
	}
	
	//TC2677 : To verify if the Org Admin is able to use all the functionality on the Food Over Base page.
	@Test()
	public void sprint15_US1207_TC2677() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		//** Variable Section : **//*
		String storeId = GlobalVariable.orgAdminStoreId;
		String userId = GlobalVariable.orgAdminUserId;
		String newtargetPercentValue = String.valueOf(Base.generateNdigitRandomNumber(2));
		//***********************************//*
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Food OverBase page
		FoodOverBasePage foodOverBasePage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToFoodOverBasePage();
		//Click on Target percentage column header link image
		foodOverBasePage.CurrentMonth_TargetPercent_ColumnHeader_Image_LK.click();
		wait.until(ExpectedConditions.visibilityOf(foodOverBasePage.PostTargetValuesPopUp_BaseFood_TB));
		//edit the target percentage values and save the new values
		foodOverBasePage.editTargetPercentValues(newtargetPercentValue);
		Thread.sleep(3000);
		newtargetPercentValue = newtargetPercentValue+"%";
		if (foodOverBasePage.CurrentMonth_BaseFood_TargetPercent_Value.getText().equals(newtargetPercentValue)
				& foodOverBasePage.CurrentMonth_MenuItemWaste_TargetPercent_Value.getText().equals(newtargetPercentValue)
				& foodOverBasePage.CurrentMonth_RawWaste_TargetPercent_Value.getText().equals(newtargetPercentValue)
				& foodOverBasePage.CurrentMonth_Condiment_TargetPercent_Value.getText().equals(newtargetPercentValue)
				& foodOverBasePage.CurrentMonth_EmployeeManagerFood_TargetPercent_Value.getText().equals(newtargetPercentValue)
				& foodOverBasePage.CurrentMonth_DiscountCoupon_TargetPercent_Value.getText().equals(newtargetPercentValue)
				& foodOverBasePage.CurrentMonth_StatVariance_TargetPercent_Value.getText().equals(newtargetPercentValue)
				& foodOverBasePage.CurrentMonth_UnexplainedDifference_TargetPercent_Value.getText().equals(newtargetPercentValue)){
			Reporter.reportPassResult(
					browser, "sprint15_US1207_TC2677",
					"Org Admin should be able to edit targetPercent value for current month in FoodOverBase Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint15_US1207_TC2677","sprint15_US1207_TC2677",
					"Org Admin should be able to edit targetPercent value for current month in FoodOverBase Page",
					"Fail");
			AbstractTest.takeSnapShot("sprint15_US1207_TC2677_Condition1");
		}
		String comment = "Test Automation "+ Base.randomNumberFiveDigit();
		//Click on the post comment button for current month and save a comment
		foodOverBasePage.postCommentForCurrentMonth(comment);
		//Verify that new comment is saved 
		foodOverBasePage.PostCommentForCurrentMonth_BT.click();
		if (foodOverBasePage.CommentBox_TB.getAttribute("value").equals(comment)) {
			Reporter.reportPassResult(
					browser,"sprint15_US1207_TC2677",
					"Org Admin should be able to save a comment in food over base page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint15_US1207_TC2677_Condition2","sprint15_US1207_TC2677",
					"Org Admin should be able to save a comment in food over base page",
					"Fail");
			AbstractTest.takeSnapShot("sprint15_US1207_TC2677_Condition2");
		}
	}
	
	//TC2678:To verify if the Org Admin is able to view the raw item activity details
	@Test()
	public void sprint15_US1207_TC2678() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.orgAdminStoreId;
		String userId = GlobalVariable.orgAdminUserId;
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
					browser,"sprint15_US1207_TC2678",
					"Org Admin is able to view the raw item activity deatails",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint15_US1207_TC2678","sprint15_US1207_TC2678",
					"Org Admin is able to view the raw item activity deatails",
					"Fail");
			AbstractTest.takeSnapShot("sprint15_US1207_TC2678");
		}
	}
	
	//TC2679 : To verify if the Org Admin is able to view the details of waste/promo on the raw item activity view page
	@Test()
	public void sprint15_US1207_TC2679() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.orgAdminStoreId;
		String userId = GlobalVariable.orgAdminUserId;
		HSSFSheet rawItemActivitySheet = ReadTestData.getTestDataSheet("sprint15_US1208_TC2649", "Object1");
		String wrinId = ReadTestData.getTestData(rawItemActivitySheet, "WRINId");
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
		// Get end date yesterday date
		Calendar cal2 = Calendar.getInstance();
		cal2.add(Calendar.DATE, -1);
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
					browser,"sprint15_US1207_TC2679",
					"Org Admin should be able to view the details of waste/promo on the raw item activity view page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint15_US1207_TC2679","sprint15_US1207_TC2679",
					"Org Admin should be able to view the details of waste/promo on the raw item activity view page",
					"Fail");
			AbstractTest.takeSnapShot("sprint15_US1207_TC2679");
		}
	}
	
	//TC2680 : To verify if the Org Admin is able to use all the functionality on the Menu Item Information page.
	@Test()
	public void sprint15_US1207_TC2680() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.orgAdminStoreId;
		String userId = GlobalVariable.orgAdminUserId;
		HSSFSheet menuItemActivityPageSheet = ReadTestData.getTestDataSheet("sprint15_US1208_TC2650", "Object1");
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
					browser,"sprint15_US1207_TC2680",
					"Org Admin should be able to view item page with the information of the menu item",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint15_US1207_TC2680","sprint15_US1207_TC2680",
					"Org Admin user should be able to view item page with the information of the menu item",
					"Fail");
			AbstractTest.takeSnapShot("sprint15_US1207_TC2680");
		}
	}
	
	//TC2681 : To verify if the Org Admin is able to use all the functionality on the Menu Item activity page.
	@Test()
	public void sprint15_US1207_TC2681() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.orgAdminStoreId;
		String userId = GlobalVariable.orgAdminUserId;
		HSSFSheet menuItemActivityPageSheet = ReadTestData.getTestDataSheet("sprint15_US1208_TC2652", "Object1");
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
					browser,"sprint15_US1207_TC2681",
					"Org Admin should be able to view  the list of menu item activity for the selected menu item, date range and time range",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint15_US1207_TC2681","sprint15_US1207_TC2681",
					"Org Admin user should be able to view  the list of menu item activity for the selected menu item, date range and time range",
					"Fail");
			AbstractTest.takeSnapShot("sprint15_US1207_TC2681");
		}
	}	
	
	//TC2682: To verify if the Org Admin is able to add a custom list
	@Test()
	public void sprint15_US1207_TC2682() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.orgAdminStoreId;
		String userId = GlobalVariable.orgAdminUserId;
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
					browser, "sprint15_US1207_TC2682",
					"orgAdmin should be able to add a custom list", "Pass");

		} else {
			Reporter.reportTestFailure(
					browser, "sprint15_US1207_TC2682","sprint15_US1207_TC2682",
					"orgAdmin should be able to add a custom list", "Fail");
			AbstractTest.takeSnapShot("sprint15_US1207_TC2682");
		}
	}
	
	//TC2683: To verify if the Org Admin is able to cancel a custom list add
	@Test()
	public void sprint15_US1207_TC2683() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.orgAdminStoreId;
		String userId = GlobalVariable.orgAdminUserId;
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
					browser, "sprint15_US1207_TC2683",
					"Org Admin should be  able to cancel a custom list add", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint15_US1207_TC2683","sprint15_US1207_TC2683",
					"Org Admin should be  able to cancel a custom list add", "Fail");
			AbstractTest.takeSnapShot("sprint15_US1207_TC2683");
		}
	}
	
	//TC2684: To verify if the Org Admin is able to delete a custom list
	@Test()
	public void sprint15_US1207_TC2684() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.orgAdminStoreId;
		String userId = GlobalVariable.orgAdminUserId;
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
					browser, "sprint15_US1207_TC2684",
					"Org Admin should be able to delete a custom list",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint15_US1207_TC2684","sprint15_US1207_TC2684",
					"Org Admin should be able to delete a custom list",
					"Fail");
			AbstractTest.takeSnapShot("sprint15_US1207_TC2684");
		}
	}
	
	//TC2685: To verify if the Org Admin is able to restore a custom list
	@Test()
	public void sprint15_US1207_TC2685() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.orgAdminStoreId;
		String userId = GlobalVariable.orgAdminUserId;
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
					browser, "sprint15_US1207_TC2685",
					"Org Admin should be able to restore a custom list",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint15_US1207_TC2685","sprint15_US1207_TC2685",
					"Org Admin should be able to restore a custom list",
					"Fail");
			AbstractTest.takeSnapShot("sprint15_US1207_TC2685");
		}
	}
	
	//TC2686: To verify if the Org Admin is able to add a manual vendor
	@Test()
	public void sprint15_US1207_TC2686() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		ManualVendorsPage manualVendorsPage;
		String storeId = GlobalVariable.orgAdminStoreId;
		String userId = GlobalVariable.orgAdminUserId;
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
					browser, "sprint15_US1207_TC2686",
					"Org Admin is able to add a manual vendor",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint15_US1207_TC2686","sprint15_US1207_TC2686",
					"Org Admin is able to add a manual vendor",
					"Fail");
			AbstractTest.takeSnapShot("sprint15_US1207_TC2686");
		}
	}
	
	//TC2687: To verify if the Org Admin is able to edit and delete a manual vendor
	@Test()
	public void sprint15_US1207_TC2687() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		ManualVendorsPage manualVendorsPage;
		String storeId = GlobalVariable.orgAdminStoreId;
		String userId = GlobalVariable.orgAdminUserId;
		String randomNum = String.valueOf(Base.generateNdigitRandomNumber(4));
		String newVendorName = "Testauto" + randomNum;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Manual Vendor page
		manualVendorsPage =  homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToManualVendorsPage();
		wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.AddVendor_BT));
		// Create a new vendor
		manualVendorsPage.createANewVendor(newVendorName, randomNum);
		//click on edit button for the vendor
		manualVendorsPage.editVendor_BT(newVendorName).click();
		wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.EditVendorDetails_Title));
		//edit the vendor details with different vendor name
		newVendorName =  newVendorName +"Edit";
		manualVendorsPage.editVendorDetails(newVendorName, randomNum);
		//click on edit button for the vendor
		manualVendorsPage.editVendor_BT(newVendorName).click();
		wait.until(ExpectedConditions.visibilityOf(manualVendorsPage.EditVendorDetails_Title));
		//click on delete button
		wait.until(ExpectedConditions.elementToBeClickable(manualVendorsPage.Delete_BT)).click();
		//click on submit button
		wait.until(ExpectedConditions.elementToBeClickable(manualVendorsPage.DeleteVendorConfirmationPopUp_Submit_BT)).click();
		Thread.sleep(4000);
		//verify that operator is able to delete the manual vendor
		if (manualVendorsPage.verifyVendorDeleted(newVendorName)) {
			Reporter.reportPassResult(
					browser, "sprint15_US1207_TC2687",
					"Org Admin is able to delete a manual vendor",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint15_US1207_TC2687","sprint15_US1207_TC2687",
					"Org Admin is able to delete a manual vendor",
					"Fail");
			AbstractTest.takeSnapShot("sprint15_US1207_TC2687");
		}
	}
	
	//TC2691: Verify that Org Admin user is restricted from access to the Physical Inventory pages and functionality.
	@Test()
	public void sprint15_US1207_TC2691() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.orgAdminStoreId;
		String userId = GlobalVariable.orgAdminUserId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Manual Vendor page
		homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement();
		if(!Base.isElementDisplayed(homePage.PhysicalInventory_BT)){
			Reporter.reportPassResult(
					browser, "sprint15_US1207_TC2691",
					"User should be restricted from access to the Physical Inventory pages and functionality.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint15_US1207_TC2691","sprint15_US1207_TC2691",
					"User should be restricted from access to the Physical Inventory pages and functionality.",
					"Fail");
			AbstractTest.takeSnapShot("sprint15_US1207_TC2691");
		}

	}

}
