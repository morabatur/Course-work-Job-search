package sample;

public class VacancyPojo {
    String name;
    String kindOfActivity;
    String possition;
    String qualification;
    String profession;
    String salary;
    String dateOfPublic;

    @Override
    public String toString() {
        return "VacancyPojo{" +
                "name='" + name + '\'' +
                ", kindOfActivity='" + kindOfActivity + '\'' +
                ", possition='" + possition + '\'' +
                ", qualification='" + qualification + '\'' +
                ", profession='" + profession + '\'' +
                ", salary='" + salary + '\'' +
                ", dateOfPublic='" + dateOfPublic + '\'' +
                '}';
    }

    public VacancyPojo(String name, String kindOfActivity, String possition, String qualification, String profession, String salary, String dateOfPublic) {
        this.name = name;
        this.kindOfActivity = kindOfActivity;
        this.possition = possition;
        this.qualification = qualification;
        this.profession = profession;
        this.salary = salary;
        this.dateOfPublic = dateOfPublic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKindOfActivity() {
        return kindOfActivity;
    }

    public void setKindOfActivity(String kindOfActivity) {
        this.kindOfActivity = kindOfActivity;
    }

    public String getPossition() {
        return possition;
    }

    public void setPossition(String possition) {
        this.possition = possition;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getDateOfPublic() {
        return dateOfPublic;
    }

    public void setDateOfPublic(String dateOfPublic) {
        this.dateOfPublic = dateOfPublic;
    }
}
