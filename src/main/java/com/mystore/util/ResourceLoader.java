package com.mystore.util;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class ResourceLoader {
    public static InputStream getResourceAsStream(String path) {
        try {
            InputStream stream = ResourceLoader.class.getClassLoader().getResourceAsStream(path);
            if (Objects.nonNull(stream)) {
                return stream;
            }
            return Files.newInputStream(Path.of(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
