package re.ethernity.todolist.Entity;

import jakarta.persistence.Entity;

@Entity
public class RecurrentTask extends Task {
    public String getFrequency() {
        return Frequency;
    }

    public void setFrequency(String frequency) {
        Frequency = frequency;
    }

    private String Frequency;

    public RecurrentTask() {}
}