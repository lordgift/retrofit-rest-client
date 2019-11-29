package th.co.cdgs.training.retrofitrestclient.api

import retrofit2.Call
import retrofit2.http.*
import th.co.cdgs.training.retrofitrestclient.recycler.Friend


interface Service {

    @PUT("friends/{id}.json")
    fun updateFriendList(@Path("id") id: Int, @Body friend: Friend): Call<Friend>

    @GET("friends.json")
    fun friendList(): Call<List<Friend?>?>

    @DELETE("friends/{id}.json")
    fun delete(@Path("id") id: Int): Call<Any>

}