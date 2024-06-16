package sprint1_Opportunity;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

public class CreateOpportunityWithoutMandatoryFields {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		//Disable web Notification
		ChromeOptions option =new ChromeOptions();
		option.addArguments("--disable-notifications");
		
		//Launch Browser
		
		WebDriver driver=new ChromeDriver(option);
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		//Login to Salesforce Application
		
		driver.get("https://login.salesforce.com/");
		
		driver.findElement(By.id("username")).sendKeys("bootcamp_2024@testleaf.com");
		
		driver.findElement(By.id("password")).sendKeys("Bootcamp@123");
		
		driver.findElement(By.id("Login")).click();
		
		//Click on Toggle menu button
		
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		
		//Click View All
		
		driver.findElement(By.xpath("//button[@aria-label='View All Applications']")).click();
		
		//Click Sales from App Launcher
		
		driver.findElement(By.xpath("//input[@placeholder='Search apps or items...']")).sendKeys("sales");
		
		driver.findElement(By.xpath("//p[text()='Manage your sales process with accounts, leads, opportunities, and more']")).click();
		
		Thread.sleep(5000);
		
		//Click on Opportunity tab
		
		WebElement opportunity = driver.findElement(By.xpath("//span[text()='Opportunities']"));
				
		((JavascriptExecutor) driver).executeScript("arguments[0].click();",opportunity);
				
		Thread.sleep(3000);
		
		//Click on New Button
		
		driver.findElement(By.xpath("//a[@title='New']")).click();
		
		//Choose Close Date as Tomorrow's date
		
		WebElement closedDate = driver.findElement(By.xpath("//div//input[@name='CloseDate']"));
		
		
		SimpleDateFormat format= new SimpleDateFormat("dd/MM/yyyy");
		
		Calendar instance = Calendar.getInstance();
		
		instance.add(Calendar.DATE, 1);
		
		String tomDate = format.format(instance.getTime());
		
		closedDate.sendKeys(tomDate);	
		
		Thread.sleep(2000);
		
		// Save
		
		driver.findElement(By.xpath("//lightning-button//button[@name='SaveEdit']")).click();
		
		Thread.sleep(2000);
		
		//Alert message
		
		WebElement text = driver.findElement(By.xpath("//h2[@title='We hit a snag.']"));
		
		System.out.println(text.getText());
		
		//Review Alert message
		
		WebElement alert1 = driver.findElement(By.xpath("//div[@class='fieldLevelErrors']/div"));
		
		String reviewText = alert1.getText();
		
		System.out.println(reviewText);

		WebElement alert2 = driver.findElement(By.xpath("//div[@class='fieldLevelErrors']/ul"));
		
		String missedField = alert2.getText();
		
		System.out.println(missedField);
		
		//Expected Result:Complete this field message should be displayed for Name and Stage fields
		
		Assert.assertTrue(alert2.isDisplayed());
		
		
		System.out.println("Complete this field message is displayed for Name and Stage");
		
		
		
		
		
		
		
		
		
		
		

	}

}
