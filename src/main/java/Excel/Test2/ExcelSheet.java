package Excel.Test2;

import java.util.Arrays;
import java.util.List;

/**
 * @description: 导出多个sheet表
 * @author: ***
 * @date: 2022/9/15
 */
public class ExcelSheet {

    /*** sheet的名称*/
    private String fileName;

    /*** sheet里的标题*/
    private String[] handers;

    /*** sheet里的数据集*/
    private List<String[]> dataset;






    public ExcelSheet(String fileName, String[] handers, List<String[]> dataset) {
        this.fileName = fileName;
        this.handers = handers;
        this.dataset = dataset;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String[] getHanders() {
        return this.handers;
    }

    public List<String[]> getDataset() {
        return this.dataset;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setHanders(String[] handers) {
        this.handers = handers;
    }

    public void setDataset(List<String[]> dataset) {
        this.dataset = dataset;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ExcelSheet)) {
            return false;
        } else {
            ExcelSheet other = (ExcelSheet)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label39: {
                    Object this$fileName = this.getFileName();
                    Object other$fileName = other.getFileName();
                    if (this$fileName == null) {
                        if (other$fileName == null) {
                            break label39;
                        }
                    } else if (this$fileName.equals(other$fileName)) {
                        break label39;
                    }

                    return false;
                }

                if (!Arrays.deepEquals(this.getHanders(), other.getHanders())) {
                    return false;
                } else {
                    Object this$dataset = this.getDataset();
                    Object other$dataset = other.getDataset();
                    if (this$dataset == null) {
                        if (other$dataset != null) {
                            return false;
                        }
                    } else if (!this$dataset.equals(other$dataset)) {
                        return false;
                    }

                    return true;
                }
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof ExcelSheet;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $fileName = this.getFileName();
        result = result * 59 + ($fileName == null ? 43 : $fileName.hashCode());
        result = result * 59 + Arrays.deepHashCode(this.getHanders());
        Object $dataset = this.getDataset();
        result = result * 59 + ($dataset == null ? 43 : $dataset.hashCode());
        return result;
    }

    public String toString() {
        return "ExcelSheet(fileName=" + this.getFileName() + ", handers=" + Arrays.deepToString(this.getHanders()) + ", dataset=" + this.getDataset() + ")";
    }

}
