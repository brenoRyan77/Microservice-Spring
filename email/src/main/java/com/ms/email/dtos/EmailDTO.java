package com.ms.email.dtos;

import java.util.UUID;

public record EmailDTO(String name,
                       String emailTo,
                       String subject,
                       UUID userId) {
}
