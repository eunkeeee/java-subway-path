package subway.domain;

import java.util.List;

public class Path {
    private final List<String> path;
    private final int totalDistance;
    private final int totalTime;

    public Path(List<String> path, int totalDistance, int totalTime) {
        this.path = path;
        this.totalDistance = totalDistance;
        this.totalTime = totalTime;
    }

    public List<String> getPath() {
        return path;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public int getTotalTime() {
        return totalTime;
    }
}
