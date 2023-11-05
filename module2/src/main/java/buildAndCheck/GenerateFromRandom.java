package buildAndCheck;

import db.Db;
import entity.Link;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateFromRandom {

    final Db db = Db.getInstance();
    final Check check = new Check();

    public List<Link> generateListOfRandomAndCheckedLinks() {
        List<Link> list = new ArrayList<>();
        int numberOfCitiesToGenerate = 10;
        while (numberOfCitiesToGenerate > 0) {
            Link link = generateOneRandomLink();
            if (check.isCloneExists(link, list) && !check.linkIsConnectedToItself(link)) {
                list.add(link);
                numberOfCitiesToGenerate -= 2;
            } else {
                generateOneRandomLink();
                System.out.println("list=" + list);
                System.out.println("link=" + link);
                generateOneRandomLink();
                System.out.println("numberOfCitiesToGenerate=" + numberOfCitiesToGenerate);
            }
        }
        db.setQtyOfRelations(list.size() * 2);
        for (Link link : list) {
            System.out.println("random link=" + link);
        }
        return list;
    }

    private Link generateOneRandomLink() {
        return new Link(randomNumberGenerator(), randomNumberGenerator(), randomNumberGenerator());
    }

    private int randomNumberGenerator() {
        final int limit = 4;
        Random r = new Random();
        return r.nextInt(limit) + 1;
    }

    public List<Integer> generateRandomRoute() {
        List<Integer> list = new ArrayList<>();
        list.add(randomNumberGenerator());
        list.add(randomNumberGenerator());
        System.out.println("random route=" + list);
        return list;
    }
}
