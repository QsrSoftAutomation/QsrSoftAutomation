package eInventoryPageClasses;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import common.Base;

public class PhysicalInventoryPage  extends AbstractPage{

	public PhysicalInventoryPage(WebDriver driver) 
	{
		super(driver);
		PageFactory.initElements(driver, this);

	}
	@FindBy(xpath ="//input[@id='new_inventory_button']")
	public WebElement StartNewInventory_BT;
	
	@FindBy(xpath ="//table[@id='inventory_table']/tbody/tr[2]/td[6]")
	public WebElement TotalUnits_Column_Value;

	@FindBy(xpath ="//input[@id='start_new_inventory']")
	public WebElement StartInventory_BT;
	
	@FindBy(xpath="//input[@class='form-control table-search']")
	public WebElement CreateInventory_Search_TB;
	
	@FindBy(xpath="//div[@id='inventory_table_filter']/label/input")
	public WebElement ViewInventory_Search_TB;
	
	@FindBy(xpath="//select[@id='print_list']")
	public WebElement SelectABlankInventoryToPrint_DD;
	
	@FindBy(xpath="//select[@id='print_list']/option")
	public List <WebElement> SelectABlankInventoryToPrint_DD_List;
	
	@FindBy(xpath ="//input[@id='start_edit_inventory']")
	public WebElement EditInventory_BT;
	
	@FindBy(xpath ="//input[@id='add_new_item_button']")
	public WebElement AddNewItem_BT;
	
	@FindBy(xpath ="//input[@id='add_item_button']")
	public WebElement AddItem_BT;
	
	@FindBy(xpath="//input[@id='autocomplete_inventory']")
	public WebElement AddInventory_Search_TB;
	
	@FindBy(xpath ="//input[@id='save_button']")
	public WebElement Save_BT;
	
	@FindBy(xpath ="//input[@id='complete_button']")
	public WebElement Post_BT;
	
	@FindBy(xpath="//select[@id='list_type']")
	public WebElement SelectANewInventory_DD;
	
	@FindBy(xpath="//input[contains(@class,'table-search')]")
	public WebElement SearchItem_TB;
	
	@FindBy(xpath="//input[contains(@class,'case_count')]")
	public WebElement OuterPackQty_TB;
	
	@FindBy(xpath="//input[contains(@class,'loose_count')]")
	public WebElement LooseCountQty_TB;

	@FindBy(xpath="//span[contains(text(),'Time: ')]")
	public WebElement InventoryTime_Label;
	
	@FindBy(xpath="//input[@id='disp_time']")
	public WebElement SelectDateAndTimeForNewInventoryPopUp_Time_TB;
	
	@FindBy(xpath="//input[@id='cancel_new_inventory']")
	public WebElement SelectDateAndTimeForNewInventoryPopUp_Cancel_BT;
	
	@FindBy(xpath="//h3[contains(.,'Previous Inventories for Store Number')]")
	public WebElement PreviousInventoriesForStoreNumber_Title;
	
	@FindBy(xpath = "//th[text()='Outer Pack']")
	public WebElement InventoryTable_OuterPackColumn_Label;
	
	@FindBy(xpath = "//div[contains(text(),'Your Inventory has been saved.')]")
	public WebElement InventorySaved_Confirmation_MSG;
	
	@FindBy(xpath = "//th[text()='WRIN']")
	public WebElement InventoryTable_WRINColumn_Label;
	
	@FindBy(xpath = "//th[text()='Loose Unit']")
	public WebElement InventoryTable_LooseUnitColumn_Label;
	
	@FindBy(xpath = "//th[text()='Description']")
	public WebElement InventoryTable_DescriptionColumn_Label;
	
	@FindBy(xpath = "//th[text()='Inner Pack']")
	public WebElement InventoryTable_InnerPackColumn_Label;
	
	@FindBy(xpath = "//th[text()='Total Units']")
	public WebElement InventoryTable_TotalUnitsColumn_Label;
	
	@FindBy(xpath = "//th[text()='Audit']")
	public WebElement InventoryTable_AuditColumn_Label;
	
	@FindBy(xpath = "//input[@id='start_edit_inventory']")
	public WebElement AlreadyAnInventoryPopUp_ViewInventory_BT;
	
	@FindBy(xpath = "//input[@id='start_edit_inventory']")
	public WebElement AlreadyAnInventoryPopUp_Cancel_BT;
	
	@FindBy(xpath="//input[contains(@class,'inner_pack_count')]")
	public WebElement InnerPackQty_TB;
	
