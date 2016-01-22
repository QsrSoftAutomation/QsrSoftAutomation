package eInventoryPageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import common.Base;

public class MenuItemInformationPage extends AbstractPage
{

	public MenuItemInformationPage(WebDriver driver) 
	{
		super(driver);
		PageFactory.initElements(driver,this);
		// TODO Auto-generated constructor stub
	}
	
	
	@FindBy(xpath ="//h2[contains(.,'Menu Item Information')]")
	public WebElement MenuItemInformation_Title;
	
	@FindBy(xpath ="//input[@id='autocomplete']")
	public WebElement EnterMenuItemNumberOrDescription_TB;
	
	@FindBy(xpath ="//th[text()='Attribute']")
	public WebElement Attribute_Column_Label;
	
	@FindBy(xpath ="//td[text()='Menu Item Number']")
	public WebElement MenuItemNumber_Label;
	
	@FindBy(xpath ="//table[@id='menu_item_info_table']/tbody/tr[1]//span")
	public WebElement MenuItemNumber_Value;
	
	@FindBy(xpath ="//td[text()='Long Description']")
	public WebElement LongDescription_Label;
	
	@FindBy(xpath ="//table[@id='menu_item_info_table']/tbody/tr[2]//span")
	public WebElement LongDescription_Value;
	
	@FindBy(xpath ="//td[text()='Currently on POS']")
	public WebElement CurrentlyOnPOS_Label;
	
	@FindBy(xpath ="//table[@id='menu_item_info_table']/tbody/tr[3]/td[2]")
	public WebElement CurrentlyOnPOS_Value;
	
	@FindBy(xpath ="//td[text()='Daypart Code']")
	public WebElement DaypartCode_Label;
	
	@FindBy(xpath ="//td[text()='Family Group']")
	public WebElement FamilyGroup_Label;
	
	@FindBy(xpath ="//td[text()='Recipe']")
	public WebElement Recipe_Label;
	
	@FindBy(xpath ="//td[text()='Historical Recipe']")
	public WebElement HistoricalRecipe_Label;
	
	@FindBy(xpath ="(//table[@class='display nowrap'])[2]//th[text()='Effective Start Date:Time']")
	public WebElement HistoricalRecipe_EffectiveStartDateTime_Label;
	
	@FindBy(xpath ="(//table[@class='display nowrap'])[2]//th[text()='Effective End Date:Time']")
	public WebElement HistoricalRecipe_EffectiveEndDateTime_Label;
		
	@FindBy(xpath ="//input[@id='done_button']")
	public WebElement Close_BT;
	
	@FindBy(xpath ="//button[@id='mia-info-recipe-btn']")
	public WebElement Recipe_BT;
	
	@FindBy(xpath ="//button[@id='mia-info-historic-recipe-btn']")
	public WebElement HistoricRecipe_BT;
	
	
	//Method to Search and Select Menu Item
	
	public MenuItemInformationPage searchAndSelectMenuItem(String menuItem) throws InterruptedException
	{
		EnterMenuItemNumberOrDescription_TB.sendKeys(menuItem);
		action.sendKeys(Keys.SPACE).build().perform(); 
		Thread.sleep(1500); 
		action.sendKeys(Keys.BACK_SPACE).build().perform();
		//int size=driver.findElements(By.xpath("//strong[text()='" + menuItem + "']")).size();
		driver.findElement(By.xpath("(//strong[text()='" + menuItem + "'])[1]")).click();
		wait.until(ExpectedConditions.visibilityOf(Attribute_Column_Label));
		return PageFactory.initElements(driver, MenuItemInformationPage.class);
		
	}
	
	public boolean verifyMenuItemInfoPageLoaded(String menuItemNumber,String menuItemDescription){
		boolean menuItemInfoLoaded = Base.isElementDisplayed(By.xpath("//table[@id='mia-info-top-table']/tbody/tr[@id='mia-info-top-table-tr']/td/span[text()='"+menuItemNumber+"']/../following-sibling::td/span[text()='"+menuItemDescription+"']"));
		return menuItemInfoLoaded & Base.isElementDisplayed(Recipe_BT) & Base.isElementDisplayed(HistoricRecipe_BT);
	}
	
	

}
