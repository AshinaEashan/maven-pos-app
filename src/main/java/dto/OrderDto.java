package dto;


import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class OrderDto extends RecursiveTreeObject<OrderDto> {
    private String orderId;
    private String date;
    private String customerId;

}
