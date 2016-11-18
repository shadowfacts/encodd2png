package net.shadowfacts.encode2png;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;

/**
 * @author shadowfacts
 */
public class Encode2PNG {

	public static void encode(File input, File output) throws IOException {
		long length = input.length();
		if (length > Integer.MAX_VALUE) {
			throw new RuntimeException("Input file size " + length + " is larger than maximum size " + Integer.MAX_VALUE);
		}
		int pixels = (int)Math.ceil(length / 4.0);
		int size = (int)Math.ceil(Math.sqrt(pixels + 1));
		InputStream in = new FileInputStream(input);
		BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);

		image.setRGB(0, 0, (int)length);

		byte[] buffer = new byte[4];
		for (int i = 1; ; i++) {
			int result = in.read(buffer);
			if (result == -1) break;
			image.setRGB(i % size, i / size, encode(buffer));
		}
		ImageIO.write(image, "png", output);
	}

	public static void decode(File input, File output) throws IOException {
		FileOutputStream out = new FileOutputStream(output);

		BufferedImage image = ImageIO.read(input);

		int length = image.getRGB(0, 0);
		int pixels = (int)Math.ceil(length / 4.0);
		int size = image.getWidth();

		int remaining = length;
		for (int i = 1; i <= pixels; i++) {
			int rgb = image.getRGB(i % size, i / size);
			byte[] array = decode(rgb);
			if (remaining < 4) {
				array = Arrays.copyOfRange(array, 0, remaining);
			}
			out.write(array);

			remaining -= 4;
			if (remaining <= 0) {
				break;
			}
		}
	}

	private static int encode(byte[] buf) {
		return (((buf[3]       ) << 24) |
				((buf[2] & 0xff) << 16) |
				((buf[1] & 0xff) <<  8) |
				((buf[0] & 0xff)      ));
	}

	private static byte[] decode(int i) {
		byte[] array = new byte[4];
		array[3] = (byte)(i >> 24);
		array[2] = (byte)((i >> 16) & 0xff);
		array[1] = (byte)((i >> 8) & 0xff);
		array[0] = (byte)(i & 0xff);
		return array;
	}

}
