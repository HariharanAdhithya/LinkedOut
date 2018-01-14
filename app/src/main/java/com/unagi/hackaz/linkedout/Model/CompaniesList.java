package com.unagi.hackaz.linkedout.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bhargav on 1/14/18.
 */

public class CompaniesList{

        @SerializedName("meta")
        @Expose
        private Meta meta;
        @SerializedName("objects")
        @Expose
        private ArrayList<Object> objects = null;

        public Meta getMeta() {
            return meta;
        }

        public void setMeta(Meta meta) {
            this.meta = meta;
        }

        public ArrayList<Object> getObjects() {
            return objects;
        }

        public void setObjects(ArrayList<Object> objects) {
            this.objects = objects;
        }

}
