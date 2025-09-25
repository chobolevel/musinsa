package com.musinsa.auth.service

import com.musinsa.auth.dto.JwtResponse
import com.musinsa.auth.dto.LoginRequest
import com.musinsa.auth.dto.ReissueResponse

interface AuthService {

    fun login(request: LoginRequest): JwtResponse

    fun reissue(refreshToken: String): ReissueResponse
}