	@FindBy(xpath = "//table[@id='inventory_selection_table']//tr/td[@class='changed_count']/a")
	public List<WebElement> InventoryTable_AuditBtn_List;
	
	@FindBy(xpath = "//th[text()='Time Stamp']")
	public WebElement AuditDetailPopUp_TimeStamp_Label;
	
	@FindBy(xpath = "//th[text()='User']")
	public WebElement AuditDetailPopUp_User_Label;
	
	@FindBy(xpath = "//th[text()='Field Name']")
	public WebElement AuditDetailPopUp_FieldName_Label;
	
	@FindBy(xpath = "//th[text()='Before Value']")
	public WebElement AuditDetailPopUp_BeforeValue_Label;
	
	@FindBy(xpath = "//th[text()='After Value']")
	public WebElement AuditDetailPopUp_AfterValue_Label;
	
	@FindBy(xpath = "//b[@id='audit_title']")
	public WebElement AuditDetailForRawItemPopUp_Headet_Label;
	
	@FindBy(xpath = "//div[contains(text(),'Values must be numeric with up to 2 decimals. (Example: 12345.99)')]")
	public WebElement InvalidValue_MSG;
	
	@FindBy(xpath="//div[@id='app_content']/p[text()='Click a row to open inventory:']")
	public WebElement ClickRowToOpenInventory_Label;
	
	@FindBy(xpath = "//th[text()='Date']")
	public WebElement InventoryTable_DateColumn_Label;
	
	@FindBy(xpath = "//th[text()='Time']")
	public WebElement InventoryTable_TimeColumn_Label;
	
	@FindBy(xpath = "//th[text()='Last Saved']")
	public WebElement InventoryTable_LastSavedColumn_Label;
	
	@FindBy(xpath = "//th[text()='Created By']")
	public WebElement InventoryTable_CreatedByColumn_Label;
	
	@FindBy(id = "back-to-top")
	public WebElement BackToTop_BT;
	
	@FindBy(xpath = "//div[@id='cal']/div[contains(@class,'xdsoft_datetimepicker')]/div[@class='xdsoft_datepicker active']")
	public WebElement SelectDateAndTimeForNewInventoryPopUp_DatePicker;
	
	@FindBy(xpath = "//div[@id='cal']/div[contains(@class,'xdsoft_datetimepicker')]/div[@class='xdsoft_timepicker active']")
	public WebElement SelectDateAndTimeForNewInventoryPopUp_TimePicker;
	
	@FindBy(xpath = "//input[@id='disp_date']")
	public WebElement SelectDateAndTimeForNewInventoryPopUp_Date_TB;
	
	@FindBy(xpath = "//span[@id='last_saved_at']")
	public WebElement LastSavedAt_Label;
	
	@FindBy(xpath = "//input[contains(@class,'btn-sm close')]")
	public WebElement AuditDetailForRawItemPopUp_Close_BT;
	
	@FindBy(xpath = "//a[contains(.,'✓')]")
	public WebElement Audit_Column_Value;
	
	@FindBy(xpath = "//div[@class='xdsoft_timepicker active']/div/div[1]/div[contains(@class,'xdsoft_current')]")
	public WebElement SelectDateAndTimeForNewInventoryPopUp_SelectedTime;
	
	@FindBy(xpath = "//input[@id='missingItemsPost']")
	public WebElement PostInventoryPopUp_Finalize_BT;
	
	@FindBy(xpath = "//input[@id='pendPurchasesPost']")
	public WebElement PostInventoryWarning_Finalize_BT;
	
	@FindBy(xpath = "//input[@id='postInventoryOk']")
	public WebElement PostInventoryWarning_Post_BT;
	
	/*********New UI*************/
	
	@FindBy(xpath = "//h1[text()='Physical Inventory']")
	public WebElement PhysicalInventoryPage_Title;
	
	@FindBy(xpath = "//eb-button[@id='daily_inventory_create']/button[@value='Create']")
	public WebElement CreateDailyInventory_BT;
	
	@FindBy(xpath = "//eb-button[@id='weekly_inventory_create']/button[@value='Create']")
	public WebElement CreateWeeklyInventory_BT;
	
	@FindBy(xpath = "//eb-button[@id='monthly_inventory_create']/button[@value='Create']")
	public WebElement CreateMonthlyInventory_BT;
	
	

