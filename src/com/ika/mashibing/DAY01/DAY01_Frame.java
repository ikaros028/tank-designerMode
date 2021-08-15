package com.ika.mashibing.DAY01;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DAY01_Frame {

    public static void main(String[] args) {
        // 创建继承了Frame的用户自定义窗口对象
        DAY01_TankFrame user_T = new DAY01_TankFrame();
        user_T.setVisible(true);
        while (true) {
            // 线程停歇100ms
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            user_T.repaint();
        }

        // 通过循环调用，产生自动变化

    }
}
