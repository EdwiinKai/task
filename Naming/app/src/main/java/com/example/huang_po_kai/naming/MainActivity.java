package com.example.huang_po_kai.naming;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btn_java1;
            Button btn_java2;
            Button btn_java3;
            Button btn_java5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_java1=(Button)findViewById(R.id.btn1);
        btn_java1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,calendarActivity.class);
                startActivity(intent);
            }
        });
        btn_java2=(Button)findViewById(R.id.btn2);
        btn_java2.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){
                Intent intent=new Intent(MainActivity.this,nfctouchActivity.class);
                startActivity(intent);
            }
        });
        btn_java3=(Button)findViewById(R.id.btn3);
        btn_java3.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){
                Intent intent=new Intent(MainActivity.this,Most_recent_newsActivity.class);
                startActivity(intent);
            }
        });
        btn_java5=(Button)findViewById(R.id.btn5);
        btn_java5.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){
                Intent intent=new Intent(MainActivity.this,weather.class);
                startActivity(intent);
            }
        });
    }
}
