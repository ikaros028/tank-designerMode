package com.ika.mashibing.DAY01;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DAY01_Frame {

    public static void main(String[] args) {
	    // Frame类
        Frame f = new Frame();
        // 设置宽高，像素
        f.setSize(800, 600);
        // 设置大小可变
        f.setResizable(true);
        // 设置标题
        f.setTitle("tank war");
        // 设置窗口监听器，重写关闭窗口的方法
        f.addWindowListener(new WindowAdapter() {
            /**
             * 窗口关闭事件
             * @param e
             */
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        // 显示窗口
        f.setVisible(true);


        // 创建继承了Frame的用户自定义窗口对象
        DAY01_TankFrame user_T = new DAY01_TankFrame();
        user_T.setVisible(true);
        user_T.setVisible(false);
        user_T.setVisible(true);
    }
}
