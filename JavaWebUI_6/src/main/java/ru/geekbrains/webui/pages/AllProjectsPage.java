package ru.geekbrains.webui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.geekbrains.webui.base.BaseView;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AllProjectsPage extends BaseView {


    @FindBy(xpath = ".//a[@title='Создать проект']")
    private WebElement createNewProjectButton;

    public AllProjectsPage(WebDriver driver) {
        super(driver);
    }

    public NewProjectPage clickOnCreateNewProject() {
        createNewProjectButton.click();
        return new NewProjectPage(driver);
    }

    public AllProjectsPage checkNewProjectPopUp() {
        String message = wait10second.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//div[@class='message']"))).getText();
        System.out.println(message);
        assertTrue(message.contains("Проект сохранен"));
        return this;
    }


}
