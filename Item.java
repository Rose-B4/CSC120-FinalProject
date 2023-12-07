public class Item {
    public String name;
    public String description;
    public boolean canBeMoved;
    
    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String toString() {
        return this.name+" || "+this.description;
    }
}
