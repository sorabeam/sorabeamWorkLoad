package Cookies;

import Main.Animation.Animate;
import Main.Animation.AnimationType;
import Main.Cookies.Cookie;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import Main.GameLogic.GameLogic;
import Main.Scene.GameplayScene;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.*;
import testutil.UtilTest;

import static org.junit.jupiter.api.Assertions.*;

class CookieTest {
    private GameplayScene mockScene;


    @BeforeAll
    static void initJavaFX() {
        UtilTest.init();
    }

    /**
     * Dummy Animate
     * ใช้แทน Animate จริงเพื่อไม่ให้เรียก JavaFX
     */
    static class DummyAnimate extends Animate {

        public DummyAnimate() {
            super(null,
                    1,1,1,1,
                    1,1,1,1,
                    1,
                    1,1);
        }

        @Override
        public void changeAnimationState(AnimationType type) {
            // ไม่ต้องทำอะไร
        }
    }

    /**
     * TestCookie สำหรับใช้ทดสอบ
     */
    static class TestCookie extends Cookie {

        public TestCookie() {
            super(1,"Test",100,"Test Cookie");

            // ใส่ dummy animate ป้องกัน null
            this.cookie = new DummyAnimate();
        }

        @Override
        public void useSkill() {

        }

        @Override
        public Animate createCookie() {
            return cookie;
        }
    }

    @Test
    void testInitialHp() {

        TestCookie cookie = new TestCookie();

        assertEquals(100, cookie.get_Hp());
        assertEquals(100, cookie.getMaxHp());

    }

    @Test
    void testHeal() {

        TestCookie cookie = new TestCookie();

        cookie.setHp(50);
        cookie.heal(20);

        assertEquals(70, cookie.get_Hp());

    }

    @Test
    void testHealCannotExceedMaxHp() {

        TestCookie cookie = new TestCookie();

        cookie.heal(50);

        assertEquals(100, cookie.get_Hp());

    }

    @Test
    void testSetHpCannotExceedMaxHp() {

        TestCookie cookie = new TestCookie();

        cookie.setHp(200);

        assertEquals(100, cookie.get_Hp());

    }

    @Test
    void testTakeDamage() {

        TestCookie cookie = new TestCookie();

        cookie.takeDamage(30);

        assertEquals(70, cookie.get_Hp());

    }

    @Test
    void testInvincible() {

        TestCookie cookie = new TestCookie();

        cookie.setInvincible(1);

        assertTrue(cookie.isInvincible());

    }

    @Test
    void testScore() {

        TestCookie cookie = new TestCookie();

        cookie.setScore(500);

        assertEquals(500, cookie.get_Score());

    }

    @Test
    void testDeath() {

        TestCookie cookie = new TestCookie();

        cookie.takeDamage(200);

        assertTrue(cookie.isDead());

    }
}