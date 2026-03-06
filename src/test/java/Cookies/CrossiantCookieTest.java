package Cookies;

import Main.Cookies.CrossiantCookie;
import Main.ObjectInGame.Items.CroissantType;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import testutil.UtilTest;

import static org.junit.jupiter.api.Assertions.*;

class CrossiantCookieTest {

    @BeforeAll
    static void initJavaFx() {
        UtilTest.init();
    }

    @Test
    void testJellyCounterIncrease() {
        UtilTest.runOnFxThreadAndWait(() -> {
            CrossiantCookie cookie = new CrossiantCookie();

            cookie.onJellyCollected();

            assertEquals(1, cookie.getSkillCounter());
        });
    }

    @Test
    void testCroissantReadyAfter50Jellies() {
        UtilTest.runOnFxThreadAndWait(() -> {
            CrossiantCookie cookie = new CrossiantCookie();

            for (int i = 0; i < 50; i++) {
                cookie.onJellyCollected();
            }

            assertTrue(cookie.isCroissantReady());
            assertEquals(0, cookie.getSkillCounter());
        });
    }

    @Test
    void testCroissantCycle() {
        UtilTest.runOnFxThreadAndWait(() -> {

            CrossiantCookie cookie = new CrossiantCookie();

            assertEquals(CroissantType.ORIGINAL, cookie.consumeCroissant());
            assertEquals(CroissantType.BUTTER, cookie.consumeCroissant());
            assertEquals(CroissantType.STRAWBERRY, cookie.consumeCroissant());
            assertEquals(CroissantType.ORIGINAL, cookie.consumeCroissant());
        });
    }
}