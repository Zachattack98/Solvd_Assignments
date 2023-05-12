package computerrepairservice.enums;

public enum Time {
    FULLDAY,
    HALFDAY;
    
    public double getTime() {   
        switch (this) {
            case FULLDAY:
                return 1.0; //time for replacing any component; one full day
            case HALFDAY:
                return 0.5; //time for replacing any component; one full day
            default:
                return 0.0;
        }
    }
}