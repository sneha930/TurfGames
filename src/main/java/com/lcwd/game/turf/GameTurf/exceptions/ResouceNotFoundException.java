package com.lcwd.game.turf.GameTurf.exceptions;

public class ResouceNotFoundException extends RuntimeException{

    public ResouceNotFoundException(String message) {
        super(message);
    }

    public ResouceNotFoundException() {
        super("Resource not found");
    }
}
