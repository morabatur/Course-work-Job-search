package sample;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataBaseConnector {
    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/ekk";
    private static final String user = "ekk-15459@localhost";
    private static final String password = "zibenaht3000";

    // JDBC variables for opening and managing connection
    private static Connection connection = null;
    private static Statement stmt;
    private static ResultSet rs;

    public static void startConnection() throws SQLException {
        System.out.println("-------- MySQL JDBC Connection Testing ------------");

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;
        }

        System.out.println("MySQL JDBC Driver Registered!");


        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/ekk", "ekk-15459", "zibenaht3000");

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }


    }

    public static ArrayList<VacancyPojo> getVacancy() {
        ArrayList<VacancyPojo> vcpj = new ArrayList<>();
        try {
            stmt = connection.createStatement();
            // executing SELECT query
            rs = stmt.executeQuery("CALL get_all_vacancy()");

            int i = 0;
            while (rs.next()) {
                String name = rs.getString(1);
                String kind = rs.getString(2);
                String position = rs.getString(3);
                String qualification = rs.getString(4);
                String proffesion = rs.getString(5);
                int salary1 = rs.getInt(6);
                String salary = Integer.toString(salary1);
                String date = rs.getDate(7).toString();

                vcpj.add(new VacancyPojo(name, kind, position, qualification, proffesion, salary, date));
                System.out.println(vcpj.get(i).toString());
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vcpj;
    }

    public static ArrayList<String> getNameEmployers() throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        stmt = connection.createStatement();
        // executing SELECT query
        rs = stmt.executeQuery("SELECT employers.name FROM employers");

        while (rs.next()) {
            list.add(rs.getString(1));
            System.out.println(rs.getString(1));
        }
        return list;
    }

    public static ArrayList<VacancyPojo> getVacancyOneCompany(String nameEmployers) {
        ArrayList<VacancyPojo> vcpj = new ArrayList<>();
        try {
            stmt = connection.createStatement();
            // executing SELECT query
            rs = stmt.executeQuery("SELECT employers.name, kind_of_activity.kind_of_activity, vacancy.position, vacancy.qualification, vacancy.profession,\n" +
                    "  vacancy.salary, vacancy.date_of_public\n" +
                    "FROM vacancy\n" +
                    "JOIN  employers ON employers.id_employer=vacancy.id_employer\n" +
                    "JOIN  kind_of_activity ON  kind_of_activity.id_kind_of_activity=vacancy.id_kind_of_activity\n" +
                    "WHERE employers.name=" + "\'" + nameEmployers + "\'");

            int i = 0;
            while (rs.next()) {
                String name = rs.getString(1);
                String kind = rs.getString(2);
                String position = rs.getString(3);
                String qualification = rs.getString(4);
                String proffesion = rs.getString(5);
                int salary1 = rs.getInt(6);
                String salary = Integer.toString(salary1);
                String date = rs.getDate(7).toString();

                vcpj.add(new VacancyPojo(name, kind, position, qualification, proffesion, salary, date));
                System.out.println(vcpj.get(i).toString());
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vcpj;
    }

    public static ArrayList<String> getKindOfActivity() throws SQLException {
        ArrayList<String> listKind = new ArrayList<>();
        stmt = connection.createStatement();
        // executing SELECT query
        rs = stmt.executeQuery("SELECT kind_of_activity.id_kind_of_activity, kind_of_activity.kind_of_activity\n" +
                "FROM kind_of_activity");

        while (rs.next()) {
            listKind.add(rs.getString(1) + "#" + rs.getString(2));
            System.out.println(rs.getString(1));
        }
        return listKind;
    }


    public static ArrayList<SummaryPojo> getSummaryOnKind(String kind) {
        ArrayList<SummaryPojo> vcpj = new ArrayList<>();
        try {
            stmt = connection.createStatement();
            // executing SELECT query
            rs = stmt.executeQuery("SELECT CONCAT(candidates.name, \" \", candidates.surname), summary.qualification, summary.profession,\n" +
                    "  summary.expected_salary, summary.date_of_public\n" +
                    "FROM summary\n" +
                    "JOIN candidates ON candidates.id_candidate=summary.id_candidates\n" +
                    "WHERE summary.id_kind_of_activity IN (\n" +
                    "  SELECT kind_of_activity.id_kind_of_activity\n" +
                    "  FROM kind_of_activity\n" +
                    "  WHERE kind_of_activity.kind_of_activity=" + "\'" + kind + "\');");

            int i = 0;
            while (rs.next()) {
                String name = rs.getString(1);
                String qualification = rs.getString(2);
                String proffesion = rs.getString(3);
                int salary1 = rs.getInt(4);
                String salary = Integer.toString(salary1);
                String date = rs.getDate(5).toString();

                vcpj.add(new SummaryPojo(name, qualification, proffesion, salary, date));
                System.out.println(vcpj.get(i).toString());
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vcpj;
    }


    public static ArrayList<ContractPojo> getAllContracts() {
        ArrayList<ContractPojo> vcpj = new ArrayList<>();
        try {
            stmt = connection.createStatement();
            // executing SELECT query
            rs = stmt.executeQuery("SELECT  contracts.id_contracts, contracts.date, employers.name, CONCAT(candidates.name, ' ', candidates.surname),\n" +
                    "  vacancy.position,contracts.fee, CONCAT(agents.name, ' ', agents.surname)\n" +
                    "FROM contracts\n" +
                    "JOIN  agents ON contracts.id_agent = agents.id_agents\n" +
                    "JOIN  employers ON contracts.id_employers = employers.id_employer\n" +
                    "JOIN candidates ON contracts.id_candidates = candidates.id_candidate\n" +
                    "JOIN vacancy ON contracts.id_position = vacancy.id_vacancy");

            int i = 0;
            while (rs.next()) {
                String id = rs.getString(1);
                String date = rs.getString(2).toString();
                String employer = rs.getString(3).toString();
                String candidat = rs.getString(4);
                String position = rs.getString(5);
                int salary1 = rs.getInt(6);
                String salary = Integer.toString(salary1);
                String agent = rs.getString(7);
                vcpj.add(new ContractPojo(id, date, employer, candidat, position, salary, agent));
                //System.out.println(vcpj.get(i).toString());
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vcpj;
    }


    public static ArrayList<String> getCandidates() throws SQLException {
        ArrayList<String> listCandidates = new ArrayList<>();
        stmt = connection.createStatement();
        // executing SELECT query
        rs = stmt.executeQuery("SELECT candidates.id_candidate, CONCAT(candidates.name, ' ', candidates.surname, ' ', candidates.patronymic)\n" +
                "                FROM  candidates");

        while (rs.next()) {
            String number = rs.getString(1);
            String name = rs.getString(2);
            listCandidates.add(number + " " + name);

        }
        return listCandidates;
    }

    public static ArrayList<JobPojo> getSearchJob(String id) {
        ArrayList<JobPojo> vcpj = new ArrayList<>();
        try {
            stmt = connection.createStatement();
            // executing SELECT query
            rs = stmt.executeQuery("CALL find_job_candidates(" + id + ")");

            int i = 0;
            while (rs.next()) {
                String company = rs.getString(1);
                String position = rs.getString(2);
                int salary1 = rs.getInt(3);
                String salary = Integer.toString(salary1);
                String qualification = rs.getString(4);
                vcpj.add(new JobPojo(company, position, salary, qualification));
                //System.out.println(vcpj.get(i).toString());
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vcpj;
    }


    public static String[] getBueroIncome() {
        String mass[] = new String[2];
        try {
            stmt = connection.createStatement();
            // executing SELECT query
            rs = stmt.executeQuery("SELECT *\n" +
                    "FROM bureau_income");


            while (rs.next()) {
                mass[0] = rs.getString(1);
                mass[1] = rs.getString(2);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mass;
    }


    public static void insertIntoCandidates(String name, String surmame, String patr) {
        try {
            stmt = connection.createStatement();
            // executing SELECT query
            stmt.executeUpdate("INSERT INTO `candidates` (`id_candidate`, `surname`, `name`, `patronymic`, `counter`) VALUES (NULL, '" + name + "', '" + surmame + "', '" + patr + "', '0')");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertIntoEmployers(String name, String kind, String addr, String ph) {
        try {
            stmt = connection.createStatement();
            // executing SELECT query
            stmt.executeUpdate("INSERT INTO `employers` (`id_employer`, `name`, `id_kind_of_activity`, `address`, `phone_number`) " +
                    "VALUES (NULL, '" + name + "', '" + kind + "', '" + addr + "', '" + ph + "')\n");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean autorization(String id, String name) {
        String fullName = "";
        boolean res = false;
        try {
            stmt = connection.createStatement();
            // executing SELECT query
            rs = stmt.executeQuery("SELECT CONCAT( agents.surname, \" \",agents.name, \" \", agents.patronymic)\n" +
                    "FROM agents\n" +
                    "WHERE id_agents="+id);


            while (rs.next()) {
                fullName = rs.getString(1);
            }
            if (fullName.equalsIgnoreCase(name)) {res = true;}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }


}
