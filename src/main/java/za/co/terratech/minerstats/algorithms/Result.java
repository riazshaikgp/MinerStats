
package za.co.terratech.minerstats.algorithms;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Result {

    @SerializedName("simplemultialgo")
    @Expose
    private List<Simplemultialgo> simplemultialgo = new ArrayList<Simplemultialgo>();

    public List<Simplemultialgo> getSimplemultialgo() {
        return simplemultialgo;
    }

    public void setSimplemultialgo(List<Simplemultialgo> simplemultialgo) {
        this.simplemultialgo = simplemultialgo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(simplemultialgo).toHashCode();
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
        return new EqualsBuilder().append(simplemultialgo, rhs.simplemultialgo).isEquals();
    }

}
