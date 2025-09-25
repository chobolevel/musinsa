package com.musinsa.auth.util

import com.musinsa.common.exception.ErrorCode
import com.musinsa.common.exception.UnauthorizedException
import com.musinsa.common.properties.JwtProperties
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Header
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.Date
import java.util.concurrent.TimeUnit

@Component
class TokenProvider(
    private val jwtProperties: JwtProperties,
) {

    private final val now = Date()

    fun generateAccessToken(id: Long): String {
        val expiredAt = Date(now.time + TimeUnit.MINUTES.toMillis(30))
        return Jwts.builder()
            .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
            .setIssuer(jwtProperties.issuer)
            .setIssuedAt(now)
            .setExpiration(expiredAt)
            .setSubject(id.toString())
            .signWith(SignatureAlgorithm.HS256, jwtProperties.secret)
            .compact()
    }

    fun generateRefreshToken(id: Long): String {
        val expiredAt = Date(now.time + TimeUnit.MINUTES.toMillis(30))
        return Jwts.builder()
            .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
            .setIssuer(jwtProperties.issuer)
            .setIssuedAt(now)
            .setExpiration(expiredAt)
            .setSubject(id.toString())
            .signWith(SignatureAlgorithm.HS256, jwtProperties.secret)
            .compact()
    }

    fun getId(token: String): Long {
        val claims: Claims = Jwts.parser()
            .setSigningKey(jwtProperties.secret)
            .parseClaimsJws(token)
            .body
        return claims.subject.toLong()
    }

    fun validateToken(token: String): Boolean {
        return try {
            Jwts.parser()
                .setSigningKey(jwtProperties.secret)
                .parseClaimsJws(token)
            true
        } catch (e: ExpiredJwtException) {
            throw UnauthorizedException(
                errorCode = ErrorCode.EXPIRED_TOKEN,
                message = ErrorCode.EXPIRED_TOKEN.defaultMessage
            )
        } catch (e: JwtException) {
            throw UnauthorizedException(
                errorCode = ErrorCode.INVALID_TOKEN,
                message = ErrorCode.INVALID_TOKEN.defaultMessage
            )
        }
    }
}
