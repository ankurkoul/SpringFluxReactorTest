import org.junit.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;

public class FluxTest {
    /**
     * flux-->just,empty
     *   |--->fromXXX
     *          |------> fromIterable,  fromArray,  fromStream
     *          |------>from(OtherPublisher)
     *   |---->interval--->infinite flow----> choose few based on take()
     *   |---->range---->start,limt
     *          |------>initalRequest----> .subscribe(null,null,null,subscription -> subscription.request(3))
     *                      |--->take inital...and ignore rest--->hence no oncompleteEvent
     *          |------>for request Until all not consumed----->limitRate----->
     */

    @Test
    public void firstMethod() {
        Flux.just("A", "B", "C")
                .log()
                .subscribe(e -> System.out.println("subscribed to....." + e));
    }

    @Test
    public void justAsIterable_is_processedInOneShot() {
        Flux.just(Arrays.asList("A", "b", "c"))
                .log()
                .subscribe(e -> System.out.println("subscribed to....." + e));
    }

    @Test
    public void fromIterable_is_processedIndividually() {
        Flux.fromIterable(Arrays.asList("A", "b", "c"))
                .log()
                .subscribe(e -> System.out.println("subscribed to....." + e));
    }

    @Test
    public void fromRange() {
        Flux.range(1, 5)
                .log()
                .subscribe(e -> System.out.println("subscribed to....." + e));
    }

    @Test
    public void fromRange_with_request() {
        Flux.range(1, 5)
                .log()
                .subscribe(null,
                        null,
                        null,
                        subscription -> subscription.request(3));
        //No On Complete because out of 5 only 3 is used
    }


    @Test
    public void fromRange_with_limit_request_from_not_only_inital_request_until_everything_is_processed() {
        Flux.range(1, 5)
                .log()
                .limitRate(3)
                .subscribe();
        // On Complete because everything is processed
    }

    @Test
    public void fromInterval() throws InterruptedException {
        Flux.interval(Duration.ofSeconds(2))
                .log()
                .subscribe(e -> System.out.println("subscribed to....." + e));
//Without Sleep interval thread will not used
        Thread.sleep(4000);
    }
}
