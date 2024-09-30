package com.cinntra.vista.EasyPrefs;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("unused")
public class Prefs {
    private static final String DEFAULT_SUFFIX = "_preferences";
    private static final String LENGTH = "#LENGTH";
    private static SharedPreferences mPrefs;
    private static SharedPreferences.Editor editor;

    @Deprecated
    public static void initPrefs(Context context) {
        // Implementation for initializing using the builder is below
    }

    private static void initPrefs(Context context, String prefsName, int mode) {
        mPrefs = context.getSharedPreferences(prefsName, mode);
        editor = mPrefs.edit();
    }

    public static SharedPreferences getPreferences() {
        if (mPrefs != null) {
            return mPrefs;
        }
        throw new RuntimeException("Prefs class not correctly instantiated. Please call Builder.setContext().build() in the Application class onCreate.");
    }

    public static Map<String, ?> getAll() {
        return getPreferences().getAll();
    }

    public static int getInt(String key, int defValue) {
        return getPreferences().getInt(key, defValue);
    }

    public static boolean getBoolean(String key, boolean defValue) {
        return getPreferences().getBoolean(key, defValue);
    }

    public static long getLong(String key, long defValue) {
        return getPreferences().getLong(key, defValue);
    }

    public static double getDouble(String key, double defValue) {
        return Double.longBitsToDouble(getPreferences().getLong(key, Double.doubleToRawLongBits(defValue)));
    }

    public static void setMPINValue(String mpinValue) {
        if (mpinValue != null) {
            putString("mpinValue", mpinValue);
        }
    }

    public static String getMPINValue() {
        return getPreferences().getString("mpinValue", "");
    }

    public static void setFromWhere(String fromWhere) {
        if (fromWhere != null) {
            putString("fromWhere", fromWhere);
        }
    }

    public static void setMobileNo(String mobile) {
        if (mobile != null) {
            putString("mobile", mobile);
        }
    }

    public static void clearSession() {
        editor = getPreferences().edit();
        editor.clear();
        editor.apply();
    }

    public static void setCardCode(String cardCode) {
        if (cardCode != null) {
            putString("_cardCode", cardCode);
        }
    }

    public static String getCardCode() {
        return getPreferences().getString("_cardCode", "");
    }

    public static void setSalesEmployeeCode(String salesEmployeeCode) {
        if (salesEmployeeCode != null) {
            putString("_salesEmployeeCode", salesEmployeeCode);
        }
    }

    public static String getSalesEmployeeCode() {
        return getPreferences().getString("_salesEmployeeCode", "");
    }

    public static void setCardName(String cardName) {
        if (cardName != null) {
            putString("card_name", cardName);
        }
    }

    public static String getCardName() {
        return getPreferences().getString("card_name", "");
    }

    public static void setDistributorID(String distributor_id) {
        if (distributor_id != null) {
            putString("_distributor_id", distributor_id);
        }
    }

    public static String getDistributorID() {
        return getPreferences().getString("_distributor_id", "");
    }

    public static void setToken(String token) {
        if (token != null) {
            putString("token", token);
        }
    }

    public static String getToken() {
        return getPreferences().getString("token", "");
    }

    public static String getMobileNO() {
        return getPreferences().getString("mobile", "");
    }

    public static String getFromWhere() {
        return getPreferences().getString("fromWhere", "");
    }

    public static float getFloat(String key, float defValue) {
        return getPreferences().getFloat(key, defValue);
    }

    public static String getString(String key, String defValue) {
        return getPreferences().getString(key, defValue);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static Set<String> getStringSet(String key, Set<String> defValue) {
        SharedPreferences prefs = getPreferences();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return prefs.getStringSet(key, defValue);
        } else {
            return getOrderedStringSet(key, defValue);
        }
    }

    public static Set<String> getOrderedStringSet(String key, Set<String> defValue) {
        SharedPreferences prefs = getPreferences();
        if (prefs.contains(key + LENGTH)) {
            LinkedHashSet<String> set = new LinkedHashSet<>();
            int stringSetLength = prefs.getInt(key + LENGTH, -1);
            if (stringSetLength >= 0) {
                for (int i = 0; i < stringSetLength; i++) {
                    set.add(prefs.getString(key + "[" + i + "]", null));
                }
            }
            return set;
        }
        return defValue;
    }

    public static void putLong(String key, long value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public static void putInt(String key, int value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static void putDouble(String key, double value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putLong(key, Double.doubleToRawLongBits(value));
        editor.apply();
    }

    public static void putFloat(String key, float value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public static void putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static void putString(String key, String value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putString(key, value);
        editor.apply();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void putStringSet(String key, Set<String> value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            SharedPreferences.Editor editor = getPreferences().edit();
            editor.putStringSet(key, value);
            editor.apply();
        } else {
            putOrderedStringSet(key, value);
        }
    }

    public static void putOrderedStringSet(String key, Set<String> value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        int stringSetLength = 0;
        if (mPrefs.contains(key + LENGTH)) {
            stringSetLength = mPrefs.getInt(key + LENGTH, -1);
        }
        editor.putInt(key + LENGTH, value.size());
        int i = 0;
        for (String aValue : value) {
            editor.putString(key + "[" + i + "]", aValue);
            i++;
        }
        while (i < stringSetLength) {
            editor.remove(key + "[" + i + "]");
            i++;
        }
        editor.apply();
    }

    public static void remove(String key) {
        SharedPreferences prefs = getPreferences();
        SharedPreferences.Editor editor = prefs.edit();
        if (prefs.contains(key + LENGTH)) {
            int stringSetLength = prefs.getInt(key + LENGTH, -1);
            if (stringSetLength >= 0) {
                editor.remove(key + LENGTH);
                for (int i = 0; i < stringSetLength; i++) {
                    editor.remove(key + "[" + i + "]");
                }
            }
        }
        editor.remove(key);
        editor.apply();
    }

    public static boolean contains(String key) {
        return getPreferences().contains(key);
    }

    public static SharedPreferences.Editor clear() {
        SharedPreferences.Editor editor = getPreferences().edit().clear();
        editor.apply();
        return editor;
    }

    public static SharedPreferences.Editor edit() {
        return getPreferences().edit();
    }

    public static class Builder {
        private String mKey;
        private Context mContext;
        private int mMode = -1;
        private boolean mUseDefault = false;

        public Builder setPrefsName(String prefsName) {
            mKey = prefsName;
            return this;
        }

        public Builder setContext(Context context) {
            mContext = context;
            return this;
        }

        @SuppressLint("WorldReadableFiles")
        public Builder setMode(int mode) {
            if (mode == ContextWrapper.MODE_PRIVATE || mode == ContextWrapper.MODE_WORLD_READABLE ||
                    mode == ContextWrapper.MODE_WORLD_WRITEABLE || mode == ContextWrapper.MODE_MULTI_PROCESS) {
                mMode = mode;
            } else {
                throw new RuntimeException("The mode in the SharedPreference can only be set to ContextWrapper.MODE_PRIVATE, ContextWrapper.MODE_WORLD_READABLE, ContextWrapper.MODE_WORLD_WRITEABLE or ContextWrapper.MODE_MULTI_PROCESS");
            }
            return this;
        }

        public Builder setUseDefaultSharedPreference(boolean defaultSharedPreference) {
            mUseDefault = defaultSharedPreference;
            return this;
        }

        public void build() {
            if (TextUtils.isEmpty(mKey)) {
                mKey = mContext.getPackageName() + DEFAULT_SUFFIX;
            }
            initPrefs(mContext, mKey, mMode);
        }
    }
}
