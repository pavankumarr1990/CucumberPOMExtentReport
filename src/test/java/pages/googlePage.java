package pages;

import common.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static common.SeleniumDriver.getDriver;

public class googlePage {

    Utilities utilities=new Utilities(getDriver());


    WebDriver driver;
    public googlePage(WebDriver driver)
    {
        this.driver=getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//input[@title='Search']")
    private WebElement type_googleSearch;
    @FindBy(xpath="//input[@title='Search']")
    private WebElement type_googleBtn;

    public void googleSearch_Box() throws InterruptedException {
        utilities.typeStrValue(type_googleSearch, "Narashima swamy");
        utilities.clickElement(type_googleBtn);
        System.out.println("Narashima swamy");
    }

}
