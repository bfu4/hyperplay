package com.github.bfu4.hyperplay.event;

import com.github.bfu4.hyperplay.rest.JSON;
import com.github.bfu4.hyperplay.rest.JSONObject;

import javax.annotation.concurrent.Immutable;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;

/**
 * Modified off of <a href="https://github.com/team-catgirl/pounce/blob/main/src/main/java/team/catgirl/pounce/EventBus.java">pounce</a>.
 */
@Immutable
public class ListenerData {

    /**
     * The invocation method for {@link Listener listeners}.
     */
    public static final Method INVOKE_METHOD;

    static {
        // Temporary assignment
        Method tmpInvoke;
        try {
            tmpInvoke = Listener.class.getMethod("onEvent", Event.class);
        } catch (NoSuchMethodException e) {
            tmpInvoke = null;
        }
        INVOKE_METHOD = tmpInvoke;
        // Set accessible, if for some reason not accessible.
        INVOKE_METHOD.setAccessible(true);
    }

    /**
     * Type of event.
     */
    public final Class<? extends Event> eventType;

    /**
     * Json object of the event type.
     */
    public final JSONObject eventJson;

    /**
     * Weak reference of the initial listener to handle GC.
     */
    public final WeakReference<Listener<?>> self;

    /**
     * Create new listener meta data.
     * @param listener      listener to associate with
     * @param eventType     type of event
     */
    public ListenerData(final Listener<?> listener, final Class<? extends Event> eventType) {
        this.eventType = eventType;
        this.self = new WeakReference<>(listener);
        this.eventJson = new JSONObject("event", eventType);
    }

    /**
     * Serialize the data into a JSON string.
     * @return  json string
     */
    @Override
    public String toString() {
        return JSON.stringify(
                "listener",
                eventJson,
                new JSONObject("reference", self)
        );
    }

}
