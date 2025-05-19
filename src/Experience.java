package src;

import java.time.LocalDate;

public class Experience {
    private final String org;
    private final LocalDate startDate;
    private LocalDate endDate;

    public Experience(String org, LocalDate startDate) {
        this.org = org;
        this.startDate = startDate;
        this.endDate = null;
    }

    public String getOrg() {
        return org;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
