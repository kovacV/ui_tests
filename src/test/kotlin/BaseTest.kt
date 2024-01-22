import com.codeborne.selenide.Selenide
import config.ConfigManager
import org.junit.jupiter.api.BeforeEach

open class BaseTest {

    @BeforeEach
    fun openSite() {
        Selenide.open(ConfigManager.prop.getProperty("URL"))
    }
}