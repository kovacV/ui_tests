package pages

import com.codeborne.selenide.Selectors
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.SelenideElement

class DragAndDropPage {

    val a: SelenideElement
        get() = Selenide.`$`(Selectors.byXpath("//*[@id='column-a']"))

    val b: SelenideElement
        get() = Selenide.`$`(Selectors.byXpath("//*[@id='column-b']"))


    fun getHeader(elem: String): SelenideElement {
        return Selenide.`$`(Selectors.byXpath("//*[@id='column-$elem']/header"))
    }
}