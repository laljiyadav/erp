package main.erp.root.erp.main.erp.root.erp.Utils;

/**
 * Created by root on 12/3/15.
 */
public class SectionItem {

    String sid,secName;

    public SectionItem(String sid1, String sectionName1) {

        sid=sid1;
        secName=sectionName1;

    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSecName() {
        return secName;
    }

    public void setSecName(String secName) {
        this.secName = secName;
    }
}
