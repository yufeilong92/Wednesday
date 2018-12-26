package com.xuechuan.xcedu.ui.bank;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xuechuan.xcedu.R;
import com.xuechuan.xcedu.adapter.bank.CaseAnswerAdapter;
import com.xuechuan.xcedu.adapter.bank.GmGridViewAdapter;
import com.xuechuan.xcedu.base.BaseActivity;
import com.xuechuan.xcedu.base.DataMessageVo;
import com.xuechuan.xcedu.fragment.GmReadTwoFragment;
import com.xuechuan.xcedu.sqlitedb.DoBankSqliteHelp;
import com.xuechuan.xcedu.sqlitedb.DoLogSqlteHelp;
import com.xuechuan.xcedu.sqlitedb.DoLogProgressSqliteHelp;

import com.xuechuan.xcedu.sqlitedb.QuestionSqliteHelp;
import com.xuechuan.xcedu.sqlitedb.UserInfomDbHelp;
import com.xuechuan.xcedu.utils.DialogUtil;
import com.xuechuan.xcedu.utils.EncryptionUtil;
import com.xuechuan.xcedu.utils.GmReadColorManger;
import com.xuechuan.xcedu.utils.GmTextUtil;
import com.xuechuan.xcedu.utils.T;
import com.xuechuan.xcedu.vo.CaseCardVo;
import com.xuechuan.xcedu.vo.SqliteVo.DoBankSqliteVo;
import com.xuechuan.xcedu.vo.SqliteVo.ErrorSqliteVo;
import com.xuechuan.xcedu.vo.SqliteVo.DoLogProgreeSqliteVo;
import com.xuechuan.xcedu.vo.SqliteVo.QuestionSqliteVo;
import com.xuechuan.xcedu.vo.SqliteVo.UserInfomSqliteVo;
import com.xuechuan.xcedu.weight.CommonPopupWindow;
import com.xuechuan.xcedu.weight.ReaderViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: TextActivity
 * @Package com.xuechuan.xcedu.ui.bank
 * @Description: 简答题
 * @author: L-BackPacker
 * @date: 2018.12.05 下午 3:21
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018.12.05
 */
public class CaseNewTextActivity extends BaseActivity implements View.OnClickListener, GmReadTwoFragment.notification {
    /**
     * 章节id
     */
    private static String CHAPTER_ID = "com.xuechuan.xcedu.ui.bank.CHAPTER_ID";
    /**
     * 科目
     */
    private static String COUERSID = "com.xuechuan.xcedu.ui.bank.COUERSID";
    private Context mContext;

    private int mChapter_Id;
    private String mCouers_id;
    private ArrayList<QuestionSqliteVo> lists;
    private DoBankSqliteHelp mDoBankHelp;

    private GmReadTwoFragment mReadFragment;
    private ImageView mIvTextBarBack;
    private ImageView mIvTextBarTimeImg;
    private TextView mActivityTitleText;
    private LinearLayout mLlTextBarTitle;
    private ImageView mIvTextBarDelect;
    private ImageView mIvTextBarMore;
    private LinearLayout mLlTextTitleBar;
    private View mVGmReadLine;
    private ReaderViewPager mReaderViewPager;
    private ImageView mShadowView;
    private FrameLayout mFlContentLayoutOne;
    private TextView mTvTextCollect;
    private ImageView mIvTextMenu;
    private TextView mTvTextQid;
    private TextView mTvTextAllqid;
    private TextView mTvTextShare;
    private LinearLayout mLiTextNavbar;
    private LinearLayout mLlNewtextBar;
    private LinearLayout mSlidingLayout;
    private GmTextUtil mTextUtil;
    private CommonPopupWindow mPopAnswer;
    private GmReadColorManger mColorManger;
    private DoLogProgressSqliteHelp mDoLogProgressSqliteHelp;
    private DialogUtil mDialogUtil;
    private QuestionSqliteVo mQuestionSqliteVo;
    private View mVGmBarLine;
    private DoLogSqlteHelp mDoLoghelp;
    private ArrayList<CaseCardVo> mCardLists = new ArrayList<>();
    /**
     * @param context
     * @param id        章节id
     * @param mCouersid 科目id
     * @return
     */
    public static Intent start_Intent(Context context, int id, String mCouersid) {
        Intent intent = new Intent(context, CaseNewTextActivity.class);
        intent.putExtra(CHAPTER_ID, id);
        intent.putExtra(COUERSID, mCouersid);
        return intent;
    }

/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_text);
        initView();
    }
