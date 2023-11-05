package routesAndCost;

import db.Db;
import entity.Link;
import java.util.ArrayList;
import java.util.List;

public class Routes {

    private final List<Integer> pathIndexes = new ArrayList<>();
    private final List<Integer> currentRoute = new ArrayList<>();
    private Integer currentPosition;
    final Db db = Db.getInstance();
    final List<Link> allLinks = db.getAllLinks();
    Link link = new Link();

    public List<List<Integer>> createAllPossibleRoutes(List<Integer> startFinishList) {
        List<List<Integer>> possibleRoutes = new ArrayList<>();
        Integer start = startFinishList.get(0);
        Integer finish = startFinishList.get(1);
        for (int i = 0; i < allLinks.size(); i++) {
            link = allLinks.get(i);
            if (currentRoute.isEmpty()) {
                currentPosition = start;
                addStepToRoute(currentPosition);
            }
            if (linkIsSuitableToChange() && notBanned() && !linkIsFinish(finish)) {
                stepForward();
                addStepToRoute(currentPosition);
                addBannedPosition();
                i = startFromTheBeginning();
            } else if (linkIsFinish(finish)) {
                addStepToRoute(finish);
                addBannedPosition();
                possibleRoutes.add(new ArrayList<>(currentRoute));
                i = pathIndexes.get(pathIndexes.size() - 1);
                removeBannedPosition();
                removeStepFromRoute();
                if (endOfList(i) && pathIndexes.size() > 0) {
                    i = pathIndexes.get(pathIndexes.size() - 1);
                    removeStepFromRoute();
                    stepBack();
                } else if (pathIndexes.size() == 0) {
                }
            } else if (endOfList(i) && (currentRoute.size() > 1)) {
                i = pathIndexes.get(pathIndexes.size() - 1);
                removeStepFromRoute();
                stepBack();
                if (endOfList(i) && pathIndexes.size() > 0) {
                    i = pathIndexes.get(pathIndexes.size() - 1);
                    removeStepFromRoute();
                    stepBack();
                } else if (pathIndexes.size() == 0) {
                }
            } else if (endOfList(i)) {
            }
        }
        currentRoute.clear();
        return possibleRoutes;
    }

    private boolean linkIsSuitableToChange() {
        return link.getIndexOfA().equals(currentPosition) || link.getIndexOfB().equals(currentPosition);
    }

    private int startFromTheBeginning() {
        return -1;
    }

    private boolean notBanned() {
        return !pathIndexes.contains(allLinks.indexOf(link));
    }

    private void stepForward() {
        currentPosition = link.getIndexOfA().equals(currentPosition) ? link.getIndexOfB() : link.getIndexOfA();
    }

    private void stepBack() {
        currentPosition = currentRoute.get(currentRoute.size() - 1);
        removeBannedPosition();
    }

    private void addStepToRoute(Integer newStep) {
        currentRoute.add(newStep);
    }

    private void removeStepFromRoute() {
        currentRoute.remove(currentRoute.size() - 1);
    }

    private void addBannedPosition() {
        pathIndexes.add(allLinks.indexOf(link));
    }

    private void removeBannedPosition() {
        pathIndexes.remove(pathIndexes.size() - 1);
    }

    private boolean endOfList(Integer i) {
        return i == allLinks.size() - 1;
    }

    private boolean linkIsFinish(Integer finish) {
        return (link.getIndexOfA().equals(currentPosition) && link.getIndexOfB().equals(finish)) || (link.getIndexOfB().equals(currentPosition) && link.getIndexOfA().equals(finish));
    }
}
