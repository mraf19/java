package programmerzamannow.jpa.entity;

import java.time.LocalDateTime;

public interface UpdatedAtAware {

    public void setUpdatedAt(LocalDateTime updatedAt);
}
