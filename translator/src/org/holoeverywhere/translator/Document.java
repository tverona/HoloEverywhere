
package org.holoeverywhere.translator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONObject;

public class Document {
    protected final Map<String, Map<String, String>> data = new HashMap<String, Map<String, String>>();
    protected final List<String> grab = new ArrayList<String>();
    protected String prefix, filenamePattern;
    protected boolean ignoreDefaultLocale;

    public synchronized Map<String, Map<String, String>> mergeData(Grabber grabber) {
        Map<String, Map<String, String>> map;
        grabber.reset();
        grabber.grab(grab.toArray(new String[grab.size()]));
        map = new HashMap<String, Map<String, String>>(grabber.getData());
        grabber.reset();
        for (Entry<String, Map<String, String>> entry : data.entrySet()) {
            Map<String, String> submap = map.get(entry.getKey());
            if (submap == null) {
                map.put(entry.getKey(), entry.getValue());
            } else {
                submap.putAll(entry.getValue());
            }
        }
        return map;
    }

    @SuppressWarnings("unchecked")
    public Document parse(JSONObject object) {
        grab.clear();
        data.clear();
        prefix = object.optString("prefix", null);
        filenamePattern = object.optString("filename_pattern", "%s.xml");
        ignoreDefaultLocale = object.optBoolean("ignore_default_locale", false);
        if (object.has("grab")) {
            JSONArray grab = object.optJSONArray("grab");
            for (int i = 0; i < grab.length(); i++) {
                String forGrab = grab.optString(i);
                if (forGrab != null) {
                    this.grab.add(forGrab);
                }
            }
        }
        if (object.has("data")) {
            JSONObject data = object.optJSONObject("data");
            Iterator<String> keys = data.sortedKeys();
            while (keys.hasNext()) {
                final String key = keys.next();
                if (key == null || key.length() == 0) {
                    continue;
                }
                Map<String, String> map = new HashMap<String, String>();
                this.data.put(key, map);
                JSONObject translates = data.optJSONObject(key);
                Iterator<String> locales = translates.sortedKeys();
                while (locales.hasNext()) {
                    final String locale = locales.next();
                    if (locale == null || locale.length() == 0) {
                        continue;
                    }
                    map.put(locale, translates.optString(locale));
                }
            }
        }
        return this;
    }

    public String getNameForEntry(String key) {
        if (prefix == null) {
            return key;
        }
        return prefix + key;
    }
}
