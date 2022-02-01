package AfkoAPI.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Comparator;


public class BlacklistEntry {
    private String entry;

    protected BlacklistEntry() {}
    public BlacklistEntry(String entry) {
        this.entry = entry;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }
}
