//2022427833 니스타

package callbackInterface;

public class CallbackInterfaceEx01 {
    String url;
    HttpGetThread httpGetThread;
    HttpGetCallbackInterface httpCallback;

    public CallbackInterfaceEx01(String url) {
        this.url = url;
        httpGetThread = new HttpGetThread(url);
        httpCallback = new HttpGetCallbackInterface() {
            @Override
            public void updateProgress(String line) {
                System.out.println(line);
            }

            @Override
            public void updateDate(String date) {
                System.out.println("Latest update date: " + date);
            }
        };
        httpGetThread.setCallback(httpCallback);
        httpGetThread.start();
    }

    public static void main(String[] args) {
        new CallbackInterfaceEx01("https://finance.naver.com/sise/sise_market_sum.naver");
    }
}
