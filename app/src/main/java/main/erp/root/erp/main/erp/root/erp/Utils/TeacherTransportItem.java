package main.erp.root.erp.main.erp.root.erp.Utils;

/**
 * Created by root on 12/4/15.
 */
public class TeacherTransportItem {

    String cardId,name,rollNo,status;

    public TeacherTransportItem(String carId1, String rollNo1, String name1, String status1) {

        status=status1;
        name=name1;
        cardId=carId1;
        rollNo=rollNo1;


    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
