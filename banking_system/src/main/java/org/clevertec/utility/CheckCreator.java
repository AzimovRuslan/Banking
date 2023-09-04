package org.clevertec.utility;

import org.clevertec.entity.DepWithTransaction;
import org.clevertec.entity.Transaction;
import org.clevertec.entity.TransferTransaction;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CheckCreator {

    public <T> void create(T transaction) throws IOException {
        String fileName = createFile((Transaction) transaction);

        try(FileWriter writer = new FileWriter("check/" + fileName)) {

            if (transaction instanceof DepWithTransaction) {
                writer.write(CheckFormat.formatForDepWithTransaction((DepWithTransaction) transaction));
            } else {
                writer.write(CheckFormat.formatForTransferTransaction((TransferTransaction) transaction));
            }

            writer.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public String createFile(Transaction transaction) throws IOException {

        String s = transaction.getTime().toString().replace('.', '=').replace(':', '-');
        File file = new File("check//" + s + ".txt");

        return file.getName();
    }
}
