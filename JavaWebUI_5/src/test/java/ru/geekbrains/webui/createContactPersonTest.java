package ru.geekbrains.webui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

@ExtendWith(TimingExtension.class)
public class CreateContactPersonTest {
    private final String LOGIN_PAGE_URL = "https://crm.geekbrains.space/user/login";
    private final String STUDENT_LOGIN = "Applanatest1";
    private final String STUDENT_PASSWORD = "Student2020!";
    private WebDriver driver;
    private static Logger logger = LoggerFactory.getLogger(CreateNewProjectTest.class);

    @BeforeAll
    static void beforeAllTests() {
        logger.info("\n Создение контактного лица");
    }

    @BeforeAll
    public static void setupWebDriverManager() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeTest() {
        setUpDriverSession();
        login();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Создание контактного лица")
    public void createContactPersonTest() {
        Actions action = new Actions(driver);
        WebElement menuProjects = driver.findElement(By.xpath(
                "//ul[@class='nav nav-multilevel main-menu']/li[@class='dropdown first']/a[@class='unclickable']/span[text()='Контрагенты']"));
        action.moveToElement(menuProjects).perform();

        driver.findElement(By.xpath(".//span[@class='title' and text()='Контактные лица']")).click();

        WebDriverWait waitFiveSeconds = new WebDriverWait(driver, 7);
        waitFiveSeconds.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(
                ".//a[@title='Создать контактное лицо']"))));
        driver.findElement(By.xpath(".//a[@title='Создать контактное лицо']")).click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.urlContains("/create"));

        // field "lastName"
        driver.findElement(By.xpath(".//input[@name='crm_contact[lastName]']")).sendKeys("Chaplin"); //text field
        // field "firstName"
        driver.findElement(By.xpath(".//input[@name='crm_contact[firstName]']")).sendKeys("Charly"); //text field
        driver.findElement(By.xpath("//span[@class='select2-chosen' and text()='Укажите организацию']")).click();
        driver.findElement(By.xpath("//div[@class='select2-search']//input[@type='text']")).sendKeys("Bins-Haley"); //text field  ////input[@class='select2-input']
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@class='select2-match' and text()='Bins-Haley']"))));
        driver.findElement(By.xpath("//span[@class='select2-match' and text()='Bins-Haley']")).click();

        //field Position (JobTitle)
        driver.findElement(By.xpath(".//input[@name='crm_contact[jobTitle]']")).sendKeys("Actor"); //text field

        //button "Apply"
        driver.findElement(By.xpath("//div[@class='btn-group']/button[@class='btn btn-success action-button']")).click();
        //Assert - comparing result
        String message = new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//div[@class='message']"))).getText();
       // System.out.println(message);
        Assertions.assertTrue(message.contains("Контактное лицо сохранено"));
    }

    protected void login() {
        driver.get(LOGIN_PAGE_URL);

        WebElement loginTextInput = driver.findElement(By.cssSelector("input[id='prependedInput']"));
        loginTextInput.sendKeys(STUDENT_LOGIN);

        WebElement passwordTextInput = driver.findElement(By.cssSelector(".span2[name='_password']"));
        passwordTextInput.sendKeys(STUDENT_PASSWORD);

        WebElement loginButton = driver.findElement(By.xpath(".//button[@name='_submit']"));
        loginButton.click();
    }

    private void setUpDriverSession() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
}
