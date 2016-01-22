package sprint12;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import sprint2.AbstractTest;
import common.Base;
import common.GlobalVariable;
import common.Reporter;
import eInventoryPageClasses.VarianceStatPage;
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.PromotionsAndWastePage;
import eInventoryPageClasses.RawItemActivityPage;
import eInventoryPageClasses.RawItemInformationPage;
import eInventoryPageClasses.RawItemWastePage;
import eInventoryPageClasses.VarianceStatPage;

public class US1203_Level5Access extends AbstractTest {

	// TC2116: Verify that the level 5 user should be able to view the Stat Variance page.
	@Test()
	public void sprint12_US1203_TC2116() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		VarianceStatPage varianceStatPage;
		String storeId = GlobalVariable.level5StoreId;
		String userId = GlobalVariable.level5UserId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Go to 'Daily stat' page and verify that user is able to navigated to Daily Stat Page
		varianceStatPage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().navigateToOtherInventoryFunctions().goToVarianceStatPage();
		if (Base.isElementDisplayed(varianceStatPage.VarianceStatType_DD)) {
			Reporter.reportPassResult(
					browser, "sprint12_US1203_TC2116",
					"Level 6 user should be redirected to Daily Stat page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint12_US1203_TC2116","sprint12_US1203_TC2116",
					"Level 6 user should be redirected to Daily Stat page",
					"Fail");
			AbstractTest.takeSnapShot("sprint12_US1203_TC2116");
		}
	}
	
	// TC2117: Verify that the level 5 user should be able to enter Raw Waste.
	@Test()
	public void sprint12_US1203_TC2117() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.level5StoreId;
		String userId = GlobalVariable.level5UserId;
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
		if (rawItemWastePage.verifyWasteItemIsAdded(wrinId01)) {
			Reporter.reportPassResult(
					browser, "sprint12_US1203_TC2117",
					"Level 6 user should be able to enter Raw Waste.", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "sprint12_US1203_TC2117","sprint12_US1203_TC2117",
					"Level 6 user should be able to enter Raw Waste.",
					"Fail");
			AbstractTest.takeSnapShot("sprint12_US1203_TC2117");
		}
	}
	
	/*TC2118: Verify that the user is restricted from all other views and functionality in the Inventory system except 
	physical inventory, state variance page, raw waste and raw item information page.*/
	@Test()
	public void sprint12_US1203_TC2118() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.level5StoreId;
		String userId = GlobalVariable.level5UserId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// go to Inventory Management
		homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement();
		Thread.sleep(4000);
		// Verify that purchage page and custom raw item list page should not display
		if (Base.isElementDisplayed(homePage.Purchases_BT)&& Base.isElementDisplayed(homePage.CustomRawItemLists_BT)) {
			Reporter.reportTestFailure(
					browser,"sprint11_US1204_TC2053_Condition1","sprint11_US1204_TC2053",
					"Purchage Page and Custome Raw Item list page should not display",
					"Fail");
			AbstractTest.takeSnapShot("sprint11_US1204_TC2052_Condition1");
		} else {
			Reporter.reportPassResult(
					browser,"sprint11_US1204_TC2053",
					"Purchage Page and Custome Raw Item list page should not display",
					"Pass");
		}
		// Verify that Physical Inventory and Promotion and waste link should display
		if (Base.isElementDisplayed(homePage.PhysicalInventory_BT) && Base.isElementDisplayed(homePage.PromotionAndWaste_BT)) {
			Reporter.reportPassResult(
					browser,"sprint11_US1204_TC2053",
					"Physical Inventory Page and Promotion and waste page should  display",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint11_US1204_TC2053_Condition2","sprint11_US1204_TC2053",
					"Physical Inventory Page and Promotion and waste page should  display",
					"Fail");
			AbstractTest.takeSnapShot("sprint11_US1204_TC2052_Condition2");
		}
		// Navigate to other Inventory function page
		homePage.navigateToOtherInventoryFunctions();
		// Verify that Transfer,Manual Vendors ,Menu Item Activity ,Menu Item Information ,Food over base pages should not display
		if (Base.isElementDisplayed(homePage.Transfers_BT) && Base.isElementDisplayed(homePage.ManualVendors_BT)
				&& Base.isElementDisplayed(homePage.MenuItemActivity_BT)&& Base.isElementDisplayed(homePage.MenuItemInformation_BT)
				&& Base.isElementDisplayed(homePage.FoodOverBase_BT)) {
			Reporter.reportTestFailure(
					browser,"sprint11_US1204_TC2053_Condition3","sprint11_US1204_TC2053",
					"Verify that Transfer,Manual Vendors ,Menu Item Activity ,Menu Item Information ,Food over base pages should not display",
					"Fail");
			AbstractTest.takeSnapShot("sprint11_US1204_TC2052_Condition3");
		} else {
			Reporter.reportPassResult(
					browser,"sprint11_US1204_TC2053",
					"Verify that Transfer,Manual Vendors ,Menu Item Activity ,Menu Item Information ,Food over base pages should not display",
					"Pass");
		}
		// Verify that Raw Item Activity,Raw Item Information and Daily stat  page should display
		if (Base.isElementDisplayed(homePage.RawItemActivity_BT)&& Base.isElementDisplayed(homePage.RawItemInformation_BT)
				&& Base.isElementDisplayed(homePage.VarianceStat_BT)) {
			Reporter.reportPassResult(
					browser,"sprint11_US1204_TC2053",
					"Verify that Raw Item Activity,Raw Item Information and Daily stat page should display",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint11_US1204_TC2053_Condition4","sprint11_US1204_TC2053",
					"Verify that Raw Item Activity,Raw Item Information and Daily stat page should display",
					"Fail");
			AbstractTest.takeSnapShot("sprint11_US1204_TC2052_Condition4");
		}
	}
	
	//TC2120:Verify that the level 5 user can VIEW-ONLY the Raw Item Information page.
	@Test()
	public void sprint12_US1203_TC2120() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		RawItemInformationPage rawItemInformationPage;
		String storeId = GlobalVariable.level5StoreId;
		String userId = GlobalVariable.level5UserId;
		String wrinId01 = GlobalVariable.rawItemWatsewrin1;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to raw item info page
		rawItemInformationPage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToRawItemInformationPage();
		// Search for the WRIN ID
		rawItemInformationPage.searchAndSelectWRINID(wrinId01);
		wait.until(ExpectedConditions.visibilityOf(rawItemInformationPage.RawItemInfo_Title_Label));
		// Verify that Manual Purchase check box and ListType drop down is disabled for Level 6 user
		if (rawItemInformationPage.ManualPurchase_CB.getAttribute("disabled").equals("true")
				& rawItemInformationPage.ListType_DD.getAttribute("disabled").equals("true")) {
			Reporter.reportPassResult(
					browser,"sprint12_US1203_TC2120",
					"The raw item information for wrin=x should be readable only",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint12_US1203_TC2120","sprint12_US1203_TC2120",
					"The raw item information for wrin=x should be readable only",
					"Fail");
			AbstractTest.takeSnapShot("sprint12_US1203_TC2120");
		}
	}
	
	// TC2121:Verify that the level 5 user can VIEW-ONLY the Raw Item Activity page.
	@Test()
	public void sprint12_US1203_TC2121() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		String storeId = GlobalVariable.level5StoreId;
		String userId = GlobalVariable.level5UserId;
		String wrin = GlobalVariable.rawItemActivityWrin;
		RawItemActivityPage rawItemActivityPage;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to raw item info page
		rawItemActivityPage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToRawItemActivityPage();
		// Search and select a wrin ID
		rawItemActivityPage.searchAndSelectWRINID(wrin);
		wait.until(ExpectedConditions.visibilityOf(rawItemActivityPage.getItemDetails_Button));
		rawItemActivityPage.getItemDetails_Button.click();
		wait.until(ExpectedConditions.visibilityOf(rawItemActivityPage.DoneWithThisItem_BT));
		if (Base.isElementDisplayed(rawItemActivityPage.DoneWithThisItem_BT) 
				& driver.findElements(By.xpath("//button[@id='view_details_btn']")).size()==0) {
			Reporter.reportPassResult(
					browser,"sprint11_US1204_TC2119",
					"Raw Item activity page should display in non editable mode",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint11_US1204_TC2119","sprint11_US1204_TC2119",
					"Raw Item activity page should display in non editable mode",
					"Fail");
			AbstractTest.takeSnapShot("sprint11_US1204_TC2119");
		}
	}
}
