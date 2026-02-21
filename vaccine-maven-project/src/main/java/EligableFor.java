import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;


public class EligableFor{
    @Condition
    public boolean isEligable(@Fact("number")int currentAge, @Fact("number") int ageRequirement){
        return currentAge > ageRequirement; // each person is always elligable for all vaccines
    }
}