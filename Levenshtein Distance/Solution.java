import java.util.*;

public class Solution {
    public static void main(String[] args) {

        // TODO: проверка орфографии: предложить пользователю один из наиболее подходящих вариантов для
        //  исправления допущенных в веденной фразе орфографических ошибок (если они имеются)

        /**
         * На вход подается введенная пользователем фраза 'target' (возможно, с орфографическими ошибками:
         * например, пропущен символ(-ы), допущена опечатка(-и)). С помощью расстояния Левенштейна 'levDist'
         * и заранее заданного словаря 'dictionary' (фраз без ошибок, которые в теории мог ввести пользователь)
         * требуется определить наиболее подходящую фразу, которую хотел ввести пользователь, и предложить ему вариант исправления.
         */

        System.out.println(spellCheck("превед")); // в слове допущены орфографические ошибки => предложить вариант исправления
        System.out.println(spellCheck("привет")); // слово введено без ошибок
    }

    public static String spellCheck(String target) {
        List<String> dictionary = new ArrayList<>();
        dictionary.add("привет");
        dictionary.add("приветствую");
        dictionary.add("приветик");

        Map<String, Double> probabilities = new HashMap<>();
        double maxProbability = 0;
        String mostSuitable = null;

        dictionary.forEach(x -> probabilities.put(x,
                1 - (double)LevenshteinDistance.levDist(x, target) / Math.max(x.length(), target.length())));

        for (String s : probabilities.keySet()) {
            if (probabilities.get(s) == 1) {
                return "В веденной фразе ошибок нет";
            }
            if (probabilities.get(s) > maxProbability) {
                maxProbability = probabilities.get(s);
                mostSuitable = s;
            }
        }

        return "Возможно, вы хотели ввести '" + mostSuitable + "' вместо '" + target + "'";
    }
}