package com.binkos.starlypancacke.database

import androidx.room.TypeConverter
import com.binkos.starlypancacke.database.entity.FoodDb
import com.binkos.starlypancacke.database.entity.OrganizationDb
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.stream.Collectors


class DatabaseConverters {

    @TypeConverter
    fun stringToOrganizations(json: String?): List<OrganizationDb?>? {
        val type: Type = object : TypeToken<List<OrganizationDb?>?>() {}.type
        return Gson().fromJson<List<OrganizationDb>>(json, type)
    }

    @TypeConverter
    fun organizationsToString(list: List<OrganizationDb?>?): String? {
        val type: Type = object : TypeToken<List<OrganizationDb?>?>() {}.type
        return Gson().toJson(list, type)
    }

    @TypeConverter
    fun stringToFood(json: String?): List<FoodDb?>? {
        val type: Type = object : TypeToken<List<FoodDb?>?>() {}.type
        return Gson().fromJson<List<FoodDb>>(json, type)
    }

    @TypeConverter
    fun foodsToString(list: List<FoodDb?>?): String? {
        val type: Type = object : TypeToken<List<FoodDb?>?>() {}.type
        return Gson().toJson(list, type)
    }

    @TypeConverter
    fun fromStrings(strings: List<String?>): String? {
        return strings.stream().collect(Collectors.joining(","))
    }

    @TypeConverter
    fun toStrings(data: String): List<String?> {
        return data.split(",".toRegex())
    }
}