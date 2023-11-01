package lk.ijse.finalproject02.DTO.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ViewAttendenceTm {
    private String name;
    private String NIC;
    private String date;
    private String attend;
}
