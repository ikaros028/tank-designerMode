package com.ika.mashibing.DAY01.bean;

import com.ika.mashibing.DAY01.Dir;
import com.ika.mashibing.DAY01.Group;
import com.ika.mashibing.DAY01.ResourceMgr;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 将坦克对象单独封装
 */
public class Tank {
    public final static int WIDTH = ResourceMgr.tankD.getWidth();
    public final static int HEIGHT = ResourceMgr.tankD.getHeight();
    private int x, y;
    private Dir dir = Dir.DOWN;
    private BufferedImage tankImg = null;
    private Explode boom;
    private static final int SPEED = 5;
    private boolean moving = true;
    private boolean live = true;
    private boolean fire = false;
    private Group group = Group.BAD;
    private Random rd = new Random();

    public Tank(int x, int y, Dir dir, Group gr) {
        super();
        this.x = x;
        this.y = y;
        this.group = gr;
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

        fire = rd.nextInt(10) > 5;
    }

    /**
     * 判断坦克是否相撞，若重叠则设置对象消亡
     * @param tank
     */
    public void colideWith(Tank tank) {
        // 新建矩形，判断矩形是否重叠
        Rectangle rectThis = new Rectangle(x, y, WIDTH, HEIGHT);
        Rectangle rectSpecified = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
        if (group != tank.group && rectThis.intersects(rectSpecified)) {
            setLive(false);
            tank.setLive(false);
        }
    }
    /**
     * 判断子弹是否相撞，若重叠则设置对象消亡
     * @param bullet
     */
    public void colideWith(Bullet bullet) {
        // 新建矩形，判断矩形是否重叠
        Rectangle rectThis = new Rectangle(x, y, WIDTH, HEIGHT);
        Rectangle rectSpecified = new Rectangle(bullet.getX(), bullet.getY(), Bullet.WIDTH, Bullet.HEIGHT);
        if (group != bullet.getGroup() && rectThis.intersects(rectSpecified)) {
            setLive(false);
            bullet.setLive(false);
        }
    }

    public Bullet fire(int x, int y) {
        return new Bullet(x, y, this.dir, this.group);
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

    public Dir getDir() {
        return dir;
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

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public boolean isFire() {
        return fire;
    }

    public void setFire(boolean fire) {
        this.fire = fire;
    }

    public Explode getBoom() {
        return this.boom;
    }

    public void addBoom() {
        this.boom = new Explode(x, y);
    }
}
