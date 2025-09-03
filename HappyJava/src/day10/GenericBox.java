package day10;

//설계할 당시에는 이 박스에 뭐가 담길지 아직 몰라.
//그래서 타입을 딱 정하지 않고..
//생성시에 예를들어 GenericBox<Pen> pen....
//이렇게 생성되면, I 가 모두 Pen 으로 바뀜.
public class GenericBox<I> {
    I item;

    public I getItem() {
        return item;
    }

    public void setItem(I item) {
        this.item = item;
    }
}
