import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ApplePayStrategy implements MoneyTransferStrategy {
    private String email;
    private String password;
    public ApplePayStrategy(String email, String password) {
        String sql = "SELECT email,password "
                + "FROM applePay WHERE email = ?";
        String email1 = null;
        String password1 = null;
        try (Connection conn = Connect.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            email1 = rs.getString("email");
            password1 = rs.getString("password");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        if (email.equals(email1) && password.equals(password1)) {
            this.email = email;
            this.password = password;
        }
        else {
            System.out.println("Wrong Email or Password!\n Try again.");
            System.exit(0);
        }
    }
    @Override
    public void transfer(int amount) {
        System.out.println(amount +" transferred with Apple Pay");
    }
}