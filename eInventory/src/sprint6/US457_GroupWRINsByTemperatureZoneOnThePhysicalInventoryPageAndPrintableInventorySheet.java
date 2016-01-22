package sprint6;

import java.io.IOException;
import java.util.ArrayList;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import sprint2.AbstractTest;

import common.Base;
import common.GlobalVariable;
import common.Reporter;

import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.PhysicalInventoryPage;

public class US457_GroupWRINsByTemperatureZoneOnThePhysicalInventoryPageAndPrintableInventorySheet extends AbstractTest
{
	//Verify that Raw Item Temperature Zone groupings are present when the printed sheet is generated from physical inventory landing page
	//Need to update
	@Test()
	
	public void Sprint6_US457_TC1228() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	{
		/*Start-Variable Deceleration*/
		String storeId=GlobalVariable.StoreId;
		String frozenWrinId=GlobalVariable.frozenWRIN;
		String refrigeratedId=GlobalVariable.RefrigeratedWRIN;
		String dryWrinId=GlobalVariable.DryWRIN;
		String userId = GlobalVariable.userId;
		/*End-Variable Deceleration*/
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		PhysicalInventoryPage physicalInventoryPage=PageFactory.initElements(driver, PhysicalInventoryPage.class);
		homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPhysicalInventoryPage();
		Select select =new Select(physicalInventoryPage.SelectABlankInventoryToPrint_DD);
		select.selectByVisibleText("Monthly");
	    Thread.sleep(10000);
		// Switch to new window opened
		 ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		 System.out.println(tabs2.size());
		 driver.switchTo().window(tabs2.get(1));
		 Thread.sleep(5000);
		 if(driver.findElements(By.xpath("//div[text()='Frozen']")).size()==1 && driver.findElements(By.xpath("//div[text()='Refrigerated']/following-sibling::div[text()='Dry']")).size()==1
				 && driver.findElements(By.xpath("//div[text()='Frozen']/following-sibling::div[text()='Refrigerated']")).size()==1)
		 {
				Reporter.reportPassResult(browser, "Sprint6_US457_TC1228", "Frozon should be on top and Refrigerated uder the frozon and Dry should be under Refrigerated", "Pass");

		 }
		 else
		 {
			 	Reporter.reportTestFailure(browser, "Sprint6_US457_TC1228_Condition1", "Sprint6_US457_TC1228", "Frozon should be on top and Refrigerated uder the frozon and Dry should be under Refrigerated", "Fail");
				AbstractTest.takeSnapShot("Sprint6_US457_TC1228_Condition1");
		 }
		 
		 int size=driver.findElements(By.xpath("//div[text()='Refrigerated']/preceding-sibling::div[text()!='Frozen']")).size();
		 //Code for verifying frozen item
		 for(int i=1;i<=size;i++)
		 {
			 
			 String frozenWrin=driver.findElement(By.xpath("//div[text()='Refrigerated']/preceding-sibling::div["+i+"][text()!='Frozen']/div[1]")).getText();
			 if(frozenWrin.equalsIgnoreCase(frozenWrinId))
			 {
					Reporter.reportPassResult(browser, "Sprint6_US457_TC1228", "Item of frozen group shoudl be in the Frozen group", "Pass");
					break;
			 }
			 else if (i==size)
			 {
				 Reporter.reportTestFailure(browser, "Sprint6_US457_TC1228_Condition2", "Sprint6_US457_TC1228", "Item of frozen group shoudl be in the Frozen group", "Fail");
				 AbstractTest.takeSnapShot("Sprint6_US457_TC1228_Condition2");
			 }
			 else
			 {
				 continue;
			 }
			 
		 }
		 
		//Code for verifying Refrigerated item
		 int size1=driver.findElements(By.xpath("//div[following-sibling::div[text()='Dry'] and preceding-sibling::div[text()='Refrigerated']]")).size();

		 for(int i=1;i<=size1;i++)
		 {
			 
			 String RefrigeratedWrin=driver.findElement(By.xpath("(//div[following-sibling::div[text()='Dry'] and preceding-sibling::div[text()='Refrigerated']]/div[1])["+i+"]")).getText();
			 if(RefrigeratedWrin.equalsIgnoreCase(refrigeratedId))
			 {
				 Reporter.reportPassResult(browser, "Sprint6_US457_TC1228", "Item of Refrigerated group shoudl be in the Refrigerated group", "Pass");
					break;
			 }
			 else if (i==size1)
			 {
				 Reporter.reportTestFailure(browser, "Sprint6_US457_TC1228_Condition3", "Sprint6_US457_TC1228", "Item of Refrigerated group shoudl be in the Refrigerated group", "Fail");
				 AbstractTest.takeSnapShot("Sprint6_US457_TC1228_Condition3");
			 }
			 else
			 {
				 continue;
			 }
			 
		 }
		 
		 
			//Code for verifying Dry item
		 int size2=driver.findElements(By.xpath("//div[following-sibling::div[text()!='Dry'] and preceding-sibling::div[text()='Dry']]")).size();

		 for(int i=1;i<=size2;i++)
		 {
			 
			 String DryWrin=driver.findElement(By.xpath("(//div[following-sibling::div[text()!='Dry'] and preceding-sibling::div[text()='Dry']]/div[1])["+i+"]")).getText();
			 if(DryWrin.equalsIgnoreCase(dryWrinId))
			 {
				 Reporter.reportPassResult(browser, "Sprint6_US457_TC1228", "Item of Dry group shoudl be in the Dry group", "Pass");
					break;
			 }
			 else if (i==size2)
			 {
				 Reporter.reportTestFailure(browser, "Sprint6_US457_TC1228_Condition4", "Sprint6_US457_TC1228", "Item of Dry group shoudl be in the Dry group", "Fail");
				 AbstractTest.takeSnapShot("Sprint6_US457_TC1228_Condition4");
			 }
			 else
			 {
				 continue;
			 }
			 
		 }
	 

	}
	
