package subway.domain;

import java.util.List;

public class Section {
    private final List<Station> stations;
    private final int distance;
    private final int time;

    public Section(List<Station> stations, int distance, int time) {
        this.stations = stations;
        this.distance = distance;
        this.time = time;
    }

    public List<Station> getStations() {
        return stations;
    }

    public int getDistance() {
        return distance;
    }

    public int getTime() {
        return time;
    }
}
