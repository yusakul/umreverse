package com.umeng.commonsdk.utils;

import java.util.*;
import org.json.*;

public class JSONArraySortUtil implements Comparator<JSONObject>
{
    private String mCompareKey;
    
    public void setCompareKey(final String mCompareKey) {
        this.mCompareKey = mCompareKey;
    }
    
    @Override
    public int compare(final JSONObject jsonObject, final JSONObject jsonObject2) {
        try {
            return (int)(jsonObject.getLong(this.mCompareKey) - jsonObject2.getLong(this.mCompareKey));
        }
        catch (JSONException ex) {
            ex.printStackTrace();
            return 0;
        }
    }
}
