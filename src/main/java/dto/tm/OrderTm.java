package dto.tm;

import com.jfoenix.controls.JFXButton;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class OrderTm {
    private String Code;
    private String Desc;
    private int Qty;
    private double Amount;
    private JFXButton btn;
}
