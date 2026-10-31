package Activity18;

import java.io.IOException;
import java.util.List;

public class FileLogDestination implements LogDestination {

    private TransactionLog log;

    public FileLogDestination() {
        this.log = new TransactionLog();
    }

    @Override
    public void write(TransactionCommand cmd) {
        try {
            log.log(cmd);
        } catch (IOException e) {
            throw new RuntimeException("File logging failed", e);
        }
    }

    @Override
    public List<TransactionCommand> readAll() {
        try {
            return log.readAll();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("File reading failed", e);
        }
    }

    @Override
    public void clear() {
        log.clear();
    }

    @Override
    public String getDestinationName() {
        return "FILE";
    }
}