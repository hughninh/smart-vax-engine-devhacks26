public class Vaccine {
    private VaccineType type;
    private Date date;

    public Vaccine(VaccineType type, Date date){
        this.type = type;
        this.date = date;
    }

    public boolean equals(Vaccine other){
        return type == other.type && date.equals(other.date);
    }
}
