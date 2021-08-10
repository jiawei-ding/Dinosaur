package src.service;

import java.io.FileNotFoundException;

public class Sound {
    static final String DIR="music/";
    static final String BACKGROUND="background.wav";
    static final String JUMP="jump.wav";
    static final String HIT="hit.wav";

    /**
     * 播放撞击音效
     */
    static public void jump(){
        play(DIR+JUMP,false);
    }

    /**
     * 播放背景音乐
     */
    static public void hit(){
        play(DIR+HIT,false);
    }

    /**
     * 播放bgm
     */
    static public void background(){
        play(DIR+BACKGROUND,true);
    }

    /**
     *
     * @param file 音乐文件完整名称
     * @param circulate  是否循环播放
     */
    private static void play(String file,boolean circulate){
        try {
            MusicPlayer player=new MusicPlayer(file,circulate);
            player.play();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
