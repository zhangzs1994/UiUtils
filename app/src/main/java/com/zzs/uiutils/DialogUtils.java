package com.zzs.uiutils;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;


/**
 * Created by Administrator on 2019/3/14/014.
 */

public class DialogUtils {

    private Context mContext;
    private View mContentView;
    private TextView mTitle;
    private TextView mMessage;
    private LinearLayout mContent;
    private LinearLayout mDialogLl;
    private LayoutInflater mInflater;
    private AlertDialog mDialog;
    private AlertDialog.Builder mBuilder;
    private Button mLeftButton;
    private Button mRightButton;
    private Button mSingleButton;
    private TextView mBtnLine;
    private ViewGroup.LayoutParams mParams;
    private HashMap<Button, OnClickListener> mapListener = null;
    private AdapterView.OnItemClickListener listViewListener;
    private ListView listView;
    private static final int TYPE_NORMAL = 1;
    private static final int TYPE_CHECK = 2;
    private static final int TYPE_CHECKS = 3;
    private static final int TYPE_LIST = 4;

    public DialogUtils(Context context) {
        this.mContext = context;
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        mapListener = new HashMap();
        mInflater = LayoutInflater.from(mContext);
        mBuilder = new AlertDialog.Builder(mContext);
        mContentView = mInflater.inflate(R.layout.dialog_view, null);
        mTitle = mContentView.findViewById(R.id.dialog_title);
        mMessage = mContentView.findViewById(R.id.dialog_message);
        mContent = mContentView.findViewById(R.id.dialog_content);
        mDialogLl = mContentView.findViewById(R.id.dialog_ll);
        mBtnLine = mContentView.findViewById(R.id.dialog_btn_line);
        mLeftButton = mContentView.findViewById(R.id.dialog_left_btn);
        mRightButton = mContentView.findViewById(R.id.dialog_right_btn);
        mSingleButton = mContentView.findViewById(R.id.dialog_single_btn);
        mDialog = mBuilder.create();
        mParams = new ViewGroup.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    /**
     * 设置Dialog背景颜色
     */
    public DialogUtils setBackground(int color) {
        this.mDialogLl.setBackgroundColor(mContext.getResources().getColor(color));
        return this;
    }

    /**
     * 设置Dialog背景颜色
     */
    public DialogUtils setBackgroundResource(int color) {
        this.mDialogLl.setBackgroundResource(color);
        return this;
    }

    /**
     * 设置Dialog动画
     */
    public DialogUtils setAnimations(int resId) {
        Window window = mDialog.getWindow();
        window.setWindowAnimations(resId);
        return this;
    }

    /**
     * 设置标题
     */
    public DialogUtils setTitle(String title) {
        this.mTitle.setVisibility(View.VISIBLE);
        this.mTitle.setText(title);
        return this;
    }

    /**
     * 设置标题字体颜色
     */
    public DialogUtils setTitleColor(int color) {
        this.mTitle.setTextColor(mContext.getResources().getColor(color));
        return this;
    }

    /**
     * 设置标题背景颜色
     */
    public DialogUtils setTitleBackground(int color) {
        this.mTitle.setBackgroundColor(mContext.getResources().getColor(color));
        return this;
    }

    /**
     * 设置内容
     */
    public DialogUtils setMessage(String message) {
        this.mMessage.setVisibility(View.VISIBLE);
        this.mMessage.setText(message);
        return this;
    }

    /**
     * 设置内容颜色
     */
    public DialogUtils setMessageColor(int color) {
        this.mMessage.setTextColor(mContext.getResources().getColor(color));
        return this;
    }

    /**
     * 设置Dialog位置
     */
    public DialogUtils setGravity(int gravity, int width, int height, int x, int y) {
        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(gravity);
        lp.width = width;
        lp.height = height;
        lp.x = x;
        lp.y = y;
        dialogWindow.setAttributes(lp);
        return this;
    }

    /**
     * 设置Dialog位置
     */
    public DialogUtils setWidthAndHeight(int width, int height) {
        mParams = new ViewGroup.LayoutParams(width, height);
        return this;
    }

    /**
     * 设置Dialog距离X轴Y轴的位置
     */
    public DialogUtils setGravityOfXy(int x, int y) {
        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = x;
        lp.y = y;
        dialogWindow.setAttributes(lp);
        return this;
    }

    /**
     * 设置Dialog位置
     */
    public DialogUtils setGravity(int gravity) {
        Window dialogWindow = mDialog.getWindow();
        dialogWindow.setGravity(gravity);
        return this;
    }

    /**
     * 设置点击Dialog外区域是否隐藏对话框
     */
    public DialogUtils setCanceledOnTouchOutside(boolean isCancel) {
        if (mDialog != null)
            mDialog.setCanceledOnTouchOutside(isCancel);
        return this;
    }

    /**
     * 设置点击返回键否隐藏对话框
     */
    public DialogUtils setCancelable(boolean isCancel) {
        if (mDialog != null)
            mDialog.setCancelable(isCancel);
        return this;
    }

    /**
     * 设置确认
     */
    public DialogUtils setPositiveButton(String confirm, OnClickListener listener) {
        mRightButton.setText(confirm);
        mRightButton.setOnClickListener(onClickListener);
        mapListener.put(mRightButton, listener);
        return this;
    }

    /**
     * 设置确认字体颜色
     */
    public DialogUtils setPositiveColor(int color) {
        mRightButton.setTextColor(mContext.getResources().getColor(color));
        return this;
    }

    /**
     * 设置取消
     */
    public DialogUtils setNeutralButton(String cancel, OnClickListener listener) {
        mLeftButton.setText(cancel);
        mLeftButton.setOnClickListener(onClickListener);
        mapListener.put(mLeftButton, listener);
        return this;
    }

    /**
     * 设置取消字体颜色
     */
    public DialogUtils setNeutralColor(int color) {
        mLeftButton.setTextColor(mContext.getResources().getColor(color));
        return this;
    }

    /**
     * 设置唯一按钮
     */
    public DialogUtils setSingleButton(String cancel, OnClickListener listener) {
        mBtnLine.setVisibility(View.GONE);
        mRightButton.setVisibility(View.GONE);
        mLeftButton.setVisibility(View.GONE);
        mSingleButton.setText(cancel);
        mSingleButton.setOnClickListener(onClickListener);
        mapListener.put(mSingleButton, listener);
        return this;
    }

    /**
     * 设置SingleButton字体颜色
     */
    public DialogUtils setSingleColor(int color) {
        mSingleButton.setTextColor(mContext.getResources().getColor(color));
        return this;
    }

    /**
     * button点击监听
     */
    View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            dismiss();
            Button btn = (Button) v;
            if (btn != null && mapListener.containsKey(btn)) {
                OnClickListener onClick = mapListener.get(btn);
                if (onClick != null)
                    onClick.onClick(DialogUtils.this, btn);
            }
        }
    };

    /**
     * button监听接口
     */
    public interface OnClickListener {
        void onClick(DialogUtils dialog, Button button);
    }

    /**
     * 设置列表
     */
    public DialogUtils setList(List<String> list, AdapterView.OnItemClickListener listener) {
        this.listViewListener = listener;
        listView = new ListView(mContext);
        listView.setDivider(new ColorDrawable(Color.parseColor("#f2f2f2")));
        listView.setDividerHeight(1);
        if (mContent != null) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            params.weight = 1.0f;
            mContent.addView(listView, params);
            listView.setOnItemClickListener(itemClickListener);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                    R.layout.dialog_view_list, R.id.dialog_list_item, list);
            listView.setAdapter(adapter);
            //监听高度变化，超出高度则固定dialog高度，滑动显示
            listView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    int height = 700;
                    if (v.getHeight() > height) {
                        ViewGroup.LayoutParams params = listView.getLayoutParams();
                        params.height = height;
                        listView.setLayoutParams(params);
                    }
                }
            });
        }
        return this;
    }

    /**
     * 设置列表监听
     */
    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            dismiss();
            if (listViewListener != null) {
                listViewListener.onItemClick(parent, view, position, id);
            }

        }
    };

    /**
     * 显示对话框
     */
    public void show() {
        if (mDialog != null && !mDialog.isShowing()) {
            mDialog.show();
            mDialog.getWindow().clearFlags(
                    WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
            mDialog.getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
            mDialog.setContentView(mContentView, mParams);
        }
    }

    /**
     * 关闭对话框
     */
    public void dismiss() {
        if (mDialog != null && mDialog.isShowing())
            mDialog.dismiss();
    }

}
