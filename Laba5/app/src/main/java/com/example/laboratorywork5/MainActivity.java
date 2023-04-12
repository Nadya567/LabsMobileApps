package com.example.laboratorywork5;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    VideoView videoPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoPlayer =  (VideoView)findViewById(R.id.videoPlayer);
        Uri myVideoUri= Uri.parse( "android.resource://" + getPackageName() + "/" + R.raw.tiger_video); //Указываем путь до файла
        videoPlayer.setVideoURI(myVideoUri); //Устанавливаем ссылку на файл для проигрывателя

        MediaController mediaController = new MediaController(this);
        videoPlayer.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoPlayer);

        /*buttonPlay = (Button) findViewById(R.id.button_play_video);
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play(view);
            }
        });

        buttonPause = (Button) findViewById(R.id.button_pause_video);
        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pause(view);
            }
        });

        buttonStop = (Button) findViewById(R.id.button_stop_video);
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stop(view);
            }
        });*/
    }

    Button buttonPlay;
    Button buttonPause;
    Button buttonStop;

    public void play(View view){
        videoPlayer.start(); //Начинает проигрование видео
    }
    public void pause(View view){
        videoPlayer.pause(); //Приостанавливает проигрование
    }
    public void stop(View view){
        videoPlayer.stopPlayback(); //Полностью останавлиевает видео
        videoPlayer.resume(); //Позволяет снова воспроизвести видео после остановки
    }
}