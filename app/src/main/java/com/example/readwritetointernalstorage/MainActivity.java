package com.example.readwritetointernalstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    // buat deklarasi untuk masing masing view
    // untuk digunakan dalam proses selanjutnya
    EditText inputField;
    Button writeButton;
    TextView displayText;
    Button readButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // cari elemen dengan id input_field
        inputField = findViewById(R.id.input_field);

        // cari elemen dengan id write_button
        writeButton = findViewById(R.id.write_button);

        // cari elemen dengan id display_text
        displayText = findViewById(R.id.display_text);

        // cari elemen dengan id read_button
        readButton = findViewById(R.id.read_button);

        // ketika write button ditekan maka
        writeButton.setOnClickListener(new View.OnClickListener() {

            // lanjutkan ke method onClick
            @Override
            public void onClick(View v) {
                writeFile();
            }
        });

        // ketika read button ditekan
        readButton.setOnClickListener(new View.OnClickListener() {

            // lanjutkan ke method readFile()
            @Override
            public void onClick(View v) {
                readFile();
            }
        });
    }

    // method writeFile pada writebutton diatas
    private void writeFile() {

        // ambil inputan dari inputField
        String textToSave = inputField.getText().toString();

        try {
            // cari dan simpan file bernama Tutorial File.txt
            FileOutputStream fileOutputStream = openFileOutput("Tutorial File.txt", MODE_PRIVATE);

            // tulis teks kedalamnya
            fileOutputStream.write(textToSave.getBytes());

            // tutup proses penulsian
            fileOutputStream.close();

            // dan terakhir buat popup data berhasil disimpan
            Toast.makeText(getApplicationContext(), "Teks tersimpan kedalam" + getFilesDir() + '/' + "Tutorial File.txt", Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // method readFile readButton diatas
    private void readFile() {

        try {
            // cari file dan buka file txt
            FileInputStream fileInputStream = openFileInput("Tutorial File.txt");

            // proses membaca isi file
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();

            // proses penulisan isi file menjadi baris demi baris
            String lines;

            // lakukan perulangan sampai data didalam bufferedReader habis
            while ((lines = bufferedReader.readLine()) != null) {
                // masukan isi dari variabel lines kedalam variabel stringBuffer
                // dengan menggunakan append
                stringBuffer.append(lines + "\n");
            }

            // tampilkan kedalam textView
            displayText.setText(stringBuffer.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}