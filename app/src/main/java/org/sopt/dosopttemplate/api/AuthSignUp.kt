package org.sopt.dosopttemplate.api

import org.sopt.dosopttemplate.Dto.RequestDto.RequestSignUpDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthSignUp {
    @POST("api/v1/members")
    fun signup(
        @Body request: RequestSignUpDto,
    ): Call<Unit>
}