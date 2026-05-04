package model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class Mine {

    private final UUID id = UUID.randomUUID();
    private final String label;
    private final UUID ownerUserId;
    @Setter
    private boolean triggered = false;


    public Mine(UUID ownerUserId, String label) {
        this.ownerUserId = ownerUserId;
        this.label = label;
    }

}
