package entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "accounts_table")
public class Account extends BaseEntity {

    @Column(name = "account_name", nullable = false)
    private String name;

    @Digits(integer = 6, fraction = 2)
    @Column(name = "account_sum")
    private BigDecimal sum;

    @ToString.Exclude
    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH,CascadeType.REMOVE})
    @JoinColumn(name = "account_id")
    private Set<Transaction> transactions;

    public Account() {
        this.sum = BigDecimal.valueOf(0);
        this.transactions = new HashSet<>();
        this.name = "DEFAULT";
    }
}
