
package za.co.terratech.minerstats.statsprovider;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Stat {

    @SerializedName("balance")
    @Expose
    private String balance;
    @SerializedName("rejected_speed")
    @Expose
    private String rejectedSpeed;
    @SerializedName("algo")
    @Expose
    private Integer algo;
    @SerializedName("accepted_speed")
    @Expose
    private String acceptedSpeed;

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getRejectedSpeed() {
        return rejectedSpeed;
    }

    public void setRejectedSpeed(String rejectedSpeed) {
        this.rejectedSpeed = rejectedSpeed;
    }

    public Integer getAlgo() {
        return algo;
    }

    public void setAlgo(Integer algo) {
        this.algo = algo;
    }

    public String getAcceptedSpeed() {
        return acceptedSpeed;
    }

    public void setAcceptedSpeed(String acceptedSpeed) {
        this.acceptedSpeed = acceptedSpeed;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(balance).append(rejectedSpeed).append(algo).append(acceptedSpeed).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Stat) == false) {
            return false;
        }
        Stat rhs = ((Stat) other);
        return new EqualsBuilder().append(balance, rhs.balance).append(rejectedSpeed, rhs.rejectedSpeed).append(algo, rhs.algo).append(acceptedSpeed, rhs.acceptedSpeed).isEquals();
    }

}
