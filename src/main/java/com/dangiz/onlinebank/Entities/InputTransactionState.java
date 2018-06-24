package com.dangiz.onlinebank.Entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.access.annotation.Secured;

public class InputTransactionState {
    @Getter
    @Setter
    public long RecipientId;
    @Getter
    @Setter
    public float Sum;

    public InputTransactionState() {
    }

    public InputTransactionState(long recipientId, float sum) {
        RecipientId = recipientId;
        Sum = sum;
    }

}
