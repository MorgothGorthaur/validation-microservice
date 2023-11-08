package executor.service.validator


import executor.service.model.ProxyConfigHolder
import executor.service.model.ProxyCredentials
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.stereotype.Component

import java.io.IOException
import java.net.InetSocketAddress
import java.net.Proxy

@Component
class HttpProxyValidator : ProxyValidator {
    private companion object {
        const val PROXY_CHECKER_URL = "http://httpbin.org/ip"
        const val PROXY_AUTHORIZATION = "Authorization"
    }

    override fun isValid(proxy: ProxyConfigHolder): Boolean {
        val proxiedHttpClient = createProxiedHttpClient(proxy)
        val request: Request = getRequest(proxy.proxyCredentials)
        return validateProxy(proxiedHttpClient, request)
    }

    override fun getType(): String {
        return "http"
    }

    private fun createProxiedHttpClient(proxy: ProxyConfigHolder): OkHttpClient {
        val inetSocketAddress = InetSocketAddress(proxy.proxyNetworkConfig.hostname, proxy.proxyNetworkConfig.port)
        return OkHttpClient.Builder().proxy(Proxy(Proxy.Type.HTTP, inetSocketAddress)).build()
    }

    private fun getRequest(credentials: ProxyCredentials?): Request {
        val builder: Request.Builder = Request.Builder().url(PROXY_CHECKER_URL)
        credentials?.let {
            builder.header(
                PROXY_AUTHORIZATION,
                Credentials.basic(credentials.username, credentials.password)
            )
        }
        return builder.build()
    }

    private fun validateProxy(proxiedHttpClient: OkHttpClient, request: Request): Boolean {
        try {
            proxiedHttpClient.newCall(request).execute().use { response -> return response.isSuccessful }
        } catch (ex: IOException) {
            return false
        }
    }
}