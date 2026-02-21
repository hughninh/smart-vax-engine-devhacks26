public class Date {
    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay(){
        return day;
    }
    public int getMonth(){
        return month;
    }
    public int getYear(){
        return year;
    }

    public boolean equals(Date other){
        return day == other.day && month == other.month && year == other.year;
    }

    public boolean after(Date other){
        boolean after = false;
        if(year > other.year){
            after = true;
        }else if(year == other.year){
            if(month > other.month){
                after = true;
            }else if(month == other.month){
                if(day > other.day){
                    after = true;
                }
            }
        }

        return after;
    }
}
