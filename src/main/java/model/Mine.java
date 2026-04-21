package model;

import java.util.UUID;

public class Mine {

    private final UUID id = UUID.randomUUID();
    private final String label;
    private final UUID ownerUserId;
    private boolean triggered = false;


    public Mine(UUID ownerUserId, String label) {
        this.ownerUserId = ownerUserId;
        this.label = label;
    }

    public UUID getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public UUID getOwnerUserId() {
        return ownerUserId;
    }

    public boolean isTriggered() {
        return triggered;
    }

    public void setTriggered(boolean triggered) {
        this.triggered = triggered;
    }
}
