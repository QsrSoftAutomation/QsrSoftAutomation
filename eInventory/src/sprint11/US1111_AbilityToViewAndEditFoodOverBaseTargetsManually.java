package sprint11;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import common.Base;
import common.GlobalVariable;
import common.Reporter;
import eInventoryPageClasses.FoodOverBasePage;
import eInventoryPageClasses.HomePage;
import sprint2.AbstractTest;

public class US1111_AbilityToViewAndEditFoodOverBaseTargetsManually extends AbstractTest
{
	
	//TC2029 Verify that the user is able to edit the Targets>% column for all the fields for each controllable component listed on the Food Over Base Landing page on in the Current Month to Date section. 
	
	@Test()
	
	public void Sprint11_US1111_TC2029() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	{
		/** Variable Section : **/
		FoodOverBasePage foodOverBasePage;
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String validValues="49";
		String invalidValues="50";
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Food Over Base page
		foodOverBasePage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToFoodOverBasePage();
		//Click on Target percentage column header link image
		foodOverBasePage.CurrentMonth_TargetPercent_ColumnHeader_Image_LK.click();
		wait.until(ExpectedConditions.visibilityOf(foodOverBasePage.PostTargetValuesPopUp_BaseFood_TB));
		//Verify that user is only able to enter the values between 
		foodOverBasePage.PostTargetValuesPopUp_BaseFood_TB.clear();
		foodOverBasePage.PostTargetValuesPopUp_BaseFood_TB.sendKeys(validValues);
		Thread.sleep(2000);
		boolean condition1=Base.isElementDisplayed(foodOverBasePage.PostTargetValuesPopUp_WrongValue_Error_MSG);
		foodOverBasePage.PostTargetValuesPopUp_MenuItemWaste_TB.clear();
		foodOverBasePage.PostTargetValuesPopUp_MenuItemWaste_TB.sendKeys(validValues);
		Thread.sleep(2000);
		foodOverBasePage.PostTargetValuesPopUp_RawWaste_TB.clear();
		foodOverBasePage.PostTargetValuesPopUp_RawWaste_TB.sendKeys(validValues);
		Thread.sleep(2000);
		boolean condition2=Base.isElementDisplayed(foodOverBasePage.PostTargetValuesPopUp_WrongValue_Error_MSG);
		foodOverBasePage.PostTargetValuesPopUp_Condiment_TB.clear();
		foodOverBasePage.PostTargetValuesPopUp_Condiment_TB.sendKeys(validValues);
		Thread.sleep(2000);
		boolean condition3=Base.isElementDisplayed(foodOverBasePage.PostTargetValuesPopUp_WrongValue_Error_MSG);
		foodOverBasePage.PostTargetValuesPopUp_EmployeeManagerFood_TB.clear();
		foodOverBasePage.PostTargetValuesPopUp_EmployeeManagerFood_TB.sendKeys(validValues);
		Thread.sleep(2000);
		boolean condition4=Base.isElementDisplayed(foodOverBasePage.PostTargetValuesPopUp_WrongValue_Error_MSG);
		foodOverBasePage.PostTargetValuesPopUp_DiscountCoupon_TB.clear();
		foodOverBasePage.PostTargetValuesPopUp_DiscountCoupon_TB.sendKeys(validValues);
		Thread.sleep(2000);
		boolean condition5=Base.isElementDisplayed(foodOverBasePage.PostTargetValuesPopUp_WrongValue_Error_MSG);
		foodOverBasePage.PostTargetValuesPopUp_StatVariance_TB.clear();
		foodOverBasePage.PostTargetValuesPopUp_StatVariance_TB.sendKeys(validValues);
		Thread.sleep(2000);
		boolean condition6=Base.isElementDisplayed(foodOverBasePage.PostTargetValuesPopUp_WrongValue_Error_MSG);
		foodOverBasePage.PostTargetValuesPopUp_UnexplainedDifference_TB.clear();
		foodOverBasePage.PostTargetValuesPopUp_UnexplainedDifference_TB.sendKeys(validValues);
		Thread.sleep(2000);
		boolean condition7=Base.isElementDisplayed(foodOverBasePage.PostTargetValuesPopUp_WrongValue_Error_MSG);
		if(condition1 && condition2 && condition3 && condition4 && condition5 && condition6 && condition7)
		{
			Reporter.reportTestFailure(browser, "Sprint11_US1111_TC2029_Condition1",
					"Sprint11_US1111_TC2029",
					"Error message should display for invalid store number", "Fail");
			AbstractTest.takeSnapShot("Sprint11_US1111_TC2029_Condition1");
		}
		else
		{
			Reporter.reportPassResult(
					browser, "Sprint11_US1111_TC2029",
					"Error message should display for invalid store number",
					"Pass");
		}
		//Verify that user is only able to enter the values between 
		foodOverBasePage.PostTargetValuesPopUp_BaseFood_TB.clear();
		foodOverBasePage.PostTargetValuesPopUp_BaseFood_TB.sendKeys(invalidValues);
		Thread.sleep(2000);
		boolean con1=Base.isElementDisplayed(foodOverBasePage.PostTargetValuesPopUp_WrongValue_Error_MSG);
		foodOverBasePage.PostTargetValuesPopUp_MenuItemWaste_TB.clear();
		foodOverBasePage.PostTargetValuesPopUp_MenuItemWaste_TB.sendKeys(invalidValues);
		Thread.sleep(2000);
		foodOverBasePage.PostTargetValuesPopUp_RawWaste_TB.clear();
		foodOverBasePage.PostTargetValuesPopUp_RawWaste_TB.sendKeys(invalidValues);
		Thread.sleep(2000);
		boolean con2=Base.isElementDisplayed(foodOverBasePage.PostTargetValuesPopUp_WrongValue_Error_MSG);
		foodOverBasePage.PostTargetValuesPopUp_Condiment_TB.clear();
		foodOverBasePage.PostTargetValuesPopUp_Condiment_TB.sendKeys(invalidValues);
		Thread.sleep(2000);
		boolean con3=Base.isElementDisplayed(foodOverBasePage.PostTargetValuesPopUp_WrongValue_Error_MSG);
		foodOverBasePage.PostTargetValuesPopUp_EmployeeManagerFood_TB.clear();
		foodOverBasePage.PostTargetValuesPopUp_EmployeeManagerFood_TB.sendKeys(invalidValues);
		Thread.sleep(2000);
		boolean con4=Base.isElementDisplayed(foodOverBasePage.PostTargetValuesPopUp_WrongValue_Error_MSG);
		foodOverBasePage.PostTargetValuesPopUp_DiscountCoupon_TB.clear();
		foodOverBasePage.PostTargetValuesPopUp_DiscountCoupon_TB.sendKeys(invalidValues);
		Thread.sleep(2000);
		boolean con5=Base.isElementDisplayed(foodOverBasePage.PostTargetValuesPopUp_WrongValue_Error_MSG);
		foodOverBasePage.PostTargetValuesPopUp_StatVariance_TB.clear();
		foodOverBasePage.PostTargetValuesPopUp_StatVariance_TB.sendKeys(invalidValues);
		Thread.sleep(2000);
		boolean con6=Base.isElementDisplayed(foodOverBasePage.PostTargetValuesPopUp_WrongValue_Error_MSG);
		foodOverBasePage.PostTargetValuesPopUp_UnexplainedDifference_TB.clear();
		foodOverBasePage.PostTargetValuesPopUp_UnexplainedDifference_TB.sendKeys(invalidValues);
		Thread.sleep(2000);
		boolean con7=Base.isElementDisplayed(foodOverBasePage.PostTargetValuesPopUp_WrongValue_Error_MSG);
		if(con1 && con2 && con3 && con4 && con5 && con6 && con7)
		{
			Reporter.reportPassResult(
					browser, "Sprint11_US1111_TC2029",
					"Error message should display for invalid store number",
					"Pass");
		}
		else
		{
			Reporter.reportTestFailure(browser, "Sprint11_US1111_TC2029_Condition2",
					"Sprint11_US1111_TC2029",
					"Error message should display for invalid store number", "Fail");
			AbstractTest.takeSnapShot("Sprint11_US1111_TC2029_Condition2");
		}
		
		
	}
	
//TC2030 Verify that the percentage must be positive and can be up to the hundredths decimal place for the Targets>% column of all the fields for each controllable component listed on the Food Over Base Landing page on in the Current Month to Date section.	
	
@Test()
	
