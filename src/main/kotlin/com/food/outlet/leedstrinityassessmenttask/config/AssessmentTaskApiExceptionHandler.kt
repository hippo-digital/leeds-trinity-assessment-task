package com.food.outlet.leedstrinityassessmenttask.config

import jakarta.validation.ValidationException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageConversionException
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.access.AuthorizationServiceException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.server.ServerWebInputException

@RestControllerAdvice
class HmppsResettlementPassportApiExceptionHandler {

  @ExceptionHandler(AccessDeniedException::class)
  fun handleAccessDeniedException(e: AccessDeniedException): ResponseEntity<ErrorResponse> {
    log.info("Access denied exception: {}", e.message)
    return ResponseEntity
      .status(HttpStatus.FORBIDDEN)
      .body(
        ErrorResponse(
          status = HttpStatus.FORBIDDEN.value(),
          userMessage = "Authentication problem. Check token and roles - ${e.message}",
          developerMessage = e.message,
        ),
      )
  }

  @ExceptionHandler(AuthorizationServiceException::class)
  fun handleAuthorizationServiceException(e: AccessDeniedException): ResponseEntity<ErrorResponse> {
    log.info("Auth service exception: {}", e.message)
    return ResponseEntity
      .status(HttpStatus.UNAUTHORIZED)
      .body(
        ErrorResponse(
          status = HttpStatus.UNAUTHORIZED.value(),
          userMessage = "Authentication problem. Check token and roles - ${e.message}",
          developerMessage = e.message,
        ),
      )
  }

  @ExceptionHandler(value = [ValidationException::class, ServerWebInputException::class, HttpMessageConversionException::class, MethodArgumentTypeMismatchException::class])
  fun handleValidationException(e: Exception): ResponseEntity<ErrorResponse> {
    log.info("Validation exception: {}", e.cause?.message)
    return ResponseEntity
      .status(BAD_REQUEST)
      .body(
        ErrorResponse(
          status = BAD_REQUEST,
          userMessage = "Validation failure - please check request parameters and try again",
          developerMessage = e.cause?.message,
        ),
      )
  }

  @ExceptionHandler(ResourceNotFoundException::class)
  fun handleResourceNotFoundException(e: ResourceNotFoundException): ResponseEntity<ErrorResponse> {
    log.info("Resource not found exception: {}", e.message)
    return ResponseEntity
      .status(HttpStatus.NOT_FOUND)
      .body(
        ErrorResponse(
          status = HttpStatus.NOT_FOUND.value(),
          userMessage = "Resource not found. Check request parameters - ${e.message}",
          developerMessage = e.message,
        ),
      )
  }

  @ExceptionHandler(java.lang.Exception::class)
  fun handleException(e: java.lang.Exception): ResponseEntity<ErrorResponse?>? {
    log.error("Unexpected exception", e)
    return ResponseEntity
      .status(INTERNAL_SERVER_ERROR)
      .body(
        ErrorResponse(
          status = INTERNAL_SERVER_ERROR,
          userMessage = "Unexpected error: ${e.message}",
          developerMessage = e.message,
        ),
      )
  }

  @ExceptionHandler(DuplicateDataFoundException::class)
  fun handleDuplicateDataFoundException(e: DuplicateDataFoundException): ResponseEntity<ErrorResponse> {
    log.info("Duplicate data found exception: {}", e.message)
    return ResponseEntity
      .status(HttpStatus.CONFLICT)
      .body(
        ErrorResponse(
          status = HttpStatus.CONFLICT.value(),
          userMessage = "Duplicate data found. Check request parameters - ${e.message}",
          developerMessage = e.message,
        ),
      )
  }

  companion object {
    private val log = LoggerFactory.getLogger(this::class.java)
  }
}

data class ErrorResponse(
  val status: Int,
  val errorCode: Int? = null,
  val userMessage: String? = null,
  val developerMessage: String? = null,
  val moreInfo: String? = null,
) {
  constructor(
    status: HttpStatus,
    errorCode: Int? = null,
    userMessage: String? = null,
    developerMessage: String? = null,
    moreInfo: String? = null,
  ) :
    this(status.value(), errorCode, userMessage, developerMessage, moreInfo)
}


open class ResourceNotFoundException(message: String) : RuntimeException(message)

class NoDataWithCodeFoundException(dataType: String, code: String) : ResourceNotFoundException("No $dataType found for code `$code`")

open class DuplicateDataFoundException(message: String) : RuntimeException(message)

class DuplicateWithCodeFoundException(dataType: String, code: String) : DuplicateDataFoundException("Duplicate $dataType found for code `$code`")
