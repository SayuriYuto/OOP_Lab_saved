import java.sql.*;
import java.util.*;

public class Lab6 {

    public static void main(String[] args) {
        try {
            int choice = 0;
            student s = new student();
            Scanner choicein = new Scanner(System.in);
            do {
                System.out.println(
                        "~~~~~~~~STUDENT REGISTRATION THROUGH JDBC~~~~~~~~\nSelect an operation \n 1- Register a Student \n 2- Roll No Update \n 3- Delete a Record \n 4- Search for a Student \n 5- Exit \n Enter your choice :");

                choice = choicein.nextInt();
                switch (choice) {
                    case 1:
                        s.getStudentDetails();
                        s.insertStudent();
                        break;
                    case 2:
                        s.updateStudentRollno();
                        break;
                    case 3:
                        s.deleteStudentRecord();
                        break;
                    case 4:
                        s.searchStudent();
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("Select the correct choice");
                }

            } while (choice != 5);
        } catch (Exception e) {
            System.out.println("Exception occured");
            System.out.println(e.getMessage());
        }
    }
}

class student {
    private String name;
    private String rollno;
    private String country;
    private int mark;

    public void getStudentDetails() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your Name :");
        name = input.nextLine();
        System.out.println("Enter your Roll no : ");
        rollno = input.nextLine();
        System.out.println("Enter your Place : ");
        country = input.nextLine();
        System.out.println("Enter the marks :");
        mark = input.nextInt();
    }

    public void insertStudent()
            throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

        dbmsconnection dbmsconnect = new dbmsconnection("jdbc:mysql://localhost:3306/student", "root", "admin4363");
        Connection con = dbmsconnect.getConnection();
        String sql = "insert into student values (?,?,?,?);";
        PreparedStatement statement = con.prepareStatement(sql); // Used to create SQL statment
        statement.setString(1, name);
        statement.setString(2, rollno);
        statement.setString(3, country);
        statement.setInt(4, mark);
        statement.executeUpdate();
        System.out.println("Record  inserted successfully");
        dbmsconnect.closeConnection(con, statement);
    }

    public void updateStudentRollno()
            throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        dbmsconnection dbmsconnect = new dbmsconnection("jdbc:mysql://localhost:3306/student", "root", "admin4363");
        Connection con = dbmsconnect.getConnection();
        System.out.println("Enter Your Name");
        Scanner input = new Scanner(System.in);
        String inputname = input.nextLine();
        System.out.println("Enter the new Roll no");
        String inputrollno = input.nextLine();
        String sql = "update student set rollno = ? where name = ?;";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, inputrollno);
        statement.setString(2, inputname);
        if(statement.executeUpdate()>0){
            System.out.println("Record was Updated!");
        }
        else{
            System.out.println("Record was not Found!");
        }
        dbmsconnect.closeConnection(con, statement);
    }

    public void deleteStudentRecord() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        dbmsconnection dbmsconnect = new dbmsconnection("jdbc:mysql://localhost:3306/student", "root", "admin4363");
        Connection con = dbmsconnect.getConnection();
        System.out.println("Enter the Roll No of the Student");
        Scanner input = new Scanner(System.in);
        String inputname = input.nextLine();
        String sql = "delete from student where rollno = ?;";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, inputname);
        int i = stmt.executeUpdate();
        if (i > 0) {
            System.out.println("Record Deleted Successfully");
        } else {
            System.out.println("No Such Record in the Database");
        }
        dbmsconnect.closeConnection(con, stmt);
    }

    public void searchStudent()
            throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        dbmsconnection dbmsconnect = new dbmsconnection("jdbc:mysql://localhost:3306/student", "root", "admin4363");
        Connection con = dbmsconnect.getConnection();
        System.out.println("Enter Your Name : ");
        Scanner input = new Scanner(System.in);
        String inputname = input.nextLine();
        String sql = "select * from student where name=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, inputname);
        ResultSet rs = statement.executeQuery();
        while (rs.next() != false){
            System.out.println("\n ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\nName : " + rs.getString(1) + "\t Roll No : " + rs.getString(2) + "\t Place : "
                    + rs.getString(3) + "\t Marks : " + rs.getInt(4));
                    System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        }

        dbmsconnect.closeConnection(con, statement);
    }
}

class dbmsconnection {
    String url;
    String username;
    String password;

    public dbmsconnection(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Connection getConnection()
            throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        Connection connect = null;
        connect = DriverManager.getConnection(url, username, password);
        System.out.println("Connection Established Successfully");
        return connect;
    }

    public void closeConnection(Connection connect, Statement statement) throws SQLException {
        statement.close();
        connect.close();
        System.out.println("The connection is closed\n\n");
    }
}
