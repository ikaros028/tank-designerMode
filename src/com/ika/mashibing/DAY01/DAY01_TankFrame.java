package com.ika.mashibing.DAY01;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DAY01_TankFrame extends Frame {
    private int x;
    private int y;
    public DAY01_TankFrame() {
        setSize(800, 600);
        setResizable(false);
        setTitle("u_tank war");
        // 内部类监听
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
        // 添加按键监听事件，键盘触发变化事件
        addKeyListener(new myKeyListener());
    }

    /**
     * 自定义按键监听事件
     */
    class myKeyListener extends KeyAdapter {
        /**
         * Invoked when a key has been pressed.
         *
         * @param e
         */
        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println("the key was pressed : " + e.getKeyCode());
            x += 20;
            // 调用重绘方法，由重绘方法调用paint
            repaint();
        }

        /**
         * Invoked when a key has been released.
         *
         * @param e
         */
        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println("the key was release : " + e.getKeyCode());

        }
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
