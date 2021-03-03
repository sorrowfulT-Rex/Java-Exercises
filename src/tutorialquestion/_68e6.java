package tutorialquestion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class _68e6 {

  public static void main(String[] args) {
	  final List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 9);
	  final List<Integer> list2 = Arrays.asList(1, 10, 100, 1000, 10000);
	  final List<Integer> list3 = Arrays.asList(6, 7, 8);
	  final List<List<Integer>> listOfLists = Arrays.asList(list1, list2, list3);

	  final List<Integer> allIntegers = concatenate(listOfLists);
	  final int maxList1 = findMax(list1);
	  final int minList2 = findMin(list2);
	  final int maxEmpty = findMax(Collections.emptyList());
	  final int minEmpty = findMin(Collections.emptyList());
	  final int maxOrZeroEmpty = findMinOrZero(Collections.emptyList());
	  final int minOrZeroEmpty = findMaxOrZero(Collections.emptyList());
	  final int minOfMaxes = findMinOfMaxes(listOfLists);
	  final int minOfMaxesEmpty = findMinOfMaxes(Collections.emptyList());
	  final int minOfMaxesListOfEmptyLists = findMinOfMaxes(Arrays.asList(Collections.emptyList(), Collections.emptyList()));

    System.out.println(maxList1);
    System.out.println(minList2);
    System.out.println(maxEmpty);
    System.out.println(minEmpty);
    System.out.println(maxOrZeroEmpty);
    System.out.println(minOrZeroEmpty);
    System.out.println(minOfMaxes);
    System.out.println(minOfMaxesEmpty);
    System.out.println(minOfMaxesListOfEmptyLists);
  }

	static List<Integer> concatenate(List<List<Integer>> lists) {
		return lists.stream().reduce(new ArrayList<>(), (a, b) -> {
			a.addAll(b); return a;
		});
	}

	static int findMin(List<Integer> numbers) {
  	return numbers.stream().reduce(Integer.MAX_VALUE, Math::min);
	}

	static int findMinOrZero(List<Integer> numbers) {
  	return numbers.stream().reduce(Math::min).orElse(0);
	}

	static int findMax(List<Integer> numbers) {
		return numbers.stream().reduce(Integer.MIN_VALUE, Math::max);
	}

	static int findMaxOrZero(List<Integer> numbers) {
		return numbers.stream().reduce(Math::max).orElse(0);
	}

	static int findMinOfMaxes(List<List<Integer>> listOfLists) {
  	return findMin(listOfLists.stream().map(_68e6::findMax).collect(Collectors.toList()));
	}
}
