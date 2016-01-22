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

public class US8_PhysicalInventoryListOfRawItems extends AbstractTest {
	
	//TC1824: Verify that the user is able to view a table with the following column headers: WRIN, Description, Outer Pack, Inner Pack, Loose Units, Total Units, audit.
	@Test()
	public void sprint2_US8_TC1824() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PhysicalInventoryPage physicalInventoryPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet physicalInventoryPageSheet = ReadTestData.getTestDataSheet("sprint2_US8_TC1824", "Object1");
		String wrinId = ReadTestData.getTestData(physicalInventoryPageSheet,"WRINId");
		String outerPackQty = ReadTestData.getTestData(physicalInventoryPageSheet, "OuterPackQty");
		String looseCountQty = ReadTestData.getTestData(physicalInventoryPageSheet, "LooseUnitsQty");
		String inventoryType = ReadTestData.getTestData(physicalInventoryPageSheet, "ListType");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Physical Inventory page
		physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPhysicalInventoryPage()
				.saveANewInventory(inventoryType, wrinId, outerPackQty, "",looseCountQty);
		// Get the time for the inventory
		String time = physicalInventoryPage.InventoryTime_Label.getText().split("Time: ")[1];
		//Click on Menu Btn
		homePage.Menu_DD_BT.click();
		//Navigate to physical inventory page and click on in progress inventory
		physicalInventoryPage.goToPhysicalInventoryPage().clickOnInProgressInventory(Base.returnTodayDate(), time);
		Thread.sleep(1500);
		// Verify that User should be able to view inventory column headers for in progress inventory
		if (physicalInventoryPage.verifyInventoryTableHeadersDisplayed()) {
			Reporter.reportPassResult(
					browser,"sprint2_US8_TC1824",
					"User should be able to view inventory column headers for in progress inventory",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint2_US8_TC1824","sprint2_US8_TC1824",
					"User should be able to view inventory column headers for in progress inventory",
					"Fail");
			AbstractTest.takeSnapShot("sprint2_US8_TC1824");

		}
	}
	
	//TC1825: Verify that the user should be able to view 8-digit WRIN numbers and Descriptions for each Raw Item in the WRIN and Description columns respectively.
	@Test()
	public void sprint2_US8_TC1825() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PhysicalInventoryPage physicalInventoryPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet physicalInventoryPageSheet = ReadTestData.getTestDataSheet("sprint2_US8_TC1825", "Object1");
		String wrinId = ReadTestData.getTestData(physicalInventoryPageSheet, "WRINId");
		String outerPackQty = ReadTestData.getTestData(physicalInventoryPageSheet, "OuterPackQty");
		String looseCountQty = ReadTestData.getTestData(physicalInventoryPageSheet, "LooseUnitsQty");
		String inventoryType = ReadTestData.getTestData(physicalInventoryPageSheet, "ListType");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Physical Inventory page
		physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPhysicalInventoryPage()
				.saveANewInventory(inventoryType,wrinId, outerPackQty,"", looseCountQty);
		//Get the time for the inventory
		String time = physicalInventoryPage.InventoryTime_Label.getText().split("Time: ")[1];
		//Click on Menu Btn
		homePage.Menu_DD_BT.click();
		//Navigate to physical inventory page and click on in progress inventory
		physicalInventoryPage.goToPhysicalInventoryPage().clickOnInProgressInventory(Base.returnTodayDate(), time);
		Thread.sleep(1500);
		// Verify that 8-digit WRIN number in WRIN column and description in Descriptions column for each Raw Item should be displayed
		if (physicalInventoryPage.verifyWrinNumberAndDescriptionIsDisplayedForEachRawItem()) {
			Reporter.reportPassResult(
					browser,"sprint2_US8_TC1825",
					"User should be able to view the 8-digit WRIN number in WRIN column and description in Descriptions column for each Raw Item",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint2_US8_TC1825","sprint2_US8_TC1825",
					"User should be able to view the 8-digit WRIN number in WRIN column and description in Descriptions column for each Raw Item",
					"Fail");
			AbstractTest.takeSnapShot("sprint2_US8_TC1825");

		}
	}

}
