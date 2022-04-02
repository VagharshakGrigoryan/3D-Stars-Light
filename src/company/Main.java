package company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Main {

    volatile static boolean isFrameReadyToDraw = true;

    public static void main(String[] args) {

        final int screenWidth = 1920;
        final int screenHeight = 1080;

        JFrame jFrame = new JFrame();
        jFrame.setSize(screenWidth, screenHeight);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BufferedImage image = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);

        ImageIcon icon = new ImageIcon(image);
        JLabel picLabel = new JLabel(icon);

        BorderLayout borderLayout = new BorderLayout();
        jFrame.getContentPane().setLayout(borderLayout);

        jFrame.getContentPane().add(picLabel, BorderLayout.CENTER);

        jFrame.setVisible(true);


        Model model = new Model();
        Render render = new Render();
        long lastTime = System.currentTimeMillis();
        while (jFrame.isVisible()) {
            long time = System.currentTimeMillis();
            model.update(time - lastTime);
            lastTime = time;
            if (isFrameReadyToDraw) {
                isFrameReadyToDraw = false;
            }
            render.draw(image, model);
            SwingUtilities.invokeLater(jFrame::repaint);
            isFrameReadyToDraw = true;
        }
    }
}
