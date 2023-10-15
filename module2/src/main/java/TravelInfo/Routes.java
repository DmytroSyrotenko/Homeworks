package TravelInfo;

import java.util.ArrayList;
import java.util.List;

public class Generators {
    public List<Cities> generateConnections() {
        List<Cities> connectionsTemp = new ArrayList<>();

        Cities city = new Cities();
        city.setIndexOfA(1);
        city.setIndexOfB(2);
        city.setCost(1);
        connectionsTemp.add(city);

        Cities city1 = new Cities();
        city1.setIndexOfA(1);
        city1.setIndexOfB(3);
        city1.setCost(3);
        connectionsTemp.add(city1);

        Cities city2 = new Cities();
        city2.setIndexOfA(2);
        city2.setIndexOfB(3);
        city2.setCost(1);
        connectionsTemp.add(city2);

        Cities city3 = new Cities();
        city3.setIndexOfA(2);
        city3.setIndexOfB(4);
        city3.setCost(4);
        connectionsTemp.add(city3);

        Cities city4 = new Cities();
        city4.setIndexOfA(3);
        city4.setIndexOfB(4);
        city4.setCost(1);
        connectionsTemp.add(city4);
        return connectionsTemp;
    }

    public List<String> createAllPossibleRoutes(List<Cities> connections, Integer start, Integer finish) {
        List<String> possibleRoutes = new ArrayList<>();
        StringBuilder route = new StringBuilder();
        boolean isUniqueRoute = possibleRoutes.contains(route.toString());
        List<Integer> bannedPositions = new ArrayList<>();
        Integer currentPosition = start;

        route.append("->").append(currentPosition);
        for (int i = 0; i < connections.size(); i++) {
            Cities c = connections.get(i);
            System.out.println("city=" + c);
            if ((c.indexOfA.equals(currentPosition) || c.indexOfB.equals(currentPosition)) && !bannedPositions.contains(connections.indexOf(c))) {
                currentPosition = updatePosition(c, currentPosition);
                route = addStepToThePath(route, currentPosition);
                updateBannedPositions(bannedPositions, connections.indexOf(c));
               // i = 0;
                System.out.println("currentPosition" + currentPosition);
            } else if (c.indexOfA.equals(finish) || c.indexOfB.equals(finish) && c.indexOfA.equals(currentPosition) || c.indexOfB.equals(currentPosition)) {
                System.out.println("победа???");
                System.out.println(connections.indexOf(c));
                System.out.println("!!!!!!");
                break;
            } else if (i == connections.size() - 1 && !route.isEmpty()) {
                System.out.println(c);
                System.out.println("надо раскручивать назад");
            }
            System.out.println("tempIndexes=" + bannedPositions);
        }

        possibleRoutes.add(String.valueOf(route));
        bannedPositions.clear();
        return possibleRoutes;
    }

    private Integer updatePosition(Cities c, Integer oldCurrentPosition) {
        return c.indexOfA.equals(oldCurrentPosition) ? c.getIndexOfB() : c.getIndexOfA();
    }

    private StringBuilder addStepToThePath(StringBuilder currentPath, Integer newStep) {
        return currentPath.append("->").append(newStep);
    }

    private void updateBannedPositions(List<Integer> banList, Integer newBannedPosition) {
        banList.add(newBannedPosition);
    }

}
