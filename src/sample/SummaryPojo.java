package sample;

public class SummaryPojo {
    String fullName;
    String qualificationSummary;
    String professionSummary;
    String expectedSalary;
    String datePublicSummary;

    public SummaryPojo(String fullName, String qualificationSummary, String professionSummary, String expectedSalary, String datePublicSummary) {
        this.fullName = fullName;
        this.qualificationSummary = qualificationSummary;
        this.professionSummary = professionSummary;
        this.expectedSalary = expectedSalary;
        this.datePublicSummary = datePublicSummary;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getQualificationSummary() {
        return qualificationSummary;
    }

    public void setQualificationSummary(String qualificationSummary) {
        this.qualificationSummary = qualificationSummary;
    }

    public String getProfessionSummary() {
        return professionSummary;
    }

    public void setProfessionSummary(String professionSummary) {
        this.professionSummary = professionSummary;
    }

    public String getExpectedSalary() {
        return expectedSalary;
    }

    public void setExpectedSalary(String expectedSalary) {
        this.expectedSalary = expectedSalary;
    }

    public String getDatePublicSummary() {
        return datePublicSummary;
    }

    public void setDatePublicSummary(String datePublicSummary) {
        this.datePublicSummary = datePublicSummary;
    }
}
