package executor.service.validator

import executor.service.model.ProxyConfigHolder




interface ProxyValidator {
    fun isValid(proxy: ProxyConfigHolder): Boolean
    fun getType(): String
}