package sprint2;

import java.io.IOException;

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
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.PhysicalInventoryPage;

public class US63_RawItemUOMLookupTable extends AbstractTest {

	// TC1975: Verify that the user is able to view full description UOM in the Loose Units column of physical inventory.
	@Test()
	public void sprint2_US63_TC1975() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PhysicalInventoryPage physicalInventoryPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet physicalInventoryPageSheetObj1 = ReadTestData.getTestDataSheet("sprint2_US63_TC1975", "Object1");
		String wrinId1 = ReadTestData.getTestData(physicalInventoryPageSheetObj1,"WRINId");
		String inventoryType = ReadTestData.getTestData(physicalInventoryPageSheetObj1, "ListType");
		String uomCode1 = ReadTestData.getTestData(physicalInventoryPageSheetObj1, "UOMCode");
		HSSFSheet physicalInventoryPageSheetObj2 = ReadTestData.getTestDataSheet("sprint2_US63_TC1975", "Object2");
		String wrinId2 = ReadTestData.getTestData(physicalInventoryPageSheetObj2,"WRINId");
		String uomCode2 = ReadTestData.getTestData(physicalInventoryPageSheetObj2, "UOMCode");
		HSSFSheet physicalInventoryPageSheetObj3 = ReadTestData.getTestDataSheet("sprint2_US63_TC1975", "Object3");
		String wrinId3 = ReadTestData.getTestData(physicalInventoryPageSheetObj3,"WRINId");
		String uomCode3 = ReadTestData.getTestData(physicalInventoryPageSheetObj3, "UOMCode");
		HSSFSheet physicalInventoryPageSheetObj4 = ReadTestData.getTestDataSheet("sprint2_US63_TC1975", "Object4");
		String wrinId4 = ReadTestData.getTestData(physicalInventoryPageSheetObj4,"WRINId");
		String uomCode4 = ReadTestData.getTestData(physicalInventoryPageSheetObj4, "UOMCode");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to physical inventory Page >> Save a physical inventory
		physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement()
				.goToPhysicalInventoryPage().saveANewInventory(inventoryType);
		// Get the time for the inventory
		String time = physicalInventoryPage.InventoryTime_Label.getText().split("Time: ")[1];
		// Click on Menu Btn
		homePage.Menu_DD_BT.click();
		// Navigate to physical Inventory and click on the saved inventory with status "In-Progress"
		physicalInventoryPage.goToPhysicalInventoryPage().clickOnInProgressInventory(Base.returnTodayDate(), time);
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.SearchItem_TB));
		//Search for raw item 1 and verify the loose unit code with the UOM code
		physicalInventoryPage.SearchItem_TB.clear();
		physicalInventoryPage.SearchItem_TB.sendKeys(wrinId1);
		boolean looseUnitCodeDisplayed = physicalInventoryPage.LooseCountQty_TB.getAttribute("placeholder").equals(uomCode1);
		//Search for raw item 2 and verify the loose unit code with the UOM code
		physicalInventoryPage.SearchItem_TB.clear();
		physicalInventoryPage.SearchItem_TB.sendKeys(wrinId2);
		looseUnitCodeDisplayed = looseUnitCodeDisplayed && physicalInventoryPage.LooseCountQty_TB.getAttribute("placeholder").equals(uomCode2);
		//Search for raw item 3 and verify the loose unit code with the UOM code
		physicalInventoryPage.SearchItem_TB.clear();
		physicalInventoryPage.SearchItem_TB.sendKeys(wrinId3);
		looseUnitCodeDisplayed = looseUnitCodeDisplayed && physicalInventoryPage.LooseCountQty_TB.getAttribute("placeholder").equals(uomCode3);
		//Search for raw item 4 and verify the loose unit code with the UOM code
		physicalInventoryPage.SearchItem_TB.clear();
		physicalInventoryPage.SearchItem_TB.sendKeys(wrinId4);
		looseUnitCodeDisplayed = looseUnitCodeDisplayed && physicalInventoryPage.LooseCountQty_TB.getAttribute("placeholder").equals(uomCode4);
		// Verify that the user is able to view full description UOM in the Loose Units column of physical inventory.
		if (looseUnitCodeDisplayed) {
			Reporter.reportPassResult(
					browser,"sprint2_US63_TC1975",
					"User should be able to view correct UOM code for loose units",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint2_US63_TC1975","sprint2_US63_TC1975",
					"User should be able to view correct UOM code for loose units",
					"Fail");
			AbstractTest.takeSnapShot("sprint2_US63_TC1975");
		}
	}

}
