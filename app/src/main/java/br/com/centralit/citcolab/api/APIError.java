package br.com.centralit.citcolab.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class APIError {


    public APIError() {
    }
    @SerializedName("errors")
    @Expose
    public String errors;

    @Override
    public String toString() {
        return "ErrorModel{" +
                "error='" + errors + '\'' +
                '}';
    }
}
