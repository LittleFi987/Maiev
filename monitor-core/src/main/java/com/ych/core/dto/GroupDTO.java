package com.ych.core.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @Author yechenhao
 * @Date 17/04/2018
 */
public class GroupDTO implements Serializable {

    private static final long serialVersionUID = 8125217733811538422L;

    private Integer id;

    private String groupName;

    private List<GroupItem> items;

    public static class GroupItem {
        private Integer itemId;

        private String itemName;

        public Integer getItemId() {
            return itemId;
        }

        public void setItemId(Integer itemId) {
            this.itemId = itemId;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<GroupItem> getItems() {
        return items;
    }

    public void setItems(List<GroupItem> items) {
        this.items = items;
    }
}
