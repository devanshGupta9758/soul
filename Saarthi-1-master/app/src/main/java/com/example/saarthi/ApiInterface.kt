package com.example.saarthi

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("getDeviceInfo?accessToken=ZXlKaGJHY2lPaUpJVXpJMU5pSjkuZXlKemRXSWlPaUl3SWl3aWFYTnpJam9pWjNCekxYUnlZV05yWlhJaUxDSnBZWFFpT2pFMk9EQTROREE1TmpSOS4zRTJIcHpqc2FnSGdseGRRbFByWmpaNjlsNm55TUdRQjdpNjRtQUVnS3Y4")
    fun getData() : Call<List<Object>>
}