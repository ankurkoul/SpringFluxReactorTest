import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class OperatorTest {
    /**
     * operator-->
     * map
     * flatmap---->async operation
     * flatMapMany---> mono method to convert flux
     *
     * concat
     * merge
     * both combine multiple flux plublisher---> cooncat sequencially---> first flux1...then flux2
     * while merge combine them without wsequence randomly
     *
     *
     * zip--->combine item 1 with item2 simulatenously----> [Flux1_item1,Flux2_item1],[Flux1_item2,Flux2_item2]
     */
    @Test
    public void map() {
        Flux.range(1, 5)
                .map(i -> i*10)
                //  .log()
                .subscribe(System.out::println);
    }

    @Test
    public void flatMap() {
        Flux.range(1, 5)
                .flatMap(i -> Flux.range(i * 10, 2))
                //  .log()
                .subscribe(System.out::println);
    }

    @Test
    public void flatMapMany() {
        Mono.just(3)
                .flatMapMany(i -> Flux.range(i * 10, 2))
                //  .log()
                .subscribe(System.out::println);
    } @Test
    public void concat() throws InterruptedException {
        Flux<Integer>f1=   Flux.range(1, 5).delayElements(Duration.ofMillis(1));
        Flux<Integer>f2=   Flux.range(6, 5).delayElements(Duration.ofMillis(1));
        Flux.concat(f1,f2)
                //  .log()
                .subscribe(System.out::println);
        Thread.sleep(4000);

    }

    @Test
    public void concatWith() throws InterruptedException {
        Flux<Integer>f1=   Flux.range(1, 5).delayElements(Duration.ofMillis(1));
        Flux<Integer>f2=   Flux.range(6, 5).delayElements(Duration.ofMillis(1));
        f1.concatWith(f2)
                //  .log()
                .subscribe(System.out::println);
        Thread.sleep(4000);
    }
    @Test
    public void merge() throws InterruptedException {
        Flux<Integer>f1=   Flux.range(1, 5).delayElements(Duration.ofMillis(1));
        Flux<Integer>f2=   Flux.range(6, 5).delayElements(Duration.ofMillis(1));
        Flux.merge(f1,f2)
                //  .log()
                .subscribe(System.out::println);
        Thread.sleep(4000);
    }  @Test
    public void mergeWith() throws InterruptedException {
        Flux<Integer>f1=   Flux.range(1, 5);
        Flux<Integer>f2=   Flux.range(6, 5);
        f1.mergeWith(f2)
                //  .log()
                .subscribe(System.out::println);
        Thread.sleep(4000);
    }
    @Test
    public void zip() throws InterruptedException {
        Flux<Integer>f1=   Flux.range(1, 5);
        Flux<Integer>f2=   Flux.range(6, 5);
        Flux.zip(f1,f2,(i1,i2)-> i1 +" :: "+i2)
                // .log()
                .subscribe(System.out::println);
        Thread.sleep(4000);
    } @Test
    public void zipWith() throws InterruptedException {
        Flux<Integer>f1=   Flux.range(1, 5);
        Flux<Integer>f2=   Flux.range(6, 5);
        f1.zipWith(f2)
                //  .log()
                .subscribe(System.out::println);
        Thread.sleep(4000);
    }
}
