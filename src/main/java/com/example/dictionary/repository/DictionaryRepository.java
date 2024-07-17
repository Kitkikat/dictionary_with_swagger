package com.example.dictionary.repository;

import com.example.dictionary.model.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface DictionaryRepository extends JpaRepository<Dictionary, UUID> {
}
