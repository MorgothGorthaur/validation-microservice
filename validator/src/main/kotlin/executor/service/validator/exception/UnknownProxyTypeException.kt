package executor.service.validator.exception

class UnknownProxyTypeException(type: String): Exception("unknown proxy type exception: $type")