package com.geeksven.userservice.controller

import com.nimbusds.jose.jwk.JWKSet
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class JwkSetRestController(private val jwkSet: JWKSet) {

    @GetMapping("/.well-known/jwks.json")
    fun keys(): Map<String, Any> {
        return jwkSet.toJSONObject()
    }

}