package day13;

//TV 인스턴스만 생성
public class TVFactory {
    public static TV getTV(String tvName){

        if("s".equalsIgnoreCase(tvName)){
            return new STV();
        }else if("l".equalsIgnoreCase(tvName)){
            return new LTV();
        }
        return null;
    }
}
