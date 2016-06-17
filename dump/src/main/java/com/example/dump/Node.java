package com.example.dump;

import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Ishant Rana on 17/06/16.
 */
public class Node {

    Map<String,Object> attributesMap;

    List<Node> children;



    public Node(){
        attributesMap = null;
        children = new ArrayList<>();
    }

}
