package br.com.app.colab.helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import br.com.app.colab.enumeration.AccessLevel;
import br.com.app.colab.enumeration.GenderEnum;
import br.com.app.colab.enumeration.SectorEnum;
import br.com.app.colab.model.CurrentUser;

public class CurrentUserBuilder {

    public static CurrentUser build(ArrayList response) throws JSONException{
        JSONArray jsonArray = new JSONArray(response);
        CurrentUser user = new CurrentUser();
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        user.setId(jsonObject.getLong("id"));
        user.setEmployerId(jsonObject.getLong("employerId"));
        user.setCpf(jsonObject.getString("cpf"));
        user.setUser_name(jsonObject.getString("user_name"));
        user.setGender(GenderEnum.valueOf(jsonObject.getString("gender")));
        user.setOccupation(jsonObject.getString("occupation"));
        user.setLocal_office(jsonObject.getString("local_office"));
        user.setSector(SectorEnum.valueOf(jsonObject.getString("sector")));
        user.setAccess_level(AccessLevel.valueOf(jsonObject.getString("access_level")));
        user.setPhoto_profile(jsonObject.getString("photo_profile_url"));
        return user;
    }
}
