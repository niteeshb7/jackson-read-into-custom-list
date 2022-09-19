package com.example.testapp;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
public class TmpTest {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        final SimpleModule module = new SimpleModule();
        module.addDeserializer(List.class, new InnerListDeserializer());
        mapper.registerModule(module);

        Object object = mapper.readValue(new File("C:\\niteesh\\projects\\test-app\\src\\test\\java\\com\\example\\testapp\\sample.json"), Object.class);
        log.info("Object {}", object);
    }
}
