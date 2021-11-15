package com.omni.core.wrapper

import com.omni.core.*
import com.omni.core.exception.ServerDataNullException
import com.omni.core.exception.UnhandledHttpCodeException
import com.google.gson.Gson
import com.omni.core.base.Mapper
import com.omni.core.model.ServerErrorResponseModel
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.util.concurrent.CancellationException

class ResponseWrapperHandler {
    companion object {

        inline fun <reified MODEL, ENTITY> handleResponse(
            serviceMethod: () -> Response<String>,
            successResponseMapper: Mapper<MODEL, ENTITY>,
            gson: Gson
        ): ResponseWrapper {
            return try {
                val response = serviceMethod()
                when (response.code()) {
                    HTTP_CODE_SUCCESS -> {
                        return if (response.body() == null)
                            ResponseWrapper.ServerDataNullError
                        else {
                            val responseModel = gson.fromJson(response.body(), MODEL::class.java)
                            ResponseWrapper.Success(
                                successResponseMapper.mapToDomainModel(
                                    responseModel!!
                                )
                            )
                        }
                    }
                    HTTP_CODE_NOT_MODIFIED, HTTP_CODE_CONFLICT, HTTP_CODE_UnPROCESSABLE_ENTRY, HTTP_CODE_NOT_FOUND, HTTP_CODE_INSUFFICIENT_QUALITY -> {
                        return if (response.errorBody() != null) {
                            val json = response.errorBody()!!.string()
                            val errorModel =
                                gson.fromJson(json, ServerErrorResponseModel::class.java)
                            ResponseWrapper.ServerError(errorModel)
                        } else
                            ResponseWrapper.ServerDataNullError
                    }
                    else -> {
                        throw UnhandledHttpCodeException(response.code())
                    }
                }
            } catch (e: IOException) {
                ResponseWrapper.NetworkError
            } catch (e: ServerDataNullException) {
                ResponseWrapper.ServerDataNullError
            } catch (e: UnhandledHttpCodeException) {
                ResponseWrapper.UnhandledHTTPCode(e.httpStatusCode)
            } catch (e: CancellationException) {
                ResponseWrapper.CancelCoroutine
            } catch (e: HttpException) {
                ResponseWrapper.Error
            } catch (e: Exception) {
                ResponseWrapper.Error
            }
        }
    }
}