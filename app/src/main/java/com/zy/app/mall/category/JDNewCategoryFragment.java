package com.zy.app.mall.category;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.category.fragment.ConjoinedCategoryFragment;
import com.jingdong.app.mall.category.fragment.OrdinaryL2CategoryFragment;
import com.jingdong.app.mall.category.fragment.RecommendL2CategoryFragment;
import com.jingdong.app.mall.color.CameraActivity;
import com.jingdong.app.mall.more.VoiceSearchActivity;
import com.jingdong.app.mall.more.VoiceSearchLayout;
import com.jingdong.app.mall.navigationbar.NavigationOptHelper;
import com.jingdong.app.mall.personel.MyMessageBox;
import com.jingdong.app.mall.personel.a.a.PersonalMessageManager;
import com.jingdong.app.mall.search.CameraPurchaseActivity;
import com.jingdong.app.mall.utils.CommonUtil;
import com.jingdong.app.mall.utils.LoginUser;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.config.Configuration;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.utils.ExceptionReporter;
import com.jingdong.common.utils.HttpGroup;
import com.jingdong.common.utils.JDFrescoUtils;
import com.jingdong.jdma.model.UserInfoModel;
import com.jingdong.lib.zxing.client.android.CaptureActivity;
import com.zy.app.mall.R;
import com.zy.app.mall.basic.JDTaskModule;
import com.zy.app.mall.category.adapter.ILeftAdapterListener;
import com.zy.app.mall.category.adapter.LeftListAdapter;
import com.zy.app.mall.category.b.RightColumnBase;
import com.zy.app.mall.category.b.RightListColumn;
import com.zy.app.mall.category.c.CatelogyUtil;
import com.zy.app.mall.category.fragment.L2CategoryFragment;
import com.zy.app.mall.navigationbar.JDTabFragment;
import com.zy.app.mall.personel.a.a.PersonalMessageChannel;
import com.zy.app.mall.personel.a.a.PersonalMessageObserver;
import com.zy.app.mall.searchRefactor.view.Activity.SearchActivity;
import com.zy.common.e.ConfigUtil;
import com.zy.common.entity.Catelogy;
import com.zy.common.entity.SourceEntity;
import com.zy.common.utils.DPIUtil;
import com.zy.common.utils.FileUtils;
import com.zy.common.utils.ImageUtil;
import com.zy.common.utils.JDMtaUtils;
import com.zy.common.utils.JSONArrayProxy;
import com.zy.common.utils.Log;
import com.zy.common.utils.StatisticsReportUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Robin on 2016/5/19.
 */
public class JDNewCategoryFragment extends JDTabFragment implements PersonalMessageObserver {

