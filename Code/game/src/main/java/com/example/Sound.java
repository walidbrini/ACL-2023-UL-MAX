package com.example;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound{
    private Clip clip ;
    private FloatControl volumeControl;
    private URL soundURL[] = new URL[30];

    public Sound(){
        soundURL[0]=getClass().getResource("/sound/background.wav");
        soundURL[1]=getClass().getResource("/sound/burning.wav");
        soundURL[2]=getClass().getResource("/sound/powerup.wav");
        soundURL[3]=getClass().getResource("/sound/VirtualReality.wav");

    }
    public void setFile(int i){
        try{
            AudioInputStream son = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(son);
        }catch(Exception e){
            e.printStackTrace();
        }
        volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
    }
    public void setVolume(float volume) {
        if (volumeControl != null) {
            float range = volumeControl.getMaximum() - volumeControl.getMinimum();
            float gain = (range * volume) + volumeControl.getMinimum();
            volumeControl.setValue(gain);
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
