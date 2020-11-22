package controllers;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AsyncListenerClass implements AsyncListener {
    @Override
    public void onComplete(AsyncEvent asyncEvent) throws IOException {
//      Logger.getLogger("ASYNC_LISTENER_COMPLETE").log(Level.WARNING, "Timeout setting: " + asyncEvent.getAsyncContext().getTimeout());
      asyncEvent.getAsyncContext().getResponse().getWriter().write("\nfrom AsyncListenerClass#onComplete\n");
    }

    @Override
    public void onTimeout(AsyncEvent asyncEvent) throws IllegalStateException, IOException {
        String exceptionMsg = "Timeout " + asyncEvent.getAsyncContext().getTimeout() + " exceed...";

        Logger.getLogger("ASYNC_LISTENER_TIMEOUT").log(Level.WARNING, exceptionMsg);
        asyncEvent.getAsyncContext().getResponse().getWriter().write(exceptionMsg);
//        throw new IllegalStateException(exceptionMsg);
    }

    @Override
    public void onError(AsyncEvent asyncEvent) throws IOException {

    }

    @Override
    public void onStartAsync(AsyncEvent asyncEvent) throws IOException {

    }
}
