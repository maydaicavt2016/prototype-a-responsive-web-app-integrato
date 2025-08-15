data class IntegratorSetting(
    val id: Int,
    val name: String,
    val description: String,
    val apiUrl: String,
    val authType: AuthType,
    val credentials: Credentials?
)

enum class AuthType {
    NONE, BASIC, OAUTH2, KEY
}

data class Credentials(
    val username: String?,
    val password: String?,
    val accessToken: String?,
    val refreshToken: String?
)

data class WebApp(
    val id: Int,
    val name: String,
    val description: String,
    val url: String,
    val integratorSettings: List<IntegratorSetting>
)

data class Integration(
    val id: Int,
    val webAppId: Int,
    val integratorSettingId: Int,
    val isActive: Boolean
)

data class Response (
    val status: String,
    val message: String,
    val data: Any?
)

class IntegratorRepository {
    private val integratorSettings: MutableList<IntegratorSetting> = mutableListOf()
    private val webApps: MutableList<WebApp> = mutableListOf()
    private val integrations: MutableList<Integration> = mutableListOf()

    fun saveIntegratorSetting(setting: IntegratorSetting) {
        integratorSettings.add(setting)
    }

    fun saveWebApp(webApp: WebApp) {
        webApps.add(webApp)
    }

    fun saveIntegration(integration: Integration) {
        integrations.add(integration)
    }

    fun getActiveIntegrations(): List<Integration> {
        return integrations.filter { it.isActive }
    }

    fun getWebApp(id: Int): WebApp? {
        return webApps.find { it.id == id }
    }

    fun getIntegratorSetting(id: Int): IntegratorSetting? {
        return integratorSettings.find { it.id == id }
    }
}