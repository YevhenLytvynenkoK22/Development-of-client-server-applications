import java.util.ArrayList;
import java.util.HashSet;

public class ChatFilter {
    public static void main(String[] args) {
        // Список повідомлень
        ArrayList<String> messages = new ArrayList<>();
        messages.add("Привіт всім!");
        messages.add("Це погане слово тут: xyz");
        messages.add("Все добре, продовжуємо чат");
        messages.add("Ще одне заборонене слово: abc");

        // HashSet заборонених слів
        HashSet<String> bannedWords = new HashSet<>();
        bannedWords.add("xyz");
        bannedWords.add("abc");
        bannedWords.add("123"); // приклад

        // Список повідомлень для модерації
        ArrayList<String> flaggedMessages = new ArrayList<>();

        // Перевірка кожного повідомлення
        for (String message : messages) {
            for (String word : bannedWords) {
                if (message.toLowerCase().contains(word.toLowerCase())) {
                    flaggedMessages.add(message);
                    break; // якщо знайдено заборонене слово, переходимо до наступного повідомлення
                }
            }
        }

        // Вивід результатів
        System.out.println("Повідомлення, що потребують модерації:");
        for (String flagged : flaggedMessages) {
            System.out.println(flagged);
        }
    }
}
