package it.unibo.mvc.impl;

import java.io.File;
import java.io.IOException;

public interface SimpleController {
    public void setFile(File f);

    public File getFile();

    public String getPath();

    public void printString(String s) throws IOException;
}
