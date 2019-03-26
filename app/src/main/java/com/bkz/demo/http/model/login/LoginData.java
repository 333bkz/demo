package com.bkz.demo.http.model.login;

public class LoginData {
    private DeviceUser deviceUser;
    private LoginUser loginUser;

    public DeviceUser getDeviceUser() {
        return deviceUser;
    }

    public void setDeviceUser(DeviceUser deviceUser) {
        this.deviceUser = deviceUser;
    }

    public LoginUser getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(LoginUser loginUser) {
        this.loginUser = loginUser;
    }

    @Override
    public String toString() {
        return "UserForBlockChain{" +
                "deviceUser=" + (deviceUser == null ? "" : deviceUser.toString()) +
                ", loginUser=" + (loginUser == null ? "" : loginUser.toString()) +
                '}';
    }

    public class DeviceUser {

        private String cc;
        private String softConfId;
        private String filialeId;
        private String pdtTypeId;
        private String venderId;
        private String pdtState;
        private String serialNo;

        private String userId;
        private String token;

        public String getCc() {
            return cc;
        }

        public void setCc(String cc) {
            this.cc = cc;
        }

        public String getSoftConfId() {
            return softConfId;
        }

        public void setSoftConfId(String softConfId) {
            this.softConfId = softConfId;
        }

        public String getFilialeId() {
            return filialeId;
        }

        public void setFilialeId(String filialeId) {
            this.filialeId = filialeId;
        }

        public String getPdtTypeId() {
            return pdtTypeId;
        }

        public void setPdtTypeId(String pdtTypeId) {
            this.pdtTypeId = pdtTypeId;
        }

        public String getVenderId() {
            return venderId;
        }

        public void setVenderId(String venderId) {
            this.venderId = venderId;
        }

        public String getPdtState() {
            return pdtState;
        }

        public void setPdtState(String pdtState) {
            this.pdtState = pdtState;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getSerialNo() {
            return serialNo;
        }

        public void setSerialNo(String serialNo) {
            this.serialNo = serialNo;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        @Override
        public String toString() {
            return "DeviceUserForBlockChain{" +
                    "cc='" + cc + '\'' +
                    ", softConfId='" + softConfId + '\'' +
                    ", filialeId='" + filialeId + '\'' +
                    ", pdtTypeId='" + pdtTypeId + '\'' +
                    ", venderId='" + venderId + '\'' +
                    ", pdtState='" + pdtState + '\'' +
                    ", userId='" + userId + '\'' +
                    ", serialNo='" + serialNo + '\'' +
                    ", token='" + token + '\'' +
                    '}';
        }


    }

    public class LoginUser {

        private String loginId;
        private String token;

        private String user_name;
        private String nick_name;

        public String getLoginId() {
            return loginId;
        }

        public void setLoginId(String loginId) {
            this.loginId = loginId;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        @Override
        public String toString() {
            return "LoginUserForBlockChain{" +
                    "loginId='" + loginId + '\'' +
                    ", token='" + token + '\'' +
                    ", user_name='" + user_name + '\'' +
                    ", nick_name='" + nick_name + '\'' +
                    '}';
        }
    }
}
