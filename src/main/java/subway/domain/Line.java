package subway.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Line {
    private String name;
    private final List<Station> stations;

    public Line(String name, Station... stations) {
        this.name = name;
        this.stations = Arrays.stream(stations).collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    // 추가 기능 구현
}
