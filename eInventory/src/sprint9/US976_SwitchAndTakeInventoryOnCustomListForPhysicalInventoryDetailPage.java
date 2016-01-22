package sprint9;

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
import eInventoryPageClasses.CustomRawItemListsPage;
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.PhysicalInventoryPage;

public class US976_SwitchAndTakeInventoryOnCustomListForPhysicalInventoryDetailPage extends AbstractTest {
	
	//TC1693:Verify that the user should be able to switch to any custom list in the restaurant from the "daily, weekly, monthly" dropdown on the physical inventory page.
	@Test()
	public void sprint9_US976_TC1693() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		CustomRawItemListsPage customRawItemListsPage;
		PhysicalInventoryPage physicalInventoryPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet CustomRawListPageSheet = ReadTestData.getTestDataSheet("sprint9_US976_TC1693", "Object1");
		String wrinIdForCustomRawList = ReadTestData.getTestData(CustomRawListPageSheet,"WRINId");
		String descriptionForCustomRawList = ReadTestData.getTestData(CustomRawListPageSheet,"Description");
		HSSFSheet dailyInventoryPageSheet = ReadTestData.getTestDataSheet("sprint9_US976_TC1693", "Object2");
		String wrinIdForDailyInventory = ReadTestData.getTestData(dailyInventoryPageSheet,"WRINId");
		String descriptionForDailyInventory = ReadTestData.getTestData(dailyInventoryPageSheet,"Description");
		HSSFSheet monthlyInventoryPageSheet = ReadTestData.getTestDataSheet("sprint9_US976_TC1693", "Object3");
		String wrinIdForMonthlyInventory = ReadTestData.getTestData(monthlyInventoryPageSheet,"WRINId");
		String descriptionForMonthlyInventory = ReadTestData.getTestData(monthlyInventoryPageSheet,"Description");
		HSSFSheet weeklyInventoryPageSheet = ReadTestData.getTestDataSheet("sprint9_US976_TC1693", "Object4");
		String wrinIdForWeeklyInventory = ReadTestData.getTestData(weeklyInventoryPageSheet,"WRINId");
		String descriptionForWeeklyInventory = ReadTestData.getTestData(weeklyInventoryPageSheet,"Description");
		/***********************************/
		String customRawListName = "TestList_"+Base.generateNdigitRandomNumber(5);
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to custom Raw Item List Page >> Create a custom raw item list
		customRawItemListsPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement()
				.goToCustomRawItemListsPage().createACustomRawList(customRawListName);
		//Add a wrin to the custom raw list
		customRawItemListsPage.addAWrinInCustomList(customRawListName, wrinIdForCustomRawList).clickOnARawItemList(customRawListName);
		//boolean result =  customRawItemListsPage.isRawItemPresentOnCustomRawItemDeatilTable(wrinIdForCustomRawList);
		//Navigate to physical inventory page and click on start inventory
		//Click on Menu Btn
		homePage.Menu_DD_BT.click();
		physicalInventoryPage = homePage.goToPhysicalInventoryPage();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.StartNewInventory_BT));
		physicalInventoryPage.StartNewInventory_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.StartInventory_BT));
		physicalInventoryPage.StartInventory_BT.click();
		//Select the custom raw list from the dropdown
		try {
			physicalInventoryPage.selectInventoryType(customRawListName);
		}catch(Exception e){
			physicalInventoryPage.EditInventory_BT.click();
			if (Base.isElementDisplayed(physicalInventoryPage.Save_BT)) {
				physicalInventoryPage.selectInventoryType(customRawListName);
			}
		}
		//Verify that raw item is displayed for selected custom raw list
		boolean rawItemListDisplayedForSelectedInventory = physicalInventoryPage.verifyWrinNumberAndDescriptionIsDisplayedForARawItem(wrinIdForCustomRawList,descriptionForCustomRawList);
		//Select the Daily type from the dropdown
		physicalInventoryPage.selectInventoryType("Daily");
		//Verify that raw item is displayed for Daily inventory type
		rawItemListDisplayedForSelectedInventory = rawItemListDisplayedForSelectedInventory
				&& physicalInventoryPage.verifyWrinNumberAndDescriptionIsDisplayedForARawItem(wrinIdForDailyInventory,descriptionForDailyInventory);
		//Select the Monthly type from the dropdown
		physicalInventoryPage.selectInventoryType("Monthly");
		//Verify that raw item is displayed for Monthly inventory type
		rawItemListDisplayedForSelectedInventory = rawItemListDisplayedForSelectedInventory
				&& physicalInventoryPage.verifyWrinNumberAndDescriptionIsDisplayedForARawItem(wrinIdForMonthlyInventory,descriptionForMonthlyInventory);
		//Select the Weekly type from the dropdown
		physicalInventoryPage.selectInventoryType("Weekly");
		//Verify that raw item is displayed for Weekly inventory type
		rawItemListDisplayedForSelectedInventory = rawItemListDisplayedForSelectedInventory
				&& physicalInventoryPage.verifyWrinNumberAndDescriptionIsDisplayedForARawItem(wrinIdForWeeklyInventory,descriptionForWeeklyInventory);
		//Verify user can switch between different inventory type in physical inventory type
		if (rawItemListDisplayedForSelectedInventory) {
			Reporter.reportPassResult(
					browser,"sprint9_US976_TC1693",
					"User should be able to  to switch to any custom list in the restaurant from the 'daily, weekly, monthly' dropdown on the physical inventory page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint9_US976_TC1693","sprint9_US976_TC1693",
					"User should be able to  to switch to any custom list in the restaurant from the 'daily, weekly, monthly' dropdown on the physical inventory page",
					"Fail");
			AbstractTest.takeSnapShot("sprint9_US976_TC1693");
		}
	}
	
	//TC1695:Verify that the user should be able to view the "name" of the custom list in the list dropdown on the physical inventory page.
	@Test()
	public void sprint9_US976_TC1695() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		CustomRawItemListsPage customRawItemListsPage;
		PhysicalInventoryPage physicalInventoryPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet CustomRawListPageSheet = ReadTestData.getTestDataSheet("sprint9_US976_TC1695", "Object1");
		String wrinIdForCustomRawList = ReadTestData.getTestData(CustomRawListPageSheet, "WRINId");
		/***********************************/
		String customRawListName = "TestList_"+ Base.generateNdigitRandomNumber(5);
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to custom Raw Item List Page >> Create a custom raw item list
		customRawItemListsPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement()
				.goToCustomRawItemListsPage().createACustomRawList(customRawListName);
		// Add a wrin to the custom raw list
		customRawItemListsPage.addAWrinInCustomList(customRawListName,wrinIdForCustomRawList).clickOnARawItemList(customRawListName);
		// Navigate to physical inventory page and click on start inventory
		homePage.Menu_DD_BT.click();
		physicalInventoryPage = homePage.goToPhysicalInventoryPage();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.StartNewInventory_BT));
		physicalInventoryPage.StartNewInventory_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.StartInventory_BT));
		physicalInventoryPage.StartInventory_BT.click();
		// Select the custom raw list from the dropdown
		try {
			physicalInventoryPage.selectInventoryType("Daily");
		} catch (Exception e) {
			physicalInventoryPage.EditInventory_BT.click();
			if (Base.isElementDisplayed(physicalInventoryPage.Save_BT)) {
				physicalInventoryPage.selectInventoryType("Daily");
			}
		}
		//Verify custom list is displayed in physical inventory list type dropdown
		if (physicalInventoryPage.verifyListTypeDisplayed(customRawListName)) {
			Reporter.reportPassResult(
					browser,"sprint9_US976_TC1695",
					"User should be able to  to view the name of the custom list in the list dropdown on the physical inventory page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint9_US976_TC1695","sprint9_US976_TC1695",
					"User should be able to  to view the name of the custom list in the list dropdown on the physical inventory page",
					"Fail");
			AbstractTest.takeSnapShot("sprint9_US976_TC1695");
		}
	}
	
	/*TC1696:Verify that the user should be able to add raw items ad hoc to the custom list on the physical inventory page, 
	however, the master "custom list" should stay intact until the manager changes the list from the
	setting on "Custom Raw Item Lists" page.*/
	@Test()
	public void sprint9_US976_TC1696() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		CustomRawItemListsPage customRawItemListsPage;
		PhysicalInventoryPage physicalInventoryPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet CustomRawListPageSheet1 = ReadTestData.getTestDataSheet("sprint9_US976_TC1696", "Object1");
		String wrinId1ForCustomRawList = ReadTestData.getTestData(CustomRawListPageSheet1, "WRINId");
		HSSFSheet CustomRawListPageSheet2 = ReadTestData.getTestDataSheet("sprint9_US976_TC1696", "Object2");
		String wrinId2ForCustomRawList = ReadTestData.getTestData(CustomRawListPageSheet2, "WRINId");
		String Description2ForCustomRawList = ReadTestData.getTestData(CustomRawListPageSheet1, "Description");
		/***********************************/
		String customRawListName = "TestList_"+ Base.generateNdigitRandomNumber(5);
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to custom Raw Item List Page >> Create a custom raw item list
		customRawItemListsPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement()
				.goToCustomRawItemListsPage().createACustomRawList(customRawListName);
		// Add a wrin to the custom raw list
		customRawItemListsPage.addAWrinInCustomList(customRawListName,wrinId1ForCustomRawList).clickOnARawItemList(customRawListName);
		//verify that raw item is added to custom list
		boolean rawItemDisplayed =  customRawItemListsPage.isRawItemPresentOnCustomRawItemDeatilTable(wrinId1ForCustomRawList);
		// Navigate to physical inventory page and click on start inventory
		homePage.Menu_DD_BT.click();
		physicalInventoryPage = homePage.goToPhysicalInventoryPage();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.StartNewInventory_BT));
		physicalInventoryPage.StartNewInventory_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.StartInventory_BT));
		physicalInventoryPage.StartInventory_BT.click();
		// Select the custom raw list from the dropdown
		try {
			physicalInventoryPage.selectInventoryType(customRawListName);
		} catch (Exception e) {
			physicalInventoryPage.EditInventory_BT.click();
			if (Base.isElementDisplayed(physicalInventoryPage.Save_BT)) {
				physicalInventoryPage.selectInventoryType(customRawListName);
			}
		}
		//Add another raw item to the custom list type in physical inventory page
		physicalInventoryPage.addARawItemToList(wrinId2ForCustomRawList);
		//verify that new raw item is displayed in physical inventory table
		rawItemDisplayed = rawItemDisplayed && physicalInventoryPage.verifyWrinNumberAndDescriptionIsDisplayedForARawItem(wrinId2ForCustomRawList, Description2ForCustomRawList);
		//navigate to Custom Raw Item Lists Page and click on the custom list
		homePage.Menu_DD_BT.click();
		homePage.goToCustomRawItemListsPage().clickOnARawItemList(customRawListName);
		//Verify that raw item added in physical inventory page should not displayed in Custom Raw Item Detail Table
		boolean rawItemNotAdded =  customRawItemListsPage.isRawItemPresentOnCustomRawItemDeatilTable(wrinId2ForCustomRawList);
		//Add raw item to the custom list from Custom Raw Item List Page
		homePage.Menu_DD_BT.click();
		homePage.goToCustomRawItemListsPage().addAWrinInCustomList(customRawListName,wrinId2ForCustomRawList);
		//Verify that raw item is added to custom list 
		rawItemDisplayed = rawItemDisplayed && (!rawItemNotAdded)
				&& customRawItemListsPage.clickOnARawItemList(customRawListName).isRawItemPresentOnCustomRawItemDeatilTable(wrinId2ForCustomRawList);
		if (rawItemDisplayed) {
			Reporter.reportPassResult(
					browser,"sprint9_US976_TC1696",
					"User should be able to view the new raw-item added to the master 'custom list' only after user changes the setting in master 'custom list' on 'Custom Raw Item Lists' page.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint9_US976_TC1696","sprint9_US976_TC1696",
					"User should be able to view the new raw-item added to the master 'custom list' only after user changes the setting in master 'custom list' on 'Custom Raw Item Lists' page.",
					"Fail");
			AbstractTest.takeSnapShot("sprint9_US976_TC1696");
		}
	}

}