	// @Autor:Hemlata : This method will handle the alert on selecting a different inventory type
	public void acceptTheAlertMessage() throws InterruptedException {
		Thread.sleep(3000);
		Alert alert = driver.switchTo().alert();
		alert.accept();
		Thread.sleep(15000);
	}
	
	// @Autor:Hemlata : This method will search a wrin Id and save the outerPack QTY and Loose Unit Qty for that WRIN
	public void searchItemAndSaveInInventory(String WRINID,String outerPackQty,String innerPackQty, String looseCountQty) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(SearchItem_TB));
		SearchItem_TB.clear();
		SearchItem_TB.sendKeys(WRINID);
		OuterPackQty_TB.clear();
		OuterPackQty_TB.sendKeys(outerPackQty);
		try{
			InnerPackQty_TB.clear();
			InnerPackQty_TB.sendKeys(innerPackQty);
		}catch(Exception e){
			//Inner pack is not applicable
		}
		LooseCountQty_TB.clear();
		LooseCountQty_TB.sendKeys(looseCountQty);
		Save_BT.click();
		wait.until(ExpectedConditions.visibilityOf(InventorySaved_Confirmation_MSG));
		Thread.sleep(5000);
	}

	// @Autor:Hemlata : This method will click on the saved inventory on given date and time
	public void clickOnSavedInventory(String date, String time) {
		driver.findElement(By.xpath("//*[@id='inventory_selection_table']/tbody/tr/td[contains(text(),'"+ date+ "')]/following-sibling::td[contains(text(),'"+ time + "')]")).click();
		wait.until(ExpectedConditions.visibilityOf(SelectANewInventory_DD));
	}
	
	// @Autor:Hemlata : This method will verify that same prefix WRIN should be grouped together in the Print inventory Page
	public boolean verifySamePrefixWRINAreGroupedTogether(String prefix,String WRINID1, String WRINId2) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div/div[1][contains(text(),'" + WRINID1 + "')]")));
		try {
			String nextWRINID = driver.findElement(By.xpath("//body/div/div[1][contains(text(),'"+ WRINID1+ "')]/parent::div/following-sibling::div/div[contains(text(),'"+ prefix + "')]")).getText();
			return nextWRINID.equals(WRINId2);
		} catch (Exception e) {
			String previousWRINID = driver.findElement(By.xpath("//body/div/div[1][contains(text(),'"+ WRINID1+ "')]/parent::div/preceding-sibling::div/div[contains(text(),'"+ prefix + "')]")).getText();
			return previousWRINID.equals(WRINId2);
		}
	}

	// @Autor:Hemlata : This method will verify that on searching the prefix in the saved inventory all the WRIN with same prefix should be displayed
	public boolean verifyItemWithSimilarPrifixIsSearched(String prefix,String WRINId) {
		List<WebElement> itemList = driver.findElements(By.xpath("//table[@id='inventory_table']/tbody/tr/td/span[contains(text(),"+ prefix + ")]"));
		for (WebElement item : itemList) {
			if (item.getText().equals(WRINId)) {
				return true;
			}
		}
		return false;
	}
	
	//This method will create and Post a Physical Inventory
	public PhysicalInventoryPage createAndPostAphysicalInventory(String wrinId, String innerPack, String outerPack, String looseUnit) throws InterruptedException 
	{
		wait.until(ExpectedConditions.visibilityOf(PreviousInventoriesForStoreNumber_Title));
		StartNewInventory_BT.click();
		wait.until(ExpectedConditions.visibilityOf(StartInventory_BT));
		StartInventory_BT.click();
		while (Base.isElementDisplayed(AlreadyAnInventoryPopUp_ViewInventory_BT)) {
			Thread.sleep(180000);
			AlreadyAnInventoryPopUp_Cancel_BT.click();
			StartInventory_BT.click();
		}
		wait.until(ExpectedConditions.visibilityOf(CreateInventory_Search_TB));
		selectInventoryType("Monthly");
		Thread.sleep(20000);
		CreateInventory_Search_TB.sendKeys(wrinId);
		Thread.sleep(2000);
		// enter the outer pack value
		driver.findElement(By.xpath("//table[@id='inventory_table']/tbody/tr[2]/td[3]/input")).sendKeys(outerPack);
		// enter the value in inner pack
		try {
			driver.findElement(By.xpath("//table[@id='inventory_table']/tbody/tr[2]/td[4]/input[@type='text']")).sendKeys(innerPack);
		} catch (Exception e) {
			// Inner Pack is not available
		}
		// Enter the value of loose Units
		driver.findElement(By.xpath("//table[@id='inventory_table']/tbody/tr[2]/td[5]/input[@type='text']")).sendKeys(looseUnit);
		Post_BT.click();
		try {
			acceptTheAlertMessage();
		} catch (Exception e) {
			// No alert Present
		}
		return PageFactory.initElements(driver, PhysicalInventoryPage.class);
	}
	
	//Click on the Physical Inventory
	public PhysicalInventoryPage clickOnPhysicalInventory(String date,String time)
	{
		driver.findElement(By.xpath("//table[@id='inventory_selection_table']/tbody/tr/td[1][text()='"+date+"']/following-sibling::td[text()='"+time+"']")).click();
		wait.until(ExpectedConditions.visibilityOf(ViewInventory_Search_TB));
		return PageFactory.initElements(driver, PhysicalInventoryPage.class);
		
	}
	
	//@Autor:Hemlata : This method will select inventory type from the drop down in new inventory page
	public PhysicalInventoryPage selectInventoryType(String inventoryType) throws InterruptedException{
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(SelectANewInventory_DD));
		Thread.sleep(2000);
		Select select =new Select(SelectANewInventory_DD);
		select.selectByVisibleText(inventoryType);
		try{
			acceptTheAlertMessage();
			Thread.sleep(20000);
		}catch(Exception e){}
		return PageFactory.initElements(driver, PhysicalInventoryPage.class);
	}
	
	public PhysicalInventoryPage saveANewInventory(String inventoryType,String WRINID, String outerPackQty, String innerPackQty,String looseCountQty) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(StartNewInventory_BT));
		StartNewInventory_BT.click();
		wait.until(ExpectedConditions.visibilityOf(StartInventory_BT));
		// Fetch the Time of Inventory
		String time = SelectDateAndTimeForNewInventoryPopUp_Time_TB.getAttribute("value");
		SelectDateAndTimeForNewInventoryPopUp_Cancel_BT.click();
		if(Base.isElementDisplayed(BackToTop_BT))
			BackToTop_BT.click();
		try {
			clickOnInProgressInventory(Base.returnTodayDate(), time);
		} catch (Exception e) {
			StartNewInventory_BT.click();
			StartInventory_BT.click();
			wait.until(ExpectedConditions.visibilityOf(CreateInventory_Search_TB));
		}
		if (Base.isElementDisplayed(Save_BT)) {
			selectInventoryType(inventoryType);
			searchItemAndSaveInInventory(WRINID, outerPackQty, innerPackQty,looseCountQty);
		}
		return PageFactory.initElements(driver, PhysicalInventoryPage.class);
	}
	
	//@Autor:Hemlata : This method will save and post a new inventory
	public PhysicalInventoryPage saveAndPostANewInventory(String inventoryType,String WRINID, String outerPackQty,String innerPackQty,String looseCountQty) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(StartNewInventory_BT));
		StartNewInventory_BT.click();
		wait.until(ExpectedConditions.visibilityOf(StartInventory_BT));
		// Fetch the Time of Inventory
		String time = SelectDateAndTimeForNewInventoryPopUp_Time_TB.getAttribute("value");
		SelectDateAndTimeForNewInventoryPopUp_Cancel_BT.click();
		if (Base.isElementDisplayed(BackToTop_BT))
			BackToTop_BT.click();
		try {
			clickOnPostedInventory(Base.returnTodayDate(), time);
		} catch (Exception e) {
			try {
				clickOnInProgressInventory(Base.returnTodayDate(), time);
				selectInventoryType(inventoryType);
				searchItemAndSaveInInventory(WRINID, outerPackQty,innerPackQty, looseCountQty);
			} catch (Exception ex) {
				StartNewInventory_BT.click();
				StartInventory_BT.click();
				wait.until(ExpectedConditions.visibilityOf(CreateInventory_Search_TB));
				selectInventoryType(inventoryType);
				searchItemAndSaveInInventory(WRINID, outerPackQty,innerPackQty, looseCountQty);
			}
		}
		postInventory();
		return PageFactory.initElements(driver, PhysicalInventoryPage.class);
	}
	
	public PhysicalInventoryPage postInventory() throws InterruptedException {
		if (Base.isElementDisplayed(Post_BT)) {
			Post_BT.click();
			wait.until(ExpectedConditions.visibilityOf(PostInventoryPopUp_Finalize_BT));
			PostInventoryPopUp_Finalize_BT.click();
			wait.until(ExpectedConditions.visibilityOf(PostInventoryWarning_Finalize_BT));
			PostInventoryWarning_Finalize_BT.click();
			wait.until(ExpectedConditions.visibilityOf(PostInventoryWarning_Post_BT));
			PostInventoryWarning_Post_BT.click();
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(ViewInventory_Search_TB));
		}
		return PageFactory.initElements(driver, PhysicalInventoryPage.class);
	}

	// @Autor:Hemlata : This method will take date and time as argument and click on the posted inventory at that time stamp
	public PhysicalInventoryPage clickOnPostedInventory(String date, String time) {
		driver.findElement(By.xpath("//table[@id='inventory_selection_table']//tr/td[text()='"+ date+ "']/following-sibling::td[text()='"+ time+ "']/following-sibling::td[text()='Completed']")).click();
		return PageFactory.initElements(driver, PhysicalInventoryPage.class);
	}

	// @Autor:Hemlata : This method will take wrinId as argument and return the total count for that item in inventory
	public String getTotalUnitForRawItemInInventory(String wrinId) {
		return driver.findElement(By.xpath("//table[@id='inventory_table']//tr/td[text()='"+ wrinId+ "']/following-sibling::td/span[@class='total_unit_count_display']")).getText();
	}

	// @Autor:Hemlata : This method will take date and time as argument and click on the posted inventory at that time stamp
	public PhysicalInventoryPage clickOnInProgressInventory(String date,String time) throws InterruptedException {
		driver.findElement(By.xpath("//table[@id='inventory_selection_table']//tr/td[text()='"+ date+ "']/following-sibling::td[text()='"+ time+ "']/following-sibling::td[text()='In-Progress']")).click();
		wait.until(ExpectedConditions.visibilityOf(ViewInventory_Search_TB));
		Thread.sleep(2000);
		return PageFactory.initElements(driver, PhysicalInventoryPage.class);
	}

	/*@Autor:Hemlata : This method will verify that WRIN,Description,Outer
	 * Pack,Inner Pack,Loose Unit,Total Units,Audit columns should be displayed in inventory table
	 */
	public boolean verifyInventoryTableHeadersDisplayed() {
		return Base.isElementDisplayed(InventoryTable_WRINColumn_Label)	&& Base.isElementDisplayed(InventoryTable_DescriptionColumn_Label)
				&& Base.isElementDisplayed(InventoryTable_OuterPackColumn_Label) && Base.isElementDisplayed(InventoryTable_InnerPackColumn_Label)
				&& Base.isElementDisplayed(InventoryTable_LooseUnitColumn_Label) && Base.isElementDisplayed(InventoryTable_TotalUnitsColumn_Label)
				&& Base.isElementDisplayed(InventoryTable_AuditColumn_Label);
	}

	/* @Autor:Hemlata : this method will verify that 8 digit wrin id and
	 * description should be displayed for each raw item in inventory table
	 */
	public boolean verifyWrinNumberAndDescriptionIsDisplayedForEachRawItem() {
		List<WebElement> rawItemRow = driver.findElements(By.xpath("(//table[@id='inventory_table']//tr[@role='row'])"));
		boolean result = true;
		for (int i = 1; i < rawItemRow.size(); i++) {
			String wrinId = driver.findElement(By.xpath("(//table[@id='inventory_table']//tr[@role='row']/td[1])["+ i + "]/span")).getText();
			String description = driver.findElement(By.xpath("(//table[@id='inventory_table']//tr[@role='row']/td[2])["+ i + "]/span")).getText();
			String wrinIdPart1 = wrinId.split("-")[0];
			String wrinIdPart2 = wrinId.split("-")[1];
			result = result && (wrinIdPart1.length() == 5)&& (wrinIdPart2.length() == 3);
			result = result && (!description.isEmpty());
		}
		return result;
	}

	// @Autor:Hemlata : This method will take date and time as argument and click on the posted inventory at that time stamp
	public boolean verifyWrinNumberAndDescriptionIsDisplayedForARawItem(String wrinId, String description) {
		return Base.isElementDisplayed(driver.findElement(By.xpath("//table[@id='inventory_table']//tr[@role='row']/td/span[text()='"+ wrinId+ "']/../following-sibling::td/span[text()='"+ description + "']")));
	}

	public boolean verifyWRINColumnIsOnLeft() {
		return driver.findElement(By.xpath("//table[@id='inventory_table']/thead/tr/th[1]")).getText().equals("WRIN");
	}

	public PhysicalInventoryPage saveANewInventory(String inventoryType)throws InterruptedException {
		//int objIndex = 0;
		//String objXPath = ".//*[@id='htmlButton']";
		//List<WebElement> listObj = driver.findElements(By.xpath(objXPath));
		//wait.until(ExpectedConditions.visibilityOfAllElements(listObj));
		//listObj.get(objIndex).click();
		//wait.until(ExpectedConditions.visibilityOf(StartNewInventory_BT));
		wait.until(ExpectedConditions.visibilityOf(CreateDailyInventory_BT));
		//StartNewInventory_BT.click();
		CreateDailyInventory_BT.click();
		wait.until(ExpectedConditions.visibilityOf(StartInventory_BT));
		// Fetch the Time of Inventory
		String time = SelectDateAndTimeForNewInventoryPopUp_Time_TB.getAttribute("value");
		SelectDateAndTimeForNewInventoryPopUp_Cancel_BT.click();
		if(Base.isElementDisplayed(BackToTop_BT))
			BackToTop_BT.click();
		try {
			clickOnInProgressInventory(Base.returnTodayDate(), time);
		} catch (Exception e) {
			StartNewInventory_BT.click();
			StartInventory_BT.click();
			wait.until(ExpectedConditions.visibilityOf(CreateInventory_Search_TB));
		}
		if (Base.isElementDisplayed(Save_BT)) {
			selectInventoryType(inventoryType);
			Save_BT.click();
			wait.until(ExpectedConditions.visibilityOf(InventorySaved_Confirmation_MSG));
			Thread.sleep(3000);
		}
		return PageFactory.initElements(driver, PhysicalInventoryPage.class);
	}

	// This method will create and Save a Physical Inventory
	public PhysicalInventoryPage createAndSaveAphysicalInventory(String wrinId,String innerPack, String outerPack, String looseUnit) throws InterruptedException 
	{
		wait.until(ExpectedConditions.visibilityOf(PreviousInventoriesForStoreNumber_Title));
		StartNewInventory_BT.click();
		wait.until(ExpectedConditions.visibilityOf(StartInventory_BT));
		StartInventory_BT.click();
		while (Base.isElementDisplayed(AlreadyAnInventoryPopUp_ViewInventory_BT)) {
			Thread.sleep(180000);
			AlreadyAnInventoryPopUp_Cancel_BT.click();
			StartInventory_BT.click();
		}
		wait.until(ExpectedConditions.visibilityOf(CreateInventory_Search_TB));
		selectInventoryType("Monthly");
		Thread.sleep(20000);
		CreateInventory_Search_TB.sendKeys(wrinId);
		Thread.sleep(2000);
		// enter the outer pack value
		driver.findElement(By.xpath("//table[@id='inventory_table']/tbody/tr[2]/td[3]/input")).sendKeys(outerPack);
		// enter the value in inner pack
		try {
			driver.findElement(By.xpath("//table[@id='inventory_table']/tbody/tr[2]/td[4]/input[@type='text']")).sendKeys(innerPack);
		} catch (Exception e) {
			// Inner Pack is not available
		}
		// Enter the value of loose Units
		driver.findElement(By.xpath("//table[@id='inventory_table']/tbody/tr[2]/td[5]/input[@type='text']")).sendKeys(looseUnit);
		Save_BT.click();
		try {
			acceptTheAlertMessage();
		} catch (Exception e) {
			// No alert Present
		}
		return PageFactory.initElements(driver, PhysicalInventoryPage.class);
	}
	
	//This method will verify that table headers are displayed in audit detail Pop up 
	public boolean verifyAuditPopUpTableHeaderDisplayed() {
		boolean result = AuditDetailPopUp_TimeStamp_Label.isDisplayed()	&& AuditDetailPopUp_User_Label.isDisplayed()
				&& AuditDetailPopUp_FieldName_Label.isDisplayed() && AuditDetailPopUp_BeforeValue_Label.isDisplayed()
				&& AuditDetailPopUp_AfterValue_Label.isDisplayed();
		return result;
	}
	
	//Method to click on audit mark for a raw item in physical inventory page
	public void clickOnAuditMarkForARawItem(String wrinId){
		driver.findElement(By.xpath("//table[@id='inventory_table']//tr/td/span[text()='"+wrinId+"']/../following-sibling::td/span[@class='changed_count']/a")).click();
	}
	
	//Method to click on audit mark for a physical inventory
	public void clickOnAuditMarkForInventory(String date,String time){
		driver.findElement(By.xpath("//table[@id='inventory_selection_table']//tr/td[text()='"+date+"']/following-sibling::td[text()='"+time+"']/following-sibling::td[@class='changed_count']/a")).click();
	}
	//Get total unit count for a raw item from physical inventory page
	public String getTotalUnitsForARawItem(String wrinId){
		return driver.findElement(By.xpath("//table[@id='inventory_table']//tr/td/span[text()='"+wrinId+"']/../following-sibling::td[5]")).getText();
	}
	
	/*@Author : Hemlata 
	This method will verify that a inventory List type is displayed in the ListType drop down in Physical Inventory page*/
	public boolean verifyListTypeDisplayed(String listType) {
		wait.until(ExpectedConditions.visibilityOf(SelectANewInventory_DD));
		Select listTypeDD = new Select(SelectANewInventory_DD);
		List<WebElement> inventoryListItem = listTypeDD.getOptions();
		for (WebElement vendor : inventoryListItem) {
			if (vendor.getText().equals(listType))
				return true;
		}
		return false;
	}
	
	public void addARawItemToList(String wrinId) throws InterruptedException{
		AddItem_BT.click();
		wait.until(ExpectedConditions.visibilityOf(AddInventory_Search_TB));
		Thread.sleep(1000);
		AddInventory_Search_TB.sendKeys(wrinId);
		action.sendKeys(Keys.SPACE).build().perform(); 
	    Thread.sleep(1500); 
	    action.sendKeys(Keys.BACK_SPACE).build().perform();
		int size=driver.findElements(By.xpath("//strong[text()='"+wrinId+"']")).size();
		driver.findElement(By.xpath("(//strong[text()='"+wrinId+"'])["+size+"]")).click();
		AddNewItem_BT.click();
		wait.until(ExpectedConditions.visibilityOf(SearchItem_TB));
	}
	
	public void selectADateForPhysicalInventory(String date){
		int month = Base.getMonthFromDate(date);
		int day = Base.getDayFromDate(date);				
		driver.findElement(By.xpath("//div[@class='xdsoft_calendar']/table//tr/td[@data-date='"+day+"' and @data-month='"+month+"']/div")).click();
	}
	
	//This method will select a time stamp prior to current time stamp in select date time pop up for new inventory
	public String selectTimeStampPriorToCurrentTimeStamp() throws InterruptedException {
		driver.findElement(By.xpath("//div[@class='xdsoft_timepicker active']/button[@class='xdsoft_prev']")).click();
		Thread.sleep(1000);
		String selectedTimeStamp = driver.findElement(By.xpath("//div[@class='xdsoft_timepicker active']/div/div[1]/div[contains(@class,'xdsoft_current')]/preceding-sibling::div[1]")).getText();
		driver.findElement(By.xpath("//div[@class='xdsoft_timepicker active']/div/div[1]/div[contains(@class,'xdsoft_current')]/preceding-sibling::div[1]")).click();
		return selectedTimeStamp;
	}
	
	//This method will select a time stamp in select date time pop up for new inventory
	public void selectTimeForPhysicalInventory(String time)	throws InterruptedException {
		WebElement currentTime = driver.findElement(By.xpath("//div[@class='xdsoft_timepicker active']/div/div[1]/div[contains(@class,'xdsoft_current')]"));
		if (currentTime.getAttribute("innerHTML").equals(time))
			currentTime.click();
		else {
			List<WebElement> timeStampList = driver.findElements(By.xpath("//div[@class='xdsoft_timepicker active']/div/div[1]/div[contains(@class,'xdsoft_current')]/preceding-sibling::div"));
			for (int i = 1; i <= timeStampList.size(); i++) {
				driver.findElement(By.xpath("//div[@class='xdsoft_timepicker active']/button[@class='xdsoft_prev']")).click();
				WebElement timestamp = driver
						.findElement(By.xpath("//div[@class='xdsoft_timepicker active']/div/div[1]/div[contains(@class,'xdsoft_current')]/preceding-sibling::div["+ i + "]"));
				String previousTime = timestamp.getAttribute("innerHTML");
				if (previousTime.equals(time)) {
					timestamp.click();
					break;
				}
			}
		}
	}
	
	//This method will select month from the month drop down in select date time pop up for new inventory
	public void selectMonthForPhysicalInventory(String month) throws InterruptedException{
		driver.findElement(By.xpath("//div[contains(@class,'xdsoft_month')]/span")).click();
		driver.findElement(By.xpath("//div[contains(@class,'xdsoft_monthselect')]/div[1]/div[text()='"+month+"']")).click();
		Thread.sleep(1000);
	}
	
	// @Autor:Hemlata : This method will take date and time as argument and getLast saved date and time value from the previous entries table
	public String getLastSavedDateAndTimeForInProgressInventory(String date,String time) {
		String lastSavedDateTime = driver.findElement(By.xpath("//table[@id='inventory_selection_table']//tr/td[text()='"+ date+ "']/following-sibling::td[text()='"+ time+ "']/following-sibling::td[1]")).getText();
		return lastSavedDateTime;
	}
	
	//This method will verify that inventory is saved and entry is available in inventory table for the saved inventory
	public boolean verifyInventorySaved(String date, String time) throws InterruptedException{
		return (driver.findElements(By.xpath("//table[@id='inventory_selection_table']//tr/td[text()='"+ date+ "']/following-sibling::td[text()='"+ time+ "']/following-sibling::td[text()='In-Progress']")).size()>0);
	}
	//This method will return the class attribute of a date from the calendar in select date time pop up 
	public String getClassOfDateInCalender(String date){
		int month = Base.getMonthFromDate(date);
		int day = Base.getDayFromDate(date);				
		return driver.findElement(By.xpath("//div[@class='xdsoft_calendar']/table//tr/td[@data-date='"+day+"' and @data-month='"+month+"']")).getAttribute("class");
	}
	//This method will verify that records are displayed for a selected date in inventory table	
	public boolean verifyRecordForADateIsDisplayed(String date){
		System.out.println(driver.findElements(By.xpath("//table[@id='inventory_selection_table']//tr/td[text()='"+ date+ "']")).size());
		if(driver.findElements(By.xpath("//table[@id='inventory_selection_table']//tr/td[text()='"+ date+ "']")).size()>0)
			return true;
		else
			return false;
	}
	//This method will return before value for a raw item audit pop up
	public String getBeforeValueForRawItemInAuditTable(String date){
		return driver.findElement(By.xpath("(//table[@id='audit_table']//tr/td[contains(text(),'"+date+"')])[1]/following-sibling::td[3]")).getText();
	}
	//This method will return after value for a raw item audit pop up
	public String getAfterValueForRawItemInAuditTable(String date){
		return driver.findElement(By.xpath("(//table[@id='audit_table']//tr/td[contains(text(),'"+date+"')])[1]/following-sibling::td[4]")).getText();
	}
	
	//This method will verify that time in select date time pop up  is displayed as 15 minute increments on the :00, :15, :30, and :45 of each hour
	public boolean verifyTimeDisplayedIn15MinteTimeSpan() {
		List<WebElement> timeStampList = driver.findElements(By.xpath("//div[@class='xdsoft_timepicker active']/div/div[1]/div[contains(@class,'xdsoft_time')]"));
		boolean result = true;
		for (int i = timeStampList.size(); i >= 1; i--) {
			String time = driver.findElement(By.xpath("(//div[@class='xdsoft_timepicker active']/div/div[1]/div[contains(@class,'xdsoft_time')])["+i+"]")).getAttribute("innerHTML");
			int hour = Base.getHourFromTime(time);
			int minute = Base.getMinuteFromTime(time);
			int j = i - 1;
			if (j > 0) {
				String timeWith15MinuteSlice = Base.get15MinuteTimeSlice(hour,minute);
				String previousTime = driver.findElement(By.xpath("(//div[@class='xdsoft_timepicker active']/div/div[1]/div[contains(@class,'xdsoft_time')])["+j+"]")).getAttribute("innerHTML");
				result = result && timeWith15MinuteSlice.equals(previousTime);
			}
		}
		return result;
	}
	
	//This method return the class attribute value of a time stamp
	public String getClassOfTimeStamp(String timeStamp)throws InterruptedException {
		List<WebElement> timeStampList = driver.findElements(By.xpath("//div[@class='xdsoft_timepicker active']/div/div[1]/div[contains(@class,'xdsoft_time')]"));
		for (int i = timeStampList.size(); i >= 1; i--) {
			WebElement time = driver.findElement(By.xpath("(//div[@class='xdsoft_timepicker active']/div/div[1]/div[contains(@class,'xdsoft_time')])["+i+"]"));
			String timeText = time.getAttribute("innerHTML");
			if (timeText.equals(timeStamp)) {
				return time.getAttribute("class");
			}
		}
		return "";
	}
	
}
