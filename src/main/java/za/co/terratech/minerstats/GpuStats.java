/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.terratech.minerstats;

import java.io.Serializable;

/**
 *
 * @author riazs
 */
public class GpuStats implements Serializable{
    private String gpuEthHash;
    private String gpuDecHash;
    private String gpuTemp;
    private String gpuFanSpeed;
    private String name;

    public String getGpuEthHash() {
        return gpuEthHash;
    }

    public void setGpuEthHash(String gpuEthHash) {
        this.gpuEthHash = gpuEthHash;
    }

    public String getGpuDecHash() {
        return gpuDecHash;
    }

    public void setGpuDecHash(String gpuDecHash) {
        this.gpuDecHash = gpuDecHash;
    }

    public String getGpuTemp() {
        return gpuTemp;
    }

    public void setGpuTemp(String gpuTemp) {
        this.gpuTemp = gpuTemp;
    }

    public String getGpuFanSpeed() {
        return gpuFanSpeed;
    }

    public void setGpuFanSpeed(String gpuFanSpeed) {
        this.gpuFanSpeed = gpuFanSpeed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
