package blending.board.dto.payment;

import lombok.Data;

@Data
public class PaymentRequest {

    private String pg;

    private String payMethod;

    private String merchantId;

    private Integer amount;

}
