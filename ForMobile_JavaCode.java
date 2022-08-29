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
      
      
      
      
      
    } catch (Exception e) {
      System.out.println(e);
    }
  }
  
  
  
  public static int GetPerc(int n1, int perc) {
        System.out.println("Percent :" + n1 + "x" + perc + "/100 =" + (n1 * perc) / 100);
        return (n1 * perc) / 100;
    }
  
}
