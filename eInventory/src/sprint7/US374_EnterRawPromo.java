package sprint7;

import java.math.BigDecimal;
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
import eInventoryPageClasses.RawItemPromoPage;






public class US374_EnterRawPromo extends AbstractTest
{
	
	
	
	/*'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	//TC1751 Verify that the user should be able to initiate a Raw Promo entry.
	
	@Test()
	
	public void Sprint7_US374_TC1751() throws Exception
	{
		String storeId=GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String outerPack="3";
		String innerPack="2";
		String looseUnits="8";
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String todayDate=dateFormat.format(date);
		//Go to the Promotion and Waste page
		homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPromotionsAndWastePage();
		Thread.sleep(3000);
		int recordCountBeforeAdd=driver.findElements(By.xpath("//table[@id='waste_history_table']/tbody/tr")).size();
		PromotionsAndWastePage promotionsAndWastePage=PageFactory.initElements(driver, PromotionsAndWastePage.class);
		RawItemPromoPage rawItemPromoPage=PageFactory.initElements(driver, RawItemPromoPage.class);
		//click on Promo tab
		promotionsAndWastePage.RawPromo_BT.click();
		wait.until(ExpectedConditions.visibilityOf(rawItemPromoPage.EnterNewRawPromo_Title));
		rawItemPromoPage.addARawPromoItem(GlobalVariable.rawItemPromowrin1, innerPack, outerPack, looseUnits);
		rawItemPromoPage.addASecondPromoItem(GlobalVariable.rawItemPromowrin2, "", outerPack, looseUnits);
		Thread.sleep(3000);
		String amount1=driver.findElement(By.xpath("//tr[@id='wasteItem0']/td[7]")).getText().replace("$", "");
		String amount2=driver.findElement(By.xpath("//tr[@id='wasteItem1']/td[7]")).getText().replace("$","");
		BigDecimal amountOfFirstItem = new BigDecimal(amount1);
		BigDecimal amountOfSecondItem = new BigDecimal(amount2);
		BigDecimal totalAmount1=amountOfFirstItem.add(amountOfSecondItem);
		String totalAmount=String.valueOf(totalAmount1);
		//Click on Back to Top button
		driver.findElement(By.xpath("//div[@id='back-to-top']")).click();
		Thread.sleep(2000);
		rawItemPromoPage.SubmitEntry_BT.click();
		wait.until(ExpectedConditions.visibilityOf(promotionsAndWastePage.PromotionAndWaste_Title));
		Thread.sleep(5000);
		int recordCountAfterAdd=driver.findElements(By.xpath("//table[@id='waste_history_table']/tbody/tr")).size();
		promotionsAndWastePage.Record_Expend_Button_List.get(0).click();
		Thread.sleep(3000);
		boolean result=promotionsAndWastePage.isRawPromoEntryPresent(todayDate, totalAmount);
		if(result && (recordCountAfterAdd==recordCountBeforeAdd+1))
		{
			Reporter.reportPassResult(browser, "Sprint7_US374_TC1751", "Raw waste entry should display on Promotion and Waste page", "Pass");

		}
		else
		{
			Reporter.reportTestFailure(browser, "Sprint7_US374_TC1751", "Sprint7_US374_TC1751", "Raw waste entry should display on Promotion and Waste page", "Fail");
			AbstractTest.takeSnapShot("Sprint7_US374_TC1751");
		}
		
		
		
	}
	
	/*'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	//TC1752 Verify that the user should be able to to close the Raw Promo entry.
	
	@Test()
	
	public void Sprint7_US374_TC1752() throws Exception
	{
		String storeId=GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String outerPack="1";
		String innerPack="2";
		String looseUnits="10";
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		//Go to the Promotion and Waste page

		homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToPromotionsAndWastePage();
		PromotionsAndWastePage promotionsAndWastePage=PageFactory.initElements(driver, PromotionsAndWastePage.class);

		RawItemPromoPage rawItemPromoPage=PageFactory.initElements(driver, RawItemPromoPage.class);
		//click on Promo tab
		promotionsAndWastePage.RawPromo_BT.click();
		wait.until(ExpectedConditions.visibilityOf(rawItemPromoPage.EnterNewRawPromo_Title));
		rawItemPromoPage.addARawPromoItem(GlobalVariable.rawItemPromowrin1, innerPack, outerPack, looseUnits);
		//Click on Back to Top button
		driver.findElement(By.xpath("//div[@id='back-to-top']")).click();
		Thread.sleep(2000);
		rawItemPromoPage.CancelEntry_BT.click();
		wait.until(ExpectedConditions.visibilityOf(rawItemPromoPage.RawPromoEntryIncomplete_PopUp_Window));
		//Click on Cancel button on Raw Promo entry incomplete pop up window
		rawItemPromoPage.RawPromoEntryIncomplete_PopUp_NO_BT.click();
		wait.until(ExpectedConditions.visibilityOf(rawItemPromoPage.EnterNewRawPromo_Title));
		if(rawItemPromoPage.EnterNewRawPromo_Title.isDisplayed())
		{
			Reporter.reportPassResult(browser, "Sprint2_US374_TC1752", "User should be landed to the Raw waste detail page", "Pass");
		}
		else
		{
			Reporter.reportTestFailure(browser,"Sprint2_US374_TC1752_Condition1", "Sprint2_US374_TC1752", "User should be landed to the Raw waste detail page", "Fail");
			AbstractTest.takeSnapShot("Sprint2_US374_TC1752_Condition1");
		}
		
		//Again click on Cancel Entry button
		rawItemPromoPage.CancelEntry_BT.click();
		wait.until(ExpectedConditions.visibilityOf(rawItemPromoPage.RawPromoEntryIncomplete_PopUp_Window));
		//Click on Cancel button on Raw Promo entry incomplete pop up window
		rawItemPromoPage.RawPromoEntryIncomplete_PopUp_YES_BT.click();
		wait.until(ExpectedConditions.visibilityOf(promotionsAndWastePage.PromotionAndWaste_Title));
		if(promotionsAndWastePage.PromotionAndWaste_Title.isDisplayed())
		{
			Reporter.reportPassResult(browser, "Sprint2_US374_TC1752", "User should be landed to the Promotion and Waste landing page", "Pass");
		}
		else
		{
			Reporter.reportTestFailure(browser,"Sprint2_US374_TC1752_Condition2", "Sprint2_US374_TC1752", "User should be landed to the Promotion and Waste landing page", "Fail");
			AbstractTest.takeSnapShot("Sprint2_US374_TC1752_Condition2");
		}
		
	}
	
	/*'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	
	//TC1754 Verify that the user should be able to view the raw promo entry on the raw item activity page.
	@Test()
	
	public void Sprint7_US374_TC1754() throws Exception
	{
		String storeId=GlobalVariable.StoreId;
		String userId = GlobalVariable.userId;
		String outerPack="1";
		String innerPack="2";
		String looseUnits="10";
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		RawItemActivityPage rawItemActivityPage=PageFactory.initElements(driver, RawItemActivityPage.class);
		PromotionsAndWastePage promotionsAndWastePage=PageFactory.initElements(driver, PromotionsAndWastePage.class);
		RawItemPromoPage rawItemPromoPage=PageFactory.initElements(driver, RawItemPromoPage.class);
		//Go to Raw Item Activity page and do get item details and count number of records for waste type entries
		homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToRawItemActivityPage().searchAndSelectWRINID(GlobalVariable.rawItemActivityWrin).getItemDetails_Button.click();
		int sizebeforeWaste=driver.findElements(By.xpath("//table[@id='raw_item_detail_table']/tbody/tr/td[3]/span[text()='Promo']")).size();
		//Go to the Promotion and Waste page
		homePage.Menu_DD_BT.click();
		wait.until(ExpectedConditions.visibilityOf(homePage.Menu_OtherInventoryFunction_Back_BT));
		Thread.sleep(2000);
		homePage.Menu_OtherInventoryFunction_Back_BT.click();
		wait.until(ExpectedConditions.visibilityOf(homePage.PromotionAndWaste_BT));
		homePage.PromotionAndWaste_BT.click();
		wait.until(ExpectedConditions.visibilityOf(promotionsAndWastePage.PromotionAndWaste_Title));
		//click on Promo tab
		promotionsAndWastePage.RawPromo_BT.click();
		wait.until(ExpectedConditions.visibilityOf(rawItemPromoPage.EnterNewRawPromo_Title));
		rawItemPromoPage.addARawPromoItem(GlobalVariable.rawItemPromowrin1, innerPack, outerPack, looseUnits);
		//Click on Back to Top button
		driver.findElement(By.xpath("//div[@id='back-to-top']")).click();
		Thread.sleep(2000);
		rawItemPromoPage.SubmitEntry_BT.click();
		wait.until(ExpectedConditions.visibilityOf(promotionsAndWastePage.PromotionAndWaste_Title));
		Thread.sleep(5000);
		homePage.Menu_DD_BT.click();
		wait.until(ExpectedConditions.visibilityOf(homePage.OtherInventoryFunctions_BT));
		Thread.sleep(2000);
		homePage.OtherInventoryFunctions_BT.click();
		wait.until(ExpectedConditions.visibilityOf(homePage.RawItemActivity_BT));
		homePage.RawItemActivity_BT.click();
		wait.until(ExpectedConditions.visibilityOf(rawItemActivityPage.RawItemActivity_Label));
		rawItemActivityPage.searchAndSelectWRINID(GlobalVariable.rawItemPromowrin1).getItemDetails_Button.click();
		int sizeafterWaste=driver.findElements(By.xpath("//table[@id='raw_item_detail_table']/tbody/tr/td[3]/span[text()='Promo']")).size();
		if(sizeafterWaste==sizebeforeWaste+1)
		{
			Reporter.reportPassResult(browser, "Sprint2_US374_TC1754", "Raw Promo entry should display on Activity Page", "Pass");

		}
		else
		{
			Reporter.reportTestFailure(browser,"Sprint2_US374_TC1754", "Sprint2_US374_TC1754", "Raw Promo entry should display on Activity Page", "Fail");
			AbstractTest.takeSnapShot("Sprint2_US374_TC1754");
		}

	}

}
