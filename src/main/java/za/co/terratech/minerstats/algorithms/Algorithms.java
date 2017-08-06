
package za.co.terratech.minerstats.algorithms;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Algorithms {

    @SerializedName("result")
    @Expose
    private Result result;
    @SerializedName("method")
    @Expose
    private String method;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(result).append(method).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Algorithms) == false) {
            return false;
        }
        Algorithms rhs = ((Algorithms) other);
        return new EqualsBuilder().append(result, rhs.result).append(method, rhs.method).isEquals();
    }

}
