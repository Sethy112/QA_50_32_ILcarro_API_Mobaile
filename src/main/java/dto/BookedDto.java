package dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@ToString
@AllArgsConstructor

public class BookedDto {
    private String email;
    private String startDate;
    private String endDate;

}