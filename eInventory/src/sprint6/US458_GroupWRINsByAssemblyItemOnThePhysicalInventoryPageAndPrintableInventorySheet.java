package sprint6;

import java.io.IOException;
import java.util.ArrayList;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import sprint2.AbstractTest;

import common.GlobalVariable;
import common.Reporter;

import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.PhysicalInventoryPage;

public class US458_GroupWRINsByAssemblyItemOnThePhysicalInventoryPageAndPrintableInventorySheet extends AbstractTest
{
	
	// Verify that Raw Items and assembly Item groupings are present when the printed sheet is generated of existing inventory on physical inventory page.
	
	@Test(enabled = false)
	public void Sprint6_US458_TC1230() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	{
		String wrinId=GlobalVariable.wrinID2withSamePrefix_1;
		String wrinId1=GlobalVariable.wrinID2withSamePrefix_2;
		//Get the prefix for WRIN ID
        String prefix = wrinId.split("-")[0];
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		//Navigate to physical Inventory Page
		PhysicalInventoryPage physicalInventoryPage=PageFactory.initElements(driver, PhysicalInventoryPage.class);
		homePage.selectAStore(GlobalVariable.StoreId).goToPhysicalInventoryPage();
		//Select the monthly type
		Select select =new Select(physicalInventoryPage.SelectABlankInventoryToPrint_DD);
		select.selectByVisibleText("Monthly");
	    Thread.sleep(10000);
		// Switch to new window opened
		 ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		 System.out.println(tabs2.size());
		 driver.switchTo().window(tabs2.get(1));
		 //Verify that same prefix WRIN should be grouped together in the Print inventory Page
		 boolean samePrefixWRINVerified = physicalInventoryPage.verifySamePrefixWRINAreGroupedTogether(prefix,wrinId,wrinId1);
		 if (samePrefixWRINVerified) {
				Reporter.reportPassResult(browser,
						"Sprint6_US458_TC1230",
						"WRIN ID with similer prefix should be grouped together","Pass");
			}
			else {
				Reporter.reportTestFailure(browser, 
						"Sprint6_US458_TC1230","Sprint6_US458_TC1230",
						"WRIN ID with similer prefix should be grouped together","Fail");
				AbstractTest.takeSnapShot("Sprint6_US459_TC1247");
			}
		 

	}
	
}
