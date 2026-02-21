import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserDisplayManager extends JPanel{
    private static ArrayList<User> users = new ArrayList<User>();
    private JPanel usersPanel;
    private JPanel menuPanel;
    private JPanel parent;
    public UserDisplayManager(JPanel parent){
        this.parent = parent;
        this.setLayout(new BorderLayout());
        usersPanel = new JPanel(new GridBagLayout());
        menuPanel = new JPanel(new BorderLayout());
        this.add(usersPanel, BorderLayout.CENTER);
        this.add(menuPanel, BorderLayout.NORTH);


        JButton addUserButton = new JButton("Add New User");
        addUserButton.setFont(new Font("", Font.BOLD, 50));
        addUserButton.addActionListener(new addUserListener());
        menuPanel.add(addUserButton, BorderLayout.EAST);
        updateDisplay();
    }

    public static void addUser(User user){
        users.add(user);
    }

    public void updateDisplay(){
        Font heading = new Font("", Font.BOLD, 40);
        Font subtitles = new Font("", Font.BOLD, 35);

        GridBagConstraints c = new GridBagConstraints();
        if(users.size() == 0){
            // if there are no users
            Font f = new Font("", Font.BOLD, 40);
            JLabel noUsers = new JLabel("No Users Added");
            noUsers.setFont(f);
            c.gridx = 0;
            c.gridy = 0;
            usersPanel.add(noUsers, c);
        }else{
            // iterate for each user and add the user to the panel=
            JLabel futureLabel = new JLabel("Future Vaccines: ");
            futureLabel.setFont(heading);
            c.gridy = 1;
            usersPanel.add(futureLabel, c);

            for(Vaccine v : users.get(0).getFutureVaccines()){
                // add each future vaccine to the display
                c.gridy++;
                JLabel vaccineLabel = new JLabel(v.toString());
                vaccineLabel.setFont(subtitles);
                usersPanel.add(vaccineLabel, c);
            }


            
            // iterate for each user and add the user to the panel=
            JLabel pastVaccine = new JLabel("Vaccine History: ");
            pastVaccine.setFont(heading);
            c.gridy++;
            usersPanel.add(pastVaccine, c);

            for(Vaccine v : users.get(0).getReceivedVaccines()){
                // add each future vaccine to the display
                c.gridy++;
                JLabel vaccineLabel = new JLabel(v.toString());
                vaccineLabel.setFont(subtitles);
                usersPanel.add(vaccineLabel, c);
            }




            JLabel missedVaccine = new JLabel("Missed Vaccine: ");
            missedVaccine.setFont(heading);
            c.gridy++;
            usersPanel.add(missedVaccine, c);

            for(Vaccine v : users.get(0).getMissedVaccines()){
                // add each future vaccine to the display
                c.gridy++;
                JLabel vaccineLabel = new JLabel(v.toString());
                vaccineLabel.setFont(subtitles);
                usersPanel.add(vaccineLabel, c);
            }



            JLabel notEligable = new JLabel("Not Elligable Vaccines: ");
            notEligable.setFont(heading);
            c.gridy++;
            usersPanel.add(notEligable, c);

            for(Vaccine v : users.get(0).getNotElligableVaccines()){
                // add each future vaccine to the display
                c.gridy++;
                JLabel vaccineLabel = new JLabel(v.toString());
                vaccineLabel.setFont(subtitles);
                usersPanel.add(vaccineLabel, c);
            }
        }
    }

    private class addUserListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            parent.removeAll();
            parent.invalidate();
            parent.add(new UserInformation(parent));
            parent.revalidate();
        }
    }

    private class createListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            
        }
    }
}
