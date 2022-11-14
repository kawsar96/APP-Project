import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;


public class PersonGateway {

//    private String name;
//    private int transferAmount;
//    private int bankBalance;

    public static void createPersonTable() {

        String sql = "CREATE TABLE IF NOT EXISTS person (\n"
                + " id integer PRIMARY KEY,\n"
                + " name text NOT NULL,\n"
                + " balance real\n"
                + ");";

        try{

            Statement stmt = Connect.connect().createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
//
public static String fetchPersonData() throws IOException {

    JSONParser jsonParser = new JSONParser();

    try (FileReader reader = new FileReader("/Users/kawsarahmed/IdeaProjects/APP Project/MoneyTransferJson/Person.json"))
    {
        //Read JSON file
        Object obj = jsonParser.parse(reader);

      JSONArray personList = (JSONArray) obj;
    //    System.out.println(obj);

        //Iterate over  array
        personList.forEach( p -> parsePersonData( (JSONObject) p ) );

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
    private static void parsePersonData(JSONObject person)
    {

        String id = (String) person.get("ID");
      //  System.out.println(id);
        String name = (String) person.get("Name");
       // System.out.println(name);
        String balance = (String) person.get("Balance");
       // System.out.println(balance);
        insert(id,name,balance);
    }
//
public static void insert(String id,String name,String balance) {
    String sql = "INSERT INTO person(id, name, balance) VALUES(?,?,?)";

    try{
        Connection conn = Connect.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);
        pstmt.setString(2, name);
        pstmt.setString(3, balance);
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