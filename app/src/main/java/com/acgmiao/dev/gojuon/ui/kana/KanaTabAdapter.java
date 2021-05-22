package com.acgmiao.dev.gojuon.ui.kana;


import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.acgmiao.dev.gojuon.AssetHelper;
import com.acgmiao.dev.gojuon.R;
import com.acgmiao.dev.gojuon.application.MyApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


public class KanaTabAdapter extends RecyclerView.Adapter<KanaTabAdapter.ViewHolder> {

    public static final int VIEW_TYPE_HEADER = 0;
    public static final int VIEW_TYPE_KANA = 1;
    public static final int VIEW_TYPE_DISABLED = 2;

    public int kanaType;
    public JSONArray jsonArray = null;

    public KanaTabAdapter(int kanaType) {
        this.kanaType = kanaType;
        String uri = null;
        if(kanaType == 1){
            uri="keion.json";
        } if (kanaType==2) {
            uri="dakuon.json";
        }else if (kanaType==3) {
            uri="yoon.json";
        }
        try {
            jsonArray = new JSONArray(new AssetHelper().loadJSONFromAsset(uri));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getItemViewType(final int position) {
        int type = 0;
        try {
            type = jsonArray.getJSONObject(position).getInt("type");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return type;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if(viewType == VIEW_TYPE_HEADER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header,parent,false);
        } else if(viewType == VIEW_TYPE_KANA) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kana,parent,false);
        } else if(viewType == VIEW_TYPE_DISABLED) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_disabled,parent,false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(this.getItemViewType(position) == 1){
            String hira = null;
            String kata = null;
            String roma = null;
            try {
                hira = jsonArray.getJSONObject(position).getString("hira");
                kata = jsonArray.getJSONObject(position).getString("kata");
                roma = jsonArray.getJSONObject(position).getString("roma");
            } catch (JSONException e) {
                e.printStackTrace();
            }
                holder.getHiraTextView().setText(hira);
                holder.getKataTextView().setText(kata);
                holder.getRomaTextView().setText(roma);

            holder.getCardView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String uri = ((TextView)view.findViewById(R.id.roma)).getText()+".mp3";
                    Log.i("Audio",uri);
                    try {
                        MediaPlayer player = new MediaPlayer();
                        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            public void onCompletion(MediaPlayer mp) {
                                mp.release();
                            }});
                        AssetFileDescriptor fileDescriptor = MyApplication.getInstance().getApplicationContext().getAssets().openFd("audio/"+uri);
                        player.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());
                        fileDescriptor.close();
                        player.prepare();
                        player.setLooping(false);
                        player.start();


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } else if(this.getItemViewType(position) == 0){
            try {
                holder.getTextView().setText(jsonArray.getJSONObject(position).getString("value"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public int getItemCount() {
        return this.jsonArray.length();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView hiraTextView = null;
        TextView kataTextView = null;
        TextView romaTextView = null;
        TextView textView = null;
        View cardView = null;

        public ViewHolder(View itemView) {
            super(itemView);
            hiraTextView = (TextView) itemView.findViewById(R.id.hira);
            kataTextView = (TextView) itemView.findViewById(R.id.kata);
            romaTextView = (TextView) itemView.findViewById(R.id.roma);
            textView = (TextView) itemView.findViewById(R.id.textView);
            cardView = (View) itemView.findViewById(R.id.card_view);
        }

        public TextView getHiraTextView() {
            return hiraTextView;
        }

        public TextView getKataTextView() {
            return kataTextView;
        }

        public TextView getRomaTextView() {
            return romaTextView;
        }

        public TextView getTextView() {
            return textView;
        }

        public View getCardView() {
            return cardView;
        }





    }
}