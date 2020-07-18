package entity;

import lombok.*;

/**
 * Ответ возвращаемый на запрос о ближайшем офисе
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NearAtmOffice {

    private Integer id;

    private String title;

    private Double lon;

    private Double lat;

    private String address;

    private Integer distance;
}
