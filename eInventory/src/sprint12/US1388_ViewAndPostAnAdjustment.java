package sprint12;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import common.Base;
import common.GlobalVariable;
import common.Reporter;
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.PurchasesPage;
import sprint2.AbstractTest;

public class US1388_ViewAndPostAnAdjustment extends AbstractTest
{
	
	//TC2274 Verify that the user is able to view new table on the purchases page named "adjustments" when a DC sends one.
	
	@Test()
	public void sprint12_US1388_TC2274() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	{
		/** Variable Section : **/
		PurchasesPage purchasesPage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Purchase landing page
		purchasesPage=homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPurchaseLandingPage();
		//Verify that pending adjustment table is displaying
		if(Base.isElementDisplayed(purchasesPage.PendingAdjustments_Title))
		{
			Reporter.reportPassResult(
					browser,"sprint12_US1388_TC2274",
					"Pending Adjustment table should display",
					"Pass");
		}
		else
		{
			Reporter.reportTestFailure(
					browser,"sprint12_US1388_TC2274","sprint12_US1388_TC2274",
					"Pending Adjustment table should display",
					"Fail");
			AbstractTest.takeSnapShot("sprint12_US1388_TC2274");
		}
		
		
		
		
	}
	
	//TC2275 Verify that the user is able to view the headers as Date, Vendor, WRIN number and WRIN Description.
	
	@Test()
	public void sprint12_US1388_TC2275() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	{
		
		/** Variable Section : **/
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Purchase landing page
		homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPurchaseLandingPage();
		//Verify that Date, Vendor, WRIN number and WRIN Description column header should display
		int size=driver.findElements(By.xpath("//div[@class='dataTables_scrollHead']//thead[@id='adjustments_table']/tr/th")).size();
		System.out.println("size is"+size);
		String[] value = new String[size];
		for(int i=1;i<=size;i++)
		{
			value[i-1]=driver.findElement(By.xpath("//div[@class='dataTables_scrollHead']//thead[@id='adjustments_table']/tr/th["+i+"]")).getText().trim();
			System.out.println(value[i-1]);
		}
		
		//Verify that all the colun header is displaying successfully
		if(value[0].equalsIgnoreCase("Date") && value[1].equalsIgnoreCase("Credit Order Number") && value[2].equalsIgnoreCase("Wrin Number") && value[3].equalsIgnoreCase("Wrin Description") && value[4].equalsIgnoreCase("Vendor")
				
				&& value[5].equalsIgnoreCase("Loose Units") && value[6].equalsIgnoreCase("Credit Quantity") && value[7].equalsIgnoreCase("Total Units") && value[8].equalsIgnoreCase("Status") &&   value[9].equalsIgnoreCase("Updated")
				&& value[10].equalsIgnoreCase("Posted Date") && value[11].equalsIgnoreCase("Post"))
		{
			Reporter.reportPassResult(
					browser,"sprint12_US1388_TC2275",
					"All the column header should display ",
					"Pass");
		}
		else
		{
			Reporter.reportTestFailure(
					browser,"sprint12_US1388_TC2275","sprint12_US1388_TC2275",
					"All the column header should display ",
					"Fail");
			AbstractTest.takeSnapShot("sprint12_US1388_TC2275");
		}
		
		
		
	}
	
		
	
	
}
