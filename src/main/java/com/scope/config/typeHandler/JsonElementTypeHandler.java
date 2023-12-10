package com.scope.config.typeHandler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: juvenile
 * Date: 2018/11/5
 * Time: 18:42
 * Description: JsonElementTypeHandler
 */
@MappedTypes({JSON.class, JSONObject.class, JSONArray.class})
public class JsonElementTypeHandler extends NotNullResultTypeHandler<JSON> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, JSON parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.toString());
    }

    @Override
    public JSON getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String jsonSource = rs.getString(columnName);
        if (jsonSource != null) {
            return fromString(jsonSource);
        }
        return null;
    }

    @Override
    public JSON getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String jsonSource = rs.getString(columnIndex);
        if (jsonSource != null) {
            return fromString(jsonSource);
        }
        return null;
    }

    @Override
    public JSON getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String jsonSource = cs.getString(columnIndex);
        if (jsonSource != null) {
            return fromString(jsonSource);
        }
        return null;
    }

    private JSON fromString(String source) {
        JSON res;
        if (null == source)
            return null;
        try {
            res = JSONObject.parseObject(source);
        } catch (JSONException e) {
            res = JSONArray.parseArray(source);
        }
        return res;
    }
}
