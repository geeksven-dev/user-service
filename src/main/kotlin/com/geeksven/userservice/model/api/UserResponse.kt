package com.geeksven.userservice.model.api

import com.geeksven.userservice.model.UserEntity

data class UserResponse(val username: String,
                        val firstName: String,
                        val lastName: String,
                        val email: String,
                        val roles: List<String>)

fun UserEntity.toResponse() = UserResponse(
        username = this.username,
        email = this.email,
        firstName = this.firstName,
        lastName = this.lastName,
        roles = this.roles
)