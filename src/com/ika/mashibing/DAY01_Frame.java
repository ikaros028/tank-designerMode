package com.ika.mashibing;

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
        // 设置窗口监听器
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

    }
}
