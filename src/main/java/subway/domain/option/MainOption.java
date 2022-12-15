package subway.domain.option;

import java.util.Arrays;
import subway.util.ExceptionMessage;

public enum MainOption {
    SEARCH_PATH("1"),
    QUIT("Q");

    private final String command;

    MainOption(String command) {
        this.command = command;
    }

    public static MainOption from(String command) {
        return Arrays.stream(MainOption.values())
                .filter(option -> option.command.equals(command))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.NO_MAIN_OPTION.getMessage()));
    }

    public boolean continueMain() {
        return this != QUIT;
    }

}