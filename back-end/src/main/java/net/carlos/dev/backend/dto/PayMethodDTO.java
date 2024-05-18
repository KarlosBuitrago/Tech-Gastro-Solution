package net.carlos.dev.backend.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PayMethodDTO {
    private Long id;
    private String name;
}
