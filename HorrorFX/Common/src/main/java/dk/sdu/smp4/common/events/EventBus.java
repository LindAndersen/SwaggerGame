package dk.sdu.smp4.common.events;

import java.util.*;

public class EventBus {
    private static final Map<Class<?>, List<EventListener<?>>> listeners = new HashMap<>();

    public static <T> void subscribe(Class<T> eventType, EventListener<T> listener) {
        listeners.computeIfAbsent(eventType, k -> new ArrayList<>()).add(listener);
    }

    public static <T> void post(T event) {
        List<EventListener<?>> eventListeners = listeners.getOrDefault(event.getClass(), Collections.emptyList());
        for (EventListener<?> listener : eventListeners) {
            ((EventListener<T>) listener).onEvent(event);
        }
    }

    public interface EventListener<T> {
        void onEvent(T event);
    }
}