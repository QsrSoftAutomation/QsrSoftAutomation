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
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.MenuItemInformationPage;
import sprint2.AbstractTest;

public class US898_MenuItemInformationRefineThePageAndAttributeFields extends AbstractTest
{
	// TC1536 Verify that under historical recipe section a start and end date and time should be visible to user.
	@Test()
	public void Sprint8_US898_TC1536() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		String menuItem = "1001";
		String storeId = GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		MenuItemInformationPage menuItemInformationPage = homePage.selectUser(userId).selectLocation(storeId)
				.navigateToInventoryManagement().goToMenuItemInformationPage().searchAndSelectMenuItem(menuItem);
		int startDate = driver.findElement(By.xpath("(//table[@class='display nowrap'])[2]//tr[2]/td[4]")).getText().length();
		int endDate = driver.findElement(By.xpath("(//table[@class='display nowrap'])[2]//tr[2]/td[5]")).getText().length();
		System.out.println("startDate" + startDate);
		System.out.println("enddate" + endDate);
		System.out.println("text is"+ driver.findElement(By.xpath("(//table[@class='display nowrap'])[2]//tr[2]/td[5]")).getAttribute("value"));
		// Expand the Historical Information
		menuItemInformationPage.HistoricRecipe_BT.click();
		wait.until(ExpectedConditions.visibilityOf(menuItemInformationPage.HistoricalRecipe_EffectiveEndDateTime_Label));
		Thread.sleep(3000);
		// Verify that User should be able to view the start date,end date and time under "historical recipe" field on the Menu Item Information page.
		if (Base.isElementDisplayed(menuItemInformationPage.HistoricalRecipe_EffectiveStartDateTime_Label)
				&& Base.isElementDisplayed(menuItemInformationPage.HistoricalRecipe_EffectiveEndDateTime_Label)
				&& startDate >= 1 && endDate >= 1) {
			Reporter.reportPassResult(
					browser,"Sprint8_US898_TC1536",
					"User should be able to view start and End Time under historical Rece=ipe",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"Sprint8_US898_TC1536","Sprint8_US898_TC1536",
					"User should be able to view start and End Time under historical Rece=ipe",
					"Fail");
			AbstractTest.takeSnapShot("Sprint8_US898_TC1536");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
