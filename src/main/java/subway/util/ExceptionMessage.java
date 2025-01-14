package subway.util;

public enum ExceptionMessage {

    INVALID_NOT_NUMERIC("자연수만 입력 가능합니다."),
    INVALID_OUT_OF_INT_RANGE("입력 범위를 초과하였습니다."),
    NO_SUCH_STATION("해당 이름의 역을 찾을 수 없습니다."),
    NO_MAIN_OPTION("해당하는 메인 옵션이 존재하지 않습니다."),
    NO_PATH_OPTION("해당하는 경로 기준이 존재하지 않습니다."),
    SAME_STATIONS("출발역과 도착역이 동일합니다.");

    public static final String BASE_MESSAGE = "[ERROR] %s";
    private final String message;

    ExceptionMessage(String message, Object... replaces) {
        this.message = String.format(BASE_MESSAGE, String.format(message, replaces));
    }

    public String getMessage() {
        return message;
    }
}