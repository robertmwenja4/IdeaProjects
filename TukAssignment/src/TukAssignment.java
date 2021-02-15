public class TukAssignment {
    private static int studentsCountVal;
    public String studentName;
    public String[] programmeCodes = {"CN", "IT","CCN"};
    public String selectedProgrammeCode;
    public String[] semesterAdmission = {"January", "May", "September"};
    public String selectedAdmMonth;
    public int selectedAdmMonthCode;
    public int yearOfAdm;

    public String getUserInput (int studentsCount){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter The Student Name");
        studentName = scanner.nextLine();
        System.out.println("Select a student code by entering the respective number");
        for (int i=0;i<semesterAdmission.length; i++){
            int countVal = i +1;
            System.out.println(countVal + " " + semesterAdmission[i]);
        }
        int selectedProgramme = scanner.nextInt();
        switch (selectedProgramme){
            case 1:
                selectedAdmMonth = "January";
                selectedAdmMonthCode = 1;
                break;
            case 2:
                selectedAdmMonth = "May";
                selectedAdmMonthCode = 2;
                break;
            case 3:
                selectedAdmMonth = "September";
                selectedAdmMonthCode = 3;
                break;
            default:
                System.out.println("You need to select an Adm Month");
        }
        System.out.println("Enter the programme code");
        for (int i=0;i<programmeCodes.length; i++){
            int countVal = i +1;
            System.out.println(countVal + " " + programmeCodes[i]);
        }
        int userProgrammeCode = scanner.nextInt();
        switch (userProgrammeCode){
            case 1:
                selectedProgrammeCode = "CT";
                break;
            case 2:
                selectedProgrammeCode = "IT";
                break;
            case 3:
                selectedProgrammeCode = "CCN";
                break;
            default:
                System.out.println("You need to select a programme code");
        }
        System.out.println("Enter Year of Adm");
        yearOfAdm = scanner.nextInt();
        String studentIndex = String.format("%03d", studentsCount);
        String serialPlusMonthCode = String.format("%s-%s", studentIndex, selectedAdmMonthCode);
        String newAdm = String.format(
                "%s-%s-%s", selectedProgrammeCode, serialPlusMonthCode, yearOfAdm);
        String newStudentAdm = String.format("Welcome %s, your adm is %s", studentName, newAdm);
        return newStudentAdm;
    }
    public static  Connection getDbConnection(){
        String dbUrl = "jdbc.mysql://localhost/3306/studentsDatabase";
        String dbUser = "";
        String dbUserPassword = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbUrl, dbUser, dbUserPassword);
            return con;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
    public int getUserCount(Connection con){
        String studentsCountQuery = "select count(*) from studentsTable";
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(studentsCountQuery);
            rs.next();
            studentsCountVal = rs.getInt(1);
            st.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return studentsCountVal;
    }
    public void saveNewStudent(Connection con, String adm) {

        String qs = String.format(
                "INSERT INTO studentsTable VALUES (%s %s %s, %s)",
                studentName,adm,selectedProgrammeCode, yearOfAdm);
        try{
            Statement st = con.createStatement();
            st.executeQuery(qs);
            st.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        System.out.println();
    }
    public static void main(String[] args){
        TukAssignment tu = new TukAssignment();
        Connection connection = getDbConnection();
        int userCount = tu.getUserCount(connection);
        String studentAdm = tu.getUserInput(userCount);
        tu.saveNewStudent(connection, studentAdm);
    }
}
