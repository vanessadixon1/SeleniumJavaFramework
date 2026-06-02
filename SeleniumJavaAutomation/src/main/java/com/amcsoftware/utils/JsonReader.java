package com.amcsoftware.utils;

import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class JsonReader {

    public static <R, T> Object[][] provide(String filePath, Class<R> rootType, Function<R, List<T>> extractor) {
        R root = parse(filePath, rootType);
        List<T> items = extractor.apply(root);

        Object[][] data = new Object[items.size()][1];
        for (int i = 0; i < items.size(); i++) {
            data[i][0] = items.get(i);
        }
        return data;
    }

    private static <R> R parse(String filePath, Class<R> type) {
        try (InputStreamReader reader = new InputStreamReader(
                Objects.requireNonNull(
                        JsonReader.class.getClassLoader().getResourceAsStream(filePath),
                        "Test data file not found: " + filePath
                )
        )) {
            return new Gson().fromJson(reader, type);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load test data from " + filePath, e);
        }
    }
}
