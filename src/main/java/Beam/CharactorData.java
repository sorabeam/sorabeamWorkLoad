package Beam;

import Beam.Cookies.*;
import Beam.Pets.*;

public class CharactorData {

    private CharactorData() {}



    public static final Cookie BOBACOOKIE = new BobaCookie();

    public static final Cookie CROSSIANT_COOKIE = new CrossiantCookie();

    public static final Cookie TOMYUM_COOKIE =new TomYumCookie();

    public static final Cookie LOCKING_COOKIE =
            new SampleCookie(4, "Holly Berry", 500, "MeowMeow");



    public static final Pet MOJINIGA = new Moji();

    public static final Pet Chilly = new Chilly();

    public static final Pet SALAD = new Salad();

    public static final Pet LOCKING =
            new SamplePet(4, "Lock","","UnSelect_Lock");



    private static Cookie Current_Cookie = BOBACOOKIE;
    private static Pet Current_Pet = SALAD;




    public static Cookie getCurrent_Cookie() {
        return Current_Cookie;
    }
    public static void setCurrent_Cookie(Cookie current_Cookie) {
        Current_Cookie = current_Cookie;
    }

    public static Pet getCurrent_Pet() {
        return Current_Pet;
    }
    public static void setCurrent_Pet(Pet current_Pet) {
        Current_Pet = current_Pet;
    }
}
