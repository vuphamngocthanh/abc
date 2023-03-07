package com.shop.webfe.momo;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shop.webfe.config.Environment;
import com.shop.webfe.config.PartnerInfo;
import com.shop.webfe.share.Execute;

public abstract class AbstractProcess<T, V> {
    protected PartnerInfo partnerInfo;
    protected Environment environment;
    protected Execute execute = new Execute();

    public AbstractProcess(Environment environment) {
        this.environment = environment;
        this.partnerInfo = environment.getPartnerInfo();
    }

    public static Gson getGson() {
        return new GsonBuilder()
                .disableHtmlEscaping()
                .create();
    }

    public abstract PaymentResponse execute(PaymentRequest request);
}
