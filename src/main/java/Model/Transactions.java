package Model;

public class Transactions {
    public int transaction_id;

    public int amount;

    public int balance;

    public long time_posted_epoch;

    public int user_id;

    public Transactions(int generated_transaction_id, int userId, int amount, long timePostedEpoch){

    }

    /** Posting a new transaction */
    public Transactions(int amount, long time_posted_epoch, int user_id){
        this.amount = amount;
        this.time_posted_epoch = time_posted_epoch;
        this.user_id = user_id;
    }

    /** Retrieving a transaction */
    public Transactions(int transaction_id, int amount, int balance, long time_posted_epoch, int user_id){
        this.transaction_id = transaction_id;
        this.amount = amount;
        this.balance = balance;
        this.time_posted_epoch = time_posted_epoch;
        this.user_id = user_id;
    }

    public Transactions(int balance, int userId) {
    }

    public int getTransaction_id(){
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public long getTime_posted_epoch() {
        return time_posted_epoch;
    }

    public void setTime_posted_epoch(long time_posted_epoch) {
        this.time_posted_epoch = time_posted_epoch;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transactions transactions = (Transactions) o;
        return transaction_id == transactions.transaction_id && amount == transactions.amount
                && balance == transactions.balance && time_posted_epoch == transactions.time_posted_epoch
                && user_id == transactions.user_id;
    }

    @Override
    public String toString(){
        return "Transactions{" +
                "transaction_id=" + transaction_id +
                ", amount=" + amount +
                ", balance=" + balance +
                ", time_posted_epoch=" + time_posted_epoch +
                ", user_id=" + user_id +
                '}';
    }
}
