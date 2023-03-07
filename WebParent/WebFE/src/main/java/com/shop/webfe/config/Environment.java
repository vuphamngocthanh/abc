package com.shop.webfe.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Environment {
    private PartnerInfo partnerInfo;
    private MoMoEndpoint endpoints;
    public Environment(MoMoEndpoint momoEndpoint, PartnerInfo partnerInfo) {
        this.endpoints = momoEndpoint;
        this.partnerInfo = partnerInfo;
    }
    public static Environment selectEnv() throws IOException {
        try (InputStream input = Environment.class.getClassLoader().getResourceAsStream("application.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            MoMoEndpoint devEndpoint = new MoMoEndpoint(prop.getProperty("DEV_MOMO_ENDPOINT"),
                    prop.getProperty("CREATE_URL"),
                    prop.getProperty("REFUND_URL"),
                    prop.getProperty("QUERY_URL"),
                    prop.getProperty("CONFIRM_URL"),
                    prop.getProperty("TOKEN_PAY_URL"),
                    prop.getProperty("TOKEN_BIND_URL"),
                    prop.getProperty("TOKEN_INQUIRY_URL"),
                    prop.getProperty("TOKEN_DELETE_URL"));
            PartnerInfo devInfo = new PartnerInfo(prop.getProperty("DEV_PARTNER_CODE"), prop.getProperty("DEV_ACCESS_KEY"), prop.getProperty("DEV_SECRET_KEY"));
            Environment dev = new Environment(devEndpoint, devInfo);
            return dev;
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public MoMoEndpoint getMomoEndpoint() {
        return endpoints;
    }

    public void setMomoEndpoint(MoMoEndpoint momoEndpoint) {
        this.endpoints = momoEndpoint;
    }

    public PartnerInfo getPartnerInfo() {
        return partnerInfo;
    }

    public void setPartnerInfo(PartnerInfo partnerInfo) {
        this.partnerInfo = partnerInfo;
    }

}
