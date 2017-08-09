
package za.co.terratech.minerstats.algorithms;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Simplemultialgo {

    @SerializedName("paying")
    @Expose
    private String paying;
    @SerializedName("port")
    @Expose
    private Integer port;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("algo")
    @Expose
    private Integer algo;

    public String getPaying() {
        return paying;
    }

    public void setPaying(String paying) {
        this.paying = paying;
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
        return new HashCodeBuilder().append(paying).append(port).append(name).append(algo).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Simplemultialgo) == false) {
            return false;
        }
        Simplemultialgo rhs = ((Simplemultialgo) other);
        return new EqualsBuilder().append(paying, rhs.paying).append(port, rhs.port).append(name, rhs.name).append(algo, rhs.algo).isEquals();
    }

}
