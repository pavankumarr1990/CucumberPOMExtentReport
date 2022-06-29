package common;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

import static common.SeleniumDriver.getDriver;

public class Utilities {
    WebDriver driver;

    public Utilities(WebDriver driver) {
        this.driver = getDriver();
    }

    JavascriptExecutor executor = (JavascriptExecutor) getDriver();
    public int normalWaitTime = 120;

    public void waitUntilElementPresence(By locator, int timeOutInSeconds) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(timeOutInSeconds)).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitUntilElementPresence(String xpath, int timeOutInSeconds) {
        By locator = By.xpath(xpath);
        new WebDriverWait(getDriver(), Duration.ofSeconds(timeOutInSeconds)).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitUntilElementVisible(WebElement webElement, int timeOutInSeconds) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(timeOutInSeconds)).until(ExpectedConditions.visibilityOfElementLocated((By) webElement));
    }

    public void waitUntilElementVisiblePavan(WebElement webElement, int timeOutInSeconds) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(timeOutInSeconds)).until(ExpectedConditions.visibilityOfElementLocated((By) webElement));
    }

    public void clickElement(WebElement webElement) throws InterruptedException {
        webElement.wait(normalWaitTime * 1000);
        if (!driverClick(webElement)) {
            executor.executeScript("arguments[0].click();", webElement);
        }
    }

    public void clickElement(By locator) {
        waitUntilElementPresence(locator, normalWaitTime);
        if (!driverClick(locator)) {
            executor.executeScript("arguments[0].click();", getDriver().findElement(locator));
        }
    }

    public Boolean driverClick(WebElement webElement) {
        try {
            webElement.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean driverClick(By locator) {
        try {
            getDriver().findElement(locator).click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean checkVisibility(WebElement webElement) {
        try {
            webElement.wait(2000);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean checkVisibility(By locator) {
        try {
            new WebDriverWait(getDriver(), Duration.ofSeconds(2)).until(ExpectedConditions.visibilityOfElementLocated(locator));
            getDriver().findElement(locator).wait(6000);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean checkVisibilityWithWait(WebElement webElement, int timeOutInSeconds) {
        try {
            webElement.wait(timeOutInSeconds * 1000);
            webElement.wait();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean checkPresence(WebElement webElement) {
        try {
            webElement.wait(5000);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean checkPresence(By locator) {
        try {
            new WebDriverWait(getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(locator));
            //getDriver().findElement(locator).waitForPresent(2000);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getTextValue(WebElement webElement) throws InterruptedException {
        webElement.wait(normalWaitTime * 1000);
        String text = webElement.getAttribute("value");
        if (text != "" && text != null) {
            return text;
        }
        text = webElement.getText();
        if (text != "" && text != null) {
            return text;
        }
        text = javaExGetValue(webElement);
        if (text != "" && text != null) {
            return text;
        }
        return null;
    }

    public String javaExGetValue(WebElement webElement) {
        String value = (String) executor.executeScript("return arguments[0].value", webElement);
        return value;
    }

    public String getSelectedDropDownValue(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(120));
        try {
            waitUntilElementVisible((WebElement) webElement, normalWaitTime);
            Select select = new Select(webElement);
            return select.getFirstSelectedOption().getText();
        } catch (Exception e) {
            return null;
        }
    }

    public String generateRandomName(String startString) {
        return startString + new Random().nextInt(10000);
    }

    public boolean typeStrValue(WebElement webElement, String value) {
        try {
//            waitUntilElementPresence((By) webElement, normalWaitTime);
            webElement.clear();
            Thread.sleep(200);
            webElement.sendKeys(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        return dateFormat.format(date).replace("-", "/");
    }

    public String addDaysToDate(String mmDDYYYY, int numberOfDays) throws Exception {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = dateFormat.parse(mmDDYYYY);
        cal.setTime(date);
        cal.add(Calendar.DATE, numberOfDays);
        date = cal.getTime();
        return dateFormat.format(date);
    }

    public String getCurrentDateFormat(String dateFormatStr) {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
        return dateFormat.format(date);
    }

    public String addDaysToDateFormat(String dateStr, int numberOfDays, String dateFormatStr) throws Exception {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
        Date date = dateFormat.parse(dateStr);

        cal.setTime(date);
        cal.add(Calendar.DATE, numberOfDays);
        date = cal.getTime();
        return dateFormat.format(date);
    }

    public Boolean compareDateEqual(String dateOneStr, String dateTwoStr, String dateFormatStr) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormatStr);
        Date dateOne = simpleDateFormat.parse(dateOneStr);
        Date dateTwo = simpleDateFormat.parse(dateTwoStr);
        return dateOne.equals(dateTwo);
    }

    public void switchToFrame(WebElement frameElement) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(120)).
                until(ExpectedConditions.
                        frameToBeAvailableAndSwitchToIt(frameElement));
    }
    public Boolean scrollToView(WebElement elementParam) {
        try {
            executor.executeScript("arguments[0].scrollIntoView(true);", (elementParam));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public void scrollToUp() {
        executor.executeScript("document.documentElement.scrollTop = 0;");
    }
    public void scrollToDown() {
        executor.executeScript("document.documentElement.scrollTop=4000;");
    }
    public void clearField(WebElement element) {
        Actions action = new Actions(getDriver());
        action.moveToElement(element).click().sendKeys(Keys.HOME).keyDown(Keys.SHIFT).sendKeys(Keys.END)
                .keyUp(Keys.SHIFT).sendKeys(Keys.BACK_SPACE).build().perform();
    }
    public void clickOnElementWithWait(By object, long wait) throws InterruptedException {
        getDriver().findElement(object).wait(wait);
        getDriver().findElement(object).click();
    }
    public void clickOnElementWithWait(String object, int wait) throws InterruptedException {
        getDriver().findElement(By.xpath(object)).wait(wait);
        getDriver().findElement(By.xpath(object)).click();
    }
    public void writeJson(String key, String value) {
        String folderSep = System.getProperty("file.separator");
        String filePath = System.getProperty("user.dir") + folderSep + "src" + folderSep + "main" +
                folderSep + "resources" + folderSep + "data" + folderSep + "testData.json";
        try {
            Reader reader = new FileReader(filePath);
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            reader.close();
            jsonObject.put(key, value);
            FileWriter jsonFile = new FileWriter(filePath);
            jsonFile.write(jsonObject.toJSONString());
            jsonFile.flush();
            jsonFile.close();
        } catch (Exception e) {
            Assert.assertTrue(false,"Unable to write the data to the Json file");
        }
    }
    Assert Validator;
    public String readJson(String key) {
        JSONParser parser = new JSONParser();
        String folderSep = System.getProperty("file.separator");
        String filePath = System.getProperty("user.dir") + folderSep + "src" + folderSep + "main" +
                folderSep + "resources" + folderSep + "data" + folderSep + "testData.json";
        try {
            Reader reader = new FileReader(filePath);
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            reader.close();
            return jsonObject.get(key).toString().trim();
        } catch (Exception e) {
            e.printStackTrace();
            Validator.assertTrue(false, "Unable to read from JSON file" + filePath);
        }
        return "";
    }
}
