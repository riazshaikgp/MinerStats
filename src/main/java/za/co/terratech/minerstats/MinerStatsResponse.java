package za.co.terratech.minerstats;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *
 * @author riazs
 */
@XmlRootElement
public class MinerStatsResponse implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    
    @SerializedName("host")
    @Expose
    private String host;
    
    @SerializedName("port")
    @Expose
    private String port;
    
    @SerializedName("error")
    @Expose
    private Object error;
    
    @SerializedName("result")
    @Expose
    private List<String> result = new ArrayList<String>();
    
    @SerializedName("minerResult")
    @Expose
    private Result minerResult;
    
    @SerializedName("name")
    @Expose
    private String name;
    
    private final static long serialVersionUID = -4958419375007768114L;

    public MinerStatsResponse() {
    }
    
    MinerStatsResponse(Integer id, String error) {
        this.id = id;
        this.error = error;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }

    public Result getMinerResult() {
        return minerResult;
    }

    public void setMinerResult(Result minerResult) {
        this.minerResult = minerResult;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(error).append(result).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MinerStatsResponse) == false) {
            return false;
        }
        MinerStatsResponse rhs = ((MinerStatsResponse) other);
        return new EqualsBuilder().append(id, rhs.id).append(error, rhs.error).append(result, rhs.result).isEquals();
    }

}
