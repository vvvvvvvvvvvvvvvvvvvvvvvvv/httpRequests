package com.l.http;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.http.StatusLine;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class MainActivity extends AppCompatActivity {
    String url;
    OkHttpClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        client = new OkHttpClient();
        url = "http://10.0.3.2/game/index.php";
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    postRequest();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();


    }
    /*public void postRequest() throws JSONException, IOException {
        MediaType Json = MediaType.parse("application/json;charset=utf-8");
        JSONObject actualData = new JSONObject();
        actualData.put("name", "test");
        actualData.put("email", "abachok.000@mail.ru");

        RequestBody body = RequestBody.create(Json, actualData.toString());
        Request newReq = new Request.Builder().url(url).post(body).build();
        Response response = client.newCall(newReq).execute();


    }*/
   public void postRequest() throws JSONException, IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("name", "test")
                .add("email", "aba@mail.ru")
                .add("password", "test")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();
        Log.d("test", response.body().string());

       // assertThat(response.code(), equalTo(200));

    }


    




    public void getRequest(){

        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("test", "error");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String sel = response.body().string();
                Log.d("test", sel);



            }
        });
    }
}
