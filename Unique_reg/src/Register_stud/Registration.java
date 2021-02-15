package Register_stud;

import javax.swing.*;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Scanner;


public class Registration {
    Connection con;
    Statement stmt;
    String Name,prog_code,code,sum;
    int getvalue,YearOfAdmission,Sem_admission;
    DecimalFormat d =new DecimalFormat("000");
    public void connectDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //here oop2016 is database name, root is username and password is blank/empty
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Register", "root", "");
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select count(Name)+1 from students");
            while (rs.next()) {
                //System.out.println(rs.getString(1) + "  " + rs.getString(2));
                getvalue = Integer.parseInt(rs.getString("count(Name)+1"));
                sum = d.format(getvalue);
                System.out.println(sum);

            }
            inser();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void inser(){
        int result = 0;
        try {
            code = prog_code + "-" + sum + "-" + Sem_admission + "-" + YearOfAdmission;
            System.out.println(code);
           // generateSerial("select count(*)+1 from students");
            String inser = "INSERT INTO students VALUES ('"+Name+"','"+code+"');";

            result = stmt.executeUpdate(inser);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        if (result == 1)
            System.out.println("Inserted Successfully");

        else
            System.out.println("Not inserted");
    }


         void Registration(){




            try {
                System.out.println("Your name is: ");
                Scanner input = new Scanner(System.in);
                Name = input.nextLine();

                System.out.println("Options: IT, CT or CCN");
                System.out.println("The Program Code is: ");
                Scanner inp = new Scanner(System.in);
                prog_code = inp.nextLine();

                if (prog_code.equals("CCN")||prog_code.equals("IT")||prog_code.equals("CT")){

                    System.out.println("The Admission Semester is: ");
                    Scanner in = new Scanner(System.in);
                    Sem_admission = in.nextInt();
                    if (Sem_admission == 1) {
                        System.out.println("January");
                    } else if (Sem_admission == 2) {
                        System.out.println("May");
                    } else if (Sem_admission == 3) {
                        System.out.println("September");
                    } else {
                        System.out.println("Invalid Entry");
                        System.out.println("Enter Again");
                        Sem_admission = in.nextInt();
                    }
                }else { 

                    System.out.println("The Program Code is Again: ");
                    prog_code = inp.nextLine();

                }
                System.out.println("You are Admitted in Year: ");
                Scanner user = new Scanner(System.in);
                YearOfAdmission = user.nextInt();


            }catch (Exception e){
                    System.out.println(e);
            }
            
        }

    public static void main(String[] args) {

        Registration reg = new Registration();


        int exit = 0;
        while (exit != 1) {
            System.out.println("Hello, Make Your choice: ");
            System.out.println("1. Want to Register:\n2. Exit");
            Scanner key = new Scanner(System.in);
            int choice = key.nextInt();
            switch (choice){
                case 1:

                    reg.Registration();
                   reg.connectDB();

                    break;
                case 2:
                    exit = 1;
                    System.out.println("Thank you for your Service");
                    break;
                default:
                    System.out.println("Wrong Choice");
            }
        }exit++;
        //System.out.println("Code: "+prog_code+"-"+Sem_admission+"-"+YearOfAdmission+"-"+serial);



    }
}
