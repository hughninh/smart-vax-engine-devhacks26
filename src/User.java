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
    }

    public void recieveVaccine(VaccineType type, Date dateReceived){
        receivedVaccines.add(new Vaccine(type, dateReceived));
    }

}
