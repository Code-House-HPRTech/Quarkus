package org.png.dto.projection;


import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class SimProjection {
    public Long number;
    public String provider;

    public SimProjection(Long number, String provider) {
        this.number = number;
        this.provider = provider;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}
