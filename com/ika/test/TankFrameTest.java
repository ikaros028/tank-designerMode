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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}