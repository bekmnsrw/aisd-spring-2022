public class LevenshteinDistance {
    public static int levDist(String s1, String s2) {
        int[][] distance = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i < s1.length() + 1; i++) {
            distance[i][0] = i;
        }

        for (int j = 0; j < s2.length() + 1; j++) {
            distance[0][j] = j;
        }

        for (int i = 1; i < s1.length() + 1; i++) {
            for (int j = 1; j < s2.length() + 1; j++) {
                int cost = s1.charAt(i - 1) == s2.charAt(j - 1) ? 0 : 1;
                distance[i][j] = (Math.min(Math.min(
                        distance[i - 1][j] + 1,        // удаление
                        distance[i][j - 1] + 1),       // вставка
                        distance[i - 1][j - 1] + cost  // замена
                ));
            }
        }

        return distance[s1.length()][s2.length()];
    }
}