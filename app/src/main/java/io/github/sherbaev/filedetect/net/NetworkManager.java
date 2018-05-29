package io.github.sherbaev.filedetect.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;
import com.readystatesoftware.chuck.ChuckInterceptor;

import io.github.sherbaev.filedetect.common.Config;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManager {
    private IService service;
    private ConnectivityManager connectionService;

    public NetworkManager(Context context) {
        connectionService = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new ChuckInterceptor(context))
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(Config.BASE_URL)
                .build();
        service = retrofit.create(IService.class);

    }

    public boolean isOnline() {
        NetworkInfo info = connectionService.getActiveNetworkInfo();
        return info != null;
    }

    public IService getService() {
        return service;
    }
}
