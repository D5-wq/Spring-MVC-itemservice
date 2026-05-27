package hello.itemservice.domain.member;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class Member {

    private Long id;

    @NotEmpty
    private String loginId;   // 로그인 ID

    @NotEmpty
    private String name;      // 이름

    @NotEmpty
    private String password;
}
