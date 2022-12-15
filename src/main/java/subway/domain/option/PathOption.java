package subway.domain.option;

import java.util.Arrays;
import subway.util.ExceptionMessage;

public enum PathOption {

    SHORTEST_DISTANCE("1"),
    SHORTEST_TIME("2"),
    GO_BACK("B");

    private final String command;

    PathOption(String command) {
        this.command = command;
    }

    public static PathOption from(String command) {
        return Arrays.stream(PathOption.values())
                .filter(option -> option.command.equals(command))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.NO_PATH_OPTION.getMessage()));
    }

}
