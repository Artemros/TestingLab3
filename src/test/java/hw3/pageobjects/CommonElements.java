package hw3.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

public class CommonElements {
    @FindBy(id = "mCSB_1")
    WebElement leftSection;
    @FindBy(className = "dropdown")
    WebElement service;
    @FindBy(xpath = "/html/body/header/div/nav/ul[1]/li[3]/ul/li[8]")
    WebElement differentElementsPage;
    @FindBy(xpath = "/html/body/header/div/nav/ul[1]")
    WebElement topSection;

    public CommonElements(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getLeftSection() {
        return leftSection;
    }

    public WebElement getService() {
        return service;
    }

    public WebElement getDifferentElementsPage() {
        return differentElementsPage;
    }

    public WebElement getTopSection() {
        return topSection;
    }

    public ArrayList<String> getTopSectionElements() {
        ArrayList<String> menuElements = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            String text = String.format("/html/body/header/div/nav/ul[1]/li[%d]", i + 1);
            WebElement support = topSection.findElement(By.xpath(text));
            menuElements.add(support.getText());
        }
        return menuElements;
    }
}
