package com.musinsa.auth.service

import com.musinsa.auth.dto.JwtResponse
import com.musinsa.auth.dto.LoginRequest

interface AuthService {

    fun login(request: LoginRequest): JwtResponse
}
