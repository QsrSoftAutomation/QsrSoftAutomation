package eInventoryPageClasses;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class HomePage extends AbstractPage

{
	public HomePage(WebDriver driver) 
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//a[contains(.,'Store')]//b[@class='caret']")
	public WebElement Store_DD_Button;
	
	@FindBy(xpath="//li[@class='dropdown open']//ul[@class='dropdown-menu']")
	public WebElement Store_DD;
	
	@FindBy(xpath="//a[contains(.,'Inventory')]//b[@class='caret']")
	public WebElement Inventory_DD_Button;
	
	@FindBy(xpath="//li[@class='dropdown open']//ul[@class='dropdown-menu']")
	public WebElement Inventory_DD;
	
	@FindBy(xpath="//li[@class='dropdown open']//ul[@class='dropdown-menu']//a[contains(.,'Purchases')]")
	public WebElement Inventory_DD_Purchases;
	
	@FindBy(xpath="//li[@class='dropdown open']//a[text()='Promotions & Waste']")
	public WebElement Inventory_DD_PromotionsAndWaste;
	
	@FindBy(xpath="//li[@class='dropdown open']//ul[@class='dropdown-menu']//a[contains(.,'Manual Vendors')]")
	public WebElement Inventory_DD_ManualVendors; 
	
	@FindBy(xpath="//a[text()='Transfers']")
	public WebElement Transfers_BT; 
	
	@FindBy(xpath="//a[text()='Raw Item Information']")
	public WebElement RawItemInformation_BT;
	
	@FindBy(xpath="//a[text()='Raw Item Activity']")
	public WebElement RawItemActivity_BT; 
	
	@FindBy(xpath="//a[contains(.,'Cash')]//b[@class='caret']")
	public WebElement Cash_DD_Button;
	
	@FindBy(xpath="//li[@class='dropdown open']//ul[@class='dropdown-menu']")
	public WebElement Cash_DD;
	
	@FindBy(xpath="//li[@class='dropdown open']//ul[@class='dropdown-menu']//a[contains(.,'Store Control Settings')]")
	public WebElement Cash_DD_StoreControlSettings;
	
	@FindBy(xpath="//li[3]/a[text()='Daily Stat']")
	public WebElement Inventory_DD_DailyStat;
	
	/******New UI ****/
	@FindBy(xpath="//i[@class='user-settings-icon']")
	public WebElement User_DD_BT;
	
	@FindBy(id="user-settings-dropdown")
	public WebElement User_DD;
	
	@FindBy(xpath="//span[@id='eid_name']")
	public WebElement SelectedUserName_Label;
	
	@FindBy(xpath="//li[@id='locations']/a")
	public WebElement Locations_DD_BT;
	
	@FindBy(id="store-locations-dropdown")
	public WebElement Locations_DD;
	
	@FindBy(xpath="//div[@id='nsn']")
	public WebElement SelectedLocation_Label;
	
	@FindBy(xpath="//a[@href='#menu']/i[@id='menu-icon']")
	public WebElement Menu_DD_BT;
	
	@FindBy(xpath="//div[@id='main-nav-list']/ul/li/span[text()='Inventory Management']/preceding-sibling::a")
	public WebElement InventoryManagement_BT;
	
	@FindBy(xpath="//a[text()='Physical Inventory']")  
	public WebElement PhysicalInventory_BT;
	
	@FindBy(xpath="//ul[@class='mm-listview mm-first mm-last']/li/span[text()='Other Inventory Functions']/preceding-sibling::a")
	public WebElement OtherInventoryFunctions_BT;
	
	@FindBy(xpath="//div[@id='mm-4']//a[@class='mm-btn mm-prev' ]")
	public WebElement Menu_Back_BT;
	
	@FindBy(xpath="//a[following-sibling::a[text()='Other Inventory Functions']]")
	public WebElement Menu_OtherInventoryFunction_Back_BT;
	
	@FindBy(xpath="//a[text()='Purchases']")
	public WebElement Purchases_BT;
	
	@FindBy(xpath="//a[text()='Menu Item Activity']")
	public WebElement MenuItemActivity_BT; 
	
	@FindBy(xpath="//a[text()='Custom Raw Item Lists']")
	public WebElement CustomRawItemLists_BT; 
	
	@FindBy(xpath="//a[text()='Manual Vendors']")
	public WebElement ManualVendors_BT; 
	
	@FindBy(xpath="//a[text()='Menu Item Information']")
	public WebElement MenuItemInformation_BT; 
	
	@FindBy(xpath="//a[text()='Promotions & Waste']")
	public WebElement PromotionAndWaste_BT; 
	
	@FindBy(xpath="//a[text()='Variance Stat']")
	public WebElement VarianceStat_BT; 
	
	@FindBy(xpath="//a[text()='Food Over Base']")
	public WebElement FoodOverBase_BT;
	
	@FindBy(xpath="//li[@id='store-settings']/a")
	public WebElement StoreSetting_BT;
	
	/**************************/
	//Select a store from the store drop down
	
	public HomePage selectAStore(String Store_id)
	{
		Store_DD_Button.click();
		wait.until(ExpectedConditions.visibilityOf(Store_DD));
		driver.findElement(By.xpath("//li[@class='dropdown open']//ul[@class='dropdown-menu']/li/a[text()='"+Store_id+"']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='local_dash_link navbar-brand']/div[text()='"+Store_id+"']")));
		return PageFactory.initElements(driver, HomePage.class);
	}
}
