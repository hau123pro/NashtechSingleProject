package dto.reponse;

import java.util.List;

import org.springframework.http.HttpHeaders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class HeaderResponse<T> {
    private List<T> items;
    private HttpHeaders headers;
}
