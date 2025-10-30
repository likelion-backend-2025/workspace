package builderexam.lombokuser;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class User {
    @Builder.Default
    private String name = "guest";
    @Builder.Default
    private int age = 20;
    private String email;
    private String address;

}
