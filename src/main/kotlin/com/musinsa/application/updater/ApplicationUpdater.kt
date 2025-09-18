package com.musinsa.application.updater

import com.musinsa.application.dto.UpdateApplicationRequest
import com.musinsa.application.entity.Application
import com.musinsa.application.vo.ApplicationUpdateMask
import org.springframework.stereotype.Component

@Component
class ApplicationUpdater {

    fun markAsUpdate(entity: Application, request: UpdateApplicationRequest): Application {
        request.updateMask.forEach {
            when (it) {
                ApplicationUpdateMask.NAME -> entity.name = request.name!!
            }
        }
        return entity
    }
}
