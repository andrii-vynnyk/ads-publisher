package ua.com.hedgehog.adspublisher.db;

public interface EntityDAO<Entity> {
    void insert(Entity entity);

    void update(Entity entity);

    void delete(int entityId);

    Entity find(int entityId);
}
