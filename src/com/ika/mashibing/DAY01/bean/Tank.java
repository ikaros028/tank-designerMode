package com.ika.mashibing.DAY01.bean;

import com.ika.mashibing.DAY01.Dir;
import com.ika.mashibing.DAY01.ResourceMgr;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 将坦克对象单独封装
 */
public class Tank {
    private int x, y;
    private Dir dir = Dir.DOWN;
    private BufferedImage tankImg = null;
    private static final int SPEED = 5;
    private boolean moving = false;

    public Tank(int x, int y, Dir dir) {
        super();
        this.x = x;
        this.y = y;
        setDir(dir);
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        // 绘制坦克图片
        g.drawImage(tankImg, x, y, null);
        this.move();
    }

    private void move() {
        if (!moving) {
            return;
        }
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
    }

    public Bullet fire() {
        return new Bullet(this.x, this.y, this.dir);
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

    public void setDir(Dir dir) {
        this.dir = dir;
        switch (dir) {
            case LEFT: setTankImg(ResourceMgr.tankL);break;
            case UP: setTankImg(ResourceMgr.tankU);break;
            case RIGHT: setTankImg(ResourceMgr.tankR);break;
            case DOWN: setTankImg(ResourceMgr.tankD);break;
        }
    }

    public BufferedImage getTankImg() {
        return tankImg;
    }

    public void setTankImg(BufferedImage tankImg) {
        this.tankImg = tankImg;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

}
