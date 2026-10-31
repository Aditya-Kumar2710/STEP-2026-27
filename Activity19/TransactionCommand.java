package Activity19;
import java.io.Serializable;

public interface TransactionCommand extends Serializable {
    void execute() throws Exception;

    Transaction getTransaction();
}