	public void Sprint11_US1111_TC2030() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	{
		
	/** Variable Section : **/
	FoodOverBasePage foodOverBasePage;
	String storeId = GlobalVariable.StoreId;
	String userId = GlobalVariable.userId;
	String validValues="49.55";
	String invalidValues01="-1";
	String invalidValues02="49.555";
	/***********************************/
	HomePage homePage = PageFactory.initElements(driver, HomePage.class);
	// Navigate to Food Over Base page
	foodOverBasePage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToFoodOverBasePage();
	//Click on Target percentage column header link image
	foodOverBasePage.CurrentMonth_TargetPercent_ColumnHeader_Image_LK.click();
	wait.until(ExpectedConditions.visibilityOf(foodOverBasePage.PostTargetValuesPopUp_BaseFood_TB));
	//Verify that user is only able to enter the values between 
	foodOverBasePage.PostTargetValuesPopUp_BaseFood_TB.clear();
	foodOverBasePage.PostTargetValuesPopUp_BaseFood_TB.sendKeys(validValues);
	Thread.sleep(2000);
	boolean condition1=Base.isElementDisplayed(foodOverBasePage.PostTargetValuesPopUp_WrongValue_Error_MSG);
	foodOverBasePage.PostTargetValuesPopUp_MenuItemWaste_TB.clear();
	foodOverBasePage.PostTargetValuesPopUp_MenuItemWaste_TB.sendKeys(validValues);
	Thread.sleep(2000);
	foodOverBasePage.PostTargetValuesPopUp_RawWaste_TB.clear();
	foodOverBasePage.PostTargetValuesPopUp_RawWaste_TB.sendKeys(validValues);
	Thread.sleep(2000);
	boolean condition2=Base.isElementDisplayed(foodOverBasePage.PostTargetValuesPopUp_WrongValue_Error_MSG);
	foodOverBasePage.PostTargetValuesPopUp_Condiment_TB.clear();
	foodOverBasePage.PostTargetValuesPopUp_Condiment_TB.sendKeys(validValues);
	Thread.sleep(2000);
	boolean condition3=Base.isElementDisplayed(foodOverBasePage.PostTargetValuesPopUp_WrongValue_Error_MSG);
	foodOverBasePage.PostTargetValuesPopUp_EmployeeManagerFood_TB.clear();
	foodOverBasePage.PostTargetValuesPopUp_EmployeeManagerFood_TB.sendKeys(validValues);
	Thread.sleep(2000);
	boolean condition4=Base.isElementDisplayed(foodOverBasePage.PostTargetValuesPopUp_WrongValue_Error_MSG);
	foodOverBasePage.PostTargetValuesPopUp_DiscountCoupon_TB.clear();
	foodOverBasePage.PostTargetValuesPopUp_DiscountCoupon_TB.sendKeys(validValues);
	Thread.sleep(2000);
	boolean condition5=Base.isElementDisplayed(foodOverBasePage.PostTargetValuesPopUp_WrongValue_Error_MSG);
	foodOverBasePage.PostTargetValuesPopUp_StatVariance_TB.clear();
	foodOverBasePage.PostTargetValuesPopUp_StatVariance_TB.sendKeys(validValues);
	Thread.sleep(2000);
	boolean condition6=Base.isElementDisplayed(foodOverBasePage.PostTargetValuesPopUp_WrongValue_Error_MSG);
	foodOverBasePage.PostTargetValuesPopUp_UnexplainedDifference_TB.clear();
	foodOverBasePage.PostTargetValuesPopUp_UnexplainedDifference_TB.sendKeys(validValues);
	Thread.sleep(2000);
	boolean condition7=Base.isElementDisplayed(foodOverBasePage.PostTargetValuesPopUp_WrongValue_Error_MSG);
	if(condition1 && condition2 && condition3 && condition4 && condition5 && condition6 && condition7)
	{
		Reporter.reportTestFailure(browser, "Sprint11_US1111_TC2030_Condition1",
				"Sprint11_US1111_TC2030",
				"Error message should display for invalid store number", "Fail");
		AbstractTest.takeSnapShot("Sprint11_US1111_TC2030_Condition1");
	}
	else
	{
		Reporter.reportPassResult(
				browser, "Sprint11_US1111_TC2030",
				"Error message should display for invalid store number",
				"Pass");
	}
	//Verify that user is only able to enter the values between 
	foodOverBasePage.PostTargetValuesPopUp_BaseFood_TB.clear();
	foodOverBasePage.PostTargetValuesPopUp_BaseFood_TB.sendKeys(invalidValues01);
	Thread.sleep(2000);
	boolean con1=Base.isElementDisplayed(foodOverBasePage.PostTargetValuesPopUp_WrongValue_Error_MSG);
	foodOverBasePage.PostTargetValuesPopUp_MenuItemWaste_TB.clear();
	foodOverBasePage.PostTargetValuesPopUp_MenuItemWaste_TB.sendKeys(invalidValues01);
	Thread.sleep(2000);
	foodOverBasePage.PostTargetValuesPopUp_RawWaste_TB.clear();
	foodOverBasePage.PostTargetValuesPopUp_RawWaste_TB.sendKeys(invalidValues01);
	Thread.sleep(2000);
	boolean con2=Base.isElementDisplayed(foodOverBasePage.PostTargetValuesPopUp_WrongValue_Error_MSG);
	foodOverBasePage.PostTargetValuesPopUp_Condiment_TB.clear();
	foodOverBasePage.PostTargetValuesPopUp_Condiment_TB.sendKeys(invalidValues01);
	Thread.sleep(2000);
	boolean con3=Base.isElementDisplayed(foodOverBasePage.PostTargetValuesPopUp_WrongValue_Error_MSG);
	foodOverBasePage.PostTargetValuesPopUp_EmployeeManagerFood_TB.clear();
	foodOverBasePage.PostTargetValuesPopUp_EmployeeManagerFood_TB.sendKeys(invalidValues02);
	Thread.sleep(2000);
	boolean con4=Base.isElementDisplayed(foodOverBasePage.PostTargetValuesPopUp_WrongValue_Error_MSG);
	foodOverBasePage.PostTargetValuesPopUp_DiscountCoupon_TB.clear();
	foodOverBasePage.PostTargetValuesPopUp_DiscountCoupon_TB.sendKeys(invalidValues02);
	Thread.sleep(2000);
	boolean con5=Base.isElementDisplayed(foodOverBasePage.PostTargetValuesPopUp_WrongValue_Error_MSG);
	foodOverBasePage.PostTargetValuesPopUp_StatVariance_TB.clear();
	foodOverBasePage.PostTargetValuesPopUp_StatVariance_TB.sendKeys(invalidValues02);
	Thread.sleep(2000);
	boolean con6=Base.isElementDisplayed(foodOverBasePage.PostTargetValuesPopUp_WrongValue_Error_MSG);
	foodOverBasePage.PostTargetValuesPopUp_UnexplainedDifference_TB.clear();
	foodOverBasePage.PostTargetValuesPopUp_UnexplainedDifference_TB.sendKeys(invalidValues02);
	Thread.sleep(2000);
	boolean con7=Base.isElementDisplayed(foodOverBasePage.PostTargetValuesPopUp_WrongValue_Error_MSG);
	if(con1 && con2 && con3 && con4 && con5 && con6 && con7)
	{
		Reporter.reportPassResult(
				browser, "Sprint11_US1111_TC2030",
				"Error message should display for invalid store number",
				"Pass");
	}
	else
	{
		Reporter.reportTestFailure(browser, "Sprint11_US1111_TC2030_Condition2",
				"Sprint11_US1111_TC2030",
				"Error message should display for invalid store number", "Fail");
		AbstractTest.takeSnapShot("Sprint11_US1111_TC2030_Condition2");
	}
	
}
	
//TC2031 Verify that each percentage must be <50% and >= 0% for the Targets>% column of all the fields for each controllable component listed on the Food Over Base Landing page on in the Current Month to Date section.
	
@Test()

public void Sprint11_US1111_TC2031() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
{
	
	/** Variable Section : **/
	FoodOverBasePage foodOverBasePage;
	String storeId = GlobalVariable.StoreId;
	String userId = GlobalVariable.userId;
	String validValues="49.55";
	String invalidValues01="-1";
	String invalidValues02="49.555";
	/***********************************/
	HomePage homePage = PageFactory.initElements(driver, HomePage.class);
	// Navigate to Food Over Base page
	foodOverBasePage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToFoodOverBasePage();
	//Click on Target percentage column header link image
	foodOverBasePage.CurrentMonth_TargetPercent_ColumnHeader_Image_LK.click();
	wait.until(ExpectedConditions.visibilityOf(foodOverBasePage.PostTargetValuesPopUp_BaseFood_TB));
	//Verify that user is only able to enter the values between 
	foodOverBasePage.PostTargetValuesPopUp_BaseFood_TB.clear();
	foodOverBasePage.PostTargetValuesPopUp_BaseFood_TB.sendKeys(validValues);
	Thread.sleep(2000);
	boolean condition1=Base.isElementDisplayed(foodOverBasePage.PostTargetValuesPopUp_WrongValue_Error_MSG);
	foodOverBasePage.PostTargetValuesPopUp_MenuItemWaste_TB.clear();
	foodOverBasePage.PostTargetValuesPopUp_MenuItemWaste_TB.sendKeys(validValues);
	Thread.sleep(2000);
	foodOverBasePage.PostTargetValuesPopUp_RawWaste_TB.clear();
	foodOverBasePage.PostTargetValuesPopUp_RawWaste_TB.sendKeys(validValues);
	Thread.sleep(2000);
	boolean condition2=Base.isElementDisplayed(foodOverBasePage.PostTargetValuesPopUp_WrongValue_Error_MSG);
	foodOverBasePage.PostTargetValuesPopUp_Condiment_TB.clear();
	foodOverBasePage.PostTargetValuesPopUp_Condiment_TB.sendKeys(validValues);
	Thread.sleep(2000);
	boolean condition3=Base.isElementDisplayed(foodOverBasePage.PostTargetValuesPopUp_WrongValue_Error_MSG);
	foodOverBasePage.PostTargetValuesPopUp_EmployeeManagerFood_TB.clear();
	foodOverBasePage.PostTargetValuesPopUp_EmployeeManagerFood_TB.sendKeys(validValues);
	Thread.sleep(2000);
	boolean condition4=Base.isElementDisplayed(foodOverBasePage.PostTargetValuesPopUp_WrongValue_Error_MSG);
	foodOverBasePage.PostTargetValuesPopUp_DiscountCoupon_TB.clear();
	foodOverBasePage.PostTargetValuesPopUp_DiscountCoupon_TB.sendKeys(validValues);
	Thread.sleep(2000);
	boolean condition5=Base.isElementDisplayed(foodOverBasePage.PostTargetValuesPopUp_WrongValue_Error_MSG);
	foodOverBasePage.PostTargetValuesPopUp_StatVariance_TB.clear();
	foodOverBasePage.PostTargetValuesPopUp_StatVariance_TB.sendKeys(validValues);
	Thread.sleep(2000);
	boolean condition6=Base.isElementDisplayed(foodOverBasePage.PostTargetValuesPopUp_WrongValue_Error_MSG);
	foodOverBasePage.PostTargetValuesPopUp_UnexplainedDifference_TB.clear();
	foodOverBasePage.PostTargetValuesPopUp_UnexplainedDifference_TB.sendKeys(validValues);
	Thread.sleep(2000);
	boolean condition7=Base.isElementDisplayed(foodOverBasePage.PostTargetValuesPopUp_WrongValue_Error_MSG);
	if(condition1 && condition2 && condition3 && condition4 && condition5 && condition6 && condition7)
	{
		Reporter.reportTestFailure(browser, "Sprint11_US1111_TC2031_Condition1",
				"Sprint11_US1111_TC2031",
				"Error message should display for invalid store number", "Fail");
		AbstractTest.takeSnapShot("Sprint11_US1111_TC2031_Condition1");
	}
	else
	{
		Reporter.reportPassResult(
				browser, "Sprint11_US1111_TC2031",
				"Error message should display for invalid store number",
				"Pass");
	}
	//Verify that user is only able to enter the values between 
	foodOverBasePage.PostTargetValuesPopUp_BaseFood_TB.clear();
	foodOverBasePage.PostTargetValuesPopUp_BaseFood_TB.sendKeys(invalidValues01);
	Thread.sleep(2000);
	boolean con1=Base.isElementDisplayed(foodOverBasePage.PostTargetValuesPopUp_WrongValue_Error_MSG);
	foodOverBasePage.PostTargetValuesPopUp_MenuItemWaste_TB.clear();
	foodOverBasePage.PostTargetValuesPopUp_MenuItemWaste_TB.sendKeys(invalidValues01);
	Thread.sleep(2000);
	foodOverBasePage.PostTargetValuesPopUp_RawWaste_TB.clear();
	foodOverBasePage.PostTargetValuesPopUp_RawWaste_TB.sendKeys(invalidValues01);
	Thread.sleep(2000);
	boolean con2=Base.isElementDisplayed(foodOverBasePage.PostTargetValuesPopUp_WrongValue_Error_MSG);
	foodOverBasePage.PostTargetValuesPopUp_Condiment_TB.clear();
	foodOverBasePage.PostTargetValuesPopUp_Condiment_TB.sendKeys(invalidValues01);
	Thread.sleep(2000);
	boolean con3=Base.isElementDisplayed(foodOverBasePage.PostTargetValuesPopUp_WrongValue_Error_MSG);
	foodOverBasePage.PostTargetValuesPopUp_EmployeeManagerFood_TB.clear();
	foodOverBasePage.PostTargetValuesPopUp_EmployeeManagerFood_TB.sendKeys(invalidValues02);
	Thread.sleep(2000);
	boolean con4=Base.isElementDisplayed(foodOverBasePage.PostTargetValuesPopUp_WrongValue_Error_MSG);
	foodOverBasePage.PostTargetValuesPopUp_DiscountCoupon_TB.clear();
	foodOverBasePage.PostTargetValuesPopUp_DiscountCoupon_TB.sendKeys(invalidValues02);
	Thread.sleep(2000);
	boolean con5=Base.isElementDisplayed(foodOverBasePage.PostTargetValuesPopUp_WrongValue_Error_MSG);
	foodOverBasePage.PostTargetValuesPopUp_StatVariance_TB.clear();
	foodOverBasePage.PostTargetValuesPopUp_StatVariance_TB.sendKeys(invalidValues02);
	Thread.sleep(2000);
	boolean con6=Base.isElementDisplayed(foodOverBasePage.PostTargetValuesPopUp_WrongValue_Error_MSG);
	foodOverBasePage.PostTargetValuesPopUp_UnexplainedDifference_TB.clear();
	foodOverBasePage.PostTargetValuesPopUp_UnexplainedDifference_TB.sendKeys(invalidValues02);
	Thread.sleep(2000);
	boolean con7=Base.isElementDisplayed(foodOverBasePage.PostTargetValuesPopUp_WrongValue_Error_MSG);
	if(con1 && con2 && con3 && con4 && con5 && con6 && con7)
	{
		Reporter.reportPassResult(
				browser, "Sprint11_US1111_TC2031",
				"Error message should display for invalid store number",
				"Pass");
	}
	else
	{
		Reporter.reportTestFailure(browser, "Sprint11_US1111_TC2031_Condition2",
				"Sprint11_US1111_TC2031",
				"Error message should display for invalid store number", "Fail");
		AbstractTest.takeSnapShot("Sprint11_US1111_TC2031_Condition2");
	}

}
	
	
	

//TC2033 Verify that the user has the ability to edit and save new values of Targets>% column for all the fields of each controllable component listed.


@Test()

public void Sprint11_US1111_TC2033() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
{

	/** Variable Section : **/
	FoodOverBasePage foodOverBasePage;
	String storeId = GlobalVariable.StoreId;
	String userId = GlobalVariable.userId;
	String validValues="49.55";
	
	/***********************************/
	HomePage homePage = PageFactory.initElements(driver, HomePage.class);
	// Navigate to Food Over Base page
	foodOverBasePage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToFoodOverBasePage();
	//Click on Target percentage column header link image
	foodOverBasePage.CurrentMonth_TargetPercent_ColumnHeader_Image_LK.click();
	wait.until(ExpectedConditions.visibilityOf(foodOverBasePage.PostTargetValuesPopUp_BaseFood_TB));
	//Verify that user is only able to enter the values between 
	foodOverBasePage.PostTargetValuesPopUp_BaseFood_TB.clear();
	foodOverBasePage.PostTargetValuesPopUp_BaseFood_TB.sendKeys(validValues);
	foodOverBasePage.PostTargetValuesPopUp_MenuItemWaste_TB.clear();
	foodOverBasePage.PostTargetValuesPopUp_MenuItemWaste_TB.sendKeys(validValues);
	foodOverBasePage.PostTargetValuesPopUp_RawWaste_TB.clear();
	foodOverBasePage.PostTargetValuesPopUp_RawWaste_TB.sendKeys(validValues);
	foodOverBasePage.PostTargetValuesPopUp_Condiment_TB.clear();
	foodOverBasePage.PostTargetValuesPopUp_Condiment_TB.sendKeys(validValues);
	foodOverBasePage.PostTargetValuesPopUp_EmployeeManagerFood_TB.clear();
	foodOverBasePage.PostTargetValuesPopUp_EmployeeManagerFood_TB.sendKeys(validValues);
	foodOverBasePage.PostTargetValuesPopUp_DiscountCoupon_TB.clear();
	foodOverBasePage.PostTargetValuesPopUp_DiscountCoupon_TB.sendKeys(validValues);
	foodOverBasePage.PostTargetValuesPopUp_StatVariance_TB.clear();
	foodOverBasePage.PostTargetValuesPopUp_StatVariance_TB.sendKeys(validValues);
	foodOverBasePage.PostTargetValuesPopUp_UnexplainedDifference_TB.clear();
	foodOverBasePage.PostTargetValuesPopUp_UnexplainedDifference_TB.sendKeys(validValues);
	//perform the scroll down
	//((JavascriptExecutor) driver).executeScript("scroll(0,250);");
	Thread.sleep(5000);
	//click on save button
	((JavascriptExecutor) driver).executeScript("document.getElementById('continue_transfer20').click();");
	//foodOverBasePage.PostTargetValuesPopUp_Save_BT.click();
	wait.until(ExpectedConditions.visibilityOf(foodOverBasePage.PostTargetValuesPopUp_ChangesSaved_Confirmation_MSG));
	if(Base.isElementDisplayed(foodOverBasePage.PostTargetValuesPopUp_ChangesSaved_Confirmation_MSG))
	{
		Reporter.reportPassResult(
				browser, "Sprint11_US1111_TC2033",
				"Changes Saved confirmation message should display",
				"Pass");
		
	}
	else
	{
		Reporter.reportTestFailure(browser, "Sprint11_US1111_TC2033_Condition1",
				"Sprint11_US1111_TC2033",
				"Error message should display for invalid store number", "Fail");
		AbstractTest.takeSnapShot("Sprint11_US1111_TC2033_Condition1");
	}
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='toast-message' and text()='Changes Saved']")));
	Thread.sleep(2000);
	//Click on Target percentage column header link image
	foodOverBasePage.CurrentMonth_TargetPercent_ColumnHeader_Image_LK.click();
	wait.until(ExpectedConditions.visibilityOf(foodOverBasePage.PostTargetValuesPopUp_BaseFood_TB));
	//Verify that values is saved successfully
	//Verify that user is only able to enter the values between 
	String value1=foodOverBasePage.PostTargetValuesPopUp_BaseFood_TB.getAttribute("value");
	String value2=foodOverBasePage.PostTargetValuesPopUp_MenuItemWaste_TB.getAttribute("value");
	String value3=foodOverBasePage.PostTargetValuesPopUp_RawWaste_TB.getAttribute("value");
	String value4=foodOverBasePage.PostTargetValuesPopUp_Condiment_TB.getAttribute("value");
	String value5=foodOverBasePage.PostTargetValuesPopUp_EmployeeManagerFood_TB.getAttribute("value");
	String value6=foodOverBasePage.PostTargetValuesPopUp_DiscountCoupon_TB.getAttribute("value");
	String value7=foodOverBasePage.PostTargetValuesPopUp_StatVariance_TB.getAttribute("value");
	String value8=foodOverBasePage.PostTargetValuesPopUp_UnexplainedDifference_TB.getAttribute("value");
	if(value1.equalsIgnoreCase(validValues) && value2.equalsIgnoreCase(validValues) && value3.equalsIgnoreCase(validValues)
		&&	value4.equalsIgnoreCase(validValues) && value5.equalsIgnoreCase(validValues) && value6.equalsIgnoreCase(validValues)
		&& value7.equalsIgnoreCase(validValues) && value8.equalsIgnoreCase(validValues))
	{
		Reporter.reportPassResult(
				browser, "Sprint11_US1111_TC2033",
				"Chnages should be reflected successfully",
				"Pass");
	}
	else
	{
		Reporter.reportTestFailure(browser, "Sprint11_US1111_TC2033_Condition2",
				"Sprint11_US1111_TC2033",
				"Chnages should be reflected successfully", "Fail");
		AbstractTest.takeSnapShot("Sprint11_US1111_TC2033_Condition2");
	}



}


