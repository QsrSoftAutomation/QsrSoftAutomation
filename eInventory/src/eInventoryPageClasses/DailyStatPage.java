package eInventoryPageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.Base;

public class DailyStatPage  extends AbstractPage{

	public DailyStatPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//h3[text()='Daily Inventory Stat']")
	public WebElement DailyInventory_Label;
	
	@FindBy(xpath = "//button[@id='start_date_btn']")
	public WebElement StartDate_BT;
	
	@FindBy(xpath = "(//div[@class='xdsoft_label xdsoft_month'])[1]/div[contains(@class,'xdsoft_monthselect')]")
	public WebElement MonthPicker_DD;
	
	@FindBy(xpath = "//select[@id='print_list']")
	public WebElement ListType_DD;
	
	
	public void clickOnMonth(String month){
		driver.findElement(By.xpath("(//div[@class='xdsoft_mounthpicker'])/div[@class='xdsoft_label xdsoft_month']/i")).click();
		driver.findElement(By.xpath("(//div[@class='xdsoft_mounthpicker'])/div[@class='xdsoft_label xdsoft_month']/div[contains(@class,'xdsoft_monthselect')]//div[text()='"+month+"']")).click();
	}
	
	public void selectDateFromCalender(String date){
		int month = Base.getMonthFromDate(date);
		int day = Base.getDayFromDate(date);
		driver.findElement(By.xpath("(//div[@class='xdsoft_calendar'])//tbody/tr//td[@data-month='"+month+"'and @data-date='"+day+"']")).click();
	}
	

}
