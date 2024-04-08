package pageElements

import com.codeborne.selenide.SelenideElement
import io.qameta.allure.Step

class Button(val elem: SelenideElement, val name: String) {

    @Step("Нажатие на кнопку {this.name}")
    fun click() : SelenideElement {
        elem.click()
        return elem
    }
}