package main.erp.root.erp.main.erp.root.erp.Utils;

/**
 * Created by root on 12/3/15.
 */
public class CLassItem {
    String id;
    String className;

    public CLassItem(String id1, String className1) {
        id=id1;
        className=className1;


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
