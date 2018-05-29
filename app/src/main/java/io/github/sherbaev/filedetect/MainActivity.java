package io.github.sherbaev.filedetect;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import io.github.sherbaev.filedetect.data.MRequest;
import io.github.sherbaev.filedetect.data.MResponse;
import io.github.sherbaev.filedetect.net.IService;
import io.github.sherbaev.filedetect.net.NetworkManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    private IService service;
    private NetworkManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        SharedPreferences preferences=

        FloatingActionButton fab = findViewById(R.id.fab);
        FloatingActionButton fab2 = findViewById(R.id.fab2);
        fab.setOnClickListener(view ->
        {
            if (manager.isOnline()) {
                MRequest req = new MRequest("http://");
                service.sendReq(req).enqueue(new Callback<Object>() {
                    @Override public void onResponse(Call<Object> call, Response<Object> response) {
                        if (response.isSuccessful()) {
                            MResponse ans = (MResponse) response.body();
                            if (preference.loadVersion() == 0) {
                                preference.saveVersion(ans.getVersion());
                                DownloadUrl(ans.getUrl());
                            } else if (preference.loadVersion() != 0) {
                                if (ans.getVersion() > preference.loadVersion()) {
                                    preference.saveVersion(ans.getVersion());
                                    DownloadUrl(ans.getUrl());
                                } else {
                                    Toast.makeText(MainActivity.this, "You have already downloaded", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }

                    @Override public void onFailure(Call<Object> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });
            } else
                Toast.makeText(MainActivity.this, "Internet is not avaiable", Toast.LENGTH_SHORT).show();
        });
        fab2.setOnClickListener(v -> {
//            if (ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                    != PackageManager.PERMISSION_GRANTED)
//            {
//                requestPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE);
//            }else {
//
//            }

            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                int permissionCheck;
                permissionCheck = this.checkSelfPermission(Manifest
                        .permission
                        .WRITE_EXTERNAL_STORAGE);
                if (permissionCheck != 0) {
                    this.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1001);
                }
            } else {
                Log.d(TAG, "No need to check permission ");
            }
        });


    }

    private void DownloadUrl(String url) {
        DownloadAsync async = new DownloadAsync();
        async.execute(url);
    }

    @SuppressLint("StaticFieldLeak")
    class DownloadAsync extends AsyncTask<String, Integer, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... strings) {
            String myUrl = strings[0];
            FileDownoader fileDownoader = new FileDownoader();
            fileDownoader.loadFile(myUrl);
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
