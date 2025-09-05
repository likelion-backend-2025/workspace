package day13;

// SRP 예제
class User {
    private String username;
    private String password;

    public User(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        // SRP 적용: 검증 로직은 별도 클래스로 분리
        if (PasswordValidator.isValid(password)) {
            this.password = password;
        } else {
            throw new IllegalArgumentException("Invalid password");
        }
//        if(password != null && password.length() >= 6){
//            this.password = password;
//        }else {
//            throw new IllegalArgumentException("Password length should be 6 characters");
//        }
    }


    public String getUsername() {
        return username;
    }
}

class PasswordValidator {
    public static boolean isValid(String password) {
        return password != null && password.length() >= 6;
    }
}

class SRPDemo {
    public static void main(String[] args) {
        User user = new User("Alice");
        user.setPassword("abcdef"); // 유효한 패스워드
        System.out.println(user.getUsername());
    }
}