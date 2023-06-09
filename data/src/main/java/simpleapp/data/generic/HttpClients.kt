package simpleapp.data.generic

import android.util.Log
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*
import nl.simpleapp.domain.generic.NetworkException
import java.io.IOException

class HttpClients {

    val client: HttpClient by lazy {
        HttpClient(OkHttp) {
            installDefaults()
        }
    }

    private fun HttpClientConfig<OkHttpConfig>.installDefaults() {
        installBaseUrl()
        installErrorMapping()
        installJsonSerializer()
    }

    private fun HttpClientConfig<*>.installBaseUrl() = defaultRequest {
        url {
            protocol = URLProtocol.HTTPS
        }
    }

    private fun HttpClientConfig<*>.installErrorMapping() = HttpResponseValidator {
        handleResponseException {
            val mappedException = when (it) {
                is IOException -> NetworkException()
                else -> it
            }

            Log.e(null, "An API error occurred", mappedException)
            throw mappedException
        }
    }

    private fun HttpClientConfig<*>.installJsonSerializer() = Json {
        serializer = KotlinxSerializer(
            kotlinx.serialization.json.Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            }
        )
    }

    suspend inline fun <reified T> unauthorizedGet(
        urlString: String,
        block: HttpRequestBuilder.() -> Unit = {},
    ): T = client.get(urlString) {
        block()
    }


    companion object {

        private const val JSON_PARAMETER = "json"
        private const val DECIMAL_POINT_PARAMETER = "dpic"
    }
}