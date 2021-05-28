package com.github.bfu4.hyperplay.event;

/**
 * Base abstraction of a listener.
 * @param <T>       type constraints for event
 */
@FunctionalInterface
public interface Listener<T extends Event> {

    /**
     * What happens on event.
     * @param event event
     */
    void onEvent(T event);

}
