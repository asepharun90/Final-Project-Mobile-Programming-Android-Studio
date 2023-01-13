package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HasilActivity extends AppCompatActivity {

    public TextView btnBackmenu;
    public ImageView cek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);

        TextView hasil = findViewById(R.id.hasil);
        TextView nilai = findViewById(R.id.nilai);

        hasil.setText("Jawaban Benar : "+ QuizActivity.benar+"\nJawaban Salah : "+ QuizActivity.salah);
        nilai.setText(""+ QuizActivity.hasil);

        btnBackmenu = findViewById(R.id.btnBackmenu);

        btnBackmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HasilActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

    }

    public void ulangi(View view){
        finish();
        Intent a = new Intent(getApplicationContext(), QuizActivity.class);
        startActivity(a);
    }
}