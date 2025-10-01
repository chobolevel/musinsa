package com.musinsa.common.service.upload

import com.amazonaws.HttpMethod
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.Headers
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest
import com.musinsa.common.dto.UploadRequestDto
import com.musinsa.common.dto.UploadResponseDto
import com.musinsa.common.service.upload.validator.UploadValidator
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.net.URL
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.UUID
import java.util.concurrent.TimeUnit

@Service
class UploadService(
    @Value("\${cloud.aws.s3.bucket}")
    private val bucket: String,
    private val amazonS3: AmazonS3,
    private val validator: UploadValidator,
) {

    fun getPresignedUrl(request: UploadRequestDto): UploadResponseDto {
        validator.validate(request)
        val savedPath: String = createSavedPath(
            prefix = request.prefix,
            extension = request.extension
        )
        val presignedRequest: GeneratePresignedUrlRequest = createPresignedRequest(
            savedPath = savedPath
        )
        val presignedResponse: URL = amazonS3.generatePresignedUrl(presignedRequest)
        return UploadResponseDto(
            presignedUrl = presignedResponse.toString(),
            url = "${presignedResponse.protocol}://${presignedResponse.host}${presignedResponse.path}",
            filenameWithExtension = "${request.filename}.${request.extension}"
        )
    }

    private fun createSavedPath(prefix: String, extension: String): String {
        val now: LocalDate = LocalDate.now()
        val datePath: String = now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))
        val uuid: String = UUID.randomUUID().toString()
        return "$prefix/$datePath/$uuid.$extension"
    }

    private fun createPresignedRequest(savedPath: String): GeneratePresignedUrlRequest {
        val presignedRequest: GeneratePresignedUrlRequest = GeneratePresignedUrlRequest(bucket, savedPath)
            .withMethod(HttpMethod.PUT)
            .withExpiration(Date(Date().time + TimeUnit.MINUTES.toMillis(10)))
        presignedRequest.addRequestParameter(
            Headers.S3_CANNED_ACL,
            CannedAccessControlList.PublicRead.toString()
        )
        return presignedRequest
    }
}
