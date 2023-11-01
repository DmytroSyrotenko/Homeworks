package service;

import db.Db;
import inputAndOutput.ReadAndWrite;
import routesAndCost.Cost;
import buildAndCheck.checks.GenerateFromInput;
import buildAndCheck.checks.GenerateFromRandom;
import inputAndOutput.impl.ReadAndWriteImpl;
import routesAndCost.Routes;
import buildAndCheck.checks.Check;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Service {

    final ReadAndWrite readAndWriteImpl = new ReadAndWriteImpl();
    final GenerateFromRandom generateFromRandom = new GenerateFromRandom();
    final Check check = new Check();
    final Db db = Db.getInstance();
    final Cost cost = new Cost();
    final Routes routes = new Routes();
    final GenerateFromInput generateFromInput = new GenerateFromInput();


    public void start() {
        if (readAndWriteImpl.isInputExists()) {
            db.addTemp(readAndWriteImpl.readInput());
            generateFromInput.divideInput();
            check.checkForInput();
            for (List<Integer> fromXtoY : transformCityIntoIndexNumber(db.getRoutesWeShouldBuild(), db.getAllCities())) {
                readAndWriteImpl.writeOutput(cost.getCheapestCost(routes.createAllPossibleRoutes(fromXtoY)));
            }
        } else {
            System.out.println("создаем из random");
            db.addAllLinks(generateFromRandom.generateListOfRandomAndCheckedLinks());
            readAndWriteImpl.writeOutput(cost.getCheapestCost(routes.createAllPossibleRoutes(generateFromRandom.generateRandomRoute())));
        }
    }

    public List<List<Integer>> transformCityIntoIndexNumber(List<List<String>> listOfCities, Map<String, Integer> citiesIndex) {
        List<List<Integer>> fromToInNumbers = new ArrayList<>();
        for (List<String> strings : listOfCities) {
            List<Integer> list = new ArrayList<>();
            list.add(citiesIndex.get(strings.get(0)));
            list.add(citiesIndex.get(strings.get(1)));
            fromToInNumbers.add(list);
        }
        return fromToInNumbers;
    }
}
