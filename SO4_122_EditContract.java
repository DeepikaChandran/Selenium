package week1.day1;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SO4_122_EditContract {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		//Steps:1. Login to https://login.salesforce.com
		
		driver.get("https://login.salesforce.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		driver.findElement(By.id("username")).sendKeys("mars@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Bootcamp$123");
		driver.findElement(By.id("Login")).click();
		Thread.sleep(3000);
		
			
		//2. Click on the toggle menu button from the left corner
		
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		Thread.sleep(3000);
		
		//3. Click View All and click 'Contract' from App Launcher
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		Thread.sleep(6000);
		driver.findElement(By.xpath("//label[contains(text(),'Search apps or items')]/following::input")).sendKeys("contract");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//p[@class='slds-truncate']/mark")).click();
		Thread.sleep(5000);

		//4. Click the Contract tab
		JavascriptExecutor executor = (JavascriptExecutor)driver; 
		executor.executeScript("arguments[0].click();",driver.findElement(By.xpath("//a[@title='Contracts']/span")));
		Thread.sleep(3000);
		
		
		//5. Search the Account number in the Search box
		WebElement elem1 = driver.findElement(By.xpath("//input[@name='Contract-search-input']"));
		elem1.sendKeys("00000109",Keys.ENTER);
		Thread.sleep(3000);
		
		//6.Click on the Dropdown icon and Select Edit
		
		driver.findElement(By.xpath("(//div[@data-aura-class='forceVirtualAction']//span)[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@title='Edit']")).click();
		Thread.sleep(3000);
		
		
		//7.Select Status as 'Activated'
	
		driver.findElement(By.xpath("(//a[@role='button']/preceding::div[@class='uiPopupTrigger'])[2]")).click();
		List<WebElement> statusList = driver.findElements(By.xpath("//li[@data-aura-class='uiMenuItem uiRadioMenuItem']/a"));
		
		/*int size = statusList.size();
		for (WebElement drpdown : statusList) {
			System.out.println(drpdown.getText());
			
		}*/
		System.out.println(statusList.get(2).getText());
		statusList.get(2).click();
		//WebElement ele1 = statusList.get(2);
		//ele1.click();
		
		//8.Select Owner Expiration Notice as'30 days'
		
		driver.findElement(By.xpath("(//div[@role='menu']/preceding::div[@data-aura-class='uiPopupTrigger'])[3]")).click();
		//List<WebElement> expNotice = driver.findElements(By.xpath("//li[@data-aura-class='uiMenuItem uiRadioMenuItem']/a"));	
		//expNotice.get(6).click();
	driver.findElement(By.xpath("//a[@title='30 Days']")).click();
		
//9.Click save and Verify the Status
	
	driver.findElement(By.xpath("(//span[text()='Save'])[2]")).click();
	Thread.sleep(3000);
	
	List<WebElement> getStatus = driver.findElements(By.xpath("(//td[@data-aura-class='forceInlineEditCell'])/span"));
	String text = getStatus.get(1).getText();
	System.out.println(text);
if(text.equalsIgnoreCase("Activated")) {
	System.out.println("Status has been changed to Activated");
}
else
{
	System.out.println("Status is wrong");
}
	
	}
}