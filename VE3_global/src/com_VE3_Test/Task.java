package com_VE3_Test;

import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Task {
	public static WebDriver driver;

	@BeforeMethod
	// Verify That VE3.global homepage loads successfully.
	public void Setup() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\installer selenium\\chromedriver.exe");

		ChromeOptions option = new ChromeOptions();
		option.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(option);
		driver.manage().window().maximize();
		driver.get("https://www.ve3.global");
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.MILLISECONDS);
		Thread.sleep(3000);

	}

	// Verify that Newletter form and submitting successfully
	@Test(priority = 1,enabled=true)
	public void Newsletterform() throws InterruptedException {
		driver.navigate().to("https://www.ve3.global/news/");
		JavascriptExecutor js = (JavascriptExecutor) driver;

		WebElement text = driver.findElement(By
				.xpath("//h3[contains(text(),\"Newsletter that immerses you to our latest research and insights\")]"));
		js.executeScript("arguments[0].scrollIntoView();", text);
		driver.findElement(By.xpath("//input[@name=\"your-name\"]")).sendKeys("Vrushali");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@autocomplete=\"email\"]")).sendKeys("kabadivrushali97@gmail.com");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();
//		test.log(Status.INFO, "This is an information message.");

		Thread.sleep(3000);

	}

	@Test(priority = 2,enabled=true)
	public void submit() {
		try {
			driver.get("https://www.ve3.global/news/");
			JavascriptExecutor js = (JavascriptExecutor) driver;

			WebElement text = driver.findElement(By.xpath(
					"//h3[contains(text(),\"Newsletter that immerses you to our latest research and insights\")]"));
			js.executeScript("arguments[0].scrollIntoView();", text);
			driver.findElement(By.xpath("//input[@name=\"your-name\"]")).sendKeys("Vrushali");
			Thread.sleep(3000);
			driver.findElement(By.xpath("//input[@autocomplete=\"email\"]")).sendKeys("kabadivrushali97gmail.com");
			Thread.sleep(3000);
			driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();
			Thread.sleep(3000);

			// Verify the error message
			WebElement errorMessage = driver.findElement(By.xpath("//p[@role=\"status\"]"));
			String expectedErrorMessage = "One or more fields have an error. Please check and try again.";
			System.out.println("Expected Message:" + expectedErrorMessage);
			String actualErrorMessage = errorMessage.getText();

			if (actualErrorMessage.equals(expectedErrorMessage)) {
				System.out.println("Error message verified: " + actualErrorMessage);
			} else {
				System.err.println("Error message verification failed. Expected: " + expectedErrorMessage + ", Actual: "
						+ actualErrorMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// DDF
	@Test(priority = 3,enabled=true)
	public void NewsletterDDF() throws IOException, InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\installer selenium\\chromedriver.exe");

		ChromeOptions option = new ChromeOptions();
		option.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(option);
		driver.manage().window().maximize();

		String path = "C:\\Users\\Vrushali\\OneDrive\\Desktop\\VE3_excel.xlsx";
		FileInputStream file = new FileInputStream(new File(path));
		Workbook workbook = new XSSFWorkbook(file);
		Sheet sheet = workbook.getSheet("Sheet1");

		for (int rowNum = 1; rowNum <= ((org.apache.poi.ss.usermodel.Sheet) sheet).getLastRowNum(); rowNum++) {
			Row row = (sheet).getRow(rowNum);

			String name = row.getCell(1).getStringCellValue();
			String email = row.getCell(2).getStringCellValue();

			driver.get("https://www.ve3.global/news/");

			WebElement nameField = driver.findElement(By.xpath("//input[@name=\"your-name\"]"));
			Thread.sleep(3000);
			WebElement emailField = driver.findElement(By.xpath("//input[@autocomplete=\"email\"]"));
			Thread.sleep(3000);
			WebElement submitButton = driver.findElement(By.xpath("//input[@type=\"submit\"]"));

			nameField.sendKeys(name);
			emailField.sendKeys(email);
			try {
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", submitButton);

				// Sleep for 3 seconds
			} catch (NullPointerException e) {
				System.err.println("Error: One or more elements not found on the page.");
				e.printStackTrace();
			}
		}

		file.close();

	}

	@Test(priority=4)
	public void Findallelements() {
		driver.navigate().to("https://www.ve3.global/news/");
		java.util.List<WebElement> allElements = driver.findElements(By.xpath("//*"));

		// Iterate through the elements and print their values
		for (WebElement element : allElements) {
			String elementValue = element.getText();
			if (!elementValue.isEmpty()) {
				System.out.println("Element Text: " + elementValue);
			}
		}

	}

	@AfterMethod
	public void teardown() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}

}
