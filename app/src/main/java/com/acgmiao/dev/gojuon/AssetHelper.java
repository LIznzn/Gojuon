package com.acgmiao.dev.gojuon;

import android.content.Context;
import android.content.res.AssetFileDescriptor;

import com.acgmiao.dev.gojuon.application.MyApplication;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileDescriptor;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AssetHelper {

    public String loadJSONFromAsset(String uri) {
        String json = null;
        try {
            InputStream is = MyApplication.getInstance().getApplicationContext().getAssets().open("json/"+uri);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

}
