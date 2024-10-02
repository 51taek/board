package blending.board.controller;

import blending.board.dto.payment.PaymentRequest;
import blending.board.result.ResultCode;
import blending.board.result.ResultResponse;
import blending.board.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping()
    public ResponseEntity<ResultResponse> payment(PaymentRequest paymentRequest) {
        paymentService.payment(paymentRequest);
        return ResponseEntity.ok().body(ResultResponse.of(ResultCode.PaymentSuccess));
    }
}
