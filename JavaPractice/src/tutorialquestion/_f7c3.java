package tutorialquestion;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// f7c3: Pig Latin

/*
    An English word is translated into Pig Latin as follows:
    if the word begins with a consonant, this is moved to the end of the word and "ay" is appended to the word.
    Otherwise the word begins with a vowel, in which case "way" is simply added to the word. Letters are re-capitalized
    appropriately to stick to standard English language rules, and punctuation, numbers and whitespace are left intact.
    For instance, the following sentence:

    How are you on January 1st? I am fine, thanks.

    becomes:

    Owhay areway ouyay onway Anuaryjay 1st? Iway amway inefay, hankstay.

    This is actually a slight simplification of the true rules of Pig Latin: in practice, "consonant clusters",
    not just the leading consonant, should be moved to the end of a word, so that, e.g.,
    "question" becomes "estionquay". We will ignore this complexity, so that "question" is translated to "uestionqay".

    Write a Java program that reads text from standard input and translates it into Pig Latin.
    Reading from standard input can be performed as in question 2d33.
    Translating a single word into Pig Latin is quite easy.
    The challenge in this question is in how to decompose lines of input into individual words.
 */

public class _f7c3 {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String sentence = br.readLine();

        final List<String> words = new ArrayList<>(Arrays.asList(sentence.split("(?<!^)\\b")));
        final List<String> pigLatins = new ArrayList<>();
        for (int i = 0; i < words.size(); i++) {
            var w = words.get(i);
            if (w.isBlank()) {
                pigLatins.add(w);
            } else {
                final var head = w.charAt(0);
                if (head == 'a' || head == 'o' || head == 'e' || head == 'i' || head == 'u'
                    || head == 'A' || head == 'O' || head == 'E' || head == 'I' || head == 'U') {
                    pigLatins.add(w + "way");
                } else if (Character.isAlphabetic(head)){
                    pigLatins.add(w.substring(1) + head + "ay");
                } else {
                    pigLatins.add(w);
                }
            }
        }
        for (var p : pigLatins) {
            System.out.print(p);
        }
    }

}
