package hw3.ex1;


import hw3.pageobjects.IndexPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Ex1Tests {
    WebDriver driver;
    private IndexPage indexPage;

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Пиглин/IdeaProjects/spbstu/TestinLab2/src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("https://jdi-testing.github.io/jdi-light/index.html");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        indexPage = new IndexPage(driver);
    }

    //    1)Open test site by URL
    @Test
    public void openSiteURLTest() {
        driver.get("https://jdi-testing.github.io/jdi-light/index.html");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://jdi-testing.github.io/jdi-light/index.html");
    }

    //    2)Assert Browser title
    @Test(dependsOnMethods = "openSiteURLTest")
    public void HomePageTest() {
        String currentUrl = driver.getTitle();
        Assert.assertEquals(currentUrl, "Home Page");
    }

    //    3)Perform login
    @Test(dependsOnMethods = "HomePageTest")
    public void LoginTest() {
        indexPage.login("Roman", "Jdi1234");
        //    4)Assert User name in the left-top side of screen that user is loggined
        String expectedUsername = "ROMAN IOVLEV";
        Assert.assertEquals(indexPage.getUsername(), expectedUsername);
    }

    //    5)Assert Browser title
    @Test(dependsOnMethods = "LoginTest")
    public void HomePageTest2() {
        String currentUrl = driver.getTitle();
        Assert.assertEquals(currentUrl, "Home Page");
    }

    //    6)Assert that there are 4 items on the header section are displayed and they have proper texts
    @Test(dependsOnMethods = "LoginTest")
    public void CheckItemsInHeaderTest() {
        WebElement menu = indexPage.getTopSection();
        ArrayList<String> menuElements = indexPage.getTopSectionElements();
        List<String> serviceMenuExpected = Arrays.asList("HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS");
        Assert.assertTrue(menuElements.containsAll(serviceMenuExpected));
    }

    //    7)Assert that there are 4 images on the Index Page and they are displayed
    @Test(dependsOnMethods = "LoginTest")
    public void CheckImagesTest() {
        List<WebElement> imagesList = indexPage.getImagesList();
        Assert.assertEquals(4, imagesList.size());
        Assert.assertTrue(imagesList.get(0).isDisplayed());
        Assert.assertTrue(imagesList.get(1).isDisplayed());
        Assert.assertTrue(imagesList.get(2).isDisplayed());
        Assert.assertTrue(imagesList.get(3).isDisplayed());
    }

    //    8)Assert that there are 4 texts on the Index Page under icons and they have proper text
    @Test(dependsOnMethods = "LoginTest")
    public void CheckUnderImagesTextTest() {
        List<WebElement> textList = indexPage.getTextList();
        Assert.assertEquals(4, textList.size());
        Assert.assertEquals(textList.get(0).getText(), "To include good practices\n" +
                "and ideas from successful\n" +
                "EPAM project");
        Assert.assertEquals(textList.get(1).getText(), "To be flexible and\n" +
                "customizable");
        Assert.assertEquals(textList.get(2).getText(), "To be multiplatform");
        Assert.assertEquals(textList.get(3).getText(), "Already have good base\n" +
                "(about 20 internal and\n" +
                "some external projects),\n" +
                "wish to get more…");
    }

    //    9)Assert a text of the main headers
    @Test(dependsOnMethods = "LoginTest")
    public void CheckTextAboveTest() {
        Assert.assertEquals(indexPage.getText1(), "EPAM FRAMEWORK WISHES…");
        Assert.assertEquals(indexPage.getText2(), "LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISICING ELIT, SED DO EIUSMOD TEMPOR INCIDIDUNT UT LABORE ET DOLORE MAGNA ALIQUA. UT ENIM AD MINIM VENIAM, QUIS NOSTRUD EXERCITATION ULLAMCO LABORIS NISI UT ALIQUIP EX EA COMMODO CONSEQUAT DUIS AUTE IRURE DOLOR IN REPREHENDERIT IN VOLUPTATE VELIT ESSE CILLUM DOLORE EU FUGIAT NULLA PARIATUR.");
    }

    //    10)Assert that there is the iframe in the center of page
    @Test(dependsOnMethods = "LoginTest")
    public void CheckIframeTest() {
        List<WebElement> imagesList = driver.findElements(By.tagName("iframe"));
        Assert.assertTrue(imagesList.get(0).isDisplayed());
    }

    //    11)Switch to the iframe and check that there is Epam logo in the left top conner of iframe
    @Test(dependsOnMethods = "LoginTest")
    public void CheckIframeEpamLogoTest() {
        driver.switchTo().frame("jdi-frame-site");
        List<WebElement> logo = indexPage.getLogo();
        Assert.assertFalse(logo.isEmpty());
        //    12)Switch to original window back
        driver.switchTo().parentFrame();
        driver.switchTo().defaultContent();
    }

    //    13)Assert a text of the sub header
    @Test(dependsOnMethods = "LoginTest")
    public void CheckCenterTextTest() {
        WebElement centerText = indexPage.getTextCenter();
        Assert.assertEquals(centerText.getText(), "JDI GITHUB");
        Assert.assertTrue(centerText.isDisplayed());
    //    14)Assert that JDI GITHUB is a link and has a proper URL
        Assert.assertEquals(centerText.getTagName(), "a");
        Assert.assertEquals(centerText.getAttribute("href"), "https://github.com/epam/JDI");
    }

    //    15)Assert that there is Left Section
    @Test(dependsOnMethods = "LoginTest")
    public void CheckLeftSectionTest() {
        WebElement leftSection = indexPage.getLeftSection();
        Assert.assertTrue(leftSection.isDisplayed());
    }

    //    16)Assert that there is Footer
    @Test(dependsOnMethods = "LoginTest")
    public void CheckFooterTest() {
        WebElement footer = indexPage.getFooter();
        Assert.assertTrue(footer.isDisplayed());
    }

    //    17)Close Browser
    @AfterTest
    public void endWork() {
        driver.close();
    }
}

