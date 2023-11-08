package executor.service.validator.exception

class ValidationException: Exception {
    constructor(message: String): super(message)

    constructor(message: String, cause: Throwable) : super(message, cause)
}