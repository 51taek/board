package blending.board.service;

import blending.board.config.jwt.AuthUtils;
import blending.board.dto.payment.PaymentRequest;
import blending.board.entity.Member;
import blending.board.entity.Payment;
import blending.board.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final PaymentRepository paymentRepository;

    private final AuthUtils authUtils;

    public void payment(PaymentRequest paymentRequest) {

        Member loginMember = authUtils.getLoginMember();
        UUID id = loginMember.getId();

        Payment payment = Payment.builder()
                .pg(paymentRequest.getPg())
                .payMethod(paymentRequest.getPayMethod())
                .merchantId(paymentRequest.getMerchantId())
                .amount(paymentRequest.getAmount())
                .memberId(id)
                .build();

        log.info("Payment : {}", payment);

        paymentRepository.save(payment);
    }
}
