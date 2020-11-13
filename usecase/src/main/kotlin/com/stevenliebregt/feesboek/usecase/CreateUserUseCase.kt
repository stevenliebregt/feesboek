package com.stevenliebregt.feesboek.usecase

import com.stevenliebregt.feesboek.domain.entity.User
import com.stevenliebregt.feesboek.usecase.exception.UserAlreadyExistsException
import com.stevenliebregt.feesboek.usecase.repository.IUserRepository

class CreateUserUseCase(private val userRepository: IUserRepository) {
    fun create(user: User): User {
        // TODO: Validate input

        if (userRepository.findByEmail(user.email) != null) throw UserAlreadyExistsException(user.email)

        return userRepository.add(user)
    }
}