package dto;

import lombok.*;

@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class CarsDto {
    CarDto[] cars;
}