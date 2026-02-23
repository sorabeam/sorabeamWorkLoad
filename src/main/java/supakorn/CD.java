package supakorn;

public class CD {

    int id;
    int hp;

    String Bid;
    String Sid;
    String name;
    String desc;

    public CD(int id,String name,int hp, String desc) {
        this.id = id;
        this.hp = hp;
        Bid = "B" + id;
        Sid = "S" + id;
        this.name = name;
        this.desc = desc;
    }

    public int getId() { return id; }
    public int getHp() { return hp; }
    public String getBid() { return Bid; }
    public String getSid() { return Sid; }
    public String getName() { return name; }
    public String getDesc() { return desc; }
}
