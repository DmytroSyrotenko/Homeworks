package routesAndCost;

import db.Db;
import entity.Link;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cost {

    final Db db = Db.getInstance();
    final List<Link> allLinks = db.getAllLinks();
    final Integer maxCost = 200_000;

    public Integer getCheapestCost(List<List<Integer>> possibleRoutes) {
        Map<List<Integer>, Integer> map = new HashMap<>();
        possibleRoutes.forEach(r -> map.put(r, costPerRoute(r)));
        return map.values().stream().mapToInt(integer -> integer).filter(s -> s < maxCost).min().orElse(0);
    }

    private Integer costPerRoute(List<Integer> route) {
        Integer sum = 0;
        for (int i = 0; i < route.size() - 1; i++) {
            Integer indexA = route.get(i);
            Integer indexB = route.get(i + 1);
            for (Link link : allLinks) {
                if (link.getIndexOfA().equals(indexA) && link.getIndexOfB().equals(indexB) || link.getIndexOfA().equals(indexB) && link.getIndexOfB().equals(indexA)) {
                    sum += link.getCost();
                    break;
                }
            }
        }
        return sum;
    }
}