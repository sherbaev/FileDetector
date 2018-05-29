package io.github.sherbaev.filedetect.data;

public class MRequest {
    private String requestUrl;

    public MRequest(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }
}
