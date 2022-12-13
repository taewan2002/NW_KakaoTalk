package concave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventHandler implements ActionListener {
    private int x;
    private int y;

    private GameMain ad;
    public EventHandler(GameMain ad) {
        this.ad = ad;
    }
    @Override
    public void actionPerformed(ActionEvent arg0) {
        String button_name = arg0.getActionCommand();
        String[] modNum = button_name.split(" ");
        x = Integer.parseInt(modNum[0]);
        y = Integer.parseInt(modNum[1]);
        System.out.println(Integer.toString(x) + " " + Integer.toString(y));

        ad.temp_x = x;
        ad.temp_y = y;
    }

}
