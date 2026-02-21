import org.jeasy.rules.api.Facts;

public class RulesTest {
    public static void main(String[] args){
        Facts f = new Facts(); // define some facts
        Vaccine v = new Vaccine(VaccineType.FLU, new Date(0, 0, 0));
        f.put("vaccine", v); // we have added this vaccine to the rules

        // now we can use the rules stuff
        // Rule ageRule = new MVELRule()
        // .name("age rule")
        // .description("Check if person's age is > 18 and marks the person as adult")
        // .priority(1)
        // .when("person.age > 18")
        // .then("person.setAdult(true);");
    }
}
