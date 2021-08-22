package com.ika.mashibing.DAY01.bean;

import com.ika.mashibing.DAY01.Dir;
import com.ika.mashibing.DAY01.ResourceMgr;
import com.ika.mashibing.DAY01.TankFrame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;

public class Bullet {
    public static final int WIDTH = ResourceMgr.bullD.getWidth();
    public static final int HEIGHT = ResourceMgr.bullD.getHeight();
    private static final int SPEED = 20;
    private BufferedImage bullImg = null;
    private TankFrame tf = null;
    private boolean live = true;
    private int x,y;
    private Dir dir;

    public Bullet(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        setDir(dir);
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.drawImage(bullImg, x, y, null);
        move();
    }

    private void move() {
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }

        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            live = false;
        }
    }

    public void setDir(Dir dir) {
        this.dir = dir;
        switch (dir) {
            case LEFT: setBullImg(ResourceMgr.bullL);break;
            case UP: setBullImg(ResourceMgr.bullU);break;
            case RIGHT: setBullImg(ResourceMgr.bullR);break;
            case DOWN: setBullImg(ResourceMgr.bullD);break;
        }
    }

    /**
     * 判断矩阵是否重叠，若重叠则设置对象消亡
     * @param tank
     */
    public void colideWith(Tank tank) {
        // 新建矩形，判断矩形是否重叠
        Rectangle rectThis = new Rectangle(x, y, WIDTH, HEIGHT);
        Rectangle rectSpecified = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
        if (rectThis.intersects(rectSpecified)) {
            setLive(false);
            tank.setLive(false);
        }
    }
    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public BufferedImage getBullImg() {
        return bullImg;
    }

    public void setBullImg(BufferedImage bullImg) {
        this.bullImg = bullImg;
    }

}
