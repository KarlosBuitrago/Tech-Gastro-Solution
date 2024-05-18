package net.carlos.dev.backend.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TablesDTO {
    private Long id;
    private String name;
    private String description;
}
