package com.sweetpoint.demo.controller;
import com.midtrans.httpclient.error.MidtransError;
import com.sweetpoint.demo.domain.dto.request.PaymentDto;
import com.sweetpoint.demo.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequestMapping("v1/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping(value = "/")
    public ResponseEntity<?> payment(@RequestBody PaymentDto request) throws MidtransError {
        return paymentService.pay(request);
    }
}
