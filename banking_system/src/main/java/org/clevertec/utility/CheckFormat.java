package org.clevertec.utility;

import org.clevertec.entity.DepWithTransaction;
import org.clevertec.entity.TransferTransaction;

public class CheckFormat {

    public static String formatForDepWithTransaction(DepWithTransaction depWithTransaction) {
        return "\t\tБанковский чек\t\t\n" +
                depWithTransaction.getDate() + "\t\t\t" + depWithTransaction.getTime() + "\n" +
                "Тип транзакции:\t\t\t" + depWithTransaction.getType() + "\n" +
                "Счет получателя:\t\t" + depWithTransaction.getAccount().getNumber() + "\n" +
                "Банк получателя:\t\t" + depWithTransaction.getAccount().getBank().getName() + "\n" +
                "Сумма:\t\t\t\t" + depWithTransaction.getAmount();
    }

    public static String formatForTransferTransaction(TransferTransaction transferTransaction) {
        return "\t\tБанковский чек\t\t\n" +
                transferTransaction.getDate() + "\t\t\t" + transferTransaction.getTime() + "\n" +
                "Тип транзакции:\t\t\t" + transferTransaction.getType() + "\n" +
                "Счет отправителя:\t\t" + transferTransaction.getAccountOfSender().getNumber() + "\n" +
                "Счет получателя:\t\t" + transferTransaction.getAccountOfGetter().getNumber() + "\n" +
                "Банк отправителя:\t\t" + transferTransaction.getAccountOfSender().getBank().getName() + "\n" +
                "Банк получателя:\t\t" + transferTransaction.getAccountOfGetter().getBank().getName() + "\n" +
                "Сумма:\t\t\t\t" + transferTransaction.getAmount();
    }
}
