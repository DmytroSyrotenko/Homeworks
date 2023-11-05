package db;

import entity.Link;
import java.util.*;

public class Db {

    private final List<String> temp = new ArrayList<>();
    private final Map<String, Integer> allCities = new HashMap<>();
    private final List<List<String>> routesWeShouldBuild = new ArrayList<>();
    private final List<Link> allLinks = new ArrayList<>();
    private Integer qtyOfCities = 0;
    private Integer qtyOfRelations = 0;
    private Integer qtyOfRequiredRoutes = 0;
    private static Db instance;

    public Db() {
    }

    public static Db getInstance() {
        if (instance != null) {
            return instance;
        } else {
            Db db = new Db();
            instance = db;
            return db;
        }
    }

    public void setQtyOfCities(Integer qtyOfCities) {
        this.qtyOfCities = qtyOfCities;
    }

    public void setQtyOfRelations(Integer qtyOfRelations) {
        this.qtyOfRelations = qtyOfRelations;
    }

    public void setQtyOfRequiredRoutes(Integer qtyOfRequiredRoutes) {
        this.qtyOfRequiredRoutes = qtyOfRequiredRoutes;
    }

    public Integer getQtyOfCities() {
        return qtyOfCities;
    }

    public Integer getQtyOfRelations() {
        return qtyOfRelations;
    }

    public Integer getQtyOfRequiredRoutes() {
        return qtyOfRequiredRoutes;
    }

    public void addRoutesWeShouldBuild(List<String> route) {
        this.routesWeShouldBuild.add(route);
    }

    public List<List<String>> getRoutesWeShouldBuild() {
        return this.routesWeShouldBuild;
    }

    public void addTemp(List<String> list) {
        this.temp.addAll(list);
    }

    public List<String> getTemp() {
        return this.temp;
    }

    public void addAllCities(String city, Integer ordinalNumber) {
        this.allCities.put(city, ordinalNumber);
    }

    public Map<String, Integer> getAllCities() {
        return this.allCities;
    }

    public void addLink(Link entity) {
        this.allLinks.add(entity);
    }

    public void addAllLinks(List<Link> list) {
        this.allLinks.addAll(list);
    }

    public List<Link> getAllLinks() {
        return this.allLinks;
    }
}
