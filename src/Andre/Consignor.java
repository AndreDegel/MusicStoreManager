package Andre;

/**
 * Created by Andre on 5/1/2015.
 */
public class Consignor {
    protected int consignorId;
    protected String consignorFirst;
    protected String consignorLast;
    protected String phone;
    protected String eMail;
    protected float moneyOwned;
    protected float moneyPaid;

    Consignor (int id, String first, String last, String phone, String email, float moneyOwned, float moneyPaid) {
        this.consignorId = id;
        this.consignorFirst = first;
        this.consignorLast = last;
        this.phone = phone;
        this.eMail = email;
        this.moneyOwned = moneyOwned;
        this.moneyPaid = moneyPaid;
    }

    public int getConsignorId() {
        return consignorId;
    }

    public String getConsignorFirst() {
        return consignorFirst;
    }

    public String getConsignorLast() {
        return consignorLast;
    }

    public String getPhone() {
        return phone;
    }

    public String geteMail() {
        return eMail;
    }

    public float getMoneyOwned() {
        return moneyOwned;
    }

    public float getMoneyPaid() {
        return moneyPaid;
    }

    public String toString() {
        String consignorData = "Consignor Id: " + this.consignorId + " Name: " + this.consignorFirst + " " + this.consignorLast +
                " Phone: " + this.phone + " E-Mail: " + this.eMail + " Money Owned: " + this.moneyOwned + " Money Paid: " + this.moneyPaid;
        return consignorData;
    }
}
