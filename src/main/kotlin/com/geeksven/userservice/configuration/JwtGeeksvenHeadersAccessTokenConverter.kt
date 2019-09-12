package com.geeksven.userservice.configuration

import org.springframework.security.jwt.JwtHelper
import org.springframework.security.jwt.crypto.sign.RsaSigner
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.security.oauth2.common.util.JsonParserFactory
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import java.security.KeyPair
import java.security.interfaces.RSAPrivateKey

class JwtGeeksvenHeadersAccessTokenConverter(private val customHeaders: Map<String, String>, keyPair: KeyPair) : JwtAccessTokenConverter() {
    private val objectMapper = JsonParserFactory.create()
    private val signer: RsaSigner

    init {
        super.setKeyPair(keyPair)
        this.signer = RsaSigner(keyPair.private as RSAPrivateKey)
    }

    override fun encode(accessToken: OAuth2AccessToken, authentication: OAuth2Authentication): String {
        val content: String
        try {
            content = this.objectMapper.formatMap(accessTokenConverter.convertAccessToken(accessToken, authentication))
        } catch (ex: Exception) {
            throw IllegalStateException("Cannot convert access token to JSON", ex)
        }

        return JwtHelper.encode(content, this.signer, this.customHeaders)
                .encoded
    }

}