package ru.geekbrains.webui.projects;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.geekbrains.webui.base.BaseUiTest;
import ru.geekbrains.webui.configuration.Configuration;
import ru.geekbrains.webui.enums.NavigationBarTabs;
import ru.geekbrains.webui.enums.ProjectSubMenuButtons;
import ru.geekbrains.webui.pages.AllProjectsPage;
import ru.geekbrains.webui.pages.LoginPage;

@Feature("New project")
public class NewProjectTest extends BaseUiTest {

    //@Disabled
    @DisplayName("Create a new project test")
    @Description("Create a new project")
    @Test
    public void createNewProjectPositiveTest(){
        AllProjectsPage projectScreen = (AllProjectsPage) new LoginPage(driver)
                .authoriseScenario(Configuration.LOGIN, Configuration.PASSWORD)
                .getPageNavigation()
                .moveCursorToNavigationTab(NavigationBarTabs.PROJECTS)
                .clickSubMenuButton(ProjectSubMenuButtons.PROJECT_REQUEST);

        projectScreen
                .clickOnCreateNewProject()
                .checkPageTitle()
                .enterProjectName("ST_21+")
                .pointOrganization("GeekBrains1")
                .clickProjectType()
                .selectProjectPriority(3)
                .selectProjectFinanceSource(1)
                .selectProjectBusinessUnit(1)
                .selectProjectCurator(93)
                .selectProjectRp(40)
                .selectProjectManager(20)
                .clickSafeAndClose()
                .checkNewProjectPopUp();
    }
}
