package com.example.dictionary.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dictionary")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dictionary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    @Schema(name = "id", hidden = true)
    private Long id;

    @Column(name = "code", nullable = false)
    @Schema(name = "code", example = "example", required = true)
    private String code;

    @Column(name = "description", nullable = true)
    @Schema(name = "description", example = "example", required = true)
    private String description;

    // Конструктор для создания объекта Dictionary с кодом и описанием
    public Dictionary(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
