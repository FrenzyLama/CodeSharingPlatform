package platform.applicationlayer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CodeDTO {
    private String code;

    private String date;

    private Long time;

    private Long views;

    @JsonIgnore
    private boolean timeRestriction;

    @JsonIgnore
    private boolean viewsRestriction;

    public CodeDTO(String code) {
        this.code = code;
    }

}
