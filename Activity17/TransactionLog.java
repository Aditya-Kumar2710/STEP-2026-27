package Activity17;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionLog {
    private File file;

    public TransactionLog() {
        this("data/transactions.ser");
    }

    public TransactionLog(String path) {
        file = new File(path);
    }

    public void log(TransactionCommand command) throws IOException {
        File parent = file.getParentFile();

        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        boolean append = file.exists() && file.length() > 0;

        try (ObjectOutputStream out = append
                ? new AppendableObjectOutputStream(
                    new FileOutputStream(file, true))
                : new ObjectOutputStream(
                    new FileOutputStream(file, true))) {

            out.writeObject(command);
        }
    }

    public List<TransactionCommand> readAll()
            throws IOException, ClassNotFoundException {

        List<TransactionCommand> commands = new ArrayList<>();

        if (!file.exists() || file.length() == 0) {
            return commands;
        }

        try (ObjectInputStream in =
                new ObjectInputStream(new FileInputStream(file))) {

            while (true) {
                try {
                    TransactionCommand command =
                        (TransactionCommand) in.readObject();

                    commands.add(command);
                } catch (EOFException e) {
                    break;
                }
            }
        }

        return commands;
    }

    public void clear() throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException("Could not delete log file");
        }
    }

    private static class AppendableObjectOutputStream
            extends ObjectOutputStream {

        public AppendableObjectOutputStream(OutputStream out)
                throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
            reset();
        }
    }
}