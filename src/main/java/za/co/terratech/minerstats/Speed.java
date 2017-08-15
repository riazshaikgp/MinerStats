
package za.co.terratech.minerstats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Speed {

    @SerializedName("a")
    @Expose
    private String a; //accepted
    @SerializedName("rt")
    @Expose
    private String rt; //rejected target
    @SerializedName("rs")
    @Expose
    private String rs; //rejected stale 
    @SerializedName("rd")
    @Expose
    private String rd; //rejected duplicate
    @SerializedName("ro")
    @Expose
    private String ro; //rejected other
    
    public String getA ()
    {
        return a;
    }

    public void setA (String a)
    {
        this.a = a;
    }

    public String getRt ()
    {
        return rt;
    }

    public void setRt (String rt)
    {
        this.rt = rt;
    }

    public String getRs() {
        return rs;
    }

    public void setRs(String rs) {
        this.rs = rs;
    }

    public String getRd() {
        return rd;
    }

    public void setRd(String rd) {
        this.rd = rd;
    }

    public String getRo() {
        return ro;
    }

    public void setRo(String ro) {
        this.ro = ro;
    }
    
    

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Speed) == false) {
            return false;
        }
        Speed rhs = ((Speed) other);
        return new EqualsBuilder().append(a, rhs.a).isEquals();
    }

}
