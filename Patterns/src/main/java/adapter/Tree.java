package adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Need to Adapt the Forest into Coordinates
 */


class ForestSectionToTreeAdapter {

    /**
     * This is essential, cache the thing you have transformed
     */
    private static Map<ForestSection, List<Long>> forestToTreeMap = new HashMap<>();

    private void waterForest(ForestSection sectionName) {
        if (forestToTreeMap.containsKey(sectionName)) {
            System.out.println("Retrieving coordinates from Cache ");
            List<Long> coOrds = forestToTreeMap.get(sectionName);
            Sprinkler.waterTree(coOrds.get(0), coOrds.get(1), coOrds.get(2), coOrds.get(3));

        } else {
            System.out.println("Calculating Coordinates");
            List<Tree> trees = sectionName.trees;
            Long maxLatitude = trees.stream().map(x -> x.lattitude).max(Long::compareTo).get();
            Long minLatitude = trees.stream().map(x -> x.lattitude).min(Long::compareTo).get();
            Long maxLongitude = trees.stream().map(x -> x.longitude).max(Long::compareTo).get();
            Long minLongitude = trees.stream().map(x -> x.longitude).min(Long::compareTo).get();
            forestToTreeMap.put(sectionName, List.of(minLatitude, maxLatitude, minLongitude, maxLongitude));
            Sprinkler.waterTree(minLatitude, maxLatitude, minLongitude, maxLongitude);
        }
        System.out.println("Section " + sectionName.sectionName + " watered sucessfully.");

    }

    public void waterForest(Forest forest) {
        forest.sections.forEach(this::waterForest);
    }
}


/**
 * We can water only Coordinates
 */
class Sprinkler {
    public static void waterTree(Long minLat, Long maxLat, Long minLong, Long maxLong) {
        for (Long i = minLat; i < maxLat; i++) {
            for (Long j = minLong; j < maxLong; j++) {
                System.out.println("Watering at Long : " + j + " Lat : " + i + "  successfully");
            }
        }
    }
}

/**
 * Where are the coordinates Hidden into Trees
 */
public class Tree {
    public Tree(String name, long longitude, long lattitude) {
        this.name = name;
        this.longitude = longitude;
        this.lattitude = lattitude;
    }

    String name;
    long longitude;
    long lattitude;

    @Override
    public String toString() {
        return "Tree{" +
                "name='" + name + '\'' +
                ", longitude=" + longitude +
                ", lattitude=" + lattitude +
                '}';
    }

}

class ForestSection {
    List<Tree> trees;
    String sectionName;

    ForestSection(String sectionName, List<Tree> trees) {
        this.trees = trees;
        this.sectionName = sectionName;
    }
}

class Forest {
    public Forest(String forestName, List<ForestSection> sections) {
        this.forestName = forestName;
        this.sections = sections;
    }

    String forestName;
    List<ForestSection> sections;
}



class TestClass {
    public static void main(String[] args) {
        Forest myForest = new Forest("MyForest ", List.of(
                new ForestSection("ForestSection1",
                        List.of(
                                new Tree("t1", 1, 1),
                                new Tree("t3", 2, 2),
                                new Tree("t4", 3, 3),
                                new Tree("t5", 4, 4),
                                new Tree("t2", 5, 5),
                                new Tree("t6", 6, 6)

                        )
                ),
                new ForestSection("ForestSection2",
                        List.of(
                                new Tree("t1", 3, 1),
                                new Tree("t3", 4, 3),
                                new Tree("t4", 2, 2),
                                new Tree("t5", 3, 4),
                                new Tree("t2", 1, 2),
                                new Tree("t6", 4, 6)

                        )
                )

        )
        );
        new ForestSectionToTreeAdapter().waterForest(myForest);
        new ForestSectionToTreeAdapter().waterForest(myForest);

    }
}



