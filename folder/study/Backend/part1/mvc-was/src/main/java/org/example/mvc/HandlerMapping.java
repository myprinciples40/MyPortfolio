package org.example.mvc;

public interface HandlerMapping {
    // Changed from controller type to object type: to receive annotations, not just controller interface.
    Object findHandler(HandlerKey handlerKey);
}
