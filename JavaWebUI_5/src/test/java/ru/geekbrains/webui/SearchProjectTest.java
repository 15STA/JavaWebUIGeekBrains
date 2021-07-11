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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(TimingExtension.class)
public class SearchProjectTest {

    private final String LOGIN_PAGE_URL = "https://crm.geekbrains.space/user/login";
    private final String STUDENT_LOGIN = "Applanatest1";
    private final String STUDENT_PASSWORD = "Student2020!";
    private String projectName = "ST_newProject2021";
    private WebDriver driver;
    private static Logger logger = LoggerFactory.getLogger(SearchProjectTest.class);

    @BeforeAll
    public static void beforeAllTests() {
        logger.info("\n Поиск проекта по имени");
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
    @DisplayName("Поиск проекта")
    public void findProject(){
        Actions action = new Actions(driver);
        driver.findElement(By.xpath("//a[@title = 'Поиск']/i[@class='icon-search']")).click();
        driver.findElement(By.xpath("//input[@id='search-bar-search']")).sendKeys(projectName);
        driver.findElement(By.xpath("//button[@class='btn btn-search btn-primary' and text()='Искать']")).click();
        //List<WebElement> names = driver.findElements(By.xpath("//*[contains(text(), 'ST_Project2021')]"));
        By mySelector = By.xpath("//h1[@class='user-name']/*[contains(text(), projectName)]");
        List<WebElement> names = driver.findElements(mySelector);
        if (names.isEmpty()) {
            logger.info("\n Проект не найден!");
            fail("Проект не найден!");
        }
        else {
        for(WebElement e : names) {
//            System.out.println(e + "-");
//            System.out.println(e.getText());
            if (e.getText().equals(projectName)) {
                e.click();
            }
        }
        }
        Assertions.assertTrue(driver.findElement(By.xpath(
                "//h1[@class = 'user-name']")).getText().equals(projectName));

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
