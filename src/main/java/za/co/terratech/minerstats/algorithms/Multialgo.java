
package za.co.terratech.minerstats.algorithms;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Multialgo {

    @SerializedName("default_factor")
    @Expose
    private Double defaultFactor;
    @SerializedName("port")
    @Expose
    private Integer port;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("algo")
    @Expose
    private Integer algo;

    public Double getDefaultFactor() {
        return defaultFactor;
    }

    public void setDefaultFactor(Double defaultFactor) {
        this.defaultFactor = defaultFactor;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAlgo() {
        return algo;
    }

    public void setAlgo(Integer algo) {
        this.algo = algo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(defaultFactor).append(port).append(name).append(algo).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Multialgo) == false) {
            return false;
        }
        Multialgo rhs = ((Multialgo) other);
        return new EqualsBuilder().append(defaultFactor, rhs.defaultFactor).append(port, rhs.port).append(name, rhs.name).append(algo, rhs.algo).isEquals();
    }

}
