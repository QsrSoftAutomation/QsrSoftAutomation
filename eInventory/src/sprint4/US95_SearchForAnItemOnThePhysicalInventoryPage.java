package sprint4;

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
import sprint2.AbstractTest;

public class US95_SearchForAnItemOnThePhysicalInventoryPage extends AbstractTest
{
	//TC2271 Verify,"The user will search on the WRIN column and Description column only on the Physical Inventory page".
	
	@Test()
	public void sprint4_US95_TC2271() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException 
	{
		/** Variable Section : **/
		PhysicalInventoryPage physicalInventoryPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet physicalInventoryPageSheet = ReadTestData.getTestDataSheet("sprint4_US95_TC2271", "Object1");
		String wrinId = ReadTestData.getTestData(physicalInventoryPageSheet,"WRINId");
		String description = ReadTestData.getTestData(physicalInventoryPageSheet,"Description");
		
		/*End*/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		//navigate to physical inventory page
		physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement()
				.goToPhysicalInventoryPage();
		//Edit or go to create new inventory page
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.StartNewInventory_BT));
		physicalInventoryPage.StartNewInventory_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.StartInventory_BT));
		physicalInventoryPage.StartInventory_BT.click();
		try {
			physicalInventoryPage.selectInventoryType("Monthly");
			
		} catch (Exception e) 
		{
			physicalInventoryPage.EditInventory_BT.click();
			if (Base.isElementDisplayed(physicalInventoryPage.Save_BT)) 
			{
				physicalInventoryPage.selectInventoryType("Monthly");
				Thread.sleep(15000);
			}
		}
		//Search the record based on WRIN id
		physicalInventoryPage.SearchItem_TB.sendKeys(wrinId);
		Thread.sleep(2000);
		int size1=driver.findElements(By.xpath("//table[@id='inventory_table']/tbody/tr")).size();
		//Search the record based on description
		physicalInventoryPage.SearchItem_TB.clear();
		physicalInventoryPage.SearchItem_TB.sendKeys(description);
		Thread.sleep(2000);
		int size2=driver.findElements(By.xpath("//table[@id='inventory_table']/tbody/tr")).size();
		if(size1==2 && size2==2)
		{
			Reporter.reportPassResult(
					browser,"sprint4_US95_TC2271",
					"User should be able to search the records based on WRIN Id and description",
					"Pass");
		}
		else
		{
			Reporter.reportTestFailure(
					browser,"sprint4_US95_TC2271","sprint4_US95_TC2271",
					"User should be able to search the records based on WRIN Id and description",
					"Fail");
			AbstractTest.takeSnapShot("sprint4_US95_TC2271");
		}

	}
	
//TC2272 Verify, "The list will show the items that fit the search criteria on physical inventory page".
	
	
	@Test()
	public void sprint4_US95_TC2272() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException 
	{
		/** Variable Section : **/
		PhysicalInventoryPage physicalInventoryPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet physicalInventoryPageSheet = ReadTestData.getTestDataSheet("sprint4_US95_TC2272", "Object1");
		String wrinId = ReadTestData.getTestData(physicalInventoryPageSheet,"WRINId");
		String description = ReadTestData.getTestData(physicalInventoryPageSheet,"Description");
		
		/*End*/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		//navigate to physical inventory page
		physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement()
				.goToPhysicalInventoryPage();
		//Edit or go to create new inventory page
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.StartNewInventory_BT));
		physicalInventoryPage.StartNewInventory_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.StartInventory_BT));
		physicalInventoryPage.StartInventory_BT.click();
		try {
			physicalInventoryPage.selectInventoryType("Monthly");
			
		} catch (Exception e) 
		{
			physicalInventoryPage.EditInventory_BT.click();
			if (Base.isElementDisplayed(physicalInventoryPage.Save_BT)) 
			{
				physicalInventoryPage.selectInventoryType("Monthly");
				Thread.sleep(15000);
			}
		}
		//Search the record based on WRIN id
		physicalInventoryPage.SearchItem_TB.sendKeys(wrinId);
		Thread.sleep(2000);
		int size1=driver.findElements(By.xpath("//table[@id='inventory_table']/tbody/tr")).size();
		//Search the record based on description
		physicalInventoryPage.SearchItem_TB.clear();
		physicalInventoryPage.SearchItem_TB.sendKeys(description);
		Thread.sleep(2000);
		int size2=driver.findElements(By.xpath("//table[@id='inventory_table']/tbody/tr")).size();
		if(size1==2 && size2==2)
		{
			Reporter.reportPassResult(
					browser,"sprint4_US95_TC2272",
					"User should be able to search the records based on WRIN Id and description",
					"Pass");
		}
		else
		{
			Reporter.reportTestFailure(
					browser,"sprint4_US95_TC2272","sprint4_US95_TC2272",
					"User should be able to search the records based on WRIN Id and description",
					"Fail");
			AbstractTest.takeSnapShot("sprint4_US95_TC2272");
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
