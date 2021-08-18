package com.ika.mashibing.DAY01.bean;

import com.ika.mashibing.DAY01.DAY02_Dir;

import java.awt.*;

/**
 * 将坦克对象单独封装
 */
public class Tank {
    private int x, y;
    private DAY02_Dir dir = DAY02_Dir.DOWN;
    private static final int SPEED = 10;

    public Tank(int x, int y, DAY02_Dir dir) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void paint(Graphics g) {
        // 改变坐标
        g.fillRect(x, y, 50, 50);
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

    public DAY02_Dir getDir() {
        return dir;
    }

    public void setDir(DAY02_Dir dir) {
        this.dir = dir;
    }
}