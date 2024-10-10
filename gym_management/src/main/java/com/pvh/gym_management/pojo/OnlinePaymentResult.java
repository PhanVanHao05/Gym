package com.pvh.gym_management.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "online_payment_result", schema = "gym_managementdb")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnlinePaymentResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long paymentId;

    private String paymentCode;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "confirm_at")
    private Date confirmAt;

    @Column(name = "bank_code")
    private String bankCode;

    @Column(name = "transaction_no")
    private String transactionNo;

    @Column(name = "bank_transaction_no")
    private String bankTransactionNo;

    @Column(name = "card_type")
    private String cardType;
}
