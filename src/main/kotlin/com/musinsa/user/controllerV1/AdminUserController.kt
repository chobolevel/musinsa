package com.musinsa.user.controllerV1

import com.musinsa.common.dto.CommonResponse
import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.user.dto.UpdateUserRequest
import com.musinsa.user.dto.UserResponse
import com.musinsa.user.entity.UserQueryFilter
import com.musinsa.user.serivce.UserService
import com.musinsa.user.validator.UserParameterValidator
import com.musinsa.user.vo.UserOrderType
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/admin")
class AdminUserController(
    private val service: UserService,
    private val validator: UserParameterValidator
) {

    // @HasAuthorityAdmin
    @GetMapping("/users")
    fun getUsers(
        @RequestParam(required = false) email: String?,
        @RequestParam(required = false) nickname: String?,
        @RequestParam(required = false) resigned: Boolean?,
        @RequestParam(required = false) page: Long?,
        @RequestParam(required = false) size: Long?,
        @RequestParam(required = false) orderTypes: List<UserOrderType>?
    ): ResponseEntity<CommonResponse> {
        val queryFilter = UserQueryFilter(
            email = email,
            nickname = nickname,
            resigned = resigned
        )
        val pagination = Pagination(
            page = page ?: 1,
            size = size ?: 10
        )
        val result: PaginationResponse = service.getUsers(
            queryFilter = queryFilter,
            pagination = pagination,
            orderTypes = orderTypes ?: listOfNotNull(UserOrderType.CREATED_AT_DESC)
        )
        return ResponseEntity.ok(CommonResponse(result))
    }

    @GetMapping("/users/{id}")
    fun getUser(@PathVariable("id") userId: Long): ResponseEntity<CommonResponse> {
        val result: UserResponse = service.getUser(id = userId)
        return ResponseEntity.ok(CommonResponse(result))
    }

    @PutMapping("/users/{id}")
    fun updateUser(
        @PathVariable("id") userId: Long,
        @Valid @RequestBody
        request: UpdateUserRequest
    ): ResponseEntity<CommonResponse> {
        validator.validate(request = request)
        val result: Long = service.updateUser(
            userId = userId,
            request = request
        )
        return ResponseEntity.ok(CommonResponse(result))
    }
}
