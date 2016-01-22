package eInventoryPageClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import common.Base;

public class MenuItemActivityPage extends AbstractPage
{

	public MenuItemActivityPage(WebDriver driver) 
	{
		super(driver);
		PageFactory.initElements(driver,this);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath ="//h1[text()='Menu Item Activity']")
	public WebElement MenuItemActivity_Title;
	
	@FindBy(xpath = "//input[contains(@id,'autosearchInput')]")
	public WebElement MenuItemSearchBox_TB;
	
	@FindBy(xpath = "//div[@id='app_content']/h3/span[@id='mia_page_item_desc']")
	public WebElement MenuItemActivityHeader_label;
	
	@FindBy(xpath = "//button[@id='mia_start_date_btn']")
	public WebElement MiaStartDate_BT;
	
	@FindBy(xpath = "//button[@id='mia_end_date_btn']")
	public WebElement MiaEndDate_BT;
	
	@FindBy(xpath = "//div[@id='mia_table_cap_one']/b[3]")
	public WebElement DefaultDate_label;
	
	@FindBy(xpath = "(//img[@id='eb_tp_clock'])[1]")
	public WebElement StartTime_BT;
	
	@FindBy(xpath = "(//img[@id='eb_tp_clock'])[2]")
	public WebElement EndTime_BT;
	
	@FindBy(xpath = "//input[@id='mia_start_date']")
	public WebElement MiaStartDate_TB;
	
	@FindBy(xpath = "//input[@id='mia_end_date']")
	public WebElement MiaEndDate_TB;
	
	@FindBy(xpath = "//button[@value='Show Results']")
	public WebElement ShowResults_BT;
	
	@FindBy(xpath = "//span[@id='mia-selected-item']")
	public WebElement SelectItem_Value;
	
	@FindBy(xpath = "//button[@id='mia-clear-input-btn']")
	public WebElement Clear_BT;
	
	@FindBy(xpath = "(//div[@id='eb_tp_input'])[1]")
	public WebElement StartTime_TB;
	
	@FindBy(xpath = "(//div[@id='eb_tp_input'])[1]/span[@id='eb_tp_span']")
	public WebElement StartTime_Value;
	
	@FindBy(xpath = "(//div[@id='eb_tp_input'])[2]")
	public WebElement EndTime_TB;
	
	@FindBy(xpath = "(//div[@id='eb_tp_input'])[2]/span[@id='eb_tp_span']")
	public WebElement EndTime_Value;
	
	@FindBy(xpath = "(//span[@id='eb_tp_hr_span'])[1]")
	public WebElement StartTime_hourSpan_Value;
	
	@FindBy(xpath = "(//span[@id='eb_tp_min_span'])[1]")
	public WebElement StartTime_MinSpan_Value;
	
	@FindBy(xpath = "(//span[@id='eb_tp_hr_span'])[2]")
	public WebElement EndTime_hourSpan_Value;
	
	@FindBy(xpath = "(//span[@id='eb_tp_min_span'])[2]")
	public WebElement EndTime_MinSpan_Value;
	
	@FindBy(xpath = "(//img[@id='eb_tp_hour_up'])[1]")
	public WebElement StartTime_HourUp_BT;
	
	@FindBy(xpath = "(//img[@id='eb_tp_min_up'])[1]")
	public WebElement StartTime_MinuteUp_BT;
	
	@FindBy(xpath = "(//img[@id='eb_tp_hour_up'])[2]")
	public WebElement EndTime_HourUp_BT;

	@FindBy(xpath = "(//img[@id='eb_tp_min_up'])[2]")
	public WebElement EndTime_MinuteUp_BT;
	
	@FindBy(xpath = "//table[@id='mia_table']")
	public WebElement MenuItemActivity_Table;
	
	@FindBy(xpath = "//button[@value='Information']")
	public WebElement Information_BT;

	/*
	 * This method will take WRIN Id as argument and search the the WRIN Id in Menu Item Activity Page
	 */

