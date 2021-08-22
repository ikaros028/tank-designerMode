package com.ika.mashibing.DAY01;

import com.ika.mashibing.DAY01.bean.Bullet;
import com.ika.mashibing.DAY01.bean.Tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * 坦克大战游戏界面，包含全部的游戏元素：主角坦克、敌人坦克、子弹
 */
public class TankFrame extends Frame {
    private int x;
    private int y;
    public static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;
    public final static int LEVEL = 3;
    List<Bullet> bullets = new ArrayList<Bullet>();
    List<Tank> enemies = new ArrayList<Tank>();
    Tank tank = new Tank(200,400, Dir.UP, Group.GOOD);

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
        setSize(GAME_WIDTH, GAME_HEIGHT);
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

    Image offscreenImg = null;
    /**
     * 利用双缓存解决刷新闪烁问题
     * 将变化先由gOffScreen画笔绘制到内存中的offscreenImg图片上，最后让画笔直接用g画笔画出整张图片
     * 避免绘制多个点中途刷新未定位置的对象，引起闪烁问题
     * repaint方法原本会先调用update、再调用paint方法，重写后在update中绘制完毕多点对象到图像后，再调paint一口气绘制图像
     * @param g
     */
    @Override
    public void update(Graphics g) {
        if (offscreenImg == null) {
            offscreenImg = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offscreenImg.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0,0,GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offscreenImg, 0,0, null);
    }

    /**
     * 重写窗口绘制方法，当窗口初次展示或再次展示时，会执行当前方法
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString(" 子弹的数量：" + bullets.size(), 10, 60);
        g.drawString(" 敌军的数量：" + enemies.size(), 10, 80);
        g.setColor(c);
        if (tank.isLive()) {
            tank.paint(g);
        } else {
            if (tank.getBoom() == null) {
                tank.addBoom();
            }
            if (tank.getBoom().isLive()) {
                tank.getBoom().paint(g);
            } else {
                g.setColor(Color.RED);
                g.drawString(" 游戏结束！！ ", 10, 100);
                g.setColor(c);
                return;
            }
        }
        for (int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i).isLive()) {
                bullets.get(i).paint(g);
            } else {
                bullets.remove(i);
            }
        }
        for (int i = 0; i < enemies.size(); i++) {
            Tank en = enemies.get(i);
            if (en.isLive()) {
                en.paint(g);
                if (en.isFire()) {
                    bullets.add(en.fire(en.getX() + Tank.WIDTH/2 - Bullet.WIDTH/2, en.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2));
                }
            } else {
                if (en.getBoom() == null) {
                    en.addBoom();
                }
                if (en.getBoom().isLive()) {
                    en.getBoom().paint(g);
                } else {
                    enemies.remove(i);
                }
            }
        }
        // 判断碰撞
        for (int i = 0; i < enemies.size(); i++) {
            // 坦克与子弹
            for (int j = 0; j < bullets.size(); j++) {
                enemies.get(i).colideWith(bullets.get(j));
            }
//            // 坦克与坦克（友伤）
//            for (int j = 0; j < enemies.size(); j++) {
//                if (i == j) {
//                    continue;
//                }
//                enemies.get(i).colideWith(enemies.get(j));
//            }
            // 坦克与主坦
            enemies.get(i).colideWith(tank);
        }
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
                case KeyEvent.VK_CONTROL:
                    bullets.add(tank.fire(tank.getX() + Tank.WIDTH/2 - Bullet.WIDTH/2, tank.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2));
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

    public void addEnemy (int x, int y) {
        addEnemy(x, y, Dir.DOWN);
    }
    public void addEnemy (int x, int y, Dir dir) {
        enemies.add(new Tank(x, y, dir, Group.BAD));
    }
}
