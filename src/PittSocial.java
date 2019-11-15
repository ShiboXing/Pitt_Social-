import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.sql.*;

public class PittSocial {
    private Connection _conn;

    public PittSocial(String username, String password, String url) throws ClassNotFoundException, SQLException, IOException {
        Class c = Class.forName("org.postgresql.Driver");
        Properties props = new Properties();
        props.setProperty("user", username);
        props.setProperty("password", password);
        this._conn = DriverManager.getConnection(url, props);
        /* set up the schema */
        executeSQLFile("schema.sql");
        executeSQLFile("trigger.sql");
    }

    public void executeSQLFile(String filePath) throws SQLException, IOException {
        Statement statement = _conn.createStatement();
        // initialize file reader
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        StringBuilder lines = new StringBuilder();
        String line = "";
        // read script line by line
        while ((line = reader.readLine()) != null) {
            lines.append(line);
        }
        System.out.println("queries: " + lines.toString());
        statement.execute(lines.toString());
    }


    public void createUser() throws SQLException {
        //PreparedStatement st=_conn.prepareStatement("call createuser('testest',\t'primis.in@placerateget.com',\t'5679',\n" +
        // "    '1997-09-10','2019-01-17 07:35:18.000000');");
    }
}
