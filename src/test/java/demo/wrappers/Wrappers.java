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

    public static void enterText(WebElement inputTextElement, String text) {
        try {
            inputTextElement.sendKeys(text);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }

    public static void selectCheckBox(WebDriver driver, List<WebElement> chkBoxList, HashSet<String> subjects) {
        try {
            jsScrollIntoView(driver, "i28");
            // List<WebElement> subjChkBoxList = driver.findElements(By.className("eBFwI"));

            for (WebElement elem : chkBoxList) {
                // String subjChkBoxText = elem.findElement(By.tagName("span")).getText();
                if (subjects.contains(elem.getText())) {
                    // WebElement chkBox = elem.findElement(By.className("uVccjd"));
                    clickElement(driver, elem);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }

    }

    public static void clickElement(WebDriver driver, WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }

    public static void selectRadioBtn(WebDriver driver, List<WebElement> radioBtnList, int noOfExp) {
        try {
            jsScrollIntoView(driver, "i11");

            for (WebElement elem : radioBtnList) {
                String expRadioBtnText = elem.getText();
                String[] expSplitArr = expRadioBtnText.split(" ");
                if (expSplitArr.length == 3) {
                    if (noOfExp >= Integer.parseInt(expSplitArr[0]) && noOfExp <= Integer.parseInt(expSplitArr[2])) {
                        clickElement(driver, elem);
                        break;
                    }
                } else {
                    clickElement(driver, elem);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }

    }

    public static void selectDropDown(WebDriver driver, String text) {
        try {
            jsScrollIntoView(driver, "i46");
            WebElement dropDownBox = driver.findElement(By.xpath("//div[contains(@class,'vQES8d')]/div"));
            dropDownBox.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'ncFHed')]//span[not(contains(text(),'Choose'))]")));

            List<WebElement> dropDownList = driver
                    .findElements(By.xpath("//div[contains(@class,'ncFHed')]//span[not(contains(text(),'Choose'))]"));

            for (WebElement option : dropDownList) {
                String optionValue = option.getText();
                System.out.println(optionValue);
                if (optionValue.equals(text)) {
                    clickElement(driver, option);
                    break;
                }
            }
            Thread.sleep(1000);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }

    }


    public static String getFormattedDate(String pattern, int noOfDays) {
        String dateString="";
        try {
            LocalDateTime date = LocalDateTime.now().minusDays(noOfDays);
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(pattern);

            dateString = date.format(dateFormat);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        return dateString;
    }

    public static String printSuccessMessage(WebDriver driver) {
        String successTxt = "";
        try {
            String currentUrl = driver.getCurrentUrl();
            
            if (currentUrl.contains("/formResponse")) {
                successTxt = driver.findElement(By.className("vHW8K")).getText();
            }
            System.out.println(successTxt);
            
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        return successTxt;
    }

    public static void jsScrollIntoView(WebDriver driver, String id) {
        WebElement quesSection = driver.findElement(By.id(id));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", quesSection);
    }

    public static String getEpochTIme() {
        long epoch = System.currentTimeMillis() / 1000;
        return String.valueOf(epoch);
    }
}
