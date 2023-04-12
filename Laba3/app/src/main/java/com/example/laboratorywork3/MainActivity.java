package com.example.laboratorywork3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    private String fileName = "";
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.choose_file_button);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onOpenFileClick(v);
                    }
                });


        button = (Button) findViewById(R.id.save_file_button);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onSaveFileClick(v);
                    }
                });
    }

    public void onOpenFileClick(View view)
    {
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }

        else
        {
            //Разрешение предоставлено
            OpenFileDialog();
        }
    }

    public void onSaveFileClick(View view)
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
        }
        else
        {
            SaveFile();
        }

    }

    public void OnRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // Если пользователь закрыл запрос на разрешение, не дав ответа, массив grantResults будет пустым

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Разрешение было предоставлено
                    // Выполните здесь необходимые операции для включения функциональности приложения, связанной с запрашиваемым разрешением
                    OpenFileDialog();
                } else {
                    // Разрешение не было предоставлено
                    // Выполните здесь необходимые операции для выключения функциональности приложения, связанной с запрашиваемым разрешением
                }
                return;
            }

            case 2:
            {
                SaveFile();
                return;
            }
        }
    }

    public void OpenFileDialog()
    {
        OpenFileDialog fileDialog = new OpenFileDialog(this).setOpenDialogListener(new OpenFileDialog.OpenDialogListener() { //Создаем новый класс OpenFileDialog и устанавливаем новый
            //обработчик выбора файла

            @Override
            public void OnSelectedFile(String selectedFile) {
                File file = new File(selectedFile); //Создаем экземпляр класса File, используя в качестве аргумента конструктора путь к выбранному файлу
                fileName = selectedFile;  // Задаем значение выбранного файла для последующего сохранения
                StringBuilder text = new StringBuilder();

                try {
                    BufferedReader br = new BufferedReader(new FileReader(file)); //Создаем буффер для чтения файла
                    String line;
                    while ((line = br.readLine()) != null){  //Читаем построчно до каонца файла
                        text.append(line);
                        text.append('\n');
                    }
                    br.close(); //Закрываем файл
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                TextView tv = findViewById(R.id.text_view); //Получаем наш textView
                tv.setText((text.toString()));  //Устанавливаем полученных из файла текст в textView
            }
        });
        fileDialog.show(); ; // Показываем диалог выбора файла
    }

    public void SaveFile()
    {
        if(fileName != "" && fileName != null)  //Если мы открыли файл
        {
            try {
                FileOutputStream fos = new FileOutputStream(fileName); // Открываем поток для чтения

                TextView tv = findViewById(R.id.text_view); //Выбираем наш textView
                fos.write(tv.getText().toString().getBytes());  //Пишем содержимое textView в выбранный файл
                fos.close(); //Закрываем поток

                Toast.makeText(this, "Файл сохранен!", Toast.LENGTH_SHORT).show();
            }

            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }



}











