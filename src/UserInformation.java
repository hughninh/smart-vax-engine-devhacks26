import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserInformation extends JPanel{
       public UserInformation(){
            this.setLayout(new GridBagLayout());

            JLabel title = new JLabel("Please Enter User Information");
            Font font = new Font("", Font.BOLD, 50);
            title.setFont(font);
            JTextField name = new JTextField("Please Enter Your Name");

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = 0;
            this.add(title, constraints);
            constraints.gridy = 1;
            this.add(name, constraints);

            JButton create = new JButton("Create User");
            constraints.gridy = 2;
            this.add(create, constraints);
       }
}
