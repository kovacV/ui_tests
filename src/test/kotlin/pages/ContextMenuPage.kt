package pages

import com.codeborne.selenide.Selectors
import com.codeborne.selenide.Selenide
import io.qameta.allure.Step
import org.junit.jupiter.api.Assertions

class ContextMenuPage {
    private val expectedAlertText = "You selected a context menu"
    private val boxArea
        get() = Selenide.`$`(Selectors.byCssSelector("#hot-spot"))

    @Step("Вызов контекстного меню")
    fun callContextMenu() : ContextMenuPage {
        boxArea.contextClick()
        return this
    }

    @Step("Проверка: контекстное меню открыто")
    fun verifyContextMenuIsOpen() {
        val actualAlertText = Selenide.switchTo().alert().text
        Assertions.assertEquals(expectedAlertText, actualAlertText, "alert text should be $expectedAlertText")
    }
}