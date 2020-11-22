package controllers;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AsyncListenerClass implements AsyncListener {
    @Override
    public void onComplete(AsyncEvent asyncEvent) throws IOException {
      Logger.getLogger("ASYNC_LISTENER").log(Level.WARNING, "QQQ");
    }

    @Override
    public void onTimeout(AsyncEvent asyncEvent) throws IOException {

    }

    @Override
    public void onError(AsyncEvent asyncEvent) throws IOException {

    }

    @Override
    public void onStartAsync(AsyncEvent asyncEvent) throws IOException {

    }
}
