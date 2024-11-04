package com.todorian.system.command.domain.model;

import com.todorian.system.command.domain.model.property.SettingKey;
import com.todorian.system.command.domain.model.property.ValueType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "system_setting_tb")
public class SystemSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private SettingKey settingKey;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ValueType valueType;
    @Column(nullable = false)
    private String value;

    @Builder
    public SystemSetting(SettingKey settingKey, ValueType valueType, String value) {
        this.settingKey = settingKey;
        this.valueType = valueType;
        this.value = value;
    }

    public Object getValue() {
        return switch (valueType) {
            case BOOLEAN -> Boolean.parseBoolean(value);
            case LONG -> Long.parseLong(value);
            case DOUBLE -> Double.parseDouble(value);
            default -> value;
        };
    }
}
