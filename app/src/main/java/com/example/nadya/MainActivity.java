package com.example.nadya;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    Button button; //Инициализируем переменную для хранения обхекта кнопки
    EditText editText; //Инициализируем переменную для хранения обхекта текстового поля

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText); //Присваеваем текстовому полю объект, полученный с Activity

        button = (Button) findViewById(R.id.button); //Присваеваем кнопке объект, полученный с Activity
        button.setOnClickListener(
                new View.OnClickListener() { //Здесь добавляется обработчик нажатия на кнопку
                    @Override
                    public void onClick(View v) {
                        Toast makeText = Toast.makeText(getApplicationContext(), editText.getText(), Toast.LENGTH_SHORT);
                        makeText.show(); //Выводим сообщение пользователю, содержащее текст из текстового поля
                    }
                }
        );
    }


}