package sprint12;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import common.Base;
import common.GlobalVariable;
import common.Reporter;
import eInventoryPageClasses.FoodOverBasePage;
import eInventoryPageClasses.HomePage;
import sprint2.AbstractTest;

public class US1125_DisplayDifferenceBetweenFoodOverBaseTargetsAndFoodOverBaseActuals extends AbstractTest
{
	
	//TC2149 Verify that the user is able to view a column named "% Difference" to the end of the Current Month Food Over Base table
	
	
	@Test()
	public void sprint12_US1125_TC2149() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	{
		/** Variable Section : **/
		FoodOverBasePage foodOverBasePage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Food over base page
		foodOverBasePage=homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToFoodOverBasePage();
		//Verify that Difference percentage column is displaying
		if(Base.isElementDisplayed(foodOverBasePage.DifferencePercentage_Column_Label))
		{
			Reporter.reportPassResult(
					browser,"sprint12_US1125_TC2149",
					"Differenciate percentage column header should display",
					"Pass");
		}
		else
		{
			Reporter.reportTestFailure(
					browser,"sprint12_US1125_TC2149","sprint12_US1125_TC2149",
					"Differenciate percentage column header should display",
					"Fail");
			AbstractTest.takeSnapShot("sprint12_US1125_TC2149");
		}
		
	
	}
	
	//TC2152 Verify that the user is able to view a column named "$ Difference" to the end of the Current Month Food Over Base table.	

	@Test()	

	public void sprint12_US1125_TC2152() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	{

		/** Variable Section : **/
		FoodOverBasePage foodOverBasePage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Food over base page
		foodOverBasePage=homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToFoodOverBasePage();
		//Verify that Difference Doller column is displaying
				if(Base.isElementDisplayed(foodOverBasePage.DifferencePercentage_Column_Label))
				{
					Reporter.reportPassResult(
							browser,"sprint12_US1125_TC2152",
							"Differenciate doller column header should display",
							"Pass");
				}
				else
				{
					Reporter.reportTestFailure(
							browser,"sprint12_US1125_TC2152","sprint12_US1125_TC2152",
							"Differenciate doller column header should display",
							"Fail");
					AbstractTest.takeSnapShot("sprint12_US1125_TC2152");
				}
			
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
