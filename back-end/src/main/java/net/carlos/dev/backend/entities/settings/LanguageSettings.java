package net.carlos.dev.backend.entities.settings;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "language_settings")
public class LanguageSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "system_language", nullable = false, length = 50, unique = true, columnDefinition = "varchar(50) default 'es'")
    private String systemLanguage;
    @Column(name = "locale", nullable = false, length = 50, unique = true, columnDefinition = "varchar(50) default 'es-CO'")
    private String locale;
    @Column(name = "time_zone", nullable = false, length = 50, unique = true, columnDefinition = "varchar(50) default 'America/Bogota'")
    private String timeZone;
    @Column(name = "money_format", nullable = false, length = 50, unique = true, columnDefinition = "varchar(50) default 'COP'")
    private String moneyFormat;
}
