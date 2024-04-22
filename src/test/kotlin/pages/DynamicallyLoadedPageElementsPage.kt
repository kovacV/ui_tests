package pages

import com.codeborne.selenide.Selectors
import com.codeborne.selenide.Selenide
import pageElements.Button

class DynamicallyLoadedPageElementsPage {
    val start: Button
        get() = Button(Selenide.`$`(Selectors.byXpath("//button[text()='Start']")), "Start")

    val hiddenText
        get() = Selenide.`$`(Selectors.byXpath("//h4[text()='Hello World!']"))

    fun pressStart(): DynamicallyLoadedPageElementsPage {
        start.elem.click()
        return this
    }
}