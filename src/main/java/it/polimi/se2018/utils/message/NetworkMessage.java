package it.polimi.se2018.utils.message;

import java.util.Map;

public class NetworkMessage extends Message {

    public NetworkMessage(Enum type) {
        super(type);
    }

    public NetworkMessage(Enum type, Map<String, Object> params) {
        super(type, params);
    }

    /**
     * Enum for all types of NetworkMessage instances
     */
    public enum types {
        CONNECTED,
        REFUSED
    }
}
