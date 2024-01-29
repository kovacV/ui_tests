package tests

import BaseTest
import com.codeborne.selenide.Selenide.webdriver
import com.codeborne.selenide.WebDriverConditions.url
import com.codeborne.selenide.logevents.SelenideLogger.step
import config.ConfigManager
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import pages.MenuItemPage

class Tests : BaseTest() {

    @Test
    @DisplayName("После открытия сайта - в строке поиска отображен ожидаемый url")
    fun urlShouldBeEqualToExpectedUrl() {
        step("Проверка URL в поисковой строке") {
            webdriver().shouldHave(url(ConfigManager.prop.getProperty("URL")))
        }
    }

    @Test
    @DisplayName("После нажатия на кнопку 'Add Element' - добавляется кнопка 'Delete'")
    fun deleteButtonShouldBeDisplayedAfterClickAddButton() {
        val menuItemPage = MenuItemPage()

        menuItemPage
            .openAddRemoveElementsItem()
            .addElement()
            .deleteButton.isDisplayed
    }

    @Test
    @DisplayName("Пользователь должен быть авторизованным после ввода валидных кредов")
    fun userShouldBeAuthorizedAfterLogInWithValidCredentials() {
        val menuItemPage = MenuItemPage()

        menuItemPage
            .openBasicAuthItem()
            .authorize("admin", "admin")
            .authorizationIsSuccessful()
    }

    @Test
    @DisplayName("При вводе невалидных кредов - отображается пустая страница")
    fun userShouldNotBeAuthorizedAfterLogInWithInvalidCredentials() {
        val menuItemPage = MenuItemPage()

        menuItemPage
            .openBasicAuthItem()
            .authorize("invalid", "invalid")
            .authorizationIsFailed()
    }

    @Test
    @DisplayName("При вызове контекстного меню на box - вызывается алерт с текстом 'You selected a context menu'")
    fun contextMenuOnBoxShouldOpenAlertWithText() {
        val menuItemPage = MenuItemPage()

        menuItemPage
            .openContextMenuPage()
            .callContextMenu()
            .verifyContextMenuIsOpen()
    }
}