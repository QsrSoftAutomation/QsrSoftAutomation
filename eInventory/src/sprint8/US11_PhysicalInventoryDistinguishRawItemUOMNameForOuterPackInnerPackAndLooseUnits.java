package sprint8;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import common.Base;
import common.GlobalVariable;
import common.ReadTestData;
import common.Reporter;
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.PhysicalInventoryPage;
import eInventoryPageClasses.RawItemInformationPage;
import sprint2.AbstractTest;

public class US11_PhysicalInventoryDistinguishRawItemUOMNameForOuterPackInnerPackAndLooseUnits extends AbstractTest

{
	//TC32 Verify that  User is able to view the "UOM" of "Outer Pack" on the Physical Inventory screen.
	@Test()
	public void Sprint8_US11_TC32() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/* Start-Variable Deceleration */
		HSSFSheet rawItemInfoSheet = ReadTestData.getTestDataSheet("Sprint8_US11_TC32", "Object1");
		String wrinId = ReadTestData.getTestData(rawItemInfoSheet,"WRINId");
		HSSFSheet physicalInventoryPageSheet = ReadTestData.getTestDataSheet("Sprint8_US11_TC32", "Object2");
		String inventoryType = ReadTestData.getTestData(physicalInventoryPageSheet, "ListType");
		//String wrinId = ReadTestData.getTestData(physicalInventoryPageSheet, "WRINId");
		String outerPack = ReadTestData.getTestData(physicalInventoryPageSheet,"OuterPackQty");
		String innerPack = ReadTestData.getTestData(physicalInventoryPageSheet,"InnerPackQty");
		String looseUnit = ReadTestData.getTestData(physicalInventoryPageSheet,"LooseUnitsQty");
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		PhysicalInventoryPage physicalInventoryPage;
		String actUomCaseValue;
		/* End */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Go to Physical Inventory Page
		physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPhysicalInventoryPage();
		physicalInventoryPage.saveAndPostANewInventory(inventoryType, wrinId, outerPack, innerPack, looseUnit);
		// Get the time for the inventory
		String time = physicalInventoryPage.InventoryTime_Label.getText().split("Time: ")[1];
		//Click on Menu Btn
		homePage.Menu_DD_BT.click();
		// Navigate to physical Inventory and click on the saved inventory with status "In-Progress".
		physicalInventoryPage.goToPhysicalInventoryPage().clickOnPostedInventory(Base.returnTodayDate(), time);
		// Search the wrin Id
		physicalInventoryPage.ViewInventory_Search_TB.sendKeys(wrinId);
		Thread.sleep(3000);
		actUomCaseValue = driver.findElement(By.xpath("//table[@id='inventory_table']/tbody/tr[2]/td[3]/span")).getText().trim();
		if (actUomCaseValue.equalsIgnoreCase("Case")) {
			Reporter.reportPassResult(browser, "Sprint8_US11_TC32",
					"Correct UOM case value should display", "Pass");
		} else {
			Reporter.reportTestFailure(browser, "Sprint8_US11_TC32",
					"Sprint8_US11_TC32",
					"Correct UOM case value should display", "Fail");
			AbstractTest.takeSnapShot("Sprint8_US11_TC32");
		}
	}
	
	// TC33 Verify that User is able to view the "UOM" of "Inner Pack" on the Physical Inventory screen.
	@Test()
	public void Sprint8_US11_TC33() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/* Start-Variable Deceleration */
		HSSFSheet rawItemInfoSheet = ReadTestData.getTestDataSheet("Sprint8_US11_TC33", "Object1");
		String wrinId = ReadTestData.getTestData(rawItemInfoSheet, "WRINId");
		HSSFSheet physicalInventoryPageSheet = ReadTestData.getTestDataSheet("Sprint8_US11_TC33", "Object2");
		String inventoryType = ReadTestData.getTestData(physicalInventoryPageSheet, "ListType");
		String outerPack = ReadTestData.getTestData(physicalInventoryPageSheet,"OuterPackQty");
		String innerPack = ReadTestData.getTestData(physicalInventoryPageSheet,"InnerPackQty");
		String looseUnit = ReadTestData.getTestData(physicalInventoryPageSheet,"LooseUnitsQty");
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		PhysicalInventoryPage physicalInventoryPage;
		RawItemInformationPage rawItemInformationPage;
		String expinnerPackValue;
		String actinnerPackValue;
		/* End */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Go to Raw Item Information Page
		rawItemInformationPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement()
				.goToRawItemInformationPage().searchAndSelectWRINID(wrinId);
		wait.until(ExpectedConditions.visibilityOf(rawItemInformationPage.RawItemInformation_Attribute_CasePrice_Label));
		// Get the value of UOM field of Inner pack
		expinnerPackValue = rawItemInformationPage.RawItemInformation_Attribute_InnerPackDescription_Value.getText().trim();
		// Click on Menu Btn
		homePage.Menu_DD_BT.click();
		wait.until(ExpectedConditions.visibilityOf(homePage.Menu_Back_BT));
		homePage.Menu_Back_BT.click();
		Thread.sleep(1500);
		// Go to Physical Inventory Page
		physicalInventoryPage = rawItemInformationPage.goToPhysicalInventoryPage();
		physicalInventoryPage.saveAndPostANewInventory(inventoryType, wrinId,outerPack, innerPack, looseUnit);
		// Get the time for the inventory
		String time = physicalInventoryPage.InventoryTime_Label.getText().split("Time: ")[1];
		// Click on Menu Btn
		homePage.Menu_DD_BT.click();
		Thread.sleep(1500);
		// Navigate to physical Inventory and click on the saved inventory with status "In-Progress".
		physicalInventoryPage.goToPhysicalInventoryPage().clickOnPostedInventory(Base.returnTodayDate(), time);
		// Search the wrin Id
		physicalInventoryPage.ViewInventory_Search_TB.sendKeys(wrinId);
		Thread.sleep(3000);
		actinnerPackValue = driver.findElement(By.xpath("//table[@id='inventory_table']/tbody/tr[2]/td[4]/span")).getText().trim();
		if (expinnerPackValue.equalsIgnoreCase(actinnerPackValue)) {
			Reporter.reportPassResult(browser, "Sprint8_US11_TC33",
					"Correct Inner Pack value should display", "Pass");
		} else {
			Reporter.reportTestFailure(browser, "Sprint8_US11_TC33",
					"Sprint8_US11_TC33",
					"Correct Inner pack value should display", "Fail");
			AbstractTest.takeSnapShot("Sprint8_US11_TC33");
		}
	}

	// TC34 Verify that User is able to view the "UOM" of "Loose Units" on the Physical Inventory screen.
	@Test()
	public void Sprint8_US11_TC34() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/* Start-Variable Deceleration */
		HSSFSheet rawItemInfoSheet = ReadTestData.getTestDataSheet("Sprint8_US11_TC34", "Object1");
		String wrinId = ReadTestData.getTestData(rawItemInfoSheet, "WRINId");
		HSSFSheet physicalInventoryPageSheet = ReadTestData.getTestDataSheet("Sprint8_US11_TC34", "Object2");
		String inventoryType = ReadTestData.getTestData(physicalInventoryPageSheet, "ListType");
		String outerPack = ReadTestData.getTestData(physicalInventoryPageSheet,"OuterPackQty");
		String innerPack = ReadTestData.getTestData(physicalInventoryPageSheet,"InnerPackQty");
		String looseUnit = ReadTestData.getTestData(physicalInventoryPageSheet,	"LooseUnitsQty");
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		PhysicalInventoryPage physicalInventoryPage;
		RawItemInformationPage rawItemInformationPage;
		String expUOMValueForLooseUnit;
		String actUOMValueForLooseUnit;
		/* End */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Go to Raw Item Information Page
		rawItemInformationPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement()
				.goToRawItemInformationPage().searchAndSelectWRINID(wrinId);
		wait.until(ExpectedConditions.visibilityOf(rawItemInformationPage.RawItemInformation_Attribute_CasePrice_Label));
		// Get the value of UOM field value
		expUOMValueForLooseUnit = rawItemInformationPage.RawItemInformation_Attribute_UOM_Value.getText().trim();
		// Click on Menu Btn
		homePage.Menu_DD_BT.click();
		wait.until(ExpectedConditions.visibilityOf(homePage.Menu_Back_BT));
		homePage.Menu_Back_BT.click();
		Thread.sleep(1500);
		// Go to Physical Inventory Page
		physicalInventoryPage = rawItemInformationPage.goToPhysicalInventoryPage();
		physicalInventoryPage.saveAndPostANewInventory(inventoryType, wrinId,outerPack, innerPack, looseUnit);
		// Get the time for the inventory
		String time = physicalInventoryPage.InventoryTime_Label.getText().split("Time: ")[1];
		// Click on Menu Btn
		homePage.Menu_DD_BT.click();
		Thread.sleep(1500);
		// Navigate to physical Inventory and click on the saved inventory with
		// status "In-Progress".
		physicalInventoryPage.goToPhysicalInventoryPage().clickOnPostedInventory(Base.returnTodayDate(), time);
		// Search the wrin Id
		physicalInventoryPage.ViewInventory_Search_TB.sendKeys(wrinId);
		Thread.sleep(3000);
		actUOMValueForLooseUnit = driver.findElement(By.xpath("//table[@id='inventory_table']/tbody/tr[2]/td[5]/span")).getText().trim();
		if (expUOMValueForLooseUnit.equalsIgnoreCase(actUOMValueForLooseUnit)) {
			Reporter.reportPassResult(
					browser, "Sprint8_US11_TC34",
					"Correct Loose Units UOM value should display", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "Sprint8_US11_TC34","Sprint8_US11_TC34",
					"Correct Loose Units UOM value should display", "Fail");
			AbstractTest.takeSnapShot("Sprint8_US11_TC34");
		}
	}
}
