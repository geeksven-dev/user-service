package com.geeksven.userservice.configuration

import com.nimbusds.jose.JWSAlgorithm
import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.KeyUse
import com.nimbusds.jose.jwk.RSAKey
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory
import java.security.KeyPair
import java.security.interfaces.RSAPublicKey


const val KEY_STORE_FILE = "geeksven-jwt.jks"
const val KEY_STORE_PASSWORD = "Netport67"
const val KEY_ALIAS = "geeksven-oauth-jwt"
const val JWK_KID = "geeksven-key-id"

@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfiguration {

    @Bean
    fun keyPair(): KeyPair = KeyStoreKeyFactory(ClassPathResource(KEY_STORE_FILE), KEY_STORE_PASSWORD.toCharArray()).getKeyPair(KEY_ALIAS)

    @Bean
    fun jwtAccessTokenConverter() = JwtGeeksvenHeadersAccessTokenConverter(mapOf("kid" to JWK_KID), keyPair())

    @Bean
    fun tokenStore(jwtAccessTokenConverter: JwtAccessTokenConverter) = JwtTokenStore(jwtAccessTokenConverter)

    @Bean
    fun jwkSet(): JWKSet = JWKSet(RSAKey.Builder(keyPair().public as RSAPublicKey).keyUse(KeyUse.SIGNATURE)
            .algorithm(JWSAlgorithm.RS256)
            .keyID(JWK_KID).build())
}