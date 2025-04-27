package dk.sdu.smp4.events;

import dk.sdu.smp4.common.events.services.IEventBus;

import java.util.*;

public class EventBus implements IEventBus {
    private static final Map<Class<?>, List<IEventBus.EventListener<?>>> listeners = new HashMap<>();

    @Override
    public <T> void subscribe(Class<T> eventType, IEventBus.EventListener<T> listener) {
        listeners.computeIfAbsent(eventType, k -> new ArrayList<>()).add(listener);
    }

    @Override
    public <T> void post(T event) {
        List<IEventBus.EventListener<?>> eventListeners = listeners.getOrDefault(event.getClass(), Collections.emptyList());
        for (IEventBus.EventListener<?> listener : eventListeners) {
            ((IEventBus.EventListener<T>) listener).onEvent(event);
        }
    }
}