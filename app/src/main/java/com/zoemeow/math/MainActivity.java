package com.zoemeow.math;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zoemeow.math.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();

        list = new ArrayList<String>();
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                this.list);
        binding.listView.setAdapter(adapter);

        setContentView(viewRoot);

        // +
        binding.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddToHistory(binding.editTextNum1.getText().toString(), binding.editTextNum2.getText().toString(), 0);
            }
        });

        // -
        binding.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddToHistory(binding.editTextNum1.getText().toString(), binding.editTextNum2.getText().toString(), 1);
            }
        });

        // *
        binding.btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddToHistory(binding.editTextNum1.getText().toString(), binding.editTextNum2.getText().toString(), 2);
            }
        });

        // /
        binding.btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddToHistory(binding.editTextNum1.getText().toString(), binding.editTextNum2.getText().toString(), 3);
            }
        });
    }

    private void AddToHistory(String num1, String num2, int mathType) {
        try {
            double n1 = Double.parseDouble(num1);
            double n2 = Double.parseDouble(num2);
            double nResult = 0.0;
            String tmp = "";

            switch (mathType) {
                case 0:
                    nResult = n1 + n2;
                    tmp = "+";
                    break;
                case 1:
                    nResult = n1 - n2;
                    tmp = "-";
                    break;
                case 2:
                    nResult = n1 * n2;
                    tmp = "*";
                    break;
                case 3:
                    nResult = n1 / n2;
                    tmp = "/";
                    break;
                default:
                    throw new Exception("Invaild math type!");
            }

            String result = String.format("%f %s %f\n= %f", n1, tmp, n2, nResult);
            list.add(result);
            adapter.notifyDataSetChanged();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG)
                    .show();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putStringArrayList("string1", list);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            list = (ArrayList<String>) savedInstanceState.getSerializable("string1");
            adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1,
                    this.list);
            binding.listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }
}