package pages

import com.codeborne.selenide.Modal
import com.codeborne.selenide.Selenide

open class BasePage {

    fun closeModalWindow() {
        Selenide.executeJavaScript<Modal>("document.querySelector('.modal').style.display='none'")
        Selenide.actions().click().perform()
    }
}