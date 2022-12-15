package subway.controller;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;
import subway.domain.Path;
import subway.domain.PathRepository;
import subway.domain.Station;
import subway.domain.option.PathOption;
import subway.util.ExceptionMessage;
import subway.view.InputView;
import subway.view.OutputView;

public class PathSearchingController implements Controllable {

    private final InputView inputView;
    private final OutputView outputView;
    private final Map<Status, Supplier<Status>> controllerGuide;
    private PathOption pathOption;

    public PathSearchingController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.controllerGuide = new EnumMap<>(Status.class);
        initializeControllerGuide();
    }

    private void initializeControllerGuide() {
        controllerGuide.put(Status.SELECT_PATH_OPTION, this::selectPathOption);
    }


    @Override
    public void process() {
        Status status = Status.SELECT_PATH_OPTION;
        do {
            status = controllerGuide.get(status).get();
        } while (!status.goBack());
    }

    private Status selectPathOption() {
        pathOption = inputView.readPathOption();
        Station departureStation = inputView.readDepartureStation();
        Station arrivalStation = inputView.readArrivalStation();
        validateStations(departureStation, arrivalStation);

        Path optimalPath = PathRepository.getOptimalPath(pathOption, departureStation, arrivalStation);

        return Status.GO_BACK;
    }


    private static void validateStations(Station departureStation, Station arrivalStation) {
        if (departureStation.equals(arrivalStation)) {
            throw new IllegalArgumentException(ExceptionMessage.SAME_STATIONS.getMessage());
        }
    }

    private enum Status {
        SELECT_PATH_OPTION,
        GO_BACK;

        public boolean goBack() {
            return this == GO_BACK;
        }
    }

}
