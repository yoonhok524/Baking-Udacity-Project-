package com.youknow.baking.utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Aaron on 09/07/2017.
 */

public class HttpUtil {
    public static String getResponseFromHttpUrl(String strUrl) {
        Uri uri = Uri.parse(strUrl);
        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if (url == null) {
            return null;
        }

        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            try (InputStream in = urlConnection.getInputStream(); Scanner scanner = new Scanner(in)){
                scanner.useDelimiter("\\A");
                boolean hasInput = scanner.hasNext();
                if (hasInput) {
                    return scanner.next();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
