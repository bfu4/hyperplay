package com.github.bfu4.hyperplay.rest;

import com.google.gson.Gson;

public abstract class JSON {

    /**
     * Restrict instantiation.
     */
    private JSON() { }

    /**
     * Used for parsing.
     */
    private static final Gson GSON = new Gson();

    /**
     * Stringify a singular object.
     * @param o     object to stringify
     * @return      stringified object
     */
    public static String stringify(final JSONObject o) {
        return "{" + stringify(o.key, o.value) + "}";
    }

    public static String stringify(final Object o) {
        return "{" + stringify(o.getClass().getSimpleName(), o.toString()) + "}";
    }

    /**
     * Stringify an array.
     * @param key   key
     * @param o     array to stringify
     * @return      stringified array
     */
    public static String stringify(final String key, final JSONObject... o) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < o.length; i++) {
            if (o[i] != null) {
                builder.append(
                        stringify(o[i].key, o[i].value)
                );
                if (i != o.length - 1) {
                    builder.append(",");
                }
            }
        }
        return "{" + stringify(key, "{" + builder + "}") + "}";
    }

    /**
     * Stringify an object.
     * @param key   key
     * @param o     object
     * @return      stringified object
     */
    public static String stringify(final String key, final Object o) {
        return String.format("\"%s\":\"%s\"", key, o.toString());
    }

    /***
     * Parse a value to a specified type.
     * @param value     value
     * @param type      type
     * @param <T>       type constraint
     * @return          parsed value
     */
    public static <T> T parse(final String value, final Class<T> type) {
        return GSON.fromJson(value, type);
    }

}
