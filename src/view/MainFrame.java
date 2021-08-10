package src.view;

import src.service.ScoreRecorder;
import src.service.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {

    public MainFrame(){
        restart();
        setBounds(340,150,821,260);
        setTitle("奔跑吧！小恐龙！");
        Sound.background();
        ScoreRecorder.init();
        addListener();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * restart
     */
    public void restart(){
        Container c=getContentPane();
        c.removeALL();
        GamePanel panel = new GamePanel();
        c.add(panel);
        addKeyListener(panel);
        c.validate();
    }

    /**
     *
     */
    private void addListener(){
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                ScoreRecorder.saveScore();
            }
        });
    }
}
