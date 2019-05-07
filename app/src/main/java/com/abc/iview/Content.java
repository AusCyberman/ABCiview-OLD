package com.abc.iview;


import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.util.SparseArray;

import java.util.ArrayList;

import at.huber.youtubeExtractor.VideoMeta;
import at.huber.youtubeExtractor.YouTubeExtractor;
import at.huber.youtubeExtractor.YtFile;

public class Content{
    public static ArrayList<String> categories = new ArrayList<String>();
    public static ArrayList<String> channels = new ArrayList<String>();
    public String CONTENT_TYPE="CONTENT";



    private  Integer id =0;
    private String name="Deadlock";
    private String description="DEADLOCK crashes though the incredible highs, heartbreak, camaraderie, laughs and bittersweet sorrow of what it is to be a teenager. \n When a mysterious car crash exposes the dark underbelly of an idyllic paradise, it dramatically changes the lives of the teens it touches.";
    private Integer image = R.drawable.deadlock;
    private String channel = "abc";
    private String classification = "m";
    private Boolean isPopular = false;
    private  Boolean isAdult = false;
    private Video trailer;




    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getContentType() {
        return CONTENT_TYPE;
    }

    private Integer category;
    public Boolean getAdult() {
        switch(classification){
            case "m":
                return true;
            case "ma":
                return true;
        }
        return false;
    }

    public void setAdult(Boolean adult) {
        isAdult = adult;
    }


    public void setTrailer(String url,Context context) {
        trailer=new Video(this,url,context);
    }
    public Video getTrailer(){
        return trailer;
    }
    public Boolean isPopular() {
        return isPopular;
    }

    public void makepopular() {
        isPopular = true;
    }



    public Content(String TVShow,Integer category,String CONTENT_TYPE){
        this.CONTENT_TYPE=CONTENT_TYPE;
        name=TVShow;
        this.category=category;

    }
    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }
    public Integer getClassificationImage(){
        switch(classification) {
            case "g":
                return R.drawable.g;
            case "pg":
                return R.drawable.pg;

            case "m":
                return R.drawable.m;

            case "ma":
                return R.drawable.ma;



        }

        return null;
        }



        public Integer getChannelImage(){
        Integer channelresource = null;
            switch (channel){
                case "abc":
                    channelresource = R.drawable.ic_abc;
                    System.out.println("Channel:"+channel);
                    break;
                case "abcnews":
                    channelresource = R.drawable.ic_abcnews;
                    System.out.println("Channel:"+channel);
                    break;
                case "abcme":
                    channelresource = R.drawable.ic_abcme;
                    System.out.println("Channel:"+channel);
                    break;
                case "abckids":
                    channelresource = R.drawable.ic_abckids;
                    System.out.println("Channel:"+channel);
                    break;
                case "abccomedy":
                    channelresource = R.drawable.ic_abccomedy;

                    System.out.println("Channel:"+channel);
                    break;
                case "abcarts":
                    channelresource = R.drawable.ic_abcarts;
                    System.out.println("Channel:"+channel);
                    break;
                case "iviewpresents":
                    channelresource = R.drawable.ic_iviewpresents;
                    System.out.println("Channel:"+channel);
                    break;

            }
            return channelresource;
        }





    public static class Video {
        Content content;

        Uri turi;

        @SuppressLint("StaticFieldLeak")
        public Video(Content tvshow, String URL, Context context){
            this.content =tvshow;

            new YouTubeExtractor(context) {
                @Override
                public void onExtractionComplete(SparseArray<YtFile> ytFiles, VideoMeta vMeta) {
                    if (ytFiles != null) {

                        for(int itag=22;itag<44;itag++) {
                            if(ytFiles.get(itag)!=null) {
                                turi = Uri.parse(ytFiles.get(itag).getUrl());
                                return;
                            }
                        }

                    }
                }
            }.extract(URL, true, true);
            System.out.println("URL IS "+turi);
        }
        public Content getTVShow(){
            return content;
        }

        public Uri getUri() {

            return turi;
        }


    }
    public class Episode extends Video {
        String Description;
        Integer image;
        String classification;
        String name;
        Integer id;
        public Episode(Context context,Content content, String URL, String name, String description, String classification) {
            super(content, URL,context);
            this.name=name;
            this.Description = description;
            this.classification=classification;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getId() {
            return id;
        }

        public void setImage(Integer image) {
            this.image = image;
        }

        public Integer getImage() {
            return image;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return Description;
        }

        public Integer getClassificationImage() {
            switch (classification) {
                case "g":
                    return R.drawable.g;
                case "pg":
                    return R.drawable.pg;

                case "m":
                    return R.drawable.m;

                case "ma":
                    return R.drawable.ma;


            }
            return null;
        }
    }

public static class TVShow extends Content{

    private ArrayList<Episode> episodes= new ArrayList<>();
    public ArrayList<Episode> getEpisodes() {
        return episodes;
    }
    public Episode getEpisode(Integer id){
        return episodes.get(id);
    }



    public Episode addEpisode(Context context, String name, String description, String classification, String url) {
        Episode episode = new Episode(context,this,url,name,description,classification);
        episode.setId(episodes.size());
        episodes.add(episode);

        return episode;
    }
    public TVShow(String TVShow, Integer category) {


        super(TVShow, category,"TVSHOW");

    }
}


    }

