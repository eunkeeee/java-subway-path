package subway.domain;

import java.util.List;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.domain.option.PathOption;

public class PathRepository {

    private static final WeightedMultigraph<String, DefaultWeightedEdge> distanceGraph = new WeightedMultigraph<>(
            DefaultWeightedEdge.class);
    private static final WeightedMultigraph<String, DefaultWeightedEdge> timeGraph = new WeightedMultigraph<>(
            DefaultWeightedEdge.class);


    public static void addStation(Station station) {
        distanceGraph.addVertex(station.getName());
        timeGraph.addVertex(station.getName());
    }

    public static void addSection(Section section) {
        String station1 = section.getStations().get(0).getName();
        String station2 = section.getStations().get(1).getName();
        distanceGraph.setEdgeWeight(distanceGraph.addEdge(station1, station2), section.getDistance());
        timeGraph.setEdgeWeight(timeGraph.addEdge(station1, station2), section.getTime());
    }

    public static Path getOptimalPath(PathOption pathOption, Station departureStation, Station arrivalStation) {
        List<String> path = getShortestPath(pathOption, departureStation, arrivalStation);
        int totalDistance = getTotalDistance(path);
        int totalTime = getTotalTime(path);
        return new Path(path, totalDistance, totalTime);
    }

    private static int getTotalDistance(List<String> path) {
        int totalDistance = 0;
        for (int index = 0; index < path.size() - 1; index++) {
            totalDistance += distanceGraph.getEdgeWeight(
                    distanceGraph.getEdge(path.get(index), path.get(index + 1)));
        }
        return totalDistance;
    }

    private static int getTotalTime(List<String> path) {
        int totalTime = 0;
        for (int index = 0; index < path.size() - 1; index++) {
            totalTime += timeGraph.getEdgeWeight(
                    timeGraph.getEdge(path.get(index), path.get(index + 1)));
        }
        return totalTime;
    }

    private static List<String> getShortestPath(PathOption pathOption, Station departureStation,
                                                Station arrivalStation) {
        if (pathOption == PathOption.SHORTEST_DISTANCE) {
            return calculateShortestPath(distanceGraph, departureStation, arrivalStation);
        }
        return calculateShortestPath(timeGraph, departureStation, arrivalStation);
    }

    private static List calculateShortestPath(WeightedMultigraph<String, DefaultWeightedEdge> distanceGraph,
                                              Station departureStation, Station arrivalStation) {
        return new DijkstraShortestPath(distanceGraph).getPath(departureStation.getName(), arrivalStation.getName())
                .getVertexList();
    }
}
