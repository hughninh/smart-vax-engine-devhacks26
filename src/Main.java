import javax.swing.JFrame;

public class Main{
    public static void main(String[] args){
        JFrame window = new JFrame();
        window.setTitle("Vaccine Thing");
        window.setSize(500, 500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(new UserInformation());
        window.setVisible(true);
    }
}