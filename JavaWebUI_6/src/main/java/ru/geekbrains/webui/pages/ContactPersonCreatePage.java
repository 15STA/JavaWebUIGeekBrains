package ru.geekbrains.webui.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import ru.geekbrains.webui.base.BaseView;

public class ContactPersonCreatePage extends BaseView {

    @FindBy(xpath = "//h1[@class='user-name']")
    private WebElement pageTitle;

    @FindBy(xpath = ".//input[@name='crm_contact[lastName]']")
    private WebElement lastNameInput;

    @FindBy(xpath = ".//input[@name='crm_contact[firstName]']")
    private WebElement  firstNameInput;

    @FindBy(xpath = "//span[@class='select2-arrow']")
    private WebElement  findOrganizationClick;

    @FindBy(xpath = "//input[contains (@class,'select2-input')]")
    private WebElement organizationInputForSearch;

    @FindBy(xpath = "//div[@class='select2-result-label']")
    private WebElement selectedOrganizationClick;

    @FindBy(xpath = ".//input[@name='crm_contact[jobTitle]']")
    private WebElement inputJobTitle;

    @FindBy(xpath = "//div[@class='btn-group']/button[@class='btn btn-success action-button']")
    private WebElement buttonSafeAndClose;

    public ContactPersonCreatePage(WebDriver driver) {
        super(driver);
    }

    public ContactPersonCreatePage checkPageTitle(){
        wait10second.until(ExpectedConditions.visibilityOf(pageTitle));
        Assertions.assertEquals(pageTitle.getText(), "Создать контактное лицо");
        return this;
    }

    public ContactPersonCreatePage enterLastName(String lastName){
        lastNameInput.sendKeys(lastName);
        return this;
    }

    public ContactPersonCreatePage enterFirstName(String firstName){
        firstNameInput.sendKeys(firstName);
        return this;
    }

    public ContactPersonCreatePage pointOrganization(String organization) {
        findOrganizationClick.click();
        organizationInputForSearch.clear();
        organizationInputForSearch.sendKeys(organization);
        wait10second.until(ExpectedConditions.visibilityOf(selectedOrganizationClick));
        selectedOrganizationClick.click();
        return this;
    }

    public ContactPersonCreatePage enterJobTitle(String jobTitle){
        inputJobTitle.sendKeys(jobTitle);
        return this;
    }

    public AllContactPersonsPage clickSafeAndClose() {
        buttonSafeAndClose.click();
        return new AllContactPersonsPage(driver);
    }
}

