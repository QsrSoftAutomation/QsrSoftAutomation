package sprint5;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import common.Base;
import common.GlobalVariable;
import common.ReadTestData;
import common.Reporter;
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.RawItemActivityPage;
import eInventoryPageClasses.TransferLandingPage;
import sprint2.AbstractTest;

public class US290_RawItemDetailPage extends AbstractTest{

	//Verify that user is able to Navigate to 'Raw Item Activity' Page
	@Test()

	public void Sprint5_US290_TC859() throws RowsExceededException, BiffException, WriteException, IOException, InterruptedException
	{
		/*Start-Variable Deceleration*/
		String storeId=GlobalVariable.StoreId;
		String userId=GlobalVariable.userId;
		RawItemActivityPage rawitemactivitypage=null;
		/*End-Variable Deceleration*/
		
		//Enter URL,select a store & click on 'Raw Item Activity' Link
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		rawitemactivitypage= homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToRawItemActivityPage();
		//verify user is navigated to 'Raw Item Activity' Page
		boolean result=rawitemactivitypage.RawItemActivity_Label.isDisplayed();
		if(result)
		{
			Reporter.reportPassResult(browser, "Sprint5_US290_TC859", "user must be navigated to raw item activity page", "Pass");
		}
		else
		{
			Reporter.reportTestFailure(browser, "Sprint5_US290_TC859", "Sprint5_US290_TC859", "user must be navigated to raw item activity page", "Fail");
			AbstractTest.takeSnapShot("Sprint5_US290_TC859");

		}	
	}
	//Verify that user is able to search and select one raw item
	@Test()

	public void Sprint5_US290_TC861() throws RowsExceededException, BiffException, WriteException, IOException, InterruptedException
	{
		/*Start-Variable Deceleration*/
		HSSFSheet rawItemActivitySheet = ReadTestData.getTestDataSheet("Sprint5_US290_TC861", "Object1");
		String wrinId = ReadTestData.getTestData(rawItemActivitySheet, "WRINId");
		String storeId=GlobalVariable.StoreId;
		String userId=GlobalVariable.userId;
		RawItemActivityPage rawitemactivitypage=null;

		/*End-Variable Deceleration*/
		//Enter url,select a store & click on 'Raw Item Activity' Link
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		rawitemactivitypage=homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToRawItemActivityPage();
		//verify user is able to search & select one raw item by entering wRIN id/description in search box.
		rawitemactivitypage.Search_TB.sendKeys(wrinId);
		driver.findElement(By.xpath("//strong[text()="+wrinId+"]")).click();
		if(rawitemactivitypage.RawItemActivity_Header.getText().trim().contains(wrinId))
		{
			Reporter.reportPassResult(browser, "Sprint5_US290_TC861", "user should be able to search & select one raw item", "Pass");
		}
		else
		{
			Reporter.reportTestFailure(browser, "Sprint5_US290_TC861", "Sprint5_US290_TC861", "user should be able to search & select one raw item", "Fail");
			AbstractTest.takeSnapShot("Sprint5_US290_TC861,condition_1");

		}	
		//verify date range
		boolean startdatestatus=rawitemactivitypage.RawItemActivityPage_StartDate_TB.isDisplayed();
		boolean enddatestatus=rawitemactivitypage.RawItemActivityPage_EndDate_TB.isDisplayed();
		if(startdatestatus && enddatestatus)
		{
			Reporter.reportPassResult(browser, "Sprint5_US290_TC861", "user should be able to select 'start' & 'end' date", "Pass");
		}
		else
		{
			Reporter.reportTestFailure(browser, "Sprint5_US290_TC861", "Sprint5_US290_TC861", "user should be able to select 'start' & 'end' date", "Fail");
			AbstractTest.takeSnapShot("Sprint5_US290_TC861,condition_2");

		}	

	}

	//Verify user is able to select a date range after selecting a wRIN ID on 'Raw iTEM ACTIVITY' Page.
	@Test ()

