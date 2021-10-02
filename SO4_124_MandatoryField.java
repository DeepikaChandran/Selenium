package week1.day1;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SO4_124_MandatoryField {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		

		//1. Login to https://login.salesforce.com
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://login.salesforce.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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
		
		    //4. Click on the Dropdown icon in the Contract tab
		driver.findElement(By.xpath("//span[contains(text(),'Contracts Menu')]/ancestor::a")).click();
		Thread.sleep(3000);
		
			//5. Click on New Contract
		JavascriptExecutor executor = (JavascriptExecutor)driver; 
		 executor.executeScript("arguments[0].click();",driver.findElement(By.xpath("(//a[@role='menuitem'])[1]")));
		 Thread.sleep(3000);
		
			//6. Select the accounts listed on the'Account Name' field
		 WebElement accno = driver.findElement(By.xpath("//input[@title='Search Accounts']"));
		 accno.sendKeys("test");
		 Thread.sleep(3000);
		 driver.findElement(By.xpath("//div[@title='test']")).click();
		 
			//7.Select the Contract Start Date as Tommorow's Date
		 driver.findElement(By.xpath("(//span[text()='Date Picker']/parent::a)[1]")).click();
		 Thread.sleep(3000);
		 driver.findElement(By.xpath("//td[@class='slds-is-today slds-is-selected uiDayInMonthCell']")).click();
		 
			//9.Click save
		 driver.findElement(By.xpath("(//span[text()='Save']/parent::button)[2]")).click();
			Thread.sleep(3000);
			
			//Verify the errror message
			
		driver.findElement(By.xpath("(//div[@role='alert']//span)[1]")).getText();
		String alert = driver.findElement(By.xpath("//ul[@class='errorsList']/li")).getText();
		String errorMsg = "These required fields must be completed: Contract Term (months)";
		if(errorMsg.equalsIgnoreCase(alert)) {
			System.out.println("Enter the mandatory Fields to save the contract");
		}
	else {
		System.out.println("Your coding is wrong");
		
	}
		
	}

}
