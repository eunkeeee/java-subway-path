package subway.domain;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

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
}