package src.service;

import src.view.GamePanel;
import src.view.MainFrame;
import src.view.ScoreDialog;

import java.awt.*;

/**
 * 刷新帧线程
 */
public class FreshThread extends Thread{

    public static final int FREASH=20;
    GamePanel p;

    public FreshThread(GamePanel p){
        this.p=p;
    }

    public void run(){
        while(!p.isFinish()){
            p.repaint();
            try {
                Thread.sleep(FREASH);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Container c=p.getParent();
        while(!(c instanceof MainFrame)){
            c=c.getParent();
        }
        MainFrame frame=(MainFrame) c;
        new ScoreDialog(frame);
        frame.restart();
    }
}
