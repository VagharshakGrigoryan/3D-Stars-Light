package company;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Render {
    public void draw(BufferedImage image, Model model) {
        Graphics2D graphics = image.createGraphics();
        graphics.setPaint(new Color(0, 0, 0, 255));
        graphics.fillRect(0, 0, image.getWidth(), image.getHeight());
        for (Point point : model.getPoints()) {
            int sx = image.getWidth() / 2 + (int) (image.getWidth() / 2 * point.x / point.z);
            int sy = image.getHeight() / 2 + (int) (image.getHeight() / 2 * point.y / point.z);
            if (sx < image.getWidth() && sx >= 0 && sy < image.getHeight() && sy >= 0) {
                int color = 255 + (int) (point.z * (255 / Math.abs(Model.INITIAL_Z_COORD)));
                image.setRGB(sx, sy, 0xff6347 | color << 16 | color << 8 | color);
            }

        }
    }
}
