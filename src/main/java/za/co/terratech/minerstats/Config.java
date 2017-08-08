
package za.co.terratech.minerstats;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Config {

    @SerializedName("tolerance")
    @Expose
    private Integer tolerance;
    @SerializedName("temperature")
    @Expose
    private Integer temperature;
    @SerializedName("hashrates")
    @Expose
    private Boolean hashrates;
    @SerializedName("altcoin")
    @Expose
    private String altcoin;
    @SerializedName("miners")
    @Expose
    private List<Miner> miners = new ArrayList<Miner>();

    public Integer getTolerance() {
        return tolerance;
    }

    public void setTolerance(Integer tolerance) {
        this.tolerance = tolerance;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Boolean getHashrates() {
        return hashrates;
    }

    public void setHashrates(Boolean hashrates) {
        this.hashrates = hashrates;
    }

    public String getAltcoin() {
        return altcoin;
    }

    public void setAltcoin(String altcoin) {
        this.altcoin = altcoin;
    }

    public List<Miner> getMiners() {
        return miners;
    }

    public void setMiners(List<Miner> miners) {
        this.miners = miners;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(tolerance).append(temperature).append(hashrates).append(altcoin).append(miners).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Config) == false) {
            return false;
        }
        Config rhs = ((Config) other);
        return new EqualsBuilder().append(tolerance, rhs.tolerance).append(temperature, rhs.temperature).append(hashrates, rhs.hashrates).append(altcoin, rhs.altcoin).append(miners, rhs.miners).isEquals();
    }

}
