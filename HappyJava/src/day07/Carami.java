package day07;

public class Carami {

    String name;
    @Override
    public String toString() {
        return "name : "+name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
