import java.util.*;

class PGS_42890 {
    static String[][] database;
    static int rowSize;
    static int colSize;
    static List<Integer> candidateSets;

    boolean isUnique(int set) {
        String[] keys = makeKeys(set);

        Set<String> keySet = new HashSet<>();
        for (String key : keys) {
            if (keySet.contains(key)) {
                return false;
            }
            keySet.add(key);
        }

        return true;
    }

    String[] makeKeys(int set) {
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < colSize; i++) {
            if (((set >> i) & 1) == 1) {
                indexes.add(i);
            }
        }

        String[] keys = new String[rowSize];
        for (int i = 0; i < rowSize; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < indexes.size(); j++) {
                stringBuilder.append(database[i][indexes.get(j)]);
            }
            keys[i] = stringBuilder.toString();
        }

        return keys;
    }

    boolean isMinimum(int set) {
        for (int candidateSet : candidateSets) {
            if ((candidateSet & set) == candidateSet) {
                return false;
            }
        }

        return true;
    }

    public int solution(String[][] relation) {
        rowSize = relation.length;
        colSize = relation[0].length;
        database = relation;
        candidateSets = new ArrayList<>();

        for (int i = 0; i < (1 << colSize); i++) {
            if (!isUnique(i)) {
                continue;
            }

            if (!isMinimum(i)) {
                continue;
            }

            candidateSets.add(i);
        }

        return candidateSets.size();
    }
}