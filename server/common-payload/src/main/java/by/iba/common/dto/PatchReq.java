package by.iba.common.dto;

import com.github.fge.jsonpatch.JsonPatch;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@Setter
public class PatchReq implements Serializable {
    private static final long serialVersionUID = 1L;
    private JsonPatch patch;
}
