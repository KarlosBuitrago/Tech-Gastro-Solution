package net.carlos.dev.backend.dto.settings;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LanguageSettingsDTO {
    private String systemLanguage;
    private String locale;
    private String timeZone;
    private String moneyFormat;
}
