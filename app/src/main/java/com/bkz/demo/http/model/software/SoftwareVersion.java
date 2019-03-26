package com.bkz.demo.http.model.software;

public class SoftwareVersion {
    public int appVersionNumber;
    public String downloadUrl;
    public String publishDate;
    public String remark;
    /**
     * 是否强制升级
     * 0否，1是
     */
    public int isForceUpdate;


    @Override
    public String toString() {
        return "SoftwareVersion{" +
                "appVersionNumber=" + appVersionNumber +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", remark='" + remark + '\'' +
                ", isForceUpdate=" + isForceUpdate +
                '}';
    }
}
