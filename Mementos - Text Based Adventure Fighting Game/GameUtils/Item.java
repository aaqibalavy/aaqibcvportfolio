package GameUtils;

public class Item {
    private String name;
    private String description;
    private int dropRate; // drop rate for items

    public Item(){}

    // accessor and setter methods
    public String getName(){ return this.name; }
    public String getDescription(){ return this.description; }
    public int getDropRate(){ return this.dropRate; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String desc){ this.description = desc; }
    public void setDropRate(int rate){ this.dropRate = rate; }
}
