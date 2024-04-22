package pages

import com.codeborne.selenide.Selectors
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.SelenideElement

class DynamicLoadingPage {

    private val example1: SelenideElement
        get() = Selenide.`$`(Selectors.byXpath("//a[@href='/dynamic_loading/1']"))

    private val example2: SelenideElement
        get() = Selenide.`$`(Selectors.byXpath("//a[@href='/dynamic_loading/2']"))

    fun openExample1Page(): DynamicallyLoadedPageElementsPage {
        example1.click()
        return DynamicallyLoadedPageElementsPage()
    }

    fun openExample2Page(): DynamicallyLoadedPageElementsPage {
        example2.click()
        return DynamicallyLoadedPageElementsPage()
    }
}