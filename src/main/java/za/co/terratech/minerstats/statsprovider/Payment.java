
package za.co.terratech.minerstats.statsprovider;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Payment {

    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("fee")
    @Expose
    private String fee;
    @SerializedName("TXID")
    @Expose
    private String tXID;
    @SerializedName("time")
    @Expose
    private String time;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getTXID() {
        return tXID;
    }

    public void setTXID(String tXID) {
        this.tXID = tXID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(amount).append(fee).append(tXID).append(time).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Payment) == false) {
            return false;
        }
        Payment rhs = ((Payment) other);
        return new EqualsBuilder().append(amount, rhs.amount).append(fee, rhs.fee).append(tXID, rhs.tXID).append(time, rhs.time).isEquals();
    }

}
