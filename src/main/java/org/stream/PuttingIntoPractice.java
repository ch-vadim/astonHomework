package org.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class PuttingIntoPractice {

    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        /*
         * 1. Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей к большей).
         */
        List<Transaction> sortedTransactionsFrom2011 = transactions.stream()
                .filter(it -> it.getYear()==2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .toList();
        System.out.println(sortedTransactionsFrom2011);

        /*
         * 2. Вывести список неповторяющихся городов, в которых работают трейдеры.
         */

        Set<String> uniqCities = transactions.stream()
                .map(it -> it.getTrader().getCity())
                .collect(Collectors.toSet());
        System.out.println(uniqCities);

        /*
         * 3. Найти всех трейдеров из Кембриджа и отсортировать их по именам.
         */
        List<Trader> tradersFromCambridge = transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .filter(it -> it.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println(tradersFromCambridge);

        /*
         * 4. Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном
         * порядке.
         */

        List<String> sortedTradersName = transactions.stream()
                .map(it -> it.getTrader().getName())
                .distinct()
                .sorted()
                .toList();
        System.out.println(sortedTradersName);

        /*
         * 5. Выяснить, существует ли хоть один трейдер из Милана.
         */
        boolean hasTraderFromMilan = transactions.stream()
                .anyMatch(it -> it.getTrader().getCity().equals("Milan"));
        System.out.println(hasTraderFromMilan);

        /*
         * 6. Вывести суммы всех транзакций трейдеров из Кембриджа.
         */
        //Integer overflow???
        int sumOfTransactionsByTradersFromCambridge = transactions.stream()
                .filter(it -> it.getTrader().getCity().equals("Cambridge"))
                .mapToInt(Transaction::getValue)
                .sum();
        System.out.println(sumOfTransactionsByTradersFromCambridge);

        /*
         * 7. Какова максимальная сумма среди всех транзакций?
         */

        int maxTransactionValue = transactions.stream()
                .mapToInt(Transaction::getValue)
                .max()
                .orElse(-1);
        System.out.println(maxTransactionValue);

        /*
         * 8. Найти транзакцию с минимальной суммой.
         */

        Transaction transactionWithMinValue = transactions.stream()
                .reduce((o1, o2) -> o1.getValue()< o2.getValue() ? o1 : o2)
                .get();
        System.out.println(transactionWithMinValue);
    }
}