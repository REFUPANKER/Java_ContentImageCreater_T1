
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
        CreateImage("Content Creater",
                "Update : 07",
                new String[] {
                        "Version updated to 0.8",
                        "Added Creating Image Method for developers",
                        "Added to our GitHub Page",
                });
    }

    public static void CreateImage(String imgTitle, String UpdateTXT, String[] newFeatures) {
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
            graph.drawString(imgTitle,
                    (img.getWidth() / 2) - (GetStringWidth(graph, imgTitle) / 2),
                    GetPerc(img.getHeight(), 9));
            // #endregion

            // #region MIDDLE BOX

            // enough transparency : 220
            graph.setColor(new Color(200, 200, 225, 255));
            graph.fillRoundRect(
                    GetPerc(img.getWidth(), 3),
                    GetPerc(img.getHeight(), 12),
                    img.getWidth() - (GetPerc(img.getWidth(), 3) * 2),
                    img.getHeight() - (GetPerc(img.getHeight(), 10) * 2),
                    90, 90);

            // Update TXT
            graph.setColor(new Color(0, 0, 0));
            graph.setFont(new Font("Consolas", Font.PLAIN, GetPerc(img.getWidth(), 2)));
            graph.drawString(UpdateTXT,
                    GetPerc(img.getWidth(), 1) + GetStringWidth(graph, UpdateTXT) / 2,
                    img.getHeight() - GetPerc(img.getHeight(), 6) - GetStringWidth(graph, UpdateTXT) / 2);
            // #endregion

            // #region New Features Title

            graph.setColor(new Color(0, 0, 0));
            graph.setFont(new Font("Consolas", Font.PLAIN, GetPerc(img.getWidth(), 5)));
            graph.drawString("New Features",
                    (img.getWidth() / 2) - (GetStringWidth(graph, "New Features") / 2),
                    GetPerc(img.getHeight(), 18));

            // under-line new features title
            graph.setColor(new Color(0, 0, 0));
            graph.setStroke(new BasicStroke(10));
            graph.drawLine(
                    GetPerc(img.getWidth(), 8),
                    GetPerc(img.getHeight(), 20),
                    img.getWidth() - GetPerc(img.getWidth(), 8),
                    GetPerc(img.getHeight(), 20));

            // #endregion

            // #region Features Lister
            
            graph.setFont(new Font("Lucida Console",Font.PLAIN,GetPerc(img.getWidth(), 3)));
            int topDifference=GetPerc(img.getHeight(), 25);
            for (String features : newFeatures) {
                graph.setColor(new Color(103, 84, 255));
                graph.drawOval(
                    GetPerc(img.getWidth(), 7),
                     topDifference-(GetPerc(img.getWidth(), 3)/2),
                      50,
                      50);

                graph.setColor(new Color(0, 0, 0));
                graph.drawString(features, 
                GetPerc(img.getWidth(), 10), 
                topDifference);
                topDifference+=GetPerc(GetPerc(img.getWidth(), 3),120);
            }
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
