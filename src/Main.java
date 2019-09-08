import java.util.*;

public class Main {

    public static void main(String[] args) {

        List<String> list = new ArrayList<String>() {{
            add("hot");
            add("dot");
            add("dog");
            add("lot");
            add("log");
            add("cog");
        }};

        List<List<String>> res = findLadders("hit", "cog", list);
        System.out.println("Done!");

    }


    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> words = new HashSet<>();
        HashSet<String> used = new HashSet<>();
        Queue<LinkedList<String>> q = new LinkedList<>();
        List<List<String>> result = new ArrayList<>();
        boolean found = false;

        for (String word : wordList) words.add(word);

        LinkedList<String> first = new LinkedList<>();
        first.add(beginWord);
        q.offer(first);

        //same as reached in WORD LADDER 1
        used.add(beginWord);

        while (!q.isEmpty()) {
            int size = q.size();
            HashSet<String> localUsed = new HashSet<>();
            while (size > 0) {
                LinkedList<String> curr = q.poll();
                char[] word = curr.getLast().toCharArray();
                for (int i = 0; i < word.length; i++) {
                    char temp = word[i];
                    for (int j = 'a'; j <= 'z'; j++) {
                        word[i] = (char) j;
                        String s = String.valueOf(word);
                        if (!used.contains(s) && words.contains(s)) {
                            LinkedList<String> list = new LinkedList<>(curr);
                            list.add(s);
                            if (s.equals(endWord)) {
                                found = true;
                                result.add(list);
                                continue;
                            }
                            localUsed.add(s);
                            q.offer(list);
                        }
                    }
                    word[i] = temp;
                }
                size--;
            }
            for (String s : localUsed) used.add(s);

            if (found) break;
        }
        
        return result;

    }

}