package tests

import BaseTest
import com.codeborne.selenide.Condition
import com.codeborne.selenide.Condition.attribute
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selectors
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.webdriver
import com.codeborne.selenide.WebDriverConditions
import com.codeborne.selenide.WebDriverConditions.url
import com.codeborne.selenide.logevents.SelenideLogger.step
import config.ConfigManager
import io.qameta.allure.Allure
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import pages.BrokenImagesPage
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

    @Test
    @DisplayName("При нажатии на кнопку с меняющимся ID - должно измениться циферное значение в блоке canvas")
    fun canvasValueShouldBeChangedAfterClickButton() {
        val challengingDomPage = MenuItemPage().openChallengingDomPage()

        val oldValue = challengingDomPage.getAnswerValue()
        challengingDomPage.blueButton.click()
        val newValue = challengingDomPage.getAnswerValue()

        Allure.step("Циферное значение поля Answer изменено").run { assertNotEquals(oldValue,newValue) }
    }

    @Test
    @DisplayName("")
    fun imagesShouldBeNotBroken() {
        val brokenImagesPage = MenuItemPage().openBrokenImagesPage()

        val images = brokenImagesPage.images

        for (image in images) {
            // Проверяем, что изображение видимо на странице
            println(image.isImage)
            println(image.getAttribute("src"))
        }
        //Assertions
    }
}