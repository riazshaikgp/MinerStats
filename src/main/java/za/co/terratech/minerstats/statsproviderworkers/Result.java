
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
    private List<List<Object>> workers = new ArrayList();
    
    @SerializedName("actualWorkers")
    @Expose
    private List<Worker> actualWorkers = new ArrayList();
    
    @SerializedName("algo")
    @Expose
    private Integer algo;
    
    @SerializedName("algoName")
    @Expose
    private String algoName;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public List<List<Object>> getWorkers() {
        return workers;
    }

    public void setWorkers(List<List<Object>> workers) {
        this.workers = workers;
    }

    public List<Worker> getActualWorkers() {
        return actualWorkers;
    }

    public void setActualWorkers(List<Worker> actualWorkers) {
        this.actualWorkers = actualWorkers;
    }
    
    public Integer getAlgo() {
        return algo;
    }

    public void setAlgo(Integer algo) {
        this.algo = algo;
    }

    public String getAlgoName() {
        return algoName;
    }

    public void setAlgoName(String algoName) {
        this.algoName = algoName;
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
