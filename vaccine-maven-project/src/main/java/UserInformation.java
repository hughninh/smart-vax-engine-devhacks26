import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserInformation extends JPanel{
       public UserInformation(){
            this.setLayout(new GridBagLayout());

            JLabel title = new JLabel("Please Enter User Information");
            Font font = new Font("", Font.BOLD, 50);
            title.setFont(font);
            JTextField name = new JTextField("Please Enter Your Full Name");
            
            JComboBox<String> conditions = new JComboBox<String>();
            
            String[] days = new String[365];
            for(int i = 1; i <= 365; i++){
               days[i-1] = Integer.toString(i);
            }
            JComboBox<String> dayPicker = new JComboBox<String>(days);
            
            String[] months = new String [12];
            for(int i = 1; i <= 12; i++) {
               months[i-1] = Integer.toString(i);
            }
            JComboBox<String> monthPicker = new JComboBox<String>(months);

            String[] years = new String [127];
            for(int i = 1900; i <= 2026; i++){
               years[i - 1900] = Integer.toString(i);
            }
            JComboBox<String> yearPicker = new JComboBox<String>(years);
            
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
