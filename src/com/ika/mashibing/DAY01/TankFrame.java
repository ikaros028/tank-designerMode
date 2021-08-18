package com.ika.mashibing.DAY01;

import com.ika.mashibing.DAY01.bean.Bullet;
import com.ika.mashibing.DAY01.bean.Tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {
    private int x;
    private int y;

    Tank tank = new Tank(200,200, Dir.DOWN);
    Bullet bul = new Bullet(300, 300, Dir.DOWN);

    // 通过开关，允许同时执行开事件
    /** 左移开关 */
    private boolean left = false;
    /** 右移开关 */
    private boolean right = false;
    /** 上移开关 */
    private boolean up = false;
    /** 下移开关 */
    private boolean down = false;
    public TankFrame() {
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
                closeWindow();
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
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    left = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    right = true;
                    break;
                case KeyEvent.VK_UP:
                    up = true;
                    break;
                case KeyEvent.VK_DOWN:
                    down = true;
                    break;
                case KeyEvent.VK_ESCAPE:
                    closeWindow();
                default:
                    break;
            }
            setMainTankDir();
//            move("press " + KeyEvent.getKeyText(e.getKeyCode()));
        }

        /**
         * Invoked when a key has been released.
         *
         * @param e
         */
        @Override
        public void keyReleased(KeyEvent e) {
//            System.out.println("the key was release : " + e.getKeyCode());
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    left = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    right = false;
                    break;
                case KeyEvent.VK_UP:
                    up = false;
                    break;
                case KeyEvent.VK_DOWN:
                    down = false;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }
    }
    private void setMainTankDir() {
        if (!left && !up && !right && !down) {
            tank.setMoving(false);
        } else {
            tank.setMoving(true);
        }
        if (left) {
            tank.setDir(Dir.LEFT);
        }
        if (up) {
            tank.setDir(Dir.UP);
        }
        if (right) {
            tank.setDir(Dir.RIGHT);
        }
        if (down) {
            tank.setDir(Dir.DOWN);
        }

    }

    /**
     * 重写窗口绘制方法，当窗口初次展示或再次展示时，会执行当前方法
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        tank.paint(g);
        bul.paint(g);

    }

    /**
     * 关闭窗口，退出程序
     */
    private void closeWindow() {
        System.exit(0);
    }

    private void move(String msg) {
        System.out.println(msg + ": " + left + ", " + right + ", " + up + ", " + down);
        if (left) {
            x -= 10;
        }
        if (right) {
            x += 10;
        }
        if (up) {
            y -= 10;
        }
        if (down) {
            y += 10;
        }
        // 调用重绘方法，由重绘方法调用paint
        repaint();
    }
}
