package ru.job4j.gc.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        try (BufferedReader br = new BufferedReader(new FileReader(cachingDir + key))) {
            List<String> str = new ArrayList<>();
            br.lines().forEach(str::add);
            put(key, new String(str.toString()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return get(key);
    }

}
