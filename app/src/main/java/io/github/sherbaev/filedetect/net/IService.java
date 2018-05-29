package io.github.sherbaev.filedetect.net;

import io.github.sherbaev.filedetect.data.MRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IService {
    @POST("login")
    Call<Object> sendReq(@Body MRequest req);

}
