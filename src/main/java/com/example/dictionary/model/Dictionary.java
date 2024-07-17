package com.example.dictionary.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dictionary")
public class Dictionary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @Schema(hidden = true)
    private UUID id;

    @Column(name = "code")
    @Schema(name = "code", example = "sample_code", required = true)
    private String code;

    @Column(name = "description")
    @Schema(name = "description", example = "Sample Description", required = false)
    private String description;

    @OneToMany(mappedBy = "dictionary", cascade = CascadeType.ALL)
    private List<Data> dataList;

}
