package testcases;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import utilities.ExcelReader;
public class LoginTest {
	WebDriver driver;
	String baseurl="https://parabank.parasoft.com/parabank/index.htm";
	@BeforeClass
	public void logintest() {
		 driver=new ChromeDriver();
		 
		driver.get(baseurl);
		if(driver.getTitle().equals("ParaBank | Welcome | Online Banking")) {
			System.out.println("Website is working...");
			driver.manage().window().maximize();
		}
		else {
			System.out.println("Not Working...");
			driver.quit();
		}
	}
	@Test(priority = 1)
	public void register() throws InterruptedException {
		driver.findElement(By.linkText("Register")).click();
		//redirecting to register page
		if(driver.getTitle().equals("ParaBank | Register for Free Online Account Access")) {
			System.out.println("Now you are in Register page....");
			driver.findElement(By.id("customer.firstName")).sendKeys("Jothika");
			driver.findElement(By.id("customer.lastName")).sendKeys("Kasimariappan");
			driver.findElement(By.name("customer.address.street")).sendKeys("Ganapathy Nagar");
			driver.findElement(By.name("customer.address.city")).sendKeys("Avadi");
			driver.findElement(By.id("customer.address.state")).sendKeys("Tamil Nadu");
			driver.findElement(By.id("customer.address.zipCode")).sendKeys("600055");
			driver.findElement(By.name("customer.phoneNumber")).sendKeys("9346499936");
			driver.findElement(By.id("customer.ssn")).sendKeys("123456948");
			String username="Pranavaa";
			driver.findElement(By.id("customer.username")).sendKeys(username);
			driver.findElement(By.name("customer.password")).sendKeys("Pranavaa@19");
			driver.findElement(By.name("repeatedPassword")).sendKeys("Pranavaa@19");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"customerForm\"]/table/tbody/tr[13]/td[2]/input")).submit();
			//validate the register page
			WebElement successmsg = driver.findElement(By.tagName("h1"));
			System.out.println(successmsg);
			Assert.assertEquals(successmsg.getText() , "Welcome "+username ,"Register Failed");
			System.out.println("Register Test Passed!");
		}else {
			System.out.println("Register Page is not opening...");
			driver.quit();
		}
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"leftPanel\"]/ul/li[8]/a")).click();
		driver.get(baseurl);
	}
	@Test(priority =2)
	public void validlogin() throws IOException, InterruptedException {
		
	        List<String[]> loginData = ExcelReader.readCSV("src/test/resources/testdata/validLoginData.csv");
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	        for (String[] credentials : loginData) {
	            String validusername = credentials[0];
	            String validpassword = credentials[1];

	            driver.findElement(By.xpath("//*[@id=\"loginPanel\"]/form/div[1]/input")).sendKeys(validusername);
	            Thread.sleep(2000);
	            driver.findElement(By.xpath("//*[@id=\"loginPanel\"]/form/div[2]/input")).sendKeys(validpassword);
	            Thread.sleep(2000);
	            driver.findElement(By.xpath("//*[@id=\"loginPanel\"]/form/div[3]/input")).submit();
	            

	            // Validate successful login
	            WebElement successmsg = driver.findElement(By.tagName("h1"));
	            Assert.assertEquals(successmsg.getText() , "Accounts Overview" ,"Login Failed");
	            if (successmsg.getText().equals("Accounts Overview")) {
	                System.out.println("Login Test Passed for user: " + validusername);
	                
	                try (FileWriter writer = new FileWriter("src/test/resources/testdata/loginData.xlsx", true)) {
	                    writer.append(validusername).append(":").append(validpassword).append("\n");
	                    System.out.println("Login details stored in Excel file successfully!");
	                    driver.findElement(By.xpath("//*[@id=\"leftPanel\"]/ul/li[8]/a")).click();
	                }
	            } else {
	                System.out.println("Login Test Failed for user: " + validusername);
	            }
	            
	            Thread.sleep(2000);
	            // Navigate back to login page for next iteration
	            driver.get(baseurl);        
	            
	        }
	}
			
	@Test(priority = 3)
	public void invalidlogin() throws InterruptedException {
		
		List<String[]> loginData = ExcelReader.readCSV("src/test/resources/testdata/invalidLoginData.csv");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        for (String[] credentials : loginData) {
            String invalidusername = credentials[0];
            String invalidpassword = credentials[1];
            
            driver.findElement(By.xpath("//*[@id=\"loginPanel\"]/form/div[1]/input")).sendKeys(invalidusername);
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"loginPanel\"]/form/div[2]/input")).sendKeys(invalidpassword);
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"loginPanel\"]/form/div[3]/input")).submit();
            
         // Validate successful login
            WebElement failermsg = driver.findElement(By.xpath("//*[@id=\"rightPanel\"]/p"));
            Assert.assertTrue(failermsg.isDisplayed(), "Expected error message not displayed.");
            //Assert.assertEquals(failermsg.getText() , "The username and password could not be verified." ,"Something went wrong");
            if (failermsg.getText().equals("The username and password could not be verified.")) {
                System.out.println("Login Test Failed for user: " + invalidusername + " That is : " + failermsg.getText());
            } else {
                System.out.println("Login Test Failed for user: " + invalidusername);
            }
            
            Thread.sleep(2000);
            // Navigate back to login page for next iteration
            driver.get(baseurl);  
        }
	}
	@Test(priority = 4)
	public void logout() throws InterruptedException{
		
		driver.findElement(By.xpath("//*[@id=\"loginPanel\"]/form/div[1]/input")).sendKeys("Pranavaa");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"loginPanel\"]/form/div[2]/input")).sendKeys("Pranavaa@19");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"loginPanel\"]/form/div[3]/input")).submit();
        
        if(driver.getCurrentUrl().equals("https://parabank.parasoft.com/parabank/register.htm")) {
        	WebElement logout=driver.findElement(By.xpath("//*[@id=\"leftPanel\"]/ul/li[8]/a"));
        	Assert.assertTrue(logout.isDisplayed(), "Logout button is not displayed. Logout might have failed.");
        	logout.click();
        	if(driver.getCurrentUrl().equals("https://parabank.parasoft.com/parabank/overview.htm")) {
        		System.out.println("You have logout succesfully...");
        	}
        	else {
        		System.out.println("You can able to logout...");
        	}
        }
        else {
        	System.out.println("You are an unauthorized user...");
        }
        
		
	}
	
}
	

