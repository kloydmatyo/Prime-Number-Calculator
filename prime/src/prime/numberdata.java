package prime;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class numberdata {

    private final StringProperty ID;
    private final StringProperty NUMBER;
    private final StringProperty PRIME_NUMBER;

    public numberdata() {
        this.ID = new SimpleStringProperty(this, "id");
        this.NUMBER = new SimpleStringProperty(this, "NUMBER");
        this.PRIME_NUMBER = new SimpleStringProperty(this, "PRIME_NUMBER");
    }

    public StringProperty IDProperty() {
        return ID;
    }

    public String getID() {
        return ID.get();
    }

    public void setID(String newID) {
        ID.set(newID);
    }

    public StringProperty NUMBERProperty() {
        return NUMBER;
    }

    public String getNUMBER() {
        return NUMBER.get();
    }

    public void setNUMBER(String newNUMBER) {
        NUMBER.set(newNUMBER);
    }

    public StringProperty PRIME_NUMBERProperty() {
        return PRIME_NUMBER;
    }

    public String getPRIME_NUMBER() {
        return PRIME_NUMBER.get();
    }

    public void setPRIME_NUMBER(String newPRIME_NUMBER) {
        PRIME_NUMBER.set(newPRIME_NUMBER);
    }
}