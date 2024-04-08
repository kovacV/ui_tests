package pages

import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selectors
import com.codeborne.selenide.Selenide

class BrokenImagesPage {
    val images: ElementsCollection
        get(): ElementsCollection = Selenide.`$$`(Selectors.byCssSelector(".example img"))
}