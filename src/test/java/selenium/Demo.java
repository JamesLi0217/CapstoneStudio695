package selenium;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Demo {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.safari.driver", "/Applications/Safari.app");
		WebDriver driver = new SafariDriver();
		driver.get("http://localhost:8080/Jobs/");
		driver.manage().window().maximize();
		boolean testBefore = driver.getPageSource().contains("Welcome, Toms11");
		driver.findElement(By.id("loginUser")).sendKeys("Toms11");
		driver.findElement(By.id("loginPassword")).sendKeys("111");
		driver.findElement(By.id("login-btn")).sendKeys(Keys.ENTER);
		
		new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfElementLocated(By.id("item-nav")));
		boolean testAfter = driver.getPageSource().contains("Welcome, Toms11");
		driver.close();
		Assert.assertNotEquals(testBefore, testAfter);
	}

}
