package stepDef;


import com.aventstack.extentreports.Status;
import common.ExtentManager;
import common.ExtentTestManager;
import common.SeleniumDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import java.io.File;
import java.nio.file.Files;

public class AfterAndBefore {
    protected Scenario scenario;
    static String scenarioName;

    public String urls = "";

    //    @Before
//    public void setUp() {
//        //ExtentCucumberFormatter.initiateExtentCucumberFormatter();
//        System.out.println("Before");
//        SeleniumDriver.setUpDriver();
//    }
    @Before
    public void setUp(Scenario scenario) {
        this.scenario = scenario;
        scenarioName = scenario.getName();
        System.out.println("Before");
        ExtentTestManager.startTest(scenario.getName());
        ExtentTestManager.getTest().log(Status.INFO, "Scenarion Started" + scenario.getName());
        SeleniumDriver.setUpDriver();
    }


//    @After
//    public static void tearDown(Scenario scenario) {
//        System.out.println("After");
//        System.out.println("Pavan After");
//        WebDriver driver = SeleniumDriver.getDriver();
//        System.out.println(scenario.isFailed());
//        if (scenario.isFailed()) {
//            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//            scenario.attach(screenshotBytes, "image/png", scenario.getName());
//
////            scenario.attach(screenshotBytes, "image/png", "image");
//
//            System.out.println("Pavan After sceenshot");
//        }
//        System.out.println("TearDown");
//        SeleniumDriver.tearDown();
//    }

    @After
    public static void after(Scenario scenario) {
        if (scenario.isFailed()) {
            ExtentTestManager.logFail("Scenario Failed");
            ExtentTestManager.addScreenShotsOnFailure();
        } else {
            ExtentTestManager.scenarioPass();
        }
        ExtentManager.getReporter().flush();
        System.out.println("TearDown");
        SeleniumDriver.tearDown();
    }


}

