package com.xuechuan.xcedu.vo;

import com.xuechuan.xcedu.vo.SqliteVo.QuestionSqliteVo;

import java.util.ArrayList;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: xcedu
 * @Package com.xuechuan.xcedu.vo
 * @Description: 答题卡数据
 * @author: L-BackPacker
 * @date: 2018.12.26 下午 5:21
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public class CaseCardVo {
    private int type;//1 为没有简答和阅读 2为有
    private ArrayList<QuestionSqliteVo> list;
    private QuestionSqliteVo vo;

    public QuestionSqliteVo getVo() {
        return vo;
    }

    public void setVo(QuestionSqliteVo vo) {
        this.vo = vo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ArrayList<QuestionSqliteVo> getList() {
        return list;
    }

    public void setList(ArrayList<QuestionSqliteVo> list) {
        this.list = list;
    }
}
