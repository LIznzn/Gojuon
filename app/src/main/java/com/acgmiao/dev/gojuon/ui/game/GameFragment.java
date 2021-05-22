package com.acgmiao.dev.gojuon.ui.game;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.acgmiao.dev.gojuon.R;

public class GameFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_game, container, false);

        TextView textView = view.findViewById(R.id.text_game_main);
        Button option1 = view.findViewById(R.id.btn_game_option1);
        Button option2 = view.findViewById(R.id.btn_game_option2);
        Button option3 = view.findViewById(R.id.btn_game_option3);
        Button option4 = view.findViewById(R.id.btn_game_option4);

        textView.setText("あ");
        option1.setText("a");
        option2.setText("i");
        option3.setText("u");
        option4.setText("o");



        return view;
    }



}