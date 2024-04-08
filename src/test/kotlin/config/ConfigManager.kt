package config

import java.io.IOException
import java.util.*

object ConfigManager {
    val prop: Properties
        get() {
            val prop = Properties()
            try {
                ConfigManager::class.java.classLoader.getResourceAsStream("config.properties")
                    ?.use { resourceAsStream ->
                        synchronized(ConfigManager::class.java) {
                            prop.load(resourceAsStream)
                        }
                    }
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return prop
        }
}