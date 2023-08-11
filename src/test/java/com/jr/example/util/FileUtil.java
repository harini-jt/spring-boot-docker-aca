package com.jr.example.util;

import static java.util.Objects.requireNonNull;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.SneakyThrows;

public class FileUtil {

    @SneakyThrows
    public static String readFile(String path) {
        return Files.readString(pathOf(path));
    }

    @SneakyThrows
    public static Path pathOf(String path) {
        return Paths.get(requireNonNull(FileUtil.class.getResource(path)).toURI());
    }
}
