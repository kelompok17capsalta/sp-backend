package com.sweetpoint.demo.service;

import com.midtrans.Midtrans;
import com.midtrans.httpclient.SnapApi;
import com.midtrans.httpclient.error.MidtransError;

import com.sweetpoint.demo.constant.ConstantApp;
import com.sweetpoint.demo.domain.dto.request.PaymentDto;
import com.sweetpoint.demo.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Slf4j
@Service
public class PaymentService {

    public ResponseEntity<Object> pay(PaymentDto request) throws MidtransError {

        Midtrans.serverKey = "SB-Mid-server-VZ6innSZ-v_nXz63iIulNVeR";

        Midtrans.isProduction = false;

        UUID idRand = UUID.randomUUID();
        Map<String, Object> params = new HashMap<>();

        Map<String, String> transactionDetails = new HashMap<>();
        transactionDetails.put("order_id", String.valueOf(idRand));
        transactionDetails.put("gross_amount", String.valueOf(request.getPrice()));

        Map<String, String> creditCard = new HashMap<>();
        creditCard.put("secure", "true");

        Map<String, String> customerDetails = new HashMap<>();
        customerDetails.put("name", request.getName());
        customerDetails.put("provider", request.getProvider());
        customerDetails.put("credentials", request.getCredentials());

        params.put("transaction_details", transactionDetails);
        params.put("credit_card", creditCard);
        params.put("customer_details", customerDetails);

        String transactionToken = SnapApi.createTransactionToken(params);

        Map<String, Object> response = new HashMap<>();
        response.put("token", transactionToken);

        return ResponseUtil.build(ConstantApp.SUCCESS, response, HttpStatus.OK);
    }
}
