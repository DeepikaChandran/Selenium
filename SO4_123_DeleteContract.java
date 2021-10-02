package week1.day1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SO4_123_DeleteContract {

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
		Thread.sleep(3000);
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
		elem1.sendKeys("00000102",Keys.ENTER);
		Thread.sleep(5000);
		
		//6 .Click on the aDropdown icon and Select Delete
		driver.findElement(By.xpath("(//div[@data-aura-class='forceVirtualAction']//span)[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@title='Delete']")).click();
		Thread.sleep(3000);
		
		//7. Click on the Delete option in the displayed popup window.
		driver.findElement(By.xpath("//button[@title='Delete']")).click();
		
		//8. Verify Whether Contract is Deleted using  Contract number
		
		WebElement elem2 = driver.findElement(By.xpath("//input[@name='Contract-search-input']"));
		elem2.clear();
		elem2.sendKeys("00000102",Keys.ENTER);
		Thread.sleep(3000);
		String text1 = driver.findElement(By.xpath("//span[text()='No items to display.']")).getText();
		String s ="No items to display.";
		if(s.equalsIgnoreCase(text1)) {
			System.out.println("Your contract has been deleted");
		}
		else {
			System.out.println("Something wrong with you coding");
		}
		

	}

}
