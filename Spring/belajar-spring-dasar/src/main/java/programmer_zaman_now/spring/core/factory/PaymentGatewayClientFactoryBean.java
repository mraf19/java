package programmer_zaman_now.spring.core.factory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;
import programmer_zaman_now.spring.core.client.PaymentGatewayClient;

@Component("paymentGatewayClient")
public class PaymentGatewayClientFactoryBean implements FactoryBean<PaymentGatewayClient> {
    @Override
    public PaymentGatewayClient getObject() throws Exception {
        PaymentGatewayClient client = new PaymentGatewayClient();
        client.setEndpoint("https://google.com");
        client.setPublicKey("public");
        client.setPrivateKey("private");
        return client;
    }

    @Override
    public Class<?> getObjectType() {
        return PaymentGatewayClient.class;
    }
}
