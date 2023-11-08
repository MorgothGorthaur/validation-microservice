package executor.service.model

data class ProxyConfigHolder(
    val proxyNetworkConfig: ProxyNetworkConfig,
    val proxyCredentials: ProxyCredentials?
)
