package com.geeksven.userservice.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("users")
class UserEntity {
    @Id
    var id: Long? = null
    var email: String = ""
    var username: String = ""
    var password: String = ""
    var firstName: String = ""
    var lastName: String = ""
    var roles: List<String> = mutableListOf()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserEntity

        if (id != other.id) return false
        if (email != other.email) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + email.hashCode()
        return result
    }

}
