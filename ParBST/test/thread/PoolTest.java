package thread;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

public class PoolTest {
    static final int nThreads = 8;
    static final int nTests = 10000000;
    static long time;
    static Pool pool;

    @BeforeClass
    public static void makeInstance() throws Exception {
        pool = new Pool(nThreads);
    }

    @Test
    public void push() throws Exception {
        AtomicInteger count = new AtomicInteger();

        for (int i = 0; i < nTests; ++i) {
            pool.push(() -> {
                count.getAndIncrement();
            });
        }

        while (!pool.isEmpty());
        assertEquals(count.get(), nTests);
    }

    @Test
    public void time() throws Exception {
        time = System.currentTimeMillis();
        {
            AtomicInteger count = new AtomicInteger();

            for (int i = 0; i < nTests; ++i) {
                pool.push(() -> {
                    count.getAndIncrement();
                });
            }
        }
        long poolTime = System.currentTimeMillis() - time;

        time = System.currentTimeMillis();
        {
            AtomicInteger count = new AtomicInteger();

            for (int i = 0; i < nTests; ++i) {
                count.getAndIncrement();
            }
        }
        long defaultTime = System.currentTimeMillis() - time;

        System.out.println(poolTime);
        System.out.println(defaultTime);
    }
}