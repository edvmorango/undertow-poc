package com.utils.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CompletablePromise<T> extends CompletableFuture<T> {

    private Future<T> future;


    public CompletablePromise(Future<T> future) {
        this.future = future;

        PromiseScheduler.schedule(this::fulfill);
    }

    private void fulfill() {

        if (future.isDone()) {
            try {
                complete(future.get());
            } catch (InterruptedException e) {
                completeExceptionally(e);
            } catch (ExecutionException e) {
                completeExceptionally(e.getCause());
            }
            return;
        }

        if (future.isCancelled()) {
            cancel(true);
            return;
        }

        PromiseScheduler.schedule(this::fulfill);

    }
}

