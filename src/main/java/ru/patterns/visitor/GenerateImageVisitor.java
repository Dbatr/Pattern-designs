package ru.patterns.visitor;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import ru.patterns.models.Theme;

import javax.imageio.ImageIO;

/**
 * Паттерн Visitor
 */
public class GenerateImageVisitor implements ThemeVisitor {
    @Override
    public void visit(Theme theme) {
        String primaryColorHex = theme.getPrimaryColor();
        String secondaryColorHex = theme.getSecondaryColor();

        Color primaryColor = Color.decode(primaryColorHex);
        Color secondaryColor = Color.decode(secondaryColorHex);

        BufferedImage image = new BufferedImage(400, 200, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        g2d.setColor(primaryColor);
        g2d.fillRect(50, 50, 300, 50);

        g2d.setColor(secondaryColor);
        g2d.fillRect(50, 120, 300, 50);


        g2d.setColor(Color.BLACK);
        g2d.drawString("Primary Color: " + primaryColorHex, 50, 40);
        g2d.drawString("Secondary Color: " + secondaryColorHex, 50, 110);

        g2d.dispose();


        try {
            ImageIO.write(image, "PNG", new File("assets/theme_colors.png"));
            System.out.println("Image generated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saving image.");
        }
    }
}
