public class TransferList {
    Person transferredMoney;
    Person receivedMoney;
    public TransferList(Person transferredMoney, Person receivedMoney) {
        this.transferredMoney = transferredMoney;
        this.receivedMoney = receivedMoney;
    }

    /*        BEFORE REFACTORING
    public int calculateBalance() {
        if(transferredMoney.getBankBalance() > transferredMoney.getTransferAmount()) {
            int senderBankBalance = transferredMoney.getBankBalance() - transferredMoney.getTransferAmount();
            int receiverBankBalance = receivedMoney.getBankBalance() + transferredMoney.getTransferAmount();
            transferredMoney.setBankBalance(senderBankBalance);
            receivedMoney.setBankBalance(receiverBankBalance);
            return transferredMoney.getTransferAmount();
        }
        else if(transferredMoney.getBankBalance() < transferredMoney.getTransferAmount()) {
            return 0;
        }
        else {
            return 0;
        }
    }*/

    /*    AFTER REFACTORING  */

    public int calculateBalance() {
        if(transferredMoney.getBankBalance() > transferredMoney.getTransferAmount()) {
            transferredMoney.setBankBalance(transferredMoney.getBankBalance() - transferredMoney.getTransferAmount());
            receivedMoney.setBankBalance(receivedMoney.getBankBalance() + transferredMoney.getTransferAmount());
            return transferredMoney.getTransferAmount();
        }
        return 0;
    }
    public void transfer(MoneyTransferStrategy transferMethod){
        int amount = calculateBalance();
        if(amount > 0) {
            transferMethod.transfer(amount);
            transferredMoney.details();
            receivedMoney.details();
        }
        else {
            System.out.println("Insufficient balance, transfer not possible");
        }
    }
}