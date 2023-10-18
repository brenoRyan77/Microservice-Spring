package com.ms.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO {

    private UUID userId;
    private String emailTo;
    private String subject;
    private String text;
}
