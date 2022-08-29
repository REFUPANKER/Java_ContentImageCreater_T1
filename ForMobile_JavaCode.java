import android.graphics.*;
import android.graphics.Bitmap.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.os.Environment;

public class App {
    public static void main(String[] args) throws Exception 
    {
        System.out.println("App Running...");
        CreateImage(
                new Color(129, 123, 173),
                new Color(45, 45, 45),
                "Timilyze",
                "Startup Update",
                new String[] {
                        "Created Database connections",
                        "Recreated Register System",
                        "Next Step is recreating Login System",
                        "Setting up Reminders",
                        "[About Other Apps]",
                        "> Idle Game : Still Developing ...",
                        "Setted up next steps and plan new features",
            });
    }
  
  public static void CreateImage(Color TopLeftColor, Color BottomRightColor, String imgTitle, String UpdateTXT,String[] newFeatures) 
  {
    try{
    Bitmap img=Bitmap.createBitmap(3000,3000,Bitmap.Config.RGB_565);
    Canvas graph=new Canvas(img);
    Paint p1=new Paint();
// #region Background Triangles
// Gray Bottom Right - default : (45, 45, 45)
    p1.setColor(BottomRightColor);
    for (int i = 0; i < img.getWidth(); i++) {
      graph.drawLine(img.getWidth() - i, i, img.getWidth(), i);
    }
// Light Purple Top Left - default : (129, 123, 173)
    p1.setColor(TopLeftColor);
    for (int i = 0; i < img.getWidth(); i++) {
      graph.drawLine(0, i, img.getWidth() - i, i);
    }
// #endregion
// #region Add Project Title
  p1.setColor(new Color(255, 255, 255));
  p1.setFont(new Font("Arial", Font.PLAIN, GetPerc(img.getWidth(), 8)));
  graph.drawString(imgTitle,(img.getWidth() / 2) - (GetStringWidth(graph, imgTitle) / 2),
  GetPerc(img.getHeight(), 9));
// #endregion
// #region MIDDLE BOX
// enough transparency : 220
    p1.setColor(new Color(200, 200, 225, 255));
    graph.fillRoundRect(
        GetPerc(img.getWidth(), 3),
        GetPerc(img.getHeight(), 12),
        img.getWidth() - (GetPerc(img.getWidth(), 3) * 2),
        img.getHeight() - (GetPerc(img.getHeight(), 10) * 2),
        90, 90);

// Update TXT
    p1.setColor(new Color(0, 0, 0));
    p1.setFont(new Font("Consolas", Font.PLAIN, GetPerc(img.getWidth(), 2)));
    graph.drawString(UpdateTXT,
        GetPerc(img.getWidth(), 1) + GetStringWidth(graph, UpdateTXT) / 2,
        img.getHeight() - GetPerc(img.getHeight(), 6) - GetStringWidth(graph, UpdateTXT) / 2);
// #endregion
// #region New Features Title
    p1.setColor(new Color(0, 0, 0));
    p1.setFont(new Font("Consolas", Font.PLAIN, GetPerc(img.getWidth(), 5)));
    graph.drawString("New Features",
        (img.getWidth() / 2) - (GetStringWidth(graph, "New Features") / 2),
        GetPerc(img.getHeight(), 18));
// under-line new features title
    p1.setColor(new Color(0, 0, 0));
    p1.setStroke(new BasicStroke(10));
    graph.drawLine(
        GetPerc(img.getWidth(), 8),
        GetPerc(img.getHeight(), 20),
        img.getWidth() - GetPerc(img.getWidth(), 8),
        GetPerc(img.getHeight(), 20));
// #endregion
// #region Features Lister
    p1.setFont(new Font("Lucida Console", Font.PLAIN, GetPerc(img.getWidth(), 3)));
    int topDifference = GetPerc(img.getHeight(), 25);
    for (String features : newFeatures) {
        p1.setColor(new Color(103, 84, 255));
        
        graph.drawOval(
            GetPerc(img.getWidth(), 7),
            topDifference - (GetPerc(img.getWidth(), 3) / 2),50,50);
        
        p1.setColor(new Color(0, 0, 0));
        graph.drawString(features,
            GetPerc(img.getWidth(), 10),
            topDifference);
        topDifference += GetPerc(GetPerc(img.getWidth(), 3), 120);
    }
// #endregion  
        // Saving File System
        File outptFile=new File(imgTitle.replace(" ", "")+".png");
            ImageIO.write(img, "png", outptFile);
            System.out.println("Image Saved");
        // =========
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
