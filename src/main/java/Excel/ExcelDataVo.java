package Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author zdj
 * @version 1.0
 * @date 2021/09/27 14:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelDataVo implements Serializable {
    /**
     * excel sheet的名称
     */
    private String sheetName;


    /**
     * excel sheet表的标题
     */
    private String sheetTopColHeaderTitle;


    /**
     * excel sheet表的列头名称
     */
    private String[] sheetTopColHeaderName;


    /**
     * excel sheet表的列头属性
     */
    private String[] sheetTopColHeaderAttribute;


    /**
     * excel sheet表的数据
     */
    private List<Map<String, Object>> sheetDataList;
}


