package application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Scholarship {
    private final StringProperty name;
    private final StringProperty amount;
    private final StringProperty sponsor;
    private final StringProperty email;
    private final StringProperty minGPA;
    private final StringProperty awardFreq;

    public Scholarship(String name, String amount, String sponsor, String email, String minGPA, String awardFreq) {
        this.name = new SimpleStringProperty(name);
        this.amount = new SimpleStringProperty(amount);
        this.sponsor = new SimpleStringProperty(sponsor);
        this.email = new SimpleStringProperty(email);
        this.minGPA = new SimpleStringProperty(minGPA);
        this.awardFreq = new SimpleStringProperty(awardFreq);
    }

    // Add getters for JavaFX properties
    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty amountProperty() {
        return amount;
    }

    public StringProperty sponsorProperty() {
        return sponsor;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty minGPAProperty() {
        return minGPA;
    }

    public StringProperty awardFreqProperty() {
        return awardFreq;
    }
    public String getName() {
        return name.get();
    }
    

    @Override
    public String toString() {
       
        return name.get() + "," + amount.get() + "," + sponsor.get() + "," + email.get() + "," + minGPA.get() + "," + awardFreq.get();
    }
}
