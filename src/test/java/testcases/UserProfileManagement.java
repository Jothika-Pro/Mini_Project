package testcases;

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

import utilities.ExcelReader;

public class UserProfileManagement {
	
	WebDriver driver;
	
	@BeforeClass
	public void login() {
		driver = new ChromeDriver();
		driver.get("https://demo.guru99.com/V2/index.php");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("/html/body/form/table/tbody/tr[1]/td[2]/input")).sendKeys("mngr610513");
		driver.findElement(By.xpath("/html/body/form/table/tbody/tr[2]/td[2]/input")).sendKeys("gUvypEt");
		driver.findElement(By.xpath("/html/body/form/table/tbody/tr[3]/td[2]/input[1]")).click();
		String cururl = driver.getCurrentUrl();
		System.out.println(cururl);
		if(driver.getCurrentUrl().equals("https://demo.guru99.com/V2/webpages/Managerhomepage.php")) {
			System.out.println("You have logined successfully...");
		}else {
			System.out.println("Login Failed....");
		}
	}
	@Test(priority = 1)
	public void updateProfileTest() throws InterruptedException {
		
		driver.findElement(By.xpath("/html/body/div[3]/div/ul/li[3]/a")).click();
        driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[6]/td[2]/input")).sendKeys("10385");
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[11]/td[2]/input[1]")).click();
        Thread.sleep(2000);
        String elt = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[2]/td/p")).getText();
        if(elt.equals("Edit Customer")) {
            	System.out.println("you are in edit profile page...");
            	List<String[]> profileData = ExcelReader.readCSV("src/test/resources/testdata/profileData.csv");
     	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
     	        for (String[] credentials : profileData) {
     	            String newaddress = credentials[0];
     	            String newcity = credentials[1];
     	            String newmobile = credentials[2];
     	            String newemail = credentials[3];
     	            WebElement address = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[8]/td[2]/textarea"));
     	            address.clear();
     	            address.sendKeys(newaddress);
     	            WebElement city = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[9]/td[2]/input"));
    	            city.clear();
    	            city.sendKeys(newcity);
    	            WebElement mobile = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[12]/td[2]/input"));
    	            mobile.clear();
    	            mobile.sendKeys(newmobile);
    	            WebElement email = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[13]/td[2]/input"));
    	            email.clear();
    	            email.sendKeys(newemail);
    	            
    	            Assert.assertEquals(address.getAttribute("value"), "Ganapathyy nagar");
    	            Assert.assertEquals(city.getAttribute("value"), "Kanchipuram");
    	            Assert.assertEquals(mobile.getAttribute("value"), "9150764949");
    	            Assert.assertEquals(email.getAttribute("value"), "jo@gmail.com");
    	            
    	            driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[14]/td[2]/input[1]")).click();           
     	        }
            }else {
            	System.out.println("you are providing invalid creadentials...");
            }
	}
	@Test(priority = 2)
	public void test() {
		
	}
	
}
