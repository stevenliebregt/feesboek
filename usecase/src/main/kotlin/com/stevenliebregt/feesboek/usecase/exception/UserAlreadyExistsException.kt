package com.stevenliebregt.feesboek.usecase.exception

import java.lang.RuntimeException

class UserAlreadyExistsException(email: String) : RuntimeException(email)
