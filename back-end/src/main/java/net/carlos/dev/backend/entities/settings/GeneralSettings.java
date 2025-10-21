package net.carlos.dev.backend.entities.settings;

import jakarta.persistence.*;

@Entity
@Table(name = "general_settings")
public class GeneralSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name_restaurant", nullable = false)
    private String nameRestaurant;
    @Column(name = "footer_printing", nullable = false)
    private String footerPrinting;
    @Column(name = "logo", nullable = false)
    private String logo;
    @Column(name = "menu_columns", nullable = false)
    private String menuColumns;
    @Column(name = "serial", nullable = false)
    private String serial;
    @Column(name = "serial_plugin_printer", nullable = false)
    private String serialPluginPrinter;
    @Column(name = "printer_mode", nullable = false)
    private String printerMode;
    @Column(name = "printer_selected", nullable = false)
    private String printerSelected;
    @Column(name = "length_name_quantity", nullable = false)
    private String lengthNameQuantity;
    @Column(name = "length_subtotal", nullable = false)
    private String lengthSubtotal;
    @Column(name = "enable_activity_logs", nullable = false)
    private String enableActivityLogs;
    @Column(name = "enable_background_data", nullable = false)
    private String enableBackgroundData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
