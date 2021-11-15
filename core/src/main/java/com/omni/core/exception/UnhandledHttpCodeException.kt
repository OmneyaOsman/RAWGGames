package com.omni.core.exception

class UnhandledHttpCodeException(val httpStatusCode: Int) : Exception()