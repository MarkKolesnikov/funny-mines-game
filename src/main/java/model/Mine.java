package model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class Mine {

    private final UUID id = UUID.randomUUID();
    private final String label;
    private final UUID ownerUserId;

    @Setter private Boolean triggered = false;
}
