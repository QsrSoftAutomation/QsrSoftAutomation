package sprint8;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import common.GlobalVariable;
import common.ReadTestData;
import common.Reporter;
import sprint2.AbstractTest;
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.RawItemInformationPage;

public class US740_ViewYieldRangeInfoRawItemInformationPage extends AbstractTest {

	//TC1579:Verify the user is able to view Target Yield range for any Raw Item on raw item information page.
	@Test()
	public void sprint8_US740_TC1579() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/**Variable Section :**/
		RawItemInformationPage rawItemInformationPage; 
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet rawItemInformationPageSheet = ReadTestData.getTestDataSheet("sprint8_US740_TC1579", "Object1");
		String wrinIDYieldRange1 = ReadTestData.getTestData(rawItemInformationPageSheet, "WRINId");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		//Navigate to raw item info page
		rawItemInformationPage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToRawItemInformationPage();
		//Search for the WRIN ID
		rawItemInformationPage.searchAndSelectWRINID(wrinIDYieldRange1);
		wait.until(ExpectedConditions.visibilityOf(rawItemInformationPage.RawItemInfo_Title_Label));
		//Verify that Target Yield Range should be displayed in raw item info page
		if(!(rawItemInformationPage.TargetYieldRange_Value.getText().isEmpty())){
			Reporter.reportPassResult(
					browser,"sprint8_US740_TC1579",
					"User should be able to view the target yield range in Raw Item Information Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint8_US740_TC1579","sprint8_US740_TC1579",
					"User should be able to view the target yield range in Raw Item Information Page",
					"Fail");
			AbstractTest.takeSnapShot("sprint8_US740_TC1579");

		}
	}
	
	// TC1581:Verify "Target Yield Range" for raw item whose midrange yield number is < 100
	@Test()
	public void sprint8_US740_TC1581() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/**Variable Section :**/
		RawItemInformationPage rawItemInformationPage; 
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet rawItemInformationPageSheet = ReadTestData.getTestDataSheet("sprint8_US740_TC1581", "Object1");
		String wrinIDYieldRange1 = ReadTestData.getTestData(rawItemInformationPageSheet, "WRINId");
		float middleRangeYieldNumber = Float.valueOf(ReadTestData.getTestData(rawItemInformationPageSheet, "YieldRange"));
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		//Navigate to raw item info page
		rawItemInformationPage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToRawItemInformationPage();
		//Search for a WRIN Id that has the middle Range Number <100
		rawItemInformationPage.searchAndSelectWRINID(wrinIDYieldRange1);
		wait.until(ExpectedConditions.visibilityOf(rawItemInformationPage.RawItemInfo_Title_Label));
		Thread.sleep(1500);
		//Get the total Yield Range Value
		String yieldRange = rawItemInformationPage.TargetYieldRange_Value.getText();
		//Get the low point
		float lowPoint = Float.parseFloat(yieldRange.split(" - ")[0]);
		//Verify that low point value should be middleRangeYieldNumber - 1
		boolean verifyYieldRange = (lowPoint == (middleRangeYieldNumber - 1));
		//Get the high point
		float highPoint = Float.parseFloat(yieldRange.split(" - ")[1]);
		//Verify that high point value should be middleRangeYieldNumber + 1
		verifyYieldRange = (highPoint == (middleRangeYieldNumber + 1));
		if (verifyYieldRange) {
			Reporter.reportPassResult(
					browser,"sprint8_US740_TC1581",
					"User should be able to view the lowPoint as midpointyieldnumber-1 and HighPoint as midpointyieldnumber+1",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint8_US740_TC1581","sprint8_US740_TC1581",
					"User should be able to view the lowPoint as midpointyieldnumber-1 and HighPoint as midpointyieldnumber+1",
					"Fail");
			AbstractTest.takeSnapShot("sprint8_US740_TC1581");

		}

	}
	
	// TC1582:Verify "Target Yield Range" for raw item whose midrange yield number is >100
	@Test()
	public void sprint8_US740_TC1582() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/**Variable Section :**/
		RawItemInformationPage rawItemInformationPage; 
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HSSFSheet rawItemInformationPageSheet = ReadTestData.getTestDataSheet("sprint8_US740_TC1582", "Object1");
		String wrinIDYieldRange2 = ReadTestData.getTestData(rawItemInformationPageSheet, "WRINId");
		float middleRangeYieldNumber = Float.valueOf(ReadTestData.getTestData(rawItemInformationPageSheet, "YieldRange"));
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		//Navigate to raw item info page
		rawItemInformationPage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToRawItemInformationPage();
		//Search for a WRIN Id that has the middle Range Number > 100
		rawItemInformationPage.searchAndSelectWRINID(wrinIDYieldRange2);
		wait.until(ExpectedConditions.visibilityOf(rawItemInformationPage.RawItemInfo_Title_Label));
		//Get the total Yield Range Value
		String yieldRange = rawItemInformationPage.TargetYieldRange_Value.getText();
		//Get the low point
		float lowPoint = Float.parseFloat(yieldRange.split(" - ")[0]);
		//Verify that low point value should be middleRangeYieldNumber - 10
		boolean verifyYieldRange = (lowPoint == (middleRangeYieldNumber - 10));
		//Get the high point
		float highPoint = Float.parseFloat(yieldRange.split(" - ")[1]);
		//Verify that high point value should be middleRangeYieldNumber + 10
		verifyYieldRange = (highPoint == (middleRangeYieldNumber + 10));
		if (verifyYieldRange) {
			Reporter.reportPassResult(
					browser,"sprint8_US740_TC1582",
					"User should be able to view the lowPoint as midpointyieldnumber-10 and HighPoint as midpointyieldnumber+10",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"sprint8_US740_TC1582","sprint8_US740_TC1582",
					"User should be able to view the lowPoint as midpointyieldnumber-10 and HighPoint as midpointyieldnumber+10",
					"Fail");
			AbstractTest.takeSnapShot("sprint8_US740_TC1582");

		}

	}

}
