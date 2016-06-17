package com.example.dump;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.RunnableFuture;

/**
 * Created by Ishant Rana on 17/06/16.
 */
public class UnfoldSDK{

    private static Node root = null;
    private View view;
    private static Activity activity = null;

    public static void init(Activity activity, String key){
        new UnfoldSDK(activity);
    }

    private UnfoldSDK(final Activity activity){
        root = null;
        this.activity = activity;
        this.view = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                root = createTree(view);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }




    private Node createTree(View view){
        if(view == null){
            return null;
        }

        Node node = new Node();
        node.attributesMap = getAttributesHashMap(view);
        if(view instanceof ViewGroup){
            for(int i=0;i<((ViewGroup)view).getChildCount();i++){
                View nextChild = ((ViewGroup)view).getChildAt(i);
                Node newNode = createTree(nextChild);
                node.children.add(newNode);
            }
        }
        return node;
    }


    public static void dumpHierarchy(Activity activity){
        Gson gson = new Gson();
        if(root!=null){
            String string = gson.toJson(root);
            Log.i("dump",string);
            root = null;
        }
    }


    private HashMap<String ,Object> getAttributesHashMap(View view){
        HashMap<String, Object> hashMap = new HashMap<>();
        Attributes attributes = getAttributes(view);

        hashMap.put("class",attributes.getClassName());
        hashMap.put("clickable",attributes.isClickable());
        hashMap.put("scrollable",attributes.isScrollable());
        hashMap.put("bounds",attributes.getBounds().createList());
        return hashMap;
    }


    private Attributes getAttributes(View view){
        Attributes attributes = new Attributes();
        attributes.setClassName(view.getClass().getName());
        attributes.setClickable(view.isClickable());
        attributes.setScrollable(view.isScrollContainer());
        Bounds bounds = new Bounds();
        int left = view.getLeft();
        bounds.setLeft(left);
        int top = view.getTop();
        bounds.setTop(top);
        int height = view.getHeight();
        bounds.setHeight(height);
        int width = view.getWidth();
        bounds.setWidth(width);
        attributes.setBounds(bounds);
        return attributes;
    }

}
