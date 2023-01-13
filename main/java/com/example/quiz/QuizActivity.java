package com.example.quiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    public ImageView btnBack;
    TextView kuis;
    RadioGroup rg;
    RadioButton PilihanA, PilihanB, PilihanC, PilihanD;
    int nomor = 0;
    public static int hasil, benar, salah;

    //pertanyaan
    String[] pertanyaan_kuis = new String[]{
            "1. Jika kamu punya tiga jeruk dan kamu mengambil dua. Berapa banyak jeruk yang kamu punya?",
            "2. Simbol garis - garis yang dibawahnya ada angka - angka yang selalu ditemukan pada produk disebut",
            "3. ASEAN berdiri pada tanggal",
            "4. Tidak punya kaki, tidak punya sayap, tapi bisa terbang. Apakah aku?",
            "5. Bendera negara Asia Tenggara yang apabila dibalik tida ada perubahan susunan warna adalah"
    };

    //pilihan jawaban a, b, c, d
    String[] pilihan_jawaban = new String[]{
            "Dua", "Tiga", "Empat", "Lima",
            "Fingerprint", "Codec", "Captcha", "Barcode",
            "8 Agustus 1968", "8 Agustus 1965", "8 Agustus 1967", "17 Agustus 1967",
            "Pesawat Terbang", "Asap", "Unggas", "Burung",
            "Indonesia", "Thailand", "Vietnam", "Laos"
    };

    //jawaban benar
    String[] jawaban_benar = new String[]{
            "Dua",
            "Barcode",
            "8 Agustus 1967",
            "Asap",
            "Thailand"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        kuis = findViewById(R.id.kuis);
        rg = findViewById(R.id.pilihan);
        PilihanA = findViewById(R.id.pilihanA);
        PilihanB = findViewById(R.id.pilihanB);
        PilihanC = findViewById(R.id.pilihanC);
        PilihanD = findViewById(R.id.pilihanD);

        kuis.setText(pertanyaan_kuis[nomor]);
        PilihanA.setText(pilihan_jawaban[0]);
        PilihanB.setText(pilihan_jawaban[1]);
        PilihanC.setText(pilihan_jawaban[2]);
        PilihanD.setText(pilihan_jawaban[3]);

        rg.check(0);
        benar = 0;
        salah = 0;
    }

    private void showDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Quiz Belum Selesai! Yakin Keluar?");
        alertDialogBuilder
                .setMessage("Klik Ya untuk keluar!")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void next(View view) {
        if (PilihanA.isChecked() || PilihanB.isChecked() || PilihanC.isChecked() || PilihanD.isChecked()) {

            RadioButton jawaban_user = (RadioButton) findViewById(rg.getCheckedRadioButtonId());
            String ambil_jawaban_user = jawaban_user.getText().toString();
            rg.check(0);
            if (ambil_jawaban_user.equalsIgnoreCase(jawaban_benar[nomor])) benar++;
            else salah++;
            nomor++;
            if (nomor < pertanyaan_kuis.length) {
                kuis.setText(pertanyaan_kuis[nomor]);
                PilihanA.setText(pilihan_jawaban[(nomor * 4) + 0]);
                PilihanB.setText(pilihan_jawaban[(nomor * 4) + 1]);
                PilihanC.setText(pilihan_jawaban[(nomor * 4) + 2]);
                PilihanD.setText(pilihan_jawaban[(nomor * 4) + 3]);

            } else {
                hasil = benar * 20;
                Intent selesai = new Intent(getApplicationContext(), HasilActivity.class);
                startActivity(selesai);
            }
        }
        else {
            Toast.makeText(this,"Kamu Jawab Dulu",Toast.LENGTH_LONG).show();
        }
    }
}