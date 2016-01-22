package sprint5;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import common.GlobalVariable;
import common.Reporter;
import eInventoryPageClasses.HomePage;
import sprint2.AbstractTest;

public class US610_MaintainenceLandingPage extends AbstractTest {

		
	//Verify that user is able to select Raw Item Activity from Inventory drop-down list.
	@Test()
	
	public void Sprint5_US610_TC772() throws RowsExceededException, BiffException, WriteException, IOException, InterruptedException
	{
		/*Start-Variable Deceleration*/
		String storeId=GlobalVariable.StoreId;
		String userId=GlobalVariable.userId;
		/*End-Variable Deceleration*/
		//Enter url & verify user is able to select Raw Item Activity from eInventory dropdown list
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		boolean result=homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToRawItemActivityPage().RawItemActivity_Label.isDisplayed();
		if(result)
		{
			Reporter.reportPassResult(browser, "Sprint5_US610_TC772", "user should be select 'Raw Item Activity' from eInventory ddlist ", "Pass");
		}
		else
		{
			Reporter.reportTestFailure(browser, "Sprint5_US610_TC772", "Sprint5_US610_TC772", "user should be select 'Raw Item Activity' from eInventory ddlist ", "Fail");
			AbstractTest.takeSnapShot("Sprint5_US610_TC772_condition1");

		}	
	}
	//Verify that user is able to select Raw Item Information from Inventory drop-down list.
	@Test()
	
	public void Sprint5_US610_TC773() throws RowsExceededException, BiffException, WriteException, IOException, InterruptedException
	{
		/*Start-Variable Deceleration*/
		String storeId=GlobalVariable.StoreId;
		String userId=GlobalVariable.userId;
		/*End-Variable Deceleration*/
		//Enter URL & verify user is able to select Raw Item Information from eInventory dropdown list
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		boolean result=homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToRawItemInformationPage().RawItemInformation_Label.isDisplayed();
		if(result)
		{
			Reporter.reportPassResult(browser, "Sprint5_US610_TC773", "user should be select 'Raw Item Information' from eInventory ddlist ", "Pass");
		}
		else
		{
			Reporter.reportTestFailure(browser, "Sprint5_US610_TC773", "Sprint5_US610_TC773", "user should be select 'Raw Item Information' from eInventory ddlist ", "Fail");
			AbstractTest.takeSnapShot("Sprint5_US610_TC773_condition1");

		}	
	}
	

}
