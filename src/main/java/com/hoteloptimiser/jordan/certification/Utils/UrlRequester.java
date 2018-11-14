package com.hoteloptimiser.jordan.certification.Utils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class UrlRequester {

    public interface actionOnPost {
        void action(Request.Builder build, RequestBody body);

        RequestBody getRequestBody();
    }


    private static void setHeaders(Request.Builder builder, HashMap<String, String> header) {
        for (Map.Entry<String, String> entries : header.entrySet()) {
            builder.header(entries.getKey(), entries.getValue());
        }
    }

    @SneakyThrows
    public static String launchPostRequest(
            String formattedUrl,
            HashMap<String, String> header,
            actionOnPost post
    )
    {
        RequestBody requestBody = post.getRequestBody();

        Request.Builder build = new Request.Builder().url(formattedUrl);

        if (header != null)
            setHeaders(build, header);

        post.action(build, requestBody);

        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(build.build()).execute();

        if (response.isSuccessful())
            return response.body().string();

        return null;
    }

}