	//Verify all the raw items present in physical inventory are visible for each Temperature Zone..

		@Test()

		public void Sprint6_US457_TC1226() throws RowsExceededException, BiffException, WriteException, IOException, InterruptedException
		{

			/*Start-Variable Deceleration*/
			String storeId=GlobalVariable.StoreId;
			String frozenWrinId=GlobalVariable.frozenWRIN;
			String refrigeratedId=GlobalVariable.RefrigeratedWRIN;
			String dryWrinId=GlobalVariable.DryWRIN;
			String userId = GlobalVariable.userId;
			PhysicalInventoryPage physicalInventoryPage;
			String inventoryType = "Monthly";
			/*End-Variable Deceleration*/
			//Create instance of home page
			HomePage homePage=PageFactory.initElements(driver, HomePage.class);
			// Navigate to physical inventory Page >> Save a physical inventory
			physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement()
					.goToPhysicalInventoryPage().saveANewInventory(inventoryType);
			// Get the time for the inventory
			String time = physicalInventoryPage.InventoryTime_Label.getText().split("Time: ")[1];
			//Click on Menu Btn
			homePage.Menu_DD_BT.click();
			// Navigate to physical Inventory and click on the saved inventory with status "In-Progress".
			physicalInventoryPage.goToPhysicalInventoryPage().clickOnInProgressInventory(Base.returnTodayDate(), time);
			//validate frozen is on top and wRINIDFrozen is under it also Refrigerated is under Frozen and wRINIDRefrigerated is under it & Dry is under
			//Refrigerated and wRINIDDry is under it..
			for(int i=1;i<2;i++)
			{
				if(driver.findElement(By.xpath("//tr[@class='tableGroupHeadings']["+i+"]/td")).getText().equalsIgnoreCase("Frozen")
						&& driver.findElement(By.xpath("//tr[@class='tableGroupHeadings']["+i+"+1]/td")).getText().equalsIgnoreCase("Refrigerated") 	
						&& driver.findElement(By.xpath("//tr[@class='tableGroupHeadings']["+i+"+2]/td")).getText().equalsIgnoreCase("Dry"))
				{
					Reporter.reportPassResult(browser, "Sprint6_US457_TC1226", "Frozen field must be @ top followed by refrigerated followed by dry", "Pass");
				}
				else
				{
					Reporter.reportTestFailure(browser, "Sprint6_US457_TC1226_Condition1", "Sprint6_US457_TC1226", "Frozen field must be @ top followed by refrigerated followed by dry", "Fail");
					AbstractTest.takeSnapShot("Sprint6_US457_TC1226_Condition1");

				}	
			}
			//Search the Frozon WRIN ID
			physicalInventoryPage.CreateInventory_Search_TB.sendKeys(frozenWrinId);
			Thread.sleep(2000);
			int size1=driver.findElements(By.xpath("//table[@id='inventory_table']/tbody/tr")).size();
			boolean result1=driver.findElement(By.xpath("//table[@id='inventory_table']/tbody/tr[1]/td/span")).getText().trim().equalsIgnoreCase("Frozen");
			//Search the Refrigerator WRIN
			physicalInventoryPage.CreateInventory_Search_TB.clear();
			physicalInventoryPage.CreateInventory_Search_TB.sendKeys(refrigeratedId);
			Thread.sleep(2000);
			int size2=driver.findElements(By.xpath("//table[@id='inventory_table']/tbody/tr")).size();
			boolean result2=driver.findElement(By.xpath("//table[@id='inventory_table']/tbody/tr[1]/td/span")).getText().trim().equalsIgnoreCase("Refrigerated");
			//Search the dry WRIN
			physicalInventoryPage.CreateInventory_Search_TB.clear();
			physicalInventoryPage.CreateInventory_Search_TB.sendKeys(dryWrinId);
			Thread.sleep(2000);
			int size3=driver.findElements(By.xpath("//table[@id='inventory_table']/tbody/tr")).size();
			boolean result3=driver.findElement(By.xpath("//table[@id='inventory_table']/tbody/tr[1]/td/span")).getText().trim().equalsIgnoreCase("Dry");
			if(size1==2 && size2==2 && size3==2 && result1 && result2 && result3)
			{
				Reporter.reportPassResult(browser, "Sprint6_US457_TC1226", "wRINIDFrozen should be under 'Frozen',wRINIDRefrigerated should be under 'Refrigerated' & wRINIDDry should be under 'Dry Item'", "Pass");
			}
			else
			{
				Reporter.reportTestFailure(browser, "Sprint6_US457_TC1226_Condition2", "Sprint6_US457_TC1226", "wRINIDFrozen should be under 'Frozen',wRINIDRefrigerated should be under 'Refrigerated' & wRINIDDry should be under 'Dry Item'", "Fail");
				AbstractTest.takeSnapShot("Sprint6_US457_TC1226_Condition2");

			}	

		}
}
