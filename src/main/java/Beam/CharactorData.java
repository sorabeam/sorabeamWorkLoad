package Beam;

import Beam.Cookies.BobaCookie;
import Beam.Cookies.Cookie;
import Beam.Cookies.SampleCookie;
import Beam.Pets.Moji;
import Beam.Pets.Pet;
import Beam.Pets.SamplePet;

public class CharactorData {

    private CharactorData() {} // ป้องกันการ new คลาสนี้

    public static final Cookie BOBACOOKIE = new BobaCookie();

    public static final Cookie SHADOW_MILK =
            new SampleCookie(2, "Shadow Milk", 500, "Brahe");

    public static final Cookie PURE_VANILLA =
            new SampleCookie(3, "Pure Vanilla", 500, "Nihhen");

    public static final Cookie HOLLY_BERRY =
            new SampleCookie(4, "Holly Berry", 500, "MeowMeow");

    public static final Cookie WHITE_LILY =
            new SampleCookie(5, "White Lily", 500, "Eoking");

    public static final Pet MOJINIGA = new Moji();

    public static final Pet PANDAFANG =
            new SamplePet(2, "Pandadang","Hello Boi","DrogonFruit");

    public static final Pet KANGFUNIGA =
            new SamplePet(3, "Kangfuniga","Chi ga dutch","Pitachio");

    public static final Pet LOCKING =
            new SamplePet(4, "Lock","Play more 1.67M Games to unlock","Lemon");

    private static Cookie Current_Cookie = BOBACOOKIE;

    public static Cookie getCurrent_Cookie() {
        return Current_Cookie;
    }
    public static void setCurrent_Cookie(Cookie current_Cookie) {
        Current_Cookie = current_Cookie;
    }

    private static Pet Current_Pet = MOJINIGA;

    public static Pet getCurrent_Pet() {
        return Current_Pet;
    }

    public static void setCurrent_Pet(Pet current_Pet) {
        Current_Pet = current_Pet;
    }
}
