package week1.day1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SO4_125_SortContractNumber {

	public static void main(String[] args) throws InterruptedException {
		//Test Steps:
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
		Thread.sleep(5000);
		
			//3. Click View All and click 'Contract' from App Launcher
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		Thread.sleep(6000);
		driver.findElement(By.xpath("//label[contains(text(),'Search apps or items')]/following::input")).sendKeys("contract");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//p[@class='slds-truncate']/mark")).click();
		Thread.sleep(5000);
		
			//4.Click on Contracts Tab
		JavascriptExecutor executor = (JavascriptExecutor)driver; 
		executor.executeScript("arguments[0].click();",driver.findElement(By.xpath("//a[@title='Contracts']/span")));
		Thread.sleep(3000);
		
			//5.Sort the Contracts by Contract number in ascending order
		
		WebElement table = driver.findElement(By.xpath("//table[@data-aura-class='uiVirtualDataTable']"));
		List<WebElement> tablerow = table.findElements(By.tagName("tr"));
		//System.out.println(tablerow.size());
		List<WebElement> tableCol = table.findElements(By.tagName("td"));
		//System.out.println(tableCol.size());
		List<WebElement> tableHead = table.findElements(By.xpath("//th[@class='slds-cell-edit lockTrigger cellContainer']//a"));
		int size = tableHead.size();
		ArrayList sortContract = new ArrayList();
		for (int i = 0; i < tableHead.size(); i++) {
			String j = tableHead.get(i).getText().trim();
			sortContract.add(j);
		}
		System.out.println("Before Sorting completed:::"+sortContract);
		Collections.sort(sortContract);
		System.out.println("After Sorting completed:::"+sortContract);

		JavascriptExecutor execute = (JavascriptExecutor)driver; 
		Object sortLast = execute.executeScript("arguments[0].click();",driver.findElement(By.xpath("//span[@title='Contract Number']")));
		Thread.sleep(2000);
		WebElement table1 = driver.findElement(By.xpath("//table[@data-aura-class='uiVirtualDataTable']"));
		List<WebElement> tablerow1 = table1.findElements(By.tagName("tr"));
		//System.out.println(tablerow.size());
		List<WebElement> tableCol1 = table1.findElements(By.tagName("td"));
		//System.out.println(tableCol.size());
		List<WebElement> tableHead1 = table1.findElements(By.xpath("//th[@class='slds-cell-edit lockTrigger cellContainer']//a"));
		System.out.println(tableHead1.size());
		
		for (int i = 0; i < tableHead1.size(); i++) {
			String aftrSort = tableHead1.get(i).getText().trim();
			String sortList = (String) sortContract.get(i);
			if(aftrSort.equalsIgnoreCase(sortList)) {
				System.out.println("***After Sorting**********");
				System.out.println(aftrSort);
			}else {
				System.out.println("Value Not Matached:::"+aftrSort+"="+sortList);
			}
			
			
		}





		
	
		
		//List<WebElement> contractNumbers = driver.findElements(By.xpath("//th[@class='slds-cell-edit lockTrigger cellContainer']"));
		
	/*String[] newArr = new String[tableHead.size()];
		
		System.out.println(newArr);
		
		for (int i = 0; i < tableHead.size(); i++) {
		newArr[i]= tableHead.get(i).getText().trim();
		}
		
		System.out.println("**********Before Sort*********");
		System.out.println(newArr);
		
		Arrays.sort(newArr);
		System.out.println("**********After Sort*********");
		System.out.println(newArr);*/
	
		
			//6. Verify the Contracts displayed in ascending order by Contract number
	}

}
