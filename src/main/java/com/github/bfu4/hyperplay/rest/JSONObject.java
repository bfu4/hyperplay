package com.github.bfu4.hyperplay.rest;

import javax.annotation.concurrent.Immutable;

/**
 * A simple key/value pairing object, is {@link com.google.gson.Gson} compatible.
 */
@Immutable
public class JSONObject {

    /**
     * Object key.
     */
    public String key;

    /**
     * Object value.
     */
    public Object value;

    /**
     * Create a key, value JSONObject, to be used for serialization in {@link JSON}.
     * @param key       key
     * @param value     value
     */
    public JSONObject(final String key, final Object value) {
        this.key = key;
        this.value = value;
    }

}
