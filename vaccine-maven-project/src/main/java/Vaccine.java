public class Vaccine {
    public static final VaccineType[] types = {VaccineType.FLU, VaccineType.DTAP_IPV_HIB, VaccineType.PNEU_C_20};
    private VaccineType type;
    private Date date;

    public Vaccine(VaccineType type, Date date){
        this.type = type;
        this.date = date;
    }

    public boolean equals(Vaccine other){
        return type == other.type;
    }

    public Date getDate(){
        return date;
    }

    public String toString(){
        String s = "";

        switch (type) {
            case FLU:
                s += "FLU ";
                break;
            case TDAP:
                s += "TDAP ";
                break;
        
            default:
                s += "other -> please correct error in vaccine toString";
                break;
        }

        s += date.getMonth() + "/" + date.getDay() + "/" + date.getYear();

        return s;
    }
}
