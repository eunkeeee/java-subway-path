package subway.controller;

import java.util.EnumMap;
import java.util.Map;
import subway.domain.option.MainOption;
import subway.domain.option.PathOption;
import subway.view.InputView;
import subway.view.OutputView;

public class MainController {
    private final InputView inputView;
    private final OutputView outputView;
    private final Map<MainOption, Runnable> gameGuide;

    public MainController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.gameGuide = new EnumMap<>(MainOption.class);
        initializeControllers();
    }

    private void initializeControllers() {
        gameGuide.put(MainOption.SEARCH_PATH, this::searchPath);
        gameGuide.put(MainOption.QUIT, this::quit);
    }

    public void process() {
        initializeSubway();
        MainOption mainOption;
        do {
            mainOption = inputView.readMainOption();
            gameGuide.get(mainOption).run();
        } while (mainOption.continueMain());
    }

    private void initializeSubway() {
        InitializingController initializingController = new InitializingController(inputView, outputView);
        initializingController.process();
    }

    private void searchPath() {
        PathOption pathOption = inputView.readPathOption();
    }

    private void quit() {
    }

}
