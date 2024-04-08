import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 전화번호 목록 - 분
public class BOJ_5052 {
    static StringBuilder sb = new StringBuilder();
    static class TrieNode {
        Map<Character, TrieNode> childNode = new HashMap<>();
        boolean endOfWord;

        public void insert(String word) {
            TrieNode trieNode = this;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);

                trieNode.childNode.putIfAbsent(ch, new TrieNode());
                trieNode = trieNode.childNode.get(ch);

                if (i == word.length()-1) {
                    trieNode.endOfWord = true;
                    return;
                }
            }
        }

        public boolean equals(String word) {
            TrieNode trieNode = this;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                TrieNode node = trieNode.childNode.get(ch);
                if (node == null) {
                    return false;
                }
                trieNode = node;
            }
            return trieNode.endOfWord;
        }

        public boolean contains(String word) {
            TrieNode trieNode = this;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                TrieNode node = trieNode.childNode.get(ch);
                if (node == null) {
                    return false;
                }
                trieNode = node;
            }

            return true;
        }
    }
    public static void main(String[] args) throws Exception {
        init();
        System.out.println(sb.toString());
    }

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            String[] numbers = new String[N];
            for (int i = 0; i < N; i++) {
                numbers[i] = br.readLine();
            }

            Arrays.sort(numbers, new Comparator<String>() {
                public int compare(String o1, String o2) {
                    return o2.compareTo(o1);
                }
            });

            String answer = "YES";
            TrieNode trie = new TrieNode();
            for (int i = 0; i < N; i++) {
                if (trie.contains(numbers[i])) {
                    answer = "NO";
                    break;
                }
                trie.insert(numbers[i]);
            }

            sb.append(answer).append("\n");
        }
    }
}