import java.util.ArrayList;

public class User {
    private Date birthday;
    private String name;
    private ArrayList<Vaccine> receivedVaccines;
    private ArrayList<Vaccine> futureVaccines;
    private ArrayList<Vaccine> missedVaccines;
    private ArrayList<Vaccine> notEligableVaccines;

    private ArrayList<Vaccine> reactions;

    public User(Date birthday, String name){
        this.birthday = birthday;
        this.name = name;

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

    public void recieveVaccine(VaccineType type, Date dateReceived){
        receivedVaccines.add(new Vaccine(type, dateReceived));
    }

    public int determineDifferenceInMonths(Date currentDate){
        int monthsDifferent = (currentDate.getYear() - birthday.getYear()) * 12;
        int subtractMonths = currentDate.getMonth() - birthday.getMonth();
        monthsDifferent += subtractMonths;
        System.out.println("The Difference is : " + monthsDifferent);
        return monthsDifferent;
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

        // now see if it prints out the schedule
        for(Vaccine v : futureVaccines){
            System.out.println(v);
        }
    }


    public void isAgeGood(Vaccine vaccine){

    }


}
