import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserInformation extends JPanel{
   private static final Date CURRENT_DATE = new Date(20, 2, 2026);
   private JTextField bithInputField;
   private JTextField nameInput;
   private JComboBox<String> historyBox1;
   private JTextField whenField1;
   private JPanel parent;
       public UserInformation(JPanel parent){
         this.parent = parent;
            this.setLayout(new GridBagLayout());

            // JLabel title = new JLabel("Please Enter User Information: ");
            Font font = new Font("", Font.BOLD, 40);
            // title.setFont(font);
            JLabel nameLabel = new JLabel("Please Enter Your Full Name: ");
            nameLabel.setFont(font);
            nameInput = new JTextField("Example Name");

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = 0;
            // this.add(title, constraints);
            constraints.gridx = 0;
            constraints.gridy = 0;
            this.add(nameLabel, constraints);
            constraints.gridx = 1;
            this.add(nameInput, constraints);

            JLabel dateOfBirth = new JLabel("Please Enter Your Date of Birth (mm/dd/yyyy): ");
            dateOfBirth.setFont(font);
            constraints.gridx = 0;
            constraints.gridy = 2;
            this.add(dateOfBirth, constraints);
            constraints.gridx = 1;
            bithInputField = new JTextField("00/00/0000");
            this.add(bithInputField, constraints);


            JLabel healthCondition = new JLabel("Please Enter Your Health Conditions: ");
            healthCondition.setFont(font);
            String[] conditionTypes = new String[3];
            conditionTypes[0] = "none";
            conditionTypes[1] = "diabetes";
            conditionTypes[2] = "obesity";

            constraints.gridx = 0;
            constraints.gridy++;
            this.add(healthCondition, constraints);

            constraints.gridx++;
            JComboBox<String> conditions = new JComboBox<String>(conditionTypes);
            this.add(conditions, constraints);
            

            JLabel vaccinationHistory = new JLabel("Please Enter Your Vaccination History (Leave blank if unvaxxed): ");
            vaccinationHistory.setFont(font);
            String[] vaccinationTypes = new String[6];
            vaccinationTypes[0] = "none";
            vaccinationTypes[1] = "Tdap";
            vaccinationTypes[2] = "Flu";
            vaccinationTypes[3] = "Pneu-C-20";

            constraints.gridx = 0;
            constraints.gridy++;
            historyBox1 = new JComboBox<String>(vaccinationTypes);
            whenField1 = new JTextField("00/00/0000");
            this.add(historyBox1,constraints);
            constraints.gridx = 1;
            this.add(whenField1, constraints);
            constraints.gridx = 0;
            constraints.gridy++;
            JComboBox<String> historyBox2 = new JComboBox<String>(vaccinationTypes);
            JTextField whenField2 = new JTextField("00/00/0000");
            this.add(historyBox2,constraints);
            constraints.gridx = 1;
            this.add(whenField2, constraints);
            constraints.gridx = 0;
            constraints.gridy++;
            JComboBox<String> historyBox3 = new JComboBox<String>(vaccinationTypes);
            JTextField whenField3 = new JTextField("00/00/0000");
            this.add(historyBox3,constraints);
            constraints.gridx = 1;
            this.add(whenField3, constraints);
            constraints.gridx = 0;
            constraints.gridy++;

            JButton create = new JButton("Create User");
            create.addActionListener(new createListener());
            constraints.gridy = 10;
            this.add(create, constraints);
       }

       private class createListener implements ActionListener{
         public void actionPerformed(ActionEvent e){
            // create a new user with all the needed information
            // get the birthday
            String birthdayInput = bithInputField.getText();
            // split the string
            String[] tokens = birthdayInput.split("/");
            int month = Integer.parseInt(tokens[0]);
            int day = Integer.parseInt(tokens[1]);
            int year = Integer.parseInt(tokens[2]);
            Date userBirthday = new Date(day, month, year);
            User newUser = new User(userBirthday, nameInput.getText());

            UserDisplayManager.addUser(newUser);

            if(historyBox1.getSelectedIndex() == 1){
               // then they have selected tDap
               String[] tokens1 = whenField1.getText().split("/");
               System.out.println("WhenField1: " + whenField1.getText());
               int month1 = Integer.parseInt(tokens1[0]);
               int day1 = Integer.parseInt(tokens1[1]);
               int year1 = Integer.parseInt(tokens1[2]);
               Date tDapDay = new Date(day1, month1, year1);
               newUser.recieveVaccine(VaccineType.TDAP, tDapDay);
            }

            newUser.determineFutureVaccines(CURRENT_DATE);

            parent.removeAll();
            parent.add(new UserDisplayManager(parent));
            parent.revalidate();
         }
       }
}
