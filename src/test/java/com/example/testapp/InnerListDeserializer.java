package com.example.testapp;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
@Slf4j
public class InnerListDeserializer extends JsonDeserializer<List> implements ContextualDeserializer {

    private final JavaType propertyType;

    public InnerListDeserializer() {
        this(null);
    }

    public InnerListDeserializer(JavaType propertyType) {
        this.propertyType = propertyType;
    }

    @Override
    public List deserialize(JsonParser p, DeserializationContext context) throws IOException {
        SorMappingFilterEnabledList list = new SorMappingFilterEnabledList();
        ArrayNode node = p.getCodec().readTree(p);
        node.forEach(list::add);
        return list;
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext context, BeanProperty property) {
        return new InnerListDeserializer();
    }
}