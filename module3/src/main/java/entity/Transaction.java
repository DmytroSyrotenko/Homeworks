package entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "transactions_table")
public class Transaction extends BaseEntity {

    @Column(name = "transaction_type", nullable = false)
    private String transactionType;

    @Column(name = "sender_id", nullable = false)
    private Long sender;

    @Column(name = "recipient_id", nullable = false)
    private Long recipient;

    @Digits(integer = 6, fraction = 2)
    @Column(nullable = false)
    private BigDecimal sum;

    @Column(name = "transaction_comment")
    private String comment;

    @Column(name = "transaction_time")
    LocalDateTime transactionTime;

    public Transaction() {
        this.transactionTime = LocalDateTime.now();
    }
}
