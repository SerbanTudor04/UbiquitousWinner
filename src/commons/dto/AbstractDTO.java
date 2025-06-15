package commons.dto;

import commons.FileDestionations;

import java.io.FileNotFoundException;

public abstract class AbstractDTO {
    public void load(FileDestionations destination, Object source) throws UnsupportedOperationException, FileNotFoundException {
        if(destination==FileDestionations.FILE_SYSTEM){
            loadFS(source);
            return;
        }else if(FileDestionations.DATABASE==destination){
            loadDB(source);
            return;
        }
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected abstract void loadFS(Object source) throws UnsupportedOperationException, FileNotFoundException;
    protected abstract void loadDB(Object source) throws UnsupportedOperationException;
}
