package com.base.recursion;

import java.util.ArrayList;
import java.util.List;

public class Tree {
    public static void main(String[] args) {
        List<ScreenAreaInfo> list = new ArrayList<>();
        list.add(new ScreenAreaInfo(1, 0, "a"));
        list.add(new ScreenAreaInfo(6, 2, "a26"));
        list.add(new ScreenAreaInfo(7, 3, "a37"));
        list.add(new ScreenAreaInfo(8, 3, "a37"));
        list.add(new ScreenAreaInfo(18, 8, "a378"));
        list.add(new ScreenAreaInfo(2, 1, "a2"));
        list.add(new ScreenAreaInfo(3, 1, "a3"));
        list.add(new ScreenAreaInfo(4, 1, "a4"));
        list.add(new ScreenAreaInfo(5, 1, "a5"));

//        List<Integer> ids = new LinkedList<>();
//        List<Integer> s = getCityId(3, ids, list);
//        System.out.println(s);

        List<ScreenAreaInfo> list1 = buildByScreenAreaInfo(list);
        System.out.println(list1);
    }

    private static List<ScreenAreaInfo> buildByScreenAreaInfo(List<ScreenAreaInfo> treeNodes) {
        List<ScreenAreaInfo> trees = new ArrayList<ScreenAreaInfo>();
        for (ScreenAreaInfo treeNode : treeNodes) {
            if (treeNode.getParentId().equals(0)) {
                trees.add(findChildren(treeNode,treeNodes));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     */
    private static ScreenAreaInfo findChildren(ScreenAreaInfo treeNode,List<ScreenAreaInfo> treeNodes) {
        for (ScreenAreaInfo it : treeNodes) {
            if(treeNode.getId().equals(it.getParentId())) {
                if (treeNode.getDataList() == null) {
                    treeNode.setDataList(new ArrayList<ScreenAreaInfo>());
                }
                treeNode.getDataList().add(findChildren(it,treeNodes));
            }
        }
        return treeNode;
    }

    /**
     * 所有下级
     * @param cityCode
     * @param ids
     * @param areaInfoList
     * @return
     */
    public static List<Integer> getCityId(Integer cityCode, List<Integer> ids, List<ScreenAreaInfo> areaInfoList) {

        //传一个城市id  拿到该城市下的所有子集区域的id
//        List<ScreenAreaInfo> cityByCountyId = list;
        for (ScreenAreaInfo screenAreaInfo : areaInfoList) {
            if (screenAreaInfo.getParentId() == cityCode) {
                ids.add(screenAreaInfo.getId());
                getCityId(screenAreaInfo.getId(), ids, areaInfoList);
            }

        }

        return ids;
    }
}

class ScreenAreaInfo {
    private Integer id;

    private Integer parentId;

    private String name;

    private List<ScreenAreaInfo> dataList;

    public ScreenAreaInfo(Integer id, Integer parentId, String name) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ScreenAreaInfo> getDataList() {
        return dataList;
    }

    public void setDataList(List<ScreenAreaInfo> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
//        return "ScreenAreaInfo{" +
//                "id=" + id +
//                ", parentId=" + parentId +
//                ", name='" + name + '\'' +
//                ", dataList=" + dataList +
//                '}';

        return "{" + id +
                ", " + parentId +
                ", " + name +
                ", " + dataList +
                '}';
    }
}
