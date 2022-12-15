package subway.view;

import subway.domain.Path;

public class OutputView {

    private static final OutputView instance = new OutputView();

    public static OutputView getInstance() {
        return instance;
    }

    private OutputView() {
    }

    public void printExceptionMessage(Exception exception) {
        System.out.println(exception.getMessage());
    }

    public void printOptimalPath(Path optimalPath) {
        System.out.println(Message.OUTPUT_SEARCH_RESULT.message);
        printInfo(Message.OUTPUT_LINE.message);
        printInfo(String.format(Message.OUTPUT_TOTAL_DISTANCE.message, optimalPath.getTotalDistance()));
        printInfo(String.format(Message.OUTPUT_TOTAL_TIME.message, optimalPath.getTotalTime()));
        printInfo(Message.OUTPUT_LINE.message);
        optimalPath.getPath().forEach(this::printInfo);
    }

    private void printInfo(String message) {
        System.out.printf(Message.OUTPUT_INFO.message, message);
    }

    private enum Message {
        OUTPUT_SEARCH_RESULT("## 조회 결과"),
        OUTPUT_INFO("[INFO] %s%n"),
        OUTPUT_LINE("---"),
        OUTPUT_TOTAL_DISTANCE("총 거리: %dkm"),
        OUTPUT_TOTAL_TIME("총 소요 시간: %d분");

        private final String message;

        Message(String message) {
            this.message = message;
        }
    }


}