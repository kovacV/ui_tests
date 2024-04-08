package actions

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.SelenideElement
import io.qameta.allure.Step

@Step("drag and drop to {target}")
fun SelenideElement.dragAndDropTo(target: SelenideElement) {
    Selenide.actions().dragAndDrop(this, target)
        .build()
        .perform()
}