	public void Sprint5_US290_TC862() throws RowsExceededException, BiffException, WriteException, IOException, InterruptedException
	{
		/*Start-Variable Deceleration*/
		HSSFSheet rawItemActivitySheet = ReadTestData.getTestDataSheet("Sprint5_US290_TC862", "Object1");
		String wrinId = ReadTestData.getTestData(rawItemActivitySheet, "WRINId");
		String storeId=GlobalVariable.StoreId;
		String userId=GlobalVariable.userId;
		RawItemActivityPage rawitemactivitypage=null;

		/*End-Variable Deceleration*/
		//Enter url,select a store & click on 'Raw Item Activity' Link
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		rawitemactivitypage=homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToRawItemActivityPage();
		//Enter a sample wRIN id in search box & select the Item.
		rawitemactivitypage.searchAndSelectWRINID(wrinId);
		//verify default 'end' date
		rawitemactivitypage.RawItemActivityPage_EndDate_TB.click();
		String enddate=driver.findElement(By.xpath("//button[@id='end_calendar_btn']")).getAttribute("value");
		System.out.println(enddate);
		//find the current system date of system
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		//get current date time with Date()
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		//verify default 'start 'date
		rawitemactivitypage.RawItemActivityPage_StartDate_TB.click();
		String startdate=driver.findElement(By.xpath("//button[@id='start_calendar_btn']")).getAttribute("value");
		String ddc[]=dateFormat.format(date).split("/");
		ddc[1]="01";
		String newdate=ddc[0]+"/"+ddc[1]+"/"+ddc[2];
		if(startdate.equalsIgnoreCase(newdate) && enddate.equalsIgnoreCase(dateFormat.format(date)))
		{
			Reporter.reportPassResult(browser, "Sprint5_US290_TC862", "correct 'start' & 'end ' date should be displayed" , "Pass");
		}
		else
		{
			Reporter.reportTestFailure(browser, "Sprint5_US290_TC862", "Sprint5_US290_TC862", "correct 'start' & 'end ' date should be displayed", "Fail");
			AbstractTest.takeSnapShot("Sprint5_US290_TC862");
		}

	}
	//verify user is able to view all the information for selected WRIN ID on "Raw Item Activity" page.
	@Test()

	public void Sprint5_US290_TC863() throws RowsExceededException, BiffException, WriteException, IOException, InterruptedException
	{
		/*Start-Variable Deceleration*/
		HSSFSheet rawItemActivitySheet = ReadTestData.getTestDataSheet("Sprint5_US290_TC863", "Object1");
		String wrinId = ReadTestData.getTestData(rawItemActivitySheet, "WRINId");
		String storeId=GlobalVariable.StoreId;
		String userId=GlobalVariable.userId;
		RawItemActivityPage rawitemactivitypage=null;
		String systemDateAndTime;
		String userEnteredDateAndTime;
		/*End-Variable Deceleration*/
		//Enter url,select a store & click on 'Raw Item Activity' Link
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		rawitemactivitypage=homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToRawItemActivityPage();
		//verify user is navigated to 'Raw Item Activity' Page
		boolean result=rawitemactivitypage.RawItemActivity_Label.isDisplayed();
		if(result)
		{
			Reporter.reportPassResult(browser, "Sprint5_US290_TC863", "user should be navigated to raw item activity page", "Pass");
		}
		else
		{
			Reporter.reportTestFailure(browser, "Sprint5_US290_TC863_condition1", "Sprint5_US290_TC863", "user should be navigated to raw item activity page", "Fail");
			AbstractTest.takeSnapShot("Sprint5_US290_TC863_condition1");

		}		
		//Enter a sample wRIN id in search box & select the Item.
		rawitemactivitypage.searchAndSelectWRINID(wrinId);
		//click on 'get item details' button  
		driver.manage().timeouts().implicitlyWait(05, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@id='get_item_details_btn']")).click();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//verify user is able to view all the Information for the selected wRIN ID.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//u[text()='System Date and Time']")));
		int size=driver.findElements(By.xpath("//table[@id='raw_item_detail_table']/tbody/tr")).size();
		
		for(int i=1;i<=size;i++)
		{
			//Verify the system date and time for each of the records
			systemDateAndTime=driver.findElement(By.xpath("//table[@id='raw_item_detail_table']/tbody/tr["+i+"]/td[1]/span")).getText().trim();
			if(systemDateAndTime.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})\\s([0-9]{2}):([0-9]{2})"))
					{
					Reporter.reportPassResult(browser, "Sprint5_US290_TC863", "System date and time for"+i+" record should display in correct format for each record", "Pass");
					}
			else
			{
				Reporter.reportTestFailure(browser, "Sprint5_US290_TC863_condition2", "Sprint5_US290_TC863", "System date and time for "+i+" record should display in correct format for each record", "Fail");
				AbstractTest.takeSnapShot("Sprint5_US290_TC863_condition2");
			}
		}
		
		
		for(int i=1;i<=size;i++)
		{
			//Verify the User entered date and time for each of the records
			userEnteredDateAndTime=driver.findElement(By.xpath("//table[@id='raw_item_detail_table']/tbody/tr["+i+"]/td[2]/span")).getText().trim();
			if(userEnteredDateAndTime.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})\\s([0-9]{2}):([0-9]{2})"))
					{
					Reporter.reportPassResult(browser, "Sprint5_US290_TC863", "User entered date and time for "+i+" record should display in correct format for each record", "Pass");
					}
			else
			{
				Reporter.reportTestFailure(browser, "Sprint5_US290_TC863_condition3", "Sprint5_US290_TC863", "User entered date and time for "+i+" record should display in correct format for each record", "Fail");
				AbstractTest.takeSnapShot("Sprint5_US290_TC863_condition3");
			}
		}
		
		
		for(int i=1;i<=size;i++)
		{
			//Verify the i symbol displaying  for each of the records
			if(driver.findElement(By.xpath("//table[@id='raw_item_detail_table']/tbody/tr["+i+"]/td[6]/button/i")).isDisplayed())
					{
					Reporter.reportPassResult(browser, "Sprint5_US290_TC863", "'i' symmbol for "+i+" record should display", "Pass");
					}
			else
			{
				Reporter.reportTestFailure(browser, "Sprint5_US290_TC863_condition4", "Sprint5_US290_TC863", "'i' symmbol for "+i+" record should display", "Fail");
				AbstractTest.takeSnapShot("Sprint5_US290_TC863_condition4");
			}
		}
		
		for(int i=1;i<=size;i++)
		{
			//Verify that message is displaying for each records
			if((driver.findElement(By.xpath("//table[@id='raw_item_detail_table']/tbody/tr["+i+"]/td[7]/span")).getText().trim().length())!=0)
					{
					Reporter.reportPassResult(browser, "Sprint5_US290_TC863", "message for "+i+" record should display", "Pass");
					}
			else
			{
				Reporter.reportTestFailure(browser, "Sprint5_US290_TC863_condition5", "Sprint5_US290_TC863", "message for "+i+" record should display", "Fail");
				AbstractTest.takeSnapShot("Sprint5_US290_TC863_condition5");
			}
		}
				

	}
	
