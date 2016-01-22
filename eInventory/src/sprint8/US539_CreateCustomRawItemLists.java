package sprint8;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import common.Base;
import common.GlobalVariable;
import common.Reporter;
import eInventoryPageClasses.CustomRawItemListsPage;
import eInventoryPageClasses.HomePage;
import sprint2.AbstractTest;

public class US539_CreateCustomRawItemLists extends AbstractTest {

	// TC1564 Verify the the user is able to land on "Custom List" landing page.
	@Test()
	public void Sprint8_US539_TC1564() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/* Start-Variable Deceleration */
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/* End */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		CustomRawItemListsPage customRawItemListsPage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToCustomRawItemListsPage();
		if (Base.isElementDisplayed(customRawItemListsPage.CustomeRawItemLists_Title)) {
			Reporter.reportPassResult(
					browser, "Sprint8_US539_TC1564",
					"User should be redirected to Custom raw item list page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "Sprint8_US539_TC1564","Sprint8_US539_TC1564",
					"User should be redirected to Custom raw item list page",
					"Fail");
			AbstractTest.takeSnapShot("Sprint8_US539_TC1564");
		}
	}
	
	// TC1633 Verify the user must enter a name for the custom list.
	@Test()
	public void Sprint8_US539_TC1633() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/* Start-Variable Deceleration */
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		/* End */
		String rawItemListName = "Test" + Base.generateNdigitRandomNumber(3);
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		CustomRawItemListsPage customRawItemListsPage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToCustomRawItemListsPage();
		customRawItemListsPage.AddList_BT.click();
		customRawItemListsPage.CreateNewRawItemListPopup_Name_TB.sendKeys(rawItemListName);
		Thread.sleep(3000);
		if (customRawItemListsPage.CreateNewRawItemListPopup_Name_TB.getAttribute("value").trim().equalsIgnoreCase(rawItemListName)) {
			Reporter.reportPassResult(
					browser, "Sprint8_US539_TC1633",
					"User should be able to enter the list name", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "Sprint8_US539_TC1633","Sprint8_US539_TC1633",
					"User should be able to enter the list name", "Fail");
			AbstractTest.takeSnapShot("Sprint8_US539_TC1633");
		}
	}
	
	// TC1635 Verify the user has the ability to add raw items to the list.
	@Test()
	public void Sprint8_US539_TC1635() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/* Start-Variable Deceleration */
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String rawItemListName = "Test" + Base.generateNdigitRandomNumber(3);
		/* End */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Go to Custom raw Item list page
		CustomRawItemListsPage customRawItemListsPage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToCustomRawItemListsPage();
		// Code to create new Custom List
		customRawItemListsPage.createACustomRawList(rawItemListName);
		// Add raw item in the custom list
		customRawItemListsPage.addAWrinInCustomList(rawItemListName,GlobalVariable.addAnItemWrin1);
		// click on the raw item list
		customRawItemListsPage.clickOnARawItemList(rawItemListName);
		if (customRawItemListsPage.isRawItemPresentOnCustomRawItemDeatilTable(GlobalVariable.addAnItemWrin1)) {
			Reporter.reportPassResult(
					browser, "Sprint8_US539_TC1635",
					"User should be able to save Wrin in custom list", "Pass");

		} else {
			Reporter.reportTestFailure(
					browser, "Sprint8_US539_TC1635","Sprint8_US539_TC1635",
					"User should be able to save Wrin in custom list", "Fail");
			AbstractTest.takeSnapShot("Sprint8_US539_TC1635");
		}
	}
	
	//TC1636 Verify the  user has the ability to remove raw items from the list.
	@Test()
	public void Sprint8_US539_TC16362() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	{
		/* Start-Variable Deceleration */
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String rawItemListName = "Test" + Base.generateNdigitRandomNumber(3);
		/* End */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Go to Custom raw Item list page
		CustomRawItemListsPage customRawItemListsPage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToCustomRawItemListsPage();
		// Code to create new Custom List
		customRawItemListsPage.createACustomRawList(rawItemListName);
		// Add raw item in the custom list
		customRawItemListsPage.addAWrinInCustomList(rawItemListName,GlobalVariable.addAnItemWrin1);
		// click on the raw item list
		customRawItemListsPage.clickOnARawItemList(rawItemListName);
		customRawItemListsPage.deleteRawItemFromCustomList(GlobalVariable.addAnItemWrin1);
		if (!customRawItemListsPage.isRawItemPresentOnCustomRawItemDeatilTable(GlobalVariable.addAnItemWrin1)) {
			Reporter.reportPassResult(
					browser,"Sprint8_US539_TC1636",
					"Wrin ID should be deleted and should be removed for the table",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint8_US539_TC1636","Sprint8_US539_TC1635",
					"Wrin ID should be deleted and should be removed for the table",
					"Fail");
			AbstractTest.takeSnapShot("Sprint8_US539_TC1635");
		}
	}

	// TC1636 Verify the user has the ability to remove raw items from the list.
	@Test()
	public void Sprint8_US539_TC1636() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/* Start-Variable Deceleration */
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String rawItemListName = "Test" + Base.generateNdigitRandomNumber(3);
		/* End */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Go to Custom raw Item list page
		CustomRawItemListsPage customRawItemListsPage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToCustomRawItemListsPage();
		// Verify that is any custom list present or not
		if (driver.findElements(By.xpath("//table[@id='custom_raw_items_lists']/tbody/tr")).size() >= 1) {
			// click on the first custom list name
			customRawItemListsPage.CustomRawItemsLists_Table_Name_List.get(0).click();
			// Wait for the details page to be opened
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='raw_list_table']")));
			// Verify that Is any WRIN Present or not
			if (driver.findElements(By.xpath("//td[text()='No data available in table']")).size() == 1) {
				// No WRIN is present; Add a WRIN in the item
				customRawItemListsPage.addAWrinInCustomListRawItemTable(GlobalVariable.addAnItemWrin1);
			} else {
				// WRIN is present ; DO Nothing
			}
			// click on delete button for the first wrin
			String wrinId = customRawItemListsPage.CustomRawItemDetail_Table_WRIN_List.get(0).getText().trim();
			customRawItemListsPage.deleteRawItemFromCustomList(wrinId);
			if (!customRawItemListsPage.isRawItemPresentOnCustomRawItemDeatilTable(wrinId)) {
				Reporter.reportPassResult(
						browser,"Sprint8_US539_TC1636",
						"Wrin ID should be deleted and should be removed for the table",
						"Pass");
			} else {
				Reporter.reportTestFailure(
						browser,"Sprint8_US539_TC1636","Sprint8_US539_TC1635",
						"Wrin ID should be deleted and should be removed for the table",
						"Fail");
				AbstractTest.takeSnapShot("Sprint8_US539_TC1635");
			}
		} else {
			// Code to create new Custom List
			customRawItemListsPage.createACustomRawList(rawItemListName);
			// Add raw item in the custom list
			customRawItemListsPage.addAWrinInCustomList(rawItemListName,GlobalVariable.addAnItemWrin1);
			// click on the raw item list
			customRawItemListsPage.clickOnARawItemList(rawItemListName);
			// Delete the raw item from the list
			customRawItemListsPage.deleteRawItemFromCustomList(GlobalVariable.addAnItemWrin1);
			// Verify that raw item is deleted from the custom list
			if (!customRawItemListsPage.isRawItemPresentOnCustomRawItemDeatilTable(GlobalVariable.addAnItemWrin1)) {
				Reporter.reportPassResult(
						browser,"Sprint8_US539_TC1636",
						"Wrin ID should be deleted and should be removed for the table",
						"Pass");
			} else {
				Reporter.reportTestFailure(
						browser,"Sprint8_US539_TC1636","Sprint8_US539_TC1635",
						"Wrin ID should be deleted and should be removed for the table",
						"Fail");
				AbstractTest.takeSnapShot("Sprint8_US539_TC1635");
			}
		}
	}
	
	//TC1699 Verify the user has the ability to save the list.
	@Test()
	public void Sprint8_US539_TC1699() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/* Start-Variable Deceleration */
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String rawItemListName = "Test" + Base.generateNdigitRandomNumber(3);
		/* End */
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Go to Custom raw Item list page
		CustomRawItemListsPage customRawItemListsPage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToCustomRawItemListsPage();
		// Verify that is any custom list present or not
		if (driver.findElements(By.xpath("//table[@id='custom_raw_items_lists']/tbody/tr")).size() >= 1) {
			// click on the first custom list name
			customRawItemListsPage.CustomRawItemsLists_Table_Name_List.get(0).click();
			// Wait for the details page to be opened
			wait.until(ExpectedConditions.visibilityOfElementLocated(By	.xpath(".//*[@id='raw_list_table']")));
			Thread.sleep(3000);
			// Verify that Is any WRIN Present or not
			if (driver.findElements(By.xpath("//td[text()='No data available in table']")).size() == 1) {
				// No WRIN is present
				// Add a WRIN in the item
				customRawItemListsPage.addAWrinInCustomListRawItemTable(GlobalVariable.addAnItemWrin1);
			} else {
				// WRIN is present
			}
			// Click on the Save list button
			customRawItemListsPage.CustomRawItemDetail_SaveList_BT.click();
			if (Base.isElementDisplayed(customRawItemListsPage.ChangesSaved_Confirmation_MSG)) {
				Reporter.reportPassResult(
						browser, "Sprint8_US539_TC1699",
						"Confirmation message should display", "Pass");

			} else {
				Reporter.reportTestFailure(
						browser, "Sprint8_US539_TC1699",
						"Sprint8_US539_TC1699",
						"Confirmation message should display", "Fail");
				AbstractTest.takeSnapShot("Sprint8_US539_TC1699");
			}
		} else {
			// Code to create new Custom List
			customRawItemListsPage.createACustomRawList(rawItemListName);
			// click on the first custom list name
			customRawItemListsPage.CustomRawItemsLists_Table_Name_List.get(0).click();
			// Wait for the details page to be opened
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='raw_list_table']")));
			Thread.sleep(3000);
			// Verify that Is any WRIN Present or not
			if (driver.findElements(By.xpath("//td[text()='No data available in table']")).size() == 1) {
				// No WRIN is present
				// Add a WRIN in the item
				customRawItemListsPage.addAWrinInCustomListRawItemTable(GlobalVariable.addAnItemWrin1);
			} else {
				// WRIN is present
			}
			// Click on the Save list button
			customRawItemListsPage.CustomRawItemDetail_SaveList_BT.click();
			if (Base.isElementDisplayed(customRawItemListsPage.ChangesSaved_Confirmation_MSG)) {
				Reporter.reportPassResult(
						browser, "Sprint8_US539_TC1699",
						"Confirmation message should display", "Pass");

			} else {
				Reporter.reportTestFailure(
						browser, "Sprint8_US539_TC1699","Sprint8_US539_TC1699",
						"Confirmation message should display", "Fail");
				AbstractTest.takeSnapShot("Sprint8_US539_TC1699");
			}
		}
	}
}
