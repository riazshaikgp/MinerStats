
package za.co.terratech.minerstats.statsproviderex;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Result {

    @SerializedName("current")
    @Expose
    private List<Current> current = new ArrayList<Current>();
    @SerializedName("nh_wallet")
    @Expose
    private Boolean nhWallet;
    @SerializedName("past")
    @Expose
    private List<Past> past = new ArrayList<Past>();
    @SerializedName("payments")
    @Expose
    private List<Object> payments = new ArrayList<Object>();
    @SerializedName("addr")
    @Expose
    private String addr;

    public List<Current> getCurrent() {
        return current;
    }

    public void setCurrent(List<Current> current) {
        this.current = current;
    }

    public Boolean getNhWallet() {
        return nhWallet;
    }

    public void setNhWallet(Boolean nhWallet) {
        this.nhWallet = nhWallet;
    }

    public List<Past> getPast() {
        return past;
    }

    public void setPast(List<Past> past) {
        this.past = past;
    }

    public List<Object> getPayments() {
        return payments;
    }

    public void setPayments(List<Object> payments) {
        this.payments = payments;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(current).append(nhWallet).append(past).append(payments).append(addr).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Result) == false) {
            return false;
        }
        Result rhs = ((Result) other);
        return new EqualsBuilder().append(current, rhs.current).append(nhWallet, rhs.nhWallet).append(past, rhs.past).append(payments, rhs.payments).append(addr, rhs.addr).isEquals();
    }

}
