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
    private UUID id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "dictionary_id", referencedColumnName = "id")
    @Schema(name = "dictionary", example = "{ \"id\": 1 }", required = true)
    private Dictionary dictionary;

    @Column(name = "code")
    @Schema(name = "code", example = "test", required = true)
    private String code;

    @Column(name = "value", nullable = false)
    @Schema(name = "value", example = "example value", required = true)
    private String value;

}
