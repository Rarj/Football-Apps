package dev.grack.network.interceptor

import android.content.Context
import com.orhanobut.logger.Logger
import dev.grack.network.interceptor.ResponseCode.BAD_GATEWAY
import dev.grack.network.interceptor.ResponseCode.BAD_REQUEST
import dev.grack.network.interceptor.ResponseCode.FORBIDDEN
import dev.grack.network.interceptor.ResponseCode.GATEWAY_TIMEOUT
import dev.grack.network.interceptor.ResponseCode.INTERNAL_SERVER_ERROR
import dev.grack.network.interceptor.ResponseCode.NOT_FOUND
import dev.grack.network.interceptor.ResponseCode.REQUEST_TIMEOUT
import dev.grack.network.interceptor.ResponseCode.RESPONSE_BAD_GATEWAY
import dev.grack.network.interceptor.ResponseCode.RESPONSE_BAD_REQUEST
import dev.grack.network.interceptor.ResponseCode.RESPONSE_FORBIDDEN
import dev.grack.network.interceptor.ResponseCode.RESPONSE_GATEWAY_TIMEOUT
import dev.grack.network.interceptor.ResponseCode.RESPONSE_INTERNAL_SERVER_ERROR
import dev.grack.network.interceptor.ResponseCode.RESPONSE_NOT_FOUND
import dev.grack.network.interceptor.ResponseCode.RESPONSE_REQUEST_TIMEOUT
import dev.grack.network.interceptor.ResponseCode.RESPONSE_SERVICE_UNAVAILABLE
import dev.grack.network.interceptor.ResponseCode.RESPONSE_SUCCESS
import dev.grack.network.interceptor.ResponseCode.RESPONSE_UNAUTHORIZED
import dev.grack.network.interceptor.ResponseCode.SERVICE_UNAVAILABLE
import dev.grack.network.interceptor.ResponseCode.SUCCESS
import dev.grack.network.interceptor.ResponseCode.UNAUTHORIZED
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NetworkInterceptor(private val ctx: Context) : Interceptor {

  @Throws(IOException::class)
  override fun intercept(chain: Interceptor.Chain): Response {
    val request = chain.request()
    val response = chain.proceed(request)

    when {
      response.code() == RESPONSE_SUCCESS -> Logger.e(SUCCESS)
      response.code() == RESPONSE_BAD_REQUEST -> Logger.e(BAD_REQUEST)
      response.code() == RESPONSE_UNAUTHORIZED -> Logger.e(UNAUTHORIZED)
      response.code() == RESPONSE_FORBIDDEN -> Logger.e(FORBIDDEN)
      response.code() == RESPONSE_NOT_FOUND -> Logger.e(NOT_FOUND)
      response.code() == RESPONSE_REQUEST_TIMEOUT -> Logger.e(REQUEST_TIMEOUT)
      response.code() == RESPONSE_INTERNAL_SERVER_ERROR -> Logger.e(INTERNAL_SERVER_ERROR)
      response.code() == RESPONSE_BAD_GATEWAY -> Logger.e(BAD_GATEWAY)
      response.code() == RESPONSE_SERVICE_UNAVAILABLE -> Logger.e(SERVICE_UNAVAILABLE)
      response.code() == RESPONSE_GATEWAY_TIMEOUT -> Logger.e(GATEWAY_TIMEOUT)
    }

    if (ConnectivityStatus.isConnected(ctx)) {
      request.newBuilder()
        .header("Cache-Control", "public, max-age=" + 60)
        .build()

    } else {
      request.newBuilder()
        .header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
    }

    return chain.proceed(request)
  }

}