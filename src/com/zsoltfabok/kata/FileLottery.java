package com.zsoltfabok.kata;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FileLottery {

    private final File directory;
    private List<Integer> indexes;
    private final Random random;

    public FileLottery(File directory, Random random) {
        this.directory = directory;
        this.random = random;

        initializeIndexCache();
    }

    private void initializeIndexCache() {
        indexes = new ArrayList<Integer>();
        for (int i = 0; i < directory.listFiles().length; i++) {
            indexes.add(i);
        }
    }

    public String nextRandomPath() {
        if (directory.isDirectory()) {
            if (directory.listFiles().length != 0) {
                reInitializeCacheWhenEverthingHasBeenReturned();
                return getNextRandomFilename();
            } else {
                return "";
            }
        } else {
            return directory.getName();
        }
    }

    private String getNextRandomFilename() {
        Integer index = indexes.remove(random.nextInt(indexes.size()));
        return directory.listFiles()[index].getName();
    }

    private void reInitializeCacheWhenEverthingHasBeenReturned() {
        if (indexes.isEmpty()) {
            initializeIndexCache();
        }
    }

}
