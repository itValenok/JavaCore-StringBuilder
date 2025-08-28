package stream1;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamCollectorsExample {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );
        Map<String, Double> order2 = orders.stream()
                .collect(Collectors.groupingBy(Order::getProduct,Collectors.summingDouble(Order::getCost)));
        order2.entrySet().stream().sorted(Map.Entry.<String, Double>comparingByValue().reversed()).limit(3).toList().forEach(System.out::println);
    }
}