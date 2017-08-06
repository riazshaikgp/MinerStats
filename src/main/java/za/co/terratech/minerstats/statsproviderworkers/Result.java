
package za.co.terratech.minerstats.statsproviderworkers;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Result {

    @SerializedName("addr")
    @Expose
    private String addr;
    @SerializedName("workers")
    @Expose
    private List<List<String>> workers = new ArrayList<List<String>>();
    @SerializedName("algo")
    @Expose
    private Integer algo;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public List<List<String>> getWorkers() {
        return workers;
    }

    public void setWorkers(List<List<String>> workers) {
        this.workers = workers;
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
        return new HashCodeBuilder().append(addr).append(workers).append(algo).toHashCode();
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
        return new EqualsBuilder().append(addr, rhs.addr).append(workers, rhs.workers).append(algo, rhs.algo).isEquals();
    }

}
