package com.ika.mashibing.DAY01;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DAY01_TankFrame extends Frame {
    private int x;
    private int y;
    public DAY01_TankFrame() {
        setSize(800, 600);
        setResizable(false);
        setTitle("u_tank war");
        addWindowListener(new WindowAdapter() {
            /**
             * 窗口关闭事件
             * @param e
             */
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * 重写窗口绘制方法，当窗口初次展示或再次展示时，会执行当前方法
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        System.out.println("I'm painting the window");
        // 填充指定位置
//        g.fillRect(200, 200, 50, 50);
        // 改变坐标
        g.fillRect(x, y, 50, 50);
        x += 10;
        y += 10;
    }
}
