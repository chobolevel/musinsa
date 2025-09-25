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

    // TODO Authentication으로 왔다 갔다 하지 않는 방법으로 해보기(단일 책임 원칙 구현)
    // 단일 책임 원칙에 따르면 이 로직이 수정되기 위해서는 반드시 하나의 사유에 의해서만 가능하도록 설계되어야 함
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
