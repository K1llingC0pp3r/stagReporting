package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;

public class Exam {
    @SerializedName("obsazeni")
    public String obsazeni;

    @SerializedName("mistnost")
    public String mistnost;
}