package com.ika.mashibing;

public class U_Frame {

    public static void main(String[] args) {
        // 创建继承了Frame的用户自定义窗口对象
        TankFrame user_T = new TankFrame();
        user_T.setVisible(true);

        // 初始化敌方坦克
        for (int i = 0; i < TankFrame.LEVEL; i++) {
            user_T.addEnemy(50 + i*50, 200);
        }
        // 通过循环调用，产生自动变化
        while (true) {
            // 线程停歇100ms
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            user_T.repaint();
        }

    }
}
