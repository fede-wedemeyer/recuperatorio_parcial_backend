package federico.wedemeyer8.parcial.application.requests.create;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(onConstructor_ = {@JsonIgnore})
public class CreateMediaTypeRequest {
    String name;
}
