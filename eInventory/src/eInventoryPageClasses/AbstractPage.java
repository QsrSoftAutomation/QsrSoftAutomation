package eInventoryPageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage 

{
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected Actions action;
	
	 AbstractPage (WebDriver driver)
	{
		this.driver=driver;
		wait = new WebDriverWait(driver,60);
		action = new Actions(driver);
	}
	//This method will select a user from user dropdown	 
	public HomePage selectUser(String userId) {
		HomePage homePage = new HomePage(driver);
		wait.until(ExpectedConditions.visibilityOf(homePage.User_DD_BT));
		homePage.User_DD_BT.click();
		wait.until(ExpectedConditions.visibilityOf(homePage.User_DD));
		driver.findElement(By.xpath("//ul[@id='user-settings-dropdown']/li/a[text()='"+ userId + "']")).click();
		//wait.until(ExpectedConditions.visibilityOf(homePage.SelectedUserName_Label));
		return PageFactory.initElements(driver, HomePage.class);
	}
	
	//This method will select a store from locations dropdown	 
	public HomePage selectLocation(String storeId) throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.Locations_DD_BT.click();
		wait.until(ExpectedConditions.visibilityOf(homePage.Locations_DD));
		driver.findElement(By.xpath("//ul[@id='store-locations-dropdown']/li/a[text()='"+ storeId + "']")).click();
		//wait.until(ExpectedConditions.visibilityOf(homePage.SelectedLocation_Label));
		Thread.sleep(5000);
		return PageFactory.initElements(driver, HomePage.class);
	}
	
	//This method will open the menu and select inventory management from the menu options
	public HomePage navigateToInventoryManagement() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.Menu_DD_BT.click();
		wait.until(ExpectedConditions.visibilityOf(homePage.InventoryManagement_BT));
		Thread.sleep(1500);
		homePage.InventoryManagement_BT.click();
		//wait.until(ExpectedConditions.visibilityOf(homePage.PhysicalInventory_BT));
		Thread.sleep(1500);
		return PageFactory.initElements(driver, HomePage.class);
	}

	//This method will click on the 'other inventory functions' from inventory management options
	public HomePage navigateToOtherInventoryFunctions() {
		HomePage homePage = new HomePage(driver);
		homePage.OtherInventoryFunctions_BT.click();
		wait.until(ExpectedConditions.visibilityOf(homePage.RawItemActivity_BT));
		return PageFactory.initElements(driver, HomePage.class);
	}

	// To go to Purchase Landing Page
	public PurchasesPage goToPurchaseLandingPage()
	{
		HomePage homePage = new HomePage(driver);
		wait.until(ExpectedConditions.visibilityOf(homePage.Purchases_BT));
		homePage.Purchases_BT.click();
		PurchasesPage purchasePage = new PurchasesPage(driver);
//		wait.until(ExpectedConditions.visibilityOf(purchasePage.Purchases_Label));
		return PageFactory.initElements(driver, PurchasesPage.class);
	}

	 // To go to Menu Item Activity Page

	 public MenuItemActivityPage goToMenuItemActivityPage()
	 {
		 HomePage homePage = new HomePage(driver);
		 wait.until(ExpectedConditions.visibilityOf(homePage.OtherInventoryFunctions_BT));
		 homePage.OtherInventoryFunctions_BT.click();
		 wait.until(ExpectedConditions.visibilityOf(homePage.MenuItemActivity_BT));
		 homePage.MenuItemActivity_BT.click();
		 MenuItemActivityPage menuItemActivityPage=new MenuItemActivityPage(driver);
		// wait.until(ExpectedConditions.visibilityOf(menuItemActivityPage.MenuItemActivity_Title));
		 return PageFactory.initElements(driver, MenuItemActivityPage.class);
	 }

	 // To go to Menu Item Information Page

	 public MenuItemInformationPage goToMenuItemInformationPage()

	 {
		 HomePage homePage=new HomePage(driver);
		 wait.until(ExpectedConditions.visibilityOf(homePage.OtherInventoryFunctions_BT));
		 homePage.OtherInventoryFunctions_BT.click();
		 homePage.MenuItemInformation_BT.click();
		 MenuItemInformationPage menuItemInformationPage=new MenuItemInformationPage(driver);
		 wait.until(ExpectedConditions.visibilityOf(menuItemInformationPage.MenuItemInformation_Title));
		 return PageFactory.initElements(driver, MenuItemInformationPage.class);

	 }
	 
	// To go to Custome Raw Item Lists page
	public CustomRawItemListsPage goToCustomRawItemListsPage()
	{
		HomePage homePage = new HomePage(driver);
		wait.until(ExpectedConditions.visibilityOf(homePage.CustomRawItemLists_BT));
		homePage.CustomRawItemLists_BT.click();
		CustomRawItemListsPage customRawItemListsPage = new CustomRawItemListsPage(driver);
		//wait.until(ExpectedConditions.visibilityOf(customRawItemListsPage.CustomeRawItemLists_Title));
		return PageFactory.initElements(driver, CustomRawItemListsPage.class);
	}

	// To go to Manual Vendors landing Page
	 public ManualVendorsPage goToManualVendorsPage()
	 {
		 HomePage homePage = new HomePage(driver);
		 wait.until(ExpectedConditions.visibilityOf(homePage.OtherInventoryFunctions_BT));
		 homePage.OtherInventoryFunctions_BT.click();
		 wait.until(ExpectedConditions.visibilityOf(homePage.ManualVendors_BT));
		 homePage.ManualVendors_BT.click();
		 ManualVendorsPage manualVendor=new ManualVendorsPage(driver);
		 //wait.until(ExpectedConditions.visibilityOf(manualVendor.ManualVendors_Label));
		 return PageFactory.initElements(driver, ManualVendorsPage.class);

	 }

	 // go to Physical Inventory Page
	public PhysicalInventoryPage goToPhysicalInventoryPage() 
	{
		HomePage homePage = new HomePage(driver);
		wait.until(ExpectedConditions.visibilityOf(homePage.PhysicalInventory_BT));
		homePage.PhysicalInventory_BT.click();
		PhysicalInventoryPage physicalinventorypage = new PhysicalInventoryPage(driver);
//		wait.until(ExpectedConditions.visibilityOf(physicalinventorypage.PhysicalInventoryPage_Title));
		return PageFactory.initElements(driver, PhysicalInventoryPage.class);
	}

	 // To go to Promotion and Waste page
	 public PromotionsAndWastePage goToPromotionsAndWastePage() throws InterruptedException
	 {
		 HomePage homePage=new HomePage(driver);
		 wait.until(ExpectedConditions.visibilityOf(homePage.PromotionAndWaste_BT));
		 homePage.PromotionAndWaste_BT.click();
		 PromotionsAndWastePage promotionsAndWastePage=new PromotionsAndWastePage(driver);
		// wait.until(ExpectedConditions.visibilityOf(promotionsAndWastePage.PromotionAndWaste_Title));
		 return PageFactory.initElements(driver, PromotionsAndWastePage.class);
	 }

	 // go to Raw Item Activity Page(Created by Akash)
	 public RawItemActivityPage goToRawItemActivityPage()
	 {
		 HomePage homePage = new HomePage(driver);
		 wait.until(ExpectedConditions.visibilityOf(homePage.OtherInventoryFunctions_BT));
		 homePage.OtherInventoryFunctions_BT.click();
		 wait.until(ExpectedConditions.visibilityOf(homePage.RawItemActivity_BT));
		 homePage.RawItemActivity_BT.click();
		 RawItemActivityPage rawitemactivitypage=new RawItemActivityPage(driver);
		// wait.until(ExpectedConditions.visibilityOf(rawitemactivitypage.RawItemActivity_Label));
		 return  PageFactory.initElements(driver, RawItemActivityPage.class);

	 }

	 // goto Transfer Landing Page(Created by Akash)

	 public TransferLandingPage goToTransferLandingPage()
	 {
		 HomePage homePage = new HomePage(driver);
		 wait.until(ExpectedConditions.visibilityOf(homePage.OtherInventoryFunctions_BT));
		 homePage.OtherInventoryFunctions_BT.click();
		 wait.until(ExpectedConditions.visibilityOf(homePage.Transfers_BT));
		 homePage.Transfers_BT.click();
		 TransferLandingPage transferlandingpage=new TransferLandingPage(driver);
		// wait.until(ExpectedConditions.visibilityOf(transferlandingpage.TransferLandingPage_Label));
		 return  PageFactory.initElements(driver, TransferLandingPage.class);
	 }


	 // go to Raw Item Information Page
	 public RawItemInformationPage goToRawItemInformationPage()
	 {
		 HomePage homePage = new HomePage(driver);
		 wait.until(ExpectedConditions.elementToBeClickable(homePage.OtherInventoryFunctions_BT));
		 homePage.OtherInventoryFunctions_BT.click();
		 wait.until(ExpectedConditions.visibilityOf(homePage.RawItemInformation_BT));
		 homePage.RawItemInformation_BT.click();
		 RawItemInformationPage rawiteminformationpage=new RawItemInformationPage(driver);
		// wait.until(ExpectedConditions.visibilityOf(rawiteminformationpage.RawItemInformation_Label));
		 return  PageFactory.initElements(driver, RawItemInformationPage.class);
	 }
	 

	 // go to Store Control Settings Page
	 public StoreControlSettingsPage goToStoreControlSettingsPage()
	 {
		 HomePage homePage=new HomePage(driver);
		 homePage.StoreSetting_BT.click();
		 StoreControlSettingsPage storeControlSettingsPage=new StoreControlSettingsPage(driver);
		 wait.until(ExpectedConditions.visibilityOf(storeControlSettingsPage.InventorySetting_BT));
		 return PageFactory.initElements(driver, StoreControlSettingsPage.class);
	 }
	 
	// go to Raw Item Information Page(Created by Akash)
	public VarianceStatPage goToVarianceStatPage() {
		HomePage homePage = new HomePage(driver);
		wait.until(ExpectedConditions.elementToBeClickable(homePage.OtherInventoryFunctions_BT));
		homePage.OtherInventoryFunctions_BT.click();
		wait.until(ExpectedConditions.visibilityOf(homePage.VarianceStat_BT)).click();
		VarianceStatPage varianceStatPage = new VarianceStatPage(driver);
		//wait.until(ExpectedConditions.visibilityOf(dailyStatPage.DailyInventory_Label));
		return PageFactory.initElements(driver, VarianceStatPage.class);
	}
		
		//This method will navigate user to Inventory management menu
		public void goBackToInventoryManagementMenu() throws InterruptedException{
			HomePage homePage=new HomePage(driver);
			homePage.Menu_DD_BT.click();
			wait.until(ExpectedConditions.visibilityOf(homePage.Menu_Back_BT));
			Thread.sleep(1500);
			homePage.Menu_Back_BT.click();
		}
		
		// go to Raw Item Information Page
		 public FoodOverBasePage goToFoodOverBasePage()
		 {
			 HomePage homePage = new HomePage(driver);
			 wait.until(ExpectedConditions.elementToBeClickable(homePage.OtherInventoryFunctions_BT));
			 homePage.OtherInventoryFunctions_BT.click();
			 wait.until(ExpectedConditions.visibilityOf(homePage.FoodOverBase_BT));
			 homePage.FoodOverBase_BT.click();
			 FoodOverBasePage foodOverBasePage=new FoodOverBasePage(driver);
			// wait.until(ExpectedConditions.visibilityOf(foodOverBasePage.FoodOverBase_Label));
			 return  PageFactory.initElements(driver, FoodOverBasePage.class);
		 }
	 
		
}
