import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransferListTest {

    @Test
    void calculateBalance() {
        Person sender1 = new Person("Fatema Sheikh", 5);
        Person receiver1 = new Person("Protim Ghosh");
        TransferList t = new TransferList(sender1, receiver1);
        assertEquals(5, t.calculateBalance());

    }

    @Test
    void transfer() {
        Person sender2 = new Person("Mehnaz Ruponti", 3);
        Person receiver2 = new Person("Fatema Sheikh");
        TransferList t1 = new TransferList(sender2, receiver2);
        t1.transfer(new ApplePayStrategy("mehnaz@apple.com", "1114"));
        assertEquals(3, t1.calculateBalance());

        Person sender3 = new Person("Fatema Sheikh", 2);
        Person receiver3 = new Person("Protim Ghosh");
        TransferList t2 = new TransferList(sender3, receiver3);
        t2.transfer(new GooglePayStrategy("fatema@google.com", "2223"));
        assertEquals(2, t2.calculateBalance());

    }
}

