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
public class BranchesWithPredicting {

      private long id;

      private String title;

      private double lon;

      private double lat;

      private String address;

      // число, где понедельник это 1, а воскресенье 7
      private int dayOfWeek;

      // число от 0 до 23
      private int hourOfDay;

      // время ожидания, которое надо считать в секундах, а результат округлять.
      private long predicting;

}
