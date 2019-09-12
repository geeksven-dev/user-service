package com.geeksven.userservice.repository

import com.geeksven.userservice.model.UserEntity
import org.apache.ibatis.annotations.Mapper

@Mapper
interface UserRepository {
    fun findByUsername(username: String?): UserEntity?
}