package sprint11;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import common.GlobalVariable;
import common.ReadTestData;
import common.Reporter;
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.RawItemInformationPage;
import sprint2.AbstractTest;

public class US1297_RawItemInformationOnlyDisplayCalculatedYieldAndYieldRangeForAYieldItem extends AbstractTest
{
	
	
	
	//TC2043 Verify that calculated yield range and target yield range is visible against an item in raw item information page.
	
	@Test()
	
	public void Sprint11_US1111_TC2043() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	{
			
		/*Start-Variable Deceleration*/
		RawItemInformationPage rawItemInformationPage;
		String storeId=GlobalVariable.StoreId;
		String userId=GlobalVariable.userId;
		HSSFSheet rawItemInfomrationPageSheet = ReadTestData.getTestDataSheet("Sprint11_US1111_TC2043", "Object1");
		String wrinID = ReadTestData.getTestData(rawItemInfomrationPageSheet,"WRINId");
		/*End-Variable Deceleration*/
		//Go to raw item information page
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		//Navigate to Promo and waste landing page
		rawItemInformationPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToRawItemInformationPage();
		//Search and select the raw Item
		rawItemInformationPage.searchAndSelectWRINID(wrinID);
		//Verify that user is able to view the 'Targeted Yield Vale' and 'Calculated Yield Value' at the raw item information page
		String calculatedYielValue=rawItemInformationPage.CalculatedYield_Value.getText().trim();
		String targatedYielValue=rawItemInformationPage.TargetYieldRange_Value.getText().trim();
		if(calculatedYielValue.length()==0 || targatedYielValue.length()==0 || 
				calculatedYielValue.equalsIgnoreCase("Not Applicable") || targatedYielValue.equalsIgnoreCase("Not Applicable"))
		{
			Reporter.reportTestFailure(
					browser,"Sprint11_US1111_TC2043","Sprint11_US1111_TC2043",
					"Calculated Yield range and Targated Yield range should be visible",
					"Fail");
			AbstractTest.takeSnapShot("Sprint11_US1111_TC2043");
		}
		else
		{
			Reporter.reportPassResult(
					browser, "Sprint11_US1111_TC2043",
					"Calculated Yield range and Targated Yield range should be visible",
					"Pass");
		}
		
	}
	
	
	//TC2059 Verify that calculated yield range and target yield range is not  visible against a specific item on raw item information page.
		
		@Test()
		
		public void Sprint11_US1111_TC2059() throws RowsExceededException, BiffException, WriteException, IOException, InterruptedException 
		{

			/*Start-Variable Deceleration*/
			RawItemInformationPage rawItemInformationPage;
			String storeId=GlobalVariable.StoreId;
			String userId=GlobalVariable.userId;
			HSSFSheet rawItemInfomrationPageSheet = ReadTestData.getTestDataSheet("Sprint11_US1111_TC2059", "Object1");
			String wrinID = ReadTestData.getTestData(rawItemInfomrationPageSheet,"WRINId");
			/*End-Variable Deceleration*/
			//Go to raw item information page
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			//Navigate to Promo and waste landing page
			rawItemInformationPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToRawItemInformationPage();
			//Search and select the raw Item
			rawItemInformationPage.searchAndSelectWRINID(wrinID);
			//Verify that user is able to view the 'Targeted Yield Vale' and 'Calculated Yield Value' at the raw item information page
			String calculatedYielValue=rawItemInformationPage.CalculatedYield_Value.getText().trim();
			String targatedYielValue=rawItemInformationPage.TargetYieldRange_Value.getText().trim();
			if(calculatedYielValue.equalsIgnoreCase("Not Applicable") && targatedYielValue.equalsIgnoreCase("Not Applicable"))
			{
				Reporter.reportPassResult(
						browser, "Sprint11_US1111_TC2059",
						"Calculated Yield range and Targated Yield range should not be visible",
						"Pass");
			}
			else
			{
				
				Reporter.reportTestFailure(
						browser,"Sprint11_US1111_TC2059","Sprint11_US1111_TC2059",
						"Calculated Yield range and Targated Yield range should not be visible",
						"Fail");
				AbstractTest.takeSnapShot("Sprint11_US1111_TC2059");
			}
			
			
		}
}
