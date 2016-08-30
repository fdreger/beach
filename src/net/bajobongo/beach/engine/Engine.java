package net.bajobongo.beach.engine;

import net.bajobongo.beach.util.TriConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Engine<Entity> {

    private final List<Entity> entities = new ArrayList<Entity>();

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        entities.add(entity);
    }

    public void forEachEntity(Predicate<Entity> predicate, Consumer<Entity> consumer) {
        entities.stream().filter(predicate).forEach(consumer);
    }

    public <T> void forEach(ComponentMapper<T, Entity> predicate, Consumer<T> consumer) {
        entities.stream().filter(predicate).forEach((e) -> consumer.accept(predicate.getFrom(e)));
    }

    public <T, Y> void forEach(ComponentMapper<T, Entity> predicateA, ComponentMapper<Y, Entity> predicateB, BiConsumer<T, Y> consumer) {
        entities.stream().filter(predicateA).filter(predicateB).forEach((e) -> consumer.accept(predicateA.getFrom(e), predicateB.getFrom(e)));
    }

    public <T, Y, U> void forEach(ComponentMapper<T, Entity> predicateA, ComponentMapper<Y, Entity> predicateB, ComponentMapper<U, Entity> predicateC, TriConsumer<T, Y, U> consumer) {
        entities.stream().filter(predicateA).filter(predicateB).filter(predicateC).forEach((e) -> consumer.accept(predicateA.getFrom(e), predicateB.getFrom(e), predicateC.getFrom(e)));
    }

    public <T> Entity findSingleEntity(ComponentMapper<T, Entity> predicateA) {
        return entities.stream().findFirst().orElse(null);
    }

    public long count(Predicate<Entity> predicate) {
        return entities.stream().filter(predicate).count();
    }
}