//TC1142 Validate "Transfer Out" on "Raw Item Activity" page.
	@Test()
	public void Sprint5_US290_TC1142() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	{
		
		/*Start-Variable Deceleration*/
		HSSFSheet transferLandingPageSheet = ReadTestData.getTestDataSheet("Sprint5_US290_TC1142", "Object1");
		String wrinId = ReadTestData.getTestData(transferLandingPageSheet, "WRINId");
		String nationalStoreNumber = ReadTestData.getTestData(transferLandingPageSheet, "InputNationalStoreNumber");
		String outerPack = ReadTestData.getTestData(transferLandingPageSheet, "OuterPackQty");
		String innerPack = ReadTestData.getTestData(transferLandingPageSheet, "InnerPackQty");
		String looseUnits = ReadTestData.getTestData(transferLandingPageSheet, "LooseUnitsQty");
		String storeId=GlobalVariable.StoreId;
		String userId=GlobalVariable.userId;
		String type="Out";
		TransferLandingPage transferLandingPage;
		RawItemActivityPage rawItemActivityPage=null;
		String time;
		String date=Base.returnTodayDate();
		/*End-Variable Deceleration*/
		
		// Go to transfer landing page and create a transfer out
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		transferLandingPage=homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToTransferLandingPage();
		//fetch the time
		transferLandingPage.CreateNewTransfers_BT.click();
		wait.until(ExpectedConditions.visibilityOf(transferLandingPage.InsertNewTransfersPopup_Time_Value));
		time=transferLandingPage.InsertNewTransfersPopup_Time_Value.getText().trim();
		transferLandingPage.InsertNewTransfersPopup_Cancel_BT.click();
		wait.until(ExpectedConditions.visibilityOf(transferLandingPage.CanceTransferPopup_Yes_BT));
		Thread.sleep(2000);
		transferLandingPage.CanceTransferPopup_Yes_BT.click();
		//create a transfer out transaction
		transferLandingPage.createATransferTransaction(wrinId, nationalStoreNumber, type, outerPack, innerPack, looseUnits);
		Thread.sleep(3000);
		//Go to raw item activity page
		homePage.Menu_DD_BT.click();
		wait.until(ExpectedConditions.visibilityOf(homePage.RawItemActivity_BT));
		Thread.sleep(3000);
		//click on the raw item activity button
		homePage.RawItemActivity_BT.click();
		rawItemActivityPage=PageFactory.initElements(driver, RawItemActivityPage.class);
		wait.until(ExpectedConditions.visibilityOf(rawItemActivityPage.RawItemActivity_Label));
		//Search and select the wrin ID
		rawItemActivityPage.searchAndSelectWRINID(wrinId);
		wait.until(ExpectedConditions.visibilityOf(rawItemActivityPage.getItemDetails_Button));
		//click on 'get item details' button  
		rawItemActivityPage.getItemDetails_Button.click();
		//verify user is able to view all the Information for the selected wRIN ID.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//u[text()='System Date and Time']")));
		if(driver.findElement(By.xpath("//table[@id='raw_item_detail_table']/tbody/tr[1]/td[1]/span[text()='"+date+" "+time+"']")).isDisplayed() && 
				(driver.findElement(By.xpath("//table[@id='raw_item_detail_table']/tbody/tr[1]/td[3][preceding-sibling::td/span[text()='"+date+" "+time+"']]/span")).getText().trim().equalsIgnoreCase("Transfer Out")))
		{
			Reporter.reportPassResult(browser, "Sprint5_US290_TC1142", "transfer out entry should display", "Pass");
		}
		else
		{
			Reporter.reportTestFailure(browser, "Sprint5_US290_TC1142", "Sprint5_US290_TC1142", "transfer out entry should display", "Fail");
			AbstractTest.takeSnapShot("Sprint5_US290_TC1142");
		}
	}
	
	
	//TC1154 Validate "Transfer In" on "Raw Item Activity" page.
	@Test()
	public void Sprint5_US290_TC1154() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	{
		/*Start-Variable Deceleration*/
		HSSFSheet transferLandingPageSheet = ReadTestData.getTestDataSheet("Sprint5_US290_TC1154", "Object1");
		String wrinId = ReadTestData.getTestData(transferLandingPageSheet, "WRINId");
		String nationalStoreNumber = ReadTestData.getTestData(transferLandingPageSheet, "InputNationalStoreNumber");
		String outerPack = ReadTestData.getTestData(transferLandingPageSheet, "OuterPackQty");
		String innerPack = ReadTestData.getTestData(transferLandingPageSheet, "InnerPackQty");
		String looseUnits = ReadTestData.getTestData(transferLandingPageSheet, "LooseUnitsQty");
		String storeId=GlobalVariable.StoreId;
		String userId=GlobalVariable.userId;
		String type="In";
		TransferLandingPage transferLandingPage;
		RawItemActivityPage rawItemActivityPage=null;
		String time;
		String date=Base.returnTodayDate();
		/*End-Variable Deceleration*/
		
		// Go to transfer landing page and create a transfer out
		HomePage homePage=PageFactory.initElements(driver, HomePage.class);
		transferLandingPage=homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement().goToTransferLandingPage();
		//fetch the time
		transferLandingPage.CreateNewTransfers_BT.click();
		wait.until(ExpectedConditions.visibilityOf(transferLandingPage.InsertNewTransfersPopup_Time_Value));
		time=transferLandingPage.InsertNewTransfersPopup_Time_Value.getText().trim();
		transferLandingPage.InsertNewTransfersPopup_Cancel_BT.click();
		wait.until(ExpectedConditions.visibilityOf(transferLandingPage.CanceTransferPopup_Yes_BT));
		Thread.sleep(2000);
		transferLandingPage.CanceTransferPopup_Yes_BT.click();
		//create a transfer in transaction
		transferLandingPage.createATransferTransaction(wrinId, nationalStoreNumber, type, outerPack, innerPack, looseUnits);
		Thread.sleep(3000);
		//Go to raw item activity page
		homePage.Menu_DD_BT.click();
		wait.until(ExpectedConditions.visibilityOf(homePage.RawItemActivity_BT));
		Thread.sleep(3000);
		//click on the raw item activity button
		homePage.RawItemActivity_BT.click();
		rawItemActivityPage=PageFactory.initElements(driver, RawItemActivityPage.class);
		wait.until(ExpectedConditions.visibilityOf(rawItemActivityPage.RawItemActivity_Label));
		//Search and select the wrin ID
		rawItemActivityPage.searchAndSelectWRINID(wrinId);
		wait.until(ExpectedConditions.visibilityOf(rawItemActivityPage.getItemDetails_Button));
		//click on 'get item details' button  
		rawItemActivityPage.getItemDetails_Button.click();
		//verify user is able to view all the Information for the selected wRIN ID.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//u[text()='System Date and Time']")));
		if(driver.findElement(By.xpath("//table[@id='raw_item_detail_table']/tbody/tr[1]/td[1]/span[text()='"+date+" "+time+"']")).isDisplayed() && 
				(driver.findElement(By.xpath("//table[@id='raw_item_detail_table']/tbody/tr[1]/td[3][preceding-sibling::td/span[text()='"+date+" "+time+"']]/span")).getText().trim().equalsIgnoreCase("Transfer In")))
		{
			Reporter.reportPassResult(browser, "Sprint5_US290_TC1154", "transfer In entry should display", "Pass");
		}
		else
		{
			Reporter.reportTestFailure(browser, "Sprint5_US290_TC1154", "Sprint5_US290_TC1154", "transfer In entry should display", "Fail");
			AbstractTest.takeSnapShot("Sprint5_US290_TC1154");
		}
		
	}
}



