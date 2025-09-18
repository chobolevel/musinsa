package com.musinsa.application.dto

import com.musinsa.application.vo.ApplicationUpdateMask
import jakarta.validation.constraints.Size

data class UpdateApplicationRequest(
    val name: String?,
    @field:Size(min = 1)
    val updateMask: List<ApplicationUpdateMask>
)
