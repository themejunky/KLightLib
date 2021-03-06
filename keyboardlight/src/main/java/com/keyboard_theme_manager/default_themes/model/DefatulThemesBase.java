package com.keyboard_theme_manager.default_themes.model;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.android.inputmethod.latin.settings.Settings;
import com.android.inputmethod.latin.settings.SettingsValues;
import com.google.gson.Gson;
import com.keyboard_settings.CustomPreferences;
import com.keyboard_theme_dictionary.Model.theme_resource.ThemeResource;
import com.keyboard_theme_dictionary.Model.theme_resource.ThemeResource_ActionSymbol;
import com.keyboard_theme_dictionary.Model.theme_resource.ThemeResource_Icons;
import com.keyboard_theme_dictionary.Model.theme_resource.ThemeResource_KeyAlternatives;
import com.keyboard_theme_dictionary.Model.theme_resource.ThemeResource_KeyBackground;
import com.keyboard_theme_dictionary.Model.theme_resource.ThemeResource_KeyLabel;
import com.keyboard_theme_dictionary.Model.theme_resource.ThemeResource_KeyPreview;
import com.keyboard_theme_dictionary.Model.theme_resource.ThemeResource_Listing;
import com.keyboard_theme_dictionary.Model.theme_resource.ThemeResource_MoreKeys;
import com.keyboard_theme_dictionary.Model.theme_resource.ThemeResource_ShiftSymbol;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DefatulThemesBase  {

    protected String DEBUG_TAG = "DefaultThemes_Default";
    private String DT_FOLDER = "default_themes";
    protected ThemeResource mCurrentTheme;
    protected int mCurrentIndexTheme = 0;
    protected List<ThemeSettings> mAllThemes = new ArrayList<>();
    protected Context mContext;

    protected CustomPreferences mCustomPreferences;

    public void load_default_themes() {
        String[] default_themes_folders = null;
        AssetManager mAssets = mContext.getAssets();
        try {
            default_themes_folders = mAssets.list(DT_FOLDER);
        } catch (Exception e) {
            Log.d(DEBUG_TAG+"1","error parsing assets : "+e.getMessage());
        }
        if (default_themes_folders == null) {
            return;
        }
        for (String folder: default_themes_folders) {
            try {
                mAllThemes.add(inputStreamToString(mAssets.open(DT_FOLDER + "/" + folder + "/theme.json")));
            } catch (Exception e) {
                Log.d(DEBUG_TAG+"2","error assets : "+e.getMessage());
            }
        }
    }

    ThemeSettings inputStreamToString(InputStream nInputStream) {
        try {
            byte[] bytes = new byte[nInputStream.available()];
            nInputStream.read(bytes,0, bytes.length);
            String json = new String(bytes);
            return new Gson().fromJson(json,ThemeSettings.class);
        } catch (Exception e) {
            Log.d(DEBUG_TAG+"3","error inputStreamToString : "+e.getMessage());
            return null;
        }
    }

    protected ThemeResource loadDefaultTheme(int nIndex) {
        ThemeSettings mSelectedTheme = mAllThemes.get(nIndex);
        ThemeResource mTheme = new ThemeResource();

        try {
            Resources resources = mContext.getResources();
            Log.d(DEBUG_TAG+"5","este 11 : "+mSelectedTheme.keyboard_background);
            Log.d(DEBUG_TAG+"5","este 22 : "+mSelectedTheme.key_background);


            mTheme.keyBackground = new ThemeResource_KeyBackground();
            mTheme.keyLabel = new ThemeResource_KeyLabel();
            mTheme.shiftSymbol = new ThemeResource_ShiftSymbol();
            mTheme.actionSymbol = new ThemeResource_ActionSymbol();
            mTheme.keyPreview = new ThemeResource_KeyPreview();
            mTheme.keyAlternatives = new ThemeResource_KeyAlternatives();
            mTheme.moreKeys = new ThemeResource_MoreKeys();
            mTheme.icons = new ThemeResource_Icons();
            Log.d(DEBUG_TAG+"5","0");

            mTheme.keyboardBackground = getColorOrDrawable(mSelectedTheme.keyboard_background, resources);
            mTheme.theme_preview = getColorOrDrawable(mSelectedTheme.theme_preview,resources);

            mTheme.icons.ic_mic = getColorOrDrawable(mSelectedTheme.icons.ic_mic, resources);
            mTheme.icons.ic_menu = getColorOrDrawable(mSelectedTheme.icons.ic_menu, resources);
            Log.d(DEBUG_TAG+"5","01");
            mTheme.keyBackground.mKey_Querty = loadStateDrawable(mSelectedTheme.key_background.key_qwerty,resources);

            Log.d(DEBUG_TAG+"5","1");



            mTheme.keyBackground.mKey_Numeric = loadStateDrawable(mSelectedTheme.key_background.key_numeric,resources);
            mTheme.keyBackground.mKey_Space = loadStateDrawable(mSelectedTheme.key_background.key_space,resources);
            mTheme.keyBackground.mKey_Functional = loadStateDrawable(mSelectedTheme.key_background.key_functional,resources);

            Log.d(DEBUG_TAG+"5","2");

            mTheme.keyBackground.mKey_Symbols = loadStateDrawable(mSelectedTheme.key_background.key_symbols,resources);
            mTheme.keyBackground.mKey_Alt = loadStateDrawable(mSelectedTheme.key_background.key_alt,resources);
            mTheme.keyBackground.noPreview = loadStateDrawable(mSelectedTheme.key_background.key_alt,resources);
            Log.d(DEBUG_TAG+"5","3");
            mTheme.keyLabel.normal = loadStateColor(mSelectedTheme.key_label_color.normal);
            Log.d(DEBUG_TAG+"5","31");
            try {
                mTheme.keyLabel.font = Typeface.createFromAsset(mContext.getAssets(), mSelectedTheme.theme_font_family);
            } catch (Exception e) {
                Log.d(DEBUG_TAG+"5","SUGI");
            }
            Log.d(DEBUG_TAG+"5","32");
            mTheme.keyPreview.background = getColorOrDrawable(mSelectedTheme.key_preview.background,resources);
            Log.d(DEBUG_TAG+"5","33");
            mTheme.keyPreview.labelColor = Color.parseColor(mSelectedTheme.key_preview.label_color);
            Log.d(DEBUG_TAG+"5","4");
            mTheme.shiftSymbol.def = getColorOrDrawable(mSelectedTheme.symbol_shift.default_state,resources);
            mTheme.shiftSymbol.pressed = getColorOrDrawable(mSelectedTheme.symbol_shift.pressed_state,resources);
            mTheme.shiftSymbol.locked = getColorOrDrawable(mSelectedTheme.symbol_shift.locked_state,resources);
            Log.d(DEBUG_TAG+"5","5");
            mTheme.actionSymbol.done = getColorOrDrawable(mSelectedTheme.symbol_action.done,resources);
            mTheme.actionSymbol.go = getColorOrDrawable(mSelectedTheme.symbol_action.go,resources);
            mTheme.actionSymbol.enter = getColorOrDrawable(mSelectedTheme.symbol_action.enter,resources);
            mTheme.actionSymbol.next = getColorOrDrawable(mSelectedTheme.symbol_action.next,resources);
            mTheme.actionSymbol.search = getColorOrDrawable(mSelectedTheme.symbol_action.search,resources);
            mTheme.actionSymbol.send = getColorOrDrawable(mSelectedTheme.symbol_action.send,resources);

            mTheme.deleteSymbol = getColorOrDrawable(mSelectedTheme.symbol_delete,resources);
            Log.d(DEBUG_TAG+"5","6");
            mTheme.keyAlternatives.background = getColorOrDrawable(mSelectedTheme.key_alternatives.background,resources);
            mTheme.keyAlternatives.labelColor = Color.parseColor(mSelectedTheme.key_alternatives.label_color);

            mTheme.moreKeys.background = getColorOrDrawable(mSelectedTheme.more_keys.background,resources);
            Log.d(DEBUG_TAG+"5","7");
        }
        catch (Exception e) {
            Log.d(DEBUG_TAG+"4","error parssing resourse : "+e.getMessage());
        }
        return mTheme;
    }


    protected ThemeResource loadIntalledThemes(int nIndex) {
        ThemeSettings mSelectedTheme = mAllThemes.get(nIndex);
        ThemeResource mTheme = new ThemeResource();
        try {
            Resources  resources = mContext.getResources();
            mTheme.listing = new ThemeResource_Listing();
            mTheme.listing.themeNo = nIndex;
            mTheme.listing.isActive = (nIndex == mCurrentIndexTheme);
            mTheme.theme_preview = getColorOrDrawable(mSelectedTheme.theme_preview,resources);

        }
        catch (Exception e) {
            Log.d(DEBUG_TAG+"5","error parssing resourse : "+e.getMessage());
        }

        return mTheme;
    }


    Drawable getColorOrDrawable(String value, Resources resources) {

        Log.d(DEBUG_TAG+"7","este 22 : x1 : "+value);
        Log.d(DEBUG_TAG+"7","este 22 : x2 : "+resources);
        try {
            Log.d(DEBUG_TAG+"7","este 22 : x3 : ");
            return new ColorDrawable(Color.parseColor(value));
        } catch (IllegalArgumentException ignored) {
            Log.d(DEBUG_TAG+"7","este 22 : x4 : "+ignored.getMessage());
            return ContextCompat.getDrawable(mContext,resources.getIdentifier(value,"drawable",mContext.getPackageName()));
        }
    }

    StateListDrawable loadStateDrawable(ThemeSettings_States state_types, Resources resources) throws JSONException, IOException {

        Log.d(DEBUG_TAG+"6","este 22 : 221");

        StateListDrawable drawable = new StateListDrawable();
        Log.d(DEBUG_TAG+"6","este 22 : 222");
        drawable.addState(new int[]{android.R.attr.state_pressed},getColorOrDrawable(state_types.pressed_state, resources));
        Log.d(DEBUG_TAG+"6","este 22 : 223");
        drawable.addState(new int[]{},getColorOrDrawable(state_types.default_state, resources));
        Log.d(DEBUG_TAG+"6","este 22 : 224");
        return drawable;
    }

    ColorStateList loadStateColor(ThemeSettings_States json) throws JSONException {
        return new ColorStateList(new int[][]{new int[]{android.R.attr.state_pressed},new int[]{}},
                new int[]{Color.parseColor(json.pressed_state), Color.parseColor(json.default_state)});
    }

}
