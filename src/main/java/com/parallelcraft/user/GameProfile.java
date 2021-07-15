package com.parallelcraft.user;

import com.parallelcraft.util.Property;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Class for a User
 *
 * @author extremeCrazyCoder
 */
public class GameProfile {

    public static GameProfile create(UUID pUUID, String pUsername) {
        return new GameProfile(pUUID, pUsername);
    }

    private final UUID uuid;
    private String username;

    private GameProfile(UUID pUUID, String pUsername) {
        uuid = pUUID;
        username = pUsername;
    }

    public UUID getUUID() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }
    
    public List<Property> getPropertiesAsList() {
        return new ArrayList<Property>();
    }
    
    @Override
    public String toString() {
        return "GameProfile["
                + "uuid='" + uuid.toString() + "', "
                + "username='" + username + "']";
    }
}
