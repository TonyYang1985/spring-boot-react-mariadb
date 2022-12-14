package com.ibank.backend.vo.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TranferRequest {
    public  double amount;
    public  String payee;
}
