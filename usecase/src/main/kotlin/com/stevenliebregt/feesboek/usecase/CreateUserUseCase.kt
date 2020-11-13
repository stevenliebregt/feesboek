package com.stevenliebregt.feesboek.usecase

import com.stevenliebregt.feesboek.domain.entity.User
import com.stevenliebregt.feesboek.usecase.exception.UserAlreadyExistsException
import com.stevenliebregt.feesboek.usecase.repository.IUserRepository

class CreateUserUseCase(private val userRepository: IUserRepository) {
    fun create(user: User): User {
        if (userRepository.findByEmail(user.email) != null) throw UserAlreadyExistsException(user.email)

        val userToSave = User.Builder()
                .email(user.email)
                .password(user.password)
                .build()

        return userRepository.add(userToSave)
    }
}