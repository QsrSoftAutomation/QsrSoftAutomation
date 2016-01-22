package sprint7;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import common.GlobalVariable;
import common.Reporter;
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.MenuItemInformationPage;
import sprint2.AbstractTest;

public class US602_ViewMenuItemInformation extends AbstractTest
{
	// TC1349 Verify that user is able to navigate to "Menu Item Information" landing page and search and select a menu item on it.
	@Test()
	public void Sprint7_US602_TC1349() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/* Start-Variable Deceleration */
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String menuItem = GlobalVariable.menuItem;
		/* End-Variable Deceleration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		MenuItemInformationPage menuItemInformationPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement()
				.goToMenuItemInformationPage().searchAndSelectMenuItem(menuItem);
		if (menuItemInformationPage.Attribute_Column_Label.isDisplayed()) {
			Reporter.reportPassResult(browser, "Sprint7_US602_TC1349",
					"User should be able to search and select menu item",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "Sprint7_US602_TC1349","Sprint7_US602_TC1349",
					"User should be able to search and select menu item",
					"Fail");
			AbstractTest.takeSnapShot("Sprint7_US602_TC1349");
		}
	}
	
	/*TC1350 Verify that below fields for menu item are read only to user on "Menu Information" page:
		1)Menu Item Number, 2)Description, 3)Currently on POS, 4)Daypart code, 5)Family Group, 6)Yield Item?,
		7)Current Recipe Information, 8)Historical Recipe Information, 9)Recipe History*/
	
	@Test()
	public void Sprint2_US602_TC1350() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/* Start-Variable Deceleration */
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String menuItem = GlobalVariable.menuItem;
		/* End-Variable Deceleration */
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		MenuItemInformationPage menuItemInformationPage=homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement()
				.goToMenuItemInformationPage().searchAndSelectMenuItem(menuItem);
		if (menuItemInformationPage.MenuItemNumber_Label.isDisplayed()
				&& menuItemInformationPage.DaypartCode_Label.isDisplayed()
				&& menuItemInformationPage.LongDescription_Label.isDisplayed()
				&& menuItemInformationPage.CurrentlyOnPOS_Label.isDisplayed()
				&& menuItemInformationPage.FamilyGroup_Label.isDisplayed()
				&& menuItemInformationPage.Recipe_Label.isDisplayed()
				&& menuItemInformationPage.HistoricalRecipe_Label.isDisplayed()) {
			Reporter.reportPassResult(
					browser, "Sprint7_US602_TC1350",
					"All the fields should display on Menu Information page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint7_US602_TC1350","Sprint7_US602_TC1350",
					"All the fields should display on Menu Item information page  ",
					"Fail");
			AbstractTest.takeSnapShot("Sprint7_US602_TC1350");
		}
	}
	
	/*TC1351 Verify the below Validations on the menu item information page for each menu item:
	Menu Item Number: No fixed length , Description: Should be of max. 20 characters,Currently on POS: Accepts only "Yes" or "No",
	Yield Item:Accepts only "Yes" or "No"*/
	
	@Test()
	public void Sprint2_US602_TC1351() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/* Start-Variable Deceleration */
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String menuItem = GlobalVariable.menuItem;
		/* End-Variable Deceleration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		MenuItemInformationPage menuItemInformationPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement()
				.goToMenuItemInformationPage().searchAndSelectMenuItem(menuItem);
		 /* Verify that "Menu Item Number" field should display any number of digits. "Description" field length should display
		 *  characters up to 20 max. "Currently on POS" should have either "Yes" or "No" as its value*/
		int menuItemNumberLength = menuItemInformationPage.MenuItemNumber_Value.getText().length();
		int longDescriptionLength = menuItemInformationPage.LongDescription_Value.getText().length();
		String currentlyOnPOS = menuItemInformationPage.CurrentlyOnPOS_Value.getText();
		if (menuItemNumberLength >= 0 && longDescriptionLength <= 20
				&& (currentlyOnPOS.equalsIgnoreCase("Yes") || currentlyOnPOS.equalsIgnoreCase("No"))) {
			Reporter.reportPassResult(
					browser,"Sprint7_US602_TC1351",
					"All the fields value should display with their current length value",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint7_US602_TC1351","Sprint7_US602_TC1351",
					"All the fields value should display with their current length value",
					"Fail");
			AbstractTest.takeSnapShot("Sprint7_US602_TC1351");
		}
	}
	
	//TC1373 Verify that user is able to close the "Menu Item Information" landing page
	@Test()
	public void Sprint2_US602_TC1373() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/* Start-Variable Deceleration */
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String menuItem = GlobalVariable.menuItem;
		/* End-Variable Deceleration */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		MenuItemInformationPage menuItemInformationPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement()
				.goToMenuItemInformationPage().searchAndSelectMenuItem(menuItem);
		// Click on 'Close' button
		menuItemInformationPage.Close_BT.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//th[text()='Attribute']")));
		if (driver.findElements(By.xpath("//th[text()='Attribute']")).size() == 0) {
			Reporter.reportPassResult(
					browser,"Sprint7_US602_TC1373",
					"User should be able to close the Menu Item Information page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint7_US602_TC1373","Sprint7_US602_TC1373",
					"User should be able to close the Menu Item Information page",
					"Fail");
		}
	}
}
