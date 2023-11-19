package com.example.sibal.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sibal.DBHelper;
import com.example.sibal.MyAdapter;

import com.example.sibal.R;
import com.example.sibal.databinding.FragmentHomeBinding;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class HomeFragment extends Fragment {
    private MyAdapter incomeAdapter; // 추가: 수입 어댑터
    private MyAdapter expenseAdapter; // 추가: 지출 어댑터
    private ArrayList<String> incomeData; // 추가: 수입 데이터
    private ArrayList<String> expenseData; // 추가: 지출 데이터
    private ListView incomeListView; // 추가: 수입 리스트뷰
    private ListView expenseListView; // 추가: 지출 리스트뷰
    private FragmentHomeBinding binding;
    private DBHelper dbHelper; // DBHelper 객체 추가

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // 수정된 코드 추가 시작
        // earning에 str2 값을 설정
        TextView earning = binding.textView4;
        Intent intent2 = getActivity().getIntent();
        String earnstr = intent2.getStringExtra("str2");

        try {
            int earnInt = Integer.parseInt(earnstr);
            earning.setText(String.valueOf(earnInt));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        // earning2에 계산 결과 설정
        TextView earning2 = binding.textView6;
        Intent intent3 = getActivity().getIntent();
        String earnstr1 = ((Intent) intent3).getStringExtra("str2");

        try {
            Calendar calendar1 = Calendar.getInstance();
            int today = calendar1.get(Calendar.DAY_OF_MONTH);
            int lastDayOfMonth = calendar1.getActualMaximum(Calendar.DAY_OF_MONTH);
            int earnInt = Integer.parseInt(earnstr1);
            int remainingDays = lastDayOfMonth - today - 1;
            double result = (double) earnInt / remainingDays;
            String resultString = String.format(Locale.getDefault(), "%d", (int) result);
            earning2.setText(resultString);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        // 수정된 코드 추가 끝

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

