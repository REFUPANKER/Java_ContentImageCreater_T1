
import java.awt.Color;
import java.awt.Font;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("App Running...");

        CreateImage(
                new Color(129, 123, 173),
                new Color(45, 45, 45),
                "Content Creater",
                "Startup : 01",
                new String[] {
                        "Version updated to 0.8",
                        "Added to our GitHub Page",
                        "Created Next steps about this application",
                        "Started to remaking app for Mobile devices",
                });
    }

    public static void CreateImage(Color TopLeftColor, Color BottomRightColor, String imgTitle, String UpdateTXT,
            String[] newFeatures) {
        try {
            BufferedImage img = new BufferedImage(3000, 3000, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graph = img.createGraphics();

            // #region Background Triangles
            // Gray Bottom Right - default : (45, 45, 45)
            graph.setColor(BottomRightColor);
            for (int i = 0; i < img.getWidth(); i++) {
                graph.drawLine(img.getWidth() - i, i, img.getWidth(), i);
            }

            // Light Purple Top Left - default : (129, 123, 173)
            graph.setColor(TopLeftColor);

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

            graph.setFont(new Font("Lucida Console", Font.PLAIN, GetPerc(img.getWidth(), 3)));
            int topDifference = GetPerc(img.getHeight(), 25);
            for (String features : newFeatures) {
                graph.setColor(new Color(103, 84, 255));
                graph.drawOval(
                        GetPerc(img.getWidth(), 7),
                        topDifference - (GetPerc(img.getWidth(), 3) / 2),
                        50,
                        50);

                graph.setColor(new Color(0, 0, 0));
                graph.drawString(features,
                        GetPerc(img.getWidth(), 10),
                        topDifference);
                topDifference += GetPerc(GetPerc(img.getWidth(), 3), 120);
            }
            // #endregion
            File outptFile=new File(imgTitle.replace(" ", "")+".png");
            ImageIO.write(img, "png", outptFile);
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
