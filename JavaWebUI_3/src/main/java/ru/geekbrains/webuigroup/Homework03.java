package ru.geekbrains.webuigroup;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Homework03 {
    private static final String LOGIN_PAGE_URL = "https://crm.geekbrains.space/user/login";
    private static final String STUDENT_LOGIN = "Applanatest1";
    private static final String STUDENT_PASSWORD = "Student2020!";
    private static final WebDriver driver;

    static {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //driver.manage().window().setSize(new Dimension(500, 500));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws InterruptedException {
        login();
        driver.findElement(By.xpath(
                ".//ul[@class='nav nav-multilevel main-menu']/li[@class='dropdown']/a[@class='unclickable']/span[text()='Проекты']")).click();
        driver.findElement(By.xpath(".//span[@class='title' and text()='Все проекты']")).click();

        WebDriverWait waitFiveSeconds = new WebDriverWait(driver, 7);
        waitFiveSeconds.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(
               ".//a[@title='Создать проект']"))));
        driver.findElement(By.xpath(".//a[@title='Создать проект']")).click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.urlContains("/create"));
        // field "name"
        driver.findElement(By.xpath(".//input[@name='crm_project[name]']")).sendKeys("ST_Project2021"); //text field
        // field "point organization"
        driver.findElement(By.xpath("//span[@class='select2-chosen' and text()='Укажите организацию']")).click();
        driver.findElement(By.xpath(".//input[@class='select2-input select2-focused']")).sendKeys("GeekBrains1"); //text field
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
    }

    private static void login() {
        driver.get(LOGIN_PAGE_URL);
        WebElement loginTextInput = driver.findElement(By.xpath(".//input[@name= '_username']"));
        loginTextInput.sendKeys(STUDENT_LOGIN);

        WebElement passwordTextInput = driver.findElement(By.xpath(".//input[@name= '_password']"));
        passwordTextInput.sendKeys(STUDENT_PASSWORD);

        WebElement loginButton = driver.findElement(By.xpath(".//button[@name='_submit']"));
        loginButton.click();

    }
}
