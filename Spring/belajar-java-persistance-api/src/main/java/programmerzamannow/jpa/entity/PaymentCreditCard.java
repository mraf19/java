package programmerzamannow.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "payments_credit_card")
public class PaymentCreditCard extends Payment{

    public String getMaskedCard() {
        return maskedCard;
    }

    public void setMaskedCard(String maskedCard) {
        this.maskedCard = maskedCard;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    @Column(name = "masked_card")
    private String maskedCard;

    @Column(name = "bank")
    private String bank;
}
