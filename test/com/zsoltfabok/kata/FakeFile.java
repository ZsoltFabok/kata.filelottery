package com.zsoltfabok.kata;

import java.io.File;

class FakeFile extends File {
    private static final long serialVersionUID = 1L;
    private final File[] children;
    private boolean isDirectory;

    public FakeFile(String filename) {
        super(filename);
        isDirectory = false;
        children = new File[]{};
    }

    public FakeFile(File[] children) {
        super("test directory");
        isDirectory = true;
        this.children = children;
    }

    public File[] listFiles() {
        return children;
    }

    public boolean isDirectory() {
        return isDirectory;
    }
}
