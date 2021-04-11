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
    @FindBy(xpath = "/html/body/header/div/nav/ul[1]")
    WebElement topSection;
    @FindBy(xpath = "//*[contains(@class,'benefit-icon')]")
    WebElement images;
    @FindBy(xpath = "/html/body/div/div[2]/main/div[2]/h3[1]")
    WebElement text1;
    @FindBy(xpath = "/html/body/div/div[2]/main/div[2]/p")
    WebElement text2;
    @FindBy(xpath = "/html/body/div/div[2]/main/div[2]/h3[2]")
    WebElement textCenter;
    @FindBy(tagName = "iframe")
    WebElement iframe;
    @FindBy(id = "mCSB_1")
    WebElement leftSection;
    @FindBy(tagName = "footer")
    WebElement footer;
    @FindBy(xpath = "/html/body/header/div/nav/ul[1]/li[3]/ul/li[8]")
    WebElement differentElementsPage;
    @FindBy(className = "dropdown")
    WebElement service;
    @FindBy(xpath = "//*[contains(@class,'benefit-icon')]")
    List<WebElement> imagesList;
    @FindBy(xpath = "//*[contains(@class,'benefit-txt')]")
    List<WebElement> textList;
    @FindBy(className = "epam-logo")
    List<WebElement> logo;
    @FindBy(className = "dropdown-menu")
    WebElement dropdownMenu;
    @FindBy(className = "dropdown-menu")
    WebElement titleMenu;
    @FindBy(className = "sub")
    WebElement subTitleMenu;
    @FindBy(xpath = "//*[contains(@type,'checkbox')]")
    List<WebElement> checkboxList;
    @FindBy(xpath = "//*[contains(@type,'radio')]")
    List<WebElement> radioList;
    @FindBy(tagName = "select")
    List<WebElement> dropdownList;
    @FindBy(tagName = "select")
    WebElement dropdown;
    @FindBy(xpath = "//*[@class='uui-button']")
    List<WebElement> buttonsList;
    @FindBy(xpath = "//*[@name='log-sidebar']")
    WebElement logSideBar;
    @FindBy(xpath = "//*[@class='panel-body-list logs']/li")
    List<WebElement> logs;
    @FindBy(xpath = "/html/body/div/div[2]/main/div[2]/div/div[4]/select/option[4]")
    WebElement yellow;


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

    public String getText1() {
        return text1.getText();
    }

    public String getText2() {
        return text2.getText();
    }

    public WebElement getTextCenter() {
        return textCenter.findElement(By.tagName("a"));
    }

    public WebElement getLeftSection() {
        return leftSection;
    }

    public WebElement getFooter() {
        return footer;
    }
    public WebElement getDifferentElementsPage() {
        return differentElementsPage;
    }
    public WebElement getService() {
        return service;
    }
    public List<WebElement> getImagesList(){
        return imagesList;
    }

    public List<WebElement> getTextList() {
        return textList;
    }
    public List<WebElement> getLogo() {
        return logo;
    }
    public WebElement getDropdownMenu(){
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
    public WebElement getTitleMenu(){
        return titleMenu;
    }
    public WebElement getSubTitleMenu(){
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
    public WebElement getIframe(){
        return iframe;
    }

    public List<WebElement> getCheckboxList() {
        return checkboxList;
    }

    public List<WebElement> getRadioList() {
        return radioList;
    }

    public List<WebElement> getButtonsList() {
        return buttonsList;
    }

    public List<WebElement> getDropdownList() {
        return dropdownList;
    }
    public WebElement getLogSideBar(){
        return logSideBar;
    }
    public List<WebElement> getLogs(){
        return logs;
    }
    public WebElement getDropdown(){
        return dropdown;
    }
    public WebElement getYellow(){
        return yellow;
    }
    //    public List<WebElement> getImages(){
//        //List<WebElement> imagesList= image;
//        return images;
//    }

}
