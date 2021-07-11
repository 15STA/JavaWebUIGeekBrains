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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

@ExtendWith(TimingExtension.class)
public class CreateNewProjectTest {

    private final String LOGIN_PAGE_URL = "https://crm.geekbrains.space/user/login";
    private final String STUDENT_LOGIN = "Applanatest1";
    private final String STUDENT_PASSWORD = "Student2020!";
    private String projectName = "ST_newProject2021";
    private WebDriver driver;
    private static Logger logger = LoggerFactory.getLogger(CreateNewProjectTest.class);


    @BeforeAll
    public static void beforeAllTests() {
        logger.info("\n Создение нового проекта");
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

//    @AfterEach
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }


    @Test
    public void createNewProjectTest() {
        Actions action = new Actions(driver);
        WebElement menuProjects = driver.findElement(By.xpath(
                ".//ul[@class='nav nav-multilevel main-menu']/li[@class='dropdown']/a[@class='unclickable']/span[text()='Проекты']"));
        action.moveToElement(menuProjects).perform();
        driver.findElement(By.xpath(".//span[@class='title' and text()='Все проекты']")).click();

        WebDriverWait waitFiveSeconds = new WebDriverWait(driver, 7);
        waitFiveSeconds.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(
                ".//a[@title='Создать проект']"))));
        driver.findElement(By.xpath(".//a[@title='Создать проект']")).click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.urlContains("/create"));
        // field "name"
        driver.findElement(By.xpath(".//input[@name='crm_project[name]']")).sendKeys(projectName); //text field
        // field "point organization"
        driver.findElement(By.xpath("//span[@class='select2-chosen' and text()='Укажите организацию']")).click();
        driver.findElement(By.xpath(".//input[@class='select2-input select2-focused']")).sendKeys("GeekBrains1"); //text field\
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@class='select2-match' and text()='GeekBrains1']"))));
        driver.findElement(By.xpath("//span[@class='select2-match' and text()='GeekBrains1']")).click();
        //check-box type
        driver.findElement(By.name("crm_project[type]")).click(); //check-box
        // dropdowns
        Select crmProjectPriority = new Select(driver.findElement(By.name("crm_project[priority]")));
        crmProjectPriority.selectByValue("3");
        Select crmProjectFinanceSource = new Select(driver.findElement(By.name("crm_project[financeSource]")));
        crmProjectFinanceSource.selectByValue("1");
        Select crmProjectBusinessUnit = new Select(driver.findElement(By.name("crm_project[businessUnit]")));
        crmProjectBusinessUnit.selectByValue("1");
        Select crmProjectCurator = new Select(driver.findElement(By.name("crm_project[curator]")));
        crmProjectCurator.selectByValue("93");
        Select crmProjectRp = new Select(driver.findElement(By.name("crm_project[rp]")));
        crmProjectRp.selectByValue("40");
        Select crmProjectManager = new Select(driver.findElement(By.name("crm_project[manager]")));
        crmProjectManager.selectByValue("20");
        //button "Apply"
        driver.findElement(By.xpath("//div[@class='btn-group']/button[@class='btn btn-success action-button']")).click();
        //Assert - comparing result
        String message = new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//div[@class='message']"))).getText();
        // System.out.println(message);
        Assertions.assertTrue(message.contains("Проект сохранен"));
    }


    private void login() {
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
