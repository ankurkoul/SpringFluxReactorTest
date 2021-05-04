import org.junit.Test;
import reactor.core.publisher.Mono;

public class MonoTest {
    /**
     * mono-->just.empty
     * log()
     * subscribe--->(consumer,error,done)
     * eventHandler--->Do XXX
     * a. onSubscribe--->
     * b. onRequest  --->
     * c. onSuccess  --->
     */
    @Test
    public void firstMethod() {
        Mono.just("A")
                .log()
                .subscribe(e -> System.out.println(e));
    }

    @Test
    public void eventsMethod() {
        Mono.just("A")
                .log()
                .doOnSubscribe(e -> System.out.println(" on doOnSubscribe...." + e))
                .doOnRequest(e -> System.out.println(" on doOnRequest....." + e))
                .doOnSuccess(e -> System.out.println(" on doOnSuccess....." + e))
                .subscribe(e -> System.out.println(" on doOnSubscribe..." + e));
    }

    @Test
    public void eventsMethodAndDone() {
        Mono.just("A")
                .log()
                .doOnSubscribe(e -> System.out.println(" on ON_SUB...." + e))
                .doOnRequest(e -> System.out.println(" on REQ....." + e))
                .doOnSuccess(e -> System.out.println(" on SUCC ....." + e))
                .subscribe(e -> System.out.println(" on SUB..." + e), null
                        , () -> System.out.println(" on DONE..."));
    }

    @Test
    public void eventsMethodEmpty() {
        Mono.empty()
                .log()
                .subscribe(e -> System.out.println(" on SUB..." + e), null
                        , () -> System.out.println(" on DONE..."));
    }
}
