import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class UserTimeLine extends JPanel{
    User user;

    public UserTimeLine(User user){
        this.user = user;
    }

    public void paintComponent(Graphics g){
        g.setColor(new Color(0, 255, 0));
        g.drawRect(0, 0, this.getWidth(), this.getHeight());
    }
}