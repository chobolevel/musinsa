package com.musinsa.user.controller.v1

import com.musinsa.common.annotation.HasAuthorityUser
import com.musinsa.common.dto.CommonResponse
import com.musinsa.common.extension.getUserId
import com.musinsa.user.serivce.UserFollowService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/api/v1")
class UserFollowController(
    private val service: UserFollowService
) {

    @PostMapping("/user/following/{followingUserId}")
    @HasAuthorityUser
    fun following(
        principal: Principal,
        @PathVariable followingUserId: Long
    ): ResponseEntity<CommonResponse> {
        val result: Boolean = service.following(
            userId = principal.getUserId(),
            followingUserId = followingUserId
        )
        return ResponseEntity.ok(CommonResponse(data = result))
    }

    @PostMapping("/user/un-following/{unFollowingUserId}")
    fun unFollowing(
        principal: Principal,
        @PathVariable unFollowingUserId: Long
    ): ResponseEntity<CommonResponse> {
        val result: Boolean = service.unFollowing(
            userId = principal.getUserId(),
            unFollowingUserId = unFollowingUserId
        )
        return ResponseEntity.ok(CommonResponse(data = result))
    }
}
