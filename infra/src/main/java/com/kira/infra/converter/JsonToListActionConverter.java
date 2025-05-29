package com.kira.infra.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kira.domain.model.PromotionAction;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.List;

@Converter
public class JsonToListActionConverter implements AttributeConverter<List<PromotionAction>, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<PromotionAction> actions) {
        try {
            return objectMapper.writeValueAsString(actions);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to convert actions to JSON", e);
        }
    }

    @Override
    public List<PromotionAction> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, new TypeReference<>() {});
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to convert JSON to actions", e);
        }
    }
}