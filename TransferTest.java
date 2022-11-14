import java.io.IOException;
import java.sql.SQLOutput;

public class TransferTest {

    public static void main(String[] args) throws IOException {

        PersonGateway.createPersonTable();
        PersonGateway.fetchPersonData();
        //PaymentGateWay.createPaymentTable();
        //PaymentGateWay.fetchMethodData();
        ApplePaymentGateWay.createPaymentTable();
        ApplePaymentGateWay.fetchMethodData();
        GooglePaymentGateWay.createPaymentTable();
        GooglePaymentGateWay.fetchMethodData();

        Person person1 = new Person("Fatema Sheikh", 2);
        Person person2 = new Person("Protim Ghosh");
        Person person3 = new Person("Mehnaz Ruponti", 5);
        Person person4 = new Person("Fatema Sheikh");

        System.out.println("Before Sending Money:");
        person1.details();
        person2.details();
        TransferList money = new TransferList(person1, person2);
        System.out.println("After Sending money: ");
        money.transfer(new GooglePayStrategy("fatema@google.com", "2223"));

        System.out.println("Before Sending Money:");
        person3.details();
        person4.details();
        TransferList money2 = new TransferList(person3, person4);
        System.out.println("After Sending money: ");
        money2.transfer(new ApplePayStrategy("mehnaz@apple.com", "1114"));





    }

}