package ru.otus.utils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VariablesUtil {
    private Map<String, String> varMap = new HashMap<>();

    public void setVarWithValue(String var, String value) {
        var = setRegexMask(var);
        if (varMap.containsKey(var)) {
            varMap.replace(var, value);
        }
        varMap.put(var, value);
    }

    public String getValueFromVar(String var) {
        if (varMap.containsKey(var)) {
            return varMap.get(var);
        }
        return "";
    }

    public String evalText(String text) {
        Pattern pattern = Pattern.compile("#\\{.+?\\}");
        Matcher matcher = pattern.matcher(text);
        List<String> varList = new ArrayList<>();
        while (matcher.find()) {
            varList.add(matcher.group());
        }
        for (String var : varList) {
            text = text.replaceAll(var, varMap.get(var));
        }
        return text;
    }

    private String setRegexMask(String var) {
        return "#{" + var + "}";
    }
}
