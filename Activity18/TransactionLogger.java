package Activity18;

import java.util.List;

public class TransactionLogger {

    protected LogDestination destination;

    public TransactionLogger(LogDestination destination) {
        if (destination == null) {
            throw new IllegalArgumentException(
                "Destination cannot be null"
            );
        }

        this.destination = destination;
    }

    public void setDestination(LogDestination destination) {
        if (destination == null) {
            throw new IllegalArgumentException(
                "Destination cannot be null"
            );
        }

        this.destination = destination;
    }

    public void log(TransactionCommand cmd) {
        destination.write(cmd);
    }

    public List<TransactionCommand> readAll() {
        return destination.readAll();
    }

    public void clear() {
        destination.clear();
    }

    public String getDestinationName() {
        return destination.getDestinationName();
    }
}