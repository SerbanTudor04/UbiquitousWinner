package commons;

import java.io.Serializable;

public class TableResponseMessage<T> extends AbstractMessage {

    public TableResponseMessage(String type, Table<?> content) {
        super(type, content);
    }

    @Override
    public TableResponseMessage<T> display() {
        System.out.println(type);
        if(content == null){
            return this;
        }
        Table<?> t = (Table<?>) content;
        t.display();
        return this;
    }
}
