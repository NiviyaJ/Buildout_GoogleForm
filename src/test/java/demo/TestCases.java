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
        Boolean status;
        driver.get("https://forms.gle/wjPkzeSEk1CM7KgGA");
        status = Wrappers.enterName(driver, "Crio Learner");
        Assert.assertTrue(status);
        status = Wrappers.enterPracticeAutomationReason(driver, "I want to be the best QA Engineer!");
        Assert.assertTrue(status);
        Wrappers.selectAutomationExp(driver, 5);
        HashSet<String> subjects = new HashSet<>();
        subjects.add("Java");
        subjects.add("Selenium");
        subjects.add("TestNG");
        Wrappers.selectLearnedSubj(driver, subjects);
        status = Wrappers.selectHowToBeAddressed(driver, "Mrs");
        Assert.assertTrue(status);
        System.out.println("How to be addressed selected");
        Thread.sleep(1000);
        Wrappers.enterDate(driver);
        Wrappers.enterTime(driver);
        Wrappers.clickOnSubmit(driver);
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