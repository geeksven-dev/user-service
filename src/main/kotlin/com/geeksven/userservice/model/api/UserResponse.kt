package com.geeksven.userservice.model.api

import com.geeksven.userservice.model.UserEntity

data class UserResponse(
    val id: Long,
    val username: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val roles: List<String>
)

fun UserEntity.toResponse() = UserResponse(
    id = this.id!!,
    username = this.username,
    email = this.email,
    firstName = this.firstName,
    lastName = this.lastName,
    roles = this.roles
)
