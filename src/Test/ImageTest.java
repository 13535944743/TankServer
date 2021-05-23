package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

class ImageTest {

	@Test
	void test() {
		try {
			BufferedImage image = ImageIO.read(new File("E:/画集/画集/P站插画合集第九期"));
			assertNotNull(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
