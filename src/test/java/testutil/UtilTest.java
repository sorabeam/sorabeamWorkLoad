package testutil;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class UtilTest {

    private static final AtomicBoolean initialized = new AtomicBoolean(false);

    public static void init() {
        if (initialized.compareAndSet(false, true)) {
            new JFXPanel(); // initializes JavaFX toolkit
        }
    }

    public static void runOnFxThreadAndWait(Runnable action) {
        init();

        if (Platform.isFxApplicationThread()) {
            action.run();
            return;
        }

        CountDownLatch latch = new CountDownLatch(1);
        AtomicReference<Throwable> error = new AtomicReference<>();

        Platform.runLater(() -> {
            try {
                action.run();
            } catch (Throwable t) {
                error.set(t);
            } finally {
                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        if (error.get() != null) {
            throw new RuntimeException(error.get());
        }
    }
}