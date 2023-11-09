package lk.ijse.finalproject02.DTO.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class paymentViewtm {
    private String name;
    private String nic;
    private String month;
    private String clasid;
    private String amount;
}
