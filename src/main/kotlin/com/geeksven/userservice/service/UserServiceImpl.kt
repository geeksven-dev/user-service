package com.geeksven.userservice.service

import com.geeksven.userservice.repository.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserServiceImpl(private val userRepository: UserRepository) : UserService {
    override fun loadUserByUsername(username: String?): UserDetails = userRepository.findByUsername(username)?.let {
        User.withUsername(it.username)
                .password(it.password)
                .authorities("USER")
                .build()
    } ?: throw UsernameNotFoundException("Could not find account with username $username!")

    override fun findUserByUsername(username: String) = userRepository.findByUsername(username)
}