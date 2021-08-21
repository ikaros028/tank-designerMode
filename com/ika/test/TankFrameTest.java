import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
public class TankFrameTest {
    @Test
    public void test() {
        // 测试报错
//        fail("Not yet implemented");
        // 断言，判断是否。。
        assertNotNull(new Object());

        // 测试Image
        try {
            BufferedImage bImg = ImageIO.read(new File("D:\\QQMusicCache\\QQMusicPicture\\UserPic1443553962-100.jpg"));
            assertNotNull(bImg);

            // 读取工程内部资源D:\projects\tank-designerMode\src\resource\image\BadTank1.png
            BufferedImage bImg2 = ImageIO.read(TankFrameTest.class.getClassLoader()
                        .getResourceAsStream("resource/image/BadTank1.png"));
            // 虚拟机基础知识：ClassLoader this.getClass().getClassLoader()
            assertNotNull(bImg2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}