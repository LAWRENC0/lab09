package it.unibo.mvc;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.controller.DrawNumberControllerImpl;
import it.unibo.mvc.model.DrawNumberImpl;
import it.unibo.mvc.view.DrawNumberStdoutView;
import it.unibo.mvc.view.DrawNumberSwingView;

/**
 * Application entry-point.
 */
public final class LaunchApp {

    private LaunchApp() { }

    /**
     * Runs the application.
     *
     * @param args ignored
     * @throws ClassNotFoundException if the fetches class does not exist
     * @throws SecurityException
     * @throws NoSuchMethodException if the 0-ary constructor do not exist
     * @throws InvocationTargetException if the constructor throws exceptions
     * @throws InstantiationException if the constructor throws exceptions
     * @throws IllegalAccessException in case of reflection issues
     * @throws IllegalArgumentException in case of reflection issues
     */
    public static void main(final String... args) throws 
        ClassNotFoundException,
        NoSuchMethodException,
        SecurityException,
        InstantiationException,
        IllegalAccessException,
        IllegalArgumentException,
        InvocationTargetException {
        final var model = new DrawNumberImpl();
        final DrawNumberController app = new DrawNumberControllerImpl(model);
        final Class<?> c1 = Class.forName("it.unibo.mvc.view.DrawNumberSwingView");
        final Class<?> c2 = Class.forName("it.unibo.mvc.view.DrawNumberStdoutView");
        final Constructor<?> cns1 = c1.getConstructor();
        final Constructor<?> cns2 = c2.getConstructor();
        for(int i=0; i<3; i++){
            app.addView((DrawNumberView)cns1.newInstance());
            app.addView((DrawNumberView)cns2.newInstance());
        }
    }
}
