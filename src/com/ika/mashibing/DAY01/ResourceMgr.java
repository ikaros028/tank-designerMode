package com.ika.mashibing.DAY01;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 资源加载管理器
 * 初始化时将必要的资源加载到内存中
 */
public class ResourceMgr {
    public static BufferedImage tankL, tankU, tankR, tankD;
    public static BufferedImage bullL, bullU, bullR, bullD;
    static {
        try {
            tankL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("image/tankL.gif"));
            tankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("image/tankU.gif"));
            tankR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("image/tankR.gif"));
            tankD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("image/tankD.gif"));
            bullL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("image/bulletL.gif"));
            bullU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("image/bulletU.gif"));
            bullR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("image/bulletR.gif"));
            bullD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("image/bulletD.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
