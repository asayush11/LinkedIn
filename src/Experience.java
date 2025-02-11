package src;

import java.util.Date;

public class Experience {
    private final String org;
    private final Date startDate;
    private Date endDate;

    public Experience(String org, Date startDate) {
        this.org = org;
        this.startDate = startDate;
        this.endDate = null;
    }

    public String getOrg() {
        return org;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
