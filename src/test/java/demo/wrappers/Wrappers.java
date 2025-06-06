package demo.wrappers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
    
    WebDriverWait wait;
    
    public static boolean enterName(WebDriver driver, String text){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'k3kHxc')]//input")));
        WebElement nameTextBoxDiv = driver.findElement(By.className("k3kHxc"));
        nameTextBoxDiv.click();
        WebElement nameTextBox = nameTextBoxDiv.findElement(By.tagName("input"));
        // WebElement nameTextBox = driver.findElement(By.xpath("//div[contains(@class, 'k3kHxc')]//input"));
        // nameTextBox.click();
        nameTextBox.sendKeys(text);
        return nameTextBox.getAttribute("data-initial-value").equals(text);
    }

    public static boolean enterPracticeAutomationReason(WebDriver driver, String text){
        long epoch = System.currentTimeMillis()/1000;
        WebElement automationTextArea = driver.findElement(By.className("KHxj8b"));
        automationTextArea.click();
        text = text+" "+epoch;
        automationTextArea.sendKeys(text);
        return automationTextArea.getAttribute("data-initial-value").equals(text);
    }

    public static void selectLearnedSubj(WebDriver driver, HashSet<String> subjects){
        jsScrollIntoView(driver, "i28");
        List<WebElement> subjChkBoxList = driver.findElements(By.className("eBFwI"));
        
        for(WebElement elem : subjChkBoxList){
            String subjChkBoxText = elem.findElement(By.tagName("span")).getText();
            if(subjects.contains(subjChkBoxText)){
                WebElement chkBox = elem.findElement(By.className("uVccjd"));
                chkBox.click();
            }
        }
        
    }

    public static void selectAutomationExp(WebDriver driver, int noOfExp){
        jsScrollIntoView(driver, "i11");
        List<WebElement> expRadioBtnList = driver.findElements(By.className("nWQGrd"));
        WebElement radioBtn = null;
        
        for(WebElement elem : expRadioBtnList){
            String expRadioBtnText = elem.findElement(By.tagName("span")).getText();
            String[] expSplitArr = expRadioBtnText.split(" ");
            if(expSplitArr.length == 3){
                if(noOfExp >= Integer.parseInt(expSplitArr[0]) && noOfExp <= Integer.parseInt(expSplitArr[2])){
                    radioBtn = elem.findElement(By.className("d7L4fc"));
                    break;
                }
            }
            else{
                radioBtn = elem.findElement(By.className("d7L4fc"));
            }
        }
        radioBtn.click();
    }
    
    public static boolean selectHowToBeAddressed(WebDriver driver, String text){
        
        jsScrollIntoView(driver, "i46");
        WebElement dropDownBox = driver.findElement(By.xpath("//div[contains(@class,'vQES8d')]/div"));
        dropDownBox.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'OA0qNb')]//div[contains(@class,'OIC90c')]")));
        
        List<WebElement> dropDownList = driver.findElements(By.xpath("//div[contains(@class,'OA0qNb')]//div[contains(@class,'OIC90c')]"));
        
        for(WebElement option : dropDownList){
            String optionValue = option.getAttribute("data-value");
            
            if(optionValue.equals(text)){
                option.click();
                break;
            }
        }

        List<WebElement> chkDropDownSelectedList = driver.findElements(By.xpath("//div[contains(@class,'ry3kXd')]//div[contains(@class,'OIC90c')]"));
        for(WebElement option : chkDropDownSelectedList){
            String optionValue = option.getAttribute("data-value");
            
            if(optionValue.equals(text)){
                return option.getAttribute("aria-selected").equals("true");
            }
        }
        return false;
    }

    public static void enterDate(WebDriver driver){
        
        jsScrollIntoView(driver, "i51");
        WebElement dateInput = driver.findElement(By.xpath("//div[contains(@class,'o7cIKf')]//input"));
        
        LocalDateTime date = LocalDateTime.now().minusDays(7);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        dateInput.sendKeys(date.format(dateFormat));
    }

    public static void enterTime(WebDriver driver){
        
        jsScrollIntoView(driver, "i57");
        WebElement hourInput = driver.findElement(By.xpath("(//div[contains(@class,'genAeb')]//input)[1]"));
        WebElement minInput = driver.findElement(By.xpath("(//div[contains(@class,'genAeb')]//input)[2]"));
        
        hourInput.sendKeys("07");
        minInput.sendKeys("30");
    }

    public static boolean clickOnSubmit(WebDriver driver){
        WebElement submitBtn = driver.findElement(By.className("Y5sE8d"));
        submitBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.urlContains("/formResponse"));
    }

    public static String printSuccessMessage(WebDriver driver){
        String currentUrl = driver.getCurrentUrl();
        String successTxt = "";
        if(currentUrl.contains("/formResponse")){
            successTxt = driver.findElement(By.className("vHW8K")).getText();     
        }
        System.out.println(successTxt);
        return successTxt;
    }

    public static void jsScrollIntoView(WebDriver driver, String id){
        WebElement quesSection = driver.findElement(By.id(id));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView();", quesSection);
    }
}
