package com.dwh.web

import mx.com.satoritech.web.APIConstants
import okhttp3.Interceptor
import okhttp3.Response

class ApiServiceInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
       val urlWithParams = chain.request()
           .url
           .newBuilder()
           .addQueryParameter(APIConstants.PARAM_API_KEY, APIConstants.API_KEY)
           .addQueryParameter(APIConstants.PARAM_LANGUAGE, APIConstants.LANGUAGE)
           .addQueryParameter(APIConstants.PARAM_PAGE, APIConstants.PAGE)
           .build()

        var request = chain.request()

        request = request.newBuilder()
            .url(urlWithParams)
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .build()

        return  chain.proceed(request)
    }
}