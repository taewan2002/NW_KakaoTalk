package function;

import javax.swing.*;
import java.awt.*;

public class ImgSetSize {

    private String file_name;
    private ImageIcon img3;
    private ImageIcon icon;
    private int i;
    private int j;
    public ImgSetSize(String file_name, int i, int j){
        this.icon = new ImageIcon(file_name);
        this.i = i;
        this.j = j;
    }
    public ImageIcon getImg(){
        Image img1 = icon.getImage();  //ImageIcon을 Image로 변환.
        Image img2 = img1.getScaledInstance(i, j, Image.SCALE_SMOOTH);
        ImageIcon img3 = new ImageIcon(img2);
        this.img3 = img3;

        return img3;
    }

}
