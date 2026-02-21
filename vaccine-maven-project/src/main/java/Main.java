import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class Main{
    public static void main(String[] args){

        try {
        UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
    } 
    catch (Exception e) {}
        JFrame window = new JFrame();
        window.setTitle("Vaccine Manager");
        window.setSize(1500, 1000);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel);
        mainPanel.add(new UserDisplayManager(mainPanel));
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}