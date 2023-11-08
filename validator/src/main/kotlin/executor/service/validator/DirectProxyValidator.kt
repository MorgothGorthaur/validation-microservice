package executor.service.validator

import executor.service.model.ProxyConfigHolder
import org.springframework.stereotype.Component

@Component
class DirectProxyValidator : ProxyValidator {

    override fun isValid(proxy: ProxyConfigHolder): Boolean = false

    override fun getType(): String = "direct"
}