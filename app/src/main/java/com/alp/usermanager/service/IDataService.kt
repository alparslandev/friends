package com.alp.usermanager.service

import com.alp.usermanager.service.model.User
import com.alp.usermanager.service.request.LoginRequest
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST


interface IDataService {

    @POST("sessions")
    fun login(@Body request: LoginRequest): Observable<User>

}