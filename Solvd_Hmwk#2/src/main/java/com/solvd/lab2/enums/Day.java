package computerrepairservice.enums;

import computerrepairservice.lambdas.Function;

public enum Day {
    MONDAY, 
    TUESDAY, 
    WEDNESDAY,
    THURSDAY, 
    FRIDAY, 
    SATURDAY,
    SUNDAY;
    
    public void halfOffDay(Day day, int price) {
        Function<Integer, Float> factor = null;
        
        //half days are Tuesday and Thursday
        if(day==TUESDAY || day==THURSDAY) {
            factor = p -> p/2.0f;
        }
        else { //any other day is full price
            factor = p -> p/1.0f;
        } 
        
        factor.apply(price);
    }
}