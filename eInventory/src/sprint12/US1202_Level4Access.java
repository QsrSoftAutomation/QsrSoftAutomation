package sprint12;

import java.io.IOException;
import java.util.List;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import sprint2.AbstractTest;
import common.Base;
import common.GlobalVariable;
import common.ReadTestData;
import common.Reporter;
import eInventoryPageClasses.CustomRawItemListsPage;
import eInventoryPageClasses.VarianceStatPage;
import eInventoryPageClasses.FoodOverBasePage;
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.ManualInvoiceNewPage;
import eInventoryPageClasses.MenuItemActivityPage;
import eInventoryPageClasses.PromotionsAndWastePage;
import eInventoryPageClasses.PurchasesPage;
import eInventoryPageClasses.RawItemActivityPage;
import eInventoryPageClasses.RawItemInformationPage;
import eInventoryPageClasses.RawItemWastePage;
import eInventoryPageClasses.TransferLandingPage;
import eInventoryPageClasses.VarianceStatPage;

public class US1202_Level4Access extends AbstractTest {

	// TC2126: Verify that the level 4 user should be able to enter a manual purchase.
	@Test()
	public void sprint12_US1202_TC2126() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.level4StoreId;
		String userId = GlobalVariable.level4UserId;
		String wrinId = "00406";
		String vendor = "jon";
		String quantity = "1";
		/***********************************/
		String invoiceNumber = Base.randomNumberFiveDigit();
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		PurchasesPage purchasesPage = homePage.selectUser(userId)
				.selectLocation(storeId).navigateToInventoryManagement().goToPurchaseLandingPage();
		// Go to manual invoice new page then select a vendor then add a row item
		ManualInvoiceNewPage manualInvoiceNewPage = purchasesPage
				.goToManualInvoiceNewPage().createAManualPurchaseForWrinID(wrinId, vendor, quantity, invoiceNumber);
		boolean invoiceSaved = Base.isElementDisplayed(manualInvoiceNewPage.InvoiceSaved_Confirmation_MSG);
		manualInvoiceNewPage.clickOnFinalizeButton().FinalizePopUp_Continue_BT.click();
		wait.until(ExpectedConditions.visibilityOf(manualInvoiceNewPage.InvoiceFinalize_Confirmation_MSG));
		boolean invoicePosted = Base.isElementDisplayed(manualInvoiceNewPage.InvoiceFinalize_Confirmation_MSG);
		if (invoiceSaved & invoicePosted) {
			Reporter.reportPassResult(
					browser, "sprint12_US1202_TC2126",
					"Level 4 user should be able to enter a manual purchase.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint12_US1202_TC2126","sprint12_US1202_TC2126",
					"Level 4 user should be able to enter a manual purchase.",
					"Fail");
			AbstractTest.takeSnapShot("sprint12_US1202_TC2126");
		}
	}
	
	// TC2127: Verify that the level 4 user is restricted from the "restore purchases" functionality on the Purchases page.
	@Test()
	public void sprint12_US1202_TC2127() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.level4StoreId;
		String userId = GlobalVariable.level4UserId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		PurchasesPage purchasesPage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPurchaseLandingPage();
		// Go to manual invoice new page then select a vendor then add a row item
		if (Base.isElementDisplayed(purchasesPage.RestorePurchases_BT)) {
			Reporter.reportTestFailure(
					browser,"sprint12_US1202_TC2127","sprint12_US1202_TC2127",
					"Level 4 User should not be able to access restore purchases page.",
					"Fail");
			AbstractTest.takeSnapShot("sprint12_US1202_TC2127");
		} else {
			Reporter.reportPassResult(
					browser,"sprint12_US1202_TC2127",
					"Level 4 User should not be able to access restore purchases page.",
					"Pass");
		}
	}
	
	// TC2128: Verify that the level 4 user is restricted from viewing the Store Ledger on the Purchases page.
	@Test()
	public void sprint12_US1202_TC2128() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.level4StoreId;
		String userId = GlobalVariable.level4UserId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		PurchasesPage purchasesPage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPurchaseLandingPage();
		// Go to manual invoice new page then select a vendor then add a row item
		if (Base.isElementDisplayed(purchasesPage.ViewStoreLedger_BT)) {
			Reporter.reportTestFailure(
					browser,"sprint12_US1202_TC2128","sprint12_US1202_TC2128",
					"Level 4 User should be restricted from viewing the Store Ledger on the Purchases page",
					"Fail");
			AbstractTest.takeSnapShot("sprint12_US1202_TC2128");
		} else {
			Reporter.reportPassResult(
					browser,"sprint12_US1202_TC2128",
					"Level 4 User should be restricted from viewing the Store Ledger on the Purchases page",
					"Pass");
		}
	}
	
	// TC2129:Verify that the level 4 user is able to enter raw waste.
	@Test()
	public void sprint12_US1202_TC2129() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.level4StoreId;
		String userId = GlobalVariable.level4UserId;
		String wrinId01 = GlobalVariable.rawItemWatsewrin1;
		String outerPack = "1";
		String innerPack = "1";
		String looseUnits = "1";
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to promo and waste landing page
		PromotionsAndWastePage promotionsAndWastePage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPromotionsAndWastePage();
		// click on waste tab
		promotionsAndWastePage.RawWaste_BT.click();
		RawItemWastePage rawItemWastePage = PageFactory.initElements(driver,RawItemWastePage.class);
		wait.until(ExpectedConditions.visibilityOf(rawItemWastePage.EnterNewRawWaste_Title));
		// Add a raw waste item
		rawItemWastePage.addARawItem(wrinId01, innerPack, outerPack, looseUnits);
		Thread.sleep(3000);
		// Verify that Level 6 user should be able to enter Raw Waste.
		boolean wasteItemAdded = rawItemWastePage.verifyWasteItemIsAdded(wrinId01);
		rawItemWastePage.BackToTop_BT.click();
		Thread.sleep(3000);
		rawItemWastePage.CancelEntry_BT.click();
		boolean wasteEntryCanceled = Base.isElementDisplayed(rawItemWastePage.RawWasteEntryIncomplete_PopUp_NO_BT);
		rawItemWastePage.RawWasteEntryIncomplete_PopUp_NO_BT.click();
		if (wasteItemAdded & wasteEntryCanceled) {
			Reporter.reportPassResult(
					browser, "sprint12_US1202_TC2129",
					"Level 4 user should be able to enter raw waste", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint12_US1202_TC2129","sprint12_US1202_TC2129",
					"Level 4 user should be able to enter raw waste",
					"Fail");
			AbstractTest.takeSnapShot("sprint12_US1202_TC2129");
		}
	}
	
	//TC2130:Verify that the level 4 user is not able to enter raw promo.
	@Test()
	public void sprint12_US1202_TC2130() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.level4StoreId;
		String userId = GlobalVariable.level4UserId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		PromotionsAndWastePage promotionsAndWastePage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToPromotionsAndWastePage();
		// Go to manual invoice new page then select a vendor then add a row item
		if (Base.isElementDisplayed(promotionsAndWastePage.RawPromo_BT)) {
			Reporter.reportTestFailure(
					browser,"sprint12_US1202_TC2130","sprint12_US1202_TC2130",
					"Level 4 user should not able to enter raw promo",
					"Fail");
			AbstractTest.takeSnapShot("sprint12_US1202_TC2130");
		} else {
			Reporter.reportPassResult(
					browser,"sprint12_US1202_TC2130",
					"Level 4 user should not able to enter raw promo",
					"Pass");
		}
	}
	
	//TC2131:Verify that the level 4 user is able transfer raw items in/out to other stores.
	@Test()
	public void sprint12_US1202_TC2131() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.level4StoreId;
		String userId = GlobalVariable.level4UserId;
		HSSFSheet transferLandingPageSheet = ReadTestData.getTestDataSheet("Sprint5_US290_TC1142", "Object1");
		String wrinId = ReadTestData.getTestData(transferLandingPageSheet, "WRINId");
		String nationalStoreNumber = ReadTestData.getTestData(transferLandingPageSheet, "InputNationalStoreNumber");
		String outerPack = ReadTestData.getTestData(transferLandingPageSheet, "OuterPackQty");
		String innerPack = ReadTestData.getTestData(transferLandingPageSheet, "InnerPackQty");
		String looseUnits = ReadTestData.getTestData(transferLandingPageSheet, "LooseUnitsQty");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		TransferLandingPage transferLandingPage = homePage.selectUser(userId)
				.selectLocation(storeId).navigateToInventoryManagement().goToTransferLandingPage();
		// Proceed to insert a new transfer against a WRIN to office
		transferLandingPage.CreateNewTransfers_BT.click();
		wait.until(ExpectedConditions.visibilityOf(transferLandingPage.InsertNewTransfersPopup_Time_Value));
		String time=transferLandingPage.InsertNewTransfersPopup_Time_Value.getText().trim();
		transferLandingPage.InsertNewTransfersPopup_Cancel_BT.click();
		wait.until(ExpectedConditions.visibilityOf(transferLandingPage.CanceTransferPopup_Yes_BT));
		Thread.sleep(2000);
		transferLandingPage.CanceTransferPopup_Yes_BT.click();
		//create a transfer out transaction
		wait.until(ExpectedConditions.visibilityOf(transferLandingPage.CreateNewTransfers_BT));
		transferLandingPage.CreateNewTransfers_BT.click();
		wait.until(ExpectedConditions.visibilityOf(transferLandingPage.InsertNewTransfersPopup_InputNationalStoreNo_TB));
		transferLandingPage.InsertNewTransfersPopup_InputNationalStoreNo_TB.sendKeys(nationalStoreNumber);
		//click on transfer out or in
		transferLandingPage.InsertNewTransfersPopup_TransferOut_RB.click();
		transferLandingPage.insertAndAddDetailsToTransfer(wrinId, outerPack, innerPack, looseUnits);
		Thread.sleep(3000);
		String amount = transferLandingPage.AddTransferPopup_TotalAmount_Value.getText().split("\\$")[1];
		transferLandingPage.AddTransferItemsPopup_Submit_BT.click();
		Thread.sleep(5000);
		if (transferLandingPage.verifyTransferPlaced(Base.returnTodayDate(), time, amount)) {
			Reporter.reportPassResult(
					browser, "sprint12_US1202_TC2126",
					"Level 4 user should be able to transfer raw items in/out to other stores",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint12_US1202_TC2126","sprint12_US1202_TC2126",
					"Level 4 user should be able to transfer raw items in/out to other stores",
					"Fail");
			AbstractTest.takeSnapShot("sprint12_US1202_TC2126");
		}
	}
	
	//TC2133:Verify that the level 4 user is restricted from the "office" transfer.
	@Test()
	public void sprint12_US1202_TC2133() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.level4StoreId;
		String userId = GlobalVariable.level4UserId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		TransferLandingPage transferLandingPage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToTransferLandingPage();
		// Proceed to insert a new transfer against a WRIN to office
		transferLandingPage.CreateNewTransfers_BT.click();
		if (Base.isElementDisplayed(transferLandingPage.transferToOffice_chkBox)) {
			Reporter.reportTestFailure(
					browser,"sprint12_US1202_TC2133","sprint12_US1202_TC2133",
					"Level 4 user should be restricted from the office transfer",
					"Fail");
			AbstractTest.takeSnapShot("sprint12_US1202_TC2133");
		} else {
			Reporter.reportPassResult(
					browser,"sprint12_US1202_TC2133",
					"Level 4 user should be restricted from the office transfer",
					"Pass");
		}
	}
	
	//TC2134:Verify that the level 4 user can use all the functionality on the Stat Variance page.
	@Test()
	public void sprint12_US1202_TC2134() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.level4StoreId;
		String userId = GlobalVariable.level4UserId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Promo and waste page
		VarianceStatPage dailyStatPage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToVarianceStatPage();
		dailyStatPage.StartDate_BT.click();
		Thread.sleep(2000);
		String date = "07/02/2015";
		int month = Base.getMonthFromDate(date);
		String monthName = Base.getMonthName(month+1);
		//dailyStatPage.clickOnMonth(monthName);
		dailyStatPage.selectDateFromCalender(date);
		Select select  = new Select(dailyStatPage.ListType_DD);
		List<WebElement>listType = select.getOptions();
		boolean listTypeDisplayed = listType.get(1).getText().equals("Daily");
		listTypeDisplayed = listTypeDisplayed & listType.get(2).getText().equals("Weekly");
		listTypeDisplayed = listTypeDisplayed & listType.get(3).getText().equals("Monthly");
		if (listTypeDisplayed) {
			Reporter.reportPassResult(
					browser, "sprint12_US1202_TC2134",
					"Level 4 user should be able to enter raw waste", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint12_US1202_TC2134","sprint12_US1202_TC2134",
					"Level 4 user should be able to enter raw waste",
					"Fail");
			AbstractTest.takeSnapShot("sprint12_US1202_TC2134");
		}
	}
	
	//TC2136:Verify that the level 4 user can use all the functionality on the Food over base page.
	@Test()
	public void sprint12_US1202_TC2136() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.level4StoreId;
		String userId = GlobalVariable.level4UserId;
		String newValue = "12";
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Promo and waste page
		FoodOverBasePage foodOverBasePage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToFoodOverBasePage();
		//Click on Target percentage column header link image
		foodOverBasePage.CurrentMonth_TargetPercent_ColumnHeader_Image_LK.click();
		wait.until(ExpectedConditions.visibilityOf(foodOverBasePage.PostTargetValuesPopUp_BaseFood_TB));
		foodOverBasePage.editTargetPercentValues(newValue);
		Thread.sleep(4000);
		newValue = newValue+"%";
		if (foodOverBasePage.CurrentMonth_BaseFood_TargetPercent_Value.getText().equals(newValue)
				& foodOverBasePage.CurrentMonth_MenuItemWaste_TargetPercent_Value.getText().equals(newValue)
				& foodOverBasePage.CurrentMonth_RawWaste_TargetPercent_Value.getText().equals(newValue)
				& foodOverBasePage.CurrentMonth_Condiment_TargetPercent_Value.getText().equals(newValue)
				& foodOverBasePage.CurrentMonth_EmployeeManagerFood_TargetPercent_Value.getText().equals(newValue)
				& foodOverBasePage.CurrentMonth_DiscountCoupon_TargetPercent_Value.getText().equals(newValue)
				& foodOverBasePage.CurrentMonth_StatVariance_TargetPercent_Value.getText().equals(newValue)
				& foodOverBasePage.CurrentMonth_UnexplainedDifference_TargetPercent_Value.getText().equals(newValue)){
			Reporter.reportPassResult(
					browser, "sprint12_US1202_TC2136",
					"Level 4 user should be able to edit targetPercent value for current month in FoodOverBase Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint12_US1202_TC2136","sprint12_US1202_TC2136",
					"Level 4 user should be able to edit targetPercent value for current month in FoodOverBase Page",
					"Fail");
			AbstractTest.takeSnapShot("sprint12_US1202_TC2136");
		}
	}
	
	//TC2137:Verify that the level 4 user can use all the functionality on the Raw Item Activity page.
	@Test()
	public void sprint12_US1202_TC2137() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.level4StoreId;
		String userId = GlobalVariable.level4UserId;
		String wrinId = GlobalVariable.addTransferItemWrin;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Promo and waste page
		RawItemActivityPage rawItemActivityPage = homePage.selectUser(userId)
				.selectLocation(storeId).navigateToInventoryManagement().goToRawItemActivityPage();
		wait.until(ExpectedConditions.visibilityOf(rawItemActivityPage.RawItemActivity_Label));
		rawItemActivityPage.searchAndSelectWRINID(wrinId);
		rawItemActivityPage.StartDate_BT.click();
		Thread.sleep(2000);
		rawItemActivityPage.selectStartDate(Base.returnTodayDate());
		rawItemActivityPage.EndDate_BT.click();
		Thread.sleep(2000);
		rawItemActivityPage.selectEndDate(Base.returnTodayDate());
		rawItemActivityPage.getItemDetails_Button.click();
		boolean rawItemDetailDisplayed = rawItemActivityPage.RawItemActivity_Header.getText().contains(wrinId);
		rawItemActivityPage.DoneWithThisItem_BT.click();
		if(rawItemDetailDisplayed & Base.isElementDisplayed(rawItemActivityPage.Search_TB)){
			Reporter.reportPassResult(browser, "sprint12_US1202_TC2137",
					"Level 4 user should be able to enter raw waste", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint12_US1202_TC2137","sprint12_US1202_TC2137",
					"Level 4 user should be able to enter raw waste", "Fail");
			AbstractTest.takeSnapShot("sprint12_US1202_TC2137");
		}
	}
	
	//TC2140:Verify that the level 4 user can use all the functionality on the Menu Item activity page.
	@Test()
	public void sprint12_US1202_TC2140() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.level4StoreId;
		String userId = GlobalVariable.level4UserId;
		String menuItemNumber = GlobalVariable.menuItem;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Promo and waste page
		MenuItemActivityPage menuItemActivityPage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToMenuItemActivityPage();
		menuItemActivityPage.searchAndSelectMenuItem(menuItemNumber);
		wait.until(ExpectedConditions.visibilityOf(menuItemActivityPage.MiaStartDate_BT));
		//enter start date 
        menuItemActivityPage.enterDateInMenuItemStartDate(Base.returnTodayDate());
       //enter end date 
        menuItemActivityPage.enterDateInMenuItemEndDate(Base.returnTodayDate());
		menuItemActivityPage.ShowResults_BT.click();
		wait.until(ExpectedConditions.visibilityOf(menuItemActivityPage.SelectItem_Value));
		if (Base.isElementDisplayed(menuItemActivityPage.SelectItem_Value)
				& Base.isElementDisplayed(menuItemActivityPage.MenuItemActivity_Table)) {
			Reporter.reportPassResult(
					browser, "sprint12_US1202_TC2140",
					"Level 4 user should be able to enter raw waste", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint12_US1202_TC2140","sprint12_US1202_TC2140",
					"Level 4 user should be able to enter raw waste", "Fail");
			AbstractTest.takeSnapShot("sprint12_US1202_TC2140");
		}
	}
	
	//TC2141:Verify that the level 4 user can READ-ONLY Raw Item Information.
	@Test()
	public void sprint12_US1202_TC2141() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.level4StoreId;
		String userId = GlobalVariable.level4UserId;
		String wrinId01 = GlobalVariable.rawItemWatsewrin1;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to raw item info page
		RawItemInformationPage rawItemInformationPage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToRawItemInformationPage();
		// Search for the WRIN ID
		rawItemInformationPage.searchAndSelectWRINID(wrinId01);
		wait.until(ExpectedConditions.visibilityOf(rawItemInformationPage.RawItemInfo_Title_Label));
		// Verify that Manual Purchase check box and ListType drop down is disabled for Level 6 user
		if (rawItemInformationPage.ManualPurchase_CB.getAttribute("disabled").equals("true")
				& rawItemInformationPage.ListType_DD.getAttribute("disabled").equals("true")) {
			Reporter.reportPassResult(
					browser, "sprint12_US1202_TC2141",
					"Level 4 user should be able to enter raw waste", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint12_US1202_TC2141","sprint12_US1202_TC2141",
					"Level 4 user should be able to enter raw waste", "Fail");
			AbstractTest.takeSnapShot("sprint12_US1202_TC2141");
		}
	}
	
	//TC2142:Verify that the level 4 user is restricted from the Manual Vendors page.
	@Test()
	public void sprint12_US1202_TC2142() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.level4StoreId;
		String userId = GlobalVariable.level4UserId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to raw item info page
		homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement();
		wait.until(ExpectedConditions.elementToBeClickable(homePage.OtherInventoryFunctions_BT));
		homePage.OtherInventoryFunctions_BT.click();
		wait.until(ExpectedConditions.visibilityOf(homePage.RawItemInformation_BT));
		if (Base.isElementDisplayed(homePage.ManualVendors_BT)) {
			Reporter.reportTestFailure(
					browser,"sprint12_US1202_TC2142","sprint12_US1202_TC2142",
					"Level 4 user should be restricted from Manual Vendors page",
					"Fail");
			AbstractTest.takeSnapShot("sprint12_US1202_TC2142");
		} else {
			Reporter.reportPassResult(
					browser,"sprint12_US1202_TC2142",
					"Level 4 user should be restricted from Manual Vendors page",
					"Pass");
		}
	}
	
	// TC2143:Verify that the level 4 user is restricted from all Inventory Restaurant setting functionality.
	@Test()
	public void sprint12_US1202_TC2143() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.level4StoreId;
		String userId = GlobalVariable.level4UserId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to raw item info page
		homePage.selectUser(userId).selectLocation(storeId);
		if (Base.isElementDisplayed(homePage.StoreSetting_BT)) {
			Reporter.reportTestFailure(
					browser,"sprint12_US1202_TC2143","sprint12_US1202_TC2143",
					"Level 4 user should be restricted from store setting page",
					"Fail");
			AbstractTest.takeSnapShot("sprint12_US1202_TC2143");
		} else {
			Reporter.reportPassResult(
					browser,"sprint12_US1202_TC2143",
					"Level 4 user should be restricted from store setting page",
					"Pass");
		}
	}
	
	// TC2144:Verify that the level 4 User is restricted from restore custom list functionality on the Custom List page.
	@Test()
	public void sprint12_US1202_TC2144() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		//** Variable Section : **//*
		String storeId = GlobalVariable.level4StoreId;
		String userId = GlobalVariable.level4UserId;
		//***********************************//*
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to raw item info page
		CustomRawItemListsPage customRawItemListsPage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToCustomRawItemListsPage();
		if (Base.isElementDisplayed(customRawItemListsPage.RestoreLists_BT)) {
			Reporter.reportTestFailure(
					browser,"sprint12_US1202_TC2144","sprint12_US1202_TC2144",
					"Level 4 user should be restricted from restore custom list functionality on the Custom List page",
					"Fail");
			AbstractTest.takeSnapShot("sprint12_US1202_TC2144");
		} else {
			Reporter.reportPassResult(
					browser,"sprint12_US1202_TC2144",
					"Level 4 user should be restricted from restore custom list functionality on the Custom List page",
					"Pass");
		}
	}
}
