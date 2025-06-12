package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.events.WebDriverListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.lang.InterruptedException;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
// import io.github.bonigarcia.wdm.WebDriverManager;
 import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void testCase01() throws InterruptedException{
        driver.get("https://forms.gle/wjPkzeSEk1CM7KgGA");
        Thread.sleep(5000);
        //enter name
        WebElement nameTextBox = driver.findElement(By.xpath("//div[contains(@class, 'k3kHxc')]//input"));
        Wrappers.enterText(nameTextBox, "Crio Learner");
        //enter why you practing automation
        WebElement automationTextArea = driver.findElement(By.className("KHxj8b"));
        Wrappers.enterText(automationTextArea, "I want to be the best QA Engineer! "+Wrappers.getEpochTIme());
        //Select automation experience
        List<WebElement> expRadioBtnList = driver.findElements(By.className("OvPDhc"));
        Wrappers.selectRadioBtn(driver, expRadioBtnList, 5);
        Thread.sleep(1000);
        //Select subjects learned
        HashSet<String> subjects = new HashSet<>();
        subjects.add("Java");
        subjects.add("Selenium");
        subjects.add("TestNG");
        List<WebElement> subjChkBoxList = driver.findElements(By.xpath("//span[contains(@class, 'vBHf')]"));
        Wrappers.selectCheckBox(driver, subjChkBoxList, subjects);
        //Select how to be addressed
        Wrappers.selectDropDown(driver, "Mrs");
        System.out.println("How to be addressed selected");
        //enter date of 7 days ago
        String date = Wrappers.getFormattedDate("dd/MM/yyyy", 7);
        WebElement dateInput = driver.findElement(By.xpath("//div[contains(@class,'cIKf')]//input"));
        Wrappers.enterText(dateInput, date);
        //enter time 07:30
        WebElement hourTextBox = driver.findElement(By.xpath("//input[@aria-label='Hour']"));
        WebElement minTextBox = driver.findElement(By.xpath("//input[@aria-label='Minute']"));
        Wrappers.enterText(hourTextBox, "07");
        Wrappers.enterText(minTextBox, "30");
        //Submit the form
        WebElement submitBtn = driver.findElement(By.className("Y5sE8d"));
        Wrappers.clickElement(driver, submitBtn);
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/formResponse"));
        System.out.println("Form submission successful");
        String msg = Wrappers.printSuccessMessage(driver);
        Assert.assertEquals(msg, "Thanks for your response, Automation Wizard!");
        Thread.sleep(2000);
    }

    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}