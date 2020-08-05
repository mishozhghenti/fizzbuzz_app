package com.example.fizzbuzz.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Utils {
    private static final ObjectMapper jsonMapper = createMapper();


    private static ObjectMapper createMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JodaModule());
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

    public static <T> List<T> fromFileToList(String fileName, TypeReference<List<T>> typeReference) {
        try {
            File f = new File(fileName);
            return jsonMapper.readValue(f, typeReference);
        } catch (IOException e) {
            throw new IllegalStateException("Can't convert String to Object", e);
        }
    }

    public static int module(int number, int base) {
        if (number < base) {
            return number;
        } else if (number > base) {
            return module(number + ~base + 1, base);
        } else {
            return 0;
        }
    }
}
