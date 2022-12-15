package subway.controller;

import subway.view.InputView;
import subway.view.OutputView;

public class MainController {
    private final InputView inputView;
    private final OutputView outputView;

    public MainController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void process() {
        InitializingController initializingController = new InitializingController(inputView, outputView);
        initializingController.process();
    }
}
