package com.xuechuan.xcedu.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.xuechuan.xcedu.db.Converent.UserConverent;
import com.xuechuan.xcedu.db.Converent.UserLookConverent;
import com.xuechuan.xcedu.db.Converent.UserLookVideoConverent;
import com.xuechuan.xcedu.vo.UserInfomVo;
import java.util.List;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER_INFOM_DB".
*/
public class UserInfomDbDao extends AbstractDao<UserInfomDb, Long> {

    public static final String TABLENAME = "USER_INFOM_DB";

    /**
     * Properties of entity UserInfomDb.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Vo = new Property(1, String.class, "vo", false, "VO");
        public final static Property Moid = new Property(2, String.class, "moid", false, "MOID");
        public final static Property SkillBook = new Property(3, boolean.class, "SkillBook", false, "SKILL_BOOK");
        public final static Property ColligateBook = new Property(4, boolean.class, "ColligateBook", false, "COLLIGATE_BOOK");
        public final static Property CaseBook = new Property(5, boolean.class, "CaseBook", false, "CASE_BOOK");
        public final static Property ShowDayOrNight = new Property(6, String.class, "ShowDayOrNight", false, "SHOW_DAY_OR_NIGHT");
        public final static Property UserNextGo = new Property(7, boolean.class, "userNextGo", false, "USER_NEXT_GO");
        public final static Property Token = new Property(8, String.class, "token", false, "TOKEN");
        public final static Property TokenTime = new Property(9, String.class, "tokenTime", false, "TOKEN_TIME");
        public final static Property DelectQuestion = new Property(10, String.class, "delectQuestion", false, "DELECT_QUESTION");
        public final static Property SkillData = new Property(11, String.class, "skillData", false, "SKILL_DATA");
        public final static Property ColoctData = new Property(12, String.class, "coloctData", false, "COLOCT_DATA");
        public final static Property CaseData = new Property(13, String.class, "caseData", false, "CASE_DATA");
        public final static Property WrongDataSkill = new Property(14, String.class, "WrongDataSkill", false, "WRONG_DATA_SKILL");
        public final static Property WrongDataColoct = new Property(15, String.class, "WrongDataColoct", false, "WRONG_DATA_COLOCT");
        public final static Property WrongDataCase = new Property(16, String.class, "WrongDataCase", false, "WRONG_DATA_CASE");
        public final static Property LookVideolist = new Property(17, String.class, "lookVideolist", false, "LOOK_VIDEOLIST");
    }

    private final UserConverent voConverter = new UserConverent();
    private final UserLookConverent skillDataConverter = new UserLookConverent();
    private final UserLookConverent coloctDataConverter = new UserLookConverent();
    private final UserLookConverent caseDataConverter = new UserLookConverent();
    private final UserLookConverent WrongDataSkillConverter = new UserLookConverent();
    private final UserLookConverent WrongDataColoctConverter = new UserLookConverent();
    private final UserLookConverent WrongDataCaseConverter = new UserLookConverent();
    private final UserLookVideoConverent lookVideolistConverter = new UserLookVideoConverent();

    public UserInfomDbDao(DaoConfig config) {
        super(config);
    }
    
    public UserInfomDbDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER_INFOM_DB\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"VO\" TEXT," + // 1: vo
                "\"MOID\" TEXT," + // 2: moid
                "\"SKILL_BOOK\" INTEGER NOT NULL ," + // 3: SkillBook
                "\"COLLIGATE_BOOK\" INTEGER NOT NULL ," + // 4: ColligateBook
                "\"CASE_BOOK\" INTEGER NOT NULL ," + // 5: CaseBook
                "\"SHOW_DAY_OR_NIGHT\" TEXT," + // 6: ShowDayOrNight
                "\"USER_NEXT_GO\" INTEGER NOT NULL ," + // 7: userNextGo
                "\"TOKEN\" TEXT," + // 8: token
                "\"TOKEN_TIME\" TEXT," + // 9: tokenTime
                "\"DELECT_QUESTION\" TEXT," + // 10: delectQuestion
                "\"SKILL_DATA\" TEXT," + // 11: skillData
                "\"COLOCT_DATA\" TEXT," + // 12: coloctData
                "\"CASE_DATA\" TEXT," + // 13: caseData
                "\"WRONG_DATA_SKILL\" TEXT," + // 14: WrongDataSkill
                "\"WRONG_DATA_COLOCT\" TEXT," + // 15: WrongDataColoct
                "\"WRONG_DATA_CASE\" TEXT," + // 16: WrongDataCase
                "\"LOOK_VIDEOLIST\" TEXT);"); // 17: lookVideolist
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER_INFOM_DB\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, UserInfomDb entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        UserInfomVo vo = entity.getVo();
        if (vo != null) {
            stmt.bindString(2, voConverter.convertToDatabaseValue(vo));
        }
 
        String moid = entity.getMoid();
        if (moid != null) {
            stmt.bindString(3, moid);
        }
        stmt.bindLong(4, entity.getSkillBook() ? 1L: 0L);
        stmt.bindLong(5, entity.getColligateBook() ? 1L: 0L);
        stmt.bindLong(6, entity.getCaseBook() ? 1L: 0L);
 
        String ShowDayOrNight = entity.getShowDayOrNight();
        if (ShowDayOrNight != null) {
            stmt.bindString(7, ShowDayOrNight);
        }
        stmt.bindLong(8, entity.getUserNextGo() ? 1L: 0L);
 
        String token = entity.getToken();
        if (token != null) {
            stmt.bindString(9, token);
        }
 
        String tokenTime = entity.getTokenTime();
        if (tokenTime != null) {
            stmt.bindString(10, tokenTime);
        }
 
        String delectQuestion = entity.getDelectQuestion();
        if (delectQuestion != null) {
            stmt.bindString(11, delectQuestion);
        }
 
        List skillData = entity.getSkillData();
        if (skillData != null) {
            stmt.bindString(12, skillDataConverter.convertToDatabaseValue(skillData));
        }
 
        List coloctData = entity.getColoctData();
        if (coloctData != null) {
            stmt.bindString(13, coloctDataConverter.convertToDatabaseValue(coloctData));
        }
 
        List caseData = entity.getCaseData();
        if (caseData != null) {
            stmt.bindString(14, caseDataConverter.convertToDatabaseValue(caseData));
        }
 
        List WrongDataSkill = entity.getWrongDataSkill();
        if (WrongDataSkill != null) {
            stmt.bindString(15, WrongDataSkillConverter.convertToDatabaseValue(WrongDataSkill));
        }
 
        List WrongDataColoct = entity.getWrongDataColoct();
        if (WrongDataColoct != null) {
            stmt.bindString(16, WrongDataColoctConverter.convertToDatabaseValue(WrongDataColoct));
        }
 
        List WrongDataCase = entity.getWrongDataCase();
        if (WrongDataCase != null) {
            stmt.bindString(17, WrongDataCaseConverter.convertToDatabaseValue(WrongDataCase));
        }
 
        List lookVideolist = entity.getLookVideolist();
        if (lookVideolist != null) {
            stmt.bindString(18, lookVideolistConverter.convertToDatabaseValue(lookVideolist));
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, UserInfomDb entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        UserInfomVo vo = entity.getVo();
        if (vo != null) {
            stmt.bindString(2, voConverter.convertToDatabaseValue(vo));
        }
 
        String moid = entity.getMoid();
        if (moid != null) {
            stmt.bindString(3, moid);
        }
        stmt.bindLong(4, entity.getSkillBook() ? 1L: 0L);
        stmt.bindLong(5, entity.getColligateBook() ? 1L: 0L);
        stmt.bindLong(6, entity.getCaseBook() ? 1L: 0L);
 
        String ShowDayOrNight = entity.getShowDayOrNight();
        if (ShowDayOrNight != null) {
            stmt.bindString(7, ShowDayOrNight);
        }
        stmt.bindLong(8, entity.getUserNextGo() ? 1L: 0L);
 
        String token = entity.getToken();
        if (token != null) {
            stmt.bindString(9, token);
        }
 
        String tokenTime = entity.getTokenTime();
        if (tokenTime != null) {
            stmt.bindString(10, tokenTime);
        }
 
        String delectQuestion = entity.getDelectQuestion();
        if (delectQuestion != null) {
            stmt.bindString(11, delectQuestion);
        }
 
        List skillData = entity.getSkillData();
        if (skillData != null) {
            stmt.bindString(12, skillDataConverter.convertToDatabaseValue(skillData));
        }
 
        List coloctData = entity.getColoctData();
        if (coloctData != null) {
            stmt.bindString(13, coloctDataConverter.convertToDatabaseValue(coloctData));
        }
 
        List caseData = entity.getCaseData();
        if (caseData != null) {
            stmt.bindString(14, caseDataConverter.convertToDatabaseValue(caseData));
        }
 
        List WrongDataSkill = entity.getWrongDataSkill();
        if (WrongDataSkill != null) {
            stmt.bindString(15, WrongDataSkillConverter.convertToDatabaseValue(WrongDataSkill));
        }
 
        List WrongDataColoct = entity.getWrongDataColoct();
        if (WrongDataColoct != null) {
            stmt.bindString(16, WrongDataColoctConverter.convertToDatabaseValue(WrongDataColoct));
        }
 
        List WrongDataCase = entity.getWrongDataCase();
        if (WrongDataCase != null) {
            stmt.bindString(17, WrongDataCaseConverter.convertToDatabaseValue(WrongDataCase));
        }
 
        List lookVideolist = entity.getLookVideolist();
        if (lookVideolist != null) {
            stmt.bindString(18, lookVideolistConverter.convertToDatabaseValue(lookVideolist));
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public UserInfomDb readEntity(Cursor cursor, int offset) {
        UserInfomDb entity = new UserInfomDb( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : voConverter.convertToEntityProperty(cursor.getString(offset + 1)), // vo
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // moid
            cursor.getShort(offset + 3) != 0, // SkillBook
            cursor.getShort(offset + 4) != 0, // ColligateBook
            cursor.getShort(offset + 5) != 0, // CaseBook
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // ShowDayOrNight
            cursor.getShort(offset + 7) != 0, // userNextGo
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // token
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // tokenTime
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // delectQuestion
            cursor.isNull(offset + 11) ? null : skillDataConverter.convertToEntityProperty(cursor.getString(offset + 11)), // skillData
            cursor.isNull(offset + 12) ? null : coloctDataConverter.convertToEntityProperty(cursor.getString(offset + 12)), // coloctData
            cursor.isNull(offset + 13) ? null : caseDataConverter.convertToEntityProperty(cursor.getString(offset + 13)), // caseData
            cursor.isNull(offset + 14) ? null : WrongDataSkillConverter.convertToEntityProperty(cursor.getString(offset + 14)), // WrongDataSkill
            cursor.isNull(offset + 15) ? null : WrongDataColoctConverter.convertToEntityProperty(cursor.getString(offset + 15)), // WrongDataColoct
            cursor.isNull(offset + 16) ? null : WrongDataCaseConverter.convertToEntityProperty(cursor.getString(offset + 16)), // WrongDataCase
            cursor.isNull(offset + 17) ? null : lookVideolistConverter.convertToEntityProperty(cursor.getString(offset + 17)) // lookVideolist
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, UserInfomDb entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setVo(cursor.isNull(offset + 1) ? null : voConverter.convertToEntityProperty(cursor.getString(offset + 1)));
        entity.setMoid(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setSkillBook(cursor.getShort(offset + 3) != 0);
        entity.setColligateBook(cursor.getShort(offset + 4) != 0);
        entity.setCaseBook(cursor.getShort(offset + 5) != 0);
        entity.setShowDayOrNight(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setUserNextGo(cursor.getShort(offset + 7) != 0);
        entity.setToken(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setTokenTime(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setDelectQuestion(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setSkillData(cursor.isNull(offset + 11) ? null : skillDataConverter.convertToEntityProperty(cursor.getString(offset + 11)));
        entity.setColoctData(cursor.isNull(offset + 12) ? null : coloctDataConverter.convertToEntityProperty(cursor.getString(offset + 12)));
        entity.setCaseData(cursor.isNull(offset + 13) ? null : caseDataConverter.convertToEntityProperty(cursor.getString(offset + 13)));
        entity.setWrongDataSkill(cursor.isNull(offset + 14) ? null : WrongDataSkillConverter.convertToEntityProperty(cursor.getString(offset + 14)));
        entity.setWrongDataColoct(cursor.isNull(offset + 15) ? null : WrongDataColoctConverter.convertToEntityProperty(cursor.getString(offset + 15)));
        entity.setWrongDataCase(cursor.isNull(offset + 16) ? null : WrongDataCaseConverter.convertToEntityProperty(cursor.getString(offset + 16)));
        entity.setLookVideolist(cursor.isNull(offset + 17) ? null : lookVideolistConverter.convertToEntityProperty(cursor.getString(offset + 17)));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(UserInfomDb entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(UserInfomDb entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(UserInfomDb entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
