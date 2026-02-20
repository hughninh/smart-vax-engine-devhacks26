import java.awt.BorderLayout;
import java.awt.Color;
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
    private ArrayList<User> users;
    private JPanel usersPanel;
    private JPanel menuPanel;
    private JPanel parent;
    public UserDisplayManager(JPanel parent){
        this.parent = parent;
        users = new ArrayList<User>();
        this.setLayout(new BorderLayout());
        usersPanel = new JPanel(new GridBagLayout());
        usersPanel.setBackground(new Color(255, 255, 255));
        menuPanel = new JPanel(new BorderLayout());
        menuPanel.setBackground(new Color(0, 255, 0));
        this.add(usersPanel, BorderLayout.CENTER);
        this.add(menuPanel, BorderLayout.NORTH);


        JButton addUserButton = new JButton("Add New User");
        addUserButton.setFont(new Font("", Font.BOLD, 50));
        addUserButton.addActionListener(new addUserListener());
        menuPanel.add(addUserButton, BorderLayout.EAST);
        updateDisplay();
    }

    public void updateDisplay(){
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
            // iterate for each user and add the user to the panel
        }
    }

    private class addUserListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            parent.removeAll();
            parent.invalidate();
            parent.add(new UserInformation());
            parent.revalidate();
        }
    }
}
