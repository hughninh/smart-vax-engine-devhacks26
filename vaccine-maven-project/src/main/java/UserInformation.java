import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserInformation extends JPanel{
   private static final Date CURRENT_DATE = new Date(20, 2, 2026);
   private JTextField birthInputField;
   private JTextField nameInput;
   private JComboBox<String> historyBox1;
   private JTextField whenField1;
   private JComboBox<String> historyBox2;
   private JTextField whenField2;
   private JComboBox<String> historyBox3;
   private JTextField whenField3;
   private JComboBox<String> conditions;
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
            nameInput.setFont(font);

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
            birthInputField = new JTextField("00/00/0000");
            birthInputField.setFont(font);
            this.add(birthInputField, constraints);


            JLabel healthCondition = new JLabel("Please Enter Your Health Conditions: ");
            healthCondition.setFont(font);
            String[] conditionTypes = new String[3];
            conditionTypes[0] = "none";
            conditionTypes[1] = "allergic to polysorbate 80";
            conditionTypes[2] = "allergic to aluminum phosphate";

            constraints.gridx = 0;
            constraints.gridy++;
            this.add(healthCondition, constraints);

            constraints.gridx++;
            conditions = new JComboBox<String>(conditionTypes);
            conditions.setFont(font);
            this.add(conditions, constraints);
            

            JLabel vaccinationHistory = new JLabel("Please Enter Your Vaccination History (Leave blank if unvaxxed): ");
            vaccinationHistory.setFont(font);
            String[] vaccinationTypes = new String[4];
            vaccinationTypes[0] = "none";
            vaccinationTypes[1] = "Tdap";
            vaccinationTypes[2] = "Flu";
            vaccinationTypes[3] = "Pneu-C-20";

            constraints.gridx = 0;
            constraints.gridy++;
            historyBox1 = new JComboBox<String>(vaccinationTypes);
            historyBox1.setFont(font);
            whenField1 = new JTextField("00/00/0000");
            whenField1.setFont(font);
            this.add(historyBox1,constraints);
            constraints.gridx = 1;
            this.add(whenField1, constraints);
            constraints.gridx = 0;
            constraints.gridy++;
            historyBox2 = new JComboBox<String>(vaccinationTypes);
            whenField2 = new JTextField("00/00/0000");
            historyBox2.setFont(font);
            whenField2.setFont(font);
            this.add(historyBox2,constraints);
            constraints.gridx = 1;
            this.add(whenField2, constraints);
            constraints.gridx = 0;
            constraints.gridy++;
            historyBox3 = new JComboBox<String>(vaccinationTypes);
            whenField3 = new JTextField("00/00/0000");
            historyBox3.setFont(font);
            whenField3.setFont(font);
            this.add(historyBox3,constraints);
            constraints.gridx = 1;
            this.add(whenField3, constraints);
            constraints.gridx = 0;
            constraints.gridy++;

            JButton create = new JButton("Create User");
            create.setFont(font);
            create.addActionListener(new createListener());
            constraints.gridy = 10;
            JButton load = new JButton("Load User From a File");
            load.addActionListener(new loadListener());
            load.setFont(font);

            this.add(create, constraints);
            constraints.gridy = 11;
            this.add(load, constraints);
       }

       private class loadListener implements ActionListener{
         public void actionPerformed(ActionEvent e){
            try{
               Scanner fileReader = new Scanner(new File("savedUser.csv"));
               String nameString = fileReader.nextLine();
               String birthBirthdayString = fileReader.nextLine();
               String healthConditionString = fileReader.nextLine();
               String healthHistoryString = fileReader.nextLine();
               fileReader.close();
               
               // Set the UI elements based on the file 
               nameInput.setText(nameString);
               birthInputField.setText(birthBirthdayString);
               conditions.setSelectedIndex(Integer.parseInt(healthConditionString));

               // Set the vaccination history
               
               // split by comma
               String[] tokens = healthHistoryString.split(",");
               if(tokens.length >= 1){
                  String[] data = tokens[0].split(" ");
                  System.out.println("data[0]: " + data[0]);
                  whenField1.setText(data[1]);
                  int indexOfThing = 0;
                  switch (data[0]) {
                     case "FLU":
                        indexOfThing = 2;
                        break;
                     case "TDAP":
                        indexOfThing = 1;
                        break;
                     case "PNEU_C_20":
                        indexOfThing = 3;
                        break;
                  
                     default:
                        break;
                  }
                  historyBox1.setSelectedIndex(indexOfThing);
               }

               if(tokens.length >= 2){
                  String[] data = tokens[1].split(" ");
                  whenField2.setText(data[1]);
                  int indexOfThing = 0;
                  switch (data[0]) {
                     case "FLU":
                        indexOfThing = 2;
                        break;
                     case "TDAP":
                        indexOfThing = 1;
                        break;
                     case "PNEU_C_20":
                        indexOfThing = 3;
                        break;
                  
                     default:
                        break;
                  }
                  historyBox2.setSelectedIndex(indexOfThing);
               }

               if(tokens.length >= 3){
                  String[] data = tokens[2].split(" ");
                  whenField3.setText(data[1]);
                  int indexOfThing = 0;
                  switch (data[0]) {
                     case "FLU":
                        indexOfThing = 2;
                        break;
                     case "TDAP":
                        indexOfThing = 1;
                        break;
                     case "PNEU_C_20":
                        indexOfThing = 3;
                        break;
                  
                     default:
                        break;
                  }
                  historyBox3.setSelectedIndex(indexOfThing);
               }


            }catch(FileNotFoundException fnfe){
               System.err.println("Error Reading the File");
            }
         }
       }

       private class createListener implements ActionListener{
         public void actionPerformed(ActionEvent e){
            // create a new user with all the needed information
            // get the birthday
            String birthdayInput = birthInputField.getText();
            // split the string
            String[] tokens = birthdayInput.split("/");
            int month = Integer.parseInt(tokens[0]);
            int day = Integer.parseInt(tokens[1]);
            int year = Integer.parseInt(tokens[2]);
            Date userBirthday = new Date(day, month, year);
            User newUser = new User(userBirthday, nameInput.getText(), parent);

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
            if(historyBox2.getSelectedIndex() == 1){
               // then they have selected tDap
               String[] tokens1 = whenField2.getText().split("/");
               System.out.println("WhenField1: " + whenField2.getText());
               int month1 = Integer.parseInt(tokens1[0]);
               int day1 = Integer.parseInt(tokens1[1]);
               int year1 = Integer.parseInt(tokens1[2]);
               Date tDapDay = new Date(day1, month1, year1);
               newUser.recieveVaccine(VaccineType.TDAP, tDapDay);
            }
            if(historyBox3.getSelectedIndex() == 1){
               // then they have selected tDap
               String[] tokens1 = whenField3.getText().split("/");
               System.out.println("WhenField1: " + whenField3.getText());
               int month1 = Integer.parseInt(tokens1[0]);
               int day1 = Integer.parseInt(tokens1[1]);
               int year1 = Integer.parseInt(tokens1[2]);
               Date tDapDay = new Date(day1, month1, year1);
               newUser.recieveVaccine(VaccineType.TDAP, tDapDay);
            }



            if(historyBox1.getSelectedIndex() == 2){
               // then they have selected tDap
               String[] tokens1 = whenField1.getText().split("/");
               System.out.println("WhenField1: " + whenField1.getText());
               int month1 = Integer.parseInt(tokens1[0]);
               int day1 = Integer.parseInt(tokens1[1]);
               int year1 = Integer.parseInt(tokens1[2]);
               Date dayThing = new Date(day1, month1, year1);
               newUser.recieveVaccine(VaccineType.FLU, dayThing);
            }
            if(historyBox2.getSelectedIndex() == 2){
               // then they have selected tDap
               String[] tokens1 = whenField2.getText().split("/");
               System.out.println("WhenField1: " + whenField2.getText());
               int month1 = Integer.parseInt(tokens1[0]);
               int day1 = Integer.parseInt(tokens1[1]);
               int year1 = Integer.parseInt(tokens1[2]);
               Date dayThing = new Date(day1, month1, year1);
               newUser.recieveVaccine(VaccineType.FLU, dayThing);
            }
            if(historyBox3.getSelectedIndex() == 2){
               // then they have selected tDap
               String[] tokens1 = whenField3.getText().split("/");
               System.out.println("WhenField1: " + whenField3.getText());
               int month1 = Integer.parseInt(tokens1[0]);
               int day1 = Integer.parseInt(tokens1[1]);
               int year1 = Integer.parseInt(tokens1[2]);
               Date dayThing = new Date(day1, month1, year1);
               newUser.recieveVaccine(VaccineType.FLU, dayThing);
            }




            if(historyBox1.getSelectedIndex() == 3){
               // then they have selected tDap
               String[] tokens1 = whenField1.getText().split("/");
               System.out.println("WhenField1: " + whenField1.getText());
               int month1 = Integer.parseInt(tokens1[0]);
               int day1 = Integer.parseInt(tokens1[1]);
               int year1 = Integer.parseInt(tokens1[2]);
               Date dayThing = new Date(day1, month1, year1);
               newUser.recieveVaccine(VaccineType.PNEU_C_20, dayThing);
            }
            if(historyBox2.getSelectedIndex() == 3){
               // then they have selected tDap
               String[] tokens1 = whenField2.getText().split("/");
               System.out.println("WhenField1: " + whenField2.getText());
               int month1 = Integer.parseInt(tokens1[0]);
               int day1 = Integer.parseInt(tokens1[1]);
               int year1 = Integer.parseInt(tokens1[2]);
               Date dayThing = new Date(day1, month1, year1);
               newUser.recieveVaccine(VaccineType.PNEU_C_20, dayThing);
            }
            if(historyBox3.getSelectedIndex() == 3){
               // then they have selected tDap
               String[] tokens1 = whenField3.getText().split("/");
               System.out.println("WhenField1: " + whenField3.getText());
               int month1 = Integer.parseInt(tokens1[0]);
               int day1 = Integer.parseInt(tokens1[1]);
               int year1 = Integer.parseInt(tokens1[2]);
               Date dayThing = new Date(day1, month1, year1);
               newUser.recieveVaccine(VaccineType.PNEU_C_20, dayThing);
            }

            newUser.setHealthConditions(conditions.getSelectedIndex());
            
            newUser.determineFutureVaccines(CURRENT_DATE);

            parent.removeAll();
            parent.add(new UserDisplayManager(parent));
            parent.revalidate();
         }
       }
}
