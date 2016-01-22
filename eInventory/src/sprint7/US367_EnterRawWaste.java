package sprint7;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import sprint2.AbstractTest;
import common.GlobalVariable;
import common.Reporter;
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.PromotionsAndWastePage;
import eInventoryPageClasses.RawItemActivityPage;
import eInventoryPageClasses.RawItemWastePage;

public class US367_EnterRawWaste extends AbstractTest

{


	
	/*'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	//TC1746 Verify that the user should be able to initiate a Raw Waste entry.
	
	@Test()
	
	public void Sprint7_US367_TC1746() throws Exception
	{
		/*Start-Variable Deceleration*/
		String storeId=GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String wrinId01=GlobalVariable.rawItemWatsewrin1;
		String wrinId02=GlobalVariable.rawItemWastewrin2;
		String outerPack="3";
		String innerPack="3";
		String looseUnits="8";
		/*End-Variable Deceleration*/
		
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String todayDate=dateFormat.format(date);
		//Go to the Promotion and Waste page
		homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPromotionsAndWastePage();
		Thread.sleep(3000);
		int recordCountBeforeAdd=driver.findElements(By.xpath("//table[@id='waste_history_table']/tbody/tr")).size();
		PromotionsAndWastePage promotionsAndWastePage=PageFactory.initElements(driver, PromotionsAndWastePage.class);
		RawItemWastePage rawItemWastePage=PageFactory.initElements(driver, RawItemWastePage.class);
		//click on waste tab
		promotionsAndWastePage.RawWaste_BT.click();
		wait.until(ExpectedConditions.visibilityOf(rawItemWastePage.EnterNewRawWaste_Title));
		rawItemWastePage.addARawItem(wrinId01, innerPack, outerPack, looseUnits);
		Thread.sleep(3000);
		rawItemWastePage.addASecondRawItem(wrinId02, "", outerPack, looseUnits);
		Thread.sleep(3000);
		String amount1=driver.findElement(By.xpath("//tr[@id='wasteItem0']/td[7]")).getText().trim().replace("$", "");
		String amount2=driver.findElement(By.xpath("//tr[@id='wasteItem1']/td[7]")).getText().trim().replace("$","");
		float amountOfFirstItem=Float.parseFloat(amount1);
		float amountOfSecondItem=Float.parseFloat(amount2);
		float totalAmount1=amountOfFirstItem+amountOfSecondItem;
		String totalAmount=Float.toString(totalAmount1);
		//Click on Back to Top button
		driver.findElement(By.xpath("//div[@id='back-to-top']")).click();
		Thread.sleep(2000);
		rawItemWastePage.SubmitEntry_BT.click();
		wait.until(ExpectedConditions.visibilityOf(promotionsAndWastePage.PromotionAndWaste_Title));
		Thread.sleep(5000);
		int recordCountAfterAdd=driver.findElements(By.xpath("//table[@id='waste_history_table']/tbody/tr")).size();
		promotionsAndWastePage.Record_Expend_Button_List.get(0).click();
		Thread.sleep(3000);
		boolean result=promotionsAndWastePage.isRawWasteEntryPresent(todayDate, totalAmount);
		if(result && (recordCountAfterAdd==recordCountBeforeAdd+1))
		{
			Reporter.reportPassResult(browser, "Sprint7_US367_TC1746", "Raw waste entry should display on Promotion and Waste page", "Pass");

		}
		else
		{
			Reporter.reportTestFailure(browser, "Sprint7_US367_TC1746", "Sprint7_US367_TC1746", "Raw waste entry should display on Promotion and Waste page", "Fail");
			AbstractTest.takeSnapShot("Sprint7_US367_TC1746");
		}
		
		}	
	/*'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	//TC1748 Verify that the user should be able to to close the Raw Waste entry.
	
	@Test()
	
	public void Sprint7_US367_TC1748() throws Exception
	{
		/*Start-Variable Deceleration*/
		String storeId=GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String wrinId01=GlobalVariable.rawItemWatsewrin1;
		String outerPack="1";
		String innerPack="2";
		String looseUnits="10";
		/*End-Variable Deceleration*/

		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		//Go to the Promotion and Waste page
		
		homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPromotionsAndWastePage();
		PromotionsAndWastePage promotionsAndWastePage=PageFactory.initElements(driver, PromotionsAndWastePage.class);
		RawItemWastePage rawItemWastePage=PageFactory.initElements(driver, RawItemWastePage.class);
		//click on waste tab
		promotionsAndWastePage.RawWaste_BT.click();
		wait.until(ExpectedConditions.visibilityOf(rawItemWastePage.EnterNewRawWaste_Title));
		rawItemWastePage.addARawItem(wrinId01, innerPack, outerPack, looseUnits);
		//click on bck to top button
		driver.findElement(By.xpath("//div[@id='back-to-top']")).click();
		Thread.sleep(2000);
		rawItemWastePage.CancelEntry_BT.click();
		wait.until(ExpectedConditions.visibilityOf(rawItemWastePage.RawWasteEntryIncomplete_PopUp_Window));
		//Click on Cancel button on Raw waste entry incomplete pop up window
		rawItemWastePage.RawWasteEntryIncomplete_PopUp_NO_BT.click();
		wait.until(ExpectedConditions.visibilityOf(rawItemWastePage.EnterNewRawWaste_Title));
		if(rawItemWastePage.EnterNewRawWaste_Title.isDisplayed())
		{
			Reporter.reportPassResult(browser, "Sprint7_US367_TC1748", "User should be landed to the Raw waste detail page", "Pass");
		}
		else
		{
			Reporter.reportTestFailure(browser,"Sprint7_US367_TC1748_Condition1", "Sprint7_US367_TC1748", "User should be landed to the Raw waste detail page", "Fail");
			AbstractTest.takeSnapShot("Sprint7_US367_TC1748_Condition1");
		}
		
		//Again click on Cancel Entry button
		rawItemWastePage.CancelEntry_BT.click();
		wait.until(ExpectedConditions.visibilityOf(rawItemWastePage.RawWasteEntryIncomplete_PopUp_YES_BT));
		//Click on Cancel button on Raw waste entry incomplete pop up window
		rawItemWastePage.RawWasteEntryIncomplete_PopUp_YES_BT.click();
		wait.until(ExpectedConditions.visibilityOf(promotionsAndWastePage.PromotionAndWaste_Title));
		if(promotionsAndWastePage.PromotionAndWaste_Title.isDisplayed())
		{
			Reporter.reportPassResult(browser, "Sprint7_US367_TC1748", "User should be landed to the Promotion and Waste landing page", "Pass");
		}
		else
		{
			Reporter.reportTestFailure(browser,"Sprint7_US367_TC1748_Condition2", "Sprint7_US367_TC1748", "User should be landed to the Promotion and Waste landing page", "Fail");
			AbstractTest.takeSnapShot("Sprint7_US367_TC1748_Condition2");
		}
		
	}
	/*'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	// TC1750 Verify that the user should be able to view the raw waste entry on the raw item activity page.
	
	@Test()
	
	public void Sprint7_US367_TC1750() throws Exception
	{
		/*Start-Variable Deceleration*/
		String storeId=GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String wrinId01=GlobalVariable.rawItemWatsewrin1;
		String outerPack="1";
		String innerPack="2";
		String looseUnits="10";
		/*End-Variable Deceleration*/
		
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		PromotionsAndWastePage promotionsAndWastePage=PageFactory.initElements(driver, PromotionsAndWastePage.class);
		RawItemActivityPage rawItemActivityPage=PageFactory.initElements(driver, RawItemActivityPage.class);
		//Go to Raw Item Activity page and do get item details and count number of records for waste type entries
		homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToRawItemActivityPage().searchAndSelectWRINID(wrinId01).getItemDetails_Button.click();
		int sizebeforeWaste=driver.findElements(By.xpath("//table[@id='raw_item_detail_table']/tbody/tr/td[3]/span[text()='Waste']")).size();
		//Go to the Promotion and Waste page
		homePage.Menu_DD_BT.click();
		wait.until(ExpectedConditions.visibilityOf(homePage.Menu_OtherInventoryFunction_Back_BT));
		Thread.sleep(2000);
		homePage.Menu_OtherInventoryFunction_Back_BT.click();
		wait.until(ExpectedConditions.visibilityOf(homePage.PromotionAndWaste_BT));
		homePage.PromotionAndWaste_BT.click();
		wait.until(ExpectedConditions.visibilityOf(promotionsAndWastePage.PromotionAndWaste_Title));
		RawItemWastePage rawItemWastePage=PageFactory.initElements(driver, RawItemWastePage.class);
		//click on waste tab
		promotionsAndWastePage.RawWaste_BT.click();
		wait.until(ExpectedConditions.visibilityOf(rawItemWastePage.EnterNewRawWaste_Title));
		rawItemWastePage.addARawItem(wrinId01, innerPack, outerPack, looseUnits);
		//click on bck to top button
		driver.findElement(By.xpath("//div[@id='back-to-top']")).click();
		Thread.sleep(2000);
		rawItemWastePage.SubmitEntry_BT.click();
		wait.until(ExpectedConditions.visibilityOf(promotionsAndWastePage.PromotionAndWaste_Title));
		Thread.sleep(5000);
		//Go to Raw Item Activity page
		homePage.Menu_DD_BT.click();
		wait.until(ExpectedConditions.visibilityOf(homePage.OtherInventoryFunctions_BT));
		homePage.OtherInventoryFunctions_BT.click();
		wait.until(ExpectedConditions.visibilityOf(homePage.RawItemActivity_BT));
		homePage.RawItemActivity_BT.click();
		wait.until(ExpectedConditions.visibilityOf(rawItemActivityPage.RawItemActivity_Label));
		rawItemActivityPage.searchAndSelectWRINID(wrinId01).getItemDetails_Button.click();
		int sizeafterWaste=driver.findElements(By.xpath("//table[@id='raw_item_detail_table']/tbody/tr/td[3]/span[text()='Waste']")).size();
		if(sizeafterWaste==sizebeforeWaste+1)
		{
			Reporter.reportPassResult(browser, "Sprint7_US367_TC1750", "Raw waste entry should display on Activity Page", "Pass");

		}
		else
		{
			Reporter.reportTestFailure(browser,"Sprint7_US367_TC1750", "Sprint7_US367_TC1750", "Raw waste entry should display on Activity Page", "Fail");
			AbstractTest.takeSnapShot("Sprint7_US367_TC1750");
		}

	}
	
	
	

}
