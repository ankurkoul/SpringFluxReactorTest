import org.junit.Test;
import reactor.core.publisher.Mono;

public class MonoErrorTest {
/** Handled  ::
*  1.by Subscriber : errorConsumer
*  2. doOnError
*  3. onErrorResume
*  4. onErrorReturn
* */
    @Test
    public void onErrorHandledBySubscriber() {
        Mono.error(new Exception())
                .log()
                .subscribe(e -> System.out.println("Success: " + e),
                        e -> System.out.println("Error :" + e)
                );
    }

    @Test
    public void onErrorHandledBySubscriberWithDone() {
        //Done is not executed
        Mono.error(new Exception())
                .log()
                .subscribe(e -> System.out.println("Success: " + e),
                        e -> System.out.println("Error :" + e),
                        () -> System.out.println("DONE.......")
                );
    }

    @Test
    public void onErrorDoneMethod() {
        //Done is not executed
        Mono.error(new Exception())
                .log()
                .doOnError(throwable -> System.out.println("do On Error...." + throwable))
                .subscribe(
                        e -> System.out.println("Success....... " + e)
                );
    }

    @Test
    public void swallowReturnMonoMethod() {
        //Done is not executed
        Mono.error(new Exception())
                .log()
                .onErrorResume(throwable -> {
                    System.out.println("do On Error...." + throwable);
                    return Mono.just("B");
                })
                .subscribe(
                        e -> System.out.println("Success....... " + e)
                );
    }

    @Test
    public void swallowReturnNormalMethod() {
        //Done is not executed
        Mono.error(new Exception())
                .log()
                .onErrorReturn("B")
                .subscribe(
                        e -> System.out.println("Success....... " + e)
                );
    }
}
