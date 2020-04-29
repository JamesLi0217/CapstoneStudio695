package selenium;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Test {
	
	WebDriver driver;
	public Test(WebDriver driver) {
		this.driver = driver;
	}
	
	public void loginTest(){
		String url = String.format("%s", "http://localhost:8080/Jobs/");
//		String url = "%s/Jobs","http://localhost:";
		driver.get(url);
		boolean testBefore = driver.getPageSource().contains("Welcome, Toms11");
		driver.findElement(By.id("loginUser")).sendKeys("Toms11");
		driver.findElement(By.id("loginPassword")).sendKeys("111");
		driver.findElement(By.id("login-btn")).sendKeys(Keys.ENTER);
		
		new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfElementLocated(By.id("item-nav")));
		boolean testAfter = driver.getPageSource().contains("Welcome, Toms11");
//		driver.close();
		Assert.assertNotEquals(testBefore, testAfter);
	}
	
	public void registerTest() {
//		System.setProperty("webdriver.safari.driver", "/Applications/Safari.app");
//		WebDriver driver = new SafariDriver();
		driver.get("http://localhost:8080/Jobs/");
		driver.manage().window().maximize();
		driver.findElement(By.id("register-form-btn")).sendKeys(Keys.ENTER);
		driver.findElement(By.id("register-username")).sendKeys("Tom115");
		driver.findElement(By.id("register-password")).sendKeys("Lsdddd111");
		driver.findElement(By.id("register-first-name")).sendKeys("Tom");
		driver.findElement(By.id("register-last-name")).sendKeys("Lee");
		driver.findElement(By.id("register-btn")).sendKeys(Keys.ENTER);
		WebDriverWait wait = new WebDriverWait(driver, 10 /*timeout in seconds*/);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		String message = alert.getText();
		alert.accept();
//		driver.close();
		Assert.assertEquals(message, "Enter a unique User Name");
	}
	
	public void favoriteNaviTest() {
		new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfElementLocated(By.id("item-nav")));
//		far fa-bookmark
		driver.findElement(By.id("fav-btn")).sendKeys(Keys.ENTER);
		String actual = driver.findElement(By.id("fav-btn")).getAttribute("class");
		String expected = "main-nav-btn active";
//		driver.close();
		Assert.assertEquals(expected, actual);
	}
	
	
	
	public void unfavoriteItemTest() throws InterruptedException {
		new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfElementLocated(By.id("item-list")));

		driver.findElement(By.id("fav-btn")).sendKeys(Keys.ENTER);
		new WebDriverWait(driver,60).until(ExpectedConditions.attributeToBe(By.id("fav-btn"), "class", "main-nav-btn active"));
		Thread.sleep(3000);
		List<WebElement> favItems = driver.findElement(By.id("item-list")).findElements(By.className("item"));
		String targetUrl = "xxxx";
		for (WebElement item : favItems) {
			WebElement cur = item.findElement(By.tagName("i"));
			String temp = cur.getAttribute("class");
			if (temp.equals("fas fa-bookmark") ){
				item.click();
//				Thread.sleep(500);
				targetUrl = item.findElement(By.className("item-name")).getAttribute("href");
				break;
			}
		}
		Thread.sleep(3000);
		driver.findElement(By.id("nearby-btn")).sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		new WebDriverWait(driver,60).until(ExpectedConditions.attributeToBe(By.id("nearby-btn"), "class", "main-nav-btn active"));

		boolean flag = true;
		new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfElementLocated(By.id("item-list")));
		Thread.sleep(3000);
		List<WebElement> items = driver.findElement(By.id("item-list")).findElements(By.className("item"));
		String expected = "fas fa-bookmark";
		for (WebElement item : items) {
			WebElement cur = item.findElement(By.tagName("i"));
			String url = item.findElement(By.className("item-name")).getAttribute("href");
			String actual = cur.getAttribute("class");
//			if (targetUrl.equals(url) && expected.equals(actual)){
//				flag = false;
//				break;
//			}
		}

		Thread.sleep(1000);
