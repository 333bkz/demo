package com.bkz.demo.http.model.url;

public class UrlData {

    public String name;
    public String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Url{" +
                "SSID='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
