package za.co.terratech.minerstats.statsproviderex;

import za.co.terratech.minerstats.Speed;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Current {

    @SerializedName("profitability")
    @Expose
    private String profitability;
    @SerializedName("data")
    @Expose
    private List<Object> data = new ArrayList<Object>();
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("suffix")
    @Expose
    private String suffix;
    @SerializedName("algo")
    @Expose
    private Integer algo;
    @SerializedName("balance")
    @Expose
    private String balance;
    @SerializedName("speed")
    @Expose
    private Speed speed;

    public String getProfitability() {
        return profitability;
    }

    public void setProfitability(String profitability) {
        this.profitability = profitability;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public Integer getAlgo() {
        return algo;
    }

    public void setAlgo(Integer algo) {
        this.algo = algo;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public Speed getSpeed() {
        return speed;
    }

    public void setSpeed(Speed speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(profitability).append(data).append(name).append(suffix).append(algo).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Current) == false) {
            return false;
        }
        Current rhs = ((Current) other);
        return new EqualsBuilder().append(profitability, rhs.profitability).append(data, rhs.data).append(name, rhs.name).append(suffix, rhs.suffix).append(algo, rhs.algo).isEquals();
    }

}
