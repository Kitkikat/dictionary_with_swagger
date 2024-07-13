package com.example.dictionary.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "data")
public class Data {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id", nullable = false, unique = true)

    @Schema(hidden = true)

    private Long id;

    @Column(name = "dictionary_id", nullable = false)

    @Schema(name = "dictionary_id", example = "1111", required = true)

    private Long dictionary_id;

    @Column(name = "code")

    @Schema(name = "code", example = "test", required = false)

    private String code;

    @Column(name = "value", nullable = false)

    @Schema(name = "value", example = "test", required = true)

    private String value;
}