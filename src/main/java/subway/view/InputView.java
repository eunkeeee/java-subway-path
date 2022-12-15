package subway.view;

import java.util.Scanner;
import subway.domain.Station;
import subway.domain.StationRepository;
import subway.domain.option.MainOption;
import subway.domain.option.PathOption;

public class InputView {
    Scanner scanner = new Scanner(System.in);
    private static final InputView instance = new InputView();

    public static InputView getInstance() {
        return instance;
    }

    private InputView() {
    }

    public MainOption readMainOption() {
        try {
            System.out.println(Message.INPUT_MAIN_OPTION.message);
            return MainOption.from(scanner.next());
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return readMainOption();
        }
    }

    public PathOption readPathOption() {
        try {
            System.out.println(Message.INPUT_PATH_OPTION.message);
            return PathOption.from(scanner.next());
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return readPathOption();
        }
    }

    public Station readDepartureStation() {
        try {
            System.out.println(Message.INPUT_DEPARTURE_STATION.message);
            return StationRepository.getStationByName(scanner.next());
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return readDepartureStation();
        }
    }

    public Station readArrivalStation() {
        try {
            System.out.println(Message.INPUT_ARRIVAL_STATION.message);
            return StationRepository.getStationByName(scanner.next());
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return readArrivalStation();
        }
    }


    private enum Message {
        INPUT_MAIN_OPTION("## 메인 화면\n"
                + "1. 경로 조회\n"
                + "Q. 종료\n"
                + "\n"
                + "## 원하는 기능을 선택하세요."),
        INPUT_PATH_OPTION("## 경로 기준\n"
                + "1. 최단 거리\n"
                + "2. 최소 시간\n"
                + "B. 돌아가기\n"
                + "\n"
                + "## 원하는 기능을 선택하세요."),
        INPUT_DEPARTURE_STATION("## 출발역을 입력하세요."),
        INPUT_ARRIVAL_STATION("## 도착역을 입력하세요.");

        private final String message;

        Message(String message) {
            this.message = message;
        }
    }
}