package org.mengyu.impl;

import org.mengyu.MergeService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuzhang
 * @date 2021/4/21 下午7:24
 * TODO
 */
public class MergeServiceImpl3 implements MergeService {
    @Override
    public List<String> mergeResult() {
        List<String> menus = new ArrayList<>();
        menus.add("group-3.1");
        menus.add("group-3.2");
        return menus;
    }
}
