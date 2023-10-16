package entity;

public abstract class BaseEntity {

    String id;

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id='" + id + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
