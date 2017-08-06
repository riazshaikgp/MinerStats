
package za.co.terratech.minerstats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Miner {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("host")
    @Expose
    private String host;
    @SerializedName("port")
    @Expose
    private Integer port;
    @SerializedName("target_eth")
    @Expose
    private Integer targetEth;
    @SerializedName("target_dcr")
    @Expose
    private Integer targetDcr;
    @SerializedName("comments")
    @Expose
    private String comments;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getTargetEth() {
        return targetEth;
    }

    public void setTargetEth(Integer targetEth) {
        this.targetEth = targetEth;
    }

    public Integer getTargetDcr() {
        return targetDcr;
    }

    public void setTargetDcr(Integer targetDcr) {
        this.targetDcr = targetDcr;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(name).append(host).append(port).append(targetEth).append(targetDcr).append(comments).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Miner) == false) {
            return false;
        }
        Miner rhs = ((Miner) other);
        return new EqualsBuilder().append(id, rhs.id).append(name, rhs.name).append(host, rhs.host).append(port, rhs.port).append(targetEth, rhs.targetEth).append(targetDcr, rhs.targetDcr).append(comments, rhs.comments).isEquals();
    }

}
