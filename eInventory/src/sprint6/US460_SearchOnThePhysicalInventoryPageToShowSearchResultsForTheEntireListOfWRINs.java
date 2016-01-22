package sprint6;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import common.GlobalVariable;
import common.ReadTestData;
import common.Reporter;
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.PhysicalInventoryPage;
import sprint2.AbstractTest;

public class US460_SearchOnThePhysicalInventoryPageToShowSearchResultsForTheEntireListOfWRINs extends AbstractTest {

	//Search on the Physical Inventory page to show search results for the entire list of WRINs

	@Test ()
	public void Sprint6_US460_TC1252() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	{
		String userId = GlobalVariable.userId;
		String storeId=GlobalVariable.StoreId;
		//Create instance of home page
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		//Navigate to physical Inventory Page
		homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPhysicalInventoryPage();
		//Select a existing Inventory
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='inventory_selection_table']/tbody/tr")));
		driver.findElements(By.xpath("//table[@id='inventory_selection_table']/tbody/tr/td")).get(0).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='search']")));
		//validate Search option is present 
		if(driver.findElements(By.xpath("//input[@type='search']")).size()==1)
		{
			Reporter.reportPassResult(browser, "Sprint6_US460_TC1252", "search field should be displayed", "Pass");
		}
		else
		{
			Reporter.reportTestFailure(browser, "Sprint6_US460_TC1252", "Sprint6_US460_TC1252", "search field should be displayed", "Fail");
			AbstractTest.takeSnapShot("Sprint6_US460_TC1252");

		}	
	}

	//Verify the user is able to search WRIN ID , enter quantities for the searched WRIN ID and validate the "Add" button to include the raw items to current physical inventory on "Physical Inventory" detail page

	@Test 

	public void Sprint6_US460_TC1253() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	{
		/* Start-Fetch the data From Test Data Sheet*/
		HSSFSheet physicalInventoryPageSheet = ReadTestData.getTestDataSheet("Sprint6_US460_TC1253", "Object1");
		String wrinId = ReadTestData.getTestData(physicalInventoryPageSheet, "WRINId");
		String userId = GlobalVariable.userId;
		String storeId=GlobalVariable.StoreId;
		/*End*/
		//Create instance of home page
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		//Navigate to physical Inventory Page
		homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPhysicalInventoryPage();
		//create a new Inventory
		PhysicalInventoryPage physicalInventoryPage=PageFactory.initElements(driver, PhysicalInventoryPage.class);
		physicalInventoryPage.StartNewInventory_BT.click();
		Thread.sleep(2000);
		physicalInventoryPage.StartInventory_BT.click();
		Thread.sleep(2500);
		if(driver.findElements(By.xpath("//input[@id='start_edit_inventory']")).size()==1)
		{
			physicalInventoryPage.EditInventory_BT.click();
		}
		driver.findElement(By.xpath("//input[@class='case_count fiveDotTwoI ebos-input form-control']")).clear();
		driver.findElement(By.xpath("//input[@class='case_count fiveDotTwoI ebos-input form-control']")).sendKeys("2");
		//Find the number of records before adding the item
		int beforeadd=driver.findElements(By.xpath(".//*[@id='inventory_table']/tbody/tr[@role='row']")).size();
		System.out.println("before"+beforeadd);
		//click on 'Add' button
		physicalInventoryPage.AddItem_BT.click();
		physicalInventoryPage.AddInventory_Search_TB.click();
		Thread.sleep(1500);
		physicalInventoryPage.AddInventory_Search_TB.sendKeys(wrinId);
		action.sendKeys(Keys.SPACE).build().perform();
		Thread.sleep(1500);
		action.sendKeys(Keys.BACK_SPACE).build().perform();
//		String AddedItem=driver.findElement(By.xpath("//div[@class='autocomplete-suggestion autocomplete-selected' and @data-index='0']")).getText();
		//select first item and add it
		driver.findElement(By.xpath("//div[@class='autocomplete-suggestion autocomplete-selected' and @data-index='0']")).click();
		physicalInventoryPage.AddNewItem_BT.click();
		//click on save button.
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.Save_BT));
		Thread.sleep(3000);
		physicalInventoryPage.Save_BT.click();
		Thread.sleep(8000);
		// Find the number of records after add
		int afteradd=driver.findElements(By.xpath(".//*[@id='inventory_table']/tbody/tr[@role='row']")).size();
		System.out.println("after"+afteradd);
	/*	//click on Existing Inventory..
		homePage.selectAStore("762").goToPhysicalInventoryPage();
		//Select a existing Inventory of monthly list having two raw items for similar wRIN prefix '0033'
		driver.findElement(By.xpath("//table[@id='inventory_selection_table']/tbody/tr/td[2][contains(text(),'16:15')]")).click();*/
		if(afteradd==beforeadd+1)
		{
			Reporter.reportPassResult(browser, "Sprint6_US460_TC1253", "Item should be added to existing Inventory", "Pass");
		}
		else 
		{
			Reporter.reportTestFailure(browser, "Sprint6_US460_TC1253", "Sprint6_US460_TC1253", "Item should be added to existing Inventory", "Fail");
			AbstractTest.takeSnapShot("Sprint6_US460_TC1253");

		}	
	}
}
