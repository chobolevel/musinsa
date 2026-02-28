package com.musinsa.user.controller.v1

import com.musinsa.common.annotation.HasAuthorityUser
import com.musinsa.common.dto.CommonResponse
import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationRequest
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.common.extension.getUserId
import com.musinsa.user.reader.UserFollowQueryFilter
import com.musinsa.user.serivce.UserFollowService
import com.musinsa.user.vo.UserFollowOrderType
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@Tag(name = "UserFollow(회원 팔로우)", description = "회원 팔로우 관리 API")
@RestController
@RequestMapping("/api/v1")
class UserFollowController(
    private val service: UserFollowService
) {

    @Operation(summary = "회원 팔로잉 API")
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

    @Operation(summary = "회원 언팔로잉 API")
    @PostMapping("/user/un-following/{unFollowingUserId}")
    @HasAuthorityUser
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

    @Operation(summary = "팔로워 목록 조회 API")
    @GetMapping("/users/{userId}/followers")
    @HasAuthorityUser
    fun getFollowers(
        @PathVariable userId: Long,
        paginationRequest: PaginationRequest,
        @RequestParam(required = false) orderTypes: List<UserFollowOrderType>?
    ): ResponseEntity<CommonResponse> {
        val queryFilter = UserFollowQueryFilter(
            followerId = null,
            followingId = userId
        )
        val pagination = Pagination(
            page = paginationRequest.page ?: 1,
            size = paginationRequest.size ?: 10
        )
        val result: PaginationResponse = service.getUserFollows(
            queryFilter = queryFilter,
            pagination = pagination,
            orderTypes = orderTypes ?: emptyList()
        )
        return ResponseEntity.ok(CommonResponse(data = result))
    }

    @Operation(summary = "팔로잉 목록 조회 API")
    @GetMapping("/users/{userId}/followings")
    @HasAuthorityUser
    fun getFollowings(
        @PathVariable userId: Long,
        paginationRequest: PaginationRequest,
        @RequestParam(required = false) orderTypes: List<UserFollowOrderType>?
    ): ResponseEntity<CommonResponse> {
        val queryFilter = UserFollowQueryFilter(
            followerId = userId,
            followingId = null,
        )
        val pagination = Pagination(
            page = paginationRequest.page ?: 1,
            size = paginationRequest.size ?: 10
        )
        val result: PaginationResponse = service.getUserFollows(
            queryFilter = queryFilter,
            pagination = pagination,
            orderTypes = orderTypes ?: emptyList()
        )
        return ResponseEntity.ok(CommonResponse(data = result))
    }
}
