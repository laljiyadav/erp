package main.erp.root.erp.main.erp.root.erp.Utils;

/**
 * Created by root on 11/30/15.
 */
public class FeeItem {

    String feeMonth;
    String tutionFess;
    String gameFees;
    String computerFees;
    String examFees;
    String otherFees;
    String lateFees;
    String status;
    String totalFees;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public FeeItem(String feeMonth1, String tutionFess1, String gameFees1, String computerFees1, String examFees1, String otherFees1, String lateFees1, String totalFees1, String status1) {
        feeMonth=feeMonth1;
        tutionFess=tutionFess1;
        lateFees=lateFees1;
        gameFees=gameFees1;
        computerFees=computerFees1;
        examFees=examFees1;
        otherFees=otherFees1;
        totalFees=totalFees1;
        status=   status1;

    }

    public String getTotalFees() {
        return totalFees;
    }

    public void setTotalFees(String totalFees) {
        this.totalFees = totalFees;
    }

    public String getLateFees() {
        return lateFees;
    }

    public void setLateFees(String lateFees) {
        this.lateFees = lateFees;
    }

    public String getOtherFees() {
        return otherFees;
    }

    public void setOtherFees(String otherFees) {
        this.otherFees = otherFees;
    }

    public String getExamFees() {
        return examFees;
    }

    public void setExamFees(String examFees) {
        this.examFees = examFees;
    }

    public String getComputerFees() {
        return computerFees;
    }

    public void setComputerFees(String computerFees) {
        this.computerFees = computerFees;
    }

    public String getGameFees() {
        return gameFees;
    }

    public void setGameFees(String gameFees) {
        this.gameFees = gameFees;
    }

    public String getTutionFess() {
        return tutionFess;
    }

    public void setTutionFess(String tutionFess) {
        this.tutionFess = tutionFess;
    }

    public String getFeeMonth() {
        return feeMonth;
    }

    public void setFeeMonth(String feeMonth) {
        this.feeMonth = feeMonth;
    }
}