    private static JDNewCategoryFragment instance;
    private static final String TAG = JDNewCategoryFragment.class.getSimpleName();
    private ArrayList<Catelogy> A = new ArrayList();
    private List<RightColumnBase> B = new ArrayList();
    private JSONArrayProxy C = null;
    private String D = null;
    private String E = null;
    private int F;
    private String G = null;
    private String H;
    private View sublist_loading_error_tips; //I
    private Button jd_tip_button;   //J
    private ImageView jd_tip_image;    //K
    private View L; //L
    private View M; //M
    private Button N;   //N
    private ImageView O;    //O
    private BaseActivity P; //P
    private View mCategoryFragmentLayout;
    private boolean r1 = false;
    private boolean S = false;
    private List<String> T = new ArrayList();
    private boolean U = false;
    private String V;
    private boolean W = false;
    private boolean X;
    private boolean Y;
    private boolean Z;
    View a;
    private boolean aa;
    private Fragment ab = null;
    private String ac = null;
    AutoCompleteTextView search_text; //b
    RelativeLayout c;   //c
    TextView d; //d
    SimpleDraweeView e; //e
    ImageView f;    //f
    HashMap<String, ArrayList<com.zy.app.mall.category.b.RightColumnBase>> g;
    BroadcastReceiver h;
    protected String i = "";
    protected String j = "";
    protected boolean k = true;
    View.OnTouchListener l = new View.OnTouchListener(){//new n(this);
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN)
            {
                //invoke-direct {p0, v0, v0}, Lcom/jingdong/app/mall/category/JDNewCategoryFragment;->a(Ljava/lang/String;Ljava/lang/String;)V
                JDNewCategoryFragment.this.a((String)null);
                return true;
            }
            return false;
        }
    };
    private String o = "-1";
    private String p;
    private String q;
    private View r;
    private ListView left_list;
    private LeftListAdapter t;
    private View u;
    private boolean v = false;
    private int w = 0;
    private long x = 0L;
    private PopupWindow y;
    private View.OnClickListener z = new View.OnClickListener(){//q(this, 0);
        @Override
        public void onClick(View view) {
            Context context = null;
            view.setPressed(false);
            switch (view.getId())
            {
                default:
                    return;
                case R.id.search_voice:    //2131168592 7F070D50    sswitch_0
                    JDNewCategoryFragment.this.a("type", "voice");
                    return;
                case R.id.search_barcode_btn:    //2131165520   7F070150    sswitch_1
                    //JDNewCategoryFragment.r(this.a);
                    if(JDNewCategoryFragment.this.y != null && JDNewCategoryFragment.this.y.isShowing())
                        JDNewCategoryFragment.this.y.dismiss();
                    context = JDNewCategoryFragment.this.P.getBaseContext();
                    if (CatelogyUtil.a(context))
                        context.startActivity(new Intent(context, CaptureActivity.class));
                    JDMtaUtils.sendCommonData(JDNewCategoryFragment.this.P, "Scan_Scan", "", "", JDNewCategoryFragment.this, "", JDNewCategoryFragment.this.P.getClass(), "");
                    return;
                case R.id.search_camera_btn:    //2131165521    7F070151    sswitch_2
                    //JDNewCategoryFragment.r(this.a);
                    if(JDNewCategoryFragment.this.y != null && JDNewCategoryFragment.this.y.isShowing())
                        JDNewCategoryFragment.this.y.dismiss();
                    context = JDNewCategoryFragment.this.P.getBaseContext();
                    if (CatelogyUtil.a(context))
                        context.startActivity(new Intent(context, CameraPurchaseActivity.class));
                    JDMtaUtils.sendCommonData(JDNewCategoryFragment.this.P, "Scan_PhotoBuy", "", "", JDNewCategoryFragment.this, "", JDNewCategoryFragment.this.P.getClass(), "");
                    return;
                case R.id.color_shopping_btn:    //2131165523   7F070153    sswitch_3
                    //JDNewCategoryFragment.r(this.a);
                    if(JDNewCategoryFragment.this.y != null && JDNewCategoryFragment.this.y.isShowing())
                        JDNewCategoryFragment.this.y.dismiss();
                    context = JDNewCategoryFragment.this.P.getBaseContext();
                    if (CatelogyUtil.a(context))
                    {
                        Intent localIntent = new Intent(context, CameraActivity.class);
                        localIntent.putExtra("com.360buy:navigationDisplayFlag", -1);
                        context.startActivity(localIntent);
                    }
                    JDMtaUtils.sendCommonData(JDNewCategoryFragment.this.P, "Scan_ColorBuy", "", "", JDNewCategoryFragment.this, "", JDNewCategoryFragment.this.P.getClass(), "");
                    return;
            }

        }
    };

    public static JDNewCategoryFragment getInstance() {
        if (instance == null)
            synchronized (JDNewCategoryFragment.class) {
                if (instance == null)
                    instance = new JDNewCategoryFragment();
            }
        return instance;
    }


    private static List<String> a(List<RightColumnBase> paramList)
    {
        ArrayList<String> localArrayList = new ArrayList();
        Iterator iterator = paramList.iterator();
        while (iterator.hasNext())
        {
            RightListColumn localObject = (RightListColumn)iterator.next();
            if (localObject.a != 1)
                continue;

            for (int i1 = 0; i1 < localObject.c(); i1++)
            {
                String str = localObject.a(i1).getImgUrl();
                if (!TextUtils.isEmpty(str))
                    localArrayList.add(str);
            }
        }
        return localArrayList;
    }

    private void a(Fragment paramFragment)
    {
        if ((paramFragment != null) && (isAdded()))
        {
            FragmentTransaction localFragmentTransaction = getChildFragmentManager().beginTransaction();
            if (localFragmentTransaction != null)
            {
                localFragmentTransaction.replace(R.id.id_content, paramFragment);//2131166228
                localFragmentTransaction.commitAllowingStateLoss();
                this.ab = paramFragment;
            }
        }
    }

    private void a(String paramString)
    {
        if (this.w < 0)
        {
            Log.e(TAG, "Current Item is -1!!!");
            return;
        }
        this.ac = paramString;
        Object localObject2 = ((Catelogy)this.A.get(this.w)).getMergeCatalogs();
        if (FileUtils.b("/sdcard/jd_test_merged_category"))
        {
            if (this.w == 0)
            {
                ArrayList localObject1 = new ArrayList();
                for (int i1 = 0; i1 < 5; i1++)
                {
                    localObject2 = new Catelogy.MergedCatelogy();
                    Catelogy localCatelogy = (Catelogy)this.A.get(i1);
                    ((Catelogy.MergedCatelogy)localObject2).setId(localCatelogy.getcId());
                    ((Catelogy.MergedCatelogy)localObject2).setName(localCatelogy.getName());
                    ((Catelogy.MergedCatelogy)localObject2).setOrder(i1);
                    ((List)localObject1).add(localObject2);

                }
            }
        }
        if (localObject2 != null)
        {
            ConjoinedCategoryFragment conjoinedCategoryFragment = new ConjoinedCategoryFragment();
            conjoinedCategoryFragment.a(this.L);
            conjoinedCategoryFragment.a(this.sublist_loading_error_tips, this.jd_tip_image);
            conjoinedCategoryFragment.a(this.H, this.D, this.w);
            conjoinedCategoryFragment.a(this.P);
            conjoinedCategoryFragment.a((List)localObject2);
            a(conjoinedCategoryFragment);
            return;
        }else {
            OrdinaryL2CategoryFragment ordinaryL2CategoryFragment = OrdinaryL2CategoryFragment.b(paramString, this.D, this.w);
            ((OrdinaryL2CategoryFragment) ordinaryL2CategoryFragment).a(this.L);
            ((OrdinaryL2CategoryFragment) ordinaryL2CategoryFragment).a(this.sublist_loading_error_tips, this.jd_tip_image);
            ((L2CategoryFragment) ordinaryL2CategoryFragment).thisActivity = this.P;
            ((OrdinaryL2CategoryFragment) ordinaryL2CategoryFragment).a(new OrdinaryL2CategoryFragment._U(){//e(this)
                @Override
                public void a(List<String> paramList) {
                    JDNewCategoryFragment.this.T.clear();
                    JDNewCategoryFragment.this.T = paramList;
                }
            });
            ((OrdinaryL2CategoryFragment) ordinaryL2CategoryFragment).d(paramString);
            a(ordinaryL2CategoryFragment);
        }
    }

    private void a(String paramString1, String paramString2)
    {
        try
        {
            if ((VoiceSearchLayout.isUseJdCustomerVoiceService()) && ("type".equals(paramString1)) && ("voice".equals(paramString2)))
            {
                Context context = this.P.getBaseContext();
                Intent intent = new Intent(context, VoiceSearchActivity.class);
                intent.putExtra("isFromHome", true);
                intent.putExtra("source", new SourceEntity("Classification_VSearch", ""));
                intent.setFlags(268435456);
                context.startActivity(intent);
                JDMtaUtils.sendCommonData(this.P.getBaseContext(), "Classification_VSearch", "", "onClick", this, JDNewCategoryFragment.class.getSimpleName(), VoiceSearchActivity.class, "");
                return;
            }else {
                Context localContext = this.P.getBaseContext();
                Intent localIntent = new Intent(localContext, SearchActivity.class);
                if ((!TextUtils.isEmpty(paramString1)) && (!TextUtils.isEmpty(paramString2)))
                    localIntent.putExtra(paramString1, paramString2);
                localIntent.putExtra("isFromHome", true);
                localIntent.addFlags(65536);
                localIntent.putExtra("isNoAnimation", true);
                localIntent.setFlags(268435456);
                localContext.startActivity(localIntent);
                JDMtaUtils.sendCommonData(this.P, "Search_Search", "", "", this, "", SearchActivity.class, "");
                return;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void a(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
    {
        this.X = paramBoolean1;
        this.Y = paramBoolean2;
        this.Z = paramBoolean3;
        this.aa = paramBoolean4;
        RecommendL2CategoryFragment localRecommendL2CategoryFragment = null;
        if ((this.ab instanceof RecommendL2CategoryFragment))
            localRecommendL2CategoryFragment = (RecommendL2CategoryFragment)this.ab;
        if (localRecommendL2CategoryFragment == null)
        {
            localRecommendL2CategoryFragment = (RecommendL2CategoryFragment)RecommendL2CategoryFragment.a(this.p, this.q);
            localRecommendL2CategoryFragment.a(this.L);
            localRecommendL2CategoryFragment.a(this.sublist_loading_error_tips, this.jd_tip_image);
            localRecommendL2CategoryFragment.thisActivity = this.P;
            localRecommendL2CategoryFragment.a(new RecommendL2CategoryFragment._AF(){//m(this)
                @Override
                public void a(boolean paramBoolean1, boolean paramBoolean2, List<String> paramList, boolean paramBoolean3) {
                    JDNewCategoryFragment.this.v = paramBoolean2;
                    JDNewCategoryFragment.this.k = paramBoolean3;
                    if ((paramBoolean1) || (!paramBoolean3))
                        JDNewCategoryFragment.synthetic_a(JDNewCategoryFragment.this, paramBoolean3, false);
                    JDNewCategoryFragment.this.T.clear();
                    JDNewCategoryFragment.this.T = paramList;
                }
            });
        }
        else
        {
            localRecommendL2CategoryFragment.b(this.p, this.q);
        }
        localRecommendL2CategoryFragment.a(paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4);
        a(localRecommendL2CategoryFragment);
        return;
    }

    private boolean a(boolean paramBoolean, View paramView)
    {
        if (paramBoolean)
        {
            paramView.setVisibility(View.VISIBLE);//0
            paramView.setOnClickListener(this.z);
        }else
            paramView.setVisibility(View.GONE);//8;
        return true;
    }

    @Override
    public final void a(int paramInt1, int paramInt2)
    {
        if (Log.D)
            Log.d("navigation-click", TAG + "   old-->" + paramInt1 + " now-->" + paramInt2);
    }

    @SuppressLint({"InflateParams"})
    public final void b()
    {
        if ((!this.r1) && (isAdded()))
        {
            LeftListAdapter.LeftListViewHolder locald = new LeftListAdapter.LeftListViewHolder();
            View localView = LayoutInflater.from(this.P).inflate(R.layout.category_new_text_item, null);//2130903256
            locald.mText = ((TextView)localView.findViewById(R.id.text));//2131166233
            locald.mText.setText("推荐分类");
            locald.mText.setTextColor(getFragmentTextColor(R.color.category_new_red_font));//2131099877
            localView.setTag(locald);
            localView.setBackgroundResource(R.drawable.category_new_left_facous);//2130838453
            this.left_list.setAdapter(null);
            this.left_list.addHeaderView(localView);
            this.left_list.setAdapter(this.t);
            this.u = localView;
            this.r1 = true;
        }
        this.w = -1;
        if (this.t != null)
            this.t.a(-1);
        this.T.clear();
        this.T = a(this.B);
    }

    public String getPageParam() {
        int i3 = 0;
        String str;

        if (TextUtils.isEmpty(this.E))
            str = "0";
        else
            str = "1";
        if (this.A != null) {//if-nez v0, :cond_2
            //v2
            //:goto_3
            for (int i2 = 0; i2 < this.A.size(); i2++) {
                List<Catelogy.MergedCatelogy> localObject = this.A.get(i2).getMergeCatalogs();
                if ((localObject != null) && (((List) localObject).size() > 0)) {
                    i3 = 1;
                    break;
                }
            }
        }

        String localObject = "1";
        if (i3 == 0)
            localObject = "0";

        return str + "_" +  localObject;
    }

    public void onAttach(Activity paramActivity)
    {
        this.P = ((BaseActivity)paramActivity);
        super.onAttach(paramActivity);
    }

    @Override
    public View onCreateViews(LayoutInflater paramLayoutInflater, Bundle paramBundle)
    {
        this.mCategoryFragmentLayout = paramLayoutInflater.inflate(R.layout.category_new_activity, null);//2130903255
        //paramLayoutInflater = this.Q;
        this.left_list = ((ListView)this.mCategoryFragmentLayout.findViewById(R.id.left_list));//2131166227
        this.sublist_loading_error_tips = this.mCategoryFragmentLayout.findViewById(R.id.sublist_loading_error_tips);//2131166230
        this.jd_tip_button = ((Button)this.sublist_loading_error_tips.findViewById(R.id.jd_tip_button));//2131165236
        this.jd_tip_button.setText(R.string.loading_error_again);//2131232396
        this.jd_tip_image = ((ImageView)this.sublist_loading_error_tips.findViewById(R.id.jd_tip_image));//2131165239
        this.jd_tip_image.setBackgroundResource(R.drawable.y_03);//2130837707
        ((TextView)this.sublist_loading_error_tips.findViewById(R.id.jd_tip_tv1)).setText(R.string.cart_error_fail);//2131165240//2131231071
        ((TextView)this.sublist_loading_error_tips.findViewById(R.id.jd_tip_tv2)).setText(R.string.cart_error_fail_check);//2131165241//2131231073
        this.c = ((RelativeLayout)this.mCategoryFragmentLayout.findViewById(R.id.category_message));//2131165804
        this.d = ((TextView)this.mCategoryFragmentLayout.findViewById(R.id.home_message_number));//2131165807
        this.e = ((SimpleDraweeView)this.mCategoryFragmentLayout.findViewById(R.id.home_message_red_dot));//2131165806
        this.r = this.mCategoryFragmentLayout.findViewById(R.id.mainlayout);//2131165737
        this.L = this.mCategoryFragmentLayout.findViewById(R.id.progress_bar);//2131166231
        this.M = this.mCategoryFragmentLayout.findViewById(R.id.main_loading_error_tips);//2131166232
        this.N = ((Button)this.M.findViewById(R.id.jd_tip_button));//2131165236
        this.N.setText(R.string.loading_error_again);//2131232396
        this.O = ((ImageView)this.M.findViewById(R.id.jd_tip_image));//2131165239
        this.O.setBackgroundResource(R.drawable.y_03);//2130837707
        this.f = ((ImageView)this.mCategoryFragmentLayout.findViewById(R.id.catagory_list_to_top));//2131166229
        ((TextView)this.M.findViewById(R.id.jd_tip_tv1)).setText(R.string.cart_error_fail);//2131165240//2131231071
        ((TextView)this.M.findViewById(R.id.jd_tip_tv2)).setText(R.string.cart_error_fail_check);//2131165241//2131231073
        this.g = new HashMap();
        this.p = UserInfoModel.getEncryptLoginUserName(LoginUserBase.getLoginUserName());
        if (this.p == null)
            this.p = "";
        this.q = StatisticsReportUtil.genarateDeviceUUID(this.P);
        //paramLayoutInflater = this.Q;
        this.a = this.mCategoryFragmentLayout.findViewById(R.id.common_title_2);//2131166226
        this.a.setVisibility(View.VISIBLE);//0
        this.search_text = ((AutoCompleteTextView)this.mCategoryFragmentLayout.findViewById(R.id.search_text));//2131166399
        this.search_text.setFocusable(false);
        this.search_text.setOnTouchListener(this.l);
        this.mCategoryFragmentLayout.findViewById(R.id.search_box_layout).setOnTouchListener(this.l);//2131166397
        this.mCategoryFragmentLayout.findViewById(R.id.category_saoasao_button).setOnClickListener(new View.OnClickListener(){//2131165803 //o(this)
            @Override
            public void onClick(View view) {
                if (CatelogyUtil.a(JDNewCategoryFragment.this.P))
                    JDNewCategoryFragment.this.startActivity(new Intent(JDNewCategoryFragment.this.P, CaptureActivity.class));
                JDMtaUtils.sendCommonData(JDNewCategoryFragment.this.P, "Search_Scan", "", "", JDNewCategoryFragment.this, "", JDNewCategoryFragment.this.P.getClass(), "");

            }
        });
        this.c.setOnClickListener(new View.OnClickListener(){//b(this)
            @Override
            public void onClick(View view) {
                if (JDNewCategoryFragment.synthetic_q(JDNewCategoryFragment.this))
                    return;
                JDMtaUtils.sendCommonData(JDNewCategoryFragment.this.P, "Classification_MyMessage", "", "", JDNewCategoryFragment.this, "", "", "");
                JDNewCategoryFragment.this.e.setVisibility(View.GONE);
                JDNewCategoryFragment.this.d.setVisibility(View.GONE);
                PersonalMessageManager.a(LoginUserBase.getLoginUserName());
                PersonalMessageManager.a("message", System.currentTimeMillis(), JDNewCategoryFragment.this.P.getHttpGroupWithNPSGroup());
                //paramView = new c(this);
                LoginUser.getInstance().executeLoginRunnable(JDNewCategoryFragment.this.P, new Runnable(){
                    @Override
                    public void run() {
                        Intent localIntent = new Intent(JDNewCategoryFragment.this.P, MyMessageBox.class);
                        localIntent.putExtra("title", JDNewCategoryFragment.this.getFragmentString(R.string.content_message_center));
                        localIntent.putExtra("com.360buy:navigationDisplayFlag", -1);
                        JDNewCategoryFragment.this.P.startActivity(localIntent);
                    }
                });
            }
        });
        if (this.y == null)
        {
            Context context = this.P.getBaseContext();
            int i1 = DPIUtil.getHeight() * 140 / 1280;
            PopupWindow popupWindow = new PopupWindow(context);
            popupWindow.setWidth(-1);
            popupWindow.setHeight(i1);
            popupWindow.setBackgroundDrawable(new ColorDrawable(0xCC3E3F4C));//-868335796
            popupWindow.setFocusable(true);
            popupWindow.setAnimationStyle(R.style.popup_anim_alpha_style);//2131296588
            popupWindow.update();
            this.y = popupWindow;
        }
        View view = ImageUtil.inflate(R.layout.app_search_toolbar_button, null);//2130903176
        if (view != null)
        {
            a(ConfigUtil.a(14, false), view.findViewById(R.id.search_camera_btn));//2131165521
            view.findViewById(R.id.search_barcode_btn).setOnClickListener(this.z);//2131165520
            view.findViewById(R.id.color_shopping_btn).setOnClickListener(this.z);//2131165523
            this.mCategoryFragmentLayout.findViewById(R.id.search_voice).setOnClickListener(this.z);//2131168592
            this.y.setContentView(view);
        }
        IntentFilter intentFilter = new IntentFilter("refresh_recommedData");
        this.h = new BroadcastReceiver(){//p(this, 0);
            @Override
            public void onReceive(Context context, Intent intent) {
                if ((intent != null) && ("refresh_recommedData".equals(intent.getAction())))
                {
                    JDNewCategoryFragment.this.p = UserInfoModel.getEncryptLoginUserName(LoginUserBase.getLoginUserName());
                    if (JDNewCategoryFragment.this.p == null)
                        JDNewCategoryFragment.this.p = "";
                    JDNewCategoryFragment.this.q = StatisticsReportUtil.genarateDeviceUUID(JDNewCategoryFragment.this.P);
                    if ((JDNewCategoryFragment.this.w == -1) && (JDNewCategoryFragment.this.getActivity() != null) && (!JDNewCategoryFragment.this.getActivity().isFinishing()))
                    {
                        if (Log.D)
                            Log.d(JDNewCategoryFragment.TAG, "ActivityHasNotFinished");
                        JDNewCategoryFragment.this.a(false, false, true, false);
                    }
                }
            }
        };
        getActivity().getApplicationContext().registerReceiver(this.h, intentFilter);
        a(false, true, true, false);
        this.left_list.setDivider(null);
        this.t = new LeftListAdapter(this.A, this.P);
        this.t.a(new ILeftAdapterListener(){//f(this)
            @Override
            public void a(View paramView) {
                JDNewCategoryFragment.this.u = paramView;
            }
        });
        this.left_list.setAdapter(this.t);
        this.left_list.setOnItemClickListener(new AdapterView.OnItemClickListener(){//g(this)
            @Override
            public void onItemClick(AdapterView<?> adapterView, final View view, int paramInt, long l) {
                if (((JDNewCategoryFragment.this.v) && (JDNewCategoryFragment.this.w == paramInt - 1)) || ((!JDNewCategoryFragment.this.v) && (JDNewCategoryFragment.this.w == paramInt)))
                    return;
                JDFrescoUtils.a(JDNewCategoryFragment.this.T);
                if (JDNewCategoryFragment.this.S)
                {
                    JDNewCategoryFragment.this.u = JDNewCategoryFragment.this.left_list.getChildAt(0);
                    JDNewCategoryFragment.this.S = false;
                }
                if ((JDNewCategoryFragment.this.u != null) && (JDNewCategoryFragment.this.isAdded()))
                {
                    JDNewCategoryFragment.this.u.setBackgroundResource(R.drawable.category_new_left_normal);//2130838454
                    ((LeftListAdapter.LeftListViewHolder)JDNewCategoryFragment.this.u.getTag()).mText.setTextColor(JDNewCategoryFragment.this.getFragmentTextColor(R.color.category_new__dark_font));//2131099873
                }
                if (JDNewCategoryFragment.this.isAdded())
                {
                    view.setBackgroundResource(R.drawable.category_new_left_facous);//2130838453
                    ((LeftListAdapter.LeftListViewHolder)view.getTag()).mText.setTextColor(JDNewCategoryFragment.this.getFragmentTextColor(R.color.category_new_red_font));//2131099877
                }
                if ((JDNewCategoryFragment.this.v) && (paramInt != 0))
                {
                    Catelogy catelogy = (Catelogy)JDNewCategoryFragment.this.A.get(paramInt - 1);
                    JDNewCategoryFragment.this.w = paramInt - 1;
                    JDNewCategoryFragment.this.o = catelogy.getcId();
                    JDNewCategoryFragment.this.a(JDNewCategoryFragment.this.o);
                    JDNewCategoryFragment.this.H = JDNewCategoryFragment.this.o;
                    JDMtaUtils.sendCommonData(JDNewCategoryFragment.this.P, "Classification_BCategory", JDNewCategoryFragment.this.o + "_" + JDNewCategoryFragment.this.D + "_" + paramInt, "", JDNewCategoryFragment.this, "", JDNewCategoryFragment.this.P.getClass(), "");
                }
                if ((JDNewCategoryFragment.this.v) && (paramInt == 0))
                {
                    JDNewCategoryFragment.this.post(new Runnable(){//h(this)
                        @Override
                        public void run() {
                            JDNewCategoryFragment.this.sublist_loading_error_tips.setVisibility(View.GONE);
                            if (JDNewCategoryFragment.this.isAdded())
                                JDNewCategoryFragment.this.jd_tip_image.setBackgroundResource(R.drawable.category_kongbai);//2130838448
                        }
                    });
                    JDNewCategoryFragment.this.a(false, false, true, false);
                    JDNewCategoryFragment.this.w = -1;
                    JDMtaUtils.sendCommonData(JDNewCategoryFragment.this.P, "Classification_CateCustomize", "", "", JDNewCategoryFragment.this, "", JDNewCategoryFragment.this.P.getClass(), "");
                    JDNewCategoryFragment.this.T.clear();
                    JDNewCategoryFragment.this.T = JDNewCategoryFragment.this.a(JDNewCategoryFragment.this.B);
                }
                if (!JDNewCategoryFragment.this.v)
                {
                    Catelogy catelogy = (Catelogy)JDNewCategoryFragment.this.A.get(paramInt);
                    JDNewCategoryFragment.this.w = paramInt;
                    JDNewCategoryFragment.this.o = catelogy.getcId();
                    JDNewCategoryFragment.this.a(JDNewCategoryFragment.this.o);
                    JDNewCategoryFragment.this.H = JDNewCategoryFragment.this.o;
                    JDMtaUtils.sendCommonData(JDNewCategoryFragment.this.P, "Classification_BCategory", JDNewCategoryFragment.this.o + "_" + JDNewCategoryFragment.this.D + "_" + (paramInt + 1), "", JDNewCategoryFragment.this, "", JDNewCategoryFragment.this.P.getClass(), "");
                }
                if (JDNewCategoryFragment.this.t != null)
                    JDNewCategoryFragment.this.t.a(JDNewCategoryFragment.this.w);
                JDNewCategoryFragment.this.post(new Runnable(){//i(this, view)
                    @Override
                    public void run() {
                        JDNewCategoryFragment.this.left_list.smoothScrollBy(view.getTop(), 700);
                    }
                });
                JDNewCategoryFragment.this.u = view;
            }
        });
        this.jd_tip_button.setOnClickListener(new View.OnClickListener(){//j(this)
            @Override
            public void onClick(View view) {
                JDNewCategoryFragment.this.a(JDNewCategoryFragment.this.o);
            }
        });
        this.N.setOnClickListener(new View.OnClickListener(){//k(this)
            @Override
            public void onClick(View view) {
                JDNewCategoryFragment.this.U = true;
                JDNewCategoryFragment.this.a(false, true, true, false);
            }
        });
        this.f.setOnClickListener(new View.OnClickListener(){//l(this)
            @Override
            public void onClick(View view) {

            }
        });
        return this.mCategoryFragmentLayout;
    }

    public void onDetach()
    {
        super.onDetach();
    }

    @Override
    public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
    {
        return false;
    }

    public void onPause()
    {
        this.W = true;
        super.onPause();
        PersonalMessageManager.a(LoginUserBase.getLoginUserName());
        PersonalMessageManager.b(this);
    }

    @Override
    public void onPersonalMessageReceived(final Map<String, PersonalMessageChannel> paramMap)
    {
        post(new Runnable(){//a(this, paramMap)
            @Override
            public void run() {
                PersonalMessageChannel localObject;
                if ((paramMap != null) && (paramMap.containsKey("message")))
                {
                    localObject = (PersonalMessageChannel)paramMap.get("message");
                    if (localObject != null){
                        if (((PersonalMessageChannel)localObject).a())
                        {//if-eqz v1, :cond_4
                            int i = ((PersonalMessageChannel)localObject).num;
                            if (i <= 0)
                            {
                                JDNewCategoryFragment.this.e.setVisibility(View.GONE);
                                JDNewCategoryFragment.this.d.setVisibility(View.GONE);
                                return;
                            }else {
                                String str = "99+";
                                if (i <= 99)
                                    str = i + "";
                                JDNewCategoryFragment.this.e.setVisibility(View.GONE);
                                JDNewCategoryFragment.this.d.setText((CharSequence) localObject);
                                JDNewCategoryFragment.this.d.setVisibility(View.VISIBLE);
                                return;
                            }
                        }else if (((PersonalMessageChannel)localObject).b()){
                            JDNewCategoryFragment.this.e.setVisibility(View.VISIBLE);
                            JDNewCategoryFragment.this.d.setVisibility(View.GONE);
                            return;
                        }else {
                            JDNewCategoryFragment.this.d.setVisibility(View.GONE);
                            JDNewCategoryFragment.this.e.setVisibility(View.GONE);
                        }
                    }
                }
                return;
            }
        });
    }

    public void onResume()
    {
        super.onResume();
        PersonalMessageManager.a(LoginUserBase.getLoginUserName());
        PersonalMessageManager.a(this);
        if (LoginUser.hasLogin())
        {
            PersonalMessageManager.a(LoginUserBase.getLoginUserName());
            PersonalMessageManager.a(this.P.getHttpGroupWithNPSGroup());
        }
        if ((this.search_text != null) && (!isAdded())){
            if (!LoginUserBase.hasLogin())
            {
                this.e.setVisibility(View.GONE);//8;//8
                this.d.setVisibility(View.GONE);//8;//8
            }
            this.search_text.setHint(R.string.homeActivity_autoComplete);//2131231773
            this.V = CommonUtil.getJdSharedPreferences().getString("hintKeyWord", "");
            if (!TextUtils.isEmpty(this.V))
                this.search_text.setHint(this.V);
            NavigationOptHelper.getInstance();
            NavigationOptHelper.c(1);
            if ((this.v) && (!this.r1) && (this.left_list.getHeaderViewsCount() == 0))
                b();
            if (this.W) {
                a(this.ab);
                this.W = false;
            }
        }
        return;
    }

    protected void setPageId(String paramString)
    {
        this.i = paramString;
    }

    protected void setShopId(String paramString)
    {
        this.j = paramString;
    }

    static void synthetic_a(final JDNewCategoryFragment paramJDNewCategoryFragment, final boolean paramBoolean1, final boolean paramBoolean2)
    {
        paramJDNewCategoryFragment.post(new Runnable(){//d(paramJDNewCategoryFragment)
            @Override
            public void run() {
                paramJDNewCategoryFragment.L.setVisibility(View.GONE);
                paramJDNewCategoryFragment.r.setVisibility(View.VISIBLE);
                paramJDNewCategoryFragment.M.setVisibility(View.GONE);
                if (paramJDNewCategoryFragment.isAdded())
                    paramJDNewCategoryFragment.O.setBackgroundResource(R.drawable.category_kongbai);//2130838448
            }
        });
        final HttpGroup.HttpSetting localHttpSetting = new HttpGroup.HttpSetting();
        localHttpSetting.setListener(new HttpGroup.OnCommonListener(){// r(paramJDNewCategoryFragment, new ExceptionReporter(localHttpSetting), paramBoolean1, paramBoolean2)
            private void a()
            {
                paramJDNewCategoryFragment.post(new Runnable(){//t(this)
                    @Override
                    public void run() {
                        paramJDNewCategoryFragment.r.setVisibility(View.GONE);
                        paramJDNewCategoryFragment.M.setVisibility(View.VISIBLE);
                        if (paramJDNewCategoryFragment.isAdded())
                            paramJDNewCategoryFragment.O.setBackgroundResource(R.drawable.y_03);//2130837707
                    }
                });
            }
            @Override
            public void onReady(HttpGroup.HttpSettingParams paramHttpSettingParams) {

            }

            @Override
            public void onError(HttpGroup.HttpError paramHttpError) {
                a();
            }

            @Override
            public void onEnd(HttpGroup.HttpResponse paramHttpResponse) {
                if (!paramBoolean2)//this.c //if-nez v0, :cond_2
                {
                    if (!paramBoolean1)//this.d //if-nez v0, :cond_0
                        JDNewCategoryFragment.synthetic_a(paramJDNewCategoryFragment, true, true);
                    paramJDNewCategoryFragment.D = paramHttpResponse.getJSONObject().optString("catalogSortEventId");
                    paramJDNewCategoryFragment.E = paramHttpResponse.getJSONObject().optString("catalogTopNum");
                    paramJDNewCategoryFragment.C = paramHttpResponse.getJSONObject().getJSONArrayOrNull("catelogyList");
                    if (TextUtils.isEmpty(paramJDNewCategoryFragment.E)) {//if-eqz v0, :cond_3
                        paramJDNewCategoryFragment.G =  "null";
                        paramJDNewCategoryFragment.F = 0;
                    }else{
                        paramJDNewCategoryFragment.F = Integer.parseInt(paramJDNewCategoryFragment.E);
                        paramJDNewCategoryFragment.G = Catelogy.getCmsTotalCid(Catelogy.toList(paramJDNewCategoryFragment.C, 0), paramJDNewCategoryFragment.F);
                    }

                    if ((paramJDNewCategoryFragment.C == null) || (paramJDNewCategoryFragment.C.length() == 0))
                    {
                        new ExceptionReporter(localHttpSetting).reportHttpBusinessException(paramHttpResponse);
                        a();
                    }else{
                        paramJDNewCategoryFragment.post(new Runnable(){//s(this)
                            @Override
                            public void run() {
                                paramJDNewCategoryFragment.A.clear();
                                paramJDNewCategoryFragment.A.addAll(Catelogy.toList(paramJDNewCategoryFragment.C, 0));
                                if (!paramJDNewCategoryFragment.v)
                                {
                                    paramJDNewCategoryFragment.synthetic_z(paramJDNewCategoryFragment);
                                    paramJDNewCategoryFragment.t.notifyDataSetChanged();
                                }else {
                                    paramJDNewCategoryFragment.b();
                                    paramJDNewCategoryFragment.t.notifyDataSetChanged();
                                }
                            }
                        });
                    }

                }
                //:cond_2
                //:goto_1
                return;
            }
        });
        localHttpSetting.setFunctionId("entranceCatalog");
        localHttpSetting.setHost(Configuration.getPortalHost());
        if (paramBoolean1)
        {
            localHttpSetting.setLocalFileCache(true);
            localHttpSetting.setLocalFileCacheTime(86400000L);
            if (paramBoolean2)//if-eqz p2, :cond_2
                localHttpSetting.setCacheMode(4);
            else
                localHttpSetting.setCacheMode(0);
        }

            localHttpSetting.setBussinessId(300);
            if (paramJDNewCategoryFragment.U)
            {
                localHttpSetting.setCacheMode(2);
                paramJDNewCategoryFragment.U = false;
            }
            paramJDNewCategoryFragment.P.getHttpGroupaAsynPool().add(localHttpSetting);
            return;

    }

    static boolean synthetic_q(JDNewCategoryFragment paramJDNewCategoryFragment)
    {
        long l1 = System.currentTimeMillis();
        if (l1 - paramJDNewCategoryFragment.x < 800L)
            return true;
        paramJDNewCategoryFragment.x = l1;
        return false;
    }

    static void synthetic_z(JDNewCategoryFragment paramJDNewCategoryFragment)
    {
        paramJDNewCategoryFragment.H = ((Catelogy)paramJDNewCategoryFragment.A.get(0)).getcId();
        paramJDNewCategoryFragment.o = ((Catelogy)paramJDNewCategoryFragment.A.get(0)).getcId();
        paramJDNewCategoryFragment.a(paramJDNewCategoryFragment.o);
        paramJDNewCategoryFragment.S = true;
    }

    public static class JDNewCategoryTM extends JDTaskModule
    {
        private JDNewCategoryFragment category;

        public void doInit()
        {
            this.category = JDNewCategoryFragment.getInstance();
            if (this.category.getArguments() == null)
            {
                getBundle().putInt("com.360buy:navigationFlag", 1);
                this.category.setArguments(getBundle());
            }
        }

        public void doShow()
        {
            replaceAndCommit(this.category, Integer.valueOf(1));
        }
    }
}
