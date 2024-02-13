package gr.codehub.j101.p02collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordCountDemo {
    private static final Logger logger = LoggerFactory.getLogger(WordCountDemo.class);

    public static void main(String[] args) {
        String text = "a person and a dog are good friends "+
                "just like a dog and a person are also very "+
                "good friends because a dog is always a great animal";
        String[] words = text.split("\\s+");
        logger.info("{}", (Object) words);
        System.out.println(words.length);
        for (String word: words)
            System.out.println(word);

        Map<String, Integer> map = new HashMap<>();
        for (String word: words) {
            map.merge(word, 1, Integer::sum);
        }
        logger.info("{}", map);
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
		// list.sort(Map.Entry.<String, Integer>comparingByValue().reversed()); // does the same as the two lines below
		list.sort(Map.Entry.comparingByValue());
		list = list.reversed();
        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
