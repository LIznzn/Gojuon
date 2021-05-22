package com.acgmiao.dev.gojuon.ui.kana;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.acgmiao.dev.gojuon.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class KanaFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kana, container, false);


        TabLayout tabLayout = view.findViewById(R.id.tab_kana);
        ViewPager2 viewPager = view.findViewById(R.id.view_pager);
        viewPager.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                switch (position) {
                    case 0:
                        return new KanaTabFragment(1);
                    case 1:
                        return new KanaTabFragment(2);
                    case 2:
                        return new KanaTabFragment(3);
                    default:
                        //should not be here
                        return null;
                }
            }

            @Override
            public int getItemCount() {
                return 3;
            }
        });
        new TabLayoutMediator(tabLayout, viewPager, ((tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText(R.string.seion);
                    break;
                case 1:
                    tab.setText(R.string.dakuon);
                    break;
                case 2:
                    tab.setText(R.string.yoon);
                    break;
                default:
                    //should not be here
            }
        })).attach();

        return view;
    }

}