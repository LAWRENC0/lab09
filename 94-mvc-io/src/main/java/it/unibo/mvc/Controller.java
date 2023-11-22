package it.unibo.mvc;

import java.util.List;

/**
 *
 */
public interface Controller {
    public void setNextString(String s);

    public String getNextString();

    public List<String> getStringHistory();

    public void printCurrentString();
}
