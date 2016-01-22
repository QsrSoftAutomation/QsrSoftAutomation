package sprint2;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import common.Base;
import common.GlobalVariable;
import common.ReadTestData;
import common.Reporter;
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.PhysicalInventoryPage;

public class US9_PhysicalInventoryRawItemWRIN extends AbstractTest {

	// TC1826: Verify that the user is able is view the 8 digit WRIN in the "WRIN" column on the Physical Inventory page.
	@Test()
	public void sprint2_US9_TC1826() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PhysicalInventoryPage physicalInventoryPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet physicalInventoryPageSheet = ReadTestData.getTestDataSheet("sprint2_US9_TC1826", "Object1");
		String wrinId = ReadTestData.getTestData(physicalInventoryPageSheet, "WRINId");
		String outerPackQty = ReadTestData.getTestData(physicalInventoryPageSheet, "OuterPackQty");
		String looseCountQty = ReadTestData.getTestData(physicalInventoryPageSheet, "LooseUnitsQty");
		String inventoryType = ReadTestData.getTestData(physicalInventoryPageSheet, "ListType");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to physical inventory Page >> Save a physical inventory
		physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPhysicalInventoryPage()
				.saveANewInventory(inventoryType,wrinId, outerPackQty,"", looseCountQty);
		// Get the time for the inventory
		String time = physicalInventoryPage.InventoryTime_Label.getText().split("Time: ")[1];
		//Click on Menu Btn
		homePage.Menu_DD_BT.click();
		//Navigate to physical Inventory and click on the saved inventory with status "In-Progress".
		physicalInventoryPage.goToPhysicalInventoryPage().clickOnInProgressInventory(Base.returnTodayDate(), time);
		// Verify that the user is able is view the 8 digit WRIN in the "WRIN" column on the Physical Inventory page
		if (physicalInventoryPage.verifyWrinNumberAndDescriptionIsDisplayedForEachRawItem()) {
			Reporter.reportPassResult(
					browser,"sprint2_US9_TC1826",
					"User should be able to view the 8-digit WRIN=xxxxxxxx in WRIN column.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint2_US9_TC1826","sprint2_US9_TC1826",
					"User should be able to view the 8-digit WRIN=xxxxxxxx in WRIN column.",
					"Fail");
			AbstractTest.takeSnapShot("sprint2_US9_TC1826");

		}
	}
	// TC1827: Verify that an 8 digit WRIN with leading zeroes will be displayed with the leading zeroes.
	@Test()
	public void sprint2_US9_TC1827() throws RowsExceededException,BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PhysicalInventoryPage physicalInventoryPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet physicalInventoryPageSheet = ReadTestData.getTestDataSheet("sprint2_US9_TC1827", "Object1");
		String wrinId = ReadTestData.getTestData(physicalInventoryPageSheet, "WRINId");
		String description = ReadTestData.getTestData(physicalInventoryPageSheet, "Description");
		String outerPackQty = ReadTestData.getTestData(physicalInventoryPageSheet, "OuterPackQty");
		String looseCountQty = ReadTestData.getTestData(physicalInventoryPageSheet, "LooseUnitsQty");
		String inventoryType = ReadTestData.getTestData(physicalInventoryPageSheet, "ListType");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to physical inventory Page >> Save a physical inventory
		physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPhysicalInventoryPage()
				.saveANewInventory(inventoryType,wrinId, outerPackQty,"", looseCountQty);
		// Get the time for the inventory
		String time = physicalInventoryPage.InventoryTime_Label.getText().split("Time: ")[1];
		//Click on Menu Btn
		homePage.Menu_DD_BT.click();
		//Navigate to physical Inventory and click on the saved inventory with status "In-Progress".
		physicalInventoryPage.goToPhysicalInventoryPage().clickOnInProgressInventory(Base.returnTodayDate(), time);
		//Verify that user should be able to view the WRIN number with leading zeroes.
		if (physicalInventoryPage.verifyWrinNumberAndDescriptionIsDisplayedForARawItem(wrinId,description)) {
			Reporter.reportPassResult(
					browser,"sprint2_US9_TC1827",
					"User should be able to view the WRIN number with leading zeroes.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint2_US9_TC1827","sprint2_US9_TC1827",
					"User should be able to view the WRIN number with leading zeroes.",
					"Fail");
			AbstractTest.takeSnapShot("sprint2_US9_TC1827");

		}
	}
	
	// TC1835: Verify that the user should be able to view the 8 digit WRIN in the format as first 5 digits and then a dash ("-") followed by the remaining 3 digits.
	@Test()
	public void sprint2_US9_TC1835() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PhysicalInventoryPage physicalInventoryPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet physicalInventoryPageSheet = ReadTestData.getTestDataSheet("sprint2_US9_TC1835", "Object1");
		String wrinId = ReadTestData.getTestData(physicalInventoryPageSheet, "WRINId");
		String outerPackQty = ReadTestData.getTestData(physicalInventoryPageSheet, "OuterPackQty");
		String looseCountQty = ReadTestData.getTestData(physicalInventoryPageSheet, "LooseUnitsQty");
		String inventoryType = ReadTestData.getTestData(physicalInventoryPageSheet, "ListType");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to physical inventory Page >> Save a physical inventory
		physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPhysicalInventoryPage()
				.saveANewInventory(inventoryType,wrinId, outerPackQty,"", looseCountQty);
		// Get the time for the inventory
		String time = physicalInventoryPage.InventoryTime_Label.getText().split("Time: ")[1];
		//Click on Menu Btn
		homePage.Menu_DD_BT.click();
		//Navigate to physical Inventory and click on the saved inventory with status "In-Progress".
		physicalInventoryPage.goToPhysicalInventoryPage().clickOnInProgressInventory(Base.returnTodayDate(), time);
		Thread.sleep(1500);
		// Verify that user should be able to view the 8-digit WRIN as xxxxx-xxx in WRIN column.
		if (physicalInventoryPage.verifyWrinNumberAndDescriptionIsDisplayedForEachRawItem()) {
			Reporter.reportPassResult(
					browser,"sprint2_US9_TC1835",
					"User should be able to view the 8 digit WRIN in the format as first 5 digits and then a dash ('-') followed by the remaining 3 digits",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint2_US9_TC1835","sprint2_US9_TC1835",
					"User should be able to view the 8 digit WRIN in the format as first 5 digits and then a dash ('-') followed by the remaining 3 digits",
					"Fail");
			AbstractTest.takeSnapShot("sprint2_US9_TC1835");

		}
	}
	
	// TC1836: Verify that an 8 digit WRIN with trailing zeroes will be displayed with the trailing zeroes.
	@Test()
	public void sprint2_US9_TC1836() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PhysicalInventoryPage physicalInventoryPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet physicalInventoryPageSheet = ReadTestData.getTestDataSheet("sprint2_US9_TC1836", "Object1");
		String wrinId = ReadTestData.getTestData(physicalInventoryPageSheet, "WRINId");
		String description = ReadTestData.getTestData(physicalInventoryPageSheet, "Description");
		String outerPackQty = ReadTestData.getTestData(physicalInventoryPageSheet, "OuterPackQty");
		String looseCountQty = ReadTestData.getTestData(physicalInventoryPageSheet, "LooseUnitsQty");
		String inventoryType = ReadTestData.getTestData(physicalInventoryPageSheet, "ListType");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to physical inventory Page >> Save a physical inventory
		physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPhysicalInventoryPage()
				.saveANewInventory(inventoryType,wrinId, outerPackQty,"", looseCountQty);
		// Get the time for the inventory
		String time = physicalInventoryPage.InventoryTime_Label.getText().split("Time: ")[1];
		//Click on Menu Btn
		homePage.Menu_DD_BT.click();
		//Navigate to physical Inventory and click on the saved inventory with status "In-Progress".
		physicalInventoryPage.goToPhysicalInventoryPage().clickOnInProgressInventory(Base.returnTodayDate(), time);
		// Verify that user should be able to view the WRIN number with trailing zeroes.
		if (physicalInventoryPage.verifyWrinNumberAndDescriptionIsDisplayedForARawItem(wrinId,description)) {
			Reporter.reportPassResult(
					browser,"sprint2_US9_TC1836",
					"User should be able to view the WRIN number with trailing zeroes.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint2_US9_TC1836","sprint2_US9_TC1836",
					"User should be able to view the WRIN number with leading zeroes.",
					"Fail");
			AbstractTest.takeSnapShot("sprint2_US9_TC1836");

		}
	}
	
	// TC1837: Verify that the user should be able to view the 8 digit WRIN justified on left side within the "WRIN" column.
	@Test()
	public void sprint2_US9_TC1837() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PhysicalInventoryPage physicalInventoryPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet physicalInventoryPageSheet = ReadTestData.getTestDataSheet("sprint2_US9_TC1837", "Object1");
		String wrinId = ReadTestData.getTestData(physicalInventoryPageSheet, "WRINId");
		String outerPackQty = ReadTestData.getTestData(physicalInventoryPageSheet, "OuterPackQty");
		String looseCountQty = ReadTestData.getTestData(physicalInventoryPageSheet, "LooseUnitsQty");
		String inventoryType = ReadTestData.getTestData(physicalInventoryPageSheet, "ListType");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to physical inventory Page >> Save a physical inventory
		physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPhysicalInventoryPage()
				.saveANewInventory(inventoryType,wrinId, outerPackQty,"", looseCountQty);
		// Get the time for the inventory
		String time = physicalInventoryPage.InventoryTime_Label.getText().split("Time: ")[1];
		//Click on Menu Btn
		homePage.Menu_DD_BT.click();
		//Navigate to physical Inventory and click on the saved inventory with status "In-Progress".
		physicalInventoryPage.goToPhysicalInventoryPage().clickOnInProgressInventory(Base.returnTodayDate(), time);
		Thread.sleep(1500);
		// Verify that the user should be able to view the 8 digit WRIN justified on left side within the "WRIN" column.
		if (physicalInventoryPage.verifyWRINColumnIsOnLeft()
				&& physicalInventoryPage.verifyWrinNumberAndDescriptionIsDisplayedForEachRawItem()) {
			Reporter.reportPassResult(
					browser,"sprint2_US9_TC1837",
					"User should be able to view the WRIN number justified on left side within the WRIN column.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint2_US9_TC1837","sprint2_US9_TC1837",
					"User should be able to view the WRIN number justified on left side within the WRIN column.",
					"Fail");
			AbstractTest.takeSnapShot("sprint2_US9_TC1837");

		}
	}

}
