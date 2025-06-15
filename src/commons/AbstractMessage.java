package commons;

import java.io.Serial;
import java.io.Serializable;

public abstract class AbstractMessage implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    public String type;
    public Object content;
    public AbstractMessage(String type, Object content) {
        this.type = type;
        this.content = content;
    }

    public abstract AbstractMessage display();

    @Override
    public String toString() {
        return "Message{type='" + type + "', content='" + content + "'}";
    }
}
