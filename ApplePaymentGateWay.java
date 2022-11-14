import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ApplePaymentGateWay {

    public static void createPaymentTable() {

        String sql =     "CREATE TABLE IF NOT EXISTS applePay ("
                + "id       INTEGER   UNIQUE   REFERENCES person (id)"
                + "MATCH SIMPLE,"
                + "email    VARCHAR (50) NOT NULL,"
                + "password VARCHAR      NOT NULL"
                +   ");";



        try{

            Statement stmt = Connect.connect().createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String fetchMethodData() throws IOException {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("/Users/kawsarahmed/IdeaProjects/APP Project/MoneyTransferJson/ApplePay.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray appleList = (JSONArray) obj;
          //  System.out.println(obj);

            //Iterate over  array
            appleList.forEach( m -> parseAppleData( (JSONObject) m ) );

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (ParseException e)
        {
            e.printStackTrace();
        }

        return null;
    }
    private static void parseAppleData(JSONObject method)
    {
        String id = (String) method.get("id");

        String email = (String) method.get("email");
       // System.out.println(email);
        String password = (String) method.get("password");
      //  System.out.println(password);
        insert(id, email,password);
    }

    public static void insert(String id, String email, String password) {
        String sql = "INSERT INTO applePay(id, email, password) VALUES(?,?,?)";

        try{
            Connection conn = Connect.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.executeUpdate();
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
