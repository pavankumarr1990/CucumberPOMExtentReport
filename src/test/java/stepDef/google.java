package stepDef;

import common.SeleniumDriver;
import common.Utilities;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.googlePage;

import static common.SeleniumDriver.getDriver;

public class google {
    //    WebDriver driver;
//    public google(WebDriver driver)
//    {
//        this.driver=getDriver();
//    }
    Utilities utilities=new Utilities(getDriver());
    @Given("^Open browser and navigate to \"([^\"]*)\"$")
    public void open_browser_and_navigate_to_something(String strArg1) {
        SeleniumDriver.openPage(strArg1);
    }

    googlePage gp=new googlePage(getDriver());
    @Then("^Fail the test case$")
    public void fail_the_test_case() throws Throwable {
        Thread.sleep(5000);
        gp.googleSearch_Box();
        Thread.sleep(5000);
    }

}