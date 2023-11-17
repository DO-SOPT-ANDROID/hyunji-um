package org.sopt.dosopttemplate.api

import org.sopt.dosopttemplate.Dto.RequestDto.RequestLoginDto
import org.sopt.dosopttemplate.Dto.ResponseDto.ResponseLoginDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthLogin {
    @POST("api/v1/members/sign-in")
    fun login(
        @Body request: RequestLoginDto,
    ): Call<ResponseLoginDto>
}