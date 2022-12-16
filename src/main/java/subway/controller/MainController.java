package subway.controller;

import java.util.EnumMap;
import java.util.Map;
import subway.domain.option.MainOption;
import subway.view.InputView;
import subway.view.OutputView;

public class MainController {
    private final InputView inputView;
    private final OutputView outputView;
    private final Map<MainOption, Controllable> controllers;

    public MainController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.controllers = new EnumMap<>(MainOption.class);
        initializeControllers();
    }

    private void initializeControllers() {
        controllers.put(MainOption.SEARCH_PATH, new PathSearchingController(inputView, outputView));
    }

    public void service() {
        initializeSubway();
        MainOption mainOption;
        do {
            mainOption = inputView.readMainOption();
            process(mainOption);
        } while (mainOption.continueMain());
    }

    private void process(MainOption mainOption) {
        try {
            controllers.get(mainOption).process();
        } catch (NullPointerException ignored) {
        }
    }

    private void initializeSubway() {
        try {
            InitializingController initializingController = new InitializingController(inputView, outputView);
            initializingController.process();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

}
