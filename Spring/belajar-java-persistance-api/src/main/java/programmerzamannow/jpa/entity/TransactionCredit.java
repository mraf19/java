package programmerzamannow.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions_credit")
public class TransactionCredit extends Transaction {

    public Long getCredit_amount() {
        return credit_amount;
    }

    public void setCredit_amount(Long credit_amount) {
        this.credit_amount = credit_amount;
    }

    @Column(name = "credit_amount")
    private Long credit_amount;
}
