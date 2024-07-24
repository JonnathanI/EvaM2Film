package com.example.evam2.service


import com.example.evam2.dto.registerDto
import com.example.evam2.model.Users
import com.example.evam2.respository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserSecurityService: UserDetailsService {
    @Autowired
    lateinit var userRepository: UserRepository
    @Override
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails? {
        val user = userRepository.findByUsername(username)
            ?: throw
            UsernameNotFoundException(
                "User $username not found."
            )

        val roles: Array<String?> = user.roles?.map {
                role -> role.rol }!!.toTypedArray()

        return User.builder()
            .username(user.username)
            .password(user.password)
            .roles(*roles)
            .accountLocked(user.locked!!)
            .disabled(user.disabled!!)
            .build()
    }
    fun register(registerDto: registerDto):Users{
        val newUser = Users()
        newUser.apply {
            username = registerDto.username
            password = registerDto.password
            email = registerDto.email
            locked = false
            disabled = false
        }
        userRepository.save(newUser)
        return newUser
    }

}