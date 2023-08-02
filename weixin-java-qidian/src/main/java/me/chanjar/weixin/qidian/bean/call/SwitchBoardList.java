package me.chanjar.weixin.qidian.bean.call;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;

@Data
public class SwitchBoardList {
    private List<SwitchBoard> records;

    public List<String> switchBoards() {
        return records.stream().map(SwitchBoard::getSwitchboard).collect(Collectors.toList());
    }
}
