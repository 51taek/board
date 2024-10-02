package blending.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor
public class Payment {

    @Id @GeneratedValue
    private Long id;

    private String pg;

    private String payMethod;

    private String merchantId;

    private Integer amount;

    private UUID memberId;

    @Builder
    public Payment(String pg, String payMethod, String merchantId, Integer amount, UUID memberId) {
        this.pg = pg;
        this.payMethod = payMethod;
        this.merchantId = merchantId;
        this.amount = amount;
        this.memberId = memberId;
    }
}
