package com.geeksven.userservice.service

import com.geeksven.userservice.model.UserEntity
import org.springframework.security.core.userdetails.UserDetailsService

interface UserService : UserDetailsService {
    fun findUserByUsername(username: String): UserEntity?
}