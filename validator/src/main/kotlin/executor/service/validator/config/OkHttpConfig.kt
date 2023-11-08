package executor.service.validator.config

import okhttp3.OkHttpClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OkHttpConfig {
    @Bean
    fun okHttpClient(): OkHttpClient = OkHttpClient()
}