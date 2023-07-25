package mars.ourmindmaze.dto.friend;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RequestFriendUpdateDto {
    @Schema(example = "5")
    private Long friendId;

    @Schema(example = "Y")
    private String status;
}
