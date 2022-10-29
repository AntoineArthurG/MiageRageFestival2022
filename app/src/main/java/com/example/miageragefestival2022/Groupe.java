package com.example.miageragefestival2022;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Groupe {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;

    /**
     * No args constructor for use in serialization
     *
     */
    public Groupe() {
    }

    /**
     *
     * @param code
     * @param data
     * @param message
     */
    public Groupe(String code, String message, Data data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {

        @SerializedName("artiste")
        @Expose
        private String artiste;
        @SerializedName("texte")
        @Expose
        private String texte;
        @SerializedName("web")
        @Expose
        private String web;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("scene")
        @Expose
        private String scene;
        @SerializedName("jour")
        @Expose
        private String jour;
        @SerializedName("heure")
        @Expose
        private String heure;
        @SerializedName("time")
        @Expose
        private Integer time;

        /**
         * No args constructor for use in serialization
         *
         */
        //public Data() {}

        /**
         *
         * @param image
         * @param heure
         * @param jour
         * @param web
         * @param artiste
         * @param texte
         * @param time
         * @param scene
         */
        public Data(String artiste, String texte, String web, String image, String scene, String jour, String heure, Integer time) {
            super();
            this.artiste = artiste;
            this.texte = texte;
            this.web = web;
            this.image = image;
            this.scene = scene;
            this.jour = jour;
            this.heure = heure;
            this.time = time;
        }

        public String getArtiste() {
            return artiste;
        }

        public void setArtiste(String artiste) {
            this.artiste = artiste;
        }

        public String getTexte() {
            return texte;
        }

        public void setTexte(String texte) {
            this.texte = texte;
        }

        public String getWeb() {
            return web;
        }

        public void setWeb(String web) {
            this.web = web;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getScene() {
            return scene;
        }

        public void setScene(String scene) {
            this.scene = scene;
        }

        public String getJour() {
            return jour;
        }

        public void setJour(String jour) {
            this.jour = jour;
        }

        public String getHeure() {
            return heure;
        }

        public void setHeure(String heure) {
            this.heure = heure;
        }

        public Integer getTime() {
            return time;
        }

        public void setTime(Integer time) {
            this.time = time;
        }

    }
}