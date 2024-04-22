package pages

import com.codeborne.selenide.Selectors
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.SelenideElement
import org.openqa.selenium.By.ByXPath
import pageElements.Button

class EntryAdPage : BasePage() {

    private val close: Button = Button(Selenide.`$`(Selectors.byXpath("//p[text()='Close']")), "Close")

    val modalWindow: SelenideElement = Selenide.`$`(Selectors.byXpath("//div[@class='modal']"))

    val clickHereHref: SelenideElement =  Selenide.`$`(Selectors.byXpath("//a[text()='click here']"))
}