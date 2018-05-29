package io.github.sherbaev.filedetect;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FileDownoader {
    File file;
    private OkHttpClient client;

    public FileDownoader() {
        client = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    public File loadFile(String url) {
        String filename = getUrl(url);

        file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), filename);

        Request resquest = new Request.Builder().url(url).build();
        client.newCall(resquest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200) {
                    FileOutputStream out = new FileOutputStream(file);
                    byte[] buffer = response.body().bytes();
                    out.write(buffer);
                    out.flush();

                }
            }
        });
        return null;
    }

    private String getUrl(String url) {
        String urls[] = url.split("/");
        return urls[urls.length - 1];
    }
}
