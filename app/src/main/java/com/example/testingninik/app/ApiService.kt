package com.example.testingninik.app

import com.example.testingninik.model.ResponModel
import com.example.testingninik.model.ResponModelEbook
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


interface ApiService {

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("nama") nama: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("alamat") alamat: String,
        @Field("nomor_telepon") nomor_telepon: String
    ): Call<ResponModel>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ResponModel>

    @GET("pengguna/tampil_ebook")
    fun getAllEbook(): Call<ResponModelEbook>

    @FormUrlEncoded
    @POST("daftar_simpan")
    fun simpan_buku(
        @Field("user_id") user_id: String,
        @Field("buku_id") buku_id: String
    ): Call<ResponModel>

    @FormUrlEncoded
    @POST("pengajuan_ebook")
    fun pengajuan_ebook(
        @Field("judul_buku") user_id: String,
        @Field("jenis_buku") jenis_buku: String,
        @Field("alamat_donatur") alamat_donatur: String,
        @Field("donatur") donatur: String,
        @Field("sinopsis") sinopsis: String,
        @Field("jenis_donasi") jenis_donasi: String,
        @Field("jumlah_buku") jumlah_buku: String
    ): Call<ResponModel>

    @Multipart
    @POST("pengajuan_buku")
    fun pengajuan_buku(

        @Part("foto_cover\"; filename=\"myfile.jpg\" ") foto_cover: RequestBody?,
        @Part("judul_buku") judul_buku: RequestBody?,
        @Part("jenis_buku") jenis_buku: RequestBody?,
        @Part("alamat_donatur") alamat_donatur: RequestBody?,
        @Part("donatur") donatur: RequestBody?,
        @Part("sinopsis") sinopsis: RequestBody?,
        @Part("jenis_donasi") jenis_donasi: RequestBody?,
        @Part("jumlah_buku") jumlah_buku: RequestBody?
    ): Call<ResponModel>

    @Multipart
    @POST("donasi_ebook")
    fun donasi_ebook(

        @Part("file_ebook\"; filename=\"myfile.pdf\" ") file_ebook: RequestBody?,
        @Part("id_donasi") id_donasi: RequestBody?,
        @Part("judul_buku") judul_buku: RequestBody?
    ): Call<ResponModel>


//    @Multipart
//    @POST("/api/Accounts/editaccount")
//    fun editUser(
//        @Header("Authorization") authorization: String?,
//        @Part("file\"; filename=\"pp.png\" ") file: RequestBody?,
//        @Part("FirstName") fname: RequestBody?,
//        @Part("Id") id: RequestBody?
//    ): Call<User?>?

//    @Multipart
//    @POST("/api/receipt/upload")
//    fun uploadReceipt(
//        @Header("Cookie") sessionIdAndRz: String?,
//        @Part file: MultipartBody.Part?,
//        @Part("items") items: RequestBody?,
//        @Part("isAny") isAny: RequestBody?
//    ): Call<List<UploadResult?>?>?
}