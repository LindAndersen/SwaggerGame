import dk.sdu.smp4.common.events.services.IEventBus;
import dk.sdu.smp4.events.EventBus;

module Events {
    requires CommonEvents;
    provides IEventBus with EventBus;
}