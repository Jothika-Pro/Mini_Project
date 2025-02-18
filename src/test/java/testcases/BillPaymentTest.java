package testcases;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BillPaymentTest {
WebDriver driver;
	
	@BeforeClass
	public void login() throws InterruptedException {
		driver=new ChromeDriver();
		driver.get("https://parabank.parasoft.com/parabank/index.htm");
		driver.findElement(By.xpath("//*[@id=\"loginPanel\"]/form/div[1]/input")).sendKeys("Pranavaa");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"loginPanel\"]/form/div[2]/input")).sendKeys("Pranavaa@19");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"loginPanel\"]/form/div[3]/input")).submit();
	}
	
	@Test(priority=1)
	public void addBillPayment() {
		
		driver.findElement(By.xpath("//*[@id='leftPanel']/ul/li[4]/a"));
		WebElement check = driver.findElement(By.tagName("h1"));
		Assert.assertEquals(check.getText(), "Bill Payment Service","You are not in Bill Payament Page");
        WebElement name = driver.findElement(By.xpath("//*[@id='billpayForm']/form/table/tbody/tr[1]/td[2]/input"));
        name.sendKeys("Kasimariappan");
        WebElement address = driver.findElement(By.xpath("//*[@id='billpayForm']/form/table/tbody/tr[2]/td[2]/input"));
        address.sendKeys("TK Street");
        WebElement city=driver.findElement(By.xpath("//*[@id='billpayForm']/form/table/tbody/tr[3]/td[2]/input"));
        city.sendKeys("Ayanavaram");
        WebElement state=driver.findElement(By.xpath("//*[@id='billpayForm']/form/table/tbody/tr[4]/td[2]/input"));
        state.sendKeys("Tamil Nadu");
        WebElement code = driver.findElement(By.xpath("//*[@id='billpayForm']/form/table/tbody/tr[5]/td[2]/input"));
        code.sendKeys("600023");
        WebElement mobile = driver.findElement(By.xpath("//*[@id='00f41f54-5dd1-48fa-817a-1542aa5d7667']"));
        mobile.sendKeys("9840394848");
        WebElement account = driver.findElement(By.xpath("//*[@id='billpayForm']/form/table/tbody/tr[8]/td[2]/input"));
        account.sendKeys("13655");
        WebElement veraccount = driver.findElement(By.xpath("//*[@id='billpayForm']/form/table/tbody/tr[9]/td[2]/input"));
        veraccount.sendKeys("13655");
        WebElement amt = driver.findElement(By.xpath("//*[@id='billpayForm']/form/table/tbody/tr[9]/td[2]/input"));
        amt.sendKeys("13655");
        driver.findElement(By.xpath("//*[@id='billpayForm']/form/table/tbody/tr[14]/td[2]/input")).click();
        WebElement success = driver.findElement(By.tagName("h1"));
		Assert.assertEquals(success.getText(), "Bill Payment Service","You are not in Bill Payament Page");
            			
          
	}
	@Test
	public void incorrectmsg() {
		driver.findElement(By.xpath("//*[@id='leftPanel']/ul/li[4]/a")).click();
		WebElement check = driver.findElement(By.tagName("h1"));
		Assert.assertEquals(check.getText(), "Bill Payment Service","You are not in Bill Payament Page");
        WebElement name = driver.findElement(By.xpath("//*[@id=\"billpayForm\"]/form/table/tbody/tr[1]/td[2]/input"));
        name.sendKeys("Kasimariappan");
        WebElement address = driver.findElement(By.xpath("//*[@id=\"billpayForm\"]/form/table/tbody/tr[2]/td[2]/input"));
        address.sendKeys("TK Street");
        WebElement city=driver.findElement(By.xpath("//*[@id=\"billpayForm\"]/form/table/tbody/tr[3]/td[2]/input"));
        city.sendKeys("Ayanavaram");
        WebElement state=driver.findElement(By.xpath("//*[@id=\"billpayForm\"]/form/table/tbody/tr[4]/td[2]/input"));
        state.sendKeys("Tamil Nadu");
        WebElement code = driver.findElement(By.xpath("//*[@id=\"billpayForm\"]/form/table/tbody/tr[5]/td[2]/input"));
        code.sendKeys("600023");
        WebElement mobile = driver.findElement(By.xpath("//*[@id=\"f3fbe30e-25cc-4c5f-8454-9f45ed61ff39\"]"));
        mobile.sendKeys("9840394848");
        WebElement account = driver.findElement(By.xpath("//*[@id=\"billpayForm\"]/form/table/tbody/tr[8]/td[2]/input"));
        account.sendKeys("13655");
        WebElement veraccount = driver.findElement(By.xpath("//*[@id=\"billpayForm\"]/form/table/tbody/tr[9]/td[2]/input"));
        veraccount.sendKeys("13655");
        WebElement amt = driver.findElement(By.xpath("//*[@id=\"billpayForm\"]/form/table/tbody/tr[9]/td[2]/input"));
        amt.sendKeys("13655");
        driver.findElement(By.xpath("//*[@id=\"billpayForm\"]/form/table/tbody/tr[14]/td[2]/input")).click();
        
        WebElement errorMsg = driver.findElement(By.xpath("//*[@id=\"validationModel-verifyAccount-mismatch"));
        Assert.assertTrue(errorMsg.getText().contains("The funds could not be transferred"), "Error message not displayed for insufficient funds");
	}
}
