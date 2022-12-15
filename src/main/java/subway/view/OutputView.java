package subway.view;

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

    private enum Message {
        OUTPUT_SEARCH_RESULT("## 조회 결과"),
        OUTPUT_INFO("[INFO] %s");

        private final String message;

        Message(String message) {
            this.message = message;
        }
    }


}