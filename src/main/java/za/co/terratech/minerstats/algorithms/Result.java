
package za.co.terratech.minerstats.algorithms;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Result {

    @SerializedName("multialgo")
    @Expose
    private List<Multialgo> multialgo = new ArrayList<Multialgo>();

    public List<Multialgo> getMultialgo() {
        return multialgo;
    }

    public void setMultialgo(List<Multialgo> multialgo) {
        this.multialgo = multialgo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(multialgo).toHashCode();
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
        return new EqualsBuilder().append(multialgo, rhs.multialgo).isEquals();
    }

}
