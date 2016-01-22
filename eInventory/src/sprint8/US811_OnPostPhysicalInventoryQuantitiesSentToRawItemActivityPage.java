package sprint8;

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
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.PhysicalInventoryPage;
import eInventoryPageClasses.RawItemActivityPage;

public class US811_OnPostPhysicalInventoryQuantitiesSentToRawItemActivityPage  extends AbstractTest{
	
	// TC1537: Verify that when a Physical Inventory is finalized, the quantities counted for applicable raw items will show on the Raw Item Activity page for the date="D" and time="T" selected.
	@Test()
	public void sprint8_US811_TC1537() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/**Variable Section :**/
		PhysicalInventoryPage physicalInventoryPage;
		RawItemActivityPage rawItemActivityPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet physicalInventoryPageSheet = ReadTestData.getTestDataSheet("sprint8_US811_TC1537", "Object1");
		String wrinId = ReadTestData.getTestData(physicalInventoryPageSheet, "WRINId");
		String outerPackQty =ReadTestData.getTestData(physicalInventoryPageSheet, "OuterPackQty");
		String looseCountQty = ReadTestData.getTestData(physicalInventoryPageSheet, "LooseUnitsQty");
		String inventoryType = ReadTestData.getTestData(physicalInventoryPageSheet, "ListType");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		//Navigate to physical inventory page
		physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPhysicalInventoryPage();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.StartNewInventory_BT));
		//Post a new physical inventory
		physicalInventoryPage.saveAndPostANewInventory(inventoryType, wrinId, outerPackQty,"",looseCountQty);
		//Get the time for the inventory
		String time = physicalInventoryPage.InventoryTime_Label.getText().split("Time: ")[1];
		//Click on Menu Btn
		homePage.Menu_DD_BT.click();
		// Navigate to Physical Inventry Page >> Click on posted inventory >> get Unit count for a raw item in Posted inventory
		String unitCountInPhysiclInventoryPage = homePage.goToPhysicalInventoryPage().
				clickOnPostedInventory(Base.returnTodayDate(), time).getTotalUnitForRawItemInInventory(wrinId);
		//Click on Menu Btn
		homePage.Menu_DD_BT.click();
		Thread.sleep(1500);
		// Navigate to Raw Item Activity Page >> Search for the raw item used in physical inventory >> get unit counts for that item
		rawItemActivityPage = homePage.goToRawItemActivityPage();
		String unitCountInRawItemActivityPage = rawItemActivityPage.searchAndSelectWRINID(wrinId).clickOngetItemDetailButton().
				getCountForInventoryEvent(Base.returnTodayDate(), time);
		//verify that quantities counted for applicable raw item of the finalized physical inventory should be displayed on the raw item activity page
		if(unitCountInPhysiclInventoryPage.contains(unitCountInRawItemActivityPage)){
			Reporter.reportPassResult(
					browser,"sprint8_US811_TC1537",
					"User should be able to view the quantities counted for applicable raw item of the finalized physical inventory on the raw item activity page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint8_US811_TC1537","sprint8_US811_TC1537",
					"User should be able to view the quantities counted for applicable raw item of the finalized physical inventory on the raw item activity page",
					"Fail");
			AbstractTest.takeSnapShot("sprint8_US811_TC1537");
		}

	}

	// TC1538: Verify that a finalized Physical Inventory should  show as a highlighted line on the Raw Item Activity page.
	@Test()
	public void sprint8_US811_TC1538() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/**Variable Section :**/
		PhysicalInventoryPage physicalInventoryPage;
		RawItemActivityPage rawItemActivityPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet physicalInventoryPageSheet = ReadTestData.getTestDataSheet("sprint8_US811_TC1538", "Object1");
		String wrinId = ReadTestData.getTestData(physicalInventoryPageSheet, "WRINId");
		String outerPackQty =ReadTestData.getTestData(physicalInventoryPageSheet, "OuterPackQty");
		String looseCountQty = ReadTestData.getTestData(physicalInventoryPageSheet, "LooseUnitsQty");
		String inventoryType = ReadTestData.getTestData(physicalInventoryPageSheet, "ListType");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		//Navigate to physical inventory page
		physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPhysicalInventoryPage();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.StartNewInventory_BT));
		//Post a new physical inventory
		physicalInventoryPage.saveAndPostANewInventory(inventoryType, wrinId, outerPackQty, "",looseCountQty);
		//Get the time for the inventory
		String time = physicalInventoryPage.InventoryTime_Label.getText().split("Time: ")[1];
		//Click on Menu Btn
		homePage.Menu_DD_BT.click();
		Thread.sleep(1500);
		// Navigate to Raw Item Activity Page >> Search for the raw item used in physical inventory and get the style for inventory event
		rawItemActivityPage = homePage.goToRawItemActivityPage();
		String backGroundColor = rawItemActivityPage.searchAndSelectWRINID(wrinId).clickOngetItemDetailButton().
				getStyleForPostedInventoryEvent(Base.returnTodayDate(), time);
		// verify that finalized Physical Inventory event should be highlighted on the Raw Item Activity page
		if (backGroundColor.contains("background-color: yellow")) {
			Reporter.reportPassResult(
					browser,"sprint8_US811_TC1538",
					"User should be able to view the finalized physical inventory as a highlighted line on  Raw Item Activity page.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint8_US811_TC1538","sprint8_US811_TC1538",
					"User should be able to view the finalized physical inventory as a highlighted line on  Raw Item Activity page.",
					"Fail");
			AbstractTest.takeSnapShot("sprint8_US811_TC1538");
		}

	}
	
	// TC1539: Verify that the user can bring up the Finalized Physical Inventory detail from the Raw item Activity page.
	@Test()
	public void sprint8_US811_TC1539() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/**Variable Section :**/
		PhysicalInventoryPage physicalInventoryPage;
		RawItemActivityPage rawItemActivityPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet physicalInventoryPageSheet = ReadTestData.getTestDataSheet("sprint8_US811_TC1539", "Object1");
		String wrinId = ReadTestData.getTestData(physicalInventoryPageSheet, "WRINId");
		String outerPackQty =ReadTestData.getTestData(physicalInventoryPageSheet, "OuterPackQty");
		String looseCountQty = ReadTestData.getTestData(physicalInventoryPageSheet, "LooseUnitsQty");
		String inventoryType = ReadTestData.getTestData(physicalInventoryPageSheet, "ListType");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		//Navigate to physical inventory page
		physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPhysicalInventoryPage();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.StartNewInventory_BT));
		//Post a new physical inventory
		physicalInventoryPage.saveAndPostANewInventory(inventoryType, wrinId, outerPackQty, "",looseCountQty);
		//Get the time for the inventory
		String time = physicalInventoryPage.InventoryTime_Label.getText().split("Time: ")[1];
		//Click on Menu Btn
		homePage.Menu_DD_BT.click();
		Thread.sleep(1500);
		// Navigate to Raw Item Activity Page >> Search for the raw item used in physical inventory >> click on the view details button
		rawItemActivityPage = homePage.goToRawItemActivityPage().searchAndSelectWRINID(wrinId)
				.clickOngetItemDetailButton().clickOnPostedInventoryViewDetailsButton(Base.returnTodayDate(), time);
		//Get date and time from the inventory details pop up
		String dateInRawItemActivityInventoryDetail = rawItemActivityPage.InventoryPopUp_Date_Label.getText();
		String timeInRawItemActivityInventoryDetail = rawItemActivityPage.InventoryPopUp_Time_Label.getText();
		// verify that finalized Physical Inventory details should be displayed in Raw Item Activity page
		if (dateInRawItemActivityInventoryDetail.contains(Base.returnTodayDate()) && timeInRawItemActivityInventoryDetail.contains(time) ) {
			Reporter.reportPassResult(
					browser,"sprint8_US811_TC1539",
					"User should be able to view the Finalized Physical Inventory details from the Raw Item Activity page.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint8_US811_TC1539","sprint8_US811_TC1539",
					"User should be able to view the Finalized Physical Inventory details from the Raw Item Activity page.",
					"Fail");
			AbstractTest.takeSnapShot("sprint8_US811_TC1539");
		}

	}
}
