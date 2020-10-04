package com.food_on.app.SendNotification;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {

    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAmEOviYY:APA91bHypdSCA5FIUtwsqYFDDOJRbT3PuROMjp-OH-DoaIgFkA915KJoAmZUJuGC8Ovv5_YA56bOhyVVuZt3SghkXS83l6n7qpnva1hc9sc0Qr4ZpvaOK723tBfMeBPgoFem0b-OfvET"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body NotificationSender body);
}
