import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JPanel;

public class User {
    private Date birthday;
    private String name;
    private ArrayList<Vaccine> receivedVaccines;
    private ArrayList<Vaccine> futureVaccines;
    private ArrayList<Vaccine> missedVaccines;
    private ArrayList<Vaccine> notEligableVaccines;
    private JPanel parent;

    private ArrayList<Vaccine> reactions;
    private int healthConditions = 0;

    public User(Date birthday, String name, JPanel parent){
        this.birthday = birthday;
        this.name = name;
        this.parent = parent;

        receivedVaccines = new ArrayList<Vaccine>();
        futureVaccines = new ArrayList<Vaccine>();
        missedVaccines = new ArrayList<Vaccine>();
        notEligableVaccines = new ArrayList<Vaccine>();
    }

    public ArrayList<Vaccine> getFutureVaccines(){
        return this.futureVaccines;
    }

    public ArrayList<Vaccine> getReceivedVaccines(){
        return this.receivedVaccines;
    }

    public ArrayList<Vaccine> getMissedVaccines(){
        return this.missedVaccines;
    }
    public ArrayList<Vaccine> getNotElligableVaccines(){
        return  this.notEligableVaccines;
    }

    public void recieveVaccine(VaccineType type, Date dateReceived){
        receivedVaccines.add(new Vaccine(type, dateReceived));
    }

    public int determineDifferenceInMonths(Date currentDate){
        int monthsDifferent = (currentDate.getYear() - birthday.getYear()) * 12;
        int subtractMonths = currentDate.getMonth() - birthday.getMonth();
        monthsDifferent += subtractMonths;
        // System.out.println("The Difference is : " + monthsDifferent);
        return monthsDifferent;
    }

    public int calculateDifferenceInDays(Date newerDate, Date dateOlderDate){
        int monthsDifferent = (newerDate.getYear() - dateOlderDate.getYear()) * 12;
        int subtractMonths = newerDate.getMonth() - dateOlderDate.getMonth();
        monthsDifferent += subtractMonths;
        int daysDifferent = monthsDifferent * 30;
        int subtractDays = newerDate.getDay() - dateOlderDate.getDay();
        daysDifferent += subtractDays;

        // System.out.println("The Difference is : " + monthsDifferent);
        return daysDifferent;
    }

    public void setHealthConditions(int i){
        healthConditions = i;
    }

    public void determineFutureVaccines(Date currentDate){
        Date nextDateFlu;
        System.out.println("Current Date: Get Month: " + currentDate.getMonth());
        if(currentDate.getMonth() >= 11 || determineDifferenceInMonths(new Date(31, 10, currentDate.getYear())) < 6    ){
            nextDateFlu = new Date(31, 10, currentDate.getYear() + 1);
        }else{
            nextDateFlu = new Date(31, 10, currentDate.getYear());
        }
        Vaccine flu = new Vaccine(VaccineType.FLU, nextDateFlu);
        futureVaccines.add(flu);
        // now do the logic for TDAP

        // check if the user has already gotten a tdap vaccine
        boolean alreadyHas = false;
        Vaccine oldTdap = new Vaccine(VaccineType.TDAP, new Date(0, 0, 0));
        for(Vaccine v : receivedVaccines){
            if(v.equals(oldTdap)){
                alreadyHas = true;
                oldTdap = v;
            }
        }
        System.out.println("Does the vaccine already exist" + alreadyHas);
        if(currentDate.getYear() > birthday.getYear() + 15 && !alreadyHas){
            // then you have missed your vaccine
            missedVaccines.add(new Vaccine(VaccineType.TDAP, new Date(birthday.getDay(), birthday.getMonth(),birthday.getYear() + 15)));
        }else if(alreadyHas){
            System.out.println("other logic");
            // if you have already had one, then you need another
            futureVaccines.add(new Vaccine(VaccineType.TDAP, new Date(oldTdap.getDate().getDay(),oldTdap.getDate().getMonth(), oldTdap.getDate().getYear() + 10)));
        }else{
            futureVaccines.add(new Vaccine(VaccineType.TDAP, new Date(birthday.getDay(),birthday.getMonth(), birthday.getYear() + 14)));
        }

        // add to next November 1
        // add to future vaccines


        boolean hasPNEU_C_20 = false;
        Vaccine oldPNEU_C_20 = new Vaccine(VaccineType.PNEU_C_20, new Date(0, 0, 0));
        for(Vaccine v : receivedVaccines){
            if(v.equals(oldTdap)){
                hasPNEU_C_20 = true;
                oldPNEU_C_20 = v;
            }
        }
        if(currentDate.getYear() > birthday.getYear() + 65 && !hasPNEU_C_20){
            // then you have missed it
            missedVaccines.add(new Vaccine(VaccineType.PNEU_C_20, new Date(birthday.getDay(), birthday.getMonth(),birthday.getYear() + 65)));
        }else if(healthConditions == 1 || healthConditions == 2){
            // then you are not eligable
            notEligableVaccines.add(new Vaccine(VaccineType.PNEU_C_20, new Date(birthday.getDay(), birthday.getMonth(),birthday.getYear() + 65)));
        }else{
            // then you still need it
            futureVaccines.add(new Vaccine(VaccineType.PNEU_C_20, new Date(birthday.getDay(), birthday.getMonth(),birthday.getYear() + 65)));
        }
        // now do Pneu-C-20


        // now see if it prints out the schedule
        for(Vaccine v : futureVaccines){
            System.out.println(v);
        }

        // determine if there are any upcoming days in the next month
        String content = "";
        for(Vaccine v : futureVaccines){
            System.out.println("Difference in Days: " + v + "    " + calculateDifferenceInDays(v.getDate(), currentDate));
           if (calculateDifferenceInDays(v.getDate(), currentDate) < 30 && calculateDifferenceInDays(v.getDate(), currentDate) >= 0){
            content += v + "    \n";
           }
        }
        if (!content.equals("")){
            // send a notification to the user
            SystemTray t = SystemTray.getSystemTray();
            Image im = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
            TrayIcon icon = new TrayIcon(im, "");

            try{t.add(icon);
            }catch(Exception e) {System.err.println("unable to send notification for some reason");}

            icon.displayMessage("Upcoming Vaccines: ", content, TrayIcon.MessageType.WARNING);
        }
        
        try{
            saveUser();
        }catch(FileNotFoundException fnfe){
            System.err.println("Unable to save the file");
        }

    }

    public void saveUser() throws FileNotFoundException{
        // save each of the vaccine history
        // we can recalculate the future vaccines
        // we can determine the missed vaccines
        // we have to save the health conditions
        // we have the name
        // we have to save the birthday
        PrintWriter p = new PrintWriter(new File("savedUser.csv"));
        p.write(name + "\n");
        p.write(birthday.toString() + "\n");
        p.write(healthConditions + "\n");
        for (Vaccine v : receivedVaccines){
            p.write(v.toString() + ",");
        }

        p.close();

    }


    public void isAgeGood(Vaccine vaccine){

    }


}
