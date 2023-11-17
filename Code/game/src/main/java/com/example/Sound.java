package com.example;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound{
    Clip clip ;
    URL soundURL[] = new URL[30];

    public Sound(){
        soundURL[0]=getClass().getResource("/sound/background.wav");
        soundURL[1]=getClass().getResource("/sound/burning.wav");
        soundURL[2]=getClass().getResource("/sound/powerup.wav");
    }
    public void setFile(int i){
        try{
            AudioInputStream son = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(son);
        }catch(Exception e){

        }
    }
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }

}
