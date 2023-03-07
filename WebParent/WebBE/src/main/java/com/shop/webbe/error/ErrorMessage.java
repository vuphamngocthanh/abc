package com.shop.webbe.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorMessage {
    private String field;
    private String errorMassage;
}
