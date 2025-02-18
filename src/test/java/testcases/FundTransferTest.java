package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FundTransferTest {
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
	@Test(priority = 1)
	public void transferMoneyTest() {
		driver.findElement(By.xpath("//*[@id=\"leftPanel\"]/ul/li[3]/a")).click();
		
		if(driver.getTitle().equals("ParaBank | Transfer Funds")) {
			WebElement amt = driver.findElement(By.xpath("//*[@id=\"amount\"]"));
			amt.sendKeys("5000");
			WebElement dd1=driver.findElement(By.xpath("//select[@id='fromAccountId']"));
//			Select select1=new Select(dd1);
//			select1.selectByVisibleText(" 21336");
//			WebElement dd2=driver.findElement(By.xpath("//select[@id='toAccountId']"));
//			Select select2=new Select(dd2);
//			select2.selectByVisibleText("21447");
			
			Assert.assertEquals(amt.getAttribute("value"),"5000","Wrong Amount has being entered...");
			
//			WebElement fromselected = select1.getFirstSelectedOption();
//			Assert.assertEquals(fromselected.getText(), "13677","Expected not selected for from");
//			
//			WebElement toselected = select2.getFirstSelectedOption();
//			Assert.assertEquals(toselected.getText(), "13677","Expected not selected for to");
			
			driver.findElement(By.xpath("//*[@id='transferForm']/div[2]/input")).click();
			
			WebElement successmsg = driver.findElement(By.tagName("h1"));
			System.out.println(successmsg);
			Assert.assertEquals(successmsg.getText() , "Transfer Complete!" ,"Failed");
			System.out.println("Fund Transfer Test Passed!");	
			
		}else {
			System.out.println("Cant Find this page....");
		}
	}
	
	@Test(priority=2)
	public void insufficientTransfer() {
		
		driver.findElement(By.xpath("//*[@id=\"leftPanel\"]/ul/li[3]/a")).click();
		
		if(driver.getTitle().equals("ParaBank | Transfer Funds")) {
			WebElement amt = driver.findElement(By.xpath("//*[@id=\"amount\"]"));
			amt.sendKeys("5000000000000");
//			WebElement dd1=driver.findElement(By.xpath("//select[@id='fromAccountId']"));
//			Select select1=new Select(dd1);
//			select1.selectByVisibleText("13677");
//			WebElement dd2=driver.findElement(By.xpath("//select[@id='toAccountId']"));
//			Select select2=new Select(dd2);
//			select2.selectByVisibleText("13677");
			
			Assert.assertEquals(amt.getAttribute("value"),"5000000000000","Wrong Amount has being entered...");
			driver.findElement(By.xpath("//*[@id='transferForm']/div[2]/input")).click();
			WebElement errormsg = driver.findElement(By.tagName("h1"));
			if (errormsg.getText().equals("Transfer Complete!")) {
	            Assert.fail("Bug Found: Transfer Complete message displayed despite insufficient funds.");
	            System.out.println("Transfer should be failed but it is completed");
	        } else {
	            Assert.assertTrue(true, "Proper error handling for insufficient funds.");
	        }
		}else {
			System.out.println("Page not found");
		}
	}
	

			
}
