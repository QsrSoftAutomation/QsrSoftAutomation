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
import sprint2.AbstractTest;

public class US1152_DisplayProductNetSalesDollar extends AbstractTest
{
	
	//TC2168 Verify that the user is able to view the label "Product Net Sales $" in the Current Month section on the Food Over Base page.
	
	@Test()
	
	public void Sprint11_US1152_TC2168() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException 
	{
		
		/** Variable Section : **/
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Food over base page
		homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToFoodOverBasePage();
		Thread.sleep(4000);
		//Verify that Product Net Sales Label is displaying
		if(Base.isElementDisplayed(By.xpath("//h4[contains(.,'Product Net Sales:')]")))
		{
			Reporter.reportPassResult(
					browser,"Sprint11_US1152_TC2168",
					"'Product Net Sales' label should display",
					"Pass");
		}
		else
		{
			Reporter.reportTestFailure(
					browser,"Sprint11_US1152_TC2168","Sprint11_US1152_TC2168",
					"'Product Net Sales' label should display",
					"Fail");
			AbstractTest.takeSnapShot("Sprint11_US1152_TC2168");
		}
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
