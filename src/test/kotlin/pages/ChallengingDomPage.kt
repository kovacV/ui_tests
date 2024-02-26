package pages

import com.codeborne.selenide.Selectors
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.SelenideElement
import io.qameta.allure.Allure
import io.qameta.allure.Attachment
import io.qameta.allure.Step
import pageElements.Button

class ChallengingDomPage {
    val REGEX = Regex("Answer:\\s*(\\d+)")
    val table10Columns
        get(): SelenideElement = Selenide.`$`(Selectors.byXpath("//div[contains(@class,'large-10 columns')]"))
    val scriptBlock
        get(): SelenideElement = Selenide.`$`(Selectors.byXpath("//div[@id=\"content\"]/script"))
//    val blueButton
//        get(): SelenideElement = Selenide.`$`(".button")
    val blueButton: Button
        get(): Button = Button(Selenide.`$`(".button"), "Синяя кнопка")
    val redButton
        get(): SelenideElement = Selenide.`$`(".button.alert")
    val greenButton
        get(): SelenideElement = Selenide.`$`(".button.success")

    @Step("Нажатие на кнопку")
    fun clickButton(): ChallengingDomPage {
        blueButton.click()
        return this
    }

    fun getScriptBody(): String? {
        return scriptBlock.getAttribute("innerHTML")
    }

    @Step("Получение значения \"Answer\"")
    fun getAnswerValue(): String? {
        val answerValue = getScriptBody()?.let { REGEX.find(it)?.destructured?.component1() }
        Allure.addAttachment("Answers value", answerValue)
        return answerValue
    }
}