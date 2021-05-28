package com.github.bfu4.hyperplay.event;

public interface Bus {

    <T extends Event> void subscribe(Listener<T> listener);

    <T extends Event> void unsubscribe(Listener<T> listener);

    void dispatch(Event event);

    void skip();

}