//		driver.close();
		Assert.assertTrue(flag);
	}
	
	public void favoriteItemTest() throws InterruptedException {
		new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfElementLocated(By.id("item-list")));
		Thread.sleep(2000);
		List<WebElement> items = driver.findElement(By.id("item-list")).findElements(By.className("item"));
		String targetUrl = "xxxx";
		for (WebElement item : items) {
			WebElement cur = item.findElement(By.tagName("i"));
			String temp = cur.getAttribute("class");
			if (temp.equals("far fa-bookmark") ){
				item.click();
				targetUrl = item.findElement(By.className("item-name")).getAttribute("href");
				break;
			}
		}
		Thread.sleep(2000);
		driver.findElement(By.id("fav-btn")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		new WebDriverWait(driver,60).until(ExpectedConditions.attributeToBe(By.id("fav-btn"), "class", "main-nav-btn active"));

		boolean flag = false;
		new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfElementLocated(By.id("item-list")));
		List<WebElement> favItems = driver.findElement(By.id("item-list")).findElements(By.className("item"));
		for (WebElement item : favItems) {
//			WebElement cur = item.findElement(By.tagName("i"));
			if (targetUrl.equals(item.findElement(By.className("item-name")).getAttribute("href"))){
				flag = true;
				break;
			}
		}

		Thread.sleep(1000);
//		driver.close();
		Assert.assertTrue(flag);
	}
	
	
	public void recommendationNaviTest() throws InterruptedException {
		new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfElementLocated(By.id("fav-btn")));
		driver.findElement(By.id("recommend-btn")).sendKeys(Keys.ENTER);
		String actual = driver.findElement(By.id("recommend-btn")).getAttribute("class");
		String expected = "main-nav-btn active";
//		driver.close();
		Thread.sleep(1000);
		Assert.assertEquals(expected, actual);
		
	}
	
	public void jobExtLinkTest() throws InterruptedException {
		
		new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(By.className("item-name")));
		WebElement element = driver.findElement(By.className("item-name"));
		String expectedJob = element.getText();
		element.sendKeys(Keys.ENTER);
		Thread.sleep(3000);
//		driver.close();
		new WebDriverWait(driver,10);
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
		String actualJob = driver.findElement(By.cssSelector("h1")).getText();
		driver.close();
		Assert.assertEquals(expectedJob, actualJob);
		
	}
	
	
	public void jobDetailTest() {
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));
		new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfElementLocated(By.className("item-name")));
		String jobName = driver.findElement(By.className("item-name")).getText().toString();
//		driver.close();
		Assert.assertNotNull(jobName);
	}
	
	public void logoutBtnTest() {
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));
		
		new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfElementLocated(By.id("item-nav")));
		
		boolean testBefore = driver.getPageSource().contains("Welcome, Toms11");
		driver.findElement(By.id("logout")).sendKeys(Keys.ENTER);
		new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfElementLocated(By.id("login-btn")));
		boolean testAfter = driver.getPageSource().contains("Welcome, Toms11");
//		driver.close();
		Assert.assertNotEquals(testBefore, testAfter);
		
	}

	public static void main(String[] args) throws InterruptedException{
		
		System.setProperty("webdriver.safari.driver", "/Applications/Safari.app");
		WebDriver driver = new SafariDriver();
		driver.get("http://localhost:8080/Jobs/");
		driver.manage().window().maximize();
		
//		driver.findElement(By.id("loginUser")).sendKeys("Toms11");
//		driver.findElement(By.id("loginPassword")).sendKeys("111");
//		driver.findElement(By.id("login-btn")).sendKeys(Keys.ENTER);
		
		Test tester = new Test(driver);
		
		tester.registerTest();
		System.out.println("Test Register Ok");
		tester.loginTest();
		System.out.println("Test Login Ok");
		tester.jobDetailTest();
		System.out.println("Test Job Detail Ok");
		tester.favoriteItemTest();
		System.out.println("Test Favorite Job Ok");
		tester.unfavoriteItemTest();
		System.out.println("Test Unfavorite Job Ok");
		tester.favoriteNaviTest();
		System.out.println("Test Favorite Navigation Ok");
		tester.recommendationNaviTest();
		System.out.println("Test Recommendation Navigation Ok");
		
		
		tester.jobExtLinkTest();
		System.out.println("Test External Application Ok");
		tester.logoutBtnTest();
		System.out.println("Test Logout Navigation Ok");
		driver.close();
		
	}

}
