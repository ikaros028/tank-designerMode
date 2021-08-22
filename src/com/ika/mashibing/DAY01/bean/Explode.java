package com.ika.mashibing.DAY01.bean;

import com.ika.mashibing.DAY01.Audio;
import com.ika.mashibing.DAY01.ResourceMgr;

import java.awt.*;

public class Explode {
    public final static int WIDTH = ResourceMgr.explosion[0].getWidth();
    public final static int HEIGHT = ResourceMgr.explosion[0].getHeight();
    private int x, y;
    private boolean live = true;
    /**爆炸进行到第几步*/
    private int step = 0;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }

    public void paint(Graphics g) {
        if (step >= ResourceMgr.explosion.length) {
            step = 0;
            live = false;
            return;
        }
        g.drawImage(ResourceMgr.explosion[step++], x, y, null);
    }
    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }
}
