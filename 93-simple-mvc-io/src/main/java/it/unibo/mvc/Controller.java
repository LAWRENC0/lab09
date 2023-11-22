package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import it.unibo.mvc.impl.SimpleController;

/**
 * Application controller. Performs the I/O.
 */
public class Controller implements SimpleController{
    private static final String DEFAULT_PATH = System.getProperty("user.home")
            + File.separator
            + "output.txt";
    private static final File DEFAULT_FILE = new File(DEFAULT_PATH);
    private File currentFile;

    public Controller (){
        this.currentFile = DEFAULT_FILE;
    }

    @Override
    public void setFile(File f) {
        this.currentFile = f;
    }

    @Override
    public File getFile() {
        return this.currentFile;
    }

    @Override
    public String getPath() {
        return this.currentFile.getPath();
    }

    @Override
    public void printString(String s) throws IOException {
        try (PrintStream ps = new PrintStream(this.currentFile.getAbsolutePath(), StandardCharsets.UTF_8)) {
            ps.print(s);
        } catch (IOException e1) {
            System.out.println("In CONTROLLER method printString caught IOEXCEPTION");
        }
    }

}
