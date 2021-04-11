package hw3.ex2steps;

import hw3.pageobjects.CommonElements;
import hw3.pageobjects.DifferentElementsPage;
import hw3.pageobjects.IndexPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Ex2Steps {
    WebDriver driver;
    private IndexPage indexPage;
    private DifferentElementsPage differentElementsPage;
    private CommonElements commonElements;
    private Properties properties;
    private SoftAssert softAssert;

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
        differentElementsPage = new DifferentElementsPage(driver);
        commonElements = new CommonElements(driver);
        indexPage = new IndexPage(driver);
        softAssert = new SoftAssert();
    }

    private void openDifferentElementsPage() {
        if (!commonElements.getDifferentElementsPage().isDisplayed()) {
            WebElement service = commonElements.getService();
            service.click();
        }
        WebElement toggle = commonElements.getDifferentElementsPage();
        toggle.click();
    }

    @When("I open SiteURL")
    public void openSiteURL() {
        driver.get(properties.getProperty("siteURL"));
        indexPage = new IndexPage(driver);
        commonElements = new CommonElements(driver);
    }

    @Then("Home page is opened")
    public void openSiteURLTest() {
        softAssert.assertEquals(driver.getCurrentUrl(), properties.getProperty("siteURL"), "wrong URL");
    }

    @When("I log in as {string} - {string}")
    public void login(String userName, String userPassword) throws IOException {
        indexPage.login(userName, userPassword);


    }

    @Then("I logged in as {string}")
    public void loginCheck(String expectedUsername) throws IOException {
        softAssert.assertEquals(indexPage.getUsername(), expectedUsername, "loggined as wrong person");
    }

    @Then("Browser title is {string}")
    public void homePage(String homePage) {
        Assert.assertEquals(driver.getTitle(), homePage, "wrong hope page");
    }

    @When("I click on Service")
    public void clickOnService() {
        WebElement service = commonElements.getService();
        service.click();
    }

    @Then("There are {int} elements with proper texts in the header")
    public void checkServiceTest(int num) {
        WebElement menu = indexPage.getDropdownMenu();
        ArrayList<String> serviceMenu = indexPage.getServiceElements();
        List<String> serviceMenuExpected = Arrays.asList(properties.getProperty("fullServiceMenu").split(","));
        softAssert.assertTrue(serviceMenu.contains(properties.getProperty("support")), "not contains support");
        softAssert.assertTrue(serviceMenu.contains(properties.getProperty("dates")), "not contains dates");
        softAssert.assertTrue(serviceMenu.contains(properties.getProperty("complexTable")), "not contains complex table");
        softAssert.assertTrue(serviceMenu.contains(properties.getProperty("simpleTable")), "not contains simple table");
        softAssert.assertTrue(serviceMenu.contains(properties.getProperty("tableWithPages")), "not contains table with pages");
        softAssert.assertTrue(serviceMenu.contains(properties.getProperty("differentElements")), "not contains different elements");
        softAssert.assertAll();
    }

    @When("I click on Service in the left section")
    public void clickOnServiceInLeftSection() {
        WebElement titleMenu = indexPage.getTitleMenu();
        titleMenu.click();
    }

    //    6)Click on Service subcategory in the left section and check that drop down contains options
    @Then("There are {int} elements with proper texts in the left section")
    public void checkLeftServiceTest(int num) {
        WebElement menu = indexPage.getSubTitleMenu();
        ArrayList<String> serviceMenu = indexPage.getTitleMenuElements();
        List<String> serviceMenuExpected = Arrays.asList(properties.getProperty("fullServiceMenuLowerCase").split(","));
        softAssert.assertTrue(serviceMenu.contains(properties.getProperty("supportLowerCase")), "not contains support");
        softAssert.assertTrue(serviceMenu.contains(properties.getProperty("datesLowerCase")), "not contains dates");
        softAssert.assertTrue(serviceMenu.contains(properties.getProperty("complexTableLowerCase")), "not contains complex table");
        softAssert.assertTrue(serviceMenu.contains(properties.getProperty("simpleTableLowerCase")), "not contains simple table");
        softAssert.assertTrue(serviceMenu.contains(properties.getProperty("tableWithPagesLowerCase")), "not contains table with pages");
        softAssert.assertTrue(serviceMenu.contains(properties.getProperty("differentElementsLowerCase")), "not contains different elements");
        softAssert.assertAll();
    }

    //    7)Open through the header menu Service -> Different Elements Page
    @When("I open Different Elements page")
    public void differentElementsPageTest() {
        openDifferentElementsPage();
    }

    @Then("There are {int} checkboxes and {int} radios and {int} buttons and {int} dropdown")
    public void checkingInterfaceTest(int box, int radio, int button, int dropdown) {
        softAssert.assertEquals(driver.getCurrentUrl(), properties.getProperty("differentElementsURL"));
        List<WebElement> checkboxList = differentElementsPage.getCheckboxList();
        List<WebElement> radioList = differentElementsPage.getRadioList();
        List<WebElement> dropdownList = differentElementsPage.getDropdownList();
        List<WebElement> buttonsList = differentElementsPage.getButtonsList();
        softAssert.assertEquals(box, checkboxList.size());
        softAssert.assertEquals(radio, radioList.size());
        softAssert.assertEquals(button, buttonsList.size());
        softAssert.assertEquals(dropdown, dropdownList.size());
        softAssert.assertAll();

    }

    @And("There is left section")
    public void leftSection() {
        softAssert.assertTrue(differentElementsPage.getLogSideBar().isDisplayed(), "there is no right section");
    }

    @And("There is right section")
    public void rightSection() {
        softAssert.assertTrue(commonElements.getLeftSection().isDisplayed(), "there is no left section");
    }

    @And("I open select checkboxes")
    public void selectCheckBoxes() {
        List<WebElement> checkboxList = differentElementsPage.getCheckboxList();
        WebElement water = checkboxList.get(0);
        WebElement wind = checkboxList.get(2);
        water.click();
        wind.click();
    }

    @And("Checkboxes are selected")
    public void checkBoxesSelected() {
        List<WebElement> checkboxList = differentElementsPage.getCheckboxList();
        softAssert.assertTrue(checkboxList.get(0).isSelected(), "water isn`t selected");
        softAssert.assertTrue(checkboxList.get(2).isSelected(), "wind isn`t selected");
        softAssert.assertAll();
    }

    @And("Log rows of checkboxes are displayed and correct")
    public void checkBoxesAreCorrect() {
        List<WebElement> logs = differentElementsPage.getLogs();
        softAssert.assertEquals(logs.size(), 2);
        softAssert.assertTrue(logs.get(0).isDisplayed(), "waterlog isn`t displayed");
        softAssert.assertTrue(logs.get(1).isDisplayed(), "windlog isn`t displayed");
        String waterlog = logs.get(1).getText();
        String windlog = logs.get(0).getText();
        softAssert.assertTrue(waterlog.contains("Water") && (waterlog.contains("true")), "waterlog dont contains water or true state");
        softAssert.assertTrue(windlog.contains("Wind") && (windlog.contains("true")), "windlog dont contains wind or true state");
        softAssert.assertAll();
    }

    @And("I select Selen")
    public void selectingSelen() {
        List<WebElement> radioList = differentElementsPage.getRadioList();
        WebElement selen = radioList.get(3);
        selen.click();
    }

    //    13)Select radio
    @And("Log rows of radios are displayed and correct")
    public void selectingRadioTest() {
        List<WebElement> radioList = differentElementsPage.getRadioList();
        WebElement selen = radioList.get(3);
        softAssert.assertTrue(selen.isSelected(), "selen isn`t selected");
        List<WebElement> logs = differentElementsPage.getLogs();
        for (int i = 0; i < logs.size(); i++) {
            String s = String.format("log %d isn`t displayed", i);
            softAssert.assertTrue(logs.get(i).isDisplayed(), s);
        }
        String selenlog = logs.get(0).getText();
        softAssert.assertTrue(selenlog.contains(properties.getProperty("selen")) && (selenlog.contains(properties.getProperty("metal"))), "selenlog dont contains selen or metal");
        softAssert.assertAll();
    }

    @And("I select Yellow")
    public void selectingYellow() {
        WebElement dropdown = differentElementsPage.getDropdown();
        dropdown.click();
        WebElement yellow = differentElementsPage.getYellow();
        yellow.click();
    }

    @And("Log rows of dropdown are displayed and correct")
    public void selectingInDropdownTest() {
        softAssert.assertTrue(differentElementsPage.getYellow().isSelected(), "yellow isn`t selected");
        List<WebElement> logs = differentElementsPage.getLogs();
        for (int i = 0; i < logs.size(); i++) {
            softAssert.assertTrue(logs.get(i).isDisplayed(), "");
        }
        String yellowlog = logs.get(0).getText();
        softAssert.assertTrue(yellowlog.contains(properties.getProperty("colors")) && (yellowlog.contains(properties.getProperty("yellow"))), "");
        softAssert.assertAll();
    }

    @And("I unselect checkboxes")
    public void unselectingCheckbox() {
        List<WebElement> checkboxList = differentElementsPage.getCheckboxList();
        WebElement water = checkboxList.get(0);
        WebElement wind = checkboxList.get(2);
        water.click();
        wind.click();
    }

    //    17)Unselect and assert checkboxes
    @And("Checkboxes not selected")
    public void unselectingCheckBoxTest() {
        List<WebElement> checkboxList = differentElementsPage.getCheckboxList();
        softAssert.assertFalse(checkboxList.get(0).isSelected(), "water is selected");
        softAssert.assertFalse(checkboxList.get(2).isSelected(), "wind is selected");
        //    18)Assert that for each checkbox there is an individual log row and value is corresponded to the status of checkbox.
        List<WebElement> logs = differentElementsPage.getLogs();
        for (int i = 0; i < logs.size(); i++) {
            String s = String.format("log %d isn`t displayed", i);
            softAssert.assertTrue(logs.get(i).isDisplayed(), s);
        }
        String windlog = logs.get(0).getText();
        String waterlog = logs.get(1).getText();
        softAssert.assertTrue(waterlog.contains(properties.getProperty("water")) && (waterlog.contains("false")), "wrong waterlog or its state in true");
        softAssert.assertTrue(windlog.contains(properties.getProperty("wind")) && (windlog.contains("false")), "wrong windlog or its state in true");
        softAssert.assertAll();
    }

    @After
    public void endWork() {
        driver.close();
    }
}
