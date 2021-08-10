package src.service;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MusicPlayer implements Runnable {
    File soundFile;
    Thread thread;
    boolean circulate;

    public MusicPlayer(String filepath,boolean circulate) throws FileNotFoundException {
        this.circulate=circulate;
        soundFile=new File(filepath);
        if(!soundFile.exists()){
            throw new FileNotFoundException(filepath+"未找到");
        }
    }

    /**
     * 播放
     */
    public void play(){
        thread =new Thread(this);
        thread.start();
    }

    /**
     * 停止播放
     */
    public void stop(){
        thread.stop();
    }

    @Override
    public void run() {
        byte[] auBuffer=new byte[1024*128];
        do{
            AudioInputStream audioInputStream=null;
            SourceDataLine auline=null;

            try {
                audioInputStream= AudioSystem.getAudioInputStream(soundFile);
                AudioFormat format= audioInputStream.getFormat();
                DataLine.Info info=new DataLine.Info(SourceDataLine.class,format);
                auline=(SourceDataLine) AudioSystem.getLine(info);
                auline.open(format);
                auline.start();
                int  byteCount=0;
                while(byteCount !=-1){
                    byteCount=audioInputStream.read(auBuffer,0,auBuffer.length);
                    if(byteCount>=0){
                        auline.write(auBuffer,0,byteCount);
                    }
                }

            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }finally {
                auline.drain();
                auline.close();
            }
        }while(circulate);

    }
}


