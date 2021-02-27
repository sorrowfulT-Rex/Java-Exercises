package tutorialquestion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class _fe94 {

  public static void main(String[] args) {
    List<String> input =
        new ArrayList<>(
            List.of("QTE", "7z", "Bruh", "114514", "Himitsunokoutei", "Luke Skywalker"));
    System.out.println(reverseEachString(input));
    System.out.println(reverseEachStringMonolithic(input));
    System.out.println(sqrtsOfFirstDigits(input));
    System.out.println(sqrtsOfFirstDigitsMonolithic(input));
  }

  static List<String> reverseEachString(List<String> input) {
    return input.stream()
        .map(StringBuilder::new)
        .map(StringBuilder::reverse)
        .map(StringBuilder::toString)
        .collect(Collectors.toList());
  }

  static List<String> reverseEachStringMonolithic(List<String> input) {
    return input.stream()
        .map(str -> (new StringBuilder(str)).reverse().toString())
        .collect(Collectors.toList());
  }

  static List<Double> sqrtsOfFirstDigits(List<String> input) {
    return input.stream()
        .filter(str -> !str.isBlank() && Character.isDigit(str.charAt(0)))
        .map(str -> str.charAt(0) + "")
        .map(Double::parseDouble)
        .map(Math::sqrt)
        .collect(Collectors.toList());
  }

  static List<Double> sqrtsOfFirstDigitsMonolithic(List<String> input) {
    return input.stream()
        .filter(str -> !str.isBlank() && Character.isDigit(str.charAt(0)))
        .map(str -> Math.sqrt(Double.parseDouble(str.charAt(0) + "")))
        .collect(Collectors.toList());
  }
}
