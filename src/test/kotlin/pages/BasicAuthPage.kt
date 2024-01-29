package pages

import com.codeborne.selenide.Selectors
import com.codeborne.selenide.Selenide
import io.qameta.allure.Step
import org.junit.jupiter.api.Assertions

class BasicAuthPage {
    private val successfulAuthorizeText
        get() = Selenide.`$`(Selectors.byXpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]"))

    @Step("Авторизация с кредами: user = {user}, password = {password}")
    fun authorize(user: String, password: String): BasicAuthPage {
        Selenide.open("https://$user:$password@the-internet.herokuapp.com/basic_auth")
        return this
    }

    @Step("Проверка: пользователь авторизован")
    fun authorizationIsSuccessful(): BasicAuthPage {
        Assertions.assertTrue(successfulAuthorizeText.isDisplayed, "successful text should be displayed")
        return this
    }

    @Step("Проверка: пользователь не авторизован")
    fun authorizationIsFailed(): BasicAuthPage {
        Assertions.assertFalse(successfulAuthorizeText.isDisplayed, "successful text should be displayed")
        return this
    }
}