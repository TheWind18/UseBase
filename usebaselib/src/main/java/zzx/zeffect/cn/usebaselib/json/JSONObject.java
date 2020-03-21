/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package zzx.zeffect.cn.usebaselib.json;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * 稍稍封装一下，处理一下为Null的情况
 */
public class JSONObject {

    private org.json.JSONObject dataJson;

    public static final Object NULL = new Object() {
        @Override
        public boolean equals(Object o) {
            return o == this || o == null; // API specifies this broken equals implementation
        }

        @Override
        public String toString() {
            return "null";
        }
    };

    public JSONObject(String json) throws JSONException {
        dataJson = new org.json.JSONObject(json);
    }

    public int length() {
        return dataJson.length();
    }


    public boolean isNull(String name) {
        return dataJson.isNull(name);
    }

    public boolean has(String name) {
        return dataJson.has(name);
    }


    public Object get(String name) throws JSONException {
        return dataJson.get(name);
    }


    public Object opt(String name) {
        return dataJson.opt(name);
    }


    public boolean optBoolean(String name) {
        return optBoolean(name, false);
    }

    public boolean optBoolean(String name, boolean fallback) {
        if (dataJson.isNull(name)) {
            return false;
        }
        return dataJson.optBoolean(name, fallback);
    }


    public double optDouble(String name) {
        return optDouble(name, Double.NaN);
    }

    public double optDouble(String name, double fallback) {
        if (isNull(name)) {
            return fallback;
        }
        return dataJson.optDouble(name, fallback);
    }

    public int optInt(String name) {
        return optInt(name, 0);
    }

    public int optInt(String name, int fallback) {
        if (isNull(name)) {
            return fallback;
        }
        return dataJson.optInt(name, fallback);
    }


    public long optLong(String name) {
        return optLong(name, 0L);
    }


    public long optLong(String name, long fallback) {
        if (isNull(name)) {
            return fallback;
        }
        return dataJson.optLong(name, fallback);
    }

    public String optString(String name) {
        return optString(name, "");
    }


    public String optString(String name, String fallback) {
        if (isNull(name)) {
            return fallback;
        }
        return dataJson.optString(name, fallback);
    }

    public JSONArray optJSONArray(String name) {
        Object object = opt(name);
        return object instanceof JSONArray ? (JSONArray) object : null;
    }


    public JSONObject optJSONObject(String name) {
        Object object = opt(name);
        return object instanceof JSONObject ? (JSONObject) object : null;
    }

}
