package com.dimitar.noli.vo;

import com.google.firebase.database.Exclude;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/**
 * Created by dimitar on 12.01.18.
 */


public class Note extends Observable {

    @SerializedName("id")
    public int id;
    @SerializedName("title")
    public String title;
    @SerializedName("description")
    public String description;


    public Note() {
    }

    public Note(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("title", title);
        result.put("description", description);

        return result;
    }

    @Override public String toString() {
        return "Note{" +
                "id=" + id +
                ", title=" + title +
                ", description=" + description +
                '}';
    }
}