	public MenuItemActivityPage searchAndSelectMenuItem(String menuItem)
			throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(MenuItemSearchBox_TB));
		MenuItemSearchBox_TB.sendKeys(menuItem);
		action.sendKeys(Keys.SPACE).build().perform();
		Thread.sleep(1500);
		action.sendKeys(Keys.BACK_SPACE).build().perform();
		driver.findElement(By.xpath("(//strong[text()='" + menuItem + "'])[1]")).click();
		return PageFactory.initElements(driver, MenuItemActivityPage.class);

	}
	
	// This method will enter date in Start Date field
	public MenuItemActivityPage enterDateInMenuItemStartDate(String date) throws InterruptedException {
		int month = Base.getMonthFromDate(date);
		int day = Base.getDayFromDate(date);
		// Click on Select Date and Time text box
		MiaStartDate_TB.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='xdsoft_calendar'])[1]")));
		driver.findElement(By.xpath("(//div[@class='xdsoft_calendar'])[1]//tbody/tr//td[@data-month='"+ month + "'and @data-date='" + day + "']")).click();
		return PageFactory.initElements(driver, MenuItemActivityPage.class);
	}

	// This method will enter date in End Date field
	public MenuItemActivityPage enterDateInMenuItemEndDate(String date) throws InterruptedException {
		int month = Base.getMonthFromDate(date);
		int day = Base.getDayFromDate(date);
		MiaEndDate_TB.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='xdsoft_calendar'])[2]")));
		driver.findElement(By.xpath("(//div[@class='xdsoft_calendar'])[2]//tbody/tr//td[@data-month='"+ month + "'and @data-date='" + day + "']")).click();
		return PageFactory.initElements(driver, MenuItemActivityPage.class);
	}

	//This method will verify that a date is selected for Start Date field
	public boolean verifyDateIsSelectedForStartDate(String date) {
		if (MiaStartDate_TB.getAttribute("value").equals(date))
			return true;
		else
			return false;

	}
	//This method will verify that a date is selected for End Date field
	public boolean verifyDateIsSelectedForEndDate(String date) {
		if (MiaEndDate_TB.getAttribute("value").equals(date))
			return true;
		else
			return false;

	}
	
	//This method will verify that future date is disabled for Start Date field
	public boolean verifyFutureDateIsDisabledForStartDate(String date) {
		int month = Base.getMonthFromDate(date);
		int day = Base.getDayFromDate(date);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='xdsoft_calendar'])[1]")));
		String dateClass = driver.findElement(
						By.xpath("(//div[@class='xdsoft_calendar'])[1]//tbody/tr//td[@data-month='"+ month + "' and @data-date='" + day + "']")).getAttribute("class");
		if (dateClass.contains("xdsoft_disabled"))
			return true;
		else
			return false;

	}
	
	//This method will verify that future date is disabled for End Date field	
	public boolean verifyFutureDateIsDisabledForEndDate(String date) {
		int month = Base.getMonthFromDate(date);
		int day = Base.getDayFromDate(date);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='xdsoft_calendar'])[2]")));
		String dateClass = driver.findElement(
						By.xpath("(//div[@class='xdsoft_calendar'])[2]//tbody/tr//td[@data-month='"+ month + "' and @data-date='" + day + "']")).getAttribute("class");
		if (dateClass.contains("xdsoft_disabled"))
			return true;
		else
			return false;

	}
	
	// This method will click on the Start time button and enter the time for Start Time
	public void selectStartTime(String startTime)throws InterruptedException {
		StartTime_BT.click();
		Thread.sleep(1000);
		String hourValue = startTime.split(":")[0];
		while(!StartTime_hourSpan_Value.getText().equals(hourValue)){
			StartTime_HourUp_BT.click();
		}
		StartTime_hourSpan_Value.click();
		String minuteValue = startTime.split(":")[1];
		while(!StartTime_MinSpan_Value.getText().equals(minuteValue)){
			StartTime_MinuteUp_BT.click();
		}
		StartTime_MinSpan_Value.click();
		StartTime_TB.click();
	}
	
	// This method will click on the end time button and enter the time for End Time
	public void selectEndTime(String time) throws InterruptedException {
		EndTime_BT.click();
		Thread.sleep(1000);
		String hourValue = time.split(":")[0];
		while (!EndTime_hourSpan_Value.getText().equals(hourValue)) {
			EndTime_HourUp_BT.click();
		}
		EndTime_hourSpan_Value.click();
		String minuteValue = time.split(":")[1];
		while (!EndTime_MinSpan_Value.getText().equals(minuteValue)) {
			EndTime_MinuteUp_BT.click();
		}
		EndTime_MinSpan_Value.click();
		EndTime_TB.click();
	}
	
	
	//This method will verify that menu item activity search results will be displayed for the selected date and time range
	public boolean verifyMenuActivityTimeForSelectedDateRange(String startdate,String endDate, String startTime,String endTime) throws InterruptedException{
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[contains(@class,'tableGroupHeadsOnlyBG')]")));
		int startDay =  Base.getDayFromDate(startdate);
		int endDay =  Base.getDayFromDate(endDate);
		String month = startdate.split("/")[0];
		String year = startdate.split("/")[2];
		boolean timeRangeMatched = true;
		for(int i = startDay; i <= endDay; i++){
			String day;
			if (i > 0 && i < 10) {
				day = "0" + i;
			} else {
				day = String.valueOf(i);
			}
			String date = month + day+ year ;
			String timeDuration = driver.findElement(By.xpath("//tr[contains(@class,'date"+date+"_group')]/td[2]/div")).getText();
			String selectedTime = timeDuration.split("\\|")[1];
			String startTimeInRecord = selectedTime.split(" to ")[0].trim();
			String EndTimeInRecord = selectedTime.split(" to ")[1].trim();
			timeRangeMatched = startTime.equals(startTimeInRecord);
			timeRangeMatched = timeRangeMatched && endTime.equals(EndTimeInRecord);
		}
		return timeRangeMatched;
	}
	
	//This method will verify that Sold value for a menu item will be displayed in menu Item Activity Search Results for selected date and Time Range 
	public boolean verifySoldValueForMenuItemDisplayed(String startdate,String endDate) throws InterruptedException{
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[contains(@class,'tableGroupHeadsOnlyBG')]")));
		int startDay =  Base.getDayFromDate(startdate);
		int endDay =  Base.getDayFromDate(endDate);
		String month = startdate.split("/")[0];
		String year = startdate.split("/")[2];
		boolean soldValueFound = true;
		for(int i = startDay; i <= endDay; i++){
			String day;
			if (i > 0 && i < 10) {
				day = "0" + i;
			} else {
				day = String.valueOf(i);
			}
			String date = month + day + year;
			String soldValue = driver.findElement(By.xpath("//tr[contains(@class,'date"+date+"_group')]/td[4]/div")).getText();
			soldValueFound = soldValueFound && (! soldValue.isEmpty());
		}
		return soldValueFound;
	}
	
	//This method will verify that Waste value for a menu item will be displayed in menu Item Activity Search Results for selected date and Time Range
	public boolean verifyWasteValueForMenuItemDisplayed(String startdate,String endDate) throws InterruptedException{
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[contains(@class,'tableGroupHeadsOnlyBG')]")));
		int startDay =  Base.getDayFromDate(startdate);
		int endDay =  Base.getDayFromDate(endDate);
		String month = startdate.split("/")[0];
		String year = startdate.split("/")[2];
		boolean wasteValueFound = true;
		for(int i = startDay; i <= endDay; i++){
			String day;
			if (i > 0 && i < 10) {
				day = "0" + i;
			} else {
				day = String.valueOf(i);
			}
			String date = month + day + year;
			String wasteValue = driver.findElement(By.xpath("//tr[contains(@class,'date"+date+"_group')]/td[7]/div")).getText();
			wasteValueFound = wasteValueFound && (! wasteValue.isEmpty());
			
		}
		return wasteValueFound;		
	}
	//This method will verify that Promo value for a menu item will be displayed in menu Item Activity Search Results for selected date and Time Range
	public boolean verifyPromoValueForMenuItemDisplayed(String startdate,String endDate) throws InterruptedException{
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[contains(@class,'tableGroupHeadsOnlyBG')]")));
		int startDay =  Base.getDayFromDate(startdate);
		int endDay =  Base.getDayFromDate(endDate);
		String month = startdate.split("/")[0];
		String year = startdate.split("/")[2];
		boolean promoValueFound = true;
		for(int i = startDay; i <= endDay; i++){
			String day;
			if (i > 0 && i < 10) {
				day = "0" + i;
			} else {
				day = String.valueOf(i);
			}
			String date = month + day + year;
			String promoValue = driver.findElement(By.xpath("//tr[contains(@class,'date"+date+"_group')]/td[8]/div")).getText();
			promoValueFound = promoValueFound && (! promoValue.isEmpty());
		}
		return promoValueFound;
		
	}
	
	// This method will verify that EmpMeal value for a menu item will be displayed in menu Item Activity Search Results for selected date and Time Range
	public boolean verifyEmpMealValueForMenuItemDisplayed(String startdate,String endDate) throws InterruptedException {
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[contains(@class,'tableGroupHeadsOnlyBG')]")));
		int startDay = Base.getDayFromDate(startdate);
		int endDay = Base.getDayFromDate(endDate);
		String month = startdate.split("/")[0];
		String year = startdate.split("/")[2];
		boolean empMealValueFound = true;
		for (int i = startDay; i <= endDay; i++) {
			String day;
			if (i > 0 && i < 10) {
				day = "0" + i;
			} else {
				day = String.valueOf(i);
			}
			String date = month + day + year;
			String empMealValue = driver.findElement(By.xpath("//tr[contains(@class,'date" + date+ "_group')]/td[5]/div")).getText();
			empMealValueFound = empMealValueFound && (!empMealValue.isEmpty());
		}
		return empMealValueFound;

	}

	// This method will verify that Mgr Meal value for a menu item will be displayed in menu Item Activity Search Results for selected date and Time Range
	public boolean verifyMgrMealValueForMenuItemDisplayed(String startdate,String endDate) throws InterruptedException {
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[contains(@class,'tableGroupHeadsOnlyBG')]")));
		int startDay = Base.getDayFromDate(startdate);
		int endDay = Base.getDayFromDate(endDate);
		String month = startdate.split("/")[0];
		String year = startdate.split("/")[2];
		boolean mgrMealValueFound = true;
		for (int i = startDay; i <= endDay; i++) {
			String day;
			if (i > 0 && i < 10) {
				day = "0" + i;
			} else {
				day = String.valueOf(i);
			}
			String date = month + day + year;
			String mgrMealValue = driver.findElement(By.xpath("//tr[contains(@class,'date" + date+ "_group')]/td[6]/div")).getText();
			mgrMealValueFound = mgrMealValueFound && (!mgrMealValue.isEmpty());
		}
		return mgrMealValueFound;

	}
	
	/*This method will verify that Activity value for a menu item will be displayed in menu Item Activity Search Results 
	for selected date and Time Range and verify that Activity=Sold+Emp meal+Mgr meal+Waste+Promo*/
	public boolean verifyPOSActivityForMenuItemDisplayed(String startdate,String endDate) throws InterruptedException{
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[contains(@class,'tableGroupHeadsOnlyBG')]")));
		int startDay =  Base.getDayFromDate(startdate);
		int endDay =  Base.getDayFromDate(endDate);
		String month = startdate.split("/")[0];
		String year = startdate.split("/")[2];
		boolean activityValueFound = true;
		for(int i = startDay; i <= endDay; i++){
			String day;
			if (i > 0 && i < 10) {
				day = "0" + i;
			} else {
				day = String.valueOf(i);
			}
			String date = month + day + year;
			int POSActivityValue = Integer.parseInt(driver.findElement(By.xpath("//tr[contains(@class,'date"+date+"_group')]/td[3]/div")).getText());
			int soldValue = Integer.parseInt(driver.findElement(By.xpath("//tr[contains(@class,'date"+date+"_group')]/td[4]/div")).getText());
			int empMealValue = Integer.parseInt(driver.findElement(By.xpath("//tr[contains(@class,'date"+date+"_group')]/td[5]/div")).getText());
			int mgrMealValue = Integer.parseInt(driver.findElement(By.xpath("//tr[contains(@class,'date"+date+"_group')]/td[6]/div")).getText());
			int wasteValue = Integer.parseInt(driver.findElement(By.xpath("//tr[contains(@class,'date"+date+"_group')]/td[7]/div")).getText());
			int promoValue = Integer.parseInt(driver.findElement(By.xpath("//tr[contains(@class,'date"+date+"_group')]/td[8]/div")).getText());			
			boolean activityCalculationResult = (POSActivityValue == soldValue + empMealValue + mgrMealValue + wasteValue + promoValue);
			activityValueFound = activityValueFound && activityCalculationResult;
		}
		return activityValueFound;
		
	}
	//Expand the menu item activity for a date group
	public void expandDateGroup() throws InterruptedException{
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[contains(@class,'tableGroupHeadsOnlyBG')]")));
		List<WebElement> miaRecordGroupList = driver.findElements(By.xpath("//tr[contains(@class,'tableGroupHeadsOnlyBG')]"));
		for(int i=1;i<=miaRecordGroupList.size();i++){
			driver.findElement(By.xpath("(//tr[contains(@class,'tableGroupHeadsOnlyBG')])["+i+"]/td[1]")).click();;
			Thread.sleep(1000);
			
		}
		
	}
	
	//This method will verify that menu activity should be displayed in detail with 15 minutes time span for a selected date and selected time range
	//It will take date argument as MM/DD/YYYY format or YYYYMMDD format
	public boolean verifyMenuActivityTimeInDetailForSelectedDate(String date, String startTime,String endTime){
		if (date.contains("/")) {
			date = getDateInYYYYMMDDFormat(date);
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[contains(@class,'tableGroupHeadsOnlyBG')]")));
		List<WebElement> miaRecordList = driver.findElements(By.xpath("//tr[contains(@class,'date"+date+" ')]"));
		int j;
		boolean result = true;
		for(int i=1;i<=miaRecordList.size();i++){
			String timeSpan = driver.findElement(By.xpath("(//tr[contains(@class,'date"+date+" ')])["+i+"]/td[2]")).getText();
			String startTimeInRecord = timeSpan.split(" to ")[0].trim();
			String EndTimeInRecord = timeSpan.split(" to ")[1].trim();
			int hour = Base.getHourFromTime(EndTimeInRecord);
			int minute = Base.getMinuteFromTime(EndTimeInRecord);
			String timeSlice = Base.get15MinuteTimeSlice(hour, minute);
			result = result && timeSlice.equals(startTimeInRecord);
			if (i < miaRecordList.size()) {
				j = i + 1;
				timeSpan = driver.findElement(By.xpath("(//tr[contains(@class,'date" + date+ " ')])[" + j + "]/td[2]")).getText();
				String EndTimeInNextRecord = timeSpan.split(" to ")[1].trim();
				result = result && startTimeInRecord.equals(EndTimeInNextRecord);
			}
		}
		
		return result;
		
	}
	
	// This method will verify that menu activity should be displayed in detail with 15 minutes time span for each date and selected time range
	public boolean verifyMenuActivityTimeInDetailForEachDate(String startTime, String endTime) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[contains(@class,'tableGroupHeadsOnlyBG')]")));
		List<WebElement> miaRecordGroupList = driver.findElements(By.xpath("//tr[contains(@class,'tableGroupHeadsOnlyBG')]"));
		boolean result = true;
		for(WebElement miaRecordGroup : miaRecordGroupList){
			String date =  miaRecordGroup.getAttribute("class").split(" date")[1].split("_")[0];
			result = result && verifyMenuActivityTimeInDetailForSelectedDate(date, startTime, endTime);
		}
		return result;
	}
	
	// This method will verify that menu activity should be displayed in detail with 15 minutes time span for a selected date and selected time range
	public boolean verifyMenuActivityDisplayedFor15MinTimeSlice(String date) {
		date = getDateInYYYYMMDDFormat(date);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[contains(@class,'tableGroupHeadsOnlyBG')]")));
		List<WebElement> miaRecordList = driver.findElements(By.xpath("//tr[contains(@class,'date" + date + " ')]"));
		boolean result = true;
		for (int i = 1; i <= miaRecordList.size(); i++) {
			String miaForTimeSpan = driver.findElement(By.xpath("(//tr[contains(@class,'date" + date + " ')])["+ i + "]/td[3]")).getText();
			result = result	&& (!miaForTimeSpan.isEmpty());
			}
		return result;
		}
	
	// This method will verify that total menu items sold for a date in 15 min time slice should be displayed
	public boolean verifySoldMenuItemDisplayedFor15MinTimeSlice(String date) {
		date = getDateInYYYYMMDDFormat(date);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[contains(@class,'tableGroupHeadsOnlyBG')]")));
		List<WebElement> miaRecordList = driver.findElements(By.xpath("//tr[contains(@class,'date" + date + " ')]"));
		boolean result = true;
		for (int i = 1; i <= miaRecordList.size(); i++) {
			String menuItemSoldInTimeSpan = driver.findElement(By.xpath("(//tr[contains(@class,'date" + date + " ')])["+ i + "]/td[4]")).getText();
			result = result && (!menuItemSoldInTimeSpan.isEmpty());
		}
		return result;
	}

	// This method will verify that waste menu items sold for a date in 15 min time slice should be displayed
	public boolean verifyWasteItemDisplayedFor15MinTimeSlice(String date) {
		date = getDateInYYYYMMDDFormat(date);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[contains(@class,'tableGroupHeadsOnlyBG')]")));
		List<WebElement> miaRecordList = driver.findElements(By.xpath("//tr[contains(@class,'date" + date + " ')]"));
		boolean result = true;
		for (int i = 1; i <= miaRecordList.size(); i++) {
			String wasteItemInTimeSpan = driver.findElement(By.xpath("(//tr[contains(@class,'date" + date + " ')])["+ i + "]/td[7]")).getText();
			result = result && (!wasteItemInTimeSpan.isEmpty());
		}
		return result;
	}
	
	// This method will verify that menu item Promo should be displayed for a date in 15 min time slice 
	public boolean verifyMenuItemPromoDisplayedFor15MinTimeSlice(String date) {
		date = getDateInYYYYMMDDFormat(date);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[contains(@class,'tableGroupHeadsOnlyBG')]")));
		List<WebElement> miaRecordList = driver.findElements(By.xpath("//tr[contains(@class,'date" + date + " ')]"));
		boolean result = true;
		for (int i = 1; i <= miaRecordList.size(); i++) {
			String menuItemPromoInTimeSpan = driver.findElement(By.xpath("(//tr[contains(@class,'date" + date + " ')])["+ i + "]/td[8]")).getText();
			result = result && (!menuItemPromoInTimeSpan.isEmpty());
		}
		return result;
	}
	
	public String getDateInYYYYMMDDFormat(String date){
		String month = date.split("/")[0];
		String day = date.split("/")[1];
		String year = date.split("/")[2];
		String formattedDate = month + day +year;
		return formattedDate;
		
	}
	
	public boolean verifyHistoricReciopesTableHeaderDIsplayed(){
		boolean result = driver.findElement(By.xpath(".//div[@id='mia-info-historic-recipe-table']/table/thead/tr/th[@class='mia-info-table-header-title']")).isDisplayed();
		System.out.println("Header title displayed "+ result);
		result = result & driver.findElement(By.xpath(".//div[@id='mia-info-historic-recipe-table']/table/thead/tr/th[@class='mia-wrin-column']")).isDisplayed();
		System.out.println("Wrin column displayed "+ result);
		result = result & driver.findElement(By.xpath(".//div[@id='mia-info-historic-recipe-table']/table/thead/tr/th[@class='mia-desc-column']")).isDisplayed();
		System.out.println("Description column displayed "+ result);
		return result;
	}
	
}
