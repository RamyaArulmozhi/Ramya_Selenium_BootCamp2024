package sprint1_Opportunity;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;



public class EditOpportunity {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
				//Disable the WebNotifications
		
				ChromeOptions option=new ChromeOptions();
				
				option.addArguments("--disable-notifications");
				
				//Launch the browser and maximize
				WebDriver driver=new ChromeDriver(option);
				
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
				
				driver.manage().window().maximize();
				
				//Login to Salesforce
				
				driver.get("https://login.salesforce.com/");
				
				driver.findElement(By.id("username")).sendKeys("bootcamp_2024@testleaf.com");
				
				driver.findElement(By.id("password")).sendKeys("Bootcamp@123");
				
				driver.findElement(By.id("Login")).click();
				
				//Click on toggle menu button from the left corner
				
				driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
				
				Thread.sleep(5000);
				
				//Click view All and click Sales from App Launcher
				
				driver.findElement(By.xpath("//button[@aria-label='View All Applications']")).click();
				
				driver.findElement(By.xpath("//input[@placeholder='Search apps or items...']")).sendKeys("Sales");
				
				driver.findElement(By.xpath("//p[text()='Manage your sales process with accounts, leads, opportunities, and more']")).click();
				
				//Click on Opportunity tab
				
				WebElement opportunity = driver.findElement(By.xpath("//span[text()='Opportunities']"));
				
				((JavascriptExecutor) driver).executeScript("arguments[0].click();",opportunity);
				
				Thread.sleep(3000);
				
				// Search the Opportunity 'Salesforce Automation by Your Name'
				
				String oppName="Salesforce Automation by Ramya A";
				
				WebElement editOpp = driver.findElement(By.xpath("//input[@aria-label='Search Recently Viewed list view.']"));
				
				editOpp.sendKeys(oppName);
				
				Thread.sleep(3000);
				
				editOpp.sendKeys(Keys.ENTER);
				
				Thread.sleep(5000);
				
				
				//Click on the Dropdown icon and Select Edit	
				
				List<WebElement> ddSearch = driver.findElements(By.xpath("//table[@aria-label='Recently Viewed']/tbody/tr"));
				
				for(WebElement ele: ddSearch)
				{
					String text = ele.findElement(By.xpath("th//a")).getText();
					if(oppName.equals(text))
					{
						List<WebElement> ddCols = ele.findElements(By.xpath("td"));
						System.out.println(ddCols.size());	
						
						WebElement eleIcon = ddCols.get(ddCols.size()-1);
						
						Thread.sleep(3000);
						
						driver.findElement(By.xpath("//p[@class='slds-text-body--small']")).click();
						
						Thread.sleep(3000);
						
						WebElement eleClick = eleIcon.findElement(By.xpath(".//a"));
						
						eleClick.click();
						
						Thread.sleep(5000);
						
						driver.findElement(By.xpath("//a[@data-target-selection-name='sfdc:StandardButton.Opportunity.Edit']")).click();
						
						Thread.sleep(5000);
						
						break;
					}
				}
				
				// Clear the Previous Date Field
			
				WebElement dateField = driver.findElement(By.xpath("//input[@name='CloseDate']"));
				
				dateField.clear();
				
				//Choose close date as Tomorrow's Date
				
				SimpleDateFormat format= new SimpleDateFormat("dd/MM/yyyy");
				
				Calendar instance = Calendar.getInstance();
				
				instance.add(Calendar.DATE, 1);
				
				String tomDate = format.format(instance.getTime());
				
				dateField.sendKeys(tomDate);	
				
				Thread.sleep(3000);
				
				//Select 'Stage' as Perception Analysis
				
				WebElement element = driver.findElement(By.xpath("//label[text()='Stage']/following-sibling::div//button"));
				
				element.click();
				
				String stage="Perception Analysis";
				
				driver.findElement(By.xpath("//span[@title='"+stage+"']")).click();
				
				Thread.sleep(3000);
				
				//Scrolling Down
				
				WebElement btnDel = driver.findElement(By.xpath("//label[text()='Delivery/Installation Status']/following-sibling::div//button"));
				
				btnDel.sendKeys(Keys.DOWN);
				
				Thread.sleep(4000);
				
				btnDel.click();
				
				// Select Deliver Status as In Progress
				
				driver.findElement(By.xpath("//span[@title='In progress']")).click();
				
				Thread.sleep(4000);
				
				//Enter Description as SalesForce11
				
				WebElement desc = driver.findElement(By.xpath("//label[text()='Description']/following-sibling::div/textarea"));
				
				desc.sendKeys("Salesforce11");
				
				Thread.sleep(2000);
				
				//Save the Opportunity Edited
				
				driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
				
				Thread.sleep(5000);
				
				//Verify the Toast Message
				
				WebElement toastmsg = driver.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']"));
				
				String toastText = toastmsg.getText();
				
				System.out.println("The created message is as follows " + toastText);
				
				Thread.sleep(5000);
				
				//Verify the Stage is updated in the Row displayed after updation
				
				List<WebElement> ddStage = driver.findElements(By.xpath("//table[@aria-label='Recently Viewed']/tbody/tr"));
				
				for(WebElement ele1: ddStage)
				{
					String text1 = ele1.findElement(By.xpath("th//a")).getText();
					
					if(oppName.equals(text1))
					{
						List<WebElement> ddCols1 = ele1.findElements(By.xpath("td"));
						WebElement stageEle = ddCols1.get(4);
						String text2 = stageEle.findElement(By.xpath(".//span[text()='Perception Analysis']")).getText();
						Assert.assertEquals(text2, stage);
						System.out.println("The Stage value Perception Analysis is displayed after updating");
					}
				}

				
				
			//Expected Result:The Oppurtunity is Edited Successfully
				
				System.out.println("The Opportunity is edited Successfully");
				
				
				
				
				
				
				
				
				
				
				
				
				

	}

}
