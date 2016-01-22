package sprint2;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import common.GlobalVariable;
import common.ReadTestData;
import common.Reporter;
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.PhysicalInventoryPage;

public class US58_RawItemInnerPackNameInnerPackAmount extends AbstractTest{
	
	//TC2000:Verify the INNER_PK_QTY divided by the UOM Conversion matches the INNER PACK Quantity.
	@Test()
	public void sprint2_US58_TC2000() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PhysicalInventoryPage physicalInventoryPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet physicalInventoryPageSheet = ReadTestData.getTestDataSheet("sprint2_US58_TC2000", "Object1");
		String wrinId = ReadTestData.getTestData(physicalInventoryPageSheet,"WRINId");
		String innerPackQty = ReadTestData.getTestData(physicalInventoryPageSheet, "InnerPackQty");
		String innerPack_Qty = ReadTestData.getTestData(physicalInventoryPageSheet, "INNERPACK_QTY");
		String uomConversionFactor = ReadTestData.getTestData(physicalInventoryPageSheet, "UOM_CONVERSION_FACTOR");
		String inventoryType = ReadTestData.getTestData(physicalInventoryPageSheet, "ListType");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to physical inventory Page >> Save a physical inventory with only inner pack quantity as 1
		physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement()
				.goToPhysicalInventoryPage().saveANewInventory(inventoryType, wrinId, "", innerPackQty, "");
		//get expected quantities as innerPack_Qty/uomConversionFactor
		int expectedUnits = Integer.parseInt(innerPack_Qty)/Integer.parseInt(uomConversionFactor);
		//Get total units for raw item from physical inventory page
		String totalUnits = physicalInventoryPage.getTotalUnitsForARawItem(wrinId).split(" ")[0];
		//Verify that total units should be innerPack_Qty/uomConversionFactor
		if (totalUnits.equals(String.valueOf(expectedUnits))) {
			Reporter.reportPassResult(
					browser,"sprint2_US58_TC2000",
					"User should be able to view the inner pack quantity value as (X/Y)",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint2_US58_TC2000","sprint2_US58_TC2000",
					"User should be able to view the inner pack quantity value as (X/Y)",
					"Fail");
			AbstractTest.takeSnapShot("sprint2_US58_TC2000");

		}
	}
	
	//TC2001:Verify ALTERNATE_INNERPACK_DESC matches the INNER_PACK Description on the Physical Inventory Screen
	@Test()
	public void sprint2_US58_TC2001() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PhysicalInventoryPage physicalInventoryPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet physicalInventoryPageSheet = ReadTestData.getTestDataSheet("sprint2_US58_TC2001", "Object1");
		String wrinId = ReadTestData.getTestData(physicalInventoryPageSheet,"WRINId");
		String alternateInnerPackDesc = ReadTestData.getTestData(physicalInventoryPageSheet, "ALTERNATE_INNERPACK_DESC");
		String inventoryType = ReadTestData.getTestData(physicalInventoryPageSheet, "ListType");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to physical inventory Page >> Save a physical inventory
		physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement()
				.goToPhysicalInventoryPage().saveANewInventory(inventoryType, wrinId, "", "", "");
		//Verify that ALTERNATE_INNERPACK_DESC should be displayed as INNER_PACK Description for the raw item 
		if (physicalInventoryPage.InnerPackQty_TB.getAttribute("placeholder").equals(alternateInnerPackDesc)) {
			Reporter.reportPassResult(
					browser,"sprint2_US58_TC2001",
					"User should be able to view ALTERNATE_INNERPACK_DESC as the INNER_PACK Description on the Physical Inventory Screen",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint2_US58_TC2001","sprint2_US58_TC2001",
					"User should be able to view ALTERNATE_INNERPACK_DESC as the INNER_PACK Description on the Physical Inventory Screen",
					"Fail");
			AbstractTest.takeSnapShot("sprint2_US58_TC2001");
		}
	}
}
