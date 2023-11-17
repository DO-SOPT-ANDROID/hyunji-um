package org.sopt.dosopttemplate.api

import org.sopt.dosopttemplate.Dto.ResponseDto.ResponseFollowerDto
import retrofit2.Call
import retrofit2.http.GET

interface ReqresService {
    @GET("/api/users?page=2")
    fun follower(): Call<ResponseFollowerDto>
}