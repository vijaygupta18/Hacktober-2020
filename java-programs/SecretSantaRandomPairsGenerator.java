import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class SecretSantaRandomPairsGenerator {

  private static final List<String> NAMES = Arrays.asList("Olga", "Sam", "Isaak", "Gunter");
  private static final Random RANDOM = new Random();

  public static void main(String[] args) {
    List<String> namesToProcess = (args == null || args.length == 0) ? NAMES : Arrays.asList(args);
    List<String> giftTakerPairs = new ArrayList<>();
    Set<String> giftTakers = new HashSet<>();


    namesToProcess.forEach(santa -> {
      List<String> giftCounterParts = new ArrayList<>(namesToProcess);
      giftCounterParts.remove(santa);
      giftCounterParts.removeAll(giftTakers);
      String taker = giftCounterParts.get(RANDOM.nextInt(giftCounterParts.size()));
      String pair = santa + " prepares a gift for " + taker;
      giftTakerPairs.add(pair);

      giftTakers.add(taker);
    });

    System.out.println("==================================");
    System.out.println("Behold! Fate decided the following: ");
    System.out.println("==================================");
    System.out.println();
    giftTakerPairs.forEach(System.out::println);
    System.out.println();
    System.out.println("==================================");
    System.out.println("Happy NY, kids! Ho-ho-ho!");
    System.out.println("==================================");
  }
}