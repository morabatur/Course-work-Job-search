package sample;

public class ContractPojo {
    String id_contracts;
    String date_contracts;
    String name_Employers;
    String name_candidates;
    String position_name;
    String feddback;
    String agent;

    public ContractPojo(String id_contracts, String date_contracts, String name_Employers, String name_candidates, String position_name, String feddback, String agent) {
        this.id_contracts = id_contracts;
        this.date_contracts = date_contracts;
        this.name_Employers = name_Employers;
        this.name_candidates = name_candidates;
        this.position_name = position_name;
        this.feddback = feddback;
        this.agent = agent;
    }

    public String getId_contracts() {
        return id_contracts;
    }

    public void setId_contracts(String id_contracts) {
        this.id_contracts = id_contracts;
    }

    public String getDate_contracts() {
        return date_contracts;
    }

    public void setDate_contracts(String date_contracts) {
        this.date_contracts = date_contracts;
    }

    public String getName_Employers() {
        return name_Employers;
    }

    public void setName_Employers(String name_Employers) {
        this.name_Employers = name_Employers;
    }

    public String getName_candidates() {
        return name_candidates;
    }

    public void setName_candidates(String name_candidates) {
        this.name_candidates = name_candidates;
    }

    public String getPosition_name() {
        return position_name;
    }

    public void setPosition_name(String position_name) {
        this.position_name = position_name;
    }

    public String getFeddback() {
        return feddback;
    }

    public void setFeddback(String feddback) {
        this.feddback = feddback;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    @Override
    public String toString() {
        return "ContractPojo{" +
                "id_contracts='" + id_contracts + '\'' +
                ", date_contracts='" + date_contracts + '\'' +
                ", name_Employers='" + name_Employers + '\'' +
                ", name_candidates='" + name_candidates + '\'' +
                ", position_name='" + position_name + '\'' +
                ", feddback='" + feddback + '\'' +
                ", agent='" + agent + '\'' +
                '}';
    }
}