//TC2034 Verify that the user can see the audit information.
@Test()

public void Sprint11_US1111_TC2034() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
{
	
	/** Variable Section : **/
	FoodOverBasePage foodOverBasePage;
	String storeId = GlobalVariable.StoreId;
	String userId = GlobalVariable.userId;
	String value="49.12";
	
	/***********************************/
	HomePage homePage = PageFactory.initElements(driver, HomePage.class);
	// Navigate to Food Over Base page
	foodOverBasePage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToFoodOverBasePage();
	//Click on Target percentage column header link image
	foodOverBasePage.CurrentMonth_TargetPercent_ColumnHeader_Image_LK.click();
	wait.until(ExpectedConditions.visibilityOf(foodOverBasePage.PostTargetValuesPopUp_BaseFood_TB));
	Thread.sleep(5000);
	//Verify that user is only able to,. enter the values between 
	String previuosValue=foodOverBasePage.PostTargetValuesPopUp_BaseFood_TB.getAttribute("value");
	System.out.println("previuosValue"+previuosValue);
	foodOverBasePage.PostTargetValuesPopUp_BaseFood_TB.clear();
	foodOverBasePage.PostTargetValuesPopUp_BaseFood_TB.sendKeys(value);
	//perform the scroll down
	//((JavascriptExecutor) driver).executeScript("scroll(0,250);");
	Thread.sleep(5000);
	//click on save button
	((JavascriptExecutor) driver).executeScript("document.getElementById('continue_transfer20').click();");
	wait.until(ExpectedConditions.visibilityOf(foodOverBasePage.PostTargetValuesPopUp_ChangesSaved_Confirmation_MSG));
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='toast-message' and text()='Changes Saved']")));
	Thread.sleep(2000);
	//click on Audit Trail button
	foodOverBasePage.AuditTrail_Image_LK.click();
	wait.until(ExpectedConditions.visibilityOf(foodOverBasePage.AuditForFoodOverBasePopUp_Title));
	String previousValue1=driver.findElement(By.xpath("//table[@id='fob_audit_modal_tbl']/tbody/tr[1]/td[4]")).getText().trim();
	System.out.println("previousValue1"+previousValue1);
	String value1=driver.findElement(By.xpath("//table[@id='fob_audit_modal_tbl']/tbody/tr[1]/td[5]")).getText().trim();
	System.out.println("value1"+value1);
	if(previousValue1.equalsIgnoreCase(previuosValue+"0") && value.equalsIgnoreCase(value1))
	{
		Reporter.reportPassResult(
				browser, "Sprint11_US1111_TC2034",
				"User should be able to view the audit information",
				"Pass");
	}
	else
	{
		Reporter.reportTestFailure(browser, "Sprint11_US1111_TC2034",
				"Sprint11_US1111_TC2034",
				"User should be able to view the audit information", "Fail");
		AbstractTest.takeSnapShot("Sprint11_US1111_TC2034");
	}
	
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
