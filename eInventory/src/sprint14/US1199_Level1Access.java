package sprint14;

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

import sprint2.AbstractTest;
import common.Base;
import common.GlobalVariable;
import common.ReadTestData;
import common.Reporter;
import eInventoryPageClasses.CompletedWastePage;
import eInventoryPageClasses.CustomRawItemListsPage;
import eInventoryPageClasses.FoodOverBasePage;
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.ManualVendorsPage;
import eInventoryPageClasses.PromotionsAndWastePage;
import eInventoryPageClasses.RawItemActivityPage;
import eInventoryPageClasses.RawItemInformationPage;
import eInventoryPageClasses.RawItemPromoPage;
import eInventoryPageClasses.RawItemWastePage;
import eInventoryPageClasses.TransferLandingPage;

public class US1199_Level1Access extends AbstractTest{
	
	// TC2445 : To Verify if the level 1 user is able to enter raw waste
	@Test()
	public void sprint14_US1199_TC2445() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.level1StoreId;
		String userId = GlobalVariable.level1UserId;
		String samplewRINID = GlobalVariable.rawItemWatsewrin1;
		String caseQuantity = "1";
		String innerPackQuantity = "1";
		String looseUnitQuantity = "1";
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		RawItemWastePage rawItemWastePage = PageFactory.initElements(driver, RawItemWastePage.class);
		//Navigate to Promotion and waste page
		PromotionsAndWastePage promotionAndWastePage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPromotionsAndWastePage();
		//CLick on Raw Waste Button
		promotionAndWastePage.RawWaste_BT.click();
		wait.until(ExpectedConditions.visibilityOf(rawItemWastePage.RawWaste_Title));
		//Create a raw waste entry
		rawItemWastePage.addARawItem(samplewRINID, caseQuantity, innerPackQuantity, looseUnitQuantity);
		//Get the total waste amount
		String wasteAmount = rawItemWastePage.getTotalWasteAmunt();
		System.out.println("wasteAmount "+wasteAmount);
		//Submit the raw waste entry
		rawItemWastePage.Submit_BT.click();
		//Verify that raw waste entry should displayed in Promotion and waste page
		if (promotionAndWastePage.isRawWasteEntryPresent(Base.returnTodayDate(), wasteAmount)) {
			Reporter.reportPassResult(
					browser, "sprint14_US1199_TC2445",
					"Level 1 User should be able to enter raw waste", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint14_US1199_TC2445","sprint14_US1199_TC2445",
					"Level 1 User should be able to enter raw waste", "Fail");
			AbstractTest.takeSnapShot("sprint14_US1199_TC2445");
		}
	}

	// TC2446 : To Verify if the level 1 user is able to enter completed waste
	@Test()
	public void sprint14_US1199_TC2446() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.level1StoreId;
		String userId = GlobalVariable.level1UserId;
		String samplewRINID = GlobalVariable.completedWasteWrin1;
		String quantity = "4";
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		CompletedWastePage completedWastePage = PageFactory.initElements(driver,CompletedWastePage.class);
		// Navigate to Promotion and waste page
		PromotionsAndWastePage promotionAndWastePage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPromotionsAndWastePage();
		// CLick on Completed Waste Button
		promotionAndWastePage.CompletedWaste_BT.click();
		wait.until(ExpectedConditions.visibilityOf(completedWastePage.CompletedWaste_Title));
		// Create a Completed waste entry
		completedWastePage.addAMenuItemOnCompletedWastePage(samplewRINID,quantity);
		//Get total waste amount
		String wasteAmount = completedWastePage.getTotalWasteAmunt();
		//Submit the completed waste entry
		completedWastePage.Submit_BT.click();
		Thread.sleep(3000);
		// Verify that Completed waste entry should displayed in Promotion and waste page
		if (promotionAndWastePage.isCompletedWasteEntryPresent(Base.returnTodayDate(), wasteAmount)) {
			Reporter.reportPassResult(
					browser, "sprint14_US1199_TC2446",
					"Level 1 User should be able to enter completed waste", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint14_US1199_TC2446","sprint14_US1199_TC2446",
					"Level 1 User should be able to enter completed waste", "Fail");
			AbstractTest.takeSnapShot("sprint14_US1199_TC2446");
		}
	}
	
	//TC2447 : To Verify if the level 1 user is able to enter raw promo
	@Test()
	public void sprint14_US1199_TC2447() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.level1StoreId;
		String userId = GlobalVariable.level1UserId;
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
					browser, "sprint14_US1199_TC2447",
					"Level 1 User should be able to enter  raw promo",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint14_US1199_TC2447","sprint14_US1199_TC2447",
					"Level 1 User should be able to enter  raw promo",
					"Fail");
			AbstractTest.takeSnapShot("sprint14_US1199_TC2447");
		}
	}
	
	// TC2448 : To Verify if the level 1 user is able to cancel raw waste entry
	@Test()
	public void sprint14_US1199_TC2448() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.level1StoreId;
		String userId = GlobalVariable.level1UserId;
		String samplewRINID = GlobalVariable.rawItemWatsewrin1;
		String caseQuantity = "2";
		String innerPackQuantity = "2";
		String looseUnitQuantity = "2";
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		RawItemWastePage rawItemWastePage = PageFactory.initElements(driver, RawItemWastePage.class);
		//Navigate to Promotion and waste page
		PromotionsAndWastePage promotionAndWastePage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPromotionsAndWastePage();
		//CLick on Raw Waste Button
		promotionAndWastePage.RawWaste_BT.click();
		wait.until(ExpectedConditions.visibilityOf(rawItemWastePage.RawWaste_Title));
		//Create a raw waste entry
		rawItemWastePage.addARawItem(samplewRINID, caseQuantity, innerPackQuantity, looseUnitQuantity);
		//Get the total waste amount
		String wasteAmount = rawItemWastePage.getTotalWasteAmunt();
		//cancel the raw waste entry
		rawItemWastePage.Cancel_BT.click();
		//Click on Yes button
		wait.until(ExpectedConditions.visibilityOf(rawItemWastePage.RawWasteEntryIncomplete_PopUp_YES_BT)).click();
		Thread.sleep(2000);
		//Verify that raw waste entry should not displayed in Promotion and waste page
		if (!promotionAndWastePage.isRawWasteEntryPresent(Base.returnTodayDate(), wasteAmount)) {
			Reporter.reportPassResult(
					browser, "sprint14_US1199_TC2448",
					"Level 1 User should be able to cancel raw waste entry", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint14_US1199_TC2448","sprint14_US1199_TC2448",
					"Level 1 User should be able to cancel raw waste entry", "Fail");
			AbstractTest.takeSnapShot("sprint14_US1199_TC2448");
		}
	}
	
	// TC2449 : To Verify if the level 1 user is able to cancel completed waste entry
	@Test()
	public void sprint14_US1199_TC2449() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.level1StoreId;
		String userId = GlobalVariable.level1UserId;
		String samplewRINID = GlobalVariable.completedWasteWrin1;
		String quantity = "3";
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		CompletedWastePage completedWastePage = PageFactory.initElements(driver,CompletedWastePage.class);
		// Navigate to Promotion and waste page
		PromotionsAndWastePage promotionAndWastePage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPromotionsAndWastePage();
		// CLick on Completed waste Button
		promotionAndWastePage.CompletedWaste_BT.click();
		wait.until(ExpectedConditions.visibilityOf(completedWastePage.CompletedWaste_Title));
		// Create a Completed waste entry
		completedWastePage.addAMenuItemOnCompletedWastePage(samplewRINID,quantity);
		//Get the total Completed waste amount
		String wasteAmount = completedWastePage.getTotalWasteAmunt();
		//Cancel the Completed waste entry
		completedWastePage.Cancel_BT.click();
		//click on yes button
		wait.until(ExpectedConditions.visibilityOf(completedWastePage.CompletedWasteEntryIncomplete_PopUp_YES_BT)).click();
		// Verify that Completed waste entry should not displayed in Promotion and waste page
		if (!promotionAndWastePage.isCompletedWasteEntryPresent(Base.returnTodayDate(), wasteAmount)) {
			Reporter.reportPassResult(
					browser, "sprint14_US1199_TC2449",
					"Level 1 User should be able to cancel completed waste entry", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint14_US1199_TC2449","sprint14_US1199_TC2449",
					"Level 1 User should be able to cancel completed waste entry", "Fail");
			AbstractTest.takeSnapShot("sprint14_US1199_TC2449");
		}
	}
	
	//TC2450 : To Verify if the level 1 user is able to cancel raw promo entry
	@Test()
	public void sprint14_US1199_TC2450() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.level1StoreId;
		String userId = GlobalVariable.level1UserId;
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
			Reporter.reportPassResult(browser, "sprint14_US1199_TC2450",
					"Level 1 User should be able to cancel raw promo entry", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint14_US1199_TC2450","sprint14_US1199_TC2450",
					"Level 1 User should be able to cancel  raw promo entry", "Fail");
			AbstractTest.takeSnapShot("sprint14_US1199_TC2450");
		}
	}
	
	//TC2452 : To verify that the level 1 users are able to use all of the functionality on the Transfers page.
	@Test()
	public void sprint14_US1199_TC2452() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.level1StoreId;
		String userId = GlobalVariable.level1UserId;
		HSSFSheet transferLandingPageSheet = ReadTestData.getTestDataSheet("sprint14_US1199_TC2452", "Object1");
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
					browser,"sprint14_US1199_TC2452",
					"User should be able to submit in/out transfer",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint14_US1199_TC2452","sprint14_US1199_TC2452",
					"User should be able to submit in/out transfer",
					"Fail");
			AbstractTest.takeSnapShot("sprint14_US1199_TC2452_Condition1");
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
		transferLandingPage.selectTransferType(transferType).insertAndAddDetailsToTransfer(samplewRINID,
				String.valueOf(caseQty2), String.valueOf(innerPackQty2),String.valueOf(looseUnitsQty2));
		//Get the amount
		String amount2 = transferLandingPage.AddTransferPopup_TotalAmount_Value.getText().split("\\$")[1];
		System.out.println("amount "+amount2);
		//click on cancel button
		transferLandingPage.AddTransferItemsPopup_Cancel_BT.click();
		wait.until(ExpectedConditions.visibilityOf(transferLandingPage.AddTransferItemsPopup_Warning_Message_Yes_BT)).click();
		Thread.sleep(2000);
		// Verify that user should be able to cancel transfer and tranfer entries should not displayed in transfer page 
		if (!transferLandingPage.verifyTransferPlaced(Base.returnTodayDate(),time2, amount2)) {
			Reporter.reportPassResult(browser, "sprint14_US1199_TC2452",
					"Level 1 User should be able to cancel in/out transfer",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser, "sprint14_US1199_TC2452","sprint14_US1199_TC2452",
					"Level 1 User should be able to cancel in/out transfer",
					"Fail");
			AbstractTest.takeSnapShot("sprint14_US1199_TC2452_Condition2");
		}
	}
	
	//TC2456 : To Verify if the level 1 user is able to transfer raw items to office.
	@Test()
	public void sprint14_US1199_TC2456() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.level1StoreId;
		String userId = GlobalVariable.level1UserId;
		HSSFSheet transferLandingPageSheet = ReadTestData.getTestDataSheet("sprint14_US1199_TC2456", "Object1");
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
					browser,"sprint14_US1199_TC2456",
					"User should be able to submit transfer to office",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint14_US1199_TC2456","sprint14_US1199_TC2456",
					"User should be able to submit transfer to office",
					"Fail");
			AbstractTest.takeSnapShot("sprint14_US1199_TC2456");
		}
	}
	
	//TC2457 : To Verify if the level 1 user is able to view and print raw items transfer details
	@Test()
	public void sprint14_US1199_TC2457() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.level1StoreId;
		String userId = GlobalVariable.level1UserId;
		HSSFSheet transferLandingPageSheet = ReadTestData.getTestDataSheet("sprint14_US1199_TC2457", "Object1");
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
		if (transferLandingPage.ViewTransferItemsPopup_GrandTotal_Value.getText().equals(amount)) {
			Reporter.reportPassResult(
					browser, "sprint14_US1199_TC2457",
					"level 1 user is able to view raw items transfer details", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint14_US1199_TC2457","sprint14_US1199_TC2457",
					"level 1 user is able to view raw items transfer details", "Fail");
			AbstractTest.takeSnapShot("sprint14_US1199_TC2457");
		}
	}
	
	//TC2459 : To verify that the level 1 users are able to use all of the functionality on the Food over base page.
	@Test()
	public void sprint14_US1199_TC2459() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.level1StoreId;
		String userId = GlobalVariable.level1UserId;
		String newtargetPercentValue = String.valueOf(Base.generateNdigitRandomNumber(2));
		/***********************************/
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
		//Verify that Values are saved
		if (foodOverBasePage.CurrentMonth_BaseFood_TargetPercent_Value.getText().equals(newtargetPercentValue)
				& foodOverBasePage.CurrentMonth_MenuItemWaste_TargetPercent_Value.getText().equals(newtargetPercentValue)
				& foodOverBasePage.CurrentMonth_RawWaste_TargetPercent_Value.getText().equals(newtargetPercentValue)
				& foodOverBasePage.CurrentMonth_Condiment_TargetPercent_Value.getText().equals(newtargetPercentValue)
				& foodOverBasePage.CurrentMonth_EmployeeManagerFood_TargetPercent_Value.getText().equals(newtargetPercentValue)
				& foodOverBasePage.CurrentMonth_DiscountCoupon_TargetPercent_Value.getText().equals(newtargetPercentValue)
				& foodOverBasePage.CurrentMonth_StatVariance_TargetPercent_Value.getText().equals(newtargetPercentValue)
				& foodOverBasePage.CurrentMonth_UnexplainedDifference_TargetPercent_Value.getText().equals(newtargetPercentValue)){
			Reporter.reportPassResult(
					browser, "sprint14_US1199_TC2459",
					"Level 1 user should be able to edit targetPercent value for current month in FoodOverBase Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint14_US1199_TC2459","sprint14_US1199_TC2459",
					"Level 1 user should be able to edit targetPercent value for current month in FoodOverBase Page",
					"Fail");
			AbstractTest.takeSnapShot("sprint14_US1199_TC2459_Condition1");
		}
		String comment = "Test Automation "+ Base.randomNumberFiveDigit();
		//Click on the post comment button for current month and save a comment
		foodOverBasePage.postCommentForCurrentMonth(comment);
		//Verify that new comment is saved 
		foodOverBasePage.PostCommentForCurrentMonth_BT.click();
		if (foodOverBasePage.CommentBox_TB.getAttribute("value").equals(comment)) {
			Reporter.reportPassResult(
					browser,"sprint14_US1199_TC2459",
					"Level 1 user should be able to save a comment in food over base page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint14_US1199_TC2459_Condition2","sprint14_US1199_TC2459",
					"Level 1 user should be able to save a comment in food over base page",
					"Fail");
			AbstractTest.takeSnapShot("sprint14_US1199_TC2459_Condition2");
		}
	}
	
	//TC2460:To verify that the level 1 users are able to use all of the functionality on the Raw Item Activity page.
	@Test()
	public void sprint14_US1199_TC2460() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.level1StoreId;
		String userId = GlobalVariable.level1UserId;
		String wrinId =GlobalVariable.rawItemActivityWrin;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Raw Item Activity Page
		RawItemActivityPage rawItemActivityPage = homePage.selectUser(userId)
				.selectLocation(storeId).navigateToInventoryManagement().goToRawItemActivityPage().searchAndSelectWRINID(wrinId);
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		// Get start date as 2 days earlier date from today date
		cal1.add(Calendar.DATE, -2);
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
					browser,"sprint14_US1199_TC2460",
					"Level 1 user should be able to  view the raw item activity deatails",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint14_US1199_TC2460","sprint14_US1199_TC2460",
					"Level 1 user should be able to  view the raw item activity deatails",
					"Fail");
			AbstractTest.takeSnapShot("sprint14_US1199_TC2460");
		}
	}
	
	//TC2461: verify if the level 1 user is able to view the details of waste/promo on the raw item activity view page
	@Test()
	public void sprint14_US1199_TC2461() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.level1StoreId;
		String userId = GlobalVariable.level1UserId;
		HSSFSheet rawItemActivitySheet = ReadTestData.getTestDataSheet("sprint14_US1199_TC2461", "Object1");
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
					browser,"sprint14_US1199_TC2460",
					"Level 1 user should be able to view the details of waste/promo on the raw item activity view page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint14_US1199_TC2460","sprint14_US1199_TC2460",
					"Level 1 user should be able to view the details of waste/promo on the raw item activity view page",
					"Fail");
			AbstractTest.takeSnapShot("sprint14_US1199_TC2460");
		}
	}
	
	//TC2462: verify if the level 1 user is not able to modify any raw item information
	@Test()
	public void sprint14_US1199_TC2462() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		RawItemInformationPage rawItemInformationPage; 
		String storeId = GlobalVariable.level1StoreId;
		String userId = GlobalVariable.level1UserId;
		String wrinId01=GlobalVariable.rawItemWatsewrin1;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		//Navigate to raw item info page
		rawItemInformationPage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToRawItemInformationPage();
		//Search for the WRIN ID
		rawItemInformationPage.searchAndSelectWRINID(wrinId01);
		wait.until(ExpectedConditions.visibilityOf(rawItemInformationPage.RawItemInfo_Title_Label));
		//Verify that Manual Purchase check box and ListType drop down is disabled for Level 6 user
		if(rawItemInformationPage.ManualPurchase_CB.getAttribute("disabled").equals("true")
				& rawItemInformationPage.ListType_DD.getAttribute("disabled").equals("true")){
			Reporter.reportPassResult(
					browser,"sprint14_US1199_TC2462",
					"Level 1 user can VIEW-ONLY the Raw Item Information page.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint14_US1199_TC2462","sprint14_US1199_TC2462",
					"Level 1 user can VIEW-ONLY the Raw Item Information page.",
					"Fail");
			AbstractTest.takeSnapShot("sprint14_US1199_TC2462");
		}
	}
	
	//TC2463: To verify if the level 1 user is able to add a custom list
	@Test()
	public void sprint14_US1199_TC2463() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.level1StoreId;
		String userId = GlobalVariable.level1UserId;
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
					browser, "sprint14_US1199_TC2463",
					"User should be able to add a custom list", "Pass");

		} else {
			Reporter.reportTestFailure(
					browser, "sprint14_US1199_TC2463","sprint14_US1199_TC2463",
					"User should be able to add a custom list", "Fail");
			AbstractTest.takeSnapShot("sprint14_US1199_TC2463");
		}
	}
	
	//TC2464: To verify if the level 1 user is able to cancel a custom list add
	@Test()
	public void sprint14_US1199_TC2464() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.level1StoreId;
		String userId = GlobalVariable.level1UserId;
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
		//enter custom list name
		customRawItemListsPage.CreateNewRawItemListPopup_Name_TB.sendKeys(rawItemListName);
		//click on cancel button
		customRawItemListsPage.CreateNewRawItemListPopup_Cancel_BT.click();
		// verify that custom list should not be added in custom Raw Item list page
		if (!customRawItemListsPage.verifyCustomListIsDisplayed(rawItemListName)) {
			Reporter.reportPassResult(
					browser, "sprint14_US1199_TC2464",
					"User should be  able to cancel a custom list add", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint14_US1199_TC2464","sprint14_US1199_TC2464",
					"User should be  able to cancel a custom list add", "Fail");
			AbstractTest.takeSnapShot("sprint14_US1199_TC2464");
		}
	}
	
	//TC2465: To verify if the level 1 user is able to delete a custom list
	@Test()
	public void sprint14_US1199_TC2465() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.level1StoreId;
		String userId = GlobalVariable.level1UserId;
		String rawItemListName = "Test" + Base.generateNdigitRandomNumber(3);
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Go to Custom raw Item list page
		CustomRawItemListsPage customRawItemListsPage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToCustomRawItemListsPage();
		//create new custom list 
		customRawItemListsPage.createACustomRawList(rawItemListName);
		//verify new list is added and displayed
		boolean result = customRawItemListsPage.verifyCustomListIsDisplayed(rawItemListName);
		//Delete the added custom list
		customRawItemListsPage.deleteCustomRawList(rawItemListName);
		//Verify that custom list should be deleted and should not displayed in custom Raw Item list page
		result = result & (!customRawItemListsPage.verifyCustomListIsDisplayed(rawItemListName));
		if (result) {
			Reporter.reportPassResult(browser, "sprint14_US1199_TC2465",
					"level 1 user should be able to delete a custom list", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint14_US1199_TC2465","sprint14_US1199_TC2465",
					"level 1 user should be able to delete a custom list", "Fail");
			AbstractTest.takeSnapShot("sprint14_US1199_TC2465");
		}
	}
	
	//TC2466: To verify if the level 1 user is able to restore a custom list
	@Test()
	public void sprint14_US1199_TC2466() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.level1StoreId;
		String userId = GlobalVariable.level1UserId;
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
					browser, "sprint14_US1199_TC2466",
					"level 1 user should be able to restore a custom list",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint14_US1199_TC2466","sprint14_US1199_TC2466",
					"level 1 user should be able to restore a custom list",
					"Fail");
			AbstractTest.takeSnapShot("sprint14_US1199_TC2466");
		}
	}
	
	// TC2468: To verify if the level 1 user is able to only view the manual vendor page
	@Test()
	public void sprint14_US1199_TC2468() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		ManualVendorsPage manualVendorsPage;
		String storeId = GlobalVariable.level1StoreId;
		String userId = GlobalVariable.level1UserId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Manual Vendor page
		manualVendorsPage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToManualVendorsPage();
		//Verify that level 1 user is not able to view Add Vendor Button in Manual Vendor page
		boolean result = (!Base.isElementDisplayed(manualVendorsPage.AddVendor_BT));
		//Get the name of any vendor
		String vendorName = manualVendorsPage.vendorName_List.get(0).getText();
		//Verify that 'Edit' and 'Delete' button should not be displayed for level 1 user
		result = result & (!manualVendorsPage.verifyEditButtonDisplayed(vendorName) 
				& (!manualVendorsPage.verifyVendorDeleted(vendorName)));
		if (result) {
			Reporter.reportPassResult(
					browser, "sprint14_US1199_TC2466",
					"level 1 user should be able to only view the manual vendor page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint14_US1199_TC2466","sprint14_US1199_TC2466",
					"level 1 user should be able to only view the manual vendor page",
					"Fail");
			AbstractTest.takeSnapShot("sprint14_US1199_TC2466");
		}
	}
}
