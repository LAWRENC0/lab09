package it.unibo.mvc;

import java.util.List;
import java.util.LinkedList;
import java.util.Objects;

/**
 * 
 *
 */
public final class SimpleController implements Controller {
    private final List<String> stringHistory = new LinkedList<>();

    @Override
    public void setNextString(String s) {
        this.stringHistory.add(Objects.requireNonNull(s));
    }

    @Override
    public String getNextString() {
        if(this.stringHistory.size() == 0){
            throw new IllegalStateException();
        }
        return this.stringHistory.get(stringHistory.size() - 1);
    }

    @Override
    public List<String> getStringHistory() {
        return new LinkedList<>(this.stringHistory);
    }

    @Override
    public void printCurrentString() {
        System.out.println(getNextString());
    }

}
