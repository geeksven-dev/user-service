package com.geeksven.userservice.controller

import com.geeksven.userservice.model.api.UserResponse
import com.geeksven.userservice.model.api.toResponse
import com.geeksven.userservice.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/api/v1/user")
class UserController(private val userService: UserService) {
    @GetMapping("/me")
    fun me(principal: Principal): UserResponse {
        val findUserByUsername = userService.findUserByUsername(principal.name)
        return findUserByUsername?.toResponse() ?: error("User infos not found")
    }
}