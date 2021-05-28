package com.github.bfu4.hyperplay;

/**
 * HyperPlay is the main application instance and runs the application and it's managed processes.
 */
public final class HyperPlay {

    /**
     * Hide the constructor to avoid this class from being instantiated again.
     *
     * @throws RuntimeException     should not be instantiated outside of the program entry.
     */
    private HyperPlay() throws RuntimeException {
        throw new RuntimeException("HyperPlay cannot be instantiated!");
    }

    /**
     * Entry point.
     * @param args      program arguments
     */
    public static void main(final String[] args) {

    }

}
