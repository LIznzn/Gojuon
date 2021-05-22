package com.acgmiao.dev.gojuon.ui.kana;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.acgmiao.dev.gojuon.R;

import org.json.JSONArray;

public class KanaTabFragment extends Fragment {

    private int kanaType;

    public KanaTabFragment(int kanaType) {
        this.kanaType = kanaType;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_kana_tab, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        int column = 6;
        if(kanaType == 3){
            column = 4;
        }
        GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(), column);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new KanaTabAdapter(this.kanaType));

        return view;
    }
}