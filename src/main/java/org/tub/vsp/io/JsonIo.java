package org.tub.vsp.io;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonIo {
    Gson gson;

    public JsonIo(Gson gson) {
        this.gson = gson;
    }

    public void writeJson(Object object, String filePath) throws IOException {
        //make sure that the directory exists
        Path path = Paths.get(filePath);
        Files.createDirectories(path.getParent());

        Writer writer = Files.newBufferedWriter(path);
        gson.toJson(object, writer);
        writer.flush();
        writer.close();
    }

    public <T> T readJson(String filePath, Class<T> classOfT) throws IOException {
        return gson.fromJson(Files.newBufferedReader(Paths.get(filePath)), classOfT);
    }
}
