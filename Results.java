package br.com.tcccomfirestore;

import androidx.annotation.NonNull;

public class Results {

    private String ph;
    private String description;
    private String rgbcode;

    public Results(String ph, @NonNull String description, String rgbcode) {
        this.ph = ph;
        this.description = description;
        this.rgbcode = rgbcode;

    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRgbcode() {
        return rgbcode;
    }

    public void setRgbcode(String rgbcode) {
        this.rgbcode = rgbcode;
    }
}
