package sample;

public class JobPojo {
    private String company;
    private String position;
    private String salary;
    private String qualification;

    public JobPojo(String company, String position, String salary, String qualification) {
        this.company = company;
        this.position = position;
        this.salary = salary;
        this.qualification = qualification;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
}
