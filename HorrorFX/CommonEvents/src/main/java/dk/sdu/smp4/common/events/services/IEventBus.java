package dk.sdu.smp4.common.events.services;

public interface IEventBus {
    <T> void subscribe(Class<T> eventType, EventListener<T> listener);
    <T> void post(T event);

    interface EventListener<T> {
        void onEvent(T event);
    }
}