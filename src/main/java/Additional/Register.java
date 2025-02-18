package Additional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Register {
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver=new ChromeDriver();
		driver.get("https://parabank.parasoft.com/parabank/index.htm");
		driver.manage().window().maximize();
		Thread.sleep(2000);
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
			String username="Pranava";
			driver.findElement(By.id("customer.username")).sendKeys(username);
			driver.findElement(By.name("customer.password")).sendKeys("pranava@19");
			driver.findElement(By.name("repeatedPassword")).sendKeys("pranava@19");
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
	}
}
