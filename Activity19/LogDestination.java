package Activity19;
import java.util.List;

public interface LogDestination {

    void write(TransactionCommand cmd);

    List<TransactionCommand> readAll();

    void clear();

    String getDestinationName();
}