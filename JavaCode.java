
import java.awt.Color;
import java.awt.Font;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.plaf.FontUIResource;

import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("App Running...");
        CreateImage("Content Creater", "Update : 07");
    }

    public static void CreateImage(String imgTitle, String UpdateTXT) {
        try {
            BufferedImage img = new BufferedImage(3000, 3000, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graph = img.createGraphics();

            // #region Background Triangles
            // Gray Bottom Right
            graph.setColor(new Color(45, 45, 45));
            for (int i = 0; i < img.getWidth(); i++) {
                graph.drawLine(img.getWidth() - i, i, img.getWidth(), i);
            }

            // Light Purple Top Left
            graph.setColor(new Color(129, 123, 173));

            for (int i = 0; i < img.getWidth(); i++) {
                graph.drawLine(0, i, img.getWidth() - i, i);
            }
            // #endregion

            // #region Add Project Title
            graph.setColor(new Color(255, 255, 255));
            graph.setFont(new Font("Arial", Font.PLAIN, GetPerc(img.getWidth(), 8)));

            int pnTitleWidth = GetStringWidth(graph, imgTitle);
            graph.drawString(imgTitle,
                    (img.getWidth() / 2) - (pnTitleWidth / 2),
                    GetPerc(img.getHeight(), 9));
            // #endregion

            // #region MIDDLE BOX

            // enough transparency : 220
            graph.setColor(new Color(200, 200, 225, 255));
            graph.fillRoundRect(
                    GetPerc(img.getWidth(), 12),
                    GetPerc(img.getHeight(), 12),
                    img.getWidth() - (GetPerc(img.getWidth(), 12) * 2),
                    img.getHeight() - (GetPerc(img.getHeight(), 12) * 2),
                    90, 90);

            // Update No
            graph.setColor(new Color(0, 0, 0));
            graph.setFont(new Font("Consolas", Font.PLAIN, GetPerc(img.getWidth(), 4)));
            graph.drawString(UpdateTXT,
                    GetPerc(img.getWidth(), 12) + GetStringWidth(graph, UpdateTXT) / 2,
                    img.getHeight() - GetPerc(img.getHeight(), 12) - GetStringWidth(graph, UpdateTXT) / 2);
            // #endregion

            File outpt = new File("SaveIMG.png");
            ImageIO.write(img, "png", outpt);
            System.out.println("Image Saved");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static int GetPerc(int n1, int perc) {
        System.out.println("Percent :" + n1 + "x" + perc + "/100 =" + (n1 * perc) / 100);
        return (n1 * perc) / 100;
    }

    public static int GetStringWidth(Graphics2D graph, String str) {
        return graph.getFontMetrics().stringWidth(str);
    }
}
