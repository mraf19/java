package enigma.livecodesmartclass.model.dto;


import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class CommonResponse<T> {
    private Integer statusCode;
    private String message;
    private T data;
    private String error;
}
