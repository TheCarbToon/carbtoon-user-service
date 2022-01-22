package kr.springboot.dcinside.cartoon.userservice.dto.feign.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDisplayNameUpdateFeignRequest {

    @JsonProperty("displayName")
    private String displayName;

    private String id;

    @JsonProperty("lbServiceName")
    private String lbServiceName;

    @Builder
    public UserDisplayNameUpdateFeignRequest(String displayName, String id, String lbServiceName) {
        this.displayName = displayName;
        this.id = id;
        this.lbServiceName = lbServiceName;
    }

}
