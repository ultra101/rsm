package org.example.task1;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FooBar {
    public List<String> fooBar(String input) {
        List<String> elements = parseInput(input);
        Set<String> numbers = new HashSet<>();

        IntStream.range(0, elements.size()).forEach(i -> {
            String output = "";
            String element = elements.get(i);
            int number = Integer.parseInt(element);

            if (number != 0) {
                if (number % 3 == 0) output += "foo";
                if (number % 5 == 0) output += "bar";
            }

            if (output.isEmpty()) {
                if (numbers.add(element)) {
                    elements.set(i, element);
                } else {
                    elements.set(i, element + "-copy");
                }
            } else {
                if (numbers.add(element)) {
                    elements.set(i, output);
                } else {
                    elements.set(i, output + "-copy");
                }
            }
        });

        System.out.println(elements);

        return elements;
    }

    private List<String> parseInput(String input) {
        return Collections.list(new StringTokenizer(input, ",")).stream()
                .map(token -> (String) token)
                .collect(Collectors.toList());
    }
}
