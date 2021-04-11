package hw3.ex1steps;

import hw3.pageobjects.CommonElements;
import hw3.pageobjects.IndexPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Ex1Steps {
    private WebDriver driver;
    private IndexPage indexPage;
    private CommonElements commonElements;
    private Properties properties;

    @Before
    public void setup() throws IOException {
        Path resourceDirectory = Paths.get("src", "test", "resources", "test.properties");
        properties = new Properties();
        properties.load(new InputStreamReader(new FileInputStream(resourceDirectory.toFile()), StandardCharsets.UTF_8));
        System.setProperty("webdriver.chrome.driver", properties.getProperty("pathToDriver"));
        driver = new ChromeDriver();
        driver.navigate().to(properties.getProperty("siteURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @When("I open SiteURL")
    public void openSiteURL() {
        driver.get(properties.getProperty("siteURL"));
        indexPage = new IndexPage(driver);
        commonElements = new CommonElements(driver);
    }

    @Then("Home page is opened")
    public void openSiteURLTest() {
        Assert.assertEquals(driver.getCurrentUrl(), properties.getProperty("siteURL"));
    }

    @When("I log in as {string} - {string}")
    public void login(String userName, String userPassword) throws IOException {
        indexPage.login(userName, userPassword);


    }

    @Then("I logged in as {string}")
    public void loginCheck(String expectedUsername) throws IOException {
        Assert.assertEquals(indexPage.getUsername(), expectedUsername);
    }

    @Then("Browser title is {string}")
    public void homePage(String homePage) {
        Assert.assertEquals(driver.getTitle(), homePage);
    }

    @Then("There are expected items on the header section")
    public void checkItemsInHeader() {
        WebElement menu = commonElements.getTopSection();
        ArrayList<String> menuElements = commonElements.getTopSectionElements();
        List<String> serviceMenuExpected = Arrays.asList(properties.getProperty("serviceMenuExpected").split(","));
        Assert.assertTrue(menuElements.containsAll(serviceMenuExpected));
    }

    @Then("There are {int} images on the Index Page")
    public void checkImages(int num) {
        List<WebElement> imagesList = indexPage.getImagesList();
        Assert.assertEquals(num, imagesList.size());
    }

    @And("All images are displayed")
    public void checkImagesDisplayed() {
        List<WebElement> imagesList = indexPage.getImagesList();
        for (int i = 0; i < imagesList.size(); i++) {
            Assert.assertTrue(imagesList.get(i).isDisplayed());
        }
    }

    @Then("Assert that there are {int} texts on the Index Page under icons")
    public void checkUnderImagesText(int num) {
        List<WebElement> textList = indexPage.getTextList();
        Assert.assertEquals(num, textList.size());
        Assert.assertEquals(textList.get(0).getText(), properties.getProperty("underText1"));
        Assert.assertEquals(textList.get(1).getText(), properties.getProperty("underText2"));
        Assert.assertEquals(textList.get(2).getText(), properties.getProperty("underText3"));
        Assert.assertEquals(textList.get(3).getText(), properties.getProperty("underText4"));
    }

    @And("Assert a text of the main headers")
    public void checkTextAbove() {
        Assert.assertEquals(indexPage.getText1(), properties.getProperty("mainText1"));
        Assert.assertEquals(indexPage.getText2(), properties.getProperty("mainText2"));
    }

    @Then("IFrame is displayed")
    public void checkIframeTest() {
        List<WebElement> imagesList = driver.findElements(By.tagName("iframe"));
        Assert.assertTrue(imagesList.get(0).isDisplayed());
    }

    //    11)Switch to the iframe and check that there is Epam logo in the left top conner of iframe
    @When("I switch to the iframe")
    public void switchToFrame() {
        driver.switchTo().frame(properties.getProperty("frameSite"));
    }

    @Then("There is epam-logo")
    public void checkIframeEpamLogo() {
        Assert.assertFalse(indexPage.getLogo().isEmpty());
    }

    @And("I switch to original window back")
    public void switchBack() {
        driver.switchTo().parentFrame();
        driver.switchTo().defaultContent();
    }

    //    13)Assert a text of the sub header
    @Then("The text of the sub header is displayed")
    public void checkCenterText() {
        Assert.assertTrue(indexPage.getTextCenter().isDisplayed());
    }

    @And("The text of the sub header is {string}")
    public void checkCenterTex(String link) {
        Assert.assertEquals(indexPage.getTextCenter().getText(), link);
    }

    @And("The sub header is a link")
    public void checkCenterTe() {
        Assert.assertEquals(indexPage.getTextCenter().getTagName(), "a");
    }

    @And("The URL is {string}")
    public void checkCenterT(String link) {
        Assert.assertEquals(indexPage.getTextCenter().getAttribute("href"), link);
    }

    @Then("There is a Left Section")
    public void checkLeftSection() {
        Assert.assertTrue(commonElements.getLeftSection().isDisplayed());
    }

    @And("There is a Footer")
    public void checkFooter() {
        Assert.assertTrue(indexPage.getFooter().isDisplayed());
    }

    @After
    public void endWork() {
        driver.close();
    }
}
