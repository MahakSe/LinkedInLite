package Entity;

public class Interest {
    private InterestType interestType;
    private Object interest;

    public Interest(InterestType interestType, Object interest) {
        this.interestType = interestType;
        this.interest = interest;
    }

    public InterestType getInterestType() {
        return interestType;
    }

    public void setInterestType(InterestType interestType) {
        this.interestType = interestType;
    }

    public Object getInterest() {
        return interest;
    }

    public void setInterest(Object interest) {
        this.interest = interest;
    }
}
