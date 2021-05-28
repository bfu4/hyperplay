package com.github.bfu4.hyperplay.event;

import com.github.bfu4.hyperplay.interfaces.QuickSet;

import java.util.function.Consumer;

public class EventBus implements Bus {

    /**
     * Listeners subscribed to the event bus.
     */
    private final QuickSet<Listener<?>> listeners;

    /**
     * The consumer used for the event bus.
     */
    private final Consumer<Runnable> consumer;

    /**
     * Create an event bus with the provided consumer.
     * The consumer value typically should be {@link Runnable#run()}
     * @param consumer      consumer
     */
    public EventBus(final Consumer<Runnable> consumer) {
        this.consumer = consumer;
        this.listeners = new QuickSet<>();
    }

    /**
     * Subscribe a listener to the event bus.
     * @param listener      listener
     * @param <T>           event type constraints
     */
    @Override
    public <T extends Event> void subscribe(final Listener<T> listener) {

    }

    /**
     * Unsubscribe a listener from the event bus.
     * @param listener      listener
     * @param <T>           event type constraints
     */
    @Override
    public <T extends Event> void unsubscribe(final Listener<T> listener) {

    }

    /**
     * Dispatch an event from the bus.
     * @param event         event to dispatch
     */
    @Override
    public void dispatch(final Event event) {

    }

    /**
     * Skip the current event.
     */
    @Override
    public void skip() {

    }

}
