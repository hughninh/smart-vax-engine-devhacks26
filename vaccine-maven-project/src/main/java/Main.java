import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main{
    public static void main(String[] args){
        JFrame window = new JFrame();
        window.setTitle("Vaccine Thing");
        window.setSize(1500, 1000);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel);
        mainPanel.add(new UserDisplayManager(mainPanel));
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}