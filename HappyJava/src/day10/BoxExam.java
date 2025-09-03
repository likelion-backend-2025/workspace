package day10;

public class BoxExam {
    public static void main(String[] args) {
        ObjectBox box = new ObjectBox();
        //어떤 물건을 담을 수 있을 까요?
        box.setItem(new Person("kang",20));

        box.setItem(new Pen());

        ((Pen)box.getItem()).쓰다();

        PenBox penBox = new PenBox();
        penBox.setPen(new Pen());

        penBox.getPen().쓰다();

        GenericBox<Pen> penGenericBox = new GenericBox<>();
        penGenericBox.setItem(new Pen());
        penGenericBox.getItem().쓰다();

        GenericBox<Person> personGenericBox = new GenericBox<>();
        personGenericBox.setItem(new Person("kang",20));
        personGenericBox.getItem().getName();

        GenericBox<String> stringGenericBox = new GenericBox<>();
        stringGenericBox.setItem("abc");
        stringGenericBox.getItem().toLowerCase();


    }
}
