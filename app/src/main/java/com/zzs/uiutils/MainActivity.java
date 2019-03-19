package com.zzs.uiutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.zzs.library.DialogUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showDialog(View v) {
        final List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("测试"+(i+1));
        }
        new DialogUtils(this)
                .setBackgroundResource(R.drawable.shape_dialog)
                .setTitle("测试")
                .setTitleColor(R.color.colorAccent)
                .setPositiveButton("确定", new DialogUtils.OnClickListener() {
                    @Override
                    public void onClick(DialogUtils dialog, Button button) {
                        dialog.dismiss();
                        Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNeutralButton("取消", new DialogUtils.OnClickListener() {
                    @Override
                    public void onClick(DialogUtils dialog,Button button) {
                        dialog.dismiss();
                    }
                })
                .setList(list, new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(MainActivity.this, list.get(position), Toast.LENGTH_SHORT).show();
                    }
                })
                .setAnimations(R.style.dialogWindowAnim)
                .show();
    }
}
