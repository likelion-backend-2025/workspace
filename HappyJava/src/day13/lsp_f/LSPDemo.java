package day13.lsp_f;

// LSP 위반 예제
class Rectangle {
    protected int width;
    protected int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int area() {
        return width * height;
    }
}

class Square extends Rectangle {
    public Square(int side) {
        super(side, side);
    }

    @Override
    public void setWidth(int width) {
        super.width = width;
        super.height = width;  // 너비를 설정하면 높이도 같이 변경
    }

    @Override
    public void setHeight(int height) {
        super.height = height;
        super.width = height;  // 높이를 설정하면 너비도 같이 변경
    }
}

class LSPDemo {
    public static void main(String[] args) {
        Rectangle rect = new Square(5);
        rect.setWidth(10);
        System.out.println(rect.area()); // 100 (예상: 50)
    }
}