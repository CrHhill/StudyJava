package wangyiyun;


import com.xuxueli.poi.excel.annotation.ExcelField;
import com.xuxueli.poi.excel.annotation.ExcelSheet;
import lombok.Data;
import org.apache.poi.hssf.util.HSSFColor;

@Data
@ExcelSheet(name = "网易云音乐民谣分析", headColor = HSSFColor.HSSFColorPredefined.LIGHT_GREEN)
public class Wangyiyun {

    public Wangyiyun(String description, String href, int playNums) {
        this.description = description;
        this.href = href;
        this.playNums = playNums;
    }

    @ExcelField(name = "歌曲介绍")
    private String description;

    @ExcelField(name = "歌曲链接地址")
    private String href;

    @ExcelField(name = "歌曲播放次数")
    private int playNums;

}