*/

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_new_text);
        if (getIntent() != null) {
            //章节id
            mChapter_Id = getIntent().getIntExtra(CHAPTER_ID, -1);
            //科目id
            mCouers_id = getIntent().getStringExtra(COUERSID);

        }
        initView();
        initUtils();
        lists = getLists();
        //初始化翻页效果
        initReadViewPager();
        //显示下表
        initData(0);
        //显示对话框
        doShowDialogEvent();

    }

    /**
     * 显示继续答题对话框
     */
    private void doShowDialogEvent() {
        if (mDoLogProgressSqliteHelp != null) {
            if (lists != null && !lists.isEmpty()) {
                final DoLogProgreeSqliteVo look = mDoLogProgressSqliteHelp.findLookWithTidChapterId(
                        mChapter_Id, Integer.parseInt(mCouers_id), DataMessageVo.CHAPTER_ONE);
                if (look == null) return;
                mDialogUtil.showContinueDialog(mContext, look.getNumber());
                mDialogUtil.setContinueClickListener(new DialogUtil.onContincueClickListener() {
                    @Override
                    public void onCancelClickListener() {
                        if (mReaderViewPager != null) {
                            mReaderViewPager.setCurrentItem(0, true);
                            mDoLogProgressSqliteHelp.deleteLookItem(look.getId());
                        }
                    }

                    @Override
                    public void onNextClickListener() {
                        if (mReaderViewPager != null)
                            mReaderViewPager.setCurrentItem(Integer.parseInt(look.getNumber()) - 1, true);
                    }
                });
            }
        }

    }

    private void initUtils() {
        mTextUtil = GmTextUtil.get_Instance(mContext);
        mDoBankHelp = DoBankSqliteHelp.get_Instance(mContext);
        //观看记录表
        mDoLogProgressSqliteHelp = DoLogProgressSqliteHelp.get_Instance(mContext);
        mDialogUtil = DialogUtil.getInstance();
        //用户做题记录表（去重的）
        mDoLoghelp = DoLogSqlteHelp.getInstance(mContext);
    }


    private void initData(int index) {
        mTvTextAllqid.setText(String.valueOf(lists.size()));
        mTvTextQid.setText(String.valueOf(++index));
    }


    /**
     * 设置答题卡布局
     */
    private void showAnswerCardLayout() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenHeight = metrics.heightPixels;

        mPopAnswer = new CommonPopupWindow(this, R.layout.pop_case_item_answer, ViewGroup.LayoutParams.MATCH_PARENT, (int) (screenHeight * 0.7)) {
            private RecyclerView mRlvCasenewText;
            private Button mBtnPopAnswerSumbit;
            private TextView mTvTextPopRegression;
            private TextView mTvTextPopError;
            private TextView mTvTextPopRight;
            private TextView mTvPopCount;
            private TextView mTvLine;
            private TextView mTvPopNew;

            @Override
            protected void initView() {
                View view = getContentView();
                mRlvCasenewText = (RecyclerView) view.findViewById(R.id.rlv_casenew_text);
                mBtnPopAnswerSumbit = (Button) view.findViewById(R.id.btn_pop_answer_sumbit);
                mTvTextPopRegression = (TextView) view.findViewById(R.id.tv_text_pop_regression);
                mTvTextPopError = view.findViewById(R.id.tv_text_pop_error);
                mTvTextPopRight = view.findViewById(R.id.tv_text_pop_right);
                mTvPopCount = view.findViewById(R.id.tv_pop_count);
                mTvLine = view.findViewById(R.id.tv_line);
                mTvPopNew = view.findViewById(R.id.tv_pop_new);

                Log.e("=====", "initView: "+mCardLists );
            }


            private void doDataListEvent(ArrayList<QuestionSqliteVo> lists) {
                CaseCardVo caseCardVo = new CaseCardVo();
                //题干
                ArrayList<QuestionSqliteVo> vos = new ArrayList<>();
                if (lists == null || lists.isEmpty()) return;
                for (int i = 0; i < lists.size(); i++) {
                    QuestionSqliteVo vo = lists.get(i);
                    if (vo.getQuestiontype() == 4 || vo.getQuestiontype() == 5) {
                        ArrayList<Integer> integers = groupData(vo.getId(), lists);
                        if (integers == null || integers.isEmpty()) {
                            caseCardVo.setType(1);
                            caseCardVo.setVo(vo);
                            caseCardVo.setList(vos);
                            mCardLists.add(caseCardVo);
                            lists.remove(i);
                            doDataListEvent(lists);
                        } else {
                            caseCardVo.setType(2);
                            caseCardVo.setVo(vo);
                            ArrayList<QuestionSqliteVo> integerList = getIntegerList(integers, lists);
                            caseCardVo.setList(integerList);
                            mCardLists.add(caseCardVo);
                            lists = getDeleteIntegerList(integers, lists);
                            doDataListEvent(lists);
                        }
                    }

                }

            }

            private ArrayList<QuestionSqliteVo> getIntegerList(ArrayList<Integer> integers, ArrayList<QuestionSqliteVo> datas) {
                if (integers == null || integers.isEmpty()) return null;
                if (datas == null || datas.isEmpty()) return null;
                ArrayList<QuestionSqliteVo> sqliteVos = new ArrayList<>();
                for (int i = 0; i < datas.size(); i++) {
                    QuestionSqliteVo vo = datas.get(i);
                    for (int k = 0; k < integers.size(); k++) {
                        Integer integer = integers.get(k);
                        if (vo.getId() == integer) {
                            sqliteVos.add(vo);
                        }
                    }
                }
                return sqliteVos;
            }

            private ArrayList<QuestionSqliteVo> getDeleteIntegerList(ArrayList<Integer> integers, ArrayList<QuestionSqliteVo> datas) {
                if (integers == null || integers.isEmpty()) return null;
                if (datas == null || datas.isEmpty()) return null;
                for (int i = 0; i < datas.size(); i++) {
                    QuestionSqliteVo vo = datas.get(i);
                    for (int k = 0; k < integers.size(); k++) {
                        Integer integer = integers.get(k);
                        if (vo.getId() == integer) {
                            datas.remove(i);
                        }
                    }
                }
                return datas;
            }


            private ArrayList<Integer> groupData(int id, ArrayList<QuestionSqliteVo> mData) {
                ArrayList<Integer> ints = new ArrayList<>();
                for (int i = 0; i < mData.size(); i++) {
                    QuestionSqliteVo vo = mData.get(i);
                    if (vo.getParent_id() == id) {
                        ints.add(vo.getId());
                    }
                }
                return ints;
            }

            @Override
            protected void initEvent() {
                if (mColorManger != null) {
                    mTvPopNew.setTextColor(mColorManger.getmTextTitleColor());
                    mTvPopCount.setTextColor(mColorManger.getmTextTitleColor());
                    mTvTextPopRight.setTextColor(mColorManger.getmTextFuColor());
                    mTvTextPopRegression.setTextColor(mColorManger.getmTextFuColor());
                    mTvLine.setTextColor(mColorManger.getmTextTitleColor());
                }
                mCardLists.clear();
                doDataListEvent(lists);
                mTvPopNew.setText(String.valueOf(curPosition2 + 1));
                mTvPopCount.setText(String.valueOf(lists.size()));
                bindGridViewAdapter();
            }

            @Override
            protected void initWindow() {
                super.initWindow();
                PopupWindow instance = getPopupWindow();
                instance.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        mTextUtil.setBackgroundAlpha(1f, CaseNewTextActivity.this);
                    }
                });
            }

            private void bindGridViewAdapter() {
                setGridLayoutManger(mContext,mRlvCasenewText,1);
                CaseAnswerAdapter adapter = new CaseAnswerAdapter(mContext, mCardLists);
                mRlvCasenewText.setAdapter(adapter);
            }
        };
        mPopAnswer.showAtLocation(mSlidingLayout, Gravity.BOTTOM, 0, 0);
        mTextUtil.setBackgroundAlpha(0.5f, CaseNewTextActivity.this);
    }

    private int prePosition2;
    private int curPosition2;

    private void initReadViewPager() {
        mReaderViewPager.setAdapter(new GmFragmentAdpater(getSupportFragmentManager(), mContext, lists, mCouers_id));
        mReaderViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mShadowView.setTranslationX(mReaderViewPager.getWidth() - positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                curPosition2 = position;
                prePosition2 = curPosition2;
                initData(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mReaderViewPager.setOffscreenPageLimit(1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_text_menu://菜单
                showAnswerCardLayout();
                break;
            case R.id.iv_text_bar_more://更多
                if (mReadFragment != null) {
                    mReadFragment.showGmSetting();
                }
                break;
            case R.id.iv_text_bar_back://返回
                this.finish();
                break;


        }
    }

    private ArrayList<DoBankSqliteVo> findAllDoDatas() {
        ArrayList<DoBankSqliteVo> list = new ArrayList<>();
  /*      for (int i = 0; i < lists.size(); i++) {
            QuestionSqliteVo vo = lists.get(i);
            DoBankSqliteVo doVo = mDoBankHelp.queryWQid(vo.getQuestion_id());
            if (doVo != null)
                list.add(doVo);
        }*/
        ArrayList<DoBankSqliteVo> doBankSqliteVos = mDoBankHelp.finDAllUserDoText();
        if (doBankSqliteVos == null || doBankSqliteVos.isEmpty()) return list;
        for (int i = 0; i < lists.size(); i++) {
            QuestionSqliteVo vo = lists.get(i);
            for (int k = 0; k < doBankSqliteVos.size(); k++) {
                DoBankSqliteVo sqliteVo = doBankSqliteVos.get(k);
                if (vo.getQuestion_id() == sqliteVo.getQuestion_id()) {
                    list.add(sqliteVo);
                }
            }

        }
        return list;
    }

    private void initView() {
        mContext = this;
        mIvTextBarBack = (ImageView) findViewById(R.id.iv_text_bar_back);
        mIvTextBarBack.setOnClickListener(this);
        mIvTextBarTimeImg = (ImageView) findViewById(R.id.iv_text_bar_time_img);
        mIvTextBarTimeImg.setOnClickListener(this);
        mActivityTitleText = (TextView) findViewById(R.id.activity_title_text);
        mActivityTitleText.setOnClickListener(this);
        mLlTextBarTitle = (LinearLayout) findViewById(R.id.ll_text_bar_title);
        mLlTextBarTitle.setOnClickListener(this);
        mIvTextBarDelect = (ImageView) findViewById(R.id.iv_text_bar_delect);
        mIvTextBarDelect.setOnClickListener(this);
        mIvTextBarMore = (ImageView) findViewById(R.id.iv_text_bar_more);
        mIvTextBarMore.setOnClickListener(this);
        mLlTextTitleBar = (LinearLayout) findViewById(R.id.ll_text_title_bar);
        mLlTextTitleBar.setOnClickListener(this);
        mVGmReadLine = (View) findViewById(R.id.v_gm_read_line);
        mVGmReadLine.setOnClickListener(this);
        mReaderViewPager = (ReaderViewPager) findViewById(R.id.readerViewPager);
        mReaderViewPager.setOnClickListener(this);
        mShadowView = (ImageView) findViewById(R.id.shadowView);
        mShadowView.setOnClickListener(this);
        mFlContentLayoutOne = (FrameLayout) findViewById(R.id.fl_content_layout_one);
        mFlContentLayoutOne.setOnClickListener(this);
        mTvTextCollect = (TextView) findViewById(R.id.tv_text_collect);
        mTvTextCollect.setOnClickListener(this);
        mIvTextMenu = (ImageView) findViewById(R.id.iv_text_menu);
        mIvTextMenu.setOnClickListener(this);
        mTvTextQid = (TextView) findViewById(R.id.tv_text_qid);
        mTvTextQid.setOnClickListener(this);
        mTvTextAllqid = (TextView) findViewById(R.id.tv_text_allqid);
        mTvTextAllqid.setOnClickListener(this);
        mTvTextShare = (TextView) findViewById(R.id.tv_text_share);
        mTvTextShare.setOnClickListener(this);
        mLiTextNavbar = (LinearLayout) findViewById(R.id.li_text_navbar);
        mLiTextNavbar.setOnClickListener(this);
        mLlNewtextBar = (LinearLayout) findViewById(R.id.ll_newtext_bar);
        mLlNewtextBar.setOnClickListener(this);
        mSlidingLayout = (LinearLayout) findViewById(R.id.sliding_layout);
        mSlidingLayout.setOnClickListener(this);
        mVGmBarLine = (View) findViewById(R.id.v_gm_bar_line);
        mVGmBarLine.setOnClickListener(this);
    }

    public ArrayList<QuestionSqliteVo> getLists() {
        QuestionSqliteHelp help = QuestionSqliteHelp.get_Instance(mContext);
        ArrayList<QuestionSqliteVo> questionListData = help.getChapterQuestionListData(mChapter_Id, mCouers_id);
        if (questionListData != null && !questionListData.isEmpty()) {
            for (int i = 0; i < questionListData.size(); i++) {
                QuestionSqliteVo vo = questionListData.get(i);
                String d = EncryptionUtil.D(vo.getQuestion());
                vo.setQuestionStr(d);
                String keyword = EncryptionUtil.D(vo.getKeywords());
                vo.setKeywordStr(keyword);
                String explain = EncryptionUtil.D(vo.getExplained());
                vo.setExplainedStr(explain);
            }
        }
        return questionListData;
    }

    @Override
    public void saveUserDoLog(DoBankSqliteVo vo) {
        mDoBankHelp.addDoBankItem(vo);

    }

    @Override
    public DoBankSqliteVo getUserDoLog(int quesiton_id) {
        if (mDoBankHelp == null) return null;
        DoBankSqliteVo vo = mDoBankHelp.queryWQid(quesiton_id);
        return vo;
    }

    @Override
    public void deleteUserDolog(int quesiton_id) {
        mDoBankHelp.deleteBankWithQuestid(quesiton_id);
    }

    /**
     * 下一题
     */
    @Override
    public void doRightGo() {
        if (mReaderViewPager == null) return;
        int currentItem = mReaderViewPager.getCurrentItem();
        currentItem = currentItem + 1;
        if (currentItem < 0) {
            T.showToast(mContext, "已经是最后一题");
            currentItem = 0;
        }
        mReaderViewPager.setCurrentItem(currentItem, true);
    }

    @Override
    public void changerColor(GmReadColorManger colorManger) {
        this.mColorManger = colorManger;
        mLiTextNavbar.setBackgroundColor(colorManger.getmLayoutBgColor());
        mTvTextCollect.setTextColor(colorManger.getmTextFuColor());
        mTvTextShare.setTextColor(colorManger.getmTextFuColor());
        mTvTextQid.setTextColor(colorManger.getmTextRedColor());
        mTvTextAllqid.setTextColor(colorManger.getmTextRedColor());
        mLlTextTitleBar.setBackgroundColor(colorManger.getmLayoutBgColor());
        mActivityTitleText.setTextColor(colorManger.getmTextTitleColor());
        mLlTextTitleBar.setBackgroundColor(colorManger.getmLayoutBgColor());
        mVGmReadLine.setBackgroundColor(colorManger.getmCutLineColor());
        //标题
        mLlTextTitleBar.setBackgroundColor(colorManger.getmLayoutBgColor());
        mVGmBarLine.setBackgroundColor(colorManger.getmCutLineColor());

    }

    //用户错题表
    @Override
    public void doErrorLog(ErrorSqliteVo vo) {

    }


    @Override
    public DoBankSqliteVo queryUserData(int qustion_id) {
        if (mDoBankHelp == null) return null;
        return mDoBankHelp.queryWQid(qustion_id);
    }

    public class GmFragmentAdpater extends FragmentPagerAdapter {

        private final String mCourseid;
        private Context mContext;
        private List<?> mListDatas;

        public GmFragmentAdpater(FragmentManager fm, Context mContext, List<?> mListDatas, String coursid) {
            super(fm);
            this.mListDatas = mListDatas;
            this.mContext = mContext;
            this.mCourseid = coursid;
        }

        @Override
        public Fragment getItem(int position) {
            if (mListDatas.size() == 0) {
                mReadFragment = GmReadTwoFragment.newInstance(null, position, mCourseid);
            } else {
                mQuestionSqliteVo = (QuestionSqliteVo) mListDatas.get(position);

                mReadFragment = GmReadTwoFragment.newInstance(mQuestionSqliteVo, position, mCourseid);
            }
            return mReadFragment;
        }

        @Override
        public int getCount() {
            if (mListDatas.size() == 0) {
                return 1;
            } else
                return mListDatas.size();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCardLists=null;
        doDoText();
        if (mDoBankHelp != null) {
            mDoBankHelp.delelteTable();
        }
    }

    private void doDoText() {
        if (curPosition2 == 0) return;
        if (mQuestionSqliteVo != null) {
            UserInfomDbHelp help = UserInfomDbHelp.get_Instance(mContext);
            UserInfomSqliteVo vo = help.findUserInfomVo();
            DoLogProgreeSqliteVo logSqliteVo = new DoLogProgreeSqliteVo();
            logSqliteVo.setChapterid(mChapter_Id);
            logSqliteVo.setKid(Integer.parseInt(mCouers_id));
            logSqliteVo.setTextid(mQuestionSqliteVo.getQuestion_id());
            logSqliteVo.setNumber(String.valueOf(curPosition2 + 1));
            logSqliteVo.setUserid(String.valueOf(vo.getSaffid()));
            logSqliteVo.setAllNumber(String.valueOf(lists.size()));
            logSqliteVo.setType(DataMessageVo.CHAPTER_ONE);
            mDoLogProgressSqliteHelp.addDoLookItem(logSqliteVo);
        }

    }
}