/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.terratech.minerstats;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *
 * @author riazs
 */
/*

9.7 - ETH
116
25894;132;2
12707;13186
345254;531;3
169429;175825
82;50;78;47
daggerhashimoto.eu.nicehash.com:3353;decred.eu.nicehash.com:3354
0;0;0;0

*/
public class Result implements Serializable{
    public String claymoreVersion;
    public String uptime;
    public String totalEthHashrate;
    public String totalEthShares;
    public String totalEthRejects;
    public String individualEthGpuHashrates;
    public String totalDecHashrate;
    public String totalDecShares;
    public String totalDecRejects;
    public String individualDecGpuHashrates;
    public String gpuTempFanSpeed;
    public String ethPool;
    public String decPool;
    public String ethInvalidShares;
    public String ethPoolSwitches;
    public String decInvalidShares;
    public String decPoolSwitches;
    public List<GpuStats> gpuStats = new ArrayList();
    public String name;

    public String getClaymoreVersion() {
        return claymoreVersion;
    }

    public void setClaymoreVersion(String claymoreVersion) {
        this.claymoreVersion = claymoreVersion;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public String getTotalEthHashrate() {
        return totalEthHashrate;
    }

    public void setTotalEthHashrate(String totalEthHashrate) {
        this.totalEthHashrate = totalEthHashrate;
    }

    public String getTotalEthShares() {
        return totalEthShares;
    }

    public void setTotalEthShares(String totalEthShares) {
        this.totalEthShares = totalEthShares;
    }

    public String getTotalEthRejects() {
        return totalEthRejects;
    }

    public void setTotalEthRejects(String totalEthRejects) {
        this.totalEthRejects = totalEthRejects;
    }

    public String getIndividualEthGpuHashrates() {
        return individualEthGpuHashrates;
    }

    public void setIndividualEthGpuHashrates(String individualEthGpuHashrates) {
        this.individualEthGpuHashrates = individualEthGpuHashrates;
    }

    public String getTotalDecHashrate() {
        return totalDecHashrate;
    }

    public void setTotalDecHashrate(String totalDecHashrate) {
        this.totalDecHashrate = totalDecHashrate;
    }

    public String getTotalDecShares() {
        return totalDecShares;
    }

    public void setTotalDecShares(String totalDecShares) {
        this.totalDecShares = totalDecShares;
    }

    public String getTotalDecRejects() {
        return totalDecRejects;
    }

    public void setTotalDecRejects(String totalDecRejects) {
        this.totalDecRejects = totalDecRejects;
    }

    public String getIndividualDecGpuHashrates() {
        return individualDecGpuHashrates;
    }

    public void setIndividualDecGpuHashrates(String individualDecGpuHashrates) {
        this.individualDecGpuHashrates = individualDecGpuHashrates;
    }

    public String getGpuTempFanSpeed() {
        return gpuTempFanSpeed;
    }

    public void setGpuTempFanSpeed(String gpuTempFanSpeed) {
        this.gpuTempFanSpeed = gpuTempFanSpeed;
    }

    public String getEthPool() {
        return ethPool;
    }

    public void setEthPool(String ethPool) {
        this.ethPool = ethPool;
    }

    public String getDecPool() {
        return decPool;
    }

    public void setDecPool(String decPool) {
        this.decPool = decPool;
    }

    public String getEthInvalidShares() {
        return ethInvalidShares;
    }

    public void setEthInvalidShares(String ethInvalidShares) {
        this.ethInvalidShares = ethInvalidShares;
    }

    public String getEthPoolSwitches() {
        return ethPoolSwitches;
    }

    public void setEthPoolSwitches(String ethPoolSwitches) {
        this.ethPoolSwitches = ethPoolSwitches;
    }

    public String getDecInvalidShares() {
        return decInvalidShares;
    }

    public void setDecInvalidShares(String decInvalidShares) {
        this.decInvalidShares = decInvalidShares;
    }

    public String getDecPoolSwitches() {
        return decPoolSwitches;
    }

    public void setDecPoolSwitches(String decPoolSwitches) {
        this.decPoolSwitches = decPoolSwitches;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GpuStats> getGpuStats() {
        return gpuStats;
    }

    public void setGpuStats(List<GpuStats> gpuStats) {
        this.gpuStats = gpuStats;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
    
    
}
