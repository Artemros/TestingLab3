package hw3.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class IndexPage {
    @FindBy(id = "user-icon")
    WebElement userIcon;
    @FindBy(id = "name")
    WebElement userName;
    @FindBy(xpath = "/html/body/header/div/nav/ul[2]/li/a/div/span")
    WebElement gotUsername;
    @FindBy(id = "password")
    WebElement userPassword;
    @FindBy(id = "login-button")
    WebElement loginButton;
    @FindBy(xpath = "/html/body/div/div[2]/main/div[2]/h3[1]")
    WebElement text1;
    @FindBy(xpath = "/html/body/div/div[2]/main/div[2]/p")
    WebElement text2;
    @FindBy(xpath = "/html/body/div/div[2]/main/div[2]/h3[2]")
    WebElement textCenter;
    @FindBy(tagName = "footer")
    WebElement footer;
    @FindBy(xpath = "//*[contains(@class,'benefit-icon')]")
    List<WebElement> imagesList;
    @FindBy(xpath = "//*[contains(@class,'benefit-txt')]")
    List<WebElement> textList;
    @FindBy(className = "epam-logo")
    List<WebElement> logo;
    @FindBy(className = "dropdown-menu")
    WebElement dropdownMenu;
    @FindBy(className = "menu-title")
    WebElement titleMenu;
    @FindBy(className = "sub")
    WebElement subTitleMenu;


    public IndexPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void login(String name, String password) {
        userIcon.click();
        userName.sendKeys(name);
        userPassword.sendKeys(password);
        loginButton.click();
    }

    public String getUsername() {
        return gotUsername.getText();
    }

    public String getText1() {
        return text1.getText();
    }

    public String getText2() {
        return text2.getText();
    }

    public WebElement getTextCenter() {
        return textCenter.findElement(By.tagName("a"));
    }

    public WebElement getFooter() {
        return footer;
    }

    public List<WebElement> getImagesList() {
        return imagesList;
    }

    public List<WebElement> getLogo() {
        return logo;
    }

    public List<WebElement> getTextList() {
        return textList;
    }

    public WebElement getDropdownMenu() {
        return dropdownMenu;
    }

    public ArrayList<String> getServiceElements() {
        ArrayList<String> menu = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            String text = String.format("/html/body/header/div/nav/ul[1]/li[3]/ul/li[%d]", i + 1);
            WebElement support = dropdownMenu.findElement(By.xpath(text));
            menu.add(support.getText());
        }
        return menu;
    }

    public WebElement getTitleMenu() {
        return titleMenu;
    }

    public WebElement getSubTitleMenu() {
        return subTitleMenu;
    }

    public ArrayList<String> getTitleMenuElements() {
        ArrayList<String> serviceMenu = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            String text = String.format("//*[@id=\"mCSB_1_container\"]/ul/li[3]/ul/li[%d]", i + 1);
            WebElement support = subTitleMenu.findElement(By.xpath(text));
            serviceMenu.add(support.getText());
        }
        return serviceMenu;
    }
}
