package net.carlos.dev.backend.dto.settings;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class GeneralSettingsDTO {
    private String id;
    private String nameRestaurant;
    private String footerPrinting;
    private String logo;
    private String menuColumns;
    private String serial;
    private String serialPluginPrinter;
    private String printerMode;
    private String printerSelected;
    private String lengthNameQuantity;
    private String lengthSubtotal;
    private String enableActivityLogs;
    private String enableBackgroundData;
